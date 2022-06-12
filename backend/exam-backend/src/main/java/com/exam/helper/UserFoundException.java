package com.exam.helper;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super("User With this UserName is already there in DB !!Try with deterrent username ");
    }
    public UserFoundException(String msg){ super(msg);}
}
