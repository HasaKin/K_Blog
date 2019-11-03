package com.cy.common.exception;


public class ServiceException
             extends RuntimeException{
  private static final long serialVersionUID = 8029523183323748146L;
  

  public ServiceException() {}
  
  public ServiceException(String message)
  {
    super(message);
  }
  
  public ServiceException(Throwable cause) { super(cause); }
}





