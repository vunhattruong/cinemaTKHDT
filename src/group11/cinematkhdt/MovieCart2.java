package group11.cinematkhdt;

import java.util.ArrayList;
import java.util.List;

import model.DeveloperKey;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.JSONParser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import controller.AppController;

public class MovieCart2 extends Activity {

	private String image, title, spinnerDate, spinnerTime, spinnerQuantity,
			rgSeat;
	private double totalPrice;
	private TextView tvTitle, tvDay, tvTime, tvQuantity, tvSeat, tvTotalPrice;
	private ImageView imgTicketOk;
	private NetworkImageView imageView;
	private Intent i;
	// Progress Dialog
	private ProgressDialog pDialog;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_cart_2);

		imageView = (NetworkImageView) findViewById(R.id.thumbnail);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvDay = (TextView) findViewById(R.id.tvDate);
		tvTime = (TextView) findViewById(R.id.tvTime);
		tvQuantity = (TextView) findViewById(R.id.tvQuantity);
		tvSeat = (TextView) findViewById(R.id.tvTypeSeat);
		tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
		imgTicketOk = (ImageView) findViewById(R.id.imgTicketOk);

		// get Intent
		i = getIntent();
		// get image
		image = i.getStringExtra("image");
		// get title
		title = i.getStringExtra("title");
		// get Date
		spinnerDate = i.getStringExtra("chooseDate");
		// get Time
		spinnerTime = i.getStringExtra("chooseTime");
		// get Quantity
		spinnerQuantity = i.getStringExtra("quantity");
		// get seat
		rgSeat = i.getStringExtra("seat");

		imageView.setImageUrl(image, imageLoader);
		tvTitle.setText("Title: " + title);
		tvDay.setText("Day: " + spinnerDate);
		tvTime.setText("Time: " + spinnerTime);
		tvQuantity.setText("Quantity: " + spinnerQuantity);
		tvSeat.setText("Type Seat: " + rgSeat);

		// total Price
		int quantity = Integer.parseInt(spinnerQuantity);
		if (rgSeat.equalsIgnoreCase("V.I.P")) {
			totalPrice = quantity * 80.000;
		} else {
			totalPrice = quantity * 40.000;
		}
		String p = Double.toString(totalPrice);
		tvTotalPrice.setText("Total Price: " + p);

		// set listener for imgTicketOk
		imgTicketOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				new TicketOk().execute();

			}
		});
	}

	class TicketOk extends AsyncTask<String, String, String> {
		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MovieCart2.this);
			pDialog.setMessage("Booking Tickets..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("title", title));
			params.add(new BasicNameValuePair("date", spinnerDate));
			params.add(new BasicNameValuePair("time", spinnerTime));
			params.add(new BasicNameValuePair("seat", spinnerQuantity));
			params.add(new BasicNameValuePair("typeseat", rgSeat));
			String p = Double.toString(totalPrice);
			params.add(new BasicNameValuePair("price", p));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jParser.makeHttpRequest(DeveloperKey.URL_TICKETOK, "POST",
					params);

			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Intent i = new Intent(getApplicationContext(),
							TicketSuccess.class);
					startActivity(i);

					// closing this screen
					finish();
				} else {
					// failed to create product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
}
