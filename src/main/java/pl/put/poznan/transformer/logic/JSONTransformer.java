package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class JSONTransformer {

    private final String[] transforms;

    public JSONTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        // of course normally it would to something based on transforms
        return text.toUpperCase();
    }
}
