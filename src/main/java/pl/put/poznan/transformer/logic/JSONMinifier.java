package pl.put.poznan.transformer.logic;

import org.json.JSONArray;

public class JSONMinifier {

    public String minify(String jsonString) {
        return doMinify(JSONTransformer.transform(jsonString));
    }

    private String doMinify(JSONArray array){
        String minified = String.valueOf(array);
        minified = minified.substring(1, minified.length() - 1);
        return minified;
    }

}
