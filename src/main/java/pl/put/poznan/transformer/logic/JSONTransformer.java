package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class JSONTransformer {
    private Logger logger;
    private JSONArray jsonArray;
    private String jsonText;

    public JSONTransformer(Logger logger) {
        this.logger = logger;
    }

    public void add(String text){
        this.jsonText = text;
        try {
            jsonArray = new JSONArray("["+text+"]");
        } catch (JSONException e) {
            e.printStackTrace();
            logger.debug("jej");
        }
    }
    public JSONArray getAll(){ return jsonArray; }
    public String getText(){ return jsonText; }
    public String wypisz(){ return jsonText; }
    public static JSONArray transform(String text){
        JSONArray arr = null;
        try {
            arr = new JSONArray("["+text+"]");
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return arr;
        }
    }

}
