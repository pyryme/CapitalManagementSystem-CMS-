package com.xxx.cms.bean.vo;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class GroupTransactionVO {
    private String groupId;
    private String groupName;
    private double myBalance;
    private ArrayList<GroupTransactionVO2> arrayList_members;


    public GroupTransactionVO(){

    }

    public GroupTransactionVO(String groupId, String groupName, double myBalance, ArrayList<GroupTransactionVO2> arrayList_members) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.myBalance = myBalance;
        this.arrayList_members = arrayList_members;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public double getMyBalance() {
        return myBalance;
    }

    public void setMyBalance(double myBalance) {
        this.myBalance = myBalance;
    }

    public ArrayList<GroupTransactionVO2> getArrayList_members() {
        return arrayList_members;
    }

    public void setArrayList_members(ArrayList<GroupTransactionVO2> arrayList_members) {
        this.arrayList_members = arrayList_members;
    }

    @Override
    public String toString() {
        return "GroupTransactionVO{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", myBalance=" + myBalance +
                ", arrayList_members=" + arrayList_members +
                '}';
    }
}
