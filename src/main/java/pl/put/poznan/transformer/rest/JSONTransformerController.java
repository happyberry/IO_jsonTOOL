package pl.put.poznan.transformer.rest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

/**
 * REST API controller class, includes implementation of all HTTP requests which are supported by API.
 *
 * @author kejkejovsky
 * @version 1.0
 */

@RestController
public class JSONTransformerController {

    /**
     * Logger object used for logging purposes on DEBUG and INFO level.
     */
    private static final Logger logger = LoggerFactory.getLogger(JSONTransformerController.class);

    /**
     * Container for object which will be used in response to request. For example in minify method,
     * this field will contain reference to object of JSONMinifed class, which has methods needed
     * to minify JSON.
     */
    private Component component = null;

    /**
     * Add method, allows for adding a JSON/text for further actions.
     *
     * @param array Request body - String containing JSON or plain text which will be converted in other methods.
     */

    @PostMapping("/dodaj")
    public void add(@RequestBody(required = false) String array) {
        component = new JSONComponent(array);
        logger.debug("Created JSONComponent object");
        logger.info("Uploaded new JSON/txt");
    }

    /**
     * Method allows API user to see JSON/text which is uploaded to API at the moment.
     *
     * @return If JSON/text is uploaded to API at the moment, method returns String containing it.
     * Otherwise method returns String with information that user should upload JSON/text first.
     */

    @GetMapping("/getAll")
    public String getAll() {
        if (component != null) {
            logger.debug("Showing uploaded JSON/txt");
            return component.getJsonString();
        }
        logger.debug("Attempting to print before uploading");
        return "Najpierw dodaj JSONa\n";
    }

    /**
     * Method allows API user to minify JSON which he uploaded earlier using POST request.
     *
     * @return If JSON is uploaded to API at the moment, method returns String containing minified form of it.
     * If there is text or nothing uploaded, method returns String with information that user should upload JSON first.
     */

    @GetMapping("/minify")
    public String minify() {
        logger.debug("Starting minification of uploaded JSON/txt");
        if (component != null) {
            component = new JSONMinified(component);
            try {
                component.Operation();
            } catch (JSONException e) {
                logger.error(e.getMessage());
                component = null;
                return "Zły format JSONa, dodaj nowy JSON\n";
            }
            logger.debug("Minified successfully");
            return component.getJsonString();
        }
        logger.debug("Attempting to minify before uploading");
        return "Najpierw dodaj JSONa\n";
    }

    /**
     * Method allows API user to deminify JSON which he uploaded earlier using POST request.
     *
     * @return If JSON is uploaded to API at the moment, method returns String with deminified form of uploaded JSON.
     * If there is text or nothing uploaded, method returns String with information that user should upload JSON first.
     */

    @GetMapping("/unminify")
    public String unminify() {
        logger.debug("Starting deminification of uploaded JSON/txt");
        if (component != null) {
            component = new JSONUnminified(component);
            try {
                component.Operation();
            } catch (JSONException e) {
                logger.error(e.getMessage());
                component = null;
                return "Zły format JSONa, dodaj nowy JSON\n";
            }
            logger.debug("Unminified successfully");
            return component.getJsonString();
        }
        logger.debug("Attempting to unminify before uploading");
        return "Najpierw dodaj JSONa\n";
    }

    /**
     * Method allows API user to see differences between uploaded text with POST request and
     * text he uploads in this PUT request.
     *
     * @param ob2 String with text which will be compared with text uploaded in PUT request.
     * @return If JSON/text is uploaded to API at the moment, method returns String containing list of lines with differences between texts.
     * Otherwise method returns String with information that user should upload JSON/text first.
     */

    @PutMapping("/compare")
    public String compare(@RequestBody(required = false) String ob2) {
        logger.debug("Starting comparison");
        if (component != null) {
            component = new JSONCompare(component, logger);
            logger.debug("Compared successfully");
            return component.Compare(new JSONComponent(ob2));
        }
        logger.debug("Attempting to compare before uploading");
        return "Najpierw dodaj JSONa\n";
    }
}


