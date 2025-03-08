package pawpals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pawpals.entity.User;
import pawpals.storage.JsonStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final JsonStorage jsonStorage;
    private final AtomicLong counter = new AtomicLong();

    public UserController(JsonStorage jsonStorage) {
        this.jsonStorage = jsonStorage;
    }

    // Get all users
    @GetMapping
    public List<User> getUsers() {
        return jsonStorage.readUsers();
    }

    // Add a new user
    @PostMapping
    public User addUser(@RequestBody User user) {
        List<User> users = jsonStorage.readUsers();
        users.add(user);
        jsonStorage.writeUsers(users);
        logger.info("User added " + user.getUsername());
        return user;
    }

    // Get user by ID
    @GetMapping("/{username}")
    public User getUserByName(@PathVariable String username) {
        List<User> users = jsonStorage.readUsers();
        Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        return user.orElse(null);
    }

    // Delete user by ID
    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username) {
        List<User> users = jsonStorage.readUsers();
        users.removeIf(u -> u.getUsername().equals(username));
        jsonStorage.writeUsers(users);
        return "User deleted successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> loginUser(@RequestBody User user) {
        List<User> users = jsonStorage.readUsers();

        Optional<User> returnedUser = users.stream()
                .filter(u -> u.getUsername().trim().equals(user.getUsername().trim())
                        && u.getPassword().trim().equals(user.getPassword().trim()))
                .findFirst();

        logger.info("Login Attempt created" + user);
        logger.info("returned user" + returnedUser);

        // Check if the user is present in the list
        if (returnedUser.isPresent()) {
            return ResponseEntity.ok(returnedUser);  // Return the authenticated user
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // Return 401 Unauthorized if authentication fails
        }
    }


}
