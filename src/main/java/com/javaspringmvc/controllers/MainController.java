package com.javaspringmvc.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaspringmvc.models.AuthCredentials;
import com.javaspringmvc.models.Project;
import com.javaspringmvc.models.Token;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import com.sun.tools.javac.util.Log;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static sun.jvm.hotspot.debugger.win32.coff.DebugVC50X86RegisterEnums.TAG;

/**
 * Created by tshiamotaukobong on 2016/09/03.
 */

@RequestMapping("/")
@Controller
public class MainController {

    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method=GET)
    public String index()
    {
        return "index";
    }

    @RequestMapping(value = "/login", method = POST)
    public String login(@ModelAttribute AuthCredentials authCredentials, Model model) {
        try {
            Token token = restTemplate.postForObject("http://userservice.staging.tangentmicroservices.com/api-token-auth/",
                    authCredentials, Token.class);
            httpServletRequest.getSession().setAttribute("token", token.getToken());
        }
        catch (HttpClientErrorException e)
        {
            model.addAttribute("errorMessage", "Failed to login");
            return "index";
        }
        return  "redirect:Home";
    }

    @RequestMapping(value = "/Home", method = GET)
    public String home(ModelMap model) {
        try {

            if(httpServletRequest.getSession().getAttribute("token")==null)
                return "index";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Authorization","Token " + httpServletRequest.getSession().getAttribute("token").toString());


            HttpEntity entity = new HttpEntity(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    "http://projectservice.staging.tangentmicroservices.com:80/api/v1/projects/", HttpMethod.GET, entity, String.class, String.class);
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(response.getBody());
            Project[] projects = mapper.readValue(response.getBody(), Project[].class);
            httpServletRequest.getSession().setAttribute("projects", projects);
//            model.addAttribute("token", httpServletRequest.getSession().getAttribute("token"));
            model.addAttribute("projects", projects);
        }
        catch (HttpClientErrorException e)
        {
            model.addAttribute("errorMessage", "Request failed");
            return "Home";
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "Home";
    }
}
