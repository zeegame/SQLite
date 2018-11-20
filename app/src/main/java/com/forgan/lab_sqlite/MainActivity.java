package com.forgan.lab_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listViewMovies = (ListView) findViewById(R.id.listView);
        MyDbHelper myDataHelper;
        myDataHelper = new MyDbHelper(this);
        SQLiteDatabase sqliteMyDB = myDataHelper.getWritableDatabase();
        Cursor myDBCursor = sqliteMyDB.rawQuery("SELECT " + MyDbHelper.COL_NAME + ", " + MyDbHelper.COL_DESC + ", " + MyDbHelper.COL_COVER + " FROM " + MyDbHelper.TABLE_NAME, null);

        ArrayList<String> dirArray = new ArrayList<String>();
        myDBCursor.moveToFirst();

        while (!myDBCursor.isAfterLast()) {
            dirArray.add(myDBCursor.getString(myDBCursor.getColumnIndex (MyDbHelper.COL_NAME)));
            myDBCursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, dirArray); listViewMovies.setAdapter(adapterDir);

        final Button sendButton = (Button) findViewById(R.id.addActivity);
        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
        public void onClick(View v)
        { Intent showPage = new Intent(MainActivity.this, AddActivity.class);
            startActivity(showPage);
        }
        });
        listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override public void onItemClick (AdapterView < ? > parent, View view,int position, long id)
            {
                int itemPosition = position;
                String itemValue = (String) listViewMovies.getItemAtPosition(position);
                Intent showPage = new Intent(MainActivity.this, Delelteacticity.class);
                showPage.putExtra("title", "" + itemValue + "");
                startActivity(showPage);
            }
        });
    }

}
