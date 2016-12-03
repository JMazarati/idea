package ua.pp.idea.dao;

import ua.pp.idea.dao.crud.SelectAllCategory;
import ua.pp.idea.entity.Category;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Dark on 03.12.2016.
 */
public class CategoryDaoImpl {
    private DataSource dataSource;
    private SelectAllCategory selectAllCategory;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource=dataSource;
        this.selectAllCategory= new SelectAllCategory(dataSource);

    }

    public List<Category> getAllCategory(){
        return selectAllCategory.execute();
    }
}
