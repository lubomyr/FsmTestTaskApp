package com.example.lyubomyr.fsmapp.repository;

import android.content.Context;

import com.example.lyubomyr.fsmapp.R;
import com.example.lyubomyr.fsmapp.entity.Fsm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by lyubomyr on 16.05.2017.
 */

@RunWith(JUnit4.class)
public class FsmRepositoryImplTest {
    private FsmRepository fsmRepository;

    @Before
    public void setUp() throws Exception {
        List<Fsm> fsmList = new ArrayList<>();
        String action = "action";
        String startState = "startState";
        String endState = "endState";
        fsmList.add(new Fsm(action, startState, endState));
        fsmRepository = new FsmRepositoryImpl(fsmList);
    }

    @Test
    public void setAction() throws Exception {
        String action = "action";
        String startState = "startState";
        String endState = "endState";
        fsmRepository.setState(startState);
        fsmRepository.setAction(action);
        String actual = fsmRepository.getCurrentState();
        assertEquals(actual, endState);
    }

    @Test
    public void setState() throws Exception {
        String expected = "initialState";
        fsmRepository.setState(expected);
        String actual = fsmRepository.getCurrentState();
        assertEquals(actual, expected);
    }

    @Test
    public void getCurrentState() throws Exception {
        String expected = "initialState";
        fsmRepository.setState(expected);
        String actual = fsmRepository.getCurrentState();
        assertEquals(actual, expected);
    }

}