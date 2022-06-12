package com.exam.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User With this UserName Not found in  DataBase !! ");
    }
    public UserNotFoundException(String msg){ super(msg);}
}
