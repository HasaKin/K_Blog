package com.cy.sys.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cy.common.exception.ServiceException;
import com.cy.common.vo.JsonResult;
import com.cy.common.vo.PageObject;
import com.cy.sys.entity.Comment;
import com.cy.sys.entity.SysArticle;
import com.cy.sys.service.CommentService;
import com.cy.sys.service.SysArticleService;


@Controller
@RequestMapping({"/"})
public class SysArticleController
{
  @Autowired
  SysArticleService sysArticleService;
  @Autowired
  CommentService commentService;
  
  @RequestMapping({"art/doArticleListUI"})
  public String doArticleListUI()
  {
    return "article/article_list";
  }
  
  @RequestMapping({"art/doAddArticleUI"})
  public String doAddArticleUI() {
    return "article/article_add";
  }
  

  @RequestMapping({"articleAdd"})
  public String articleAdd(HttpServletRequest request, RedirectAttributes redirectAttributes)
  {
    try
    {
      request.setCharacterEncoding("utf-8");
      SysArticle article = new SysArticle();
      article.setTitle(request.getParameter("title"));
      article.setCatalogId(Integer.valueOf(Integer.parseInt(request.getParameter("catalogId"))));
      article.setKeywords(request.getParameter("keywords"));
      article.setDesci(request.getParameter("desci"));
      article.setContent(request.getParameter("content"));
      article.setTime(new Date());
      if (this.sysArticleService.insert(article)) {
        redirectAttributes.addFlashAttribute("succ", "发表文章成功！");
        return "article/add_to";
      }
      redirectAttributes.addFlashAttribute("error", "发表文章失败！");
      return "article/add_to";
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }
  }

  @RequestMapping({"doBlogdetailUI"})
  public ModelAndView doBlogdetailUI(HttpServletRequest request)
  {
    int id = Integer.parseInt(request.getParameter("id"));
    List<Comment> comments = this.commentService.allComments(id, 0, 10);
    
    System.out.println("comment==============" + comments);
    
    SysArticle article = this.sysArticleService.selectObject(Integer.valueOf(id));
    SysArticle lastArticle = this.sysArticleService.selectLastArticle(Integer.valueOf(id));
    SysArticle nextArticle = this.sysArticleService.selectNextArticle(Integer.valueOf(id));
    
    Integer click = article.getClick();
    article.setClick(Integer.valueOf(click.intValue() + 1));
    this.sysArticleService.updateArticle(article);
    
    ModelAndView modelAndView = new ModelAndView("detail");
    modelAndView.addObject("article", article);
    modelAndView.addObject("comments", comments);
    modelAndView.addObject("lastArticle", lastArticle);
    modelAndView.addObject("nextArticle", nextArticle);
    return modelAndView;
  }
  
  @RequestMapping({"art/doBlogIndexUI"})
  public String doBlogIndexUI(HttpServletRequest request)
  {
    List<SysArticle> articles = this.sysArticleService.selectObjects();
    request.setAttribute("articles", articles);
    return "index";
  }
  
  @RequestMapping({"art/doSelectObject"})
  @ResponseBody
  public JsonResult doSelectObject(Integer id) {
    SysArticle list = this.sysArticleService.selectObject(id);
    return new JsonResult(list);
  }
  

  @RequestMapping({"art/doFindPageObjects"})
  @ResponseBody
  public JsonResult doFindPageObjects(String title, Integer pageCurrent)
  {
    PageObject<SysArticle> pageObject = 
      this.sysArticleService.findPageObjects(title, pageCurrent);
    
    return new JsonResult(pageObject);
  }
  
  @RequestMapping({"art/doDeleteObject"})
  @ResponseBody
  public JsonResult doDeleteObject(Integer... ids) {
    this.sysArticleService.deleteObjects(ids);
    return new JsonResult("delete ok!");
  }
  
  @RequestMapping({"art/doUpdateObject"})
  @ResponseBody
  public JsonResult doUpdateObject(SysArticle entity) {
    this.sysArticleService.updateObject(entity);
    return new JsonResult("update ok!");
  }
  
  @RequestMapping({"art/doInsertObject"})
  @ResponseBody
  public JsonResult doInsertObject(SysArticle entity) {
    this.sysArticleService.insert(entity);
    return new JsonResult("insert ok!");
  }
}
