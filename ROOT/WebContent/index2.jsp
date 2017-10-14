<%--
  Created by IntelliJ IDEA.
  User: Cesar
  Date: 22-04-2016
  Time: 4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.recyclothes.servlet.UAgentInfo"%>

<%
  //2. Initialize the browser info variables
  String userAgent = request.getHeader("User-Agent");
  String httpAccept = request.getHeader("Accept");

  //3. Create the UAgentInfo object
  UAgentInfo detector = new UAgentInfo(userAgent, httpAccept);

  //4. Detect whether the visitor is using a mobile device.
  //   For example, if it's an iPhone, redirect them to the
  //   iPhone-optimized version of your web site.
  if (detector.detectIphone()) {
    response.sendRedirect("404.html");
  } else {
    response.sendRedirect("principal.jsp");
  }
%>
