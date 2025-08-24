package com.example.roomdatabasemultitable;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

public class AddCourse extends AppCompatActivity {

    private MyViewModel viewModel;
    private EditText etCourseName, etNoOfStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        etCourseName = findViewById(R.id.etCourseName);
        etNoOfStudents = findViewById(R.id.etNoOfStudents);
        Button btnSave = findViewById(R.id.btnSaveCourse);

        btnSave.setOnClickListener(v -> {
            String name = etCourseName.getText().toString();
            String noStr = etNoOfStudents.getText().toString();

            if (name.isEmpty() || noStr.isEmpty()) {
                Toast.makeText(this, "ادخل البيانات", Toast.LENGTH_SHORT).show();
                return;
            }

            int count = Integer.parseInt(noStr);
            Course course = new Course(name, count);

            viewModel.insertCourse(course);

            Toast.makeText(this, "تم الحفظ", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}