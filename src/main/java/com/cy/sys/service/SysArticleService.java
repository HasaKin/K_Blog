package com.cy.sys.service;

import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysArticle;
import java.util.List;

public abstract interface SysArticleService
{
  public abstract boolean updateArticle(SysArticle paramSysArticle);
  
  public abstract SysArticle selectNextArticle(Integer paramInteger);
  
  public abstract SysArticle selectLastArticle(Integer paramInteger);
  
  public abstract List<SysArticle> selectObjects();
  
  public abstract SysArticle selectObject(Integer paramInteger);
  
  public abstract boolean insert(SysArticle paramSysArticle);
  
  public abstract int updateObject(SysArticle paramSysArticle);
  
  public abstract int deleteObjects(Integer... paramVarArgs);
  
  public abstract PageObject<SysArticle> findPageObjects(String paramString, Integer paramInteger);
}


/* Location:              C:\Users\TCTM\Documents\Tencent Files\1808806152\FileRecv\峰会文档\合肥银泰-博客网\WEB-INF\classes\!\com\jt\sys\service\SysArticleService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */