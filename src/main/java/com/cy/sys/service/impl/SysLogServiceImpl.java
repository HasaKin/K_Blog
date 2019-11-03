package com.cy.sys.service.impl;

import com.cy.common.exception.ServiceException;
import com.cy.common.vo.PageObject;
import com.cy.sys.dao.SysLogDao;
import com.cy.sys.entity.SysLog;
import com.cy.sys.service.SysLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl
  implements SysLogService
{
  @Autowired
  private SysLogDao sysLogDao;
  
  public int deleteObjects(Integer... ids)
  {
    if ((ids == null) || (ids.length == 0)) {
      throw new IllegalArgumentException("请先选择");
    }
    int rows = this.sysLogDao.deleteObjects(ids);
    
    if (rows == 0)
      throw new ServiceException("记录可能已经不存在");
    return rows;
  }

  public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent)
  {
    if ((pageCurrent == null) || (pageCurrent.intValue() < 1)) {
      throw new IllegalArgumentException("页码值不正确");
    }
    int rowCount = this.sysLogDao.getRowCount(username);
    
    if (rowCount == 0) {
      throw new ServiceException("记录不存在");
    }
    int pageSize = 3;
    int startIndex = (pageCurrent.intValue() - 1) * pageSize;
    List<SysLog> records = 
      this.sysLogDao.findPageObjects(username, 
      Integer.valueOf(startIndex), Integer.valueOf(pageSize));
    
    PageObject<SysLog> po = new PageObject();
    po.setRecords(records);
    po.setRowCount(Integer.valueOf(rowCount));
    po.setPageCurrent(pageCurrent);
    po.setPageSize(Integer.valueOf(pageSize));
    

    int pageCount = (rowCount - 1) / pageSize + 1;
    po.setPageCount(Integer.valueOf(pageCount));
    
    return po;
  }
}





