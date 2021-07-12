package com.auto.di.guan.manager.basemodel.presenter;


import com.auto.di.guan.manager.api.HttpManager;
import com.auto.di.guan.manager.basemodel.model.respone.ApplyFertilizerRecord;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.basemodel.model.respone.RaiseCropsRecord;
import com.auto.di.guan.manager.basemodel.model.respone.WateringRecord;
import com.auto.di.guan.manager.basemodel.view.IBaseView;

/**
 * Created by czl on 2019/7/9.
 * 通用的请求
 */

public class CommonPresenter extends BasePresenter<IBaseView>{
    /**
     *    产量记录
     * */
    public void addRaise(RaiseCropsRecord record) {
        doHttpTask(getApiService().saveRaise(record), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                getBaseView().success(respone);
            }

            @Override
            public void onError(Throwable error, Integer code, String msg) {
                getBaseView().fail(error, code, msg);
            }
        });
    }
    /**
     *       操作记录
     * @param record
     */
    public void addWater(WateringRecord record) {
        // 测试提交施肥
        doHttpTask(getApiService().saveWater(record), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                getBaseView().success(respone);
            }

            @Override
            public void onError(Throwable error, Integer code, String msg) {
                getBaseView().fail(error, code, msg);
            }
        });
    }
    /**
     *       施肥记录
     * @param record
     */
    public void addApply(ApplyFertilizerRecord record) {
        // 测试提交施肥
        doHttpTask(getApiService().saveApply(record), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                getBaseView().success(respone);
            }

            @Override
            public void onError(Throwable error, Integer code, String msg) {
                getBaseView().fail(error, code, msg);
            }
        });
    }

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
