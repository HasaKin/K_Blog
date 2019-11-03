 package com.cy.sys.vo;
 
 import java.io.Serializable;
 import java.util.List;

import lombok.Data;
 
@Data
 public class SysRoleVo
      implements Serializable{
   private static final long serialVersionUID = -48491083382607667L;
   private Integer id;
   private String name;
   private String note;
   private List<Integer> menuIds;
   
 }





