package com.xxx.cms.bean.vo;

public class AllMemberVO {
    String groupId;
    String groupName;
    String memberId;
    String memberName;
    String isCreator;
    String isManager;
    double balance;

    public AllMemberVO() {
    }

    public boolean isNullOrEmpty() {
        return this == null ||
                (this.groupId == null || this.groupId.isEmpty()) &&
                        (this.groupName == null || this.groupName.isEmpty()) &&
                        (this.memberId == null || this.memberId.isEmpty()) &&
                        (this.memberName == null || this.memberName.isEmpty()) &&
                        (this.isCreator == null || this.isCreator.isEmpty()) &&
                        (this.isManager == null || this.isManager.isEmpty());
    }

    public String getIsCreator() {
        return isCreator;
    }

    public void setIsCreator(String isCreator) {
        this.isCreator = isCreator;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public AllMemberVO(String groupId, String groupName, String memberId, String memberName,String isCreator, String isManager, double balance) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.memberId = memberId;
        this.memberName = memberName;
        this.isCreator = isCreator;
        this.isManager = isManager;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AllMemberVO{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", isCreator='" + isCreator + '\'' +
                ", isManager='" + isManager + '\'' +
                ", balance=" + balance +
                '}';
    }
}
