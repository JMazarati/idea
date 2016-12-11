package ua.pp.idea.dao;

import ua.pp.idea.entity.Rating;

import java.sql.SQLException;

/**
 * Created by Dark on 11.12.2016.
 */
public interface VoteDao {
    void InsertRating(Rating rating);
    void UpdateRating(Rating rating);
}
