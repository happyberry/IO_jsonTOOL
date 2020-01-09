package pl.put.poznan.transformer.rest;

import org.junit.Test;
import pl.put.poznan.transformer.logic.JSONCompare;
import pl.put.poznan.transformer.rest.JSONTransformerController.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class JSONTransformerControllerTest {
    private JSONTransformerController tester = new JSONTransformerController();
    @Test
    public void testCompareBothEmpty() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation("", "")).thenReturn("Identical");
        String result = tester.compareTest("", "", mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Identical");
    }
    @Test
    public void testCompareFirstEmpty() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation("Test", "")).thenReturn("Different");
        String result = tester.compareTest("Test", "", mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Different");
    }
    @Test
    public void testCompareSecondEmpty() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation("", "Test")).thenReturn("Different");
        String result = tester.compareTest("", "Test", mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Different");
    }
    @Test
    public void testCompareIdentical() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation("Test", "Test")).thenReturn("Identical");
        String result = tester.compareTest("Test", "Test", mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Identical");
    }
    @Test
    public void testCompareDifferent() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation("Test", "tseT")).thenReturn("Different");
        String result = tester.compareTest("Test", "tseT", mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Different");
    }
    @Test
    public void testCompareBothNull() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation(null, null)).thenReturn("Identical");
        String result = tester.compareTest(null, null, mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Identical");
    }
    @Test
    public void testCompareFirstNull() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation(null, "test")).thenReturn("Different");
        String result = tester.compareTest(null, "test", mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Different");
    }
    @Test
    public void testCompareSecondNull() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation("test", null)).thenReturn("Different");
        String result = tester.compareTest("test",null, mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Different");
    }
    @Test
    public void testCompareText1() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation("tester", "test")).thenReturn("Different");
        String result = tester.compareTest("tester","test", mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Different");
    }
    @Test
    public void testCompareText2() {
        JSONCompare mockObject = mock(JSONCompare.class);
        when(mockObject.operation("qwerty", "zxcvb")).thenReturn("Different");
        String result = tester.compareTest("qwerty", "zxcvb", mockObject);
        verify(mockObject).operation(anyString(), anyString());
        assertEquals(result, "Different");
    }

}