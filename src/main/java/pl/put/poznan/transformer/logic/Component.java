package pl.put.poznan.transformer.logic;

import org.json.JSONException;

public interface Component {

    String operation(String jsonString) throws JSONException;

    String operation(String firstString, String secondString) throws JSONException;
}

