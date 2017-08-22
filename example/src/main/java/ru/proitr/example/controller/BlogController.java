package ru.proitr.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.proitr.example.bean.URL;
import ru.proitr.example.domain.Test1;
import ru.proitr.example.repository.Test1Repository;

import java.io.IOException;
import java.util.List;

@Controller
@Transactional
public class BlogController
{
    private static Logger log = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private Test1Repository test1Repository;

    @Autowired
    private URL url;

    @RequestMapping("/")
    public String getBlog(Model mv)
    {
        log.info("start");
        mv.addAttribute("title", "Freemarker Template Demo using Spring");
        mv.addAttribute("message", "Getting started with Freemarker.<br/>Find a Freemarker templates demo using Spring.");
        mv.addAttribute("references", url.getUrlList());
        log.info("end");

        return "freemarker/index";
    }

    @RequestMapping("/getTest1")
    public String getTest1(Model mv)
    {
        try
        {
            List<Test1> dat = test1Repository.findTest1("Ivanov");
            //Test1 dat = test1Repository.findOne("1");
            log.info("end list1");
        }
        catch (BeanCreationException ex)
        {
            throw new RuntimeException(ex);
        }

        log.info("end list");

        return "freemarker/index";
    }

    @RequestMapping(value = "/admin")
    public ModelAndView getAdminPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("message","This admin page");
        model.setViewName("freemarker/admin");

        return model;
    }

    @RequestMapping(value = "/manager")
    public ModelAndView getManagerPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("message","This manager page");
        model.setViewName("freemarker/manager");

        return model;
    }

    @RequestMapping(value = "/anonymouse")
    public ModelAndView getAnonymousePage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("message","This anonymouse page");
        model.setViewName("freemarker/anonymouse");

        return model;
    }

}