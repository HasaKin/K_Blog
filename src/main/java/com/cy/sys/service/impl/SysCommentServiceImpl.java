package com.cy.sys.service.impl;

import com.cy.sys.dao.CommentDao;
import com.cy.sys.entity.Comment;
import com.cy.sys.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysCommentServiceImpl
  implements CommentService
{
  @Autowired
  public CommentDao commentDao;
  
  public List<Comment> allComments(int article_id, int offset, int limit)
  {
    return this.commentDao.queryAll(article_id, offset, limit);
  }
  
  public int insert(Comment entity)
  {
    int comment = this.commentDao.insert(entity);
    return comment;
  }
}
