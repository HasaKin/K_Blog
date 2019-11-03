package com.cy.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PageObject<T>
         implements Serializable{
  private static final long serialVersionUID = -4288938782658423221L;
  private List<T> records;
  private Integer rowCount = Integer.valueOf(0);
  
  private Integer pageCount = Integer.valueOf(0);
  
  private Integer pageCurrent = Integer.valueOf(1);
  
  private Integer pageSize = Integer.valueOf(3);

}





