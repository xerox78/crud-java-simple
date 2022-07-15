package com.xerox.friends;

import com.xerox.friends.controllers.FriendController;
import com.xerox.friends.model.Friend;
import com.xerox.friends.services.FriendService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MockMvc.class)
@Import(FriendController.class)
public class StandaloneControllerTests {

    @MockBean
    FriendService friendService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test_get_001() throws Exception {
        Friend friend = new Friend("Aaron", "Ramsey");
        List<Friend> friends = List.of(friend);

        Mockito.when(friendService.findAll()).thenReturn(friends);

        mockMvc.perform(get("/friend"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Aaron")));
    }

    @Test
    public void test_get_002() throws Exception {
        mockMvc.perform(get("/frd"))
                .andExpect(status().is(404));
    }

    @Test
    public void test_get_003() throws Exception {
        Mockito.when(friendService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/friend"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void test_getById_001() throws Exception {
        Friend friend = new Friend("Aaron", "Ramsey");
        friend.setId(1);

        Mockito.when(friendService.findById(1)).thenReturn(Optional.of(friend));

        mockMvc.perform(get("/friend/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", Matchers.is("Aaron")));
    }

    @Test
    public void test_getById_002() throws Exception {
        Friend friend = new Friend("Aaron", "Ramsey");
        friend.setId(1);

        Mockito.when(friendService.findById(1)).thenReturn(Optional.of(friend));

        mockMvc.perform(get("/friend/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("null"));
    }


}
