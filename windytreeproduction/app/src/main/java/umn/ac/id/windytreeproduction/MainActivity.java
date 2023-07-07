package umn.ac.id.windytreeproduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private ImageView homebut, orderlistbut, settingsbut;
    private TextView viewVideo;
    String fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        fullname = intent.getStringExtra("fullname");

        homebut = findViewById(R.id.homebut);
        orderlistbut = findViewById(R.id.orderlistbut);
        settingsbut = findViewById(R.id.settingsbut);
        viewVideo = findViewById(R.id.viewVideo);

        homebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.homebut:
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        break;

                }
            }
        });

        orderlistbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.orderlistbut:
                        Intent intent = new Intent(MainActivity.this, OrderList.class);
                        intent.putExtra("fullname", fullname);
                        startActivity(intent);
                        break;

                }
            }
        });

        settingsbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.settingsbut:
                        startActivity(new Intent(MainActivity.this,ProfilePage.class));
                        break;

                }
            }
        });

        viewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.viewVideo:
                        startActivity(new Intent(MainActivity.this,PlayVideo.class));
                        break;
                }

            }
        });
    }

    public void book(View view) {
        Intent signupIntent = new Intent(MainActivity.this,OrderPage.class);
        startActivity(signupIntent);
        finish();
    }

}