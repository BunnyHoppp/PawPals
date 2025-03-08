package pawpals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pawpals.entity.Pet;
import pawpals.entity.User;
import pawpals.storage.JsonStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PawPals {

    private static final Logger logger = LoggerFactory.getLogger(PawPals.class);

    public static void main(String[] args) {
        SpringApplication.run(PawPals.class, args);
        logger.info("Application Started");

        JsonStorage jsonStorage = new JsonStorage();

        List<User> users = new ArrayList<>();
        users.add(new User("Alice", "12345678", "password1"));
        users.add(new User("Bob", "87654321", "password2"));
        jsonStorage.writeUsers(users);

        // Creating and adding pets
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("Fluffy", "Alice", LocalDate.of(2025, 3, 1).toString(),
                LocalDate.of(2025, 3, 5).toString()));
        pets.add(new Pet("Buddy", "Bob", LocalDate.of(2025, 3, 5).toString(),
                LocalDate.of(2025, 4, 8).toString()));
        jsonStorage.writePets(pets);

        // Reading and printing users
        List<User> readUsers = jsonStorage.readUsers();
        System.out.println("Users:");
        readUsers.forEach(System.out::println);

        // Reading and printing pets
        List<Pet> readPets = jsonStorage.readPets();
        System.out.println("Pets:");
        readPets.forEach(System.out::println);

    }
}