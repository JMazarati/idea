package ua.pp.idea.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.idea.dao.*;
import ua.pp.idea.entity.*;
import ua.pp.idea.validator.AddcommentValidator;
import ua.pp.idea.validator.AddideaValidator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.List;


/**
 * Created by Dark on 07.11.2016.
 */
@Controller
public class IdeaController {
    @Autowired
    IdeaDao ide;
    @Autowired
    UserDaoImpl udi;
    @Autowired
    CommentDao cdi;
    @Autowired
    CategoryDaoImpl catdi;
    @Autowired
    VoteDao voteDao;
    @Autowired
    ServletContext context;
    @Autowired
    AddideaValidator addideaValidator;
    @Autowired
    AddcommentValidator addcommentValidator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String HomeController(Model uiModel, RedirectAttributes redirectAttributes) {
        List<Idea> topList = null;
        Idea myIdea = new Idea();
        try {
            topList = ide.getTop5();
            if (topList.size() < 5) {
                for (int i = 0; i < 5; i++) {
                    if (i > topList.size() - 1) {
                        myIdea.setId(i);
                        myIdea.setPict("idea.png");
                        myIdea.setCaption("Здесь могла бы быть ваша идея<br />Here could be your idea!");
                        myIdea.setTxt("<h1>Здесь могла бы быть ваша идея!<br />Here could be your idea!</h1>");
                        topList.add(myIdea);

                    }
                }
                uiModel.addAttribute("top5", topList);
            } else uiModel.addAttribute("top5", topList);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("e", e);
            return "redirect:/userregerror?error=6";
        }

        uiModel.addAttribute("tabclass1", "active");
        uiModel.addAttribute("tabclass2", "nonactive");
        uiModel.addAttribute("tabclass3", "nonactive");
        uiModel.addAttribute("tabclass4", "nonactive");
        uiModel.addAttribute("tabclass5", "nonactive");

        return "default";
    }

    @RequestMapping(value = "/viewidea", method = RequestMethod.GET)
    public String ViewIdea(@RequestParam(defaultValue = "") String sort, Model uiModel, RedirectAttributes redirectAttributes) {
        uiModel.addAttribute("tabclass1", "nonactive");
        uiModel.addAttribute("tabclass2", "active");
        uiModel.addAttribute("tabclass3", "nonactive");
        uiModel.addAttribute("tabclass4", "nonactive");
        uiModel.addAttribute("tabclass5", "nonactive");
        List<Idea> list;
        try {
            if(sort.equals(""))list=ide.getAllIdeaOrderById();
            else list = ide.getAll(Boolean.valueOf(sort));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("e", e);
            return "redirect:/userregerror?error=6";
        }
        Map<Integer, String> category = new LinkedHashMap<Integer, String>();

        for (Category item : catdi.getAllCategory()) {
            category.put(item.getId(), item.getTitle());
        }

        uiModel.addAttribute("cat", category);
        uiModel.addAttribute("date", "/viewidea?sort=true");
        uiModel.addAttribute("rating", "/viewidea?sort=false");
        uiModel.addAttribute("list", list);

        return "viewidea";
    }

    //END OF BLOCK
    //------------------------------------------------------------------------------------------------------------------
    //SELECT IDEA BY TAG
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String ViewIdeaByTag(@RequestParam(value = "tag", defaultValue = "") String tag, @RequestParam(defaultValue = "true") String sort,
                                Model uiModel, RedirectAttributes redirectAttributes) {
        List<Idea> list;
        try {
            list = ide.findIdeaByTag("%" + tag + "%", Boolean.valueOf(sort));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("e", e);
            return "redirect:/userregerror?error=6";
        }
        Map<Integer, String> category = new LinkedHashMap<Integer, String>();

        for (Category item : catdi.getAllCategory()) {
            category.put(item.getId(), item.getTitle());
        }
        uiModel.addAttribute("cat", category);
        uiModel.addAttribute("date", "/tags?tag=" + tag + "&sort=true");
        uiModel.addAttribute("rating", "/tags?tag=" + tag + "&sort=false");
        uiModel.addAttribute("list", list);
        uiModel.addAttribute("tabclass1", "nonactive");
        uiModel.addAttribute("tabclass2", "active");
        uiModel.addAttribute("tabclass3", "nonactive");
        uiModel.addAttribute("tabclass4", "nonactive");
        uiModel.addAttribute("tabclass5", "nonactive");
        return "viewidea";
    }

