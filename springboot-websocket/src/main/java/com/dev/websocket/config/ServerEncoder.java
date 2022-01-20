package com.dev.websocket.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author liudean
 * 自定义返回格式
 * @date 2022/1/11 14:54
 */
public class ServerEncoder implements Encoder.Text<JSONObject> {
    @Override
    public String encode(JSONObject jsonObject){
        return JSON.toJSONString(jsonObject);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
