package com.imie.sylf.util;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
/**
 * Deprecetaded class used for download image
 * 
 * @author Jean
 *
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    LinearLayout scrollView;
    LinearLayout.LayoutParams layoutParams;
    
    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }
    
    public DownloadImageTask(LinearLayout scrollView, LinearLayout.LayoutParams layoutParams, ImageView bmImage) {
        this.bmImage = bmImage;
        this.scrollView = scrollView;
        this.layoutParams = layoutParams;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
        
    	if(layoutParams != null && scrollView != null){
    		bmImage.setLayoutParams(layoutParams);
            scrollView.addView(bmImage);
    	}
    }
}
