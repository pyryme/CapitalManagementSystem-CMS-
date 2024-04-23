package com.xxx.cms.bean.vo;

public class UserUpdatePhoneUsernameVO {

    private String Newusername;
    private String phoneNumber;
    private String Oldusername;

    public UserUpdatePhoneUsernameVO(){//这种空的用来实例化的，好像有申请空间的作用
    }

    public UserUpdatePhoneUsernameVO(String Oldusername,String Newusername,String phoneNumber) {
        this.Oldusername = Oldusername;
        this.Newusername = Newusername;
        this.phoneNumber = phoneNumber;
    }

    public String getUserUpdatePhone() {
        return phoneNumber;
    }

    public void setUserUpdatePhone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getUserUpdateNewUsername() {
        return Newusername;
    }

    public void setUserUpdateNewUsername(String Newusername) {
        this.Newusername = Newusername;
    }

    public String getUserUpdateOldUsername() {
        return Oldusername;
    }

    public void setUserUpdateOldUsername(String Oldusername) {
        this.Oldusername = Oldusername;
    }


    @Override
    public String toString() {
        return "UserUpdatePhoneUsernameVO{" +
                ", Newusername='" + Newusername + '\'' +
                ", Oldusername='" + Oldusername + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
