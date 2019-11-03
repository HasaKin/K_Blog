package com.cy.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.common.anno.RequiresLog;
import com.cy.common.exception.ServiceException;
import com.cy.common.vo.PageObject;
import com.cy.sys.dao.SysUserDao;
import com.cy.sys.dao.SysUserRoleDao;
import com.cy.sys.entity.SysUser;
import com.cy.sys.service.SysUserService;
import com.cy.sys.vo.SysUserDeptResult;

@Service
public class SysUserServiceImpl
  implements SysUserService
{
  @Autowired
  private SysUserDao sysUserDao;
  @Autowired
  private SysUserRoleDao sysUserRoleDao;
  
  public int insertObject(SysUser entity)
  {
    if (entity == null)
      throw new IllegalArgumentException("保存对象不能为空");
    if (StringUtils.isEmpty(entity.getUsername()))
      throw new IllegalArgumentException("用户名不能为空");
    if (StringUtils.isEmpty(entity.getPassword())) {
      throw new IllegalArgumentException("密码不能为空");
    }
    

    String salt = UUID.randomUUID().toString();
    entity.setSalt(salt);
    

    SimpleHash sHash = new SimpleHash(
      "md5", 
      entity.getPassword(), 
      salt);
    entity.setPassword(sHash.toHex());
    
    int rows = this.sysUserDao.insertObject(entity);
    return rows;
  }
  

  public SysUser findUserByUserName(String username)
  {
    SysUser user = this.sysUserDao.findUserByUserName(username);
    return user;
  }
  
  public int updateById(Integer id, String email, String mobile)
  {
    int row = this.sysUserDao.updateById(id, email, mobile);
    return row;
  }
  
  public int updatePasswordById(Integer id, String password) {
    String salt = UUID.randomUUID().toString();
    SimpleHash sHash = new SimpleHash(
      "md5", 
      password, 
      salt);  
    password = sHash.toHex();   
    int row = this.sysUserDao.updatePasswordById(id, password, salt);
    return row;
  }
  

  public Map<String, Object> findObjectById(Integer userId)
  {
    if ((userId == null) || (userId.intValue() < 1)) {
      throw new IllegalArgumentException("参数值无效");
    }
    SysUserDeptResult result = 
      this.sysUserDao.findObjectById(userId);
    if (result == null) {
      throw new ServiceException("此记录可能已经不存在");
    }
    List<Integer> roleIds = 
      this.sysUserRoleDao.findRoleIdsByUserId(userId);
    
    Map<String, Object> map = new HashMap();
    map.put("user", result);
    map.put("roleIds", roleIds);
    return map;
  }
  
  @RequiresLog("修改用户")
  public int updateObject(SysUser entity, Integer[] roleIds)
  {
    if (entity == null)
      throw new IllegalArgumentException("保存对象不能为空");
    if (StringUtils.isEmpty(entity.getUsername().trim())) {
      throw new IllegalArgumentException("用户名不能为空");
    }
    
    int rows = this.sysUserDao.updateObject(entity);
    
    this.sysUserRoleDao.deleteObjectsByUserId(entity.getId());
    this.sysUserRoleDao.insertObject(entity.getId(), 
      roleIds);
    
    return rows;
  }
  
  public int saveObject(SysUser entity, Integer[] roleIds)
  {
    if (entity == null)
      throw new IllegalArgumentException("保存对象不能为空");
    if (StringUtils.isEmpty(entity.getUsername().trim()))
      throw new IllegalArgumentException("用户名不能为空");
    if (StringUtils.isEmpty(entity.getPassword().trim())) {
      throw new IllegalArgumentException("密码不能为空");
    }
    
    String salt = UUID.randomUUID().toString();
    entity.setSalt(salt);
   
    SimpleHash sh = new SimpleHash(
      "MD5", 
      entity.getPassword(), 
      salt, 
      1);
    String newPassword = sh.toHex();
    entity.setPassword(newPassword);
    
    int rows = this.sysUserDao.insertObject(entity);
    
    this.sysUserRoleDao.insertObject(entity.getId(), 
      roleIds);
    
    return rows;
  }

  @RequiresPermissions({"sys:user:valid"})
  @RequiresLog("禁用启用")
  public int validById(Integer id, Integer valid, String modifiedUser)
  {
    long startTime = System.currentTimeMillis();
    
    if ((id == null) || (id.intValue() < 1))
      throw new IllegalArgumentException("id值无效");
    if ((valid.intValue() != 0) && (valid.intValue() != 1)) {
      throw new IllegalArgumentException("状态值不正确");
    }
    int rows = this.sysUserDao.validById(id, valid, modifiedUser);
    
    if (rows == 0)
      throw new ServiceException("记录可能已经不存在");
    long endTime = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.println("totalTime=" + totalTime);
    return rows;
  }
  

  public PageObject<SysUserDeptResult> findPageObjects(String username, Integer pageCurrent)
  {
    if ((pageCurrent == null) || (pageCurrent.intValue() < 1)) {
      throw new IllegalArgumentException("页码值不正确");
    }
    int rowCount = this.sysUserDao.getRowCount(username);
    
    if (rowCount == 0) {
      throw new ServiceException("记录不存在");
    }
    int pageSize = 3;
    int startIndex = (pageCurrent.intValue() - 1) * pageSize;
    List<SysUserDeptResult> records = 
      this.sysUserDao.findPageObjects(username, 
      Integer.valueOf(startIndex), Integer.valueOf(pageSize));
    
    PageObject<SysUserDeptResult> po = new PageObject();
    po.setRecords(records);
    po.setRowCount(Integer.valueOf(rowCount));
    po.setPageCurrent(pageCurrent);
    po.setPageSize(Integer.valueOf(pageSize));
    
    int pageCount = (rowCount - 1) / pageSize + 1;
    po.setPageCount(Integer.valueOf(pageCount));
    
    return po;
  }
  

  public SysUserDeptResult findLoginUserByUserId()
  {
    SysUser sysUser = (SysUser)SecurityUtils.getSubject().getPrincipal();
    System.out.println(sysUser.getId() + ":" + sysUser.getUsername());
    SysUserDeptResult findObjectById = this.sysUserDao.findObjectById(sysUser.getId());
    return findObjectById;
  }
}




 
 