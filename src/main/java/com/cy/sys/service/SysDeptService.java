package com.cy.sys.service;

import com.cy.common.vo.Node;
import com.cy.sys.entity.SysDept;
import java.util.List;
import java.util.Map;

public abstract interface SysDeptService
{
  public abstract int saveObject(SysDept paramSysDept);
  
  public abstract int updateObject(SysDept paramSysDept);
  
  public abstract List<Node> findZTreeNodes();
  
  public abstract List<Map<String, Object>> findObjects();
  
  public abstract int deleteObject(Integer paramInteger);
}
