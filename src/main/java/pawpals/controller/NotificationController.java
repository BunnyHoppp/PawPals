package pawpals.controller;

import pawpals.entity.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    // In-memory list to store notifications
    private final List<Notification> notifications = new ArrayList<>();

    // Endpoint to get all notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notifications;
    }

    // Endpoint to create a new notification
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        notifications.add(notification); // Add notification to the list
        return notification;  // Return the created notification
    }

    // Endpoint to get notifications by owner
    @GetMapping("/owner/{owner}")
    public List<Notification> getNotificationsByOwner(@PathVariable String owner) {
        List<Notification> result = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getOwner().equals(owner)) {
                result.add(notification);
            }
        }
        return result;
    }
}
