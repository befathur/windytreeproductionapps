package umn.ac.id.windytreeproduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderVer extends AppCompatActivity {
    private Button btncancel;
    String fullname;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_ver);

        Intent intent = getIntent();
        fullname = intent.getStringExtra("fullname");

        btncancel = findViewById(R.id.cancelOrder);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deleteorder(fullname);
            }
        });
    }

    public void order(View view) {
        Intent intent = new Intent(OrderVer.this, MainActivity.class);
        intent.putExtra("fullname", fullname);
        startActivity(intent);
        finish();
    }

    public void Deleteorder(String fullname) {
        DatabaseReference drfullname = FirebaseDatabase.getInstance().getReference("datapemesan").child(fullname);

        drfullname.removeValue();
        Toast.makeText(this, "Booking Canceled!", Toast.LENGTH_SHORT).show();

        Intent cancelIntent = new Intent(OrderVer.this,MainActivity.class);
        startActivity(cancelIntent);
        finish();
    }

}