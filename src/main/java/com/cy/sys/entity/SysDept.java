 package com.cy.sys.entity;
 
 import java.util.Date;

import lombok.Data;
 
 @Data
 public class SysDept implements java.io.Serializable { 
	 private static final long serialVersionUID = -5259265803332215029L;
   private Integer id;
   private String name;
   private Integer parentId;
   private Integer sort;
   private String note;
   private Date createdTime;
   private Date modifiedTime;
   private String createdUser;
   private String modifiedUser;
  
 }





