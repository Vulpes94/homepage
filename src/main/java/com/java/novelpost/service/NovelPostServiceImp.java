package com.java.novelpost.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.LogAspect;
import com.java.novelpost.dao.NovelPostDao;
import com.java.novelpost.dto.NovelPostDto;

@Component
public class NovelPostServiceImp implements NovelPostService {
	@Autowired
	private NovelPostDao novelPostDao;

	@Override
	public void novelPostRegisterOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		NovelPostDto novelPostDto = (NovelPostDto) map.get("novelPostDto");
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int n_num = Integer.parseInt(request.getParameter("n_num"));

		LogAspect.logger.info(LogAspect.LogMsg + novelPostDto.toString());
		int check = novelPostDao.novelPostInsert(novelPostDto);
		LogAspect.logger.info(LogAspect.LogMsg + check);

		mav.addObject("check", check);
		mav.addObject("n_num", n_num);
		mav.setViewName("novelpost/registerOk");
	}

}