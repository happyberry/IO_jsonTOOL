package pl.put.poznan.transformer.logic;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.transformer.logic.JSONComponent;
import pl.put.poznan.transformer.logic.JSONMinified;

import static org.junit.Assert.*;

public class JSONMinifiedTest {

    private JSONMinified minified;

    @Before
    public void beforeTests(){
        minified = new JSONMinified(new JSONComponent());
    }

    @Test
    public void testMinifyEmpty() throws JSONException {
        assertEquals(minified.minify(""), "");

    }

    @Test
    public void testMinifyMinified() throws JSONException {
        assertEquals(minified.minify("{\"glossary\":{\"title\":\"example glossary\",\"GlossDiv\":{\"GlossList\":{\"GlossEntry\":{\"GlossTerm\":\"Standard Generalized Markup Language\",\"GlossSee\":\"markup\",\"SortAs\":\"SGML\",\"GlossDef\":{\"para\":\"A meta-markup language, used to create markup languages such as DocBook.\",\"GlossSeeAlso\":[\"GML\",\"XML\"]},\"ID\":\"SGML\",\"Acronym\":\"SGML\",\"Abbrev\":\"ISO 8879:1986\"}},\"title\":\"S\"}}}"),
                "{\"glossary\":{\"title\":\"example glossary\",\"GlossDiv\":{\"GlossList\":{\"GlossEntry\":{\"GlossTerm\":\"Standard Generalized Markup Language\",\"GlossSee\":\"markup\",\"SortAs\":\"SGML\",\"GlossDef\":{\"para\":\"A meta-markup language, used to create markup languages such as DocBook.\",\"GlossSeeAlso\":[\"GML\",\"XML\"]},\"ID\":\"SGML\",\"Acronym\":\"SGML\",\"Abbrev\":\"ISO 8879:1986\"}},\"title\":\"S\"}}}");
    }

    @Test
    public void testMinifyUnminified() throws JSONException {
        assertEquals(minified.minify("{\"glossary\": {\n" +
                "    \"title\": \"example glossary\",\n" +
                "    \"GlossDiv\": {\n" +
                "        \"GlossList\": {\"GlossEntry\": {\n" +
                "            \"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "            \"GlossSee\": \"markup\",\n" +
                "            \"SortAs\": \"SGML\",\n" +
                "            \"GlossDef\": {\n" +
                "                \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
                "                \"GlossSeeAlso\": [\n" +
                "                    \"GML\",\n" +
                "                    \"XML\"\n" +
                "                ]\n" +
                "            },\n" +
                "            \"ID\": \"SGML\",\n" +
                "            \"Acronym\": \"SGML\",\n" +
                "            \"Abbrev\": \"ISO 8879:1986\"\n" +
                "        }},\n" +
                "        \"title\": \"S\"\n" +
                "    }\n" +
                "}}"),
                "{\"glossary\":{\"title\":\"example glossary\",\"GlossDiv\":{\"GlossList\":{\"GlossEntry\":{\"GlossTerm\":\"Standard Generalized Markup Language\",\"GlossSee\":\"markup\",\"SortAs\":\"SGML\",\"GlossDef\":{\"para\":\"A meta-markup language, used to create markup languages such as DocBook.\",\"GlossSeeAlso\":[\"GML\",\"XML\"]},\"ID\":\"SGML\",\"Acronym\":\"SGML\",\"Abbrev\":\"ISO 8879:1986\"}},\"title\":\"S\"}}}");
    }

}