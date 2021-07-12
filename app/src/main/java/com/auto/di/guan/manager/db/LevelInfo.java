package com.auto.di.guan.manager.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/7/25.
 */
public class LevelInfo {
    public Long id;

    public int levelId;
    public boolean isGroupUse;
    public boolean isLevelUse;
    public boolean isOtherUse;

    public LevelInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getLevelId() {
        return this.levelId;
    }
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
    public boolean getIsGroupUse() {
        return this.isGroupUse;
    }
    public void setIsGroupUse(boolean isGroupUse) {
        this.isGroupUse = isGroupUse;
    }
    public boolean getIsLevelUse() {
        return this.isLevelUse;
    }
    public void setIsLevelUse(boolean isLevelUse) {
        this.isLevelUse = isLevelUse;
    }
    public boolean getIsOtherUse() {
        return this.isOtherUse;
    }
    public void setIsOtherUse(boolean isOtherUse) {
        this.isOtherUse = isOtherUse;
    }

}
