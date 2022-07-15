package com.xerox.friends;

import com.xerox.friends.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SystemTests {

    @Test
    public void testCRUD()
    {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/friend";

        Friend friend = new Friend("Aaron", "Ramsey");
        ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);

        Friend[] friends = restTemplate.getForObject(url, Friend[].class);
        Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("Aaron");

        Friend updatedFriend = new Friend("Gordon", "Ramsey");
        updatedFriend.setId(entity.getBody().getId());
        restTemplate.put(url, updatedFriend, Friend.class);

        Friend[] friends2 = restTemplate.getForObject(url, Friend[].class);
        Assertions.assertThat(friends2).extracting(Friend::getFirstName).containsOnly("Gordon");

        restTemplate.delete(url + "/" + entity.getBody().getId());
        Assertions.assertThat(restTemplate.getForObject(url, Friend[].class)).isEmpty();

    }


}
