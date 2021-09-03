package com.websockets.WebSockets.controllers;

import java.util.List;

import com.websockets.WebSockets.Dao.MainDao;
import com.websockets.WebSockets.model.Notifications;
import com.websockets.WebSockets.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("get")
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;

    private Notifications notifications = new Notifications(0);

    @Autowired MainDao cargodao;

    @GetMapping("/notify")
    public String getNotification() {

        notifications.increment();

        template.convertAndSend("/topic/notification", notifications);

   
        return "Notifications successfully sent to  !";
    }


    @PostMapping({ "/adduser" })
    public ResponseEntity<List<User>> createDeck(@RequestBody User payload) {


        
            List<User> userTemp = cargodao.getUsers(payload);

            template.convertAndSend("/topic/notification",userTemp);

            return new ResponseEntity<List<User>>(userTemp,HttpStatus.OK);

    }

}
