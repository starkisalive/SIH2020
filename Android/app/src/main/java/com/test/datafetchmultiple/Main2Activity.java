package com.test.datafetchmultiple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private ImageButton ibPick;
    private ImageView civProfile;
    private Button btnConfirm;
    private ProgressDialog progressDialog;
    private TextView loc1,loc2;
    private EditText near, sugg;
    Spinner spinner;
    String lod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        loc1 = findViewById(R.id.lt);
        loc2 = findViewById(R.id.lg);
        ibPick = findViewById(R.id.btn_pick);
        civProfile = findViewById(R.id.profile_image);
        spinner = findViewById(R.id.spin);
        btnConfirm = findViewById(R.id.btn_confirm);
        near = findViewById(R.id.near);
        sugg = findViewById(R.id.sugg);
        progressDialog = new ProgressDialog(Main2Activity.this);
        progressDialog.setMessage("Uploading Image, Please Wait");

        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


        //double lat = getIntent().getDoubleExtra("lon",1);


        spinner.setOnItemSelectedListener(this);

        ibPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(Main2Activity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Main2Activity.this);




                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                                if(response.isPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                                    builder.setTitle("Permission Required")
                                            .setMessage("Permission Need To Access Camera")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    intent.setData(Uri.fromParts("package", getPackageName(), null));
                                                    startActivityForResult(intent, 51);

                                                }
                                            })
                                            .setNegativeButton("Cancel", null)
                                            .show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                                token.continuePermissionRequest();

                            }
                        }).check();

            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                final Uri resultUri = result.getUri();
                final double latitude = getIntent().getDoubleExtra("lati",1);
                final double longitude = getIntent().getDoubleExtra("long",1);
                final String user_name = getIntent().getStringExtra("user_name");
                civProfile.setImageURI(resultUri);
                btnConfirm.setVisibility(View.VISIBLE);
                loc1.setText(String.valueOf(latitude));
                loc2.setText(String.valueOf(longitude));
                loc1.setVisibility(View.VISIBLE);
                loc2.setVisibility(View.VISIBLE);
                Toast.makeText(this, lod, Toast.LENGTH_SHORT).show();
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        File imageFile = new File(resultUri.getPath());
                        progressDialog.show();
                        String userName = getIntent().getStringExtra("user_name");
                        AndroidNetworking.upload("http://vektor.soumit.tech/uploaddata.php")
                                .addMultipartFile("image", imageFile)
                                .addMultipartParameter("userId", String.valueOf(user_name))
                                .addMultipartParameter("lat",String.valueOf(latitude))
                                .addMultipartParameter("lon",String.valueOf(longitude))
                                .addMultipartParameter("dist",String.valueOf(lod))
                                .addMultipartParameter("near",String.valueOf(near.getText()))
                                .addMultipartParameter("sugg",String.valueOf(sugg.getText()))
                                .setPriority(Priority.HIGH)
                                .build()
                                .setUploadProgressListener(new UploadProgressListener() {
                                    @Override
                                    public void onProgress(long bytesUploaded, long totalBytes) {

                                        float progress = (float) bytesUploaded / totalBytes * 100;
                                        progressDialog.setProgress((int) progress);

                                    }
                                })
                                .getAsString(new StringRequestListener() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            progressDialog.dismiss();
                                            JSONObject jsonObject = new JSONObject(response);
                                            int status = jsonObject.getInt("status");
                                            String message = jsonObject.getString("message");
                                            if(status == 0){
                                                Toast.makeText(Main2Activity.this, "Unable To Upload Image" + message, Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(Main2Activity.this, message, Toast.LENGTH_SHORT).show();
                                                near.setText("");
                                                sugg.setText("");
                                                Intent intent = new Intent(Main2Activity.this,NavActivity.class);
                                                intent.putExtra("user_name",user_name);
                                                startActivity(intent);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(Main2Activity.this, "Parsing Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        progressDialog.dismiss();
                                        anError.printStackTrace();
                                        Toast.makeText(Main2Activity.this, "Error uploading Image", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        lod = parent.getSelectedItem().toString();
//        Toast.makeText(this, lod, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
