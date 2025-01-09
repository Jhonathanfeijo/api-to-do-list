package jfdev.apitodolist.repository;

import jfdev.apitodolist.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
