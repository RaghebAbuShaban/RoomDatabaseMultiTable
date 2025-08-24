package com.example.roomdatabasemultitable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class Converter {
    @TypeConverter
    public Long toLong(Date date) {
        return date.getTime();
    }


    @TypeConverter
    public Date toDate(long date){
        return new Date(date);
    }


    @TypeConverter
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();

    }


    @TypeConverter
    public static Bitmap getByteAsBitMap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
