package com.auto.di.guan.manager.rtm;

import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.db.ControlInfo;
import com.auto.di.guan.manager.db.DeviceInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.sql.ControlInfoSql;
import com.auto.di.guan.manager.db.sql.GroupInfoSql;
import com.auto.di.guan.manager.entity.CmdStatus;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.event.ActivityItemEvent;
import com.auto.di.guan.manager.event.AutoTimeEvent;
import com.auto.di.guan.manager.event.DateChangeEvent;
import com.auto.di.guan.manager.event.LoginEvent;
import com.auto.di.guan.manager.socket.SocketBengEvent;
import com.auto.di.guan.manager.socket.SocketResult;
import com.auto.di.guan.manager.utils.FloatStatusUtil;
import com.auto.di.guan.manager.utils.GzipUtil;
import com.auto.di.guan.manager.utils.LogUtils;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
/**
 *     解析消息
 */
public class MessageParse {
    public static final String TAG = "MessageParse----app 同步信息--";
    public static final String 收到自动轮灌_命令同步信息 = "收到自动轮灌-----------命令同步信息";

    public static void praseData(String data, String peerId) {

        String  res = GzipUtil.ungzip(data);
//        LogUtil.e(TAG, "解压缩数据失败"+e.getMessage());
        MessageInfo info = new Gson().fromJson(res, MessageInfo.class);
        if (info == null) {
            LogUtils.e(TAG, "gson 数据解析异常");
            return;
        }
//        MessageInfo info = new Gson().fromJson(data, MessageInfo.class);
        if (info == null) {
            LogUtils.e(TAG, "gson 数据解析异常");
            return;
        }
        switch (info.getType()) {
            case MessageEntiy.TYPE_LOGIN:
                // 登录
                if (info.getDeviceInfos() != null && info.getGroupInfos() != null) {
                    BaseApp.getInstance().getChatManager().setLoginId(String.valueOf(peerId));
                    BaseApp.setDeviceInfos(info.getDeviceInfos());
                    BaseApp.setGroupInfos(info.getGroupInfos());
                    Entiy.GRID_COLUMNS = info.getCloumn();
                    EventBus.getDefault().post(new LoginEvent(true));
                }
                break;
            case MessageEntiy.TYPE_LOGOUT:
                // 登出
                EventBus.getDefault().post(new LoginEvent(false));
                break;
            case MessageEntiy.TYPE_SINGLE_READ:
                // 单个操作 读
            case MessageEntiy.TYPE_SINGLE_OPEN:
                // 单个操作 开
            case MessageEntiy.TYPE_SINGLE_CLOSE:
                // 单个操作 关
                LogUtils.e(TAG, "收到单个操作数据 ========="+new Gson().toJson(info));
                if (info.getDeviceInfos() != null) {
                    dealSingle(info.getDeviceInfos());
                }
                break;
            case MessageEntiy.TYPE_GROUP_OPEN:
                LogUtils.e(TAG, "单组轮灌开启成功");
                // 单组操作 开
                if (info.getDeviceInfos() != null && info.getGroupInfos() != null) {
                    dealGroup(info.getDeviceInfos(), info.getGroupInfos());
                }
                break;
            case MessageEntiy.TYPE_GROUP_CLOSE:
                LogUtils.e(TAG, "单组轮灌关闭成功");
                if (info.getDeviceInfos() != null && info.getGroupInfos() != null) {
                    // 单组操作 关
                    dealGroup(info.getDeviceInfos(), info.getGroupInfos());
                }
                break;
            case MessageEntiy.TYPE_AUTO_OPEN:
                LogUtils.e(TAG, "自动轮灌开启成功");
                // 单组自动轮灌 下一组
                if (info.getGroupInfos() != null && info.getDeviceInfos() != null) {
                    dealAuto(info.getDeviceInfos(), info.getGroupInfos());
                }
                break;
                // 单组自动轮灌开
            case MessageEntiy.TYPE_AUTO_CLOSE:
                LogUtils.e(TAG, "自动轮灌关闭成功");
                // 单组自动轮灌 下一组
                if (info.getGroupInfos() != null && info.getDeviceInfos() != null) {
                    dealAuto(info.getDeviceInfos(), info.getGroupInfos());
                }
                break;
                // 单组自动轮灌 开始
            case MessageEntiy.TYPE_AUTO_START:
                LogUtils.e(TAG, "自动轮灌开始成功");
                // 单组自动轮灌 下一组
                if (info.getGroupInfos() != null && info.getDeviceInfos() != null) {
                    dealAuto(info.getDeviceInfos(), info.getGroupInfos());
                }
                break;
                // 单组自动轮灌 暂停
            case MessageEntiy.TYPE_AUTO_STOP:
                LogUtils.e(TAG, "自动轮灌暂停成功");
                // 单组自动轮灌 下一组
                if (info.getGroupInfos() != null && info.getDeviceInfos() != null) {
                    dealAuto(info.getDeviceInfos(), info.getGroupInfos());
                }
                break;
                // 单组自动轮灌 开始
            case MessageEntiy.TYPE_AUTO_NEXT:
                LogUtils.e(TAG, "自动轮灌下一组成功");
                // 单组自动轮灌 下一组
                if (info.getGroupInfos() != null && info.getDeviceInfos() != null) {
                    dealAuto(info.getDeviceInfos(), info.getGroupInfos());
                }
                break;
            case MessageEntiy.TYPE_AUTO_TIME:
                LogUtils.e(TAG, "自动轮灌设置时间成功");
                // 单组自动轮灌 下一组
                if (info.getGroupInfos() != null && info.getDeviceInfos() != null) {
                    dealAuto(info.getDeviceInfos(), info.getGroupInfos());
                }
                break;
            case MessageEntiy.TYPE_TIME_COUNT:
                if (info.getGroupInfo() != null) {
                    dealAutoTime(info.getGroupInfo());
                }
                break;
            case MessageEntiy.TYPE_AUTO_STATUS:
                LogUtils.e(TAG, "===============================所有信息状态同步================================");

                if (info.getGroupInfos() != null && info.getDeviceInfos() != null) {
                    dealAutoStatus(info.getDeviceInfos(), info.getGroupInfos());
                }
                break;
            case MessageEntiy.TYPE_CREATE_GROUP:
            case MessageEntiy.TYPE_DEL_GROUP:
            case MessageEntiy.TYPE_EXIT_GROUP:
            case MessageEntiy.TYPE_DISS_GROUP:
                dealGoupOption(info.getDeviceInfos(), info.getGroupInfos());
                break;
            case MessageEntiy.TYPE_GROUP_LEVEL:
                if (info.getGroupInfos() != null) {
                    dealGoupLevel(info.getGroupInfos());
                }
                break;
            case MessageEntiy.TYPE_BENG_CLOSE:
            case MessageEntiy.TYPE_BENG_OPEN:
                if (info.getSocketResults()!= null) {
                    dealBengOption(info.getSocketResults());
                }
            case MessageEntiy.TYPE_MESSAGE:
                if (info.getCmdStatus() != null) {
                    dealMessage(info.getCmdStatus());
                }
                break;
            case MessageEntiy.TYPE_FARMLAND:
                /**
                 *  农田信息
                 */
                LogUtils.e(TAG, "收到农田同步信息");
                if (info.getMeteoRespones() != null && info.geteDepthRespones() != null) {
                    EventBus.getDefault().post(new ActivityItemEvent(info.getMeteoRespones(), info.geteDepthRespones()));
                }
                break;
        }
    }
    /**
     *   处理单个操作
     */
    public static void dealSingle(ArrayList<DeviceInfo> deviceInfos) {
        LogUtils.e(TAG, "单个操作成功");
        BaseApp.setDeviceInfos(deviceInfos);
        EventBus.getDefault().post(new DateChangeEvent(false));
    }

