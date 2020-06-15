package net.aktivreisen24.dao;

import net.aktivreisen24.model.Activity;
import net.aktivreisen24.model.Comment;
import net.aktivreisen24.model.Vacation;

import java.util.List;

public interface CommentDao {

    int addCommentToVacationById(long id, Comment c);

    int addCommentToActivityById(long id, Comment c);

    int setAllVacationCommentByVacation(Vacation obj);

    int setALLActivityCommentByActivity(Activity obj);

    List<Comment> getAllVacationCommentById(long id);

    List<Comment> getAllActivityCommentById(long id);
}
