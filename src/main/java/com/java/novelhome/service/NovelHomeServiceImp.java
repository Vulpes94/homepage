package com.java.novelhome.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.LogAspect;
import com.java.novelhome.dao.NovelHomeDao;
import com.java.novelhome.dto.NovelHomeDto;
import com.java.novelpost.dto.NovelPostDto;

@Component
public class NovelHomeServiceImp implements NovelHomeService {

	@Autowired
	private NovelHomeDao novelHomeDao;

	@Value("#{properties['novelhome.imagepath']}")
	private String imagepath;

	@Override
	public void novelhomeUploadOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		NovelHomeDto novelHomeDto = (NovelHomeDto) map.get("novelHomeDto");
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");

		MultipartFile upFile = request.getFile("file");
//		LogAspect.logger.info(LogAspect.LogMsg + upFile);

		if (upFile.getSize() != 0) {
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
			long fileSize = upFile.getSize();
//			LogAspect.logger.info(LogAspect.LogMsg + fileName + fileSize);

			File path = new File(imagepath);
			path.mkdir();

			LogAspect.logger.info(LogAspect.LogMsg + path);

			if (path.exists() && path.isDirectory()) {
				File file = new File(path, fileName);
				try {
					upFile.transferTo(file);

					novelHomeDto.setN_image_path(file.getAbsolutePath());
					novelHomeDto.setN_image_name(fileName);
					novelHomeDto.setN_image_size(fileSize);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {

			}

		}

		LogAspect.logger.info(LogAspect.LogMsg + novelHomeDto.toString());
		int check = novelHomeDao.novelHomeUpload(novelHomeDto);

		int m_num = Integer.parseInt(request.getParameter("m_num"));
		LogAspect.logger.info(LogAspect.LogMsg + m_num);

		int n_num = novelHomeDao.novelHomeSelectGetNum(m_num);
		LogAspect.logger.info(LogAspect.LogMsg + n_num);

		mav.addObject("n_num", n_num);
		mav.addObject("check", check);
		mav.addObject("novelHomeDto", novelHomeDto);

		mav.setViewName("novelhome/uploadOk");
	}

	@Override
	public void novelHomeList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int n_num = Integer.parseInt(request.getParameter("n_num"));
		LogAspect.logger.info(LogAspect.LogMsg + "n_num=" + n_num);

		NovelHomeDto novelHomeDto = novelHomeDao.novelHomeList(n_num);
		int nNumSess = novelHomeDto.getN_num();

		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null)
			pageNumber = "1";

		int currentPage = Integer.parseInt(pageNumber);
		LogAspect.logger.info(LogAspect.LogMsg + "currentPage=" + currentPage);

		int boardSize = 4;
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;

		int count = novelHomeDao.getCount(n_num);
		LogAspect.logger.info(LogAspect.LogMsg + "count=" + count);

		List<NovelPostDto> novelPostList = null;
		if (count > 0) {
			novelPostList = novelHomeDao.novelPostList(startRow, endRow, n_num);
			LogAspect.logger.info(LogAspect.LogMsg + "novelPostList.size=" + novelPostList.size());
		}
		String nickname = novelHomeDao.getNickname(n_num);
		LogAspect.logger.info(LogAspect.LogMsg + "nickname=" + nickname);
		
		mav.addObject("nickname", nickname);
		mav.addObject("novelPostList", novelPostList);
		mav.addObject("currentPage", currentPage);
		mav.addObject("boardSize", boardSize);
		mav.addObject("count", count);
		mav.addObject("novelHomeDto", novelHomeDto);
		mav.addObject("nNumSess", nNumSess);
		mav.addObject("n_num", n_num);

		mav.setViewName("novelhome/list.tiles");
	}
	
	
	@Override
	public void search(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null)
			pageNumber = "1";
		int currentPage = Integer.parseInt(pageNumber);
		LogAspect.logger.info(LogAspect.LogMsg + "currentPage=" + currentPage);
		
		String search_method=request.getParameter("search_method");
		String keyword=request.getParameter("keyword");
		LogAspect.logger.info(LogAspect.LogMsg + "search_method=" + search_method + "keyword=" + keyword);
		
		int boardSize = 9;
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;

		int count = 0;
		List<NovelHomeDto> searchList = null;
		
		if(search_method.equals("title")) {		//제목검색
			count = novelHomeDao.getTitleSearchCount(keyword);
			if (count > 0) {
				searchList = novelHomeDao.titleSearchList(startRow, endRow, keyword);
				LogAspect.logger.info(LogAspect.LogMsg + "titleSearchList.toString" + searchList.toString());
			}
		}else if(search_method.equals("writer")) {	//작가검색
			count = novelHomeDao.getWriterSearchCount(keyword);
			if (count > 0) {
				searchList = novelHomeDao.writerSearchList(startRow, endRow, keyword);
				LogAspect.logger.info(LogAspect.LogMsg + "writerSearchList.toString=" + searchList.toString());
			}
		}

		LogAspect.logger.info(LogAspect.LogMsg + "count=" + count);
		
		
		
		mav.addObject("search_method", search_method);
		mav.addObject("keyword", keyword);
		mav.addObject("currentPage", currentPage);
		mav.addObject("boardSize", boardSize);
		mav.addObject("count", count);
		mav.addObject("searchList", searchList);
		mav.setViewName("novelhome/search.tiles");
	}
}
