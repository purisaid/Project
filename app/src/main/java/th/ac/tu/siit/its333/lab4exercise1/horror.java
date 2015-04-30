package th.ac.tu.siit.its333.lab4exercise1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class horror extends ActionBarActivity {
    ArrayList<String> cat = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horror);
        LoadMessageTask l = new LoadMessageTask();
        l.execute();
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cat);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void buyClicked(View v) {

        RadioGroup rg = (RadioGroup)findViewById(R.id.rg);


        Intent result = new Intent();

        int rID = rg.getCheckedRadioButtonId();
        String grade = ((RadioButton)findViewById(rID)).getText().toString();
        int price = 100;
        result.putExtra("grade", grade);
        result.putExtra("price", price);

        this.setResult(RESULT_OK, result);
        this.finish();
    }
    class LoadMessageTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            BufferedReader reader;
            StringBuilder buffer = new StringBuilder();
            String line;

            try {
                URL u = new URL("http://ict.siit.tu.ac.th/~u5522780907/get_movie.php?category=2");
                HttpURLConnection h = (HttpURLConnection)u.openConnection();
                h.setRequestMethod("GET");
                h.setDoInput(true);
                h.connect();

                int response = h.getResponseCode();
                if (response == 200) {
                    reader = new BufferedReader(new InputStreamReader(h.getInputStream()));
                    while((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    Log.e("LoadMessageTask", buffer.toString());
                    //Parsing JSON and displaying messages

                    //To append a new message:
                    //Map<String, String> item = new HashMap<String, String>();
                    //item.put("user", u);
                    //item.put("message", m);
                    //data.add(0, item);
                    JSONObject json = new JSONObject(buffer.toString());
                    JSONArray movies = json.getJSONArray("movie");
                    for(int i=0;i<movies.length();i++) {
                        cat.add( movies.get(i).toString());
                        Log.e("GET movie name", movies.get(i).toString());
                    }
                }
            } catch (MalformedURLException e) {
                Log.e("LoadMessageTask", "Invalid URL");
            } catch (IOException e) {
                Log.e("LoadMessageTask", "I/O Exception");
            } catch (JSONException e) {
                Log.e("LoadMessageTask", "Invalid JSON");
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {

        }
    }
}