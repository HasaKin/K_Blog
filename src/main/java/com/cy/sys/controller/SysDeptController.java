 package com.cy.sys.controller;
 
 import com.cy.common.vo.JsonResult;
 import com.cy.sys.entity.SysDept;
 import com.cy.sys.service.SysDeptService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @RequestMapping({"/dept/"})
 public class SysDeptController
 {
   @Autowired
   private SysDeptService sysDeptService;
   
   @RequestMapping({"doDeptListUI"})
   public String doDeptListUI()
   {
     return "sys/dept_list";
   }
   
   @RequestMapping({"doDeptEditUI"})
   public String doDeptEditUI() { return "sys/dept_edit"; }
   
   @RequestMapping({"doUpdateObject"})
   @ResponseBody
   public JsonResult doUpdateObject(SysDept entity)
   {
     this.sysDeptService.updateObject(entity);
     return new JsonResult("update ok");
   }
   
   @RequestMapping({"doSaveObject"})
   @ResponseBody
   public JsonResult doSaveObject(SysDept entity) {
     this.sysDeptService.saveObject(entity);
     return new JsonResult("save ok");
   }
   
   @RequestMapping({"doFindZTreeNodes"})
   @ResponseBody
   public JsonResult doFindZTreeNodes() {
     return new JsonResult(
       this.sysDeptService.findZTreeNodes());
   }
   
   @RequestMapping({"doDeleteObject"})
   @ResponseBody
   public JsonResult doDeleteObject(Integer id) {
     this.sysDeptService.deleteObject(id);
     return new JsonResult("delete ok");
   }
   
   @RequestMapping({"doFindObjects"})
   @ResponseBody
   public JsonResult doFindObjects() {
     return new JsonResult(this.sysDeptService.findObjects());
   }
 }

