package com.cy.sys.controller;

import com.cy.sys.entity.Comment;
import com.cy.sys.service.CommentService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;















@Controller
@RequestMapping({"/"})
public class SysCommentController
{
  @Autowired
  private CommentService commentService;
  
  @RequestMapping({"doLoadCommentUI"})
  public String doLoadCommentUI()
  {
    return "article/comment_list";
  }
  
  @RequestMapping({"doAddComment"})
  public String doAddComment(HttpServletRequest request) {
    Comment comment = new Comment();
    comment.setArticleId(Long.valueOf(Long.parseLong(request.getParameter("articleId"))));
    comment.setContent(request.getParameter("content"));
    comment.setDate(new Date());
    comment.setName(request.getParameter("name"));
    comment.setEmail(request.getParameter("email"));
    this.commentService.insert(comment);
    return "starter";
  }
}
