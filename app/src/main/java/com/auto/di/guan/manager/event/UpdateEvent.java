package com.auto.di.guan.manager.event;

public class UpdateEvent {

    String type;

    public UpdateEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
