package ua.pp.idea.dao;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.pp.idea.entity.Idea;
import java.util.List;

/**
 * Created by Dark on 08.11.2016.
 */
public interface IdeaDao {


    List<Idea> getAllIdeaOrderById();

    List<Idea> getAll(Boolean sort);

    List<Idea> findIdeaByText(String text, Boolean sort);

    List<Idea> getTop5();

    Idea findIdeaByID(int id);

    List<Idea> findIdeaByCategory(int category_link,Boolean sort);

    List<Idea> findIdeaByUser(String category_link, Boolean sort);

    void createIdea(Idea idea);

    void deleteIdeaById(Idea idea);

    void updateIdeaById(Idea idea);

    List<Idea> findIdeaByTag(String tag,Boolean sort);
}
