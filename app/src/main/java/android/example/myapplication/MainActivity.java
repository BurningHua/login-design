package android.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_register);
        setContentView(R.layout.layout_login);
        dbHelper = new MyDatabaseHelper(this, "UserInfo.db", null, 1);
        //setContentView(R.layout.activity_main);

        Button buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                SQLiteDatabase db = dbHelper.getWritableDatabase();// 查询Book表中所有的数据
                EditText emailedit = findViewById(R.id.email);
                String email = emailedit.getText().toString();
                EditText passwordedit = findViewById(R.id.password);
                String password = passwordedit.getText().toString();
                Cursor cursor = db.query("User", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {// 遍历Cursor对象，取出数据并打印
                        String emaildb = cursor.getString(cursor.getColumnIndex
                                ("email"));
                        String passworddb = cursor.getString(cursor.getColumnIndex
                                ("password"));
                        Log.i("MainActivity", "email is " + emaildb);
                        Log.i("MainActivity", "password is " + passworddb);
                        if(emaildb.equals(email)&&passworddb.equals(password)){
                            Log.i("MainActivity", "yes " );
                            flag = true;

                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();
                if(flag == false)
                    Toast.makeText(getApplicationContext(), "fail to login",Toast.LENGTH_LONG).show();
                else
                {
                    startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                    finish();
                }



            }
        });

        Button buttonToReg = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        buttonToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
            }
        });

    }






}
