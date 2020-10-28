package com.example.qq_login_view.DataBase;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.qq_login_view.UserMessageUtils.UserMessage;

public class DataBaseHelper {
    private static final String Table_Name = "QQUserMessage";  //表名
    private static final String User_name = "name";    //用户名
    private static final String User_password = "password";     //密码
    private static final String User_Sure_password = "sure_password";  //确认密码
    private static final String User_email = "email";   //邮箱
    public MyDbHelper myDbHelper;   //数据库辅助类实例
    public SQLiteDatabase sqLiteDatabase;    //数据库实例
    public ContentValues contentValues;   //数据库的操作参数
    //获取MainActivity传来的参数
    private String username_save;
    private String password_save;
    private String sure_password_save;
    private String email_save;
    //获取数据库中的数据
    private String SQL_name;
    private String SQL_password;
    private String SQL_sure_password;
    private String SQL_email;

    //插入操作
    public void DbInsert(UserMessage user, Context context) {
        myDbHelper = new MyDbHelper(context, "QQUserMessage.db", null, 1);
        sqLiteDatabase = myDbHelper.getWritableDatabase();
        contentValues = new ContentValues();
        username_save = user.getUsername();   //获取用户名
        password_save = user.getPassword();   //获取密码
        sure_password_save = user.getSure_password();  //获取确认密码
        email_save = user.getEmail();   //获取邮箱
        if (TextUtils.isEmpty(username_save)) {
            ShowMsg("QQ号为空，请填写QQ号！", context);
        } else if (TextUtils.isEmpty(password_save)) {
            ShowMsg("密码为空，请填写密码！", context);
        } else if (TextUtils.isEmpty(email_save)) {
            ShowMsg("邮箱为空，请填写邮箱！", context);
        } else {
            contentValues.put("name", username_save);
            contentValues.put("password", password_save);
            contentValues.put("sure_password", sure_password_save);
            contentValues.put("email", email_save);
            sqLiteDatabase.insert(Table_Name, null, contentValues);
            ShowMsg("成功注册一个QQ号！", context);
        }
    }

    //选择查询并判断是否成功
    public boolean DbSelect(UserMessage userMessage, Context context) {
        myDbHelper = new MyDbHelper(context, "QQUserMessage.db", null, 1);
        sqLiteDatabase = myDbHelper.getReadableDatabase();
        username_save = userMessage.getUsername();   //获取用户名
        password_save = userMessage.getPassword();   //获取密码
        sure_password_save = userMessage.getSure_password();  //获取确认密码
        email_save = userMessage.getEmail();   //获取邮箱
        Cursor cursor = sqLiteDatabase.query(Table_Name, null, null, null, null, null,
                null, null);
        if (cursor.getCount() == 0) {
            ShowMsg("数据为空，请注册数据！" + cursor.getCount(), context);
            return false;
        }//if
        else {
            while (cursor.moveToNext()) {
                SQL_name = cursor.getString(0);   //获取数据库中的用户名
                SQL_password = cursor.getString(1);   //获取数据库中的密码
                SQL_sure_password = cursor.getString(2);   //获取数据库中的确认密码
                SQL_email = cursor.getString(3);    //获取数据库中的邮箱
                if (SQL_name.equals(username_save) && SQL_password.equals(password_save) && SQL_sure_password.equals(sure_password_save)) {
                    return true;
                }//if
            }//while

            return false;
        }//else
    }

    //清除
    public void DbClear(Context context) {
        myDbHelper = new MyDbHelper(context, Table_Name, null, 1);
        sqLiteDatabase = myDbHelper.getWritableDatabase();
        sqLiteDatabase.delete(Table_Name, null, null);
        sqLiteDatabase.close();
        ShowMsg("清除所有数据！", context);
    }

    //删除数据
    public void DbDelete(UserMessage userMessage, Context context) {
        username_save = userMessage.getUsername().trim();
        myDbHelper = new MyDbHelper(context, Table_Name, null, 1);
        sqLiteDatabase = myDbHelper.getWritableDatabase();
        sqLiteDatabase.delete(Table_Name, "User_name = ?", new String[]{username_save});
        sqLiteDatabase.close();
        ShowMsg("删除了账号为：" + username_save + "用户的信息！", context);
    }

    //通用信息提示框
    private void ShowMsg(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    //数据库操作辅助类
    public static class MyDbHelper extends SQLiteOpenHelper {


        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + Table_Name + "(" + User_name + " varchar(15)" + "," + User_password + " varchar(20)" + "," + User_Sure_password + " varchar(20)" + "," + User_email + " varchar(25))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
