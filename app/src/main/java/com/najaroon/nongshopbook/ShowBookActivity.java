package com.najaroon.nongshopbook;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowBookActivity extends AppCompatActivity {

    //Explicit
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);

        listView = (ListView) findViewById(R.id.listView);

        SynProduct synProduct = new SynProduct();
        synProduct.execute();

    }   // Main Method

    private class SynProduct extends AsyncTask<Void, Void, String> {

        private String urlJSON = "http://swiftcodingthai.com/25JUN/get_book_master.php";

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("26JuneV1", "e doIn ==> " + e.toString());
                return null;
            }

        }   // doIn

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("26JuneV1", "JSON ==>>>>> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                String[] nameStrings = new String[jsonArray.length()];
                String[] priceStrings = new String[jsonArray.length()];
                String[] coverStrings = new String[jsonArray.length()];

                for (int i=0; i<jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    nameStrings[i] = jsonObject.getString("Name");
                    priceStrings[i] = jsonObject.getString("Price");
                    coverStrings[i] = jsonObject.getString("Cover");

                }   // for

                bookAdapter bookAdapter = new bookAdapter(ShowBookActivity.this, nameStrings, priceStrings, coverStrings);
                listView.setAdapter(bookAdapter);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }   //onPost

    }   // Class

}   // Main Class
