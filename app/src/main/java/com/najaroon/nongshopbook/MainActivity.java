package com.najaroon.nongshopbook;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private static final String urlJSON = "http://swiftcodingthai.com/25JUN/get_user_nong.php";
    private boolean statusABoolean = true;
    private String truePasswordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);

    }   // Main Method

    //  Inner Class
    private class SynUserTable extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("26June", "e ==> " + e.toString());
                return null;
            }


        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("26June", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (userString.equals(jsonObject.getString("User"))) {

                        statusABoolean = false;
                        truePasswordString = jsonObject.getString("Password");

                    }   // If

                }   // for


                if (statusABoolean) {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(MainActivity.this, "ไม่มี User นี้", "ไม่มี" + userString + " ในฐานข้อมูลของเรา");
                } else if (passwordString.equals(truePasswordString)) {

                    Intent intent = new Intent(MainActivity.this, ShowBookActivity.class);
                    startActivity(intent);

                } else {

                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(MainActivity.this, "Password fail",
                            "Please try again");

                }

            } catch (Exception e) {
                Log.d("26June", "e onPost ==> " + e.toString());
            }

        }   // onPost


    }   //  Class


    public void clickSignIn(View view) {
        userString = userEditText.getText().toString();
        passwordString = passwordEditText.getText().toString();

        if (userString.equals("") || passwordString.equals("")) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่องด้วย");

        } else {

            SynUserTable synUserTable = new SynUserTable();
            synUserTable.execute();

        }

    }   //  Click SignIn

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   // Main Class
