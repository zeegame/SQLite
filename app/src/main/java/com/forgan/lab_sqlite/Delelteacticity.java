package com.forgan.lab_sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Delelteacticity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        this.setTitle("View and Delete data");
        final TextView txtTitle_show = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        final String txt_get_title = intent.getStringExtra("title");
        txtTitle_show.setText(txt_get_title);

        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DeleteRow(txt_get_title);
                Intent showPage = new Intent(Delelteacticity.this, MainActivity.class);
                startActivity(showPage);
            }
        });

        Button backtohome = (Button) findViewById(R.id.backBtn);
        backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showPage = new Intent(Delelteacticity.this, MainActivity.class);
                startActivity(showPage);
            }
        });

        Button updateData = (Button) findViewById(R.id.up);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showPage = new Intent(Delelteacticity.this, UpdateActivity.class);
                showPage.putExtra("title", "" + txt_get_title + "");
                startActivity(showPage);
            }
        });


    }

    MyDbHelper myDataHelper;
    SQLiteDatabase sqliteMyDB;

    public long DeleteRow(String dataTitle) {
        try {
            myDataHelper = new MyDbHelper(this);
            ContentValues Val = new ContentValues();
            sqliteMyDB = myDataHelper.getWritableDatabase();
            long rows = sqliteMyDB.delete(myDataHelper.TABLE_NAME, "title=?", new String[]{String.valueOf(dataTitle)});
            sqliteMyDB.close();
            Toast.makeText(Delelteacticity.this, "Success!", Toast.LENGTH_SHORT).show();
            return rows;
        } catch (Exception e) {
            return -1;
        }
    }

}