package com.cy.sys.dao;

import com.cy.common.vo.CheckBox;
import com.cy.sys.entity.SysRole;
import com.cy.sys.vo.SysRoleVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface SysRoleDao
{
  public abstract List<CheckBox> findObjects();
  
  public abstract int updateObject(SysRole paramSysRole);
  
  public abstract SysRoleVo findObjectById(Integer paramInteger);
  
  public abstract int insertObject(SysRole paramSysRole);
  
  public abstract int deleteObject(Integer paramInteger);
  
  public abstract List<SysRole> findPageObjects(@Param("name") String paramString, @Param("startIndex") Integer paramInteger1, @Param("pageSize") Integer paramInteger2);
  
  public abstract int getRowCount(@Param("name") String paramString);
}

