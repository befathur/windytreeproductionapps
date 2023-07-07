package umn.ac.id.windytreeproduction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayVideo extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video);

    }

    public void exit(View view) {
        Intent exitIntent = new Intent(PlayVideo.this,MainActivity.class);
        startActivity(exitIntent);
        finish();
    }
}