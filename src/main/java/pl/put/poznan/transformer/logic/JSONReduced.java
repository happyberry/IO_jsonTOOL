package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;

public class JSONReduced extends Decorator {

    public JSONReduced(Component component) {
        super(component);
    }

    private void reduceNode(JsonNode currentNode, String[] properties){
        System.out.println(currentNode.getNodeType());
        if (currentNode instanceof ObjectNode) {
            ((ObjectNode) currentNode).remove(Arrays.asList(properties));
            System.out.println("object");
        }
        if(currentNode.isContainerNode()){
            System.out.println("container");
            for (JsonNode child : currentNode) {
                reduceNode(child, properties);
            }
        }
        /*((ObjectNode)node).remove(Arrays.asList(properties));
        for (JsonNode child : node) {
            //System.out.println(child.get(0));
            if (child.isContainerNode()) {
                reduceNode(child, properties);
            }
        }
        //
        if (currentNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) currentNode;
            Iterator<JsonNode> node = arrayNode.elements();
            while (node.hasNext()) {
                reduceNode(node.next(), properties);
            }
        }
        else{
            ((ObjectNode)currentNode).remove(Arrays.asList(properties));
        }*/

    }

    public String reduce(String jsonString, String[] properties) throws JSONException{

        if (properties == null) properties = new String[]{};
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            reduceNode(jsonNode, properties);
            jsonString = mapper.writeValueAsString(jsonNode);
        } catch (IOException e){
            e.printStackTrace();
        }

        //System.out.println(array.length());
        //System.out.println(array);
        JSONArray array = JSONTransformer.transform(jsonString);
        return array.toString(4);
    }

    @Override
    public String operation(String jsonString) throws JSONException {
        return null;
    }

    @Override
    public String operation(String jsonString, String input) throws JSONException {
        return reduce(jsonString, input.split(","));
    }
}
