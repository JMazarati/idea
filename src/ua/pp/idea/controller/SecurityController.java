package ua.pp.idea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.expression.Expression;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.idea.dao.UserDaoImpl;
import ua.pp.idea.entity.Idea;
import ua.pp.idea.entity.User;
import ua.pp.idea.validator.SignupValidator;
import ua.pp.idea.validator.UpduserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dark on 07.11.2016.
 */
@Controller

public class SecurityController {
    @Autowired
    UserDaoImpl udi;
    @Autowired
    private SignupValidator signupValidator;
    @Autowired
    private UpduserValidator upduserValidator;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @RequestMapping("/userregerror")
    public String userregerror(@RequestParam(value = "error", defaultValue = "0") String error, Model uiModel, Locale locale) {
        String flashpatam = "";
        uiModel.addAttribute("error", error);
        try {
            flashpatam = uiModel.asMap().get("usererror").toString();
        } catch (NullPointerException ex) {

        }
        uiModel.addAttribute("message", flashpatam);
        return "userregerror";
    }

    @RequestMapping("/loginfail")
    public String loginFail(Model uiModel, Locale locale) {


        uiModel.addAttribute("message", "Error");
        return "index";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User myUser, Model model, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        //Принимает данные формы и обрабатывает их
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        signupValidator.validate(myUser, bindingResult);
        if (bindingResult.hasErrors()) {

            model.asMap().clear();
            redirectAttributes.addFlashAttribute("b", bindingResult);
            return rdrct + "/reg";
        } else {
            myUser.setUserpwd(passwordEncoder.encode(myUser.getUserpwd()));
            myUser.setDatereg(LocalDate.now());
            try {
                udi.createUser(myUser);
            } catch (Exception e) {
                return rdrct + "/userregerror?error=3";
            }
            return rdrct + "/userregerror?error=4";
        }


    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String createUserForm(@ModelAttribute User myUser, Model model) {

        BindingResult bindingResult = (BindingResult) model.asMap().get("b");
        model.addAttribute("command", myUser);
        model.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "reg";
    }

    @RequestMapping(value = "/editprofile",method = RequestMethod.GET)
    public String editProfile(User myUser, Model model) {
        myUser.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BindingResult bindingResult = (BindingResult) model.asMap().get("b");
        model.addAttribute("command", myUser);
        model.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "editprofile";
    }
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/editprofilepost",method = RequestMethod.POST)
    public String editprofilepost(@ModelAttribute User myUser,HttpServletRequest httpServletRequest, Model model, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        upduserValidator.validate(myUser, bindingResult);
        if(bindingResult.hasErrors()){
            model.asMap().clear();
            redirectAttributes.addFlashAttribute("b", bindingResult);
            return rdrct+"/editprofile";
        }
        try {
            myUser.setUserpwd(passwordEncoder.encode(myUser.getUserpwd()));
            udi.updateUser(myUser);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("e", e);
            return "redirect:/userregerror?error=6";
        }
        return rdrct+"/myoffice";
    }
}
