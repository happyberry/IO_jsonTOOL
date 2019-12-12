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

    /**
     * Container for object which will be used in response to request. For example in minify method,
     * this field will contain reference to object of JSONMinifed class, which has methods needed
     * to minify JSON.
     */
    private static Component component = new JSONComponent();

    /**
     * Method allows API user to minify JSON which he uploaded earlier using POST request.
     *
     * @param file MultipleFile containing information abut sent file.
     * @return If JSON is uploaded to API at the moment, method returns String containing minified form of it.
     * If there is text or nothing uploaded, method returns String with information that user should upload JSON first.
     */

    @PostMapping("/minify")
    public String minify(@RequestParam("file") MultipartFile file) {


        String str;
        try {
            str = FileStorageService.storeFile(file);
        } catch (FileStorageException e) {
            return e.getMessage();
        }

        String result;
        logger.debug("Starting minification of uploaded JSON/txt");

         component = new JSONMinified(component);
         try {
             result = component.operation(str);
             logger.debug("Minified successfully");
             return result;
         } catch (JSONException e) {
             logger.error(e.getMessage());
             component = null;
             return "Wrong JSON structure\n";
         }

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


        String str;
        try {
            str = FileStorageService.storeFile(file);
        } catch (FileStorageException e) {
            return e.getMessage();
        }

        String result;
        logger.debug("Starting deminification of uploaded JSON/txt");

        component = new JSONUnminified(component);
        component = new JSONUnminified(component);
        try {
            result = component.operation(str);
            logger.debug("Unminified successfully");
            return result;
        } catch (JSONException e) {
            logger.error(e.getMessage());
            component = null;
            return "Wrong JSON structure\n";
        }

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
        if (files.length != 2) return "Wrong quantity of files\n";

        String str1;
        String str2;
        try {
            str1 = FileStorageService.storeFile(files[0]);
        } catch (FileStorageException e) {
            return e.getMessage();
        }
        try {
            str2 = FileStorageService.storeFile(files[1]);
        } catch (FileStorageException e) {
            return e.getMessage();
        }

        component = new JSONCompare(component, logger);

        logger.debug("Starting comparison");
        logger.debug("Compared successfully");
        return component.compare(str1, str2);

    }


}


