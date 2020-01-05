package pl.put.poznan.transformer.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import org.json.JSONException;

public class JSONAnalyzerTest {

    private static Component component = new JSONComponent();

    @Test
    public void testAnalyzeOnMinified() {
        JSONAnalyzer anal = new JSONAnalyzer(component);
        String result = null;
        try {
            result = anal.analyze("{\"Hello\":\"World\"}");
        }
        catch (JSONException e) {
            System.out.println("EXCEPTION");
        }
        assertEquals("After minification file will be 0 Bytes smaller", result);
    }

    @Test
    public void testAnalyzeOnNotMinified() {
        JSONAnalyzer anal = new JSONAnalyzer(component);
        String result = null;
        String json = "{\"Hello\": \"World\",\n\"name\": \"sun1\"\n}";
        try {
            result = anal.analyze(json);
        }
        catch (JSONException e) {
            System.out.println("EXCEPTION");
        }
        assertEquals("After minification file will be 4 Bytes smaller", result);
    }

    @Test(expected = JSONException.class)
    public void testAnalyzeOnJSONWithoutComma() throws JSONException {
        JSONAnalyzer anal = new JSONAnalyzer(component);
        String result = null;
        String json = "{\"Hello\": \"World\"\n\"name\": \"sun1\"\n}";
        result = anal.analyze(json);
    }

    @Test(expected = JSONException.class)
    public void testAnalyzeOnJSONWithoutOpeningCurlyBracket() throws JSONException {
        JSONAnalyzer anal = new JSONAnalyzer(component);
        String result = null;
        String json = "\"Hello\": \"World\"\n\"name\": \"sun1\"\n}";
        result = anal.analyze(json);
    }

    @Test(expected = JSONException.class)
    public void testAnalyzeOnJSONWithoutClosingCurlyBracket() throws JSONException {
        JSONAnalyzer anal = new JSONAnalyzer(component);
        String result = null;
        String json = "{\"Hello\": \"World\"\n\"name\": \"sun1\"\n";
        result = anal.analyze(json);
    }

    @Test(expected = JSONException.class)
    public void testAnalyzeOnJSONWithRedundantCurlyBracket() throws JSONException {
        JSONAnalyzer anal = new JSONAnalyzer(component);
        String result = null;
        String json = "{{\"Hello\": \"World\"\n\"name\": \"sun1\"\n}}";
        result = anal.analyze(json);
    }

    @Test(expected = JSONException.class)
    public void testAnalyzeOnJSONWithDifferentNumberOfOpeningAndClosingCurlyBrackets() throws JSONException {
        JSONAnalyzer anal = new JSONAnalyzer(component);
        String result = null;
        String json = "{\"Hello\": \"World\"\n\"name\": \"sun1\"\n}}";
        result = anal.analyze(json);
    }
}