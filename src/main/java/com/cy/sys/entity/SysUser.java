 package com.cy.sys.entity;
 
 import java.io.Serializable;
 import java.util.Date;

import lombok.Data;
 
 
 
 @Data
 public class SysUser
       implements Serializable{
   private static final long serialVersionUID = 1L;
   private Integer id;
   private String username;
   private String password;
   private String salt;
   private String email;
   private String mobile;
   private Integer valid = Integer.valueOf(1);
   
   private Integer deptId = Integer.valueOf(0);
   private Date createdTime;
   private Date modifiedTime;
   private String createdUser;
   private String modifiedUser; 
  
 }





