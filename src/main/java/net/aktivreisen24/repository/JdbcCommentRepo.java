package net.aktivreisen24.repository;

import net.aktivreisen24.dao.CommentDao;
import net.aktivreisen24.model.Activity;
import net.aktivreisen24.model.Comment;
import net.aktivreisen24.model.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCommentRepo implements CommentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addCommentToVacationById(long id, Comment c) {
        return jdbcTemplate.update("INSERT INTO ar_comments (super_id, comment) SELECT comment_id, ? FROM ar_vacation WHERE vacation_id ?",
                c.getComment(),
                id);
    }

    @Override
    public int addCommentToActivityById(long id, Comment c) {
        return jdbcTemplate.update("INSERT INTO ar_comments (super_id, comment) SELECT comment_id, ? FROM ar_activity WHERE activity_id ?",
                c.getComment(),
                id);
    }

    @Override
    public int setAllVacationCommentByVacation(Vacation obj) {
        return 0;
    }

    @Override
    public int setALLActivityCommentByActivity(Activity obj) {
        return 0;
    }

    @Override
    public List<Comment> getAllVacationCommentById(long id) {
        return null;
    }

    @Override
    public List<Comment> getAllActivityCommentById(long id) {
        return null;
    }

}
