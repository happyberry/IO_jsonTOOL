package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONUnminifier {
    public String unminify(JSONArray array) throws JSONException {
        String unminified = array.toString(4);
        unminified = unminified.substring(1, unminified.length() - 1);
        return unminified;
    }
}
