package group11.cinematkhdt;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import controller.AppController;

public class MovieCart1 extends Activity implements OnClickListener {
	private String title, image, rating, genreArry;
	private int year;
	private TextView txttitle, txtrating, txtyear, txtgenre;
	private NetworkImageView imageView;
	private Spinner spnChooseDate, spnChooseTime, spnQuantity;
	private RadioGroup rgSeat;
	private RadioButton rbSeat;
	private ImageView imgCheck;
	@SuppressWarnings("rawtypes")
	private ArrayAdapter adapterChooseDate, adapterChooseTime, adapterQuantity;
	private Intent i;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_cart_1);

		// Get the ids of view objects
		findAllViewsId();
		// Get intent for cart
		getIntentFromDetailFilm();
		// Create Spinner data
		createSpinnerData();
		// Listener for imgCheck
		imgCheck.setOnClickListener(this);

	}

	private void findAllViewsId() {
		imageView = (NetworkImageView) findViewById(R.id.thumbnail);
		txttitle = (TextView) findViewById(R.id.title);
		txtrating = (TextView) findViewById(R.id.rating);
		txtgenre = (TextView) findViewById(R.id.genre);
		txtyear = (TextView) findViewById(R.id.year);
		spnChooseDate = (Spinner) findViewById(R.id.spinnerChooseDate);
		spnChooseTime = (Spinner) findViewById(R.id.spinnerChooseTime);
		spnQuantity = (Spinner) findViewById(R.id.spinnerQuantity);
		rgSeat = (RadioGroup) findViewById(R.id.rgSeat);
		imgCheck = (ImageView) findViewById(R.id.imgCheck);
	}

	private void getIntentFromDetailFilm() {
		i = getIntent();
		// get image
		image = i.getStringExtra("image");
		// get title
		title = i.getStringExtra("title");
		// get rating
		rating = i.getStringExtra("rating");
		// get genre
		genreArry = i.getStringExtra("genre");
		// get year
		year = i.getIntExtra("year", 0);
		String y = Integer.toString(year);

		imageView.setImageUrl(image, imageLoader);
		txttitle.setText(title);
		txtrating.setText("Rating: " + rating);
		txtgenre.setText("Genre: " + genreArry);
		txtyear.setText("Year: " + y);
	}

	private void createSpinnerData() {
		// Spinner Choose Date
		adapterChooseDate = ArrayAdapter.createFromResource(MovieCart1.this,
				R.array.date, android.R.layout.simple_spinner_item);
		adapterChooseDate
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnChooseDate.setAdapter(adapterChooseDate);

		// Spinner Choose Time
		adapterChooseTime = ArrayAdapter.createFromResource(MovieCart1.this,
				R.array.time, android.R.layout.simple_spinner_item);
		adapterChooseTime
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnChooseTime.setAdapter(adapterChooseTime);

		// Spinner Quantity
		adapterQuantity = ArrayAdapter.createFromResource(MovieCart1.this,
				R.array.quantity, android.R.layout.simple_spinner_item);
		adapterQuantity
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnQuantity.setAdapter(adapterQuantity);
	}

	@Override
	public void onClick(View arg0) {
		// listener for spinnerChooseDate
		String spinnerStrDate = spnChooseDate.getSelectedItem().toString();
		String spinnerStrTime = spnChooseTime.getSelectedItem().toString();
		String spinnerStrQuantity = spnQuantity.getSelectedItem().toString();
		// listener for radioGroup
		int id = rgSeat.getCheckedRadioButtonId();
		rbSeat = (RadioButton) findViewById(id);
		i = new Intent(getApplicationContext(), MovieCart2.class);
		i.putExtra("title", title);
		i.putExtra("image", image);
		i.putExtra("chooseDate", spinnerStrDate);
		i.putExtra("chooseTime", spinnerStrTime);
		i.putExtra("quantity", spinnerStrQuantity);
		i.putExtra("seat", rbSeat.getText().toString());
		startActivity(i);
	}

}