    /**
     *  处理单组操作
     */
    public static void dealGroup(ArrayList<DeviceInfo>list, ArrayList<GroupInfo >groupInfo) {
        LogUtils.e(TAG, "单组操作成功");
        if (list == null || groupInfo == null) {
            LogUtils.e(TAG, "单组操作  传递数据异常");
            return;
        }

        if (list.size() == 0) {
            LogUtils.e(TAG, "单组操作  传递数据异常, 组的控制阀为0");
            return;
        }
        BaseApp.setDeviceInfos(list);
        BaseApp.setGroupInfos(groupInfo);
//        ControlInfoSql.updataControlList(list);
//        int postion = GroupInfoSql.updateGroup(groupInfo);
        EventBus.getDefault().post(new DateChangeEvent(true, 1));
    }


    /**
     *        处理操作信息同步
     * @param cmdStatus
     */
    public static void dealMessage(CmdStatus cmdStatus) {
        LogUtils.i(TAG, "收到操作信息同步");
        EventBus.getDefault().post(cmdStatus);
    }

    /**
     *  同步 组设置相关信息
     */
    public static void dealGoupOption(ArrayList<DeviceInfo>deviceInfos, ArrayList<GroupInfo> groupInfos) {
        BaseApp.setDeviceInfos(deviceInfos);
        BaseApp.setGroupInfos(groupInfos);
        EventBus.getDefault().post(new DateChangeEvent(true));
    }

    /**
     *        同步自动轮灌时间设置
     * @param groupInfos
     */
    public static void dealGoupLevel(ArrayList<GroupInfo> groupInfos) {
        BaseApp.setGroupInfos(groupInfos);
        DateChangeEvent event = new DateChangeEvent(true);
        event.setType(MessageEntiy.TYPE_GROUP_LEVEL);
        EventBus.getDefault().post(event);
    }

    /**
     *        处理开泵相关信息
     * @param socketResults
     */
    public static void dealBengOption(List<SocketResult> socketResults) {
        EventBus.getDefault().post(new SocketBengEvent(socketResults));
    }

    /**
     *  处理自动轮灌
     */
    public static void dealAuto(ArrayList<DeviceInfo>deviceInfos, ArrayList<GroupInfo> infos) {
        LogUtils.e(TAG, 收到自动轮灌_命令同步信息);
        BaseApp.setDeviceInfos(deviceInfos);
        BaseApp.setGroupInfos(infos);
        EventBus.getDefault().post(new DateChangeEvent(true));

        /**
         * 自动轮灌单组变化
         */
    }

    /**
     *  处理自动轮灌设备状态
     */
    public static void dealAutoStatus(ArrayList<DeviceInfo>deviceInfos, ArrayList<GroupInfo> infos) {
        LogUtils.e(TAG, "收到自动轮灌------------所有设备状态同步====================================");
        BaseApp.setDeviceInfos(deviceInfos);
        BaseApp.setGroupInfos(infos);
        EventBus.getDefault().post(new DateChangeEvent(true));

        /**
         *  更新悬浮窗实时状态
         */
        GroupInfo info = GroupInfoSql.getRunGroup();
        if(info != null) {
            FloatStatusUtil.getInstance().onGroupStatusEvent(info);
        }
    }

    /**
     *  同步自动轮时间
     */
    public static void dealAutoTime(GroupInfo groupInfo) {
        LogUtils.i(TAG, "收到自动轮灌------------时间信息同步");
        EventBus.getDefault().post(new AutoTimeEvent(groupInfo));
        /*****同步自动轮灌时间******/
        FloatStatusUtil.getInstance().onAutoCountEvent(groupInfo);
    }
}
