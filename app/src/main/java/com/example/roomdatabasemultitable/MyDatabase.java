package com.example.roomdatabasemultitable;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static androidx.activity.OnBackPressedDispatcherKt.addCallback;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

    @Database(entities = {Student.class, Course.class}, version = 1, exportSchema = false)
    public abstract class MyDatabase extends RoomDatabase {

        public abstract StudentDao studentDao();
        public abstract CourseDao courseDao();

        private static volatile MyDatabase INSTANCE;

        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static MyDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (MyDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                        MyDatabase.class, "my_database")
                                .fallbackToDestructiveMigration()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

    }

