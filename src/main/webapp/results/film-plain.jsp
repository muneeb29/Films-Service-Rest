<%@ page contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ page import="model.Film"%>
<%@ page import="java.util.ArrayList"%>
<%
String empty = "";

ArrayList<Film> plainFilms = (ArrayList<Film>) request.getAttribute("films");

for (Film f : plainFilms) {
	
	out.write("+" + f.getId() + "+" + f.getTitle() + "+" + f.getYear() 
	+ "+" + f.getDirector() + "+" + f.getStars() + "+" + f.getReview());

}

%>
