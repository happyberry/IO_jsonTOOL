package pl.put.poznan.transformer.logic;

public class JSONComponent implements Component {

    private String jsonString;

    public JSONComponent(String string) {
        jsonString = string;
    }

    @Override
    public void Operation() {

    }

    @Override
    public String getJsonString() {
        return jsonString;
    }

    @Override
    public String Compare(Component component) {
        return null;
    }
}
