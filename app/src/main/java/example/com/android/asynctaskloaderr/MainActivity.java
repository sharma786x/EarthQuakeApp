package example.com.android.asynctaskloaderr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.*;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    static int i=0,j=0;
    static String x,mag,place;
    static Long time;
     ArrayList<Earthquake> earthquakeArrayList;
    ListView listView;
    static EarthquakeAdapter earthquakeAdapter;
    static Date date;
    static Earthquake earthquake;
    ArrayList<String> link=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.activity_main);

        earthquakeArrayList=new ArrayList<Earthquake>();

        earthquakeAdapter= new EarthquakeAdapter(getBaseContext(),earthquakeArrayList);

        SharedPreferences preferences=getSharedPreferences("pref file",Context.MODE_PRIVATE);

        Boolean temp=preferences.contains("minMag");

        

        getSupportLoaderManager().initLoader(0, null,this).forceLoad();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getBaseContext(),link.get(position).toString(),LENGTH_SHORT).show();
                String url = link.get(position).toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent=new Intent(this,Preferencex.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {

        return new FetchData(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        String places="";
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject baseJsonResponse = new JSONObject(data);
            JSONArray jsonArray = baseJsonResponse.getJSONArray("features");
            for(i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                JSONObject jsonObject1=jsonObject.getJSONObject("properties");
                mag= jsonObject1.getString("mag");
                link.add(jsonObject1.getString("url"));

                place=jsonObject1.getString("place");
                places=places+place;
                time=jsonObject1.getLong("time");
                date=new Date(time);
                //SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
                earthquake=new Earthquake(mag,place,date);
               earthquakeArrayList.add(earthquake);
            }



        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        finally {

            listView.setAdapter(earthquakeAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
    }

    private static class FetchData extends AsyncTaskLoader<String> {

        public FetchData(Context context) {
            super(context);
        }

        @Override
        public String loadInBackground() {
            HttpURLConnection conn=null;

            try {
                String link = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

                URL url = new URL(link);

                conn=(HttpURLConnection)url.openConnection();

                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String webPage = "",data="";

                while ((data = reader.readLine()) != null){
                    webPage += data + "\n";
                }
                x=webPage;




            }
            catch (Exception e)
            {
                Log.i("tag",""+"and");
            }
            finally {

            }
            return x;
        }

        @Override
        public void deliverResult(String data) {
            super.deliverResult(data);
        }
    }

}