package com.cy.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface SysUserRoleDao
{
  public abstract int deleteObjectsByUserId(Integer paramInteger);
  
  public abstract List<Integer> findRoleIdsByUserId(Integer paramInteger);
  
  public abstract int insertObject(@Param("userId") Integer paramInteger, @Param("roleIds") Integer[] paramArrayOfInteger);
  
  public abstract int deleteObjectsByRoleId(Integer paramInteger);
}

