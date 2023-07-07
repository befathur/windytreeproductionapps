package umn.ac.id.windytreeproduction;

import androidx.appcompat.app.AppCompatActivity;
import umn.ac.id.windytreeproduction.model.DataPemesan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderPage extends AppCompatActivity {
    EditText reqlokasi, reqwaktu, reqfullName, reqnumber, reqevent, reqselectpv, reqselecttemp;
    private Button btnNext;
    DatabaseReference inputdataD;
    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_page);

        btnNext = findViewById(R.id.next);

        reqlokasi = findViewById(R.id.lokasi);
        reqwaktu = findViewById(R.id.waktu);
        reqfullName = findViewById(R.id.fullName);
        reqnumber = findViewById(R.id.number);
        reqevent = findViewById(R.id.event);
        reqselectpv = findViewById(R.id.selectpv);
        reqselecttemp = findViewById(R.id.selecttp);

        inputdataD = FirebaseDatabase.getInstance().getReference().child("datapemesan");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputdatapemesan();
                //startActivity(new Intent(OrderPage.this, OrderVer.class));
                Intent intent = new Intent(OrderPage.this, OrderVer.class);
                intent.putExtra("fullname", fullName);
                startActivity(intent);
                finish();
            }


        });
    }

    private void inputdatapemesan() {
        String lokasi = reqlokasi.getText().toString();
        String waktu = reqwaktu.getText().toString();
        fullName = reqfullName.getText().toString();
        String number = reqnumber.getText().toString();
        String event = reqevent.getText().toString();
        String jasa = reqselectpv.getText().toString();
        String template = reqselecttemp.getText().toString();

        DataPemesan dataPemesan = new DataPemesan(lokasi, waktu, fullName, number, event, jasa, template);

        FirebaseDatabase.getInstance().getReference("datapemesan").child(fullName).setValue(dataPemesan);
        Toast.makeText(OrderPage.this,"Order Anda Sedang Diproses, Mohon tunggu hingga team kami menghubungi anda",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(OrderPage.this, OrderVer.class));
        finish();

    }


}