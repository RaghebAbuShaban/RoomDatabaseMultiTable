package com.example.roomdatabasemultitable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student student);
    @Update
    void updateStudent(Student student);
    @Delete
    void deleteStudent(Student student);
    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT * FROM Student WHERE courseId = :courseId")
    LiveData<Student> getStudentByCourseId(int courseId);
}
