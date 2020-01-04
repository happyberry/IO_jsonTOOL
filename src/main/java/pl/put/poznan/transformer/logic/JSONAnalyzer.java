package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Class containing all the logic and methods needed to calculate JSON size after minification.
 *
 * @author Jakub Raczynski
 * @version 1.0
 */
public class JSONAnalyzer extends Decorator {

    /**
     * Constructor, calls constructor from component passed in constructor's argument.
     *
     * @param component Component from which constructor will be called inside this constructor.
     */
    public JSONAnalyzer(Component component) {
        super(component);
    }

    /**
     * Method which calculates size of the JSON after minification.
     *
     * @param text String containing contents of the JSON file.
     * @return String with information about size of the JSON after minification.
     * @throws JSONException Exception thrown if String doesn't contain correct JSON.
     */
    public String analyze(String text) throws JSONException {
        JSONArray array = JSONTransformer.transform(text);
        int result = text.length() - String.valueOf(array).length() + 2;
        return "After minification file will be " + result + " Bytes smaller";
    }

    /**
     * Method derived from Component interface, wrapper for analyze method.
     *
     * @param jsonString String containing data in JSON format
     * @throws JSONException Exception thrown if String doesn't contain correct JSON.
     */
    @Override
    public String operation(String jsonString) throws JSONException {
        return analyze(jsonString);
    }

    /**
     * Stub method derived from Component interface, not needed in this class, so it just returns null.
     *
     * @param firstString  String to compare
     * @param secondString String to compare
     */
    @Override
    public String operation(String firstString, String secondString) {
        return null;
    }
}
