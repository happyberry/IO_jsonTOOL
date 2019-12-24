package pl.put.poznan.transformer.logic;

import org.json.JSONException;

public interface Component {

    String operation(String jsonString) throws JSONException;

    String compare(String firstString, String secondString);
}
