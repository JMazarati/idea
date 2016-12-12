package ua.pp.idea.dao;

import ua.pp.idea.dao.crud.InsertRating;
import ua.pp.idea.dao.crud.UpdateRating;
import ua.pp.idea.entity.Rating;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dark on 11.12.2016.
 */
public class VoteDaoImpl implements VoteDao,Serializable {
    DataSource dataSource;
    InsertRating insertRating;
    UpdateRating updateRating;
    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertRating = new InsertRating(dataSource);
        this.updateRating = new UpdateRating(dataSource);
    }
    @Override
    public void InsertRating(Rating rating){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("user_creator",rating.getUser_creator());
        paramMap.put("idea_link",rating.getIdea_link());
        paramMap.put("rating",rating.getRating());
        //paramMap.put("likeordislike",rating.getLikeordislike());
        insertRating.updateByNamedParam(paramMap);
    }

    @Override
    public void UpdateRating(Rating rating) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("user_creator",rating.getUser_creator());
        paramMap.put("idea_link",rating.getIdea_link());
        paramMap.put("rating",rating.getRating());
        updateRating.updateByNamedParam(paramMap);
    }
}
