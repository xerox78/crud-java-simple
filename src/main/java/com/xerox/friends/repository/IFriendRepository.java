package com.xerox.friends.repository;

import com.xerox.friends.model.Friend;
import org.springframework.data.repository.CrudRepository;

public interface IFriendRepository extends CrudRepository<Friend, Integer> {

    Iterable<Friend> findByFirstNameAndLastName( String firstName, String lastName);
    Iterable<Friend> findByFirstName( String firstName);
    Iterable<Friend> findByLastName(String lastName);
}
