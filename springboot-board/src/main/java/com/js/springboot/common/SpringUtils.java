package com.js.springboot.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpringUtils {

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
	
	
	/**
	 *  1. cPage 
	 *  2. limit 10
	 * 	3. totalContents 총 컨텐츠수
	 * 	4. url 이동할 주소 /spring/board/boardList.do
	 *  ----------------------------------------
	 * 	5. totalPage 전체페이지수 - pageNo 넘침 방지
	 * 	6. pageBarSize 페이지바에 표시할 페이지 개수 지정 : 5
	 * 	7. pageStart ~ pageEnd pageNo의 범위
	 * 	8. pageNo 페이지넘버를 출력할 증감변수
	 * 
	 * 
	 * 
	 * @param cpage
	 * @param numPerPage
	 * @param totalContents
	 * @param url
	 * @return
	 */
	public static String getPageBar(int cpage, int numPerPage, int totalContents, String url) {
		StringBuilder pageBar = new StringBuilder();
		//StringBuilder : 문자끼리 더하기 연산시 한 문자를 그대로 사용한다.(mutable)
		//String : 문자끼리 더하기 연산할때마다 새로운 문자를 만들어낸다.(immutable)
		pageBar.append("<nav aria-label=\"Page navigation example\">\r\n"
				+ "	  <ul class=\"pagination justify-content-center\">");
		
		int totalPage = (int)Math.ceil((double)totalContents / numPerPage); 
		int pageBarSize = 5;
		url = (url.indexOf("?") > -1) ? url + "&" : url + "?";
		
		int pageStart = ((cpage - 1) / pageBarSize) * pageBarSize + 1; // 1 6 11
		int pageEnd = pageStart + pageBarSize - 1;// 5 10 15
		
		//증감변수는 pageStart부터 시작
		int pageNo = pageStart;
		
		//1. 이전
		if(pageNo == 1) {
			//이전버튼 비활성화
			pageBar.append("<li class=\"page-item disabled\"><a class=\"page-link\" href='" + url + "cpage=" + (pageNo - 1) +  "' aria-label=\"Previous\"/>"
					+ "<span aria-hidden=\"true\">&laquo;</span>\n<span class=\"sr-only\">Previous</span></a></li>\n");
		}else {
			//이전버튼 활성화
			//pageNo가 6또는 11인경우
			pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href='" + url + "cpage=" + (pageNo - 1) +  "' aria-label=\"Previous\"/>"
					+ "<span aria-hidden=\"true\">&laquo;</span>\n<span class=\"sr-only\">Previous</span></a></li>\n"); // /spring/board/boardList.do?cpage=
		}
		//2. pageNo
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cpage) {
				//pageNo가 현재페이지와 같다면 링크만들필요없음(현재페이지인경우,링크 비활성화)
				pageBar.append("<li class=\"page-item active\"><a class=\"page-link\" id=\"activePage\" href='" + url + "cpage=" + pageNo +  "'>" + pageNo +  "<span class=\"sr-only\">(current)</span></a></li>\n");
			}else {
				//현재페이지가 아닌경우(링크 활성화)
				pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href='" + url + "cpage=" + pageNo +  "'>" + pageNo +  "</a></li>\n");
			}
			
			pageNo++;
		}
		
		
		//3. 다음
		if(pageNo > totalPage) {
			//마지막페이지가 포함된 페이지바인 경우(다음버튼 비활성화)
			//마지막페이지인경우 
			pageBar.append("<li class=\"page-item disabled\"><a class=\"page-link\" href='" + url + "cpage=" + pageNo +  "' aria-label=\"Next\">"
					+ "<span aria-hidden=\"true\">&raquo;</span>\n<span class=\"sr-only\">Next</span></a></li>\n");
		}else {
			//다음버튼 활성화
			pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href='" + url + "cpage=" + pageNo +  "' aria-label=\"Next\">"
					+ "<span aria-hidden=\"true\">&raquo;</span>\n<span class=\"sr-only\">Next</span></a></li>\n");
		}
		
		pageBar.append("</ul>\r\n"+ "</nav>");
		return pageBar.toString();
	}

}
