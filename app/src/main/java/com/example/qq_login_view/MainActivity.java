package com.example.qq_login_view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.qq_login_view.DataBase.DataBaseHelper;
import com.example.qq_login_view.UserMessageUtils.UserMessage;

public class MainActivity extends AppCompatActivity {
    EditText editText_name;
    EditText editText_password;
    EditText editText_sure_password;
    EditText editText_email;
    Button button_login;
    Button button_register;
    Button button_delete;
    Button button_clear;
    public String name;
    public String password;
    public String sure_password;
    public String email;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取编辑框数据
                name = editText_name.getText().toString().trim();
                password = editText_password.getText().toString().trim();
                sure_password = editText_sure_password.getText().toString().trim();
                email = editText_email.getText().toString().trim();
                UserMessage user = new UserMessage();
                user.setUsername(name);
                user.setPassword(password);
                user.setSure_password(sure_password);
                user.setEmail(email);
                DataBaseHelper dataBaseHelper = new DataBaseHelper();
                dataBaseHelper.DbInsert(user, MainActivity.this);
            }
        });//button_register
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取编辑框数据
                name = editText_name.getText().toString().trim();
                password = editText_password.getText().toString().trim();
                sure_password = editText_sure_password.getText().toString().trim();
                email = editText_email.getText().toString().trim();
                boolean flag;
                UserMessage user = new UserMessage();
                user.setUsername(name);
                user.setPassword(password);
                user.setSure_password(sure_password);
                user.setEmail(email);
                DataBaseHelper dataBaseHelper = new DataBaseHelper();
                flag = dataBaseHelper.DbSelect(user, MainActivity.this);
                if (flag) {
                    Intent intent = new Intent(MainActivity.this, LoginSuccessfulActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "请检查密码或者账号是否输入错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });//button_login
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper();
                dataBaseHelper.DbClear(MainActivity.this);
            }
        });//button_clear
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText_name.getText().toString().trim();
                UserMessage user = new UserMessage();
                user.setUsername(name);
                DataBaseHelper dataBaseHelper = new DataBaseHelper();
                dataBaseHelper.DbDelete(user, MainActivity.this);
            }
        });//button_delete
    }

    //初始化组件
    public void initView() {
        editText_name = findViewById(R.id.username);
        editText_password = findViewById(R.id.password);
        editText_sure_password = findViewById(R.id.sure_password);
        editText_email = findViewById(R.id.email);
        button_login = findViewById(R.id.button_login);  //登录按钮
        button_register = findViewById(R.id.button_register);   //注册按钮
        button_delete = findViewById(R.id.button_delete);   //删除按钮
        button_clear = findViewById(R.id.button_clear);     //清除按钮
    }
}