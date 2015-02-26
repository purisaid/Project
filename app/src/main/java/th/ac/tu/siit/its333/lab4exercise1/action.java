package th.ac.tu.siit.its333.lab4exercise1;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class action extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action);
    }

    public void buyClicked(View v) {

        RadioGroup rg = (RadioGroup)findViewById(R.id.rg);


        Intent result = new Intent();

        int rID = rg.getCheckedRadioButtonId();
        String grade = ((RadioButton)findViewById(rID)).getText().toString();
        result.putExtra("grade", grade);
        this.setResult(RESULT_OK, result);
        this.finish();
    }

}
