package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;

import model.Film;
import model.FilmDAO;
import model.FilmList;

@WebServlet("/Rest")
public class Rest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchFilmname = request.getParameter("searchTitle");
		String format = request.getParameter("format");
		String outputPage = "";

		FilmDAO dao = new FilmDAO();
		ArrayList<Film> filmResults = new ArrayList<Film>();
		

		if (searchFilmname == null) {
			filmResults = dao.getAllFilms();
		}

		else if (searchFilmname != null) {
			filmResults = dao.getFilmByTitle(searchFilmname);
		}

		for (Film r : filmResults) {
			// System.out.println(r.toString());
		}

		if ("json".equals(format)) {

			response.setContentType("application/json");
			outputPage = "/results/film-json.jsp";
		}

		else if ("xml".equals(format)) {

			response.setContentType("text/xml");
			outputPage = "/results/film-xml.jsp";

		}
		

		else if("text".equals(format)){

			response.setContentType("text/plain");
			outputPage = "/results/film-plain.jsp";
		}
		request.setAttribute("films", filmResults);
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
		dispatcher.include(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String format = request.getParameter("format");
		FilmDAO dao = new FilmDAO();
		Film f = new Film();

		System.out.println(format);

		if ("text".equals(format)) {
			response.setHeader("Content-Type", "text/plain");

			String filmname = request.getParameter("titleIn");
			String director = request.getParameter("directorIn");
			String stars = request.getParameter("starsIn");
			String review = request.getParameter("reviewIn");
			String ids = request.getParameter("idIn");
			String years = request.getParameter("yearIn");

			int id = Integer.parseInt(ids);
			int year = Integer.parseInt(years);

			f.setId(id);
			f.setTitle(filmname);
			f.setYear(year);
			f.setDirector(director);
			f.setStars(stars);
			f.setReview(review);
			dao.insertFilm(f);

		}

		else if ("json".equals(format)) {
			response.setHeader("Content-Type", "application/json");
			BufferedReader reader = request.getReader();

			Gson gson = new Gson();

			Film fi = gson.fromJson(reader, Film.class);

			System.out.print(fi.getId());

			f.setId(fi.getId());
			f.setTitle(fi.getTitle());
			f.setYear(fi.getYear());
			f.setDirector(fi.getDirector());
			f.setStars(fi.getStars());
			f.setReview(fi.getReview());
			dao.insertFilm(f);
		}

		else if ("xml".equals(format)) {
			response.setHeader("Content-Type", "text/xml");
			BufferedReader reader = request.getReader();
			JAXBContext jaxbContext;
			Unmarshaller jaxbUnmarshaller;

			try {
				jaxbContext = JAXBContext.newInstance(Film.class);
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Film fi = (Film) jaxbUnmarshaller.unmarshal(reader);

				f.setId(fi.getId());
				f.setTitle(fi.getTitle());
				f.setYear(fi.getYear());
				f.setDirector(fi.getDirector());
				f.setStars(fi.getStars());
				f.setReview(fi.getReview());
				dao.insertFilm(f);

			}

			catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Film Insertion Complete!");

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Film f = new Film();
		FilmDAO dao = new FilmDAO();
		String format = request.getParameter("format");

		if ("text".equals(format)) {

			response.setHeader("Content-Type", "text/plain");
			String filmname = request.getParameter("upTitle");
			String director = request.getParameter("upDirector");
			String stars = request.getParameter("upStars");
			String review = request.getParameter("upReview");
			String ids = request.getParameter("upID");
			String years = request.getParameter("upYear");

			int id = Integer.parseInt(ids);
			int year = Integer.parseInt(years);

			f.setId(id);
			f.setTitle(filmname);
			f.setYear(year);
			f.setDirector(director);
			f.setStars(stars);
			f.setReview(review);

		}

		else if ("json".equals(format)) {
			response.setHeader("Content-Type", "application/json");
			BufferedReader reader = request.getReader();
			Gson gson = new Gson();

			Film fi = gson.fromJson(reader, Film.class);

			f.setId(fi.getId());
			f.setTitle(fi.getTitle());
			f.setYear(fi.getYear());
			f.setDirector(fi.getDirector());
			f.setStars(fi.getStars());
			f.setReview(fi.getReview());

		}

		else if ("xml".equals(format)) {
			response.setHeader("Content-Type", "text/xml");
			BufferedReader reader = request.getReader();
			JAXBContext jaxbContext;
			Unmarshaller jaxbUnmarshaller;

			try {
				jaxbContext = JAXBContext.newInstance(Film.class);
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Film fi = (Film) jaxbUnmarshaller.unmarshal(reader);

				f.setId(fi.getId());
				f.setTitle(fi.getTitle());
				f.setYear(fi.getYear());
				f.setDirector(fi.getDirector());
				f.setStars(fi.getStars());
				f.setReview(fi.getReview());

			}

			catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		dao.updateFilm(f);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO dao = new FilmDAO();
		String format = request.getParameter("format");
		
		if ("text".equals(format)) {
			response.setHeader("Content-Type", "text/plain");
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			dao.deleteFilm(id);
		}

		else if ("json".equals(format)) {
			response.setHeader("Content-Type", "application/json");

			BufferedReader reader = request.getReader();
			Gson gson = new Gson();

			Film fi = gson.fromJson(reader, Film.class);

			System.out.println(fi.getId());

			int id = fi.getId();
			dao.deleteFilm(id);
		}

		else if ("xml".equals(format)) {
			response.setHeader("Content-Type", "text/xml");
			BufferedReader reader = request.getReader();
			JAXBContext jaxbContext;
			Unmarshaller jaxbUnmarshaller;

			try {
				jaxbContext = JAXBContext.newInstance(Film.class);
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Film fi = (Film) jaxbUnmarshaller.unmarshal(reader);

				int id = fi.getId();
				dao.deleteFilm(id);
			}

			catch (JAXBException e) {
				e.printStackTrace();
			}

		}
	}
}
