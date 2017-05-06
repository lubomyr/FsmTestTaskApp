package com.example.lyubomyr.fsmapp.entity;

public class Fsm {
    private String action;
    private String startState;
    private String endState;

    public Fsm(String action, String startState, String endState) {
        this.action = action;
        this.startState = startState;
        this.endState = endState;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public String getEndState() {
        return endState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }
}
