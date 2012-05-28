package org.sdu.db;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class PhotoDBActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		DBHelper dbh=new DBHelper(this);
        SQLiteDatabase db=dbh.getWritableDatabase();
        db.close();
    }
}