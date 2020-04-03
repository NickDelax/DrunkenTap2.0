package com.example.drunkentaplogin;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashBoard extends AppCompatActivity {

    String EmailHolder;
    TextView Email;
    Button LogOUT ;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser mUser;
    //@SuppressLint("SetTextI18n")
    public static final String TAG="LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Email = (TextView)findViewById(R.id.textView1);
        LogOUT = (Button)findViewById(R.id.button1);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(Login.userEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+ EmailHolder);

        // Adding click listener to Log Out button.

        LogOUT.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View v) {


                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(DashBoard.this,"Exito al cerrar sesión", Toast.LENGTH_LONG).show();
                //Intent intent=new Intent(DashboardActivity.this,login.class);
                //startActivity(intent);
               /*if (v.getId() == R.id.button1) {
                    AuthUI.getInstance()
                            .signOut(this)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull Task<Void> task) {
                                    // user is now signed out
                                    startActivity(new Intent(DashboardActivity.this, login.class));
                                    finish();
                                }
                            });
                }*/

            }
        });

    }


}
