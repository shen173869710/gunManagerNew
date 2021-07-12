package com.auto.di.guan.manager.rtm;

import android.content.Context;
import android.util.Log;

import com.auto.di.guan.manager.activity.IBaseActivity;
import com.auto.di.guan.manager.app.BaseApp;
import com.auto.di.guan.manager.entity.Entiy;
import com.auto.di.guan.manager.event.DialogEvent;
import com.auto.di.guan.manager.event.UserStatusEvent;
import com.auto.di.guan.manager.utils.LogUtils;
import com.auto.di.guan.manager.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.agora.rtm.ErrorInfo;
import io.agora.rtm.ResultCallback;
import io.agora.rtm.RtmClient;
import io.agora.rtm.RtmClientListener;
import io.agora.rtm.RtmFileMessage;
import io.agora.rtm.RtmImageMessage;
import io.agora.rtm.RtmMediaOperationProgress;
import io.agora.rtm.RtmMessage;
import io.agora.rtm.SendMessageOptions;

public class ChatManager {
    private static final String TAG = ChatManager.class.getSimpleName();

    private IBaseActivity IBaseActivity;
    private Context mContext;
    private RtmClient mRtmClient;
    private List<RtmClientListener> mListenerList = new ArrayList<>();
    /**
     *    当前登录用户的ID
     */
    private String loginId;

    public ChatManager(Context context) {
        mContext = context;
    }

    public void init() {
        String appID = "2aa0369d8f7e48d2b993df37ca325897";
        try {
            mRtmClient = RtmClient.createInstance(mContext, appID, new RtmClientListener() {
                @Override
                public void onConnectionStateChanged(int state, int reason) {
                    for (RtmClientListener listener : mListenerList) {
                        listener.onConnectionStateChanged(state, reason);
                    }
                }

                @Override
                public void onMessageReceived(RtmMessage rtmMessage, String peerId) {
//                    if (mListenerList.isEmpty()) {
//                        mMessagePool.insertOfflineMessage(rtmMessage, peerId);
//                    } else {
//                        for (RtmClientListener listener : mListenerList) {
//                            listener.onMessageReceived(rtmMessage, peerId);
//                        }
//                    }
//                    LogUtils.e(TAG, "onMessageReceived   peerid = "+peerId + "message" +rtmMessage.getText());
                    EventBus.getDefault().post(new DialogEvent(false));
                    MessageParse.praseData(rtmMessage.getText(), peerId);

                }

                @Override
                public void onImageMessageReceivedFromPeer(final RtmImageMessage rtmImageMessage, final String peerId) {
                    if (mListenerList.isEmpty()) {
                        // If currently there is no callback to handle this
                        // message, this message is unread yet. Here we also
                        // take it as an offline message.
                    } else {
                        for (RtmClientListener listener : mListenerList) {
                            listener.onImageMessageReceivedFromPeer(rtmImageMessage, peerId);
                        }
                    }
                }

                @Override
                public void onFileMessageReceivedFromPeer(RtmFileMessage rtmFileMessage, String s) {

                }

                @Override
                public void onMediaUploadingProgress(RtmMediaOperationProgress rtmMediaOperationProgress, long l) {

                }

                @Override
                public void onMediaDownloadingProgress(RtmMediaOperationProgress rtmMediaOperationProgress, long l) {

                }

                @Override
                public void onTokenExpired() {

                }

                @Override
                public void onPeersOnlineStatusChanged(Map<String, Integer> status) {
                    LogUtils.e(TAG, "onPeersOnlineStatusChanged" + status.toString());

                    for (String key : status.keySet()) {
                        int code = status.get(key);
                        EventBus.getDefault().post(new UserStatusEvent(key, code));
                        if (code == 0) {
                            LogUtils.e(TAG, "  当前用户ID  peerId = " + key + " 当前状态在线 ");
                        }else {
                            LogUtils.e(TAG, "  当前用户ID  peerId = " + key + " 当前状态离线 ");
                        }
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            throw new RuntimeException("NEED TO check rtm sdk init fatal error\n" + Log.getStackTraceString(e));
        }

        // Global option, mainly used to determine whether
        // to support offline messages now.
    }

    public RtmClient getRtmClient() {
        return mRtmClient;
    }

    /**
     *    用户登录
     */
    public void doLogin(Set<String> user, IBaseActivity activity) {

        IBaseActivity = activity;
        mRtmClient.login(null, BaseApp.getUser().getUserId().toString(), new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void responseInfo) {
                LogUtils.e(TAG, "login success");
//                runOnUiThread(() -> {
//
//                });
                addPeersOnlineStatusListen(user);
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                LogUtils.e(TAG, "login failed: " + errorInfo.getErrorCode());
            }
        });
    }


    public void addPeersOnlineStatusListen(Set<String> user) {
        mRtmClient.subscribePeersOnlineStatus(user, new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                LogUtils.e(TAG, "subscribePeersOnlineStatus: onSuccess" );
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                LogUtils.e(TAG, "subscribePeersOnlineStatus: onFailure" +errorInfo.getErrorDescription());
            }
        });
    }


    public void doLogout(Set<String> user) {
            mRtmClient.unsubscribePeersOnlineStatus(user, new ResultCallback(){
                @Override
                public void onSuccess(Object o) {

                }

                @Override
                public void onFailure(ErrorInfo errorInfo) {

                }
            });
            mRtmClient.logout(null);
        MessageUtil.cleanMessageListBeanList();
    }



    public void sendPeerMessage(String content) {
        EventBus.getDefault().post(new DialogEvent(true));
        final RtmMessage message = mRtmClient.createMessage();
        message.setText(content);
        SendMessageOptions option = new SendMessageOptions();
        option.enableOfflineMessaging = false;

        LogUtils.e(TAG, "loginId = "+loginId);
        mRtmClient.sendMessageToPeer(String.valueOf(loginId), message, option, new ResultCallback<Void>() {

            @Override
            public void onSuccess(Void aVoid) {
                LogUtils.e(TAG, "sendPeerMessage : onSuccess");
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                Entiy.onPeerError(TAG, errorInfo.getErrorCode());

            }
        });
    }

    public void sendLoginPeerMessage(String id,String content) {
        if (IBaseActivity != null) {
            IBaseActivity.showDialog();
        }

        final RtmMessage message = mRtmClient.createMessage();
        message.setText(content);
        SendMessageOptions option = new SendMessageOptions();
        option.enableOfflineMessaging = false;
        mRtmClient.sendMessageToPeer(id, message, option, new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                BaseApp.setLoginId(id);
                setLoginId(id);
                LogUtils.e(TAG, "sendPeerMessage : onSuccess");
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                Entiy.onPeerError(TAG, errorInfo.getErrorCode());
            }
        });
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
