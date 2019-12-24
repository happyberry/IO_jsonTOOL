package pl.put.poznan.transformer.logic;

public abstract class Decorator implements Component {

    protected final Component component;

    public Decorator(Component component) {
        this.component = component;
    }



}
