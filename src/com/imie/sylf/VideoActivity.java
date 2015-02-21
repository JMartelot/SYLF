package com.imie.sylf;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayerView;
import com.imie.sylf.entity.Show;
import com.imie.sylf.util.Config;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;

public class VideoActivity extends YouTubeBaseActivity implements
YouTubePlayer.OnInitializedListener, Parser<Show> {

	private static final int RECOVERY_DIALOG_REQUEST = 1;
	private static final String EXTRA_VIDEO = "video";
	private static final String EXTRA_RESULTS = "results";
	private static final String EXTRA_KEY = "key";
	private JSONArray video = null;
	private Show show = new Show();

	// YouTube player view
	private YouTubePlayerView youTubeView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_video);

		Intent i = getIntent();
		show = (Show) i.getSerializableExtra(EXTRA_VIDEO);

		String url = "http://api.themoviedb.org/3/tv/"+ show.getId() +"/videos?api_key=0d2d4cca633bc7bc04a564ac8266d3a1";

		WebServices ws = new WebServices(this);
		ws.parser = this;
		ws.execute(url);
	}

	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider,
			YouTubeInitializationResult errorReason) {
		if (errorReason.isUserRecoverableError()) {
			errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
		} else {
			String errorMessage = String.format(
					getString(R.string.error_player), errorReason.toString());
			Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		if (!wasRestored) {

			// loadVideo() will auto play video
			// Use cueVideo() method, if you don't want to play it automatically
			player.loadVideo(show.getVideo());

			// Hiding player controls
			player.setPlayerStyle(PlayerStyle.CHROMELESS);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RECOVERY_DIALOG_REQUEST) {
			// Retry initialization if user performed a recovery action
			getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
		}
	}

	private YouTubePlayer.Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.youtube_view);
	}

	@Override
	public void parseJSON(String stream) {
		if (stream != null) {
			try {
				JSONObject jsonObj = new JSONObject(stream);

				video = jsonObj.getJSONArray(EXTRA_RESULTS);	
				JSONObject v = video.getJSONObject(0);

				show.setVideo(v.getString(EXTRA_KEY));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
		
		if(show.getVideo() != null){
			bindVideo();
		} else{
			Context context = getApplicationContext();
			CharSequence text = "No Trailer for this one! Sorry^^";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
			super.onBackPressed();
		}	
	}

	public void bindVideo() {		
		youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		// Initializing video player with developer key
		youTubeView.initialize(Config.DEVELOPER_KEY, this);
	}
}