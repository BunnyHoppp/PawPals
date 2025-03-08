package pawpals.storage;

import pawpals.entity.Notification;
import pawpals.entity.Pet;
import pawpals.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonStorage {
    private static final Logger logger = LoggerFactory.getLogger(JsonStorage.class);

    private final String FILE_PATH_USERS = "data/users.json";
    private final String FILE_PATH_PETS = "data/pets.json";
    private final String FILE_PATH_NOTIFICATIONS = "data/notifications.json";
    private final ObjectMapper userMapper = new ObjectMapper();
    private final ObjectMapper petMapper = new ObjectMapper();
    private final ObjectMapper notificationMapper = new ObjectMapper();

    // Read users from JSON file
    public List<User> readUsers() {
        try {
            File file = new File(FILE_PATH_USERS);
            if (!file.exists()) {
                // If the file doesn't exist, create it
                file.createNewFile();
                logger.warn("Users file not found, creating a new file.");
                return new ArrayList<>();
            }
            return userMapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            logger.error("Error reading users file: ", e);
            return new ArrayList<>();
        }
    }

    // Read pets from JSON file
    public List<Pet> readPets() {
        try {
            File file = new File(FILE_PATH_PETS);
            if (!file.exists()) {
                // If the file doesn't exist, create it
                file.createNewFile();
                logger.warn("Pets file not found, creating a new file.");
                return new ArrayList<>();
            }
            return petMapper.readValue(file, new TypeReference<List<Pet>>() {});
        } catch (IOException e) {
            logger.error("Error reading pets file: ", e);
            return new ArrayList<>();
        }
    }

    // Read notifications from JSON file
    public List<Notification> readNotifications() {
        try {
            File file = new File(FILE_PATH_NOTIFICATIONS);
            if (!file.exists()) {
                // If the file doesn't exist, create it
                file.createNewFile();
                logger.warn("Notifications file not found, creating a new file.");
                return new ArrayList<>();
            }
            return notificationMapper.readValue(file, new TypeReference<List<Notification>>() {});
        } catch (IOException e) {
            logger.error("Error reading pets file: ", e);
            return new ArrayList<>();
        }
    }

    // Write users to JSON file
    public void writeUsers(List<User> users) {
        try {
            File file = new File(FILE_PATH_USERS);
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
                logger.warn("Users file not found, creating a new file.");
            }
            userMapper.writeValue(file, users);
        } catch (IOException e) {
            logger.error("Error writing users to file: ", e);
        }
    }

    // Write pets to JSON file
    public void writePets(List<Pet> pets) {
        try {
            File file = new File(FILE_PATH_PETS);
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
                logger.warn("Pets file not found, creating a new file.");
            }
            petMapper.writeValue(file, pets);
        } catch (IOException e) {
            logger.error("Error writing pets to file: ", e);
        }
    }

    // Write notifications to JSON file
    public void writeNotifications(List<Notification> notifications) {
        try {
            File file = new File(FILE_PATH_NOTIFICATIONS);
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
                logger.warn("Pets file not found, creating a new file.");
            }
            notificationMapper.writeValue(file, notifications);
        } catch (IOException e) {
            logger.error("Error writing pets to file: ", e);
        }
    }

}
