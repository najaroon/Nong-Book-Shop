package com.najaroon.nongshopbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //  Explicit
    private EditText userEditText, passwordEditText,
            nameEditText, addressEditText;
    private String userString, passwordString,
            nameString, addressString;
    private static final String urlPHP = "http://swiftcodingthai.com/25JUN/add_user_nong.php";
    private static final String moneySTRING = "500";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //  Bind Widget
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
        nameEditText = (EditText) findViewById(R.id.editText3);
        addressEditText = (EditText) findViewById(R.id.editText4);


    }   // Main Method

    public void clickSignUpSign(View view) {

        //  Get Value From Text
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        addressString = addressEditText.getText().toString().trim();

        //  Check Space
        if (userString.equals("") || passwordString.equals("") ||
                nameString.equals("") || addressString.equals("")) {
            //  Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่อง ครับ");

        } else {
            //  No Space
            uploadUserToServer();

        }

    }   //  Click Sign

    private void uploadUserToServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("User", userString)
                .add("Password", passwordString)
                .add("Name", nameString)
                .add("Address", addressString)
                .add("Money", moneySTRING)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override   // ถ้าโยนค่าไม่สำเร็จ
            public void onFailure(Request request, IOException e) {

            }

            @Override  // ถ้าโยนค่าสำเร็จ
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });

    }

}   // Main Class
