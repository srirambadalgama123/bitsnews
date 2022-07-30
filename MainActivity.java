package com.example.bitsnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH = 3500;
    FirebaseAuth auth;
    FirebaseUser user;
    Animation topAnimation, bottomAnimation;
    ImageView logo;
    TextView title, tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("line 27");
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        topAnimation = AnimationUtils.loadAnimation( this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation( this, R.anim.bottom_animation);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        tagline = findViewById(R.id.tagline);

        logo.setAnimation(topAnimation);
        title.setAnimation(bottomAnimation);
        tagline.setAnimation(bottomAnimation);

        auth= null;   //FirebaseAuth.getInstance ();
        user= null;         //auth.getCurrentUser ();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("username = "+user);
                if (user !=null)
                {
                    startActivity ( new Intent( MainActivity.this, HomeNavigationActivity.class ) );
                }
                else
                {
                    startActivity ( new Intent ( MainActivity.this,LoginActivity.class ) );
                }

            }
        },SPLASH);
    }
}