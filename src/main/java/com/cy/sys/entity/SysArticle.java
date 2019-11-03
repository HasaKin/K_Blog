package com.cy.sys.entity;

import java.util.Date;

import lombok.Data;


@Data
public class SysArticle
{
  private Integer id;
  private String title;
  private String keywords;
  private String desci;
  private String pic;
  private Integer click;
  private Date time;
  private Integer catalogId;
  private String content;
  
  
}





