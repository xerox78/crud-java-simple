package com.xerox.friends.utils;

public class FriendNotFoundException extends Exception
{
    public FriendNotFoundException(String errorMessage)
    {
        super(errorMessage);
    }
}
