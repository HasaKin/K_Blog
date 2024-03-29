package com.cy.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class SysMenu
  implements Serializable{
  private static final long serialVersionUID = -8805983256624854549L;
  private Integer id;
  private String name;
  private String url;
  private Integer type = Integer.valueOf(1);  
  private Integer sort; 
  private String note; 
  private Integer parentId; 
  private String permission; 
  private String createdUser;
  private String modifiedUser;
  private Date createdTime;
  private Date modifiedTime;
  
}





