package com.auto.di.guan.manager.db.sql;

import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.db.GroupInfo;

import java.util.ArrayList;
import java.util.List;

public class GroupInfoSql {

    public static List<GroupInfo> getJoinGroup() {
        List<GroupInfo> groupInfos = new ArrayList<>();
        int size = BaseApp.getGroupInfos().size();
        for (int i = 0; i < size; i++) {
            GroupInfo groupInfo = BaseApp.getGroupInfos().get(i);
            if (groupInfo.getGroupIsJoin()) {
                groupInfos.add(groupInfo);
            }
        }
        return groupInfos;
    }

    /**
     *        更新组信息
     * @param list
     */
    public static void updateGroups(List<GroupInfo> list) {
        List<GroupInfo> groupInfos = BaseApp.getGroupInfos();
        int size = groupInfos.size();
        int jSize = list.size();

        for (int i = 0; i < size; i++) {
            GroupInfo groupInfo = groupInfos.get(i);
            for (int j = 0; j < jSize; j ++) {
                GroupInfo info = list.get(j);
                if (groupInfo.getId() == info.getId()) {
                    groupInfo.setGroupTime(info.getGroupTime());
                    groupInfo.setGroupStatus(info.getGroupStatus());
                    groupInfo.setGroupLevel(info.getGroupLevel());
                    groupInfo.setGroupTime(info.getGroupTime());
                    groupInfo.setGroupRunTime(info.getGroupRunTime());
                    groupInfo.setGroupStop(info.getGroupStop());
                    groupInfo.setGroupIsJoin(info.getGroupIsJoin());
                }
            }
        }
    }


    /**
     * 更新组信息
     *
     * @param info
     */
    public static int updateGroup(GroupInfo info) {
        List<GroupInfo> groupInfos = BaseApp.getGroupInfos();
        int size = groupInfos.size();
        int postion = -1;
        for (int i = 0; i < size; i++) {
            GroupInfo groupInfo = groupInfos.get(i);
            if (groupInfo.getId() == info.getId()) {
                groupInfo.setGroupTime(info.getGroupTime());
                groupInfo.setGroupStatus(info.getGroupStatus());
                groupInfo.setGroupLevel(info.getGroupLevel());
                groupInfo.setGroupTime(info.getGroupTime());
                groupInfo.setGroupRunTime(info.getGroupRunTime());
                groupInfo.setGroupStop(info.getGroupStop());
                groupInfo.setGroupIsJoin(info.getGroupIsJoin());
                postion = i;
            }
        }
        return postion;
    }

    /**
     *        获取正在运行的设备
     * @return
     */
    public static GroupInfo getRunGroup() {
        GroupInfo groupInfo = null;
        int size = BaseApp.getGroupInfos().size();
        for (int i = 0; i < size; i++) {
            GroupInfo info = BaseApp.getGroupInfos().get(i);
            if (info.getGroupIsJoin() && info.getGroupStatus() == 1) {
                groupInfo = info;
            }
        }
        return groupInfo;
    }
}
