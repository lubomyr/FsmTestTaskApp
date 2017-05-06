package com.example.lyubomyr.fsmapp;

import android.widget.Button;
import android.widget.TextView;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest extends TestCase {

    @Test
    public void checkButtonsAndStateResultsTest() throws Exception {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);

        Button lockBtn = (Button) activity.findViewById(R.id.lockBtn);
        Button unlockBtn = (Button) activity.findViewById(R.id.unlockBtn);
        Button lockx2Btn = (Button) activity.findViewById(R.id.lockx2Btn);
        Button unlockx2Btn = (Button) activity.findViewById(R.id.unlockx2Btn);
        TextView alarmState = (TextView) activity.findViewById(R.id.alarmState);

        final String resultAlarmDisarmedAllLocked = "AlarmDisarmed AllLocked";
        final String resultAlarmArmedAllLocked = "AlarmArmed AllLocked";
        final String resultAlarmDisarmedAllUnlocked = "AlarmDisarmed AllUnlocked";
        final String resultAlarmDisarmedDriverUnlocked = "AlarmDisarmed DriverUnlocked";

        lockBtn.performClick();
        assertTrue(alarmState.getText().toString().equals(resultAlarmDisarmedAllLocked));
        lockx2Btn.performClick();
        assertTrue(alarmState.getText().toString().equals(resultAlarmArmedAllLocked));
        unlockBtn.performClick();
        assertTrue(alarmState.getText().toString().equals(resultAlarmDisarmedDriverUnlocked));
        unlockx2Btn.performClick();
        assertTrue(alarmState.getText().toString().equals(resultAlarmDisarmedDriverUnlocked));
        lockx2Btn.performClick();
        lockBtn.performClick();
        assertTrue(alarmState.getText().toString().equals(resultAlarmArmedAllLocked));
        unlockx2Btn.performClick();
        assertTrue(alarmState.getText().toString().equals(resultAlarmDisarmedAllUnlocked));
        unlockBtn.performClick();
        assertTrue(alarmState.getText().toString().equals(resultAlarmDisarmedAllUnlocked));
    }
}
