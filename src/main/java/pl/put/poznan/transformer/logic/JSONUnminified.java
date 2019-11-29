package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONUnminified extends Decorator {

    public JSONUnminified(Component component) {
        super(component);
    }

    public String unminify(String text) throws JSONException {
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

    @Override
    public void Operation() throws JSONException {
        newDecoration = unminify(component.getJsonString());
    }

    @Override
    public String Compare(Component component) {
        return null;
    }
}
