package com.example.lyubomyr.fsmapp.repository;

import android.content.Context;

import com.example.lyubomyr.fsmapp.R;
import com.example.lyubomyr.fsmapp.entity.Fsm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FsmRepositoryImpl implements FsmRepository {
    private List<Fsm> fsmList;
    private String currentState;
    private Context context;

    public FsmRepositoryImpl(Context context) {
        this.context = context;

        final String initialState = context.getString(R.string.initial_state);

        if (fsmList == null) {
            getData();
        }

        if (currentState == null) {
            currentState = initialState;
        }

    }

    private void getData() {
        final String ACTION_KEY = context.getString(R.string.action);
        final String START_STATE_KEY = context.getString(R.string.start_state);
        final String END_STATE_KEY = context.getString(R.string.end_state);
        fsmList = new ArrayList<>();
        Scanner sc = new Scanner(context.getResources().openRawResource(R.raw.fsm_json)).useDelimiter("[\n]");
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            sb.append(sc.next()).append("\n");
        }
        sc.close();

        try {
            JSONArray fsmListJson = new JSONArray(sb.toString());
            for (int i = 0; i < fsmListJson.length(); i++) {
                JSONObject model = fsmListJson.getJSONObject(i);
                String action = model.getString(ACTION_KEY);
                String startState = model.getString(START_STATE_KEY);
                String endState = model.getString(END_STATE_KEY);
                fsmList.add(new Fsm(action, startState, endState));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getStateByAction(String action) {
        for (Fsm fsm : fsmList) {
            if (action.equals(fsm.getAction()) && currentState.equals(fsm.getStartState())) {
                currentState = fsm.getEndState();
                return currentState;
            }
        }
        return null;
    }

    @Override
    public String getCurrentState() {
        return currentState;
    }
}
