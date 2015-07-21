package com.arvinlp.ir.backup;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Arvin Loripour on 7/20/2015.
 * www.Abaan.ir
 * Copyright 2015 By Arvinlp.ir.
 */
public class Backup extends Activity {
    String DATABASE_NAME = "db_name";
    String PACKAGE_NAME  = "com.arvinlp.ir.backupdb";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exportDB();
    }

/**
 * تابع که برای خروجی گرفتن از دیتابیس استفاده می گردد
 **/
 
    private void exportDB(){
        //محل فایل خروجی
        File sd = Environment.getExternalStorageDirectory();
        //محل پوشه دیتا در اندروید
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        //محل فایل دیتابیس
        String currentDBPath = "/data/"+ PACKAGE_NAME +"/databases/"+DATABASE_NAME;
        // محل جدید فایل دیتابیس ما (در ریشه مموری کارت می باشد).
        String backupDBPath = DATABASE_NAME+".sql";
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(this, "DB Exported!", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
