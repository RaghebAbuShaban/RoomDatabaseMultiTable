package com.example.roomdatabasemultitable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    Button btnAddCourse, btnAddStudent;
    RecyclerView rvCourses, rvStudents;
    CourseAdapter courseAdapter;
    StudentAdapter studentAdapter;
    MyViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnAddCourse = findViewById(R.id.btn_addCourse);
        btnAddStudent = findViewById(R.id.btn_addStudent);
        rvCourses = findViewById(R.id.rvCourses);
        rvStudents = findViewById(R.id.rvStudents);





        courseAdapter = new CourseAdapter(new ArrayList<>());
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(courseAdapter);



        studentAdapter = new StudentAdapter(new ArrayList<>());
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        rvStudents.setAdapter(studentAdapter);



        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddCourse.class);
                startActivity(intent);

            }
        });


        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddStudent.class);
                startActivity(intent);

            }
        });



        // إعداد ViewModel
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // مراقبة الكورسات وعرضها تلقائيًا في RecyclerView
        viewModel.getAllCourses().observe(this, courses -> {
            courseAdapter.setCourses(courses);
        });


        viewModel.getAllStudents().observe(this, students -> {
            studentAdapter.setStudents(students);
        });

    }
}