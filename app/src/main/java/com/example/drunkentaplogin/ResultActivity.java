package com.example.drunkentaplogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.ServerTokenSession;


import java.util.Arrays;


public class ResultActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = (TextView)findViewById(R.id.textView1);
        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("Zaf0b2Socpjhb7-PrXm71KHRWOU1kXri")
                .setServerToken("")
                //.setRedirectUri()
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                .setEnvironment(SessionConfiguration.Environment.SANDBOX).build();
        UberSdk.initialize(config);

        RideRequestButton requestButton = new RideRequestButton(ResultActivity.this);
        RelativeLayout layout = new RelativeLayout(this);
        layout.addView(requestButton);

        RideParameters rideParams = new RideParameters.Builder()
                // Optional product_id from /v1/products endpoint (e.g. UberX). If not provided, most cost-efficient product will be used
                .setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d")
                // Required for price estimates; lat (Double), lng (Double), nickname (String), formatted address (String) of dropoff location
                .setDropoffLocation(
                        (double) 37.775304f, -122.417522, "Uber HQ", "1455 Market Street, San Francisco")
                // Required for pickup estimates; lat (Double), lng (Double), nickname (String), formatted address (String) of pickup location
                .setPickupLocation((double) 37.775304f, -122.417522, "Uber HQ", "1455 Market Street, San Francisco")
                .build();
// set parameters for the RideRequestButton instance
        requestButton.setRideParameters(rideParams);
        ServerTokenSession session = new ServerTokenSession(config);
        requestButton.setSession(session);
        requestButton.loadRideInformation();

        //get rating bar object
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);
        //get text view
        TextView t=(TextView)findViewById(R.id.textResult);
        //get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        //display score
        bar.setRating(score);
        switch (score)
        {
            case 0: t.setText("Obtuviste 0%, Estas muy borracho pide un Uber no te arriesgues bro");
                break;
            case 1: t.setText("Obtuviste 20%, Estas borracho, a casa campeón");
                break;
            case 2: t.setText("Obtuviste 40%, No estas muy Borracho, quieres pedir un Uber?");
                break;
            case 3: t.setText("Obtuviste 60%, Estas bien, sigue pisteando Bro ");
                break;
            case 4:t.setText("Obtuviste 80% Chupale más");
                break;
            case 5:t.setText("Impresionante obtuviste 100%, no estas ebrio campeón");
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, DashBoard.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        Intent UberIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://m.uber.com/looking"));
        startActivity(UberIntent);
    }
}