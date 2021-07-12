package com.auto.di.guan.manager.entity;


import com.auto.di.guan.manager.R;
import com.auto.di.guan.manager.db.ControlInfo;

/**
 * Created by Administrator on 2017/7/9.
 */

public class ImageStatus {


    public static int STATUS_EMPTY = 0;
    public static int STATUS_ADD = 1;
    public static int STATUS_BIND = 2;
    public static int STATUS_WORK = 3;

    public static int STATUS_ERROE_1 = 4;
    public static int STATUS_ERROE_2 = 5;
    public static int STATUS_ERROE_3 = 6;
    public static int STATUS_ERROE_4 = 7;


    public static String [] GROUP_ICON = {

    };

    public static int PIPE_TYPE_1 = 0;
    public static int PIPE_TYPE_2 = 1;
    public static int PIPE_TYPE_MAIN_90 = 2;
    public static int PIPE_TYPE_MAIN_180 = 3;
    public static int PIPE_TYPE_MAIN_270 = 4;
    public static int PIPE_TYPE_MAIN_360 = 5;

    public static int PIPE_TYPE_CHILD_90 = 6;
    public static int PIPE_TYPE_CHILD_180 = 7;
    public static int PIPE_TYPE_CHILD_270 = 8;
    public static int PIPE_TYPE_CHILD_360 = 9;


    public static String[] IMAGE_TYPE = {
        "单控阀控器","双控阀控器","主管道(90)","主管道(180)","主管道(270)","分支管道(360)","分支管道(90)","分支管道(180)","分支管道(270)","分支管道(360)"
    };

    public static int getImageId (ControlInfo info){
        int id = 0;
        switch (info.getValveStatus()) {
            case Entiy.CONTROL_STATUS＿CONNECT:
                id = R.mipmap.lighe_1;
            break;
//            case Entiy.CONTROL_STATUS＿2:
//                id = R.mipmap.lighe_1;
//                break;
            case Entiy.CONTROL_STATUS＿RUN:
                id = R.mipmap.lighe_2;
                break;
            case Entiy.CONTROL_STATUS＿ERROR:
                id = R.mipmap.lighe_3;
                break;
        }
        return id;
    }
}
