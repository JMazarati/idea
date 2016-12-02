package ua.pp.idea.dao;

import ua.pp.idea.dao.crud.InsertNewComment;
import ua.pp.idea.dao.crud.SelectAllCommentByIdea;
import ua.pp.idea.dao.crud.SelectChildCommentsByParrentId;
import ua.pp.idea.entity.Comment;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dark on 21.11.2016.
 */
public class CommentDaoImpl implements CommentDao{
   private DataSource dataSource;
    private int cnt=0;
    private SelectAllCommentByIdea selectAllCommentByIdea;
    private SelectChildCommentsByParrentId selectChildCommentsByParrentId;
    private InsertNewComment insertNewComment;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource=dataSource;
        this.selectAllCommentByIdea = new SelectAllCommentByIdea(dataSource);
        this.selectChildCommentsByParrentId = new SelectChildCommentsByParrentId(dataSource);
        this.insertNewComment = new InsertNewComment(dataSource);
    }



    public List<Comment> getAllCommentsByIdeaLink(int idea_id) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("idea_id",idea_id);
        paramMap.put("parent",null);
        List<Comment> result = selectAllCommentByIdea.executeByNamedParam(paramMap);
        List<Comment> result2=new ArrayList<Comment>();
    cnt=1;
        for(Comment item:result){

            List<Comment> result3=getChildCommentsByParrentId(idea_id,item.getId());
            result2.add(item);
            result2.addAll(result3);

        }
        return  result2;
    }

    @Override
    public ArrayList<Comment> getChildCommentsByParrentId(int parent_id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parent_id",parent_id);
        return (ArrayList<Comment>) selectChildCommentsByParrentId.executeByNamedParam(paramMap);

    }
    @Override
    public void createNewComments(Comment comment){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("ideaLink",comment.getIdeaLink());
        paramMap.put("parentLink",comment.getParentLink());
        paramMap.put("note",comment.getNote());
        paramMap.put("userLink",comment.getUserLink());
        insertNewComment.updateByNamedParam(paramMap);
    }

    private List<Comment> getChildCommentsByParrentId(int idea_id,int parent_id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("idea_id",idea_id);
        paramMap.put("parent",parent_id);
        List<Comment> result = selectAllCommentByIdea.executeByNamedParam(paramMap);
        List<Comment> result2=new ArrayList<Comment>();
        cnt++;
        for(Comment item:result){

            List<Comment> result3=getChildCommentsByParrentId(idea_id,item.getId());
            result2.add(item);
            result2.addAll(result3);

        }
        return  result2;

    }
@Override
    public int getCnt() {
        return cnt;
    }
}
