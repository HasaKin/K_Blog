package com.cy.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface SysRoleMenuDao
{
  public abstract List<Integer> findMenuIdsByRoleIds(@Param("roleIds") Integer[] paramArrayOfInteger);
  
  public abstract int insertObject(@Param("roleId") Integer paramInteger, @Param("menuIds") Integer[] paramArrayOfInteger);
  
  public abstract int deleteObjectsByRoleId(Integer paramInteger);
  
  public abstract int deleteObjectsByMenuId(Integer paramInteger);
}

