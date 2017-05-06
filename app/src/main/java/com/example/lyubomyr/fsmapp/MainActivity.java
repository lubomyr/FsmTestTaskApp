package com.example.lyubomyr.fsmapp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lyubomyr.fsmapp.repository.FsmRepository;
import com.example.lyubomyr.fsmapp.repository.FsmRepositoryImpl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FsmRepository fsmRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fsmRepository = new FsmRepositoryImpl(this);

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
        String currentState = fsmRepository.getStateByAction(action);
        updateView(currentState);
    }
}
