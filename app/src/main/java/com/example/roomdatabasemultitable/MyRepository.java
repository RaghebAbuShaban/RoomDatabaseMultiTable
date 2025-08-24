package com.example.roomdatabasemultitable;

import android.app.Application;

import androidx.lifecycle.LiveData;


import java.util.List;

public class MyRepository {

    private StudentDao studentDao;
    private CourseDao courseDao;

    public MyRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        studentDao = db.studentDao();
        courseDao = db.courseDao();
    }


    void insertCourse(Course course){
        MyDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.insertCourse(course);
        });
    }

    void updateCourse(Course course){
        MyDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.updateCourse(course);
        });
    }

    void deleteCourse(Course course){
        MyDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.deleteCourse(course);
        });
    }

    LiveData<List<Course>> getAllCourses(){

        return courseDao.getAllCourses();

    }

    LiveData<Course> getCourseById(int id){

        return courseDao.getCourseById(id);
    }


    void insertStudent(Student student){
        MyDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.insertStudent(student);
        });
    }

    void updateStudent(Student student){
        MyDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.updateStudent(student);
        });
    }
    void deleteStudent(Student student){
        MyDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.deleteStudent(student);
        });
    }
    LiveData<List<Student>> getAllStudents(){

        return studentDao.getAllStudents();
    }

    LiveData<Student> getStudentByCourseId(int courseId){

        return studentDao.getStudentByCourseId(courseId);
    }


    
    
    
}
