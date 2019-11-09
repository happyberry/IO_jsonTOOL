package pl.put.poznan.transformer.logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class JSONTransformer {

    public JSONArray jsonArray;
    private final String[] transforms;
    private Logger logger;

    public JSONTransformer(String text, String[] transforms, Logger logger){
        this.transforms = transforms;
        this.logger = logger;

        try {
            jsonArray = new JSONArray("["+text+"]");
            logger.debug("jej"); //TODO: usunac glupie komentarze (pozniej)
        } catch (JSONException e) {
            logger.debug("blad wczytywania do JSONArray");
            //TODO: handle exception
        }
    }
}
