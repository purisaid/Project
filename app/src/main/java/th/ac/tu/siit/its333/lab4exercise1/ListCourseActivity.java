package th.ac.tu.siit.its333.lab4exercise1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class ListCourseActivity extends ActionBarActivity {
    CourseDBHelper helper;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catagories);

    }
    public void buttonClicked(View v) {
        int id = v.getId();
        Intent i;

        switch (id) {
            case R.id.action:
                i = new Intent(this, action.class);
                startActivityForResult(i, 88);
                break;

            case R.id.history:
                i = new Intent(this, history.class);
                startActivityForResult(i, 88);
                break;

            case R.id.comedy:
                i = new Intent(this, comady.class);
                startActivityForResult(i, 88);
                break;

            case R.id.horror:
                i = new Intent(this, horror.class);
                startActivityForResult(i, 88);
                break;


        }



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 88) {
            if (resultCode == RESULT_OK) {

                String grade = data.getStringExtra("grade");
                helper = new CourseDBHelper(this.getApplicationContext());
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues r = new ContentValues();

                r.put("grade", grade);
                r.put("value", 0.0);
                long newId = db.insert("course", null, r);

                if (newId != -1) {
                    Toast t = Toast.makeText(this.getApplicationContext(),
                            "Successfully added",
                            Toast.LENGTH_SHORT);
                    t.show();
                    Intent result = new Intent();
                    this.setResult(RESULT_OK, result);
                    this.finish();
                }
                else {
                    Toast t = Toast.makeText(this.getApplicationContext(),
                            "Unable to add",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        }

        Log.d("course", "onActivityResult");
    }

}



