package com.example.roomdatabasemultitable;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddStudent extends AppCompatActivity {

    MyViewModel viewModel;
    EditText etName, etDept, etDOB, etCourseId;
    ImageView ivPhoto;
    Button btnTakePhoto, btnSave;

    Bitmap selectedPhoto;
    Date selectedDate;

    ActivityResultLauncher<Intent> cameraLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = findViewById(R.id.etStudentName);
        etDept = findViewById(R.id.etStudentDept);
        etCourseId = findViewById(R.id.etCourseId);
        ivPhoto = findViewById(R.id.ivStudentPhoto);
        btnTakePhoto = findViewById(R.id.btnTakePhoto);
        etDOB = findViewById(R.id.etStudentDOB);
        btnSave = findViewById(R.id.btnSaveStudent);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // تفعيل الكاميرا
        ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            selectedPhoto = (Bitmap) result.getData().getExtras().get("data");
                            ivPhoto.setImageBitmap(selectedPhoto);
                        }
                    }
                }
        );

        btnTakePhoto.setOnClickListener(v -> {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(intent);
        });

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String dept = etDept.getText().toString().trim();
            int courseId = Integer.parseInt(etCourseId.getText().toString().trim());

            Date dob = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dob = sdf.parse(etDOB.getText().toString().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Student student = new Student(name, dept, selectedPhoto, dob, courseId);

            viewModel.insertStudent(student);

            Toast.makeText(this, "Student saved", Toast.LENGTH_SHORT).show();
            finish();
        });



    }
}
