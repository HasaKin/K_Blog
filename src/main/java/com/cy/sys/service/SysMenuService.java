package com.cy.sys.service;

import com.cy.common.vo.Node;
import com.cy.sys.entity.SysMenu;
import java.util.List;
import java.util.Map;

public abstract interface SysMenuService
{
  public abstract int updateObject(SysMenu paramSysMenu);
  
  public abstract int saveObject(SysMenu paramSysMenu);
  
  public abstract List<Node> findZtreeMenuNodes();
  
  public abstract List<Map<String, Object>> findObjects();
  
  public abstract int deleteObject(Integer paramInteger);
}

