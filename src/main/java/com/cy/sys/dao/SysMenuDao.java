package com.cy.sys.dao;

import com.cy.common.vo.Node;
import com.cy.sys.entity.SysMenu;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public abstract interface SysMenuDao
{
  public abstract List<String> findPermissions(@Param("menuIds") Integer[] paramArrayOfInteger);
  
  public abstract int updateObject(SysMenu paramSysMenu);
  
  public abstract int insertObject(SysMenu paramSysMenu);
  
  public abstract List<Node> findZtreeMenuNodes();
  
  public abstract int getChildCount(Integer paramInteger);
  
  public abstract int deleteObject(Integer paramInteger);
  
  public abstract List<Map<String, Object>> findObjects();
}

