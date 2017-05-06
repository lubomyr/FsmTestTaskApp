package com.example.lyubomyr.fsmapp.repository;

public interface FsmRepository {
    String getCurrentState();

    String getStateByAction(String action);
}
