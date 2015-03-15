package fragment;

import group11.cinematkhdt.MovieCart1;
import group11.cinematkhdt.R;
import group11.cinematkhdt.TrailerFilmFromDetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import controller.AppController;

public class DetailFilm extends Activity {

	private String title, image, detail, trailer, genreArry, rating;
	private int year;
	private TextView txttitle, txtrating, txtDetail, txtyear, txtgenre;
	private NetworkImageView imageView;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	private ImageView imgViewTrailer, imgViewBooking;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_film);

		imageView = (NetworkImageView) findViewById(R.id.thumbnail);
		txttitle = (TextView) findViewById(R.id.title);
		txtrating = (TextView) findViewById(R.id.rating);
		txtDetail = (TextView) findViewById(R.id.tvDetailInfor);
		txtgenre = (TextView) findViewById(R.id.genre);
		txtyear = (TextView) findViewById(R.id.year);
		imgViewBooking = (ImageView) findViewById(R.id.imgViewBooking);
		imgViewTrailer = (ImageView) findViewById(R.id.imgViewTrailer);

		Intent i = getIntent();
		// get image
		image = i.getStringExtra("image");
		// get title
		title = i.getStringExtra("title");
		// get rating
		rating = i.getStringExtra("rating");
		// get detail
		detail = i.getStringExtra("detail");
		// get Trailer
		trailer = i.getStringExtra("trailer");
		// get genre
		genreArry = i.getStringExtra("genre");
		// get year
		year = i.getIntExtra("year", 0);
		String y = Integer.toString(year);

		imageView.setImageUrl(image, imageLoader);
		txttitle.setText(title);
		txtrating.setText("Rating: " + rating);
		txtDetail.setText(detail);
		txtgenre.setText("Genre: " + genreArry);
		txtyear.setText("Year: " + y);

		imgViewBooking.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(DetailFilm.this, MovieCart1.class);
				i.putExtra("image", image);
				i.putExtra("title", title);
				i.putExtra("rating", rating);
				i.putExtra("genre", genreArry);
				i.putExtra("year", year);
				startActivity(i);

			}
		});
		imgViewTrailer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						TrailerFilmFromDetail.class);
				i.putExtra("trailer", trailer);
				startActivity(i);
			}
		});
	}

}
