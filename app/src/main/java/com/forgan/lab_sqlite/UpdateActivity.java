package com.forgan.lab_sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    SQLiteDatabase sqliteMyDB;
    MyDbHelper myDataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        this.setTitle("Update data");
        Intent intent = getIntent();
        final String txt_get_title =
                intent.getStringExtra("title");
        myDataHelper = new MyDbHelper(this);
        sqliteMyDB = myDataHelper.getWritableDatabase();

        Cursor myDBCursor = sqliteMyDB.rawQuery("SELECT " + MyDbHelper.COL_NAME + "," + "" + MyDbHelper.COL_DESC + ", " + MyDbHelper.COL_COVER + " FROM " + MyDbHelper.TABLE_NAME + " WHERE title= '" + txt_get_title + "'", null);
        myDBCursor.moveToFirst();

        final EditText txtTitle_show = (EditText) findViewById(R.id.txt_title);
        final EditText txtDetail_show = (EditText) findViewById(R.id.txt_detail);
        txtTitle_show.setText("" + txt_get_title + "");
        txtDetail_show.setText(myDBCursor.getString(myDBCursor.getColumnIndex("description")));
        final Button updateBtn = (Button) findViewById(R.id.btnUpdate); updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(txt_get_title);
            }
        });
    }

    public boolean update(String dataTitle) {

        Intent intent = getIntent();
        final String txt_get_title = intent.getStringExtra("title");
        final EditText txtTitle_show = (EditText) findViewById(R.id.txt_title);
        final EditText txtDetail_show = (EditText) findViewById(R.id.txt_detail);

        myDataHelper = new MyDbHelper(this);
        sqliteMyDB = myDataHelper.getWritableDatabase();
        ContentValues Val = new ContentValues();
        Val.put("title", txtTitle_show.getText().toString());
        Val.put("description", txtDetail_show.getText().toString());
        long rows = sqliteMyDB.update(myDataHelper.TABLE_NAME, Val, "title=? ", new String[]{String.valueOf(dataTitle)});
        sqliteMyDB.close();

        Toast.makeText(UpdateActivity.this, "Success!", Toast.LENGTH_SHORT).show();
        Intent showPage = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(showPage);

        return false;
    }
}
