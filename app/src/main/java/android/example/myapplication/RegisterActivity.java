package android.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        dbHelper = new MyDatabaseHelper(this, "UserInfo.db", null, 1);


        Button buttonReg = (Button) findViewById(R.id.btnRegister);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                EditText nameedit = findViewById(R.id.name);
                String name = nameedit.getText().toString();
                EditText passwordedit = findViewById(R.id.password);
                String password = passwordedit.getText().toString();
                EditText emailedit = findViewById(R.id.email);
                String email = emailedit.getText().toString();
                ContentValues values = new ContentValues();// 开始组装第一条数据
                values.put("name", name);
                values.put("password", password);
                values.put("email", email);
                Log.i("TAG", values.toString());
                //getPreferences(MODE_PRIVATE).edit().putString();
                db.insert("User", null, values); // 插入第一条数据
                //db.execSQL("insert into User values('"+name+"','"+password+"','"+email+"')");
                Toast.makeText(getApplicationContext(), "succeed",Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        Button buttonToLog = (Button) findViewById(R.id.btnLinkToLoginScreen);
        buttonToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
