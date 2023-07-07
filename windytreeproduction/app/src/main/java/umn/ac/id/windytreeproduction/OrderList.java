package umn.ac.id.windytreeproduction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderList extends AppCompatActivity {
    private ImageView homebut, orderlistbut, settingsbut;
    FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;
    private ListView listData;
    String fullName;


    ArrayList<String> arraybook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_order);

        //Intent intent = getIntent();
        //fullName = intent.getStringExtra("fullname");
        try {
            Intent intent = getIntent();
            fullName = intent.getStringExtra("fullname");
        }catch (NullPointerException e){
            Toast.makeText(this, "Data Booking Masih Kosong", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(OrderList.this,MainActivity.class));
            finish();
        }


        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = mDatabase.getInstance().getReference();

        final ArrayAdapter<String> adapterArray = new ArrayAdapter<String>(OrderList.this, android.R.layout.simple_list_item_1, arraybook);

        listData = findViewById(R.id.listBook);
        arraybook = new ArrayList<String>();
        try {
            initializeListView();
        }catch (NullPointerException e){
            Toast.makeText(this, "Data Booking Masih Kosong", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(OrderList.this,MainActivity.class));
            finish();
        }
        //initializeListView();
        //arraybook.get(0);


        homebut = findViewById(R.id.homebut);
        orderlistbut = findViewById(R.id.orderlistbut);
        settingsbut = findViewById(R.id.settingsbut);


        homebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.homebut:
                        startActivity(new Intent(OrderList.this,MainActivity.class));
                        break;

                }
            }
        });


        orderlistbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.orderlistbut:
                        startActivity(new Intent(OrderList.this,OrderList.class));
                        break;

                }
            }
        });

        settingsbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.settingsbut:
                        startActivity(new Intent(OrderList.this,ProfilePage.class));
                        break;

                }
            }
        });
    }

    private void initializeListView(){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arraybook);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("datapemesan").child(fullName);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arraybook.add(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                arraybook.remove(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listData.setAdapter(adapter);
    }
}