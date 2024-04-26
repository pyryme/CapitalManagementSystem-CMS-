package com.xxx.cms.bean.vo;

public class GroupVO {
    private String groupId;
    private String groupName;
    private String groupScale;
    private String direction;
    private String isPublic;

    public GroupVO() {
    }


    public boolean isNullOrEmpty() {
        return this == null ||
                (this.groupId == null || this.groupId.isEmpty()) &&
                        (this.groupName == null || this.groupName.isEmpty()) &&
                        (this.groupScale == null || this.groupScale.isEmpty()) &&
                        (this.direction == null || this.direction.isEmpty()) &&
                        (this.isPublic == null || this.isPublic.isEmpty());
    }



    public void reset() {
        this.groupId = null;
        this.groupName = null;
        this.groupScale = null;
        this.direction = null;
        this.isPublic = null;
    }


//
//    public  GroupVO initNull() {
//        this.groupId = groupId;
//        this.groupName = groupName;
//        this.groupScale = groupScale;
//        this.direction = direction;
//        this.isPublic = isPublic;
//    }

    public GroupVO(String groupId, String groupName, String groupScale, String direction, String isPublic) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupScale = groupScale;
        this.direction = direction;
        this.isPublic = isPublic;
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

    public String getGroupScale() {
        return groupScale;
    }

    public void setGroupScale(String groupScale) {
        this.groupScale = groupScale;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        return "GroupVO{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupScale='" + groupScale + '\'' +
                ", direction='" + direction + '\'' +
                ", isPublic='" + isPublic + '\'' +
                '}';
    }


}
//
//    public GroupVO(String group_id, String group_name, String group_scale, String direction, int is_public) {
//        this.group_id = group_id;
//        this.group_name = group_name;
//        this.group_scale = group_scale;
//        this.direction = direction;
//        this.is_public = is_public;
//    }
//
//    public String getGroup_id() {
//        return group_id;
//    }
//
//    public void setGroup_id(String group_id) {
//        this.group_id = group_id;
//    }
//
//    public String getGroup_name() {
//        return group_name;
//    }
//
//    public void setGroup_name(String group_name) {
//        this.group_name = group_name;
//    }
//
//    public String getGroup_scale() {
//        return group_scale;
//    }
//
//    public void setGroup_scale(String group_scale) {
//        this.group_scale = group_scale;
//    }
//
//    public String getDirection() {
//        return direction;
//    }
//
//    public void setDirection(String direction) {
//        this.direction = direction;
//    }
//
//    public int getIs_public() {
//        return is_public;
//    }
//
//    public void setIs_public(int is_public) {
//        this.is_public = is_public;
//    }
//
//    @Override
//    public String toString() {
//        return "GroupVO{" +
//                "group_id='" + group_id + '\'' +
//                ", group_name='" + group_name + '\'' +
//                ", group_scale='" + group_scale + '\'' +
//                ", direction='" + direction + '\'' +
//                ", is_public=" + is_public +
//                '}';
//    }




//
//
//
//import java.util.List;
//
///**
// * 群组的视图对象
// * 包含群组名称和群组成员列表等信息
// *
// * @author ChatGPT
// */
//public class GroupVO {
//    /**
//     * 群组名称
//     */
//    private String groupName;
//
//    /**
//     * 群组成员列表
//     */
//    private List<String> members;
//
//    public GroupVO() {
//    }
//
//    public GroupVO(String groupName, List<String> members) {
//        this.groupName = groupName;
//        this.members = members;
//    }
//
//    public String getGroupName() {
//        return groupName;
//    }
//
//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }
//
//    public List<String> getMembers() {
//        return members;
//    }
//
//    public void setMembers(List<String> members) {
//        this.members = members;
//    }
//
//    @Override
//    public String toString() {
//        return "GroupVO{" +
//                "groupName='" + groupName + '\'' +
//                ", members=" + members +
//                '}';
//    }
//}
