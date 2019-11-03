package com.cy.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class JsonResult
       implements Serializable{
  private static final long serialVersionUID = 3094092862970827320L;
  private Integer state = Integer.valueOf(1); 
  private String message = "ok";
  private Object data;
  
  public JsonResult() {}
  
  public JsonResult(String message)
  {
    this.message = message;
  }
  
  public JsonResult(Object data) { this.data = data; }
  
  public JsonResult(Throwable e) {
    this.state = Integer.valueOf(0);
    this.message = e.getMessage();
  }
  
}





