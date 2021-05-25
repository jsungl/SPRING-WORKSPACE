package com.kh.spring.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloSpringUtils {

	
	public static String getRenamedFilename(String originalFilename) {
		//확장자 추출
		//test.jpg
		int beginIndex = originalFilename.lastIndexOf("."); //4
		String ext = originalFilename.substring(beginIndex); //.jpg
		
		//년월일_난수 format
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
		DecimalFormat df = new DecimalFormat("000"); //정수부3자리
		
		return sdf.format(new Date()) + df.format(Math.random() * 1000) + ext;
	}

	
	
	public static String getPageBar(int cpage, int numPerPage, int totalContents, String url) {
		StringBuilder pageBar = new StringBuilder();
		int totalPage = (int)Math.ceil((double)totalContents / numPerPage); 
		int pageBarSize = 5;
		//cPage이외의 다른 사용자입력값이 있는경우대비 (/mvc/admin/memberFinder?type=id&kw=abc&cPage=  )
		url = (url.indexOf("?") > -1) ? url + "&" : url + "?";
		
		int pageStart = ((cpage - 1) / pageBarSize) * pageBarSize + 1; // 1 6 11
		int pageEnd = pageStart + pageBarSize - 1;// 5 10 15
		
		//증감변수는 pageStart부터 시작
		int pageNo = pageStart;
		
		//1. 이전
		if(pageNo == 1) {

		}else {
			//pageNo가 6또는 11인경우
			pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href='" + url + "cpage=" + (pageNo - 1) +  "' aria-label=\"Previous\"/>"
					+ "<span aria-hidden=\"true\">&laquo;</span>\n<span class=\"sr-only\">Previous</span></a></li>\n"); // /spring/board/boardList.do?cpage=
		}
		//2. pageNo
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cpage) {
				//pageNo가 현재페이지와 같다면 링크만들필요없음(현재페이지인경우)
				//pageBar.append("<span class='cpage'>" + pageNo + "</span>\n");
				pageBar.append("<li class=\"page-item disabled\"><a class=\"page-link\" href='" + url + "cpage=" + pageNo +  "'>" + pageNo +  "</a></li>\n");
			}else {
				pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href='" + url + "cpage=" + pageNo +  "'>" + pageNo +  "</a></li>\n");
			}
			
			pageNo++;
		}
		
		
		//3. 다음
		if(pageNo > totalPage) {
			//마지막페이지가 포함된 페이지바인 경우
			//마지막페이지인경우 
			
		}else {
			pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href='" + url + "cpage=" + pageNo +  "' aria-label=\"Next\">"
					+ "<span aria-hidden=\"true\">&raquo;</span>\n<span class=\"sr-only\">Next</span></a></li>\n");
		}
		
		
		return pageBar.toString();
	}

}















