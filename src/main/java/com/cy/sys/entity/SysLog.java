package com.cy.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class SysLog
  implements Serializable
{
  private static final long serialVersionUID = -4523256868571326166L;
  private Integer id;
  private String username;
  private String operation;
  private String method;
  private String params;
  private Long time;
  private String ip;
  private Date createdTime;
  

}

