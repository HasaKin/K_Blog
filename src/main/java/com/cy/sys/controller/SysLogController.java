package com.cy.sys.controller;

import com.cy.common.vo.JsonResult;
import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysLog;
import com.cy.sys.service.SysLogService;
import java.io.PrintStream;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;












@Controller
@RequestMapping({"/log/"})
public class SysLogController
{
  @Autowired
  private SysLogService sysLogService;
  
  @RequestMapping({"doLogListUI"})
  public String doLogListUI()
  {
    return "sys/log_list";
  }
  
  @RequestMapping({"doDeleteObjects"})
  @ResponseBody
  public JsonResult doDeleteObjects(Integer... ids)
  {
    System.out.println("ids=" + Arrays.toString(ids));
    this.sysLogService.deleteObjects(ids);
    return new JsonResult("delete ok");
  }
  




  @RequestMapping({"doFindPageObjects"})
  @ResponseBody
  public JsonResult doFindPageObjects(String username, Integer pageCurrent)
  {
    System.out.println("pageCurrent=" + pageCurrent);
    PageObject<SysLog> pageObject = 
      this.sysLogService.findPageObjects(username, 
      pageCurrent);
    System.out.println("sysLogService=" + this.sysLogService.getClass().getName());
    return new JsonResult(pageObject);
  }
}
