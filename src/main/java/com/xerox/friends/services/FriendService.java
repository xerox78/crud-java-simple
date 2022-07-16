package com.xerox.friends.services;

import com.xerox.friends.model.Friend;
import com.xerox.friends.repository.IFriendRepository;
import com.xerox.friends.utils.FriendNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {

    @Autowired
    IFriendRepository friendRepository;

    public Friend save(Friend friend) {
        return friendRepository.save(friend);
    }

    public Iterable<Friend> findAll() {
        return friendRepository.findAll();
    }

    public Optional<Friend> findById(Integer id)
            throws FriendNotFoundException
    {
        Optional<Friend> byId = friendRepository.findById(id);
        if(byId.isPresent())
            return byId;
        else
            throw new FriendNotFoundException("Not Found");
    }

    public Iterable<Friend> findByQuery(String firstName, String lastName)
            throws FriendNotFoundException
    {
        if (firstName != null && lastName != null)
            return friendRepository.findByFirstNameAndLastName(firstName, lastName);
        else if (firstName != null)
            return friendRepository.findByFirstName(firstName);
        else if (lastName != null)
            return friendRepository.findByLastName(lastName);
        else
            throw new FriendNotFoundException("Not Found");
    }

    public Friend updateFriend(Friend friend) throws FriendNotFoundException {
        if (friendRepository.findById(friend.getId()).isPresent())
            return friendRepository.save(friend);
        else
            throw new FriendNotFoundException("Not Found");
    }

    public void deleteById(Integer id) throws FriendNotFoundException {
        if (friendRepository.findById(id).isPresent())
            friendRepository.deleteById(id);
        else
            throw new FriendNotFoundException("Not Found");
    }

}
