package pawpals.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawpals.entity.Pet;
import pawpals.entity.User;
import pawpals.storage.JsonStorage;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);
    private final JsonStorage jsonStorage;

    // Constructor to inject JsonStorage
    public PetController(JsonStorage jsonStorage) {
        this.jsonStorage = jsonStorage;
    }

    // Endpoint to get all pets
    @GetMapping
    public ResponseEntity<List<Pet>> getPets() {
        List<Pet> pets = jsonStorage.readPets(); // Fetching all pets from the service
        logger.info(pets.toString());
        return new ResponseEntity<>(pets, HttpStatus.OK); // Returning the list of pets with status OK (200)
    }

    // Endpoint to add a new pet
    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        // Validate the incoming pet data
        if (pet.getName() == null || pet.getOwner() == null || pet.getStart() == null || pet.getEnd() == null) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if any field is missing
        }

        // Fetch existing pets, add the new pet and save it
        List<Pet> pets = jsonStorage.readPets();
        pets.add(pet);
        jsonStorage.writePets(pets);

        logger.info("Pet added: " + pet.getName() + " for owner " + pet.getOwner());

        // Return the created pet with 201 Created status
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> removePetByName(@PathVariable("name") String petName) {
        List<Pet> pets = jsonStorage.readPets();

        // Find the pet by name
        Pet petToRemove = pets.stream()
                .filter(pet -> pet.getName().equals(petName))
                .findFirst()
                .orElse(null);

        if (petToRemove != null) {
            pets.remove(petToRemove); // Remove the pet from the list
            jsonStorage.writePets(pets); // Update the JSON file
            logger.info("Pet removed: " + petToRemove.getName());
            return ResponseEntity.noContent().build(); // Return 204 No Content
        }

        return ResponseEntity.notFound().build(); // Return 404 Not Found if pet is not found
    }
}
