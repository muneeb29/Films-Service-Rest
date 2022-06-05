<%@ page contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="com.google.gson.Gson"%>

<% 

Gson gson = new Gson();
String jsonResult;

	jsonResult = gson.toJson(request.getAttribute("films"));
%>

<%= jsonResult %>


