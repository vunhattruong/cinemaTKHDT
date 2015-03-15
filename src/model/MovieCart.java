package model;

import java.util.ArrayList;

public class MovieCart {
	private ArrayList<Movie> cartMovie = new ArrayList<Movie>();

	public Movie getMovie(int position) {
		return cartMovie.get(position);
	}

	public void setMovie(Movie movie) {
		cartMovie.add(movie);
	}

	public int getCartSize() {
		return cartMovie.size();
	}

	public boolean checkMovieInCart(Movie aMovie) {
		return cartMovie.contains(aMovie);
	}
}
