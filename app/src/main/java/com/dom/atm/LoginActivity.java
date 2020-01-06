package com.dom.atm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edUserid = findViewById(R.id.userid);
        SharedPreferences setting=
                getSharedPreferences("atm",MODE_PRIVATE);
        edUserid.setText(setting.getString("PREF"," "));
    }
    public void login(View view){
        EditText edUserid = findViewById(R.id.userid);
        EditText edPassword = findViewById(R.id.password);
        String uid = edUserid.getText().toString();
        String pw = edPassword.getText().toString();
        if(uid.equals("jack")&&pw.equals("1234")){
            SharedPreferences setting=
                    getSharedPreferences("atm",MODE_PRIVATE);
            setting.edit()
                    .putString("PREF",uid)
                    .apply();
            Toast.makeText(this,"登入成功",Toast.LENGTH_LONG).show();
            getIntent().putExtra("LOGIN_USERID",uid);
            getIntent().putExtra("LOGIN_PASSWD",pw);
            setResult(RESULT_OK,getIntent());
            finish();
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("ATM")
                    .setMessage("登入失敗")
                    .setPositiveButton("OK",null)
                    .show();
        }
        }


    public void cancel(View view){


    }
}
