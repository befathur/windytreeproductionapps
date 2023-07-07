package umn.ac.id.windytreeproduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class aboutUs extends AppCompatActivity {
    private ImageView homebut, orderlistbut, settingsbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        homebut = findViewById(R.id.homebut);
        orderlistbut = findViewById(R.id.orderlistbut);
        settingsbut = findViewById(R.id.settingsbut);

        homebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.homebut:
                        startActivity(new Intent(aboutUs.this,MainActivity.class));
                        break;

                }
            }
        });

        orderlistbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.orderlistbut:
                        startActivity(new Intent(aboutUs.this,OrderList.class));
                        break;

                }
            }
        });

        settingsbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.settingsbut:
                        startActivity(new Intent(aboutUs.this,ProfilePage.class));
                        break;

                }
            }
        });
    }
}