package ua.pp.idea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.pp.idea.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dark on 19.11.2016.
 */
@Controller
public class AdminController {

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    String viewAdmin(HttpServletRequest request, Model model){
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();

        String webRootPath=context.getRealPath("/");
        String authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        //User user=null;
        //user.setUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        model.addAttribute("scheme",scheme);
        model.addAttribute("serverName",serverName);
        model.addAttribute("serverPort",serverPort);
        model.addAttribute("contextPath",contextPath);
        model.addAttribute("webRootPath",webRootPath);
        model.addAttribute("authorities0",authorities);
        model.addAttribute("authorities1",SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("user",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "admin";
    }
}
