/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.locationdemo.controller;

import com.example.locationdemo.dto.NotifyDeviceDTO;
import com.google.gson.Gson;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 *
 * @author zrrn
 */
@Log
@ServerEndpoint("/user/socket")
@Controller
public class UserWebSocketController {

    private static Session session;
    public static Double longitude;
    public static Double altitude;
    private static final Gson gson = new Gson();

    // yeni bir bağlantı açıldığında
    @OnOpen
    public void onOpen(Session session){
        UserWebSocketController.session = session;
    }
    //mesaj alındığında
    @OnMessage
    public void onMessage(Session session, String message) {
        NotifyDeviceDTO notifyDeviceDTO = gson.fromJson(message, NotifyDeviceDTO.class);
        longitude = notifyDeviceDTO.getLongitude();
        altitude = notifyDeviceDTO.getAltitude();
    }
    //bağlantı kapatıldığında
    @OnClose
    public void onClose(Session session) {
        UserWebSocketController.session = null;
        longitude = null;
        altitude = null;
    }
    //bildirim alındıgında
    public void notifyUser(String text) throws IOException {
        if (session != null)
            session.getBasicRemote().sendText(text);
    }

}
