package ua.pp.idea.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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
    private SelectAllIdea selectAllIdea;
    private SelectIdeaById selectIdeaById;
    private InsertNewIdea insertNewIdea;
    private DeleteIdeaByID deleteIdeaByID;
    private UpdateIdeaByID updateIdeaByID;
    private SelectIdeaByTag selectIdeaByTag;
    private SelectIdeaByCategory selectIdeaByCategory;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllIdea = new SelectAllIdea(dataSource);
        this.selectIdeaById = new SelectIdeaById(dataSource);
        this.insertNewIdea = new InsertNewIdea(dataSource);
        this.deleteIdeaByID = new DeleteIdeaByID(dataSource);
        this.updateIdeaByID = new UpdateIdeaByID(dataSource);
        this.selectIdeaByTag = new SelectIdeaByTag(dataSource);
        this.selectIdeaByCategory = new SelectIdeaByCategory(dataSource);

    }


    @Override
    public List<Idea> getAll() {

        return selectAllIdea.execute();
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
    public List<Idea> findIdeaByTag(String tag) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tags", tag.toLowerCase());

        return selectIdeaByTag.executeByNamedParam(paramMap);

    }
    @Override
    public List<Idea> findIdeaByCategory(int category_link) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("category_link", category_link);

        return selectIdeaByCategory.executeByNamedParam(paramMap);

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
