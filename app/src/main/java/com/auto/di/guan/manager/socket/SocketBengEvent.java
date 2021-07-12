package com.auto.di.guan.manager.socket;

import java.util.List;

public class SocketBengEvent {
    private List<SocketResult> socketResults;

    public SocketBengEvent(List<SocketResult> socketResults) {
        this.socketResults = socketResults;
    }

    public List<SocketResult> getSocketResults() {
        return socketResults;
    }

    public void setSocketResults(List<SocketResult> socketResults) {
        this.socketResults = socketResults;
    }
}
