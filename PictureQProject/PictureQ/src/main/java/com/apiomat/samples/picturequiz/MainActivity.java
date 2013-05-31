package com.apiomat.samples.picturequiz;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.apiomat.frontend.*;
import com.apiomat.frontend.callbacks.AOMCallback;
import com.apiomat.frontend.callbacks.AOMEmptyCallback;
import com.apiomat.frontend.pictureqmain.Riddle;
import com.apiomat.frontend.pictureqmain.User;

import java.net.URL;
import java.util.List;
import java.util.Random;

import static java.security.AccessController.getContext;

public class MainActivity extends Activity {

    public static final String TAG = "APIOMAT";
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private List<Riddle> riddles;
    private Riddle activeRiddle;
    static Random r = new Random();
    final User u = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.image1 = (ImageView) findViewById(R.id.picture1);
        this.image2 = (ImageView) findViewById(R.id.picture2);
        this.image3 = (ImageView) findViewById(R.id.picture3);

        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);


        u.setUserName(android_id);
        u.setPassword("test");

        u.loadMeAsync(new AOMEmptyCallback() {
            @Override
            public void isDone(ApiomatRequestException exception) {
                if (exception != null) {
                    u.saveAsync(new AOMEmptyCallback() {
                        @Override
                        public void isDone(ApiomatRequestException exception) {
                            loadRiddles();
                        }
                    });

                } else {
                    loadRiddles();
                }

            }
        });


    }

    private void loadRiddles() {
        Riddle.getRiddlesAsync("", new AOMCallback<List<Riddle>>() {
            @Override
            public void isDone(List<Riddle> resultObject, ApiomatRequestException exception) {
                riddles = resultObject;
                setNewRiddle();
            }
        });
    }

    public void setNewRiddle() {
        int length = riddles.size();
        int random = r.nextInt(length);
        Log.i(TAG, "random= " + random);
        activeRiddle = riddles.get(random);
        String[] images = {activeRiddle.getPic1URL(200, 200, "000000", null, "png"), activeRiddle.getPic2URL(200, 200, "ffffff", null, null), activeRiddle.getPic3URL(200, 200, "ffffff", null, "jpg")};
        loadImages(images);

    }

    public void loadImages(String[] images) {

        LoadImageTask task1 = new LoadImageTask(this.image1);
        task1.execute(images[0]);

        LoadImageTask task2 = new LoadImageTask(this.image2);
        task2.execute(images[1]);

        LoadImageTask task3 = new LoadImageTask(this.image3);
        task3.execute(images[2]);
    }

    public void solve(View view) {
        EditText editText = (EditText) findViewById(R.id.inputField);
        if (editText.getText().toString().equalsIgnoreCase(activeRiddle.getSolution())) {
            Toast.makeText(this, "toll", Toast.LENGTH_LONG).show();
            setNewRiddle();
        } else {
            Toast.makeText(this, "leider falsch", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
