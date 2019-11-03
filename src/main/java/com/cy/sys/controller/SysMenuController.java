package com.cy.sys.controller;

import com.cy.common.vo.JsonResult;
import com.cy.sys.entity.SysMenu;
import com.cy.sys.service.SysMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@RequestMapping({"/menu/"})
@Controller
public class SysMenuController
{
  @org.springframework.beans.factory.annotation.Autowired
  private SysMenuService sysMenuService;
  
  @RequestMapping({"doMenuListUI"})
  public String doMenuListUI() { return "sys/menu_list"; }
  
  @RequestMapping({"doMenuEditUI"})
  public String doMenuEditUI() {
    System.out.println("==doMenuEditUI==");
    return "sys/menu_edit";
  }
  


  @RequestMapping({"doUpdateObject"})
  @ResponseBody
  public JsonResult doUpdateObject(SysMenu entity)
  {
    this.sysMenuService.updateObject(entity);
    return new JsonResult("update ok");
  }
  
  @RequestMapping({"doSaveObject"})
  @ResponseBody
  public JsonResult doSaveObject(SysMenu entity)
  {
    this.sysMenuService.saveObject(entity);
    return new JsonResult("save ok");
  }
  
  @RequestMapping({"doFindZtreeMenuNodes"})
  @ResponseBody
  public JsonResult doFindZtreeMenuNodes() {
    return new JsonResult(
      this.sysMenuService.findZtreeMenuNodes());
  }
  



  @RequestMapping({"doDeleteObject"})
  @ResponseBody
  public JsonResult doDeleteObject(Integer id)
  {
    this.sysMenuService.deleteObject(id);
    return new JsonResult("delete ok");
  }
  
  @RequestMapping({"doFindObjects"})
  @ResponseBody
  public JsonResult doFindObjects() {
    return new JsonResult(
      this.sysMenuService.findObjects());
  }
}


