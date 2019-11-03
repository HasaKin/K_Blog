package com.cy.sys.entity;
 
import java.util.Date;

import lombok.Data;

@Data
 public class Comment
 {
   private Long id;
   private Long articleId;
   private Date date;
   private String name;
   private String email;
   private String content; 
 }





