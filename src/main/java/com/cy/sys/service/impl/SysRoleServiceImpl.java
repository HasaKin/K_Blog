package com.cy.sys.service.impl;

import com.cy.common.anno.DataFilter;
import com.cy.common.exception.ServiceException;
import com.cy.common.vo.CheckBox;
import com.cy.common.vo.PageObject;
import com.cy.sys.dao.SysRoleDao;
import com.cy.sys.dao.SysRoleMenuDao;
import com.cy.sys.dao.SysUserRoleDao;
import com.cy.sys.entity.SysRole;
import com.cy.sys.service.SysRoleService;
import com.cy.sys.vo.SysRoleVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
@Transactional(rollbackFor={Throwable.class}, timeout=30, propagation=Propagation.REQUIRED)
public class SysRoleServiceImpl
  implements SysRoleService
{
  @Autowired
  private SysRoleDao sysRoleDao;
  @Autowired
  private SysRoleMenuDao sysRoleMenuDao;
  @Autowired
  private SysUserRoleDao sysUserRoleDao;
  
  @Transactional(readOnly=true)
  public List<CheckBox> findObjects()
  {
    return this.sysRoleDao.findObjects();
  }
  
  public SysRoleVo findObjectById(Integer id)
  {
    if ((id == null) || (id.intValue() < 1))
      throw new IllegalArgumentException("id值不正确");
    SysRoleVo srVo = this.sysRoleDao.findObjectById(id);
    if (srVo == null)
      throw new ServiceException("此记录可能已经不存在");
    return srVo;
  }

  public int updateObject(SysRole entity, Integer[] menuIds)
  {
    if (entity == null)
      throw new IllegalArgumentException("保存对象不能为空");
    if (StringUtils.isEmpty(entity.getName()))
      throw new IllegalArgumentException("角色名不能为空");
    if ((menuIds == null) || (menuIds.length == 0)) {
      throw new IllegalArgumentException("必须为角色分配权限");
    }
    int rows = this.sysRoleDao.updateObject(entity);
    this.sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());  
    this.sysRoleMenuDao.insertObject(entity.getId(), 
      menuIds);
    
    return rows;
  }

  public int saveObject(SysRole entity, Integer[] menuIds)
  {
    if (entity == null)
      throw new IllegalArgumentException("保存对象不能为空");
    if (StringUtils.isEmpty(entity.getName()))
      throw new IllegalArgumentException("角色名不能为空");
    if ((menuIds == null) || (menuIds.length == 0)) {
      throw new IllegalArgumentException("必须为角色分配权限");
    }
    int rows = this.sysRoleDao.insertObject(entity);  
    int menuRows = this.sysRoleMenuDao.insertObject(entity.getId(), 
      menuIds);
    
    return rows;
  }
  
  @DataFilter
  public int deleteObject(Integer id)
  {
    if ((id == null) || (id.intValue() < 1)) {
      throw new IllegalArgumentException("ID值不能为空");
    }
    int rows = this.sysRoleDao.deleteObject(id);
    if (rows == 0) {
      throw new ServiceException("记录可能已经不存在");
    }
    this.sysRoleMenuDao.deleteObjectsByRoleId(id);   
    this.sysUserRoleDao.deleteObjectsByRoleId(id);  
    return rows;
  }
  

  @Transactional(isolation=Isolation.READ_COMMITTED)
  public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent)
  {
    if ((pageCurrent == null) || (pageCurrent.intValue() < 1)) {
      throw new IllegalArgumentException("页码值不正确");
    }
    int rowCount = this.sysRoleDao.getRowCount(name);
    
    if (rowCount == 0) {
      throw new ServiceException("记录不存在");
    }
    int pageSize = 3;
    int startIndex = (pageCurrent.intValue() - 1) * pageSize;
    List<SysRole> records = 
      this.sysRoleDao.findPageObjects(name, 
      Integer.valueOf(startIndex), Integer.valueOf(pageSize));
    
    PageObject<SysRole> po = new PageObject();
    po.setRecords(records);
    po.setRowCount(Integer.valueOf(rowCount));
    po.setPageCurrent(pageCurrent);
    po.setPageSize(Integer.valueOf(pageSize));  
   int pageCount = (rowCount - 1) / pageSize + 1;
    po.setPageCount(Integer.valueOf(pageCount));   
    return po;
  }
}





