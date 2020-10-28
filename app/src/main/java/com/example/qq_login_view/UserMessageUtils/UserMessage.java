package com.example.qq_login_view.UserMessageUtils;

public class UserMessage {
    private String username;  //用户账号
    private String password;   //密码
    private String sure_password;   //确认密码
    private String email;   //用户邮箱

    public UserMessage() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSure_password() {
        return sure_password;
    }

    public void setSure_password(String sure_password) {
        this.sure_password = sure_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
