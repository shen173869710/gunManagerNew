package com.auto.di.guan.manager.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import androidx.multidex.MultiDex;
import com.auto.di.guan.manager.basemodel.model.respone.LoginRespone;
import com.auto.di.guan.manager.db.DeviceInfo;
import com.auto.di.guan.manager.db.GroupInfo;
import com.auto.di.guan.manager.db.User;
import com.auto.di.guan.manager.rtm.ChatManager;
import com.auto.di.guan.manager.utils.FloatStatusUtil;
import com.auto.di.guan.manager.utils.FloatWindowUtil;
import com.auto.di.guan.manager.utils.GsonUtil;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.SPUtils;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by Administrator on 2017/6/28.
 */

public class BaseApp extends Application {

    public static String TAG = "BaseApp";
    private static BaseApp instance;
    private ChatManager mChatManager;
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        BaseApp.user = user;
    }

    private static User user = new User();

    private  static ArrayList<DeviceInfo> deviceInfos = new ArrayList<>();

    private static Context mContext=null;//上下文

    private static String loginId;



    private static ArrayList<GroupInfo> groupInfos = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        mContext = getApplicationContext();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);


        Stetho.initializeWithDefaults(this);
//        LogUtils.setFilterLevel(LogUtils.ALL);
        FloatWindowUtil.getInstance().initFloatWindow(this);
        FloatStatusUtil.getInstance().initFloatWindow(this);
//        CrashHandler.getInstance().init(this);
        CrashReport.initCrashReport(getApplicationContext(), "cc201614d7", true);

        mChatManager = new ChatManager(this);
        mChatManager.init();

        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
    public static BaseApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }


    public void exit() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 200);
    }

    public static String getLoginId() {
        return loginId;
    }

    public static void setLoginId(String loginId) {
        BaseApp.loginId = loginId;
    }

    /**
     * 判断网络是否连接
     */
    public static boolean isConnectNomarl() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null == connectivityManager) {
                return false;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    }  else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                        return true;
                    }
                }
            }else{
                try {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        return true;
                    }
                } catch (Exception e) {
                    LogUtils.e(TAG,e.getMessage());
                }
            }

            //根据Android版本判断网络是否可用：6.0以后系统提供API可用，6.0之前使用ping命令
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if(null !=networkCapabilities){
                    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
                }
            } else {
                Process ipProcess = null;
                try {
                    Runtime runtime = Runtime.getRuntime();
                    if(null !=runtime){
                        ipProcess = runtime.exec("ping -c 3 t.wuzhenpay.com");
                    }

                    if(null !=ipProcess){
                        int exitValue = ipProcess.waitFor();
                        LogUtils.i(TAG, "Process:" + exitValue);
                        return (exitValue == 0);
                    }
                } catch (Exception e) {
                    LogUtils.e(TAG, e.getMessage());
                    return false;
                }finally {
                    if(null !=ipProcess){
                        ipProcess.destroy();
                    }
                }
            }

            InputStream stream = null;
            try {
                URL url = new URL("https://www.baidu.com");
                stream = url.openStream();
                if (null != stream) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }finally {
                if(null !=stream){
                    stream.close();
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * 用户登录的token信息
     */
    private static LoginRespone loginRespone;

    public static LoginRespone getLoginRespone() {
        if (loginRespone == null) {
            String info = SPUtils.getInstance().getString(SPUtils.SAVE_TOKEN_INFO);
            if (!TextUtils.isEmpty(info)) {
                loginRespone = GsonUtil.fromJson(info, LoginRespone.class);
            }
        }
        return loginRespone;
    }


    public static String getProjectId() {
        return user.getProjectId();
    }


    public static ArrayList<DeviceInfo> getDeviceInfos() {
        return deviceInfos;
    }

    public static void setDeviceInfos(ArrayList<DeviceInfo> deviceInfos) {
        BaseApp.deviceInfos = deviceInfos;
    }

    public ChatManager getChatManager() {
        return mChatManager;
    }

    public static ArrayList<GroupInfo> getGroupInfos() {
        return groupInfos;
    }

    public static void setGroupInfos(ArrayList<GroupInfo> groupInfos) {
        BaseApp.groupInfos = groupInfos;

    }
}
