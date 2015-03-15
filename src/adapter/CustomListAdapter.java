package adapter;

import fragment.DetailFilm;
import group11.cinematkhdt.R;
import controller.AppController;
import model.Movie;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter implements Filterable {
	private Activity activity;
	private LayoutInflater inflater;
	private Filter movieFilter;
	private List<Movie> movieItems;
	private List<Movie> orgMovieItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Movie> movieItems,
			Context ctx) {
		this.activity = activity;
		this.movieItems = movieItems;
		this.orgMovieItems = movieItems;
		getFilter();
	}

	@Override
	public int getCount() {
		return movieItems.size();
	}

	@Override
	public Movie getItem(int location) {
		return movieItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return movieItems.get(position).hashCode();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView rating = (TextView) convertView.findViewById(R.id.rating);
		TextView detail = (TextView) convertView
				.findViewById(R.id.tvDetailInfor);
		TextView trailer = (TextView) convertView.findViewById(R.id.tvTrailer);
		TextView genre = (TextView) convertView.findViewById(R.id.genre);
		TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
		TextView pid = (TextView) convertView.findViewById(R.id.tvID);
		TextView stt = (TextView) convertView.findViewById(R.id.tvStt);

		// getting movie data for the row
		Movie m = movieItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

		// title
		title.setText(m.getTitle());

		// rating
		rating.setText("Rating: " + String.valueOf(m.getRating()));

		// detail
		detail.setText(m.getDetail());
		detail.setVisibility(View.GONE);

		// trailer
		trailer.setText(m.getTrailer());
		trailer.setVisibility(View.GONE);

		// genre
		genre.setText(m.getGenre());

		// release year
		year.setText(String.valueOf(m.getYear()));

		// pid
		pid.setText(String.valueOf(m.getPid()));

		// stt
		stt.setText(m.getStt());

		convertView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Movie p = movieItems.get(position);
				Intent i = new Intent(activity.getApplicationContext(),
						DetailFilm.class);
				i.putExtra("image", p.getThumbnailUrl());
				i.putExtra("title", p.getTitle());
				i.putExtra("rating", p.getRating());
				i.putExtra("detail", p.getDetail());
				i.putExtra("trailer", p.getTrailer());
				i.putExtra("genre", p.getGenre());
				i.putExtra("year", p.getYear());
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				activity.getApplicationContext().startActivity(i);
			}
		});
		return convertView;
	}

	public void resetData() {
		movieItems = orgMovieItems;
	}

	@Override
	public Filter getFilter() {
		if (movieFilter == null) {
			movieFilter = new MovieFilter();
		}

		return movieFilter;
	}

	private class MovieFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			if (constraint == null || constraint.length() == 0) {
				filterResults.values = orgMovieItems;
				filterResults.count = orgMovieItems.size();
			} else {
				List<Movie> tempList = new ArrayList<Movie>();
				// search content in movie list
				for (Movie movie : movieItems) {
					if (movie.getTitle().toUpperCase()
							.startsWith(constraint.toString().toUpperCase()))
						tempList.add(movie);

				}
				filterResults.values = tempList;
				filterResults.count = tempList.size();
			}
			return filterResults;

		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			if (results.count == 0) {
				notifyDataSetInvalidated();
			} else {
				movieItems = (List<Movie>) results.values;
				notifyDataSetChanged();
			}
		}
	}
}