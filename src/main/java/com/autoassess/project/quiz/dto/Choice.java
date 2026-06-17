package com.autoassess.project.quiz.dto;

//A DTO is basically just a Java object in memory used to hold data temporarily.
public class Choice {

    private int index;
    private ResponseMessage message;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ResponseMessage getMessage() {
        return message;
    }

    public void setMessage(ResponseMessage message) {
        this.message = message;
    }
}