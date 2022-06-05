package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "filmlist")
@XmlAccessorType(XmlAccessType.FIELD)
public class FilmList {
	@XmlElement(name ="film")
	
	ArrayList<Film> listOfFilms;
	
	public FilmList(){
		
	}
	
	public FilmList(ArrayList<Film> films) {
		this.listOfFilms = films;
	}
	
	public void setFilmList(ArrayList<Film> filmList) {
		this.listOfFilms = filmList;
	}
	
	public ArrayList<Film>getFilmList(){
		return listOfFilms;
	}

}
