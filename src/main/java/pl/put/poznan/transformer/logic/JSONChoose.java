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

public class JSONChoose extends Decorator {

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

    @Override
    public String operation(String jsonString) throws JSONException {
        return null;
    }

    @Override
    public String operation(String jsonString, String names) throws JSONException {
        return choose(jsonString, names.split(","));
    }
}
