package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.slf4j.Logger;

import java.util.Iterator;
import java.util.Set;


public class JSONCompare {
    private Logger logger;

    public JSONCompare(Logger logger) {
        this.logger = logger;
    }

    public boolean Compare(JSONArray Object1, JSONArray Object2) {
        boolean result = false;
        try {
            JSONObject firstJson = new JSONObject(Object1.toString().substring(1, Object1.toString().length() - 1));
            JSONObject secondJson = new JSONObject(Object2.toString().substring(1, Object2.toString().length() - 1));
            result = firstJson.similar(secondJson);
        } catch (JSONException e) {
            logger.debug("Błąd konwersji JSONArray do JSONObject");
        }
        return result;
    }

}
