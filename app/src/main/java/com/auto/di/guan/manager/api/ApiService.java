package com.auto.di.guan.manager.api;


import com.auto.di.guan.manager.basemodel.model.respone.ApplyFertilizerRecord;
import com.auto.di.guan.manager.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.manager.basemodel.model.respone.LoginRespone;
import com.auto.di.guan.manager.basemodel.model.respone.NoticeMessage;
import com.auto.di.guan.manager.basemodel.model.respone.RaiseCropsRecord;
import com.auto.di.guan.manager.basemodel.model.respone.WateringRecord;
import com.auto.di.guan.manager.entity.TableDataInfo;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 请求的相关接口
 */
public interface ApiService {
    /**
     *  用户登录接口
     * @return
     */
    @FormUrlEncoded
    @POST("/api/user/group/login")
    Observable<BaseRespone<LoginRespone>> login(@FieldMap Map<String, Object> map);
    /**
     *  获取用户操作信息
     * @return
     */
    @FormUrlEncoded
    @POST("/api/project/perationlist")
    Observable<BaseRespone<TableDataInfo>> getActions(@FieldMap Map<String, Object> map);
    /**
     *  发送预计信息
     * @return
     */
    @FormUrlEncoded
    @POST("/api/send/smsMsg")
    Observable<BaseRespone<TableDataInfo>> sendSmsMsg(@FieldMap Map<String, Object> map);
    /**
     *  获取通知
     * @return
     */
    @FormUrlEncoded
    @POST("api/notice/getPageList")
    Observable<BaseRespone<List<NoticeMessage>>> getNotice(@FieldMap Map<String, Object> map);
    /**
     *  保存种植作物记录
     * @return
     */
    @POST("api/save/raiseCrops")
    Observable<BaseRespone> saveRaise(@Body RaiseCropsRecord record);
    
    @FormUrlEncoded
    @POST("api/raiseCrops/getPageList")
    Observable<BaseRespone<List<RaiseCropsRecord>>>getRaiseList(@FieldMap Map<String, Object> map);
    /**
     *  浇水记录
     * @return
     */
    @POST("api/save/wateringRecord")
    Observable<BaseRespone> saveWater(@Body WateringRecord record);
    @FormUrlEncoded
    @POST("api/wateringRecord/getPageList")
    Observable<BaseRespone<List<WateringRecord>>>getWaterList(@FieldMap Map<String, Object> map);
    /**
     *  保存种植作物记录
     * @return
     */
    @POST("/api/save/applyFertilizer")
    Observable<BaseRespone> saveApply(@Body ApplyFertilizerRecord record);
    @FormUrlEncoded
    @POST("api/applyFertilizer/getPageList")
    Observable<BaseRespone<List<ApplyFertilizerRecord>>>getApplyList(@FieldMap Map<String, Object> map);
}
