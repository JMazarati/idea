package ua.pp.idea.dao;

import ua.pp.idea.entity.Idea;
import java.util.List;

/**
 * Created by Dark on 08.11.2016.
 */
public interface IdeaDao {

    public String findLastIdeaById(Long id);

    public List<Idea> findAllIdeaSortByDate();
}
