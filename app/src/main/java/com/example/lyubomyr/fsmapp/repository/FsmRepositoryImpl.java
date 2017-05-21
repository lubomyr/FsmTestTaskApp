package com.example.lyubomyr.fsmapp.repository;

import android.content.Context;

import com.example.lyubomyr.fsmapp.R;
import com.example.lyubomyr.fsmapp.entity.Fsm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FsmRepositoryImpl implements FsmRepository {
    private List<Fsm> fsmList;
    private String currentState;

    public FsmRepositoryImpl(List<Fsm> fsmList) {
        this.fsmList = fsmList;
    }

    @Override
    public void setAction(String action) {
        for (Fsm fsm : fsmList) {
            if (action.equals(fsm.getAction()) && currentState.equals(fsm.getStartState())) {
                currentState = fsm.getEndState();
            }
        }
    }

    @Override
    public String getCurrentState() {
        return currentState;
    }

    @Override
    public void setState(String state) {
        this.currentState = state;
    }
}
