package com.dee.memberservice.newException;

public class MemberAlreadyExistException extends Exception {
    public MemberAlreadyExistException(String message) {
        super(message);
    }
}
