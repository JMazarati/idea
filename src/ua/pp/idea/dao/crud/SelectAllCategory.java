package ua.pp.idea.dao.crud;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.idea.entity.Category;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dark on 03.12.2016.
 */
public class SelectAllCategory extends MappingSqlQuery<Category> {
    private static final String SQL_SELECT_ALL_CATEGORY="SELECT id,parent,title FROM category_table ORDER BY id";

    public SelectAllCategory(DataSource ds) {
        super(ds, SQL_SELECT_ALL_CATEGORY);
    }

    @Override
    protected Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setParent(resultSet.getInt("parent"));
        category.setTitle(resultSet.getString("title"));
        return category;
    }
}
