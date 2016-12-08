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
import ua.pp.idea.dao.CategoryDaoImpl;
import ua.pp.idea.dao.CommentDao;
import ua.pp.idea.dao.IdeaDaoImpl;
import ua.pp.idea.dao.UserDaoImpl;
import ua.pp.idea.entity.Category;
import ua.pp.idea.entity.Comment;
import ua.pp.idea.entity.Idea;
import ua.pp.idea.validator.AddcommentValidator;
import ua.pp.idea.validator.AddideaValidator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;
import java.util.List;


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
    CommentDao cdi;
    @Autowired
    CategoryDaoImpl catdi;
    @Autowired
    ServletContext context;
    @Autowired
    AddideaValidator addideaValidator;
    @Autowired
    AddcommentValidator addcommentValidator;

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

        Map<Integer, String> category = new LinkedHashMap<Integer, String>();

        for (Category item : catdi.getAllCategory()) {
            category.put(item.getId(), item.getTitle());
        }

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
    public String showid(@PathVariable("id") String s, Model uiModel, Comment myComment) {


        ArrayList<Comment> allComments = new ArrayList<>();
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");

        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);


        try {
            if(ide.findIdeaByID(Integer.parseInt(s)).getId()!=0){
                uiModel.addAttribute("check", ide.findIdeaByID(Integer.parseInt(s)));


                allComments.addAll(cdi.getAllCommentsByIdeaLink(Integer.parseInt(s)));


                uiModel.addAttribute("child", allComments);
                uiModel.addAttribute("count", cdi.getCnt());
            }
            else {
                return "redirect:/viewidea";
            }



        } catch (Exception e) {

            uiModel.addAttribute("err", e + "getStackTrace() " + e.getStackTrace() + "getMessage " + e.getMessage());
            //uiModel.addAttribute("comments", cdi.getAllCommentsByIdeaLink(1));
            //uiModel.addAttribute("comments1", cdi.getAllCommentsByIdeaLink(1));
            return "show";
        }

        uiModel.addAttribute("command", myComment);
        return "show";
    }

    @RequestMapping(value = "/addcomments", method = RequestMethod.POST)
    public String addComments(@ModelAttribute Comment comment, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        addcommentValidator.validate(comment, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            return "redirect:/viewidea/" + comment.getIdeaLink();
        }
        comment.setNote(Smile(comment.getNote()));
        cdi.createNewComments(comment);
        return "redirect:/viewidea/" + comment.getIdeaLink();
    }

    private void saveImage(String fileName, MultipartFile multipartFile) {
        try {


            File file = new File(context.getRealPath("/") + "/resources/upload_image/" + fileName);
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        } catch (Exception ex) {
            System.out.print(ex);
        }

    }

    @RequestMapping(value = "/deleteIdea")
    public String deleteIdea(Model myModel, @RequestParam(value = "id", defaultValue = "0") String id) {

        try{
            Idea checkIdea = ide.findIdeaByID(Integer.parseInt(id));
            if (checkIdea.getUsername().toLowerCase().equals(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase())||
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMINISTRATOR]")) {
                ide.deleteIdeaById(checkIdea);
                return "redirect:/userregerror?error=5";
            }

        }catch (Exception e){
            myModel.addAttribute("e",e);
            return "redirect:/userregerror?error=6";
        }

        return "redirect:/userregerror?error=7";

    }

    static String Smile(String smile){
        String aa = smile.replaceAll(":aa:", "<img src=\"/resources/smiles/aa.gif\"/>");
        String ab = aa.replaceAll(":ab:", "<img src=\"/resources/smiles/ab.gif\"/>");
        String ac = ab.replaceAll(":ac:", "<img src=\"/resources/smiles/ac.gif\"/>");
        String ad = ac.replaceAll(":ad:", "<img src=\"/resources/smiles/ad.gif\"/>");
        String ae = ad.replaceAll(":ae:", "<img src=\"/resources/smiles/ae.gif\"/>");
        String af = ae.replaceAll(":af:", "<img src=\"/resources/smiles/af.gif\"/>");
        String ag = af.replaceAll(":ag:", "<img src=\"/resources/smiles/ag.gif\"/>");
        String ah = ag.replaceAll(":ah:", "<img src=\"/resources/smiles/ah.gif\"/>");
        String ai = ah.replaceAll(":ai:", "<img src=\"/resources/smiles/ai.gif\"/>");
        String aj = ai.replaceAll(":aj:", "<img src=\"/resources/smiles/aj.gif\"/>");
        String ak = aj.replaceAll(":ak:", "<img src=\"/resources/smiles/ak.gif\"/>");
        String al = ak.replaceAll(":al:", "<img src=\"/resources/smiles/al.gif\"/>");
        String am = al.replaceAll(":am:", "<img src=\"/resources/smiles/am.gif\"/>");
        String an = am.replaceAll(":an:", "<img src=\"/resources/smiles/an.gif\"/>");
        String ao = an.replaceAll(":ao:", "<img src=\"/resources/smiles/ao.gif\"/>");
        String ap = ao.replaceAll(":ap:", "<img src=\"/resources/smiles/ap.gif\"/>");
        String aq = ap.replaceAll(":aq:", "<img src=\"/resources/smiles/aq.gif\"/>");
        String at = aq.replaceAll(":at:", "<img src=\"/resources/smiles/at.gif\"/>");
        String au = at.replaceAll(":au:", "<img src=\"/resources/smiles/au.gif\"/>");
        String av = au.replaceAll(":av:", "<img src=\"/resources/smiles/av.gif\"/>");
        String aw = av.replaceAll(":aw:", "<img src=\"/resources/smiles/aw.gif\"/>");
        String ax = aw.replaceAll(":ax:", "<img src=\"/resources/smiles/ax.gif\"/>");
        String ay = ax.replaceAll(":ay:", "<img src=\"/resources/smiles/ay.gif\"/>");
        String az = ay.replaceAll(":az:", "<img src=\"/resources/smiles/az.gif\"/>");
        return az;
    }
}
