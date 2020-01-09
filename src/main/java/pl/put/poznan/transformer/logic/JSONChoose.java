package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Class containing all the logic and methods needed to remove all properties excluding chosen ones from the JSON file.
 *
 * @author Kejkejovsky
 * @version 1.0
 */

public class JSONChoose extends Decorator {

    /**
     * Constructor, calls constructor from component passed in constructor's argument.
     * @param component Component from which constructor will be called inside this constructor.
     */
    public JSONChoose(Component component) {super(component);}

    private void chooseNode(JsonNode currentNode, String[] properties) {
        if (currentNode instanceof ObjectNode) {
            Iterator<String> names = currentNode.fieldNames();
            List<String> delete = new ArrayList<String>();
            while(names.hasNext()) {
                String name = names.next();

                if(!Arrays.asList(properties).contains(name)) {
                    delete.add(name);
                }
            }
            if(!delete.isEmpty()) {
                ((ObjectNode) currentNode).remove(delete);
            }
        }
        if (currentNode.isContainerNode()) {
            for (JsonNode child : currentNode) {
                chooseNode(child, properties);
            }
        }
    }

    /**
     * Method which converts JSON into tree-like structure so it can be traversed over like a graph.
     * Then it runs chooseNode private method which all properties from JSON excluding ones with keys which got passed
     * in properties parameter and converts result back into the String.
     *
     * @param jsonString String containing the JSON.
     * @param properties list of keys - properties with them will not be removed.
     * @return String with the JSON which contains only properties with given keys.
     * @throws JSONException Exception thrown if jsonString doesn't contain correct JSON.
     */
    public String choose(String jsonString, String[] properties) throws JSONException {
        if (properties == null) properties = new String[]{};
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            chooseNode(jsonNode, properties);
            jsonString = mapper.writeValueAsString(jsonNode);
        } catch (IOException e){
            e.printStackTrace();
        }

        JSONArray array = JSONTransformer.transform(jsonString);
        return array.toString(4);
    }

    /**
     * Stub method derived from Component interface, not needed in this class, so it just returns null.
     */
    @Override
    public String operation(String jsonString) throws JSONException {
        return null;
    }

    /**
     * Method derived from Component interface, wrapper for reduce method.
     *
     * @param jsonString  String containing the JSON file content.
     * @param names String containing keys of properties which will not be removed, delimited with commas.
     * @throws JSONException Exception thrown if String doesn't contain correct JSON.
     * @return String with the JSON containing only elements with keys listed in 'input' parameter.
     */
    @Override
    public String operation(String jsonString, String names) throws JSONException {
        return choose(jsonString, names.split(","));
    }
}
