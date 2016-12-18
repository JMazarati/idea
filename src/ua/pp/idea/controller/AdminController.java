package ua.pp.idea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private JavaMailSender mailSender;

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
@PreAuthorize(value = "hasRole(ROLE_ADMINISTRATOR)")
    @RequestMapping(value = "/sendmail",method = RequestMethod.GET)
    public String sendMail(){
        String recipientAddress = "alice_4@rambler.ru";
        String subject = "testSubject";
        String message = "testMessage";
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);

        // sends the e-mail
        mailSender.send(email);

        return "index";
    }
}
