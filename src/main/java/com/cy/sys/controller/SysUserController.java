package com.cy.sys.controller;

import com.cy.common.vo.JsonResult;
import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysUser;
import com.cy.sys.service.SysUserService;
import com.cy.sys.vo.SysUserDeptResult;
import java.io.PrintStream;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;










@Controller
@RequestMapping({"/user/"})
public class SysUserController
{
  @Autowired
  private SysUserService sysUserService;
  
  @RequestMapping({"doLoginUserUI"})
  @ResponseBody
  public JsonResult doLoginUserUI()
  {
    SysUserDeptResult findLoginUserByUserId = this.sysUserService.findLoginUserByUserId();
    return new JsonResult(findLoginUserByUserId);
  }
  

  @RequestMapping({"doPersonListUI"})
  public String doPersonListUI()
  {
    return "sys/person_list";
  }
  
  @RequestMapping({"doSafeListUI"})
  public String doSafeListUI() { return "sys/safe_list"; }
  
  @RequestMapping({"doLoginUserListUI"})
  public String doLoginUserListUI()
  {
    System.out.println("user_login");
    return "sys/user_login";
  }
  
  @RequestMapping({"doCalendarListUI"})
  public String doCalendarListUI() {
    return "sys/calendar";
  }
  
  @RequestMapping({"doRegister"})
  @ResponseBody
  public JsonResult doRegister(SysUser entity) {
    this.sysUserService.insertObject(entity);
    return new JsonResult("Register ok");
  }
  
  @RequestMapping({"doUpdatePassword"})
  @ResponseBody
  public JsonResult doUpdatePassword(Integer id, String password) { this.sysUserService.updatePasswordById(id, password);
    return new JsonResult("Person");
  }
  
  @RequestMapping({"doPerson"})
  @ResponseBody
  public JsonResult doPerson(SysUser entity) { this.sysUserService.updateById(entity.getId(), entity.getEmail(), entity.getMobile());
    return new JsonResult("Person");
  }
  
  @RequestMapping({"doGetPerson"})
  @ResponseBody
  public JsonResult doGetPerson()
  {
    Subject subject = 
      SecurityUtils.getSubject();
    SysUser user = (SysUser)subject.getPrincipal();
    return new JsonResult(user);
  }
  


  @RequestMapping({"doUserListUI"})
  public String doUserListUI()
  {
    return "sys/user_list";
  }
  
  @RequestMapping({"doUserEditUI"})
  public String doUserEditUI() { return "sys/user_edit"; }
  

  @RequestMapping({"doLogin"})
  @ResponseBody
  public JsonResult doLogin(String username, String password)
  {
    UsernamePasswordToken token = 
      new UsernamePasswordToken(username, 
      password);
    

    Subject subject = SecurityUtils.getSubject();
    
    subject.login(token);
    return new JsonResult("login ok");
  }
  
  @RequestMapping({"doFindObjectById"})
  @ResponseBody
  public JsonResult doFindObjectById(Integer id) {
    return new JsonResult(this.sysUserService.findObjectById(id));
  }
  
  @RequestMapping({"doSaveObject"})
  @ResponseBody
  public JsonResult doSaveObject(SysUser entity, Integer[] roleIds) {
    this.sysUserService.saveObject(entity, 
      roleIds);
    return new JsonResult("save ok");
  }
  
  @RequestMapping({"doUpdateObject"})
  @ResponseBody
  public JsonResult doUpdateObject(SysUser entity, Integer[] roleIds) {
    this.sysUserService.updateObject(entity, 
      roleIds);
    return new JsonResult("update ok");
  }
  

  @RequestMapping({"doValidById"})
  @ResponseBody
  public JsonResult doValidById(Integer id, Integer valid)
  {
    SysUser user = (SysUser)
      SecurityUtils.getSubject().getPrincipal();
    this.sysUserService.validById(id, valid, 
      user.getUsername());
    System.out.println("sysUserService=" + this.sysUserService.getClass().getName());
    return new JsonResult("update ok");
  }
  
  @RequestMapping({"doFindPageObjects"})
  @ResponseBody
  public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
    System.out.println("SysUserController.SysUserService=" + this.sysUserService.getClass().getName());
    PageObject<SysUserDeptResult> pageObject = 
      this.sysUserService.findPageObjects(username, 
      pageCurrent);
    return new JsonResult(pageObject);
  }
}
