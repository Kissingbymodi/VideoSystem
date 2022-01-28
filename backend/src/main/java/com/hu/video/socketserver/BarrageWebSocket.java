package com.hu.video.socketserver;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/BarrageWebSocket")
@Component
public class BarrageWebSocket {

    private Session session;

    private Integer videoId = null;

    /**
     * 系统连接数
     */
    private static int connectCount = 0;

    /**
     * 记录同一视频人数
     */
    private static final Map<Integer, Integer> videoCount = new ConcurrentHashMap<>();

    /**
     * 实时弹幕注册中心
     */
    private static final Map<Integer, CopyOnWriteArraySet<BarrageWebSocket>> barrageRegister = new ConcurrentHashMap<>();

    /**
     * sendGroupMessage 进行组发
     * @param userName
     * @param videoId
     * @param message
     */
    public static void sendGroupMessage(String userName, Integer videoId, String message) {
        CopyOnWriteArraySet<BarrageWebSocket> userGroup = barrageRegister.get(videoId);
        if (userGroup != null) {
            System.out.println("用户{" + userName + "}在观看视频{" + videoId + "}时，组发了message{" + message + "}");
            userGroup.forEach(barrageWebSocket -> {
                barrageWebSocket.sendMessage(userName + ":" + message);
            });
        } else {
            System.out.println("不可发送消息，因为已经没有用户观看该视频{" + videoId + "}");
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        Map<String, Object> param = new HashMap<>();
        System.out.println("session" + this.session.getQueryString());
        for (String str : this.session.getQueryString().split("&")) {
            String[] split = str.split("=");
            param.put(split[0], split[1]);
        }
        try {
            this.videoId = Integer.parseInt((String) param.get("videoId"));
            // 不为空直接放入, 为空新建CopyOnWriteArraySet<BarrageWebSocket> svu，再放入
            if (barrageRegister.get(videoId) != null) {
                barrageRegister.get(videoId).add(this);
            } else {
                CopyOnWriteArraySet<BarrageWebSocket> svu = new CopyOnWriteArraySet<>();
                svu.add(this);
                barrageRegister.put(this.videoId, svu);
            }
            // 记录连接数
            BarrageWebSocket.addConnectCount();
            BarrageWebSocket.addVideoCount(videoId);
            //反馈用户
            this.sendMessage("你可以发送弹幕了，一大波弹幕大军正在袭来！");
            this.sendMessage("当前有" + videoCount.get(videoId) + "个人正在观看此视频");
            System.out.println("有用户正在观看视频{" + videoId + "}"); //待优化
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        Integer videoId = this.videoId;
        BarrageWebSocket.subConnectCount();
        BarrageWebSocket.subVideoCount(videoId);
        if(barrageRegister.get(videoId) != null) {
            barrageRegister.get(videoId).remove(this);
            int size = barrageRegister.get(videoId).size();
            System.out.println("有用户退出观看视频{" + videoId + "}");
            if(size == 0) {
                barrageRegister.remove(videoId);
                System.out.println("视频{" + videoId + "}Socket已经移除");
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * socket收到客户端消息，给予反馈
     * @param message 客户端发来的信息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        this.sendMessage("接受到你的消息，你现在观看的视频videoId = " + this.videoId);
    }

    /**
     * sendMessage 发送消息
     * @param s
     */
    private void sendMessage(String s) {
        try {
            this.session.getBasicRemote().sendText(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加观看同一视频数
     * @param videoId
     */
    private static void addVideoCount(Integer videoId) {
        if (videoCount.get(videoId) != null) {
            Integer countTemp = videoCount.get(videoId) + 1;
            videoCount.replace(videoId, countTemp);
        } else {
            videoCount.put(videoId, 1);
        }
        System.out.println("视频{" + videoId + "}观看人数增加了，现在为" + videoCount.get(videoId));
    }

    /**
     * 减少观看同一视频的用户数
     * @param videoId
     */
    public static void subVideoCount(Integer videoId) {
        if (videoCount.get(videoId) != null) {
            Integer countTemp = videoCount.get(videoId) - 1;
            if (countTemp > 0) {
                videoCount.replace(videoId, countTemp);
                System.out.println("视频{" + videoId + "}观看人数减少了，现在为" + videoCount.get(videoId));
            } else {
                videoCount.remove(videoId);
                System.out.println("视频{" + videoId + "}已无人观看了");
            }
        }
    }

    /**
     * 增加实时弹幕总的连接数
     */
    private static synchronized void addConnectCount() {
        BarrageWebSocket.connectCount++;
        System.out.println("当前连接数增加了，现在连接数为" + BarrageWebSocket.getConnectCount());
    }

    /**
     * 减少视频弹幕的总的连接数
     */
    public static synchronized void subConnectCount() {
        if (BarrageWebSocket.getConnectCount() > 0) {
            BarrageWebSocket.connectCount--;
            System.out.println("当前连接数减少了，现在连接数为" + BarrageWebSocket.getConnectCount());
        }
    }

    /**
     * 通过webSocket得到实时弹幕此时总连接数
     *
     * @return connectCount
     */
    private static synchronized int getConnectCount() {
        return BarrageWebSocket.connectCount;
    }


}
