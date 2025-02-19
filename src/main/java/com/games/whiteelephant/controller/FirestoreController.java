package com.games.whiteelephant.controller;

import org.springframework.web.bind.annotation.*;

import com.games.whiteelephant.model.Game;
import com.games.whiteelephant.model.User;
import com.games.whiteelephant.service.FirestoreService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/firestore")
public class FirestoreController {

    private final FirestoreService firestoreService;

    public FirestoreController(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    /*@PostMapping("/add")
    public String addUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return firestoreService.addDocument("users", user.getEmail(), user);
    }*/

    @GetMapping("/get/{email}")
    public User getUser(@PathVariable String email) throws ExecutionException, InterruptedException {
        return firestoreService.getDocument("users", email, User.class);
    }

    @PostMapping("/addgame")
    public String addGame(@ModelAttribute Game game, String list) {
        try {
            List<Integer> playersList = Arrays.stream(list.split(","))
                    .map(String::trim)  // Remove any spaces
                    .map(Integer::parseInt)  // Convert to integers
                    .collect(Collectors.toList());
            
            // Set the attributes for the game object
            game.setList(playersList);
            // Save the user to Firestore
            firestoreService.addDocument("games", game.getName() , game);
            return "success";  // Redirect to a success page or return a success view
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // Return an error page or view
        }
    }
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        try {
            // Save the user to Firestore
            firestoreService.addDocument("users", user.getEmail(), user);
            return "success";  // Redirect to a success page or return a success view
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // Return an error page or view
        }
    }
}
