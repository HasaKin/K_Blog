 package com.cy.sys.vo;
 
 import com.cy.sys.entity.SysDept;

import lombok.Data;

import java.io.Serializable;
 import java.util.Date;
 
 @Data
 public class SysUserDeptResult
           implements Serializable{
   private static final long serialVersionUID = 5477389876913514595L;
   private Integer id;
   private String username;
   private String password;
   private String salt;
   private String email;
   private String mobile;
   private Integer valid = Integer.valueOf(1);
   private SysDept sysDept;
   private Date createdTime;
   private Date modifiedTime;
   private String createdUser;
   private String modifiedUser;
   
 }




