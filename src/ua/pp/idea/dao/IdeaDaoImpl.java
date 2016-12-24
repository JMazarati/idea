package ua.pp.idea.dao;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import ua.pp.idea.dao.crud.*;
import ua.pp.idea.entity.Idea;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dark on 07.11.2016.
 */
@Repository
public class IdeaDaoImpl implements Serializable, IdeaDao {
    private DataSource dataSource;
    private SelectAllIdeaOrderByRating selectAllIdeaOrderByRating;
    private SelectAllIdeaOrderByDate selectAllIdeaOrderByDate;
    private SelectIdeaById selectIdeaById;
    private InsertNewIdea insertNewIdea;
    private DeleteIdeaByID deleteIdeaByID;
    private UpdateIdeaByID updateIdeaByID;
    private SelectIdeaByTagOrderByDate selectIdeaByTagOrderByDate;
    private SelectIdeaByTagOrderByRating selectIdeaByTagOrderByRating;
    private SelectIdeaByCategoryOrderByDate selectIdeaByCategoryOrderByDate;
    private SelectIdeaByCategoryOrderByRating selectIdeaByCategoryOrderByRating;
    private SelectIdeaByUserOrderByDate selectIdeaByUserOrderByDate;
    private SelectIdeaByUserOrderByRating selectIdeaByUserOrderByRating;
    private SelectAllIdeaOrderById selectAllIdeaOrderById;
    private SelectTopIdea selectTopIdea;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllIdeaOrderByRating = new SelectAllIdeaOrderByRating(dataSource);
        this.selectAllIdeaOrderByDate = new SelectAllIdeaOrderByDate(dataSource);
        this.selectIdeaById = new SelectIdeaById(dataSource);
        this.insertNewIdea = new InsertNewIdea(dataSource);
        this.deleteIdeaByID = new DeleteIdeaByID(dataSource);
        this.updateIdeaByID = new UpdateIdeaByID(dataSource);
        this.selectIdeaByTagOrderByDate = new SelectIdeaByTagOrderByDate(dataSource);
        this.selectIdeaByTagOrderByRating = new SelectIdeaByTagOrderByRating(dataSource);
        this.selectIdeaByCategoryOrderByDate = new SelectIdeaByCategoryOrderByDate(dataSource);
        this.selectIdeaByCategoryOrderByRating = new SelectIdeaByCategoryOrderByRating(dataSource);
        this.selectIdeaByUserOrderByDate = new SelectIdeaByUserOrderByDate(dataSource);
        this.selectIdeaByUserOrderByRating = new SelectIdeaByUserOrderByRating(dataSource);
        this.selectAllIdeaOrderById = new SelectAllIdeaOrderById(dataSource);
        this.selectTopIdea = new SelectTopIdea(dataSource);


    }

    @Override
    public List<Idea> getAllIdeaOrderById() {
        return selectAllIdeaOrderById.execute();
    }

    @Override
    public List<Idea> getAll(Boolean sort) {
        if (sort)
            return selectAllIdeaOrderByDate.execute();
        else return selectAllIdeaOrderByRating.execute();
    }

    @Override
    public List<Idea> getTop5() {
        return selectTopIdea.execute();
    }

    @Override
    public Idea findIdeaByID(int id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        try {
            return selectIdeaById.executeByNamedParam(paramMap).get(0);
        } catch (Exception e) {
            Idea i1 = new Idea();
            i1.setId(0);
            i1.setCaption("Данных нет");
            i1.setTxt("Идея не существует");
            return i1;

        }
    }

    @Override
    public List<Idea> findIdeaByTag(String tag, Boolean sort) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tags", tag.toLowerCase());
        if (sort)
            return selectIdeaByTagOrderByDate.executeByNamedParam(paramMap);
        else return selectIdeaByTagOrderByRating.executeByNamedParam(paramMap);

    }

    @Override
    public List<Idea> findIdeaByCategory(int category_link, Boolean sort) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("category_link", category_link);
        if (sort)
            return selectIdeaByCategoryOrderByDate.executeByNamedParam(paramMap);
        else return selectIdeaByCategoryOrderByRating.executeByNamedParam(paramMap);
    }

    @Override
    public List<Idea> findIdeaByUser(String username, Boolean sort) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("username", username);
        if (sort)
            return selectIdeaByUserOrderByDate.executeByNamedParam(paramMap);
        else return selectIdeaByUserOrderByRating.executeByNamedParam(paramMap);
    }


    @PreAuthorize("isAuthenticated()")
    @Override
    public void createIdea(Idea idea) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("txt", idea.getTxt());
        paramMap.put("pict", idea.getPict());
        paramMap.put("video", idea.getVideo());
        paramMap.put("caption", idea.getCaption());
        paramMap.put("category", idea.getCategory());
        paramMap.put("tags", idea.getTags());
        paramMap.put("username", idea.getUsername());
        insertNewIdea.updateByNamedParam(paramMap);


    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public void deleteIdeaById(Idea idea) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", idea.getId());
        deleteIdeaByID.updateByNamedParam(paramMap);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public void updateIdeaById(Idea idea) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", idea.getId());
        paramMap.put("txt", idea.getTxt());
        paramMap.put("pict", idea.getPict());
        paramMap.put("video", idea.getVideo());
        paramMap.put("caption", idea.getCaption());
        //paramMap.put("category", idea.getCategory());
        paramMap.put("tags", idea.getTags());
        updateIdeaByID.updateByNamedParam(paramMap);
    }


}
