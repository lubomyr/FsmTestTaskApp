package com.example.lyubomyr.fsmapp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lyubomyr.fsmapp.entity.Fsm;
import com.example.lyubomyr.fsmapp.repository.FsmRepository;
import com.example.lyubomyr.fsmapp.repository.FsmRepositoryImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FsmRepository fsmRepository;
    private List<Fsm> fsmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (fsmList == null) {
            fsmList = getData();
        }
        fsmRepository = new FsmRepositoryImpl(fsmList);
        final String initialState = getString(R.string.initial_state);
        if (fsmRepository.getCurrentState() == null)
            fsmRepository.setState(initialState);

        bindEvents();

        updateView(fsmRepository.getCurrentState());
    }

    private void updateView(String state) {
        TextView alarmState = (TextView) findViewById(R.id.alarmState);
        state = state.replace("_"," ");
        alarmState.setText(state);
        int color = 0;
        if (state.contains("Disarmed"))
            color = android.R.color.holo_green_dark;
        else if (state.contains("Armed"))
            color = android.R.color.holo_red_dark;
        alarmState.setTextColor(ContextCompat.getColor(this , color));
    }

    private void bindEvents() {
        Button lockBtn = (Button) findViewById(R.id.lockBtn);
        Button unlockBtn = (Button) findViewById(R.id.unlockBtn);
        Button lockx2Btn = (Button) findViewById(R.id.lockx2Btn);
        Button unlockx2Btn = (Button) findViewById(R.id.unlockx2Btn);
        lockBtn.setOnClickListener(this);
        unlockBtn.setOnClickListener(this);
        lockx2Btn.setOnClickListener(this);
        unlockx2Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String action = button.getText().toString();
        fsmRepository.setAction(action);
        String currentState = fsmRepository.getCurrentState();
        updateView(currentState);
    }

    private List<Fsm> getData() {
        final String ACTION_KEY = getString(R.string.action);
        final String START_STATE_KEY = getString(R.string.start_state);
        final String END_STATE_KEY = getString(R.string.end_state);
        List<Fsm> list = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.fsm_json);
        Scanner sc = new Scanner(inputStream).useDelimiter("[\n]");
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
                list.add(new Fsm(action, startState, endState));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
