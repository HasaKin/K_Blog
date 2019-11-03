package com.cy.sys.dao;

import com.cy.sys.entity.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface CommentDao
{
  public abstract List<Comment> queryAll(@Param("article_id") int paramInt1, @Param("offset") int paramInt2, @Param("limit") int paramInt3);
  
  public abstract int insert(Comment paramComment);
}