    //END OF BLOCK
    //------------------------------------------------------------------------------------------------------------------
    //SELECT IDEA BY CATEGORY
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String ViewIdeaByCat(@RequestParam(value = "cat", defaultValue = "0") String cat, @RequestParam(defaultValue = "true") String sort, Model uiModel) {
        List<Idea> list;
        try {
            list = ide.findIdeaByCategory(Integer.parseInt(cat), Boolean.valueOf(sort));
        } catch (NumberFormatException ex) {
            return "redirect:/userregerror?error=6";

        }
        Map<Integer, String> category = new LinkedHashMap<Integer, String>();

        for (Category item : catdi.getAllCategory()) {
            category.put(item.getId(), item.getTitle());
        }
        uiModel.addAttribute("cat", category);
        uiModel.addAttribute("date", "/category?cat=" + cat + "&sort=true");
        uiModel.addAttribute("rating", "/category?cat=" + cat + "&sort=false");
        uiModel.addAttribute("list", list);
        uiModel.addAttribute("tabclass1", "nonactive");
        uiModel.addAttribute("tabclass2", "active");
        uiModel.addAttribute("tabclass3", "nonactive");
        uiModel.addAttribute("tabclass4", "nonactive");
        uiModel.addAttribute("tabclass5", "nonactive");
        return "viewidea";
    }

    //END BLOCK
    //------------------------------------------------------------------------------------------------------------------
    //SELECT IDEA BY USER
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String ViewIdeaByUser(@RequestParam(value = "usr", defaultValue = "") String usr, @RequestParam(defaultValue = "true") String sort,
                                 Model uiModel, RedirectAttributes redirectAttributes) {
        List<Idea> list;
        try {
            list = ide.findIdeaByUser(usr, Boolean.valueOf(sort));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("e", e);
            return "redirect:/userregerror?error=6";
        }
        Map<Integer, String> category = new LinkedHashMap<Integer, String>();

        for (Category item : catdi.getAllCategory()) {
            category.put(item.getId(), item.getTitle());
        }
        uiModel.addAttribute("cat", category);
        uiModel.addAttribute("date", "/user?usr=" + usr + "&sort=true");
        uiModel.addAttribute("rating", "/user?usr=" + usr + "&sort=false");
        uiModel.addAttribute("list", list);
        uiModel.addAttribute("tabclass1", "nonactive");
        uiModel.addAttribute("tabclass2", "active");
        uiModel.addAttribute("tabclass3", "nonactive");
        uiModel.addAttribute("tabclass4", "nonactive");
        uiModel.addAttribute("tabclass5", "nonactive");
        return "viewidea";
    }

    @RequestMapping(value = "/addidea", method = RequestMethod.GET)
    public String updateForm(@ModelAttribute Idea myIdea, Model model) {
        model.addAttribute("tabclass1", "nonactive");
        model.addAttribute("tabclass2", "nonactive");
        model.addAttribute("tabclass3", "active");
        model.addAttribute("tabclass4", "nonactive");
        model.addAttribute("tabclass5", "nonactive");
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

    @PreAuthorize("isAuthenticated()")
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
    public String Index(Model uiModel, RedirectAttributes redirectAttributes) {
        List<Idea> topList = null;
        Idea myIdea = new Idea();
        try {
            topList = ide.getTop5();
            if (topList.size() < 5) {
                for (int i = 0; i < 5; i++) {
                    if (i > topList.size() - 1) {
                        myIdea.setId(i);
                        myIdea.setPict("idea.png");
                        myIdea.setCaption("Здесь могла бы быть ваша идея<br />Here could be your idea!");
                        myIdea.setTxt("<h1>Здесь могла бы быть ваша идея!<br />Here could be your idea!</h1>");
                        topList.add(myIdea);

                    }
                }
                uiModel.addAttribute("top5", topList);
            } else uiModel.addAttribute("top5", topList);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("e", e);
            return "redirect:/userregerror?error=6";
        }
        uiModel.addAttribute("list", "addidea");
        uiModel.addAttribute("tabclass1", "active");
        uiModel.addAttribute("tabclass2", "nonactive");
        uiModel.addAttribute("tabclass3", "nonactive");
        uiModel.addAttribute("tabclass4", "nonactive");
        uiModel.addAttribute("tabclass5", "nonactive");

        return "index";
    }

    @RequestMapping(value = "/myoffice", method = RequestMethod.GET)
    public String MyOffice(Model uiModel, RedirectAttributes redirectAttributes) {
        String txt = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            User user = udi.findUserByName(txt).get(0);
            uiModel.addAttribute("useremail", user.getUseremail());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("e", e);
            return "redirect:/userregerror?error=6";
        }

        LocalDate today = LocalDate.now();
        uiModel.addAttribute("tabclass1", "nonactive");
        uiModel.addAttribute("tabclass2", "nonactive");
        uiModel.addAttribute("tabclass3", "nonactive");
        uiModel.addAttribute("tabclass4", "active");
        uiModel.addAttribute("tabclass5", "nonactive");
        uiModel.addAttribute("txt", today);
        return "myoffice";

    }

