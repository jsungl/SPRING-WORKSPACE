/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.44
 * Generated at: 2021-05-20 07:30:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Arrays;
import java.util.List;
import com.kh.spring.demo.model.vo.Dev;

public final class devUpdateForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1621235338534L));
    _jspx_dependants.put("jar:file:/C:/Workspaces/spring_workspace/hello-spring/src/main/webapp/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/fn.tld", Long.valueOf(1425946270000L));
    _jspx_dependants.put("jar:file:/C:/Workspaces/spring_workspace/hello-spring/src/main/webapp/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425946270000L));
    _jspx_dependants.put("jar:file:/C:/Workspaces/spring_workspace/hello-spring/src/main/webapp/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/fmt.tld", Long.valueOf(1425946270000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Arrays");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.kh.spring.demo.model.vo.Dev");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	Dev dev = (Dev)request.getAttribute("dev");
	String[] langArr = dev.getLang();
	List<String> langList = langArr != null ? Arrays.asList(langArr) : null;
	pageContext.setAttribute("langList", langList);

      out.write("\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/views/common/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("title", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Demo", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("div#demo-container{\r\n");
      out.write("\twidth:550px;\r\n");
      out.write("\tpadding:15px;\r\n");
      out.write("\tborder:1px solid lightgray;\r\n");
      out.write("\tborder-radius: 10px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<div id=\"demo-container\" class=\"mx-auto\">\r\n");
      out.write("\t<!-- https://getbootstrap.com/docs/4.1/components/forms/#readonly-plain-text -->\r\n");
      out.write("\t<form id=\"devFrm\"\r\n");
      out.write("\t\t  name=\"devFrm\"\r\n");
      out.write("\t\t  method=\"post\" \r\n");
      out.write("\t\t  action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/demo/updateDev.do\">\r\n");
      out.write("\t\t<div class=\"form-group row\">\r\n");
      out.write("\t\t  <label for=\"name\" class=\"col-sm-2 col-form-label\">이름</label>\r\n");
      out.write("\t\t  <div class=\"col-sm-10\">\r\n");
      out.write("\t\t    <input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dev.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" required>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"form-group row\">\r\n");
      out.write("\t\t  <label for=\"career\" class=\"col-sm-2 col-form-label\">개발경력</label>\r\n");
      out.write("\t\t  <div class=\"col-sm-10\">\r\n");
      out.write("\t\t    <input type=\"number\" class=\"form-control\" id=\"career\" name=\"career\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dev.career}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" required>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"form-group row\">\r\n");
      out.write("\t\t  <label for=\"email\" class=\"col-sm-2 col-form-label\">이메일</label>\r\n");
      out.write("\t\t  <div class=\"col-sm-10\">\r\n");
      out.write("\t\t    <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dev.email}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" required>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t  \t<!-- https://getbootstrap.com/docs/4.1/components/forms/#inline -->\r\n");
      out.write("\t    <div class=\"form-group row\">\r\n");
      out.write("\t    \t<label class=\"col-sm-2 col-form-label\">성별</label>\r\n");
      out.write("\t    \t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t    <div class=\"form-check form-check-inline\">\r\n");
      out.write("\t\t\t\t  <input class=\"form-check-input\" type=\"radio\" name=\"gender\"\r\n");
      out.write("\t\t\t\t  \t\t id=\"gender0\" value=\"M\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dev.gender eq 'M' ? 'checked' : '' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(">\r\n");
      out.write("\t\t\t\t  <label class=\"form-check-label\" for=\"gender0\">남</label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-check form-check-inline\">\r\n");
      out.write("\t\t\t\t  <input class=\"form-check-input\" type=\"radio\" name=\"gender\" \r\n");
      out.write("\t\t\t\t  \t\t id=\"gender1\" value=\"F\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dev.gender eq 'F' ? 'checked' : '' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(">\r\n");
      out.write("\t\t\t\t  <label class=\"form-check-label\" for=\"gender1\">여</label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"form-group row\">\r\n");
      out.write("\t\t\t<label class=\"col-sm-2 col-form-label\">개발언어</label>\r\n");
      out.write("\t\t\t<div class=\"col-sm-10\">\r\n");
      out.write("\t\t\t\t<div class=\"form-check form-check-inline\">\r\n");
      out.write("\t\t\t\t  <input class=\"form-check-input\" type=\"checkbox\" name=\"lang\" id=\"Java\" value=\"Java\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${langList.contains('Java') ? 'checked' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(">\r\n");
      out.write("\t\t\t\t  <label class=\"form-check-label\" for=\"Java\">Java</label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-check form-check-inline\">\r\n");
      out.write("\t\t\t\t  <input class=\"form-check-input\" type=\"checkbox\" name=\"lang\" id=\"C\" value=\"C\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${langList.contains('C') ? 'checked' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(">\r\n");
      out.write("\t\t\t\t  <label class=\"form-check-label\" for=\"C\">C</label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-check form-check-inline\">\r\n");
      out.write("\t\t\t\t  <input class=\"form-check-input\" type=\"checkbox\" name=\"lang\" id=\"Javascript\" value=\"Javascript\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${langList.contains('Javascript') ? 'checked' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(">\r\n");
      out.write("\t\t\t\t  <label class=\"form-check-label\" for=\"Javascript\">Javascript</label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"form-check form-check-inline\">\r\n");
      out.write("\t\t\t\t  <input class=\"form-check-input\" type=\"checkbox\" name=\"lang\" id=\"Python\" value=\"Python\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${langList.contains('Python') ? 'checked' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(">\r\n");
      out.write("\t\t\t\t  <label class=\"form-check-label\" for=\"Python\">Python</label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- 중요 - 수정시 반드시 고유번호도 함께 넘겨주어야 함 -->\r\n");
      out.write("\t  \t<input type=\"hidden\" name=\"no\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dev.no}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" />\r\n");
      out.write("\t  \t<button type=\"submit\" class=\"list-group-item list-group-item-action\">dev 수정</button>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("/**\r\n");
      out.write(" * 수정폼 유효성검사\r\n");
      out.write(" * \r\n");
      out.write(" */\r\n");
      out.write("$(document.devFrm).submit((e) => {\r\n");
      out.write("\t\r\n");
      out.write("\tvar $name = $(\"[name=name]\");\r\n");
      out.write("\t//1. 이름은 한글 2글자 이상이어야 한다.\r\n");
      out.write("\tif(/^[가-힣]{2,}$/.test($name.val()) == false){\r\n");
      out.write("\t\talert(\"이름은 한글 2자리이상이어야 합니다.\");\r\n");
      out.write("\t\t$name.select();\r\n");
      out.write("\t\te.preventDefault();\r\n");
      out.write("\t}\r\n");
      out.write("\t//2. 개발언어는 하나이상 선택해야 한다.\r\n");
      out.write("\tvar lang = $(\"[name=lang]:checked\").length;\r\n");
      out.write("\tif(lang < 1) {\r\n");
      out.write("\t\talert(\"개발언어는 하나이상 선택해야 합니다.\");\r\n");
      out.write("\t\te.preventDefault();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/views/common/footer.jsp", out, false);
      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}