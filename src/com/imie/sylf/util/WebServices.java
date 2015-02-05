package com.imie.sylf.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.imie.sylf.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WebServices extends AsyncTask<String,Void,String> {

    public Parser parser = null;
    Activity context;
    LinearLayout progressBar = null;
    
    public WebServices(Activity context) {
        this.context = context;
    }
    
    public WebServices(Activity context, LinearLayout progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }
    
    /* (non-Javadoc)
     * @see android.os.AsyncTask#onPreExecute()
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        
        // Display the progressBar
        if(progressBar != null){
        	progressBar.setVisibility(View.VISIBLE);
        }
    }
    
    @Override
    protected String doInBackground(String... urls) {
        StringBuilder sb = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urls[0]);

            // Open connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setChunkedStreamingMode(0);

            // Test connection to server
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                if (inputStream != null){
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    
                    String line = null;

                    // Read Server Response
                    while((line = br.readLine()) != null)
                    {
                        // Append server response in string
                        sb.append(line + "");
                    }

                    // Append Server Response To Content String 
                    Log.d("string web service", sb.toString());
                }
            }else{
                
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
        }
        return sb.toString();
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(final String result) {
        super.onPostExecute(result);


        // Hide the progressBar
        if(progressBar != null){
        	progressBar.setVisibility(View.GONE);
        }
        
        this.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    parser.parseJSON(result);
                }
        });
    }

}