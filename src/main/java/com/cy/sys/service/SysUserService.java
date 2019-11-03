package com.cy.sys.service;

import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysUser;
import com.cy.sys.vo.SysUserDeptResult;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public abstract interface SysUserService
{
  public abstract SysUser findUserByUserName(String paramString);
  
  public abstract int updatePasswordById(@Param("id") Integer paramInteger, @Param("password") String paramString);
  
  public abstract int updateById(Integer paramInteger, String paramString1, String paramString2);
  
  public abstract SysUserDeptResult findLoginUserByUserId();
  
  public abstract int insertObject(SysUser paramSysUser);
  
  public abstract int updateObject(SysUser paramSysUser, Integer[] paramArrayOfInteger);
  
  public abstract Map<String, Object> findObjectById(Integer paramInteger);
  
  public abstract int saveObject(SysUser paramSysUser, Integer[] paramArrayOfInteger);
  
  public abstract int validById(Integer paramInteger1, Integer paramInteger2, String paramString);
  
  public abstract PageObject<SysUserDeptResult> findPageObjects(String paramString, Integer paramInteger);
}

