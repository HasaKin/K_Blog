package com.cy.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping({"/"})
public class PageController
{
  @RequestMapping({"doIndexUI"})
  public String doIndexUI()
  {
    return "starter";
  }
  
  @RequestMapping({"doPageUI"})
  public String doPageUI() {
    return "common/page";
  }
  
  @RequestMapping({"doLoginUI"})
  public String doLoginUI() { return "login"; }
  

  @RequestMapping({"doRegisterUI"})
  public String doRegisterUI()
  {
    return "register";
  }
}