package com.aj.grade.gradeentries.exception;

public class ResourseNotFoundException extends Exception {
    public ResourseNotFoundException(String recourse) {
        super(recourse + " Not Found !");
    }
}
