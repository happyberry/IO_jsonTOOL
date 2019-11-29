package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Class containing all the logic and methods needed to deminify JSONs.
 *
 * @author Julia Tadej
 * @version 1.0
 */

public class JSONUnminified extends Decorator {

    /**
     * Constructor, calls constructor from component passed in constructor's argument.
     * @param component Component from which constructor will be called inside this constructor.
     */
    public JSONUnminified(Component component) {
        super(component);
    }

    /**
     * Method which does the deminification of the JSON.
     *
     * @param text String containing the JSON.
     * @return String with deminified form of the JSON.
     * @throws JSONException Exception thrown if String doesn't contain correct JSON.
     */
    public String unminify(String text) throws JSONException {
        JSONArray array = JSONTransformer.transform(text);
        String unminified = null;

        unminified = array.toString(4);

        unminified = unminified.substring(1, unminified.length() - 1);
        return unminified;
    }


    /**
     * Method derived from Component interface, wrapper for minify method.
     *
     * @throws JSONException Exception thrown if String doesn't contain correct JSON.
     */
    @Override
    public void Operation() throws JSONException {
        newDecoration = unminify(component.getJsonString());
    }

    /**
     * Stub method derived from Component interface, not needed in this class, so it just returns null.
     *
     * @param component Component passed to method just to make it compatible with interface which is implemented by
     * this class.
     */
    @Override
    public String Compare(Component component) {
        return null;
    }
}
