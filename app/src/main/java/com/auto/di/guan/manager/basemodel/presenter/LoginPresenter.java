package com.auto.di.guan.manager.basemodel.presenter;


import com.auto.di.guan.manager.api.HttpManager;
import com.auto.di.guan.manager.basemodel.model.request.BaseRequest;
import com.auto.di.guan.manager.basemodel.model.respone.ApplyFertilizerRecord;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.basemodel.model.respone.PageInfo;
import com.auto.di.guan.manager.basemodel.model.respone.RaiseCropsRecord;
import com.auto.di.guan.manager.basemodel.model.respone.WateringRecord;
import com.auto.di.guan.manager.basemodel.view.IBaseView;
import com.auto.di.guan.manager.utils.Md5Util;

import java.util.TreeMap;

/**
 * Created by czl on 2019/7/9.
 * 用户登录相关逻辑业务
 */

public class LoginPresenter extends BasePresenter<IBaseView>{

    /**
     *
     * 登录请求
     * **/
    public void doLogin(String userName, final String pwd) {
        String password = Md5Util.md5(pwd);
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("loginName",userName);
        treeMap.put("password",pwd);
//        treeMap.put("pageNum",1);
//        treeMap.put("pageSize",2);
        doHttpTask(getApiService().login(BaseRequest.toMerchantTreeMap(treeMap)), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                getBaseView().success(respone);
            }
            @Override
            public void onError(Throwable error, Integer code,String msg) {
                getBaseView().fail(error,code,msg);
            }
        });
        // 测试提交施肥

//        RaiseCropsRecord record = new RaiseCropsRecord();
//
//        doHttpTask(getApiService().saveRaise(record), new HttpManager.OnResultListener() {
//            @Override
//            public void onSuccess(BaseRespone respone) {
//                getBaseView().success(respone);
//            }
//            @Override
//            public void onError(Throwable error, Integer code,String msg) {
//                getBaseView().fail(error,code,msg);
//            }
//        });
//
//
//        WateringRecord wateringRecord = new WateringRecord();
//
//        doHttpTask(getApiService().saveWater(wateringRecord), new HttpManager.OnResultListener() {
//            @Override
//            public void onSuccess(BaseRespone respone) {
//                getBaseView().success(respone);
//            }
//            @Override
//            public void onError(Throwable error, Integer code,String msg) {
//                getBaseView().fail(error,code,msg);
//            }
//        });
//
//        ApplyFertilizerRecord app = new ApplyFertilizerRecord();
//
//        doHttpTask(getApiService().saveApply(app), new HttpManager.OnResultListener() {
//            @Override
//            public void onSuccess(BaseRespone respone) {
//                getBaseView().success(respone);
//            }
//            @Override
//            public void onError(Throwable error, Integer code,String msg) {
//                getBaseView().fail(error,code,msg);
//            }
//        });
//
//        PageInfo pageInfo = new PageInfo();
//        pageInfo.pageNum = 0;
//        pageInfo.pageSize = 10;
//        doHttpTask(getApiService().getApplyList(pageInfo), new HttpManager.OnResultListener() {
//            @Override
//            public void onSuccess(BaseRespone respone) {
////                getBaseView().success(respone);
//            }
//            @Override
//            public void onError(Throwable error, Integer code,String msg) {
////                getBaseView().fail(error,code,msg);
//            }
//        });



    }

}
