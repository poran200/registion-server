package com.aj.grade.gradeentries.exception;

public class ResourceAlreadyExistException extends Exception {
    public ResourceAlreadyExistException(String recourse) {
        super(recourse + " Already Exist ! ");
    }
}
