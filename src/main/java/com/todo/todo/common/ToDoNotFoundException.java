package com.todo.todo.common;

public class ToDoNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "Todo requested not found";
    }

    @Override
    public void printStackTrace() {
        // TODO Auto-generated method stub
        super.printStackTrace();
    }

}
