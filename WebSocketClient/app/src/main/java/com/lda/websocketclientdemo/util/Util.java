package com.lda.websocketclientdemo.util;

import android.content.Context;
import android.widget.Toast;

public class Util {
    public static final String ws = "ws://192.168.3.254:8010/result";//websocket测试地址

    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
