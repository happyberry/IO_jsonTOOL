package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONUnminified extends Decorator {

    public JSONUnminified(Component component) {
        super(component);
    }

    public String unminify(String text) throws JSONException {
        JSONArray array = JSONTransformer.transform(text);
        String unminified = null;

        unminified = array.toString(4);

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
