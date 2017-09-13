package ru.proitr.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.proitr.example.bean.URL;
import ru.proitr.example.utils.ProjectUtils;

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
        mv.addAttribute("user", ProjectUtils.getLogin());
        log.info("end");

        return "freemarker/index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView getAdminPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("message","This admin page");
        model.addObject("user", ProjectUtils.getLogin());
        model.setViewName("freemarker/auth/admin");

        return model;
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public ModelAndView getManagerPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("message","This manager page");
        model.addObject("user", ProjectUtils.getLogin());
        model.setViewName("freemarker/auth/manager");

        return model;
    }

    @RequestMapping(value = "/anonymouse", method = RequestMethod.POST)
    public ModelAndView getAnonymousePage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("message","This anonymouse page");
        model.addObject("user", ProjectUtils.getLogin());
        model.setViewName("freemarker/auth/anonymouse");

        return model;
    }
}