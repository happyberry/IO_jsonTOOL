package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONUnminifier {
    public String unminify(String text) {
        return doUnminify(JSONTransformer.transform(text));
    }

    private String doUnminify(JSONArray array) {
        String unminified = null;
        try {
            unminified = array.toString(4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        unminified = unminified.substring(1, unminified.length() - 1);
        return unminified;
    }
}