    //------------------------------------------------------------------------------------------------------------------
    // SHOW IDEA PAGE BLOCK AND SHOW COMMENTS BLOCK

    @RequestMapping(value = "/viewidea/{id}", method = RequestMethod.GET)
    public String showid(@PathVariable("id") String s, Model myModel, Idea myIdea, Comment myComment, Rating rating) {

        myModel.addAttribute("tabclass1", "nonactive");
        myModel.addAttribute("tabclass2", "active");
        myModel.addAttribute("tabclass3", "nonactive");
        myModel.addAttribute("tabclass4", "nonactive");
        myModel.addAttribute("tabclass5", "nonactive");
        ArrayList<Comment> allComments = new ArrayList<>();
        Map<Integer, String> voteList = new LinkedHashMap<>();
        for (int i = 1; i < 6; i++) {
            voteList.put(i, i + "");
        }
        myModel.addAttribute("votelist", voteList);


        try {
            if (ide.findIdeaByID(Integer.parseInt(s)).getId() != 0) {
                myIdea = ide.findIdeaByID(Integer.parseInt(s));
                myModel.addAttribute("check", myIdea);


                allComments.addAll(cdi.getAllCommentsByIdeaLink(Integer.parseInt(s)));


                myModel.addAttribute("child", allComments);
                myModel.addAttribute("count", cdi.getCnt());
                myModel.addAttribute("tags_separated", myIdea.tagString());
            } else {
                return "redirect:/viewidea";
            }


        } catch (Exception e) {

            myModel.addAttribute("err", e + "getStackTrace() " + e.getStackTrace() + "getMessage " + e.getMessage());
            //uiModel.addAttribute("comments", cdi.getAllCommentsByIdeaLink(1));
            //uiModel.addAttribute("comments1", cdi.getAllCommentsByIdeaLink(1));
            return "show";
        }


        BindingResult bindingResult = (BindingResult) myModel.asMap().get("b1");
        myModel.addAttribute("command", myComment);
        myModel.addAttribute("rcommand", rating);
        myModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "show";
    }

    // ADD COMMENTS BLOCK POST

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

    //END OF COMMENTS ADD BLOCK
    // END OF IDEA PAGE BLOCK AND SHOW COMMENTS BLOCK
    //------------------------------------------------------------------------------------------------------------------
    //DELETE IDEA BLOCK

