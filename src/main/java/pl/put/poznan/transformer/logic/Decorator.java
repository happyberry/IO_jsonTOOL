package pl.put.poznan.transformer.logic;

public abstract class Decorator implements Component {

    protected final Component component;
    protected String newDecoration;

    public String getNewDecoration() {
        return newDecoration;
    }

    public Decorator(Component component) {
        this.component = component;
        newDecoration = component.getJsonString();
    }

    @Override
    public String getJsonString() {
        return newDecoration;
    }


}
