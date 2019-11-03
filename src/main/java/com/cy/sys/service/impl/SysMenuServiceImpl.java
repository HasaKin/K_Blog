package com.cy.sys.service.impl;

import com.cy.common.exception.ServiceException;
import com.cy.common.vo.Node;
import com.cy.sys.dao.SysMenuDao;
import com.cy.sys.dao.SysRoleMenuDao;
import com.cy.sys.entity.SysMenu;
import com.cy.sys.service.SysMenuService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class SysMenuServiceImpl
  implements SysMenuService
{
  @Autowired
  private SysMenuDao sysMenuDao;
  @Autowired
  private SysRoleMenuDao sysRoleMenuDao;
  
  public int updateObject(SysMenu entity)
  {
    if (entity == null)
      throw new IllegalArgumentException("保存对象不能为空");
    if (StringUtils.isEmpty(entity.getName())) {
      throw new IllegalArgumentException("名字不能为空");
    }
    
    int rows = this.sysMenuDao.updateObject(entity);
    
    if (rows == 0)
      throw new ServiceException("记录可能已经不存在");
    return rows;
  }
  

  public int saveObject(SysMenu entity)
  {
    if (entity == null)
      throw new IllegalArgumentException("保存对象不能为空");
    if (StringUtils.isEmpty(entity.getName())) {
      throw new IllegalArgumentException("名字不能为空");
    }
    
    int rows = this.sysMenuDao.insertObject(entity);
    
    if (rows == 0)
      throw new ServiceException("insert error");
    return rows;
  }
  
  public List<Node> findZtreeMenuNodes()
  {
    List<Node> list = this.sysMenuDao.findZtreeMenuNodes();
    if ((list == null) || (list.size() == 0))
      throw new ServiceException("没有菜单信息");
    return list;
  }
  

  public int deleteObject(Integer id)
  {
    if ((id == null) || (id.intValue() < 1)) {
      throw new IllegalArgumentException("id值无效");
    }
    
    int count = this.sysMenuDao.getChildCount(id);
    if (count > 0) {
      throw new ServiceException("请先删除子菜单");
    }
    count = this.sysMenuDao.deleteObject(id);
    if (count == 0) {
      throw new ServiceException("此记录可能已经不存在");
    }
    this.sysRoleMenuDao.deleteObjectsByMenuId(id);
    
    return count;
  }
  
  public List<Map<String, Object>> findObjects()
  {
    List<Map<String, Object>> list = 
      this.sysMenuDao.findObjects();
    if ((list == null) || (list.size() == 0))
      throw new ServiceException("没有记录");
    return list;
  }
}


