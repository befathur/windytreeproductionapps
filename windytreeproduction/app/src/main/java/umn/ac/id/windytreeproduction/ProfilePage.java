package umn.ac.id.windytreeproduction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import static android.content.ContentValues.TAG;

public class ProfilePage extends AppCompatActivity {
    private ImageView homebut, orderlistbut, settingsbut;
    private Button logout;
    TextView welcome, mEmail;
    CircleImageView fotogambar;
    FirebaseAuth mAuth;
    FirebaseUser user;
    int ImageVar = 10001;
    private static final String TAG="ProfileActivity";
    private Uri uri;

    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }

        fotogambar = findViewById(R.id.fotogambar);
        homebut = findViewById(R.id.homebut);
        welcome = findViewById(R.id.welcome);
        orderlistbut = findViewById(R.id.orderlistbut);
        settingsbut = findViewById(R.id.settingsbut);
        logout = (Button) findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfilePage.this, SignActivity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user!=null){
            String email = mAuth.getCurrentUser().getEmail();
            welcome.setText("Welcome, "+email);

            if (user .getPhotoUrl() != null ){
                Glide.with(this)
                        .load(user.getPhotoUrl())
                        .circleCrop()
                        .into(fotogambar);
            }
        }

        homebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.homebut:
                        startActivity(new Intent(ProfilePage.this,MainActivity.class));
                        break;

                }
            }
        });

        orderlistbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.orderlistbut:
                        startActivity(new Intent(ProfilePage.this,OrderList.class));
                        break;

                }
            }
        });

        settingsbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.settingsbut:
                        startActivity(new Intent(ProfilePage.this,ProfilePage.class));
                        break;

                }
            }
        });
    }

    public void aboutus(View view) {
        Intent aboutusIntent = new Intent(ProfilePage.this,aboutUs.class);
        startActivity(aboutusIntent);
        finish();
    }

    public void logout(View view) {
        Intent logoutIntent = new Intent(ProfilePage.this,SignActivity.class);
        startActivity(logoutIntent);
        finish();
    }

    public void handleImageClick(View view) {
        Intent motointent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (motointent.resolveActivity(getPackageManager()) != null) {
            startActivityIfNeeded(motointent, ImageVar);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageVar){
            switch (resultCode){
                case RESULT_OK:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    fotogambar.setImageBitmap(bitmap);
                    handleUpload(bitmap);
            }
        }
    }

    private void handleUpload(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        StorageReference reference = FirebaseStorage.getInstance().getReference()
                .child("profpic")
                .child(uid + ".jpeg");

        reference.putBytes(baos.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        getdwldUrl(reference);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ",e.getCause() );
                    }
                });
    }
    private void getdwldUrl(StorageReference reference){
        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d(TAG, "onSuccess: " + uri);
                        setUserProfileUrl(uri);
                    }
                });
    }

    private void setUserProfileUrl(Uri uri){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfilePage.this, "Image Change!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfilePage.this, "Image Profile Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}