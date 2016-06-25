package com.najaroon.nongshopbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    //  Explicit
    private EditText userEditText, passwordEditText,
            nameEditText, addressEditText;
    private String userString, passwordString,
            nameString, addressString;


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



    }

}   // Main Class
