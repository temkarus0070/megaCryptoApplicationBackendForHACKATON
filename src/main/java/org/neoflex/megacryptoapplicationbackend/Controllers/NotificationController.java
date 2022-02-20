package org.neoflex.megacryptoapplicationbackend.Controllers;

import org.neoflex.megacryptoapplicationbackend.Models.NotificationsData;
import org.neoflex.megacryptoapplicationbackend.Models.SubscribeRequestDTO;
import org.neoflex.megacryptoapplicationbackend.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class NotificationController {
    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notification/subscribe")
    public void register(@RequestBody SubscribeRequestDTO subscribeRequestDTO, Principal principal) {
        notificationService.subscribe(subscribeRequestDTO, principal);

    }

    @DeleteMapping("/notification/unsubscribe")
    public void unsubscribe(@RequestBody SubscribeRequestDTO subscribeRequestDTO, Principal principal) {
        notificationService.unsubscribe(subscribeRequestDTO, principal);

    }

    @GetMapping("/notification")
    public NotificationsData getNotifies(Principal principal) {
        return notificationService.notificationsData(principal);
    }
}
