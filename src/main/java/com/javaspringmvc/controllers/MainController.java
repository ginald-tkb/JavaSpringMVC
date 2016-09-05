package com.javaspringmvc.controllers;

import com.javaspringmvc.models.AuthCredentials;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by tshiamotaukobong on 2016/09/03.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/login", method = POST)
    public String login(@ModelAttribute AuthCredentials authCredentials, HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().setAttribute("username",authCredentials.getUsername());
        return  "redirect:Home";
    }

    @RequestMapping(value = "/Home", method = GET)
    public String home(HttpServletRequest httpServletRequest, ModelMap model) {
        model.addAttribute("username", httpServletRequest.getSession().getAttribute("username"));
        return  "Home";
    }
}
