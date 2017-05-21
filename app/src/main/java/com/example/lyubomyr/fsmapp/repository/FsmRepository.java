package com.example.lyubomyr.fsmapp.repository;

public interface FsmRepository {
    String getCurrentState();

    void setState(String state);

    void setAction(String action);
}
