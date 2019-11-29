package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class JSONTransformer {
    private static Logger logger;

    public JSONTransformer(Logger logger) {
        this.logger = logger;
    }

    public static JSONArray transform(String text) throws JSONException {
        return new JSONArray("[" + text + "]");
    }

}
