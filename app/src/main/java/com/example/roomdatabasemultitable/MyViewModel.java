package com.example.roomdatabasemultitable;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    MyRepository repository;
    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new MyRepository(application);
    }

    void insertCourse(Course course){
        repository.insertCourse(course);
    }

    void updateCourse(Course course){
        repository.updateCourse(course);
    }

    void deleteCourse(Course course){
        repository.deleteCourse(course);
    }

    LiveData<List<Course>> getAllCourses(){
        return repository.getAllCourses();
    }

    LiveData<Course> getCourseById(int id){
        return repository.getCourseById(id);
    }


    void insertStudent(Student student){
        repository.insertStudent(student);
    }
    void updateStudent(Student student){
        repository.updateStudent(student);
    }
    void deleteStudent(Student student){
        repository.deleteStudent(student);
    }
    LiveData<List<Student>> getAllStudents(){
        return repository.getAllStudents();
    }

    LiveData<Student> getStudentByCourseId(int courseId){
        return repository.getStudentByCourseId(courseId);
    }

    }


