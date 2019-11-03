package com.cy.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Node
  implements Serializable{
  private static final long serialVersionUID = 4351174414771192644L;
  private Integer id;
  private String name;
  private Integer parentId;

}





 