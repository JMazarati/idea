package ua.pp.idea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.pp.idea.dao.IdeaDaoImpl;
import ua.pp.idea.dao.UserDaoImpl;
import ua.pp.idea.entity.Idea;
import ua.pp.idea.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Dark on 07.11.2016.
 */
@Controller
public class IdeaController {
    @Autowired
    IdeaDaoImpl ide;
    @Autowired
    UserDaoImpl udi;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String HomeController(Model uiModel) {


        return "index";
    }

    @RequestMapping(value = "/viewidea", method = RequestMethod.GET)
    public String ViewIdea(@ModelAttribute() Idea myidea, Model uiModel) {
        List<Idea> list = ide.getAll();
        uiModel.addAttribute("list", list);

        return "viewidea";
    }

    @RequestMapping(value = "/addidea", method = RequestMethod.GET)
    public ModelAndView updateForm() {



        Map<String, String> category = new LinkedHashMap<String, String>();
        ModelAndView nmv = new ModelAndView();

        category.put("1","Обобщенная тематика");
        category.put("2","Безопасность");
        category.put("3","Вопросы и ответы");
        category.put("4","День святого Валентина");
        category.put("5","Интернет");
        category.put("6","ИТ");
        category.put("7","Автомобили");
        category.put("8","Спорт");
        category.put("9","Обучение");
        category.put("10","Дом");
        nmv.setViewName("addidea");


        nmv.getModel().put("cat",category);
        nmv.addObject("command", new Idea());


        return nmv;//new ModelAndView("addidea", "command", new Idea());
    }

    @RequestMapping(value = "/addideapost", method = RequestMethod.POST)
    public String update(@ModelAttribute() Idea idea, HttpServletRequest httpServletRequest, Model model, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct="redirect:"+scheme+serverName+serverPort;
        //Принимает данные формы и обрабатывает их

        //       if(bindingResult.hasErrors()){
//            model.addAttribute("message", "ERROR")
//       }
        if(idea.getCaption().isEmpty()){idea.setCaption("Idea by "+ SecurityContextHolder.getContext().getAuthentication().getName());}
        if (idea.getTxt().isEmpty() && idea.getPict().isEmpty() && idea.getVideo().length() != 11) {

            return rdrct+"/viewidea";
        } else {

            idea.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            ide.createIdea(idea);
            return rdrct+"/viewidea";
        }

    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Index(Model uiModel) {

        uiModel.addAttribute("list", "addidea");

        return "index";
    }

    @RequestMapping(value = "/myoffice", method = RequestMethod.GET)
    public String MyOffice(Model uiModel) {
        String txt = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDate today = LocalDate.now();

        uiModel.addAttribute("txt", today);
        return "myoffice";

    }

    @RequestMapping(value = "/viewidea/{id}", method = RequestMethod.GET)
    public String showid(@PathVariable("id") String i, Model uiModel) {

        try {
            uiModel.addAttribute("check", ide.findIdeaByID(Integer.parseInt(i)));
        } catch (Exception e) {
            uiModel.addAttribute("check", ide.findIdeaByID(1));
        }


        return "show";
    }


}
