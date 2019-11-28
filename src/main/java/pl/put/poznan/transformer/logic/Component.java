package pl.put.poznan.transformer.logic;

public interface Component {

    void Operation();

    String getJsonString();

    String Compare(Component component);
}
