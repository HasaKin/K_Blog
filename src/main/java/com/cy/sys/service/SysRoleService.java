package com.cy.sys.service;

import com.cy.common.vo.CheckBox;
import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysRole;
import com.cy.sys.vo.SysRoleVo;
import java.util.List;

public abstract interface SysRoleService
{
  public abstract List<CheckBox> findObjects();
  
  public abstract SysRoleVo findObjectById(Integer paramInteger);
  
  public abstract int updateObject(SysRole paramSysRole, Integer[] paramArrayOfInteger);
  
  public abstract int saveObject(SysRole paramSysRole, Integer[] paramArrayOfInteger);
  
  public abstract int deleteObject(Integer paramInteger);
  
  public abstract PageObject<SysRole> findPageObjects(String paramString, Integer paramInteger);
}

