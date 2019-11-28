package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

@RestController
public class JSONTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(JSONTransformerController.class);

    private Component component = null;

    @PostMapping("/dodaj")
    public void add(@RequestBody(required = false) String array) {
        component = new JSONComponent(array);
    }

    @GetMapping("/getAll")
    public String getAll() {
        if (component != null)
            return component.getJsonString();
        return "Najpierw dodaj JSONa\n";
    }

    @GetMapping("/minify")
    public String minify() {
        if (component != null) {
            component = new JSONMinified(component);
            component.Operation();
            return component.getJsonString();
        }
        return "Najpierw dodaj JSONa\n";
    }

    @GetMapping("/unminify")
    public String unminify() {
        if (component != null) {
            component = new JSONUnminified(component);
            component.Operation();
            return component.getJsonString();
        }
        return "Najpierw dodaj JSONa\n";
    }

    @PutMapping("/compare")
    public String compare(@RequestBody(required = false) String ob2) {
        if (component != null) {
            component = new JSONCompare(component, logger);
            return component.Compare(new JSONComponent(ob2));
        }
        return "Najpierw dodaj JSONa\n";
    }
}


