package com.cy.sys.service;

import com.cy.sys.entity.Comment;
import java.util.List;

public abstract interface CommentService
{
  public abstract List<Comment> allComments(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract int insert(Comment paramComment);
}

