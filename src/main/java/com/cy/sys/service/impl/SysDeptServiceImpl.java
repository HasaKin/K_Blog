package com.cy.sys.service.impl;

import com.cy.common.exception.ServiceException;
import com.cy.common.vo.Node;
import com.cy.sys.dao.SysDeptDao;
import com.cy.sys.entity.SysDept;
import com.cy.sys.service.SysDeptService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Transactional(timeout=30, rollbackFor={RuntimeException.class})
@Service
public class SysDeptServiceImpl
             implements SysDeptService{
  @Autowired
  private SysDeptDao sysDeptDao;
  
  @Transactional(rollbackFor={Throwable.class})
  public int updateObject(SysDept entity)
  {
    if (entity == null)
      throw new ServiceException("保存对象不能为空");
    if (StringUtils.isEmpty(entity.getName())) {
      throw new ServiceException("部门不能为空");
    }
    int rows;
    try
    {
      rows = this.sysDeptDao.updateObject(entity);
    } catch (Exception e) {      
      e.printStackTrace();
      throw new ServiceException("更新失败");
    }
    return rows;
  }
  

  public int saveObject(SysDept entity)
  {
    if (entity == null)
      throw new ServiceException("保存对象不能为空");
    if (StringUtils.isEmpty(entity.getName())) {
      throw new ServiceException("部门不能为空");
    }
    int rows = this.sysDeptDao.insertObject(entity);
    


    return rows;
  }
  
  @Transactional(readOnly=true)
  public List<Node> findZTreeNodes() {
    return this.sysDeptDao.findZTreeNodes();
  }
  
  @Transactional(readOnly=true)
  public List<Map<String, Object>> findObjects() {
    return this.sysDeptDao.findObjects();
  }
  

  public int deleteObject(Integer id)
  {
    if ((id == null) || (id.intValue() <= 0)) {
      throw new ServiceException("数据不合法,id=" + id);
    }
    
    int childCount = this.sysDeptDao.getChildCount(id);
    if (childCount > 0) {
      throw new ServiceException("此元素有子元素，不允许删除");
    }
    




    int rows = this.sysDeptDao.deleteObject(id);
    if (rows == 0)
      throw new ServiceException("此信息可能已经不存在");
    return rows;
  }
}

 
 
 

