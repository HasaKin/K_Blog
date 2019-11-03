package com.cy.sys.service.impl;

import com.cy.common.exception.ServiceException;
import com.cy.common.vo.PageObject;
import com.cy.sys.dao.SysArticleDao;
import com.cy.sys.entity.SysArticle;
import com.cy.sys.service.SysArticleService;
import java.io.PrintStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class SysArticleServiceImpl
implements SysArticleService
{
	@Autowired
	private SysArticleDao sysArticleDao;

	public PageObject<SysArticle> findPageObjects(String title, Integer pageCurrent)
	{
		if ((pageCurrent == null) || (pageCurrent.intValue() < 1)) {
			throw new IllegalArgumentException("页码值不正确");
		}
		int rowCount = this.sysArticleDao.getRowCount(title);

		if (rowCount == 0) {
			throw new SecurityException("记录不存在!");
		}
		int pageSize = 5;
		int startIndex = (pageCurrent.intValue() - 1) * pageSize;
		List<SysArticle> records = 
				this.sysArticleDao.findPageObjects(title, Integer.valueOf(startIndex), Integer.valueOf(pageSize));

		PageObject<SysArticle> po = new PageObject();
		po.setRecords(records);
		po.setRowCount(Integer.valueOf(rowCount));
		po.setPageCurrent(pageCurrent);
		po.setPageSize(Integer.valueOf(pageSize));
		int pageCount = (rowCount - 1) / pageSize + 1;
		po.setPageCount(Integer.valueOf(pageCount));

		return po;
	}


	public int deleteObjects(Integer... ids)
	{
		if ((ids == null) || (ids.length == 0)) {
			throw new IllegalArgumentException("请先选择");
		}
		int rows = this.sysArticleDao.deleteObject(ids);

		if (rows == 0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}



	public int updateObject(SysArticle entity)
	{
		if (entity == null)
			throw new IllegalArgumentException("保存对象失败");
		if (StringUtils.isEmpty(entity.getTitle())) {
			throw new IllegalArgumentException("栏目名不能为空");
		}

		try
		{
			return this.sysArticleDao.updateObject(entity);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}









	public boolean insert(SysArticle entity)
	{
		if (entity == null)
			throw new ServiceException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getTitle()))
			throw new IllegalArgumentException("标题栏不能为空");
		int rows = this.sysArticleDao.insertObject(entity);
		if (rows == 0)
			throw new ServiceException("保存失败!");
		return rows > 0;
	}








	public SysArticle selectObject(Integer id)
	{
		if ((id.intValue() < 1) || (id == null))
			throw new ServiceException("请先选择!");
		SysArticle list = this.sysArticleDao.selectObject(id);
		if (list == null)
			throw new IllegalArgumentException("查询失败,请重新查询!");
		return list;
	}






	public List<SysArticle> selectObjects()
	{
		List<SysArticle> articles = this.sysArticleDao.selectObjects();
		return articles;
	}







	public SysArticle selectLastArticle(Integer id)
	{
		List<SysArticle> articles = this.sysArticleDao.selectObjects();
		int lastAId = -1;
		for (int i = 0; i < articles.size(); i++) {
			if (((SysArticle)articles.get(i)).getId().equals(id)) {
				lastAId = i - 1;
				break;
			}
		}
		if (lastAId < 0) {
			lastAId = articles.size() - 1;
		}
		return (SysArticle)articles.get(lastAId);
	}







	public SysArticle selectNextArticle(Integer id)
	{
		List<SysArticle> articles = this.sysArticleDao.selectObjects();
		int nextAId = -1;
		System.out.println("selectNextArticle() -> id = " + id);
		for (int i = 0; i < articles.size(); i++) {
			if (((SysArticle)articles.get(i)).getId().equals(id)) {
				nextAId = i + 1;
				break;
			}
		}
		if (nextAId >= articles.size()) {
			nextAId = 0;
		}

		return (SysArticle)articles.get(nextAId);
	}

	public boolean updateArticle(SysArticle entity) {
		boolean articles = this.sysArticleDao.updateArticle(entity);
		return articles;
	}
}





