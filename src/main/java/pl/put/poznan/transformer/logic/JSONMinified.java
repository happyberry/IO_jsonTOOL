package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Class containing all the logic and methods needed to minify JSONs.
 *
 * @author Jakub Raczynski
 * @version 1.0
 */

public class JSONMinified extends Decorator {

    /**
     * Constructor, calls constructor from component passed in constructor's argument.
     *
     * @param componentDecorating Component from which constructor will be called inside this constructor.
     */
    public JSONMinified(Component componentDecorating) {
        super(componentDecorating);
    }

    /**
     * Method which does the minification of the JSON.
     *
     * @param jsonString String containing the JSON.
     * @return String with minified form of the JSON.
     * @throws JSONException Exception thrown if String doesn't contain correct JSON.
     */
    public String minify(String jsonString) throws JSONException {
        JSONArray array = JSONTransformer.transform(jsonString);
        String minified = String.valueOf(array);
        minified = minified.substring(1, minified.length() - 1);
        return minified;
    }

    /**
     * Method derived from Component interface, wrapper for minify method.
     *
     * @param jsonString String containing data in JSON format
     * @throws JSONException Exception thrown if String doesn't contain correct JSON.
     */
    @Override
    public String operation(String jsonString) throws JSONException {
        return minify(jsonString);
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
