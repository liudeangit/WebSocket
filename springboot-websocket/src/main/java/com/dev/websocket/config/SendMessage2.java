package com.dev.websocket.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description : 点对点消息  //描述
 * 主要区别在于sendMessage这个方法和可自定义返回格式
 */
@Component
@ServerEndpoint(value = "/websocket/{uuid}",encoders = ServerEncoder.class)
public class SendMessage2 {

    public static ConcurrentHashMap<String, Session> clients = new ConcurrentHashMap<>();

    private final Logger log = LoggerFactory.getLogger("SendMessage2");

    /**
     * 连接建立成功调用的方法
     * */
    @OnOpen
    public void onOpen(Session session, @PathParam("uuid") String uuid) {
        log.info("客户端：" + uuid + "连接成功了");
        clients.put(uuid, session);
        sendMessage("连接成功！");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("uuid") String uuid) {
        log.info("客户端：" + uuid + "断开连接了");
        clients.remove(uuid);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到客户端消息:"+message);

    }
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }


    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message) {
        try {
            if ("".equals(message) || message==null){
                message = "革命尚未成功，同志仍需努力!";
            }
            String uuid = "123";//这样就可通过数据库或者其他情况得到uuid或者用户id
            Session session = SendMessage2.clients.get(uuid);//获取到session信息
            if (session != null) {
                JSONObject result = new JSONObject();

                result.put("data",message);
                session.getBasicRemote().sendObject(result);//自定义返回格式
            } else {
                System.out.println("找不到客户端："+uuid+"的连接");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/10 * * * * ?")
    private void taskRun(){
        sendMessage("");
    }
}
