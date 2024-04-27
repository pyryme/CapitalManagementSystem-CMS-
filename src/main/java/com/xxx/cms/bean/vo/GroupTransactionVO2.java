package com.xxx.cms.bean.vo;

//这个事配合GroupTransactionVO的
public class GroupTransactionVO2 {
    private String userId;
    private String userName;
    private String isManager;
    private double balance;

    public GroupTransactionVO2(){

    }

    public GroupTransactionVO2(String userId, String userName, String isManager, double balance) {
        this.userId = userId;
        this.userName = userName;
        this.isManager = isManager;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsManager() {
        return isManager;
    }

    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "GroupTransactionVO2{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", isManager='" + isManager + '\'' +
                ", balance=" + balance +
                '}';
    }
}
