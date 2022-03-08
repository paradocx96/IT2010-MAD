package com.sliit.madlab10;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = new MainActivity();
    }

    @Test
    public void celsius_isCorrect() {
        float result = mainActivity.convertToCelsius(100);
        assertEquals(37.77778, result,0.001);
    }

    @Test
    public void fahrenheit_isCorrect() {
        float result = mainActivity.convertToFahrenheit(100);
        assertEquals(212.0, result,0.001);
    }
}