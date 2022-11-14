package com.example.myapplication.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakePictureFromCameraIntentActivity extends AppCompatActivity {
    private static final String TAG = TakePictureFromCameraIntentActivity.class.getSimpleName();
    private static final int REQUEST_CAMERA = 1;

    @BindView(R.id.imv_image)
    ImageView imvImage;

    private Uri capturedImageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture_from_camera_intent);

        configView();
    }

    private void configView() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_take_picture)
    void onActionTakePicture() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Take Image");
        capturedImageURI = getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intentToCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentToCamera.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageURI);
        startActivityForResult(intentToCamera, REQUEST_CAMERA);
    }

    private String getRealPathFromURI(Uri contentUri) {
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = Objects.requireNonNull(cursor).getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return contentUri.getPath();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CAMERA) {
                String imagePath = getRealPathFromURI(capturedImageURI);
                if(!TextUtils.isEmpty(imagePath)) {
                    File imageFile = new File(imagePath);
                    if(imageFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                        imvImage.setImageBitmap(myBitmap);
                    }
                }
            }
        }
    }
}