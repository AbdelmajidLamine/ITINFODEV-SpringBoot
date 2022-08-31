package com.demo.web;

import com.demo.dao.NotificationDao;
import com.demo.entities.notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class NotificationRestService {

    @Autowired
    private NotificationDao notificationDao;

    private static final Logger logger = LoggerFactory.getLogger("My Notification");

    @GetMapping("/notification")
    public List<notification> getAll(){
        return notificationDao.findAll();
    }
    @GetMapping("/unreadNotification")
     public List<notification> getUnread(){
        List<notification> not=notificationDao.findAll();
        List<notification> newNot = new ArrayList<>();
        for(notification n:not){
           if(!n.isRead()){
               newNot.add(n);
           }
        }
        return newNot;
     }

     @GetMapping("/updateNot/{id}")
     public void updateNotification(@PathVariable long id){
        notificationDao.updateNot(true,id);
     }

    @PostMapping("/addNotification")
    public void addNotification(@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("details") String details){


        notificationDao.save(new notification(title,description,details,false));

    }

}
