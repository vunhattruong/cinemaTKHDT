package model;

import java.util.ArrayList;

public class Movie {
	private String title, thumbnailUrl, detail, trailer, genre, rating, stt;
	private int pid, year;
	private ArrayList<String> date;

	public Movie() {
	}

	public Movie(String name, String thumbnailUrl, String detail, int year,
			int pid, String rating, String trailer, String genre, String stt,
			ArrayList<String> date) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.detail = detail;
		this.year = year;
		this.pid = pid;
		this.rating = rating;
		this.trailer = trailer;
		this.genre = genre;
		this.date = date;
		this.stt = stt;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public ArrayList<String> getDate() {
		return date;
	}

	public void setDate(ArrayList<String> date) {
		this.date = date;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

}
