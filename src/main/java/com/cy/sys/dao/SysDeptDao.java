package com.cy.sys.dao;

import com.cy.common.vo.Node;
import com.cy.sys.entity.SysDept;
import java.util.List;
import java.util.Map;

public abstract interface SysDeptDao
{
  public abstract int updateObject(SysDept paramSysDept);
  
  public abstract int insertObject(SysDept paramSysDept);
  
  public abstract List<Node> findZTreeNodes();
  
  public abstract List<Map<String, Object>> findObjects();
  
  public abstract int getChildCount(Integer paramInteger);
  
  public abstract int deleteObject(Integer paramInteger);
}

