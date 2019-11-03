package com.cy.sys.controller;

import com.cy.common.vo.JsonResult;
import com.cy.sys.entity.SysRole;
import com.cy.sys.service.SysRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/role/"})
public class SysRoleController
{
  @org.springframework.beans.factory.annotation.Autowired
  private SysRoleService sysRoleService;
  
  @RequestMapping({"doRoleListUI"})
  public String doRoleListUI()
  {
    return "sys/role_list";
  }
  
  @RequestMapping({"doRoleEditUI"})
  public String doRoleEditUI() {
    return "sys/role_edit";
  }
  
  @RequestMapping({"doFindObjects"})
  @ResponseBody
  public JsonResult doFindObjects() {
    return new JsonResult(this.sysRoleService.findObjects());
  }
  
  @RequestMapping({"doFindObjectById"})
  @ResponseBody
  public JsonResult doFindObjectById(Integer id) {
    com.cy.sys.vo.SysRoleVo srVo = this.sysRoleService.findObjectById(id);
    return new JsonResult(srVo);
  }
  

  @RequestMapping({"doUpdateObject"})
  @ResponseBody
  public JsonResult doUpdateObject(SysRole entity, Integer[] menuIds)
  {
    this.sysRoleService.updateObject(entity, 
      menuIds);
    return new JsonResult("update ok");
  }
  

  @RequestMapping({"doSaveObject"})
  @ResponseBody
  public JsonResult doSaveObject(SysRole entity, Integer[] menuIds)
  {
    this.sysRoleService.saveObject(entity, 
      menuIds);
    return new JsonResult("save ok");
  }
  
  @RequestMapping({"doDeleteObject"})
  @ResponseBody
  public JsonResult doDeleteObject(Integer id) {
    this.sysRoleService.deleteObject(id);
    return new JsonResult("delete ok");
  }
  

  @RequestMapping({"doFindPageObjects"})
  @ResponseBody
  public JsonResult doFindPageObjects(String name, Integer pageCurrent)
  {
    com.cy.common.vo.PageObject<SysRole> pageObject = 
      this.sysRoleService.findPageObjects(name, 
      pageCurrent);
    return new JsonResult(pageObject);
  }
}

