package group11.cinematkhdt;

import model.DeveloperKey;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class TrailerFilmFromDetail extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {
	private String trailer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_trailerfilmfromdetail);

		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.videoTrailer);
		youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		Intent i = getIntent();
		trailer = i.getStringExtra("trailer");
		if (!wasRestored)
			player.cueVideo(trailer); // your video to play
	}

}
