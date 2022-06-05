package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class FilmDAO {

	Film oneFilm = null;
	Connection conn = null;
	Statement stmt = null;

	//Connection username and password require adding
	String user ="";
	// Note none default port used, 6306 not 3306
	String url = "jdbc:mysql:" + user;

	public FilmDAO() {
	}

	private void openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}

	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs) {
		Film thisFilm = null;
		try {
			thisFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisFilm;
	}

	public ArrayList<Film> getAllFilms() {

		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from films";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				allFilms.add(oneFilm);
			}
			
			System.out.println("Query: " + selectSQL);
			System.out.println("Number of Films Found: " + allFilms.size());
			
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return allFilms;
	}

	public Film getFilmByID(int id) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where id=" + id;
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		System.out.println(oneFilm.toString());

		return oneFilm;
	}

	public int insertFilm(Film f) {

		int rs1 = 0;
		openConnection();

		try {
			String insertSQL = "INSERT INTO films (id, title, year, director, stars, review) VALUES ('" + f.getId()
					+ "','" + f.getTitle() + "','" + f.getYear() + "','" + f.getDirector() + "','" + f.getStars()
					+ "','" + f.getReview() + "');";
			rs1 = stmt.executeUpdate(insertSQL);

			System.out.println("Query: " + insertSQL);
			System.out.println("Film: " + f.getTitle() + " is Inserted");
			stmt.close();
			closeConnection();

		} catch (SQLException se) {
			System.out.println(se);
		}

		return rs1;

	}

	public int updateFilm(Film f) {
		int rs1 = 0;
		openConnection();

		try {
			String updateSQL = "UPDATE films SET title = '" + f.getTitle() + "',"
					+ "year = '" + f.getYear() + "'," + "director = '" + f.getDirector() + "'," + "stars = '"
					+ f.getStars() + "'," + "review = '" + f.getReview() + "'" + "where id='" + f.getId() + "';";

			rs1 = stmt.executeUpdate(updateSQL);

			System.out.println(f.getId());
			System.out.println("Query: " + updateSQL);
			System.out.println("Film: " + f.title + " is Updated");
			stmt.close();
			closeConnection();

		} catch (SQLException se) {
			System.out.println(se);
		}

		return rs1;

	}

	public int deleteFilm(int id) {
		int rs1 = 0;
		openConnection();

		try {
			String deleteSQL = "DELETE FROM films WHERE id = '" + id + "';";
			rs1 = stmt.executeUpdate(deleteSQL);

			System.out.println("Query: " + deleteSQL);
			System.out.println("Film: " + id + " Is Deleted");
			stmt.close();
			closeConnection();

		} catch (SQLException se) {
			System.out.println(se);
		}

		return rs1;

	}

	public ArrayList<Film> getFilmByTitle(String Film) {
		ArrayList<Film> fiList = new ArrayList<Film>();
		openConnection();

		try {
			String selectSQL = "select * from films where title like '%" + Film + "%';";

			ResultSet rs1 = stmt.executeQuery(selectSQL);

			System.out.println(rs1.getFetchSize());

			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				fiList.add(oneFilm);
			}
			System.out.println("Query: " + selectSQL);
			System.out.println("Number of Films Found: " + fiList.size());
			System.out.println("Films Found: " + fiList.toString());
			stmt.close();
			closeConnection();

		} catch (SQLException se) {
			System.out.println(se);
		}

		return fiList;

	}

}
