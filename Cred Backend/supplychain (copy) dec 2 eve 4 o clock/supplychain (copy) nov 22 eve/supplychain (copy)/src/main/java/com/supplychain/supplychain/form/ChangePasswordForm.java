package com.supplychain.supplychain.form;

public class ChangePasswordForm {
    
private String oldpassword;
private String confirmpassword;
public String getConfirmpassword() {
    return confirmpassword;
}
public void setConfirmpassword(String confirmpassword) {
    this.confirmpassword = confirmpassword;
}
private String newpassword;

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

}
