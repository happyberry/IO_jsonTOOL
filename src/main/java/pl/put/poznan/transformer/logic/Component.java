package pl.put.poznan.transformer.logic;

import org.json.JSONException;

public interface Component {

    void Operation() throws JSONException;

    String getJsonString();

    String Compare(Component component);
}
