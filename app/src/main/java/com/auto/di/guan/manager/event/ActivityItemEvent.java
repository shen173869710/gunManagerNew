package com.auto.di.guan.manager.event;

import com.auto.di.guan.manager.basemodel.model.respone.EDepthRespone;
import com.auto.di.guan.manager.basemodel.model.respone.MeteoRespone;

import java.util.List;

/**
 *   Activity 点击或者关闭事件
 */
public class ActivityItemEvent {
    private List<MeteoRespone> meteoRespones;
    private List<EDepthRespone> eDepthRespones;

    public ActivityItemEvent(List<MeteoRespone> meteoRespones, List<EDepthRespone> eDepthRespones) {
        this.meteoRespones = meteoRespones;
        this.eDepthRespones = eDepthRespones;
    }

    public List<MeteoRespone> getMeteoRespones() {
        return meteoRespones;
    }

    public void setMeteoRespones(List<MeteoRespone> meteoRespones) {
        this.meteoRespones = meteoRespones;
    }

    public List<EDepthRespone> geteDepthRespones() {
        return eDepthRespones;
    }

    public void seteDepthRespones(List<EDepthRespone> eDepthRespones) {
        this.eDepthRespones = eDepthRespones;
    }
}
