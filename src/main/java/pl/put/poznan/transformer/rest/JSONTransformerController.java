package pl.put.poznan.transformer.rest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * this field will contain reference to object of JSONMinified class, which has methods needed
     * to minify JSON.
     */
    private static Component component = new JSONComponent();

    /**
     * Method allows API user to minify JSON which he uploaded as request's argument.
     *
     * @param file MultipleFile containing information about sent file.
     * @return Method returns String containing minified form of it.
     * If file isn't correct JSON, method returns string with warning message that uploaded JSON is not correct.
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
     * Method allows API user to deminify JSON uploaded as request's argument.
     *
     * @param file MultipartFile object containing information about sent file.
     * @return Method returns String with deminified form of uploaded JSON.
     * If file isn't correct JSON, method returns string with warning message that uploaded JSON is not correct.
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
     * Method allows API user to see differences between two text files uploaded as arguments of this request.
     *
     * @param files MultipleFile array with two sent files.
     * @return String containing information about differences in both files (or lack of them).
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

        component = new JSONCompare(component);

        logger.debug("Starting comparison");
        logger.debug("Compared successfully");
        try {
            return component.operation(str1, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method allows API user to see what is file size difference between uploaded JSON file and minified version of it.
     *
     * @param file JSON file which will be analyzed.
     * @return String containing information about size difference between JSON file and its minified version.
     */
    @PostMapping("/checkSizeDifference")
    public String checkSizeAfterMinification(@RequestParam("file") MultipartFile file) {
        String str;
        try {
            str = FileStorageService.storeFile(file);
        } catch (FileStorageException e) {
            return e.getMessage();
        }

        String result;
        logger.debug("Starting counting file size difference after deminification");

        component = new JSONAnalyzer(component);
        try {
            result = component.operation(str);
            logger.debug("Counted successfully");
            return result;
        } catch (JSONException e) {
            logger.error(e.getMessage());
            component = null;
            return "Wrong JSON structure\n";
        }
    }

    /**
     * Method allows API user to remove from JSON uploaded in first request's parameter properties with keys passed in second request's parameter.
     *
     * @param file JSON file which will be analyzed.
     * @param input String containing comma-delimited keys of properties, which user wants to remove.
     * @return String containing JSON content with removed properties which are identified by keys given in second request's parameter.
     */
    @PostMapping("/reduce")
    public String reduce(@RequestParam("file") MultipartFile file, @RequestParam("text") String input) {

        String str;
        try {
            str = FileStorageService.storeFile(file);
        } catch (FileStorageException e) {
            return e.getMessage();
        }

        String result;

        component = new JSONReduced(component);
        try {
            result = component.operation(str, input);
            logger.debug("Reduced successfully");
            return result;
        } catch (JSONException e) {
            logger.error(e.getMessage());
            component = null;
            return "Wrong JSON structure\n";
        }

    }

<<<<<<< HEAD
    public String compareTest(String str1, String str2, JSONCompare component) {
        return component.operation(str1, str2);
=======
    @PostMapping("/choose")
    public String choose(@RequestParam("file") MultipartFile file, @RequestParam("text") String input) {

        String str;
        try {
            str = FileStorageService.storeFile(file);
        } catch (FileStorageException e) {
            return e.getMessage();
        }

        String result;

        component = new JSONChoose(component);
        try {
            result = component.operation(str, input);
            logger.debug("Choosen successfully");
            return result;
        } catch (JSONException e) {
            logger.error(e.getMessage());
            component = null;
            return "Wrong JSON structure\n";
        }

>>>>>>> b1ff26a58e1e1e458ec2f35eed84fb47fbf0f9b6
    }

}


