package ru.proitr.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.proitr.example.bean.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Transactional
@SessionAttributes("roles")
public class BlogController
{
    private static Logger log = LoggerFactory.getLogger(BlogController.class);

    @Autowired private URL url;

    @RequestMapping("/")
    public String getBlog(Model mv)
    {
        log.info("start");
        mv.addAttribute("title", "Freemarker Template Demo using Spring");
        mv.addAttribute("message", "Getting started with Freemarker.<br/>Find a Freemarker templates demo using Spring.");
        mv.addAttribute("references", url.getUrlList());
        mv.addAttribute("user", getUser());
        log.info("end");

        return "freemarker/index";
    }

    @RequestMapping("/getTest1")
    public String getTest1(Model mv)
    {
        try
        {
            log.info("end list1");
        }
        catch (BeanCreationException ex)
        {
            throw new RuntimeException(ex);
        }

        log.info("end list");

        return "freemarker/index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView getAdminPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("message","This admin page");
        model.addObject("user", getUser());
        model.setViewName("freemarker/auth/admin");

        return model;
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public ModelAndView getManagerPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("message","This manager page");
        model.addObject("user", getUser());
        model.setViewName("freemarker/auth/manager");

        return model;
    }

    @RequestMapping(value = "/anonymouse", method = RequestMethod.POST)
    public ModelAndView getAnonymousePage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("message","This anonymouse page");
        model.addObject("user", getUser());
        model.setViewName("freemarker/auth/anonymouse");

        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error",required = false) String error,
                              @RequestParam(value = "logout",	required = false) String logout)
    {
        ModelAndView model = new ModelAndView();

        if (error != null)
        {
            model.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null)
        {
            model.addObject("message", "Logged out from JournalDEV successfully.");
        }

        model.setViewName("freemarker/auth/login");

        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null)
        {
            //persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        model.setViewName("freemarker/auth/logout");

        return model;
    }

    private String getUser()
    {
        String userName = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
        {
            userName = ((UserDetails) principal).getUsername();
        }
        else
        {
            userName = principal.toString();
        }

        return userName;
    }
}