package ua.pp.idea.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.idea.dao.IdeaDaoImpl;
import ua.pp.idea.dao.UserDaoImpl;
import ua.pp.idea.entity.Idea;
import ua.pp.idea.validator.AddideaValidator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    @Autowired
    ServletContext context;
    @Autowired
    AddideaValidator addideaValidator;

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
    public String updateForm(@ModelAttribute Idea myIdea, Model model) {


        Map<String, String> category = new LinkedHashMap<String, String>();
        //ModelAndView nmv = new ModelAndView();

        category.put("1", "Обобщенная тематика");
        category.put("2", "Безопасность");
        category.put("3", "Вопросы и ответы");
        category.put("4", "День святого Валентина");
        category.put("5", "Интернет");
        category.put("6", "ИТ");
        category.put("7", "Автомобили");
        category.put("8", "Спорт");
        category.put("9", "Обучение");
        category.put("10", "Дом");

        model.addAttribute("cat", category);


        BindingResult bindingResult = (BindingResult) model.asMap().get("b1");
        model.addAttribute("command", myIdea);
        model.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addidea";

    }

    @RequestMapping(value = "/addideapost", method = RequestMethod.POST)

    public String update(@ModelAttribute Idea myIdea, HttpServletRequest httpServletRequest, Model model, RedirectAttributes redirectAttributes, BindingResult bindingResult,
                         @RequestParam(value = "p", required = false) MultipartFile image) {

        myIdea.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        myIdea.setFile(image);

        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        //Принимает данные формы и обрабатывает их


        //upload pict
        if (!image.isEmpty()) {
            saveImage(SecurityContextHolder.getContext().getAuthentication().getName() + "_" + image.getOriginalFilename(), image);
            myIdea.setPict(SecurityContextHolder.getContext().getAuthentication().getName() + "_" + image.getOriginalFilename());
            myIdea.setFile(image);
        }
        addideaValidator.validate(myIdea, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            return rdrct + "/addidea";
        }
        if (myIdea.getCaption().isEmpty()) {
            myIdea.setCaption("Idea by " + SecurityContextHolder.getContext().getAuthentication().getName());
        }



        ide.createIdea(myIdea);
        return rdrct + "/viewidea";



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

    private void saveImage(String fileName, MultipartFile multipartFile) {
        try {


            File file = new File(context.getRealPath("/") + "/resources/upload_image/" + fileName);
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

}