    @RequestMapping(value = "/deleteIdea")
    public String deleteIdea(Model myModel, @RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            Idea checkIdea = ide.findIdeaByID(Integer.parseInt(id));
            if (checkIdea.getUsername().toLowerCase().equals(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase()) ||
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMINISTRATOR]")) {
                ide.deleteIdeaById(checkIdea);
                return "redirect:/userregerror?error=5";
            }

        } catch (Exception e) {
            myModel.addAttribute("e", e);
            return "redirect:/userregerror?error=6";
        }

        return "redirect:/userregerror?error=7";

    }

    //END OF DELETE IDEA BLOCK
    //------------------------------------------------------------------------------------------------------------------
    // UPDATE IDEA BLOCK

    @RequestMapping(value = "/editidea/{id}", method = RequestMethod.GET)
    public String editidea(@PathVariable("id") String id, Model myModel, Idea myIdea) {
        myModel.addAttribute("tabclass1", "nonactive");
        myModel.addAttribute("tabclass2", "active");
        myModel.addAttribute("tabclass3", "nonactive");
        myModel.addAttribute("tabclass4", "nonactive");
        myModel.addAttribute("tabclass5", "nonactive");
        try {
            myIdea = ide.findIdeaByID(Integer.parseInt(id));
        } catch (Exception e) {
            return "viewidea"; //обработать ошибку.
        }
        if (myIdea.getUsername().toLowerCase().equals(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase()) ||
                SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMINISTRATOR]")) {

            if (myIdea.getId() == 0) {
                return "redirect:/userregerror?error=2";
            }
            Map<Integer, String> cat = new HashMap<Integer, String>();
            cat.put(1, myIdea.getCategory());
            myModel.addAttribute("cat", cat);
            BindingResult bindingResult = (BindingResult) myModel.asMap().get("b1");
            myModel.addAttribute("command", myIdea);
            myModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
            return "editidea";
        } else return "redirect:/userregerror?error=7";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/editideapost", method = RequestMethod.POST)
    public String editideaupd(@ModelAttribute Idea myIdea, HttpServletRequest httpServletRequest,
                              RedirectAttributes redirectAttributes, BindingResult bindingResult, @RequestParam(value = "p", required = false) MultipartFile image) {
        myIdea.setFile(image);
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        if (!image.isEmpty()) {
            saveImage(SecurityContextHolder.getContext().getAuthentication().getName() + "_" + image.getOriginalFilename(), image);
            myIdea.setPict(SecurityContextHolder.getContext().getAuthentication().getName() + "_" + image.getOriginalFilename());
            myIdea.setFile(image);
        }

        addideaValidator.validate(myIdea, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            return rdrct + "/editidea/" + myIdea.getId();
        }

        try {
            if (myIdea.getUsername().toLowerCase().equals(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase()) ||
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMINISTRATOR]"))
                ide.updateIdeaById(myIdea);
            return rdrct + "/viewidea/" + myIdea.getId();
        } catch (Exception e) {
            //RedirectAttributes uerror=null;
            //uerror.addFlashAttribute("usererror", e);
            return rdrct + "/userregerror?error=3";
        }


    }

    //END OF UPDATE IDEA BLOCK
    //------------------------------------------------------------------------------------------------------------------
    //INSERT UPDATE RATING BLOCK
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public String vote(HttpServletRequest httpServletRequest, Rating rating, @RequestParam(value = "idea_link", required = false) int idea_link) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        rating.setUser_creator(SecurityContextHolder.getContext().getAuthentication().getName().toString());
        rating.setIdea_link(idea_link);


        try {
            voteDao.InsertRating(rating);
        } catch (Exception ex) {
            voteDao.UpdateRating(rating);
        }
        return rdrct + "/viewidea/" + rating.getIdea_link();
    }

    //END OF INSERT UPDATE RATING BLOCK
    //------------------------------------------------------------------------------------------------------------------
    //LIKE BLOCK
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public String like(HttpServletRequest httpServletRequest, Rating rating, @RequestParam(value = "idea_link", required = false) int idea_link,
                       @RequestParam(value = "likeordislike", required = false) int likeordislike) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        rating.setUser_creator(SecurityContextHolder.getContext().getAuthentication().getName().toString());
        rating.setIdea_link(idea_link);
        if (likeordislike == 1) rating.setLikeordislike(1);
        if (likeordislike == -1) rating.setLikeordislike(-1);

        try {
            voteDao.Insertlikeordislike(rating);
        } catch (Exception ex) {
            voteDao.Updatelikeordislike(rating);
        }
        return rdrct + "/viewidea/" + rating.getIdea_link();
    }

    //END OF BLOCK LIKE
    //------------------------------------------------------------------------------------------------------------------
    static String Smile(String smile) {
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
        String ar = aq.replaceAll(":ar:", "<img src=\"/resources/smiles/ar.gif\"/>");
        String as = ar.replaceAll(":as:", "<img src=\"/resources/smiles/as.gif\"/>");
        String at = as.replaceAll(":at:", "<img src=\"/resources/smiles/at.gif\"/>");
        String au = at.replaceAll(":au:", "<img src=\"/resources/smiles/au.gif\"/>");
        String av = au.replaceAll(":av:", "<img src=\"/resources/smiles/av.gif\"/>");
        String aw = av.replaceAll(":aw:", "<img src=\"/resources/smiles/aw.gif\"/>");
        String ax = aw.replaceAll(":ax:", "<img src=\"/resources/smiles/ax.gif\"/>");
        String ay = ax.replaceAll(":ay:", "<img src=\"/resources/smiles/ay.gif\"/>");
        String az = ay.replaceAll(":az:", "<img src=\"/resources/smiles/az.gif\"/>");
        return az;
    }
}
