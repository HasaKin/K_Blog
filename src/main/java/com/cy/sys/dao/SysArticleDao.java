package com.cy.sys.dao;

import com.cy.sys.entity.SysArticle;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface SysArticleDao
{
  public abstract boolean updateArticle(SysArticle paramSysArticle);
  
  public abstract List<SysArticle> selectObjects();
  
  public abstract SysArticle selectObject(@Param("id") Integer paramInteger);
  
  public abstract int insertObject(SysArticle paramSysArticle);
  
  public abstract int updateObject(SysArticle paramSysArticle);
  
  public abstract int deleteObject(@Param("ids") Integer... paramVarArgs);
  
  public abstract List<SysArticle> findPageObjects(@Param("title") String paramString, @Param("startIndex") Integer paramInteger1, @Param("pageSize") Integer paramInteger2);
  
  public abstract int getRowCount(@Param("title") String paramString);
}

