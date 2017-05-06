package com.example.lyubomyr.fsmapp;

import com.example.lyubomyr.fsmapp.repository.FsmRepository;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class MockitoTest extends TestCase {

    @Test
    public void getCurrentStateTest() {
        String str = "some state";
        FsmRepository fsmRepository = mock(FsmRepository.class);
        when(fsmRepository.getCurrentState()).thenReturn(str);
        String state = fsmRepository.getCurrentState();
        assertEquals(str, state);
    }

    @Test
    public void getStateByActionTest() {
        FsmRepository fsmRepository = mock(FsmRepository.class);
        String startState = "start state";
        String endState = "end state";
        when(fsmRepository.getStateByAction(startState)).thenReturn(endState);
        String state = fsmRepository.getStateByAction(startState);
        assertEquals(state, endState);
    }
}
