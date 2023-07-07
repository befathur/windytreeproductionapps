package umn.ac.id.windytreeproduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class BootScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boot_screen);

        SystemClock.sleep(3000);
        Intent loginIntent = new Intent(BootScreen.this,SignActivity.class);
        startActivity(loginIntent);
        finish();
    }
}