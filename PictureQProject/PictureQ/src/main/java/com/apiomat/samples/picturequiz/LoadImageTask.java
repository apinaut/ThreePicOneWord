package com.apiomat.samples.picturequiz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.net.URL;

/**
 * Created by phimi on 31.05.13.
 */
public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView view = null;
    LoadImageTask(ImageView view) {
        this.view = view;
    }

    @Override
    protected Bitmap doInBackground(String... images) {
        try {
            URL url = new URL(images[0]);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch(Exception e) {
            Log.e(MainActivity.TAG, "Error occured. " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        this.view.setImageBitmap(bitmap);
    }
}
