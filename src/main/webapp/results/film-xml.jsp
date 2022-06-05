<%@ page contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ page import="model.FilmList"%>
<%@ page import="javax.xml.bind.JAXBContext"%>
<%@ page import="javax.xml.bind.JAXBException"%>
<%@ page import="javax.xml.bind.Marshaller"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Film"%>


<%
try {

	JAXBContext context = JAXBContext.newInstance(FilmList.class);
	Marshaller m = context.createMarshaller();

	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	FilmList f = new FilmList();

	ArrayList<Film> films = new ArrayList<Film>();

	films = (ArrayList<Film>) request.getAttribute("films");

	f.setFilmList(films);

	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	m.marshal(f, out);

} catch (JAXBException e) {
	e.printStackTrace();
}
%>