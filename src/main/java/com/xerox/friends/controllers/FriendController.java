package com.xerox.friends.controllers;

import com.xerox.friends.model.Friend;
import com.xerox.friends.services.FriendService;
import com.xerox.friends.utils.FriendNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    Friend create(@RequestBody Friend friend)
    {
        return friendService.save(friend);
    }

    @GetMapping("/friend")
    Iterable<Friend> read()
    {
        return friendService.findAll();
    }

    @GetMapping("/friend/{id}")
    Optional<Friend> findById(@PathVariable("id") @NumberFormat(style = NumberFormat.Style.NUMBER) Integer id)
            throws FriendNotFoundException
    {
        return friendService.findById(id);
    }

    @GetMapping("/friend/search")
    Iterable<Friend> findByQuery(@RequestParam(value = "first", required = false) String firstName, @RequestParam(value = "last", required = false) String lastName)
            throws FriendNotFoundException
    {
       return friendService.findByQuery(firstName, lastName);

    }

    @PutMapping("/friend")
     Friend update(@RequestBody Friend friend)
            throws FriendNotFoundException
    {
       return friendService.updateFriend(friend);

    }

    @DeleteMapping("/friend/{id}")
    void delete(@PathVariable("id") @NumberFormat(style = NumberFormat.Style.NUMBER) Integer id)
            throws FriendNotFoundException
    {
        friendService.deleteById(id);
    }
}
