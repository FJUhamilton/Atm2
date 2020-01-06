package com.dom.atm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int RC_LOGIN = 1;
    boolean login = false;
    String[] func ={"餘額查詢","交易明細","最新消息","投資理財","離開"};
    TextView sure;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_LOGIN){
            if(resultCode == RESULT_OK) {
                String uid = data.getStringExtra("LOGIN_USERID");
                String pw = data.getStringExtra("LOGIN_PASSWD");
                Log.d("RESULT", uid + "/" + pw);
            }else {
                finish();
            }
        }
    }//防止返回鍵

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sure = findViewById(R.id.sure);
        if(!login){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivityForResult(intent,RC_LOGIN);
        }
        ListView list = findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,func);
        list.setAdapter(adapter);
        Spinner notify = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> nAdapter =
        ArrayAdapter.createFromResource(this,R.array.notify_array,android.R.layout.simple_spinner_item);
        nAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_setting){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
