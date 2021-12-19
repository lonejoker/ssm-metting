package com.xiaobai.entity;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:33
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName UsernamePassWord
 */
public class UsernamePassWord {
    private String username;
    private String password;
    private String oldpassword;
    private String newpassword;
    private String confirmpassword;

    public UsernamePassWord() {
    }

    public UsernamePassWord(String username, String password, String oldpassword, String newpassword, String confirmpassword) {
        this.username = username;
        this.password = password;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.confirmpassword = confirmpassword;
    }

    @Override
    public String toString() {
        return "UsernamePassWord{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", oldpassword='" + oldpassword + '\'' +
                ", newpassword='" + newpassword + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                '}';
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

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}