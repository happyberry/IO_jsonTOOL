package pl.put.poznan.transformer.rest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

    private static FileStorageService fileStorageService = new FileStorageService();

    /**
     * Container for object which will be used in response to request. For example in minify method,
     * this field will contain reference to object of JSONMinifed class, which has methods needed
     * to minify JSON.
     */
    private Component component = null;

    private static Decorator decorator = null;

    /**
     * Method allows API user to minify JSON which he uploaded earlier using POST request.
     *
     * @param file MultipleFile containing information abut sent file.
     * @return If JSON is uploaded to API at the moment, method returns String containing minified form of it.
     * If there is text or nothing uploaded, method returns String with information that user should upload JSON first.
     */

    @PostMapping("/minify")
    public String minify(@RequestParam("file") MultipartFile file) {


        String str = null;
        try {
            str = fileStorageService.storeFile(file);
        } catch (FileStorageException e) {
            return e.getMessage();
        }

        component = new JSONMinified(new JSONComponent(str));
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
     * @param file MultipleFile containing information abut sent file.
     * @return If JSON is uploaded to API at the moment, method returns String with deminified form of uploaded JSON.
     * If there is text or nothing uploaded, method returns String with information that user should upload JSON first.
     */

    @PostMapping("/unminify")
    public String unminify(@RequestParam("file") MultipartFile file) {
        String str = null;
        try {
            str = fileStorageService.storeFile(file);
        } catch (FileStorageException e) {
            return e.getMessage();
        }

        component = new JSONComponent(str);
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
     * @param files MultipleFile table containing information about two sent files.
     * @return If JSON/text is uploaded to API at the moment, method returns String containing list of lines with differences between texts.
     * Otherwise method returns String with information that user should upload JSON/text first.
     */

    @PostMapping("/compare")
    public String compare(@RequestParam("file") MultipartFile[] files) {
        if (files.length != 2) return "Zla ilosc plikow\n";

        String str1 = null;
        String str2 = null;
        try {
            str1 = fileStorageService.storeFile(files[0]);
        } catch (FileStorageException e) {
            return e.getMessage();
        }
        try {
            str2 = fileStorageService.storeFile(files[1]);
        } catch (FileStorageException e) {
            return e.getMessage();
        }

        component = new JSONComponent(str1);

        logger.debug("Starting comparison");
        if (component != null) {
            component = new JSONCompare(component, logger);
            logger.debug("Compared successfully");
            return component.Compare(new JSONComponent(str2));
        }
        logger.debug("Attempting to compare before uploading");
        return "Najpierw dodaj JSONa\n";
    }


}


