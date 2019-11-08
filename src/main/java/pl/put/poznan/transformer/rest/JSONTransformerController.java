package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.JSONTransformer;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class JSONTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(JSONTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper,escape") 
String[] transforms) {

	//System.out.println(transforms);
        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
	//System.out.println(transforms);

        // do the transformation, you should run your logic here, below just a silly example
        JSONTransformer transformer = new JSONTransformer(transforms);
        return transformer.transform(text);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {

	//System.out.println(transforms);
        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
	//System.out.println(transforms);

        // do the transformation, you should run your logic here, below just a silly example
        JSONTransformer transformer = new JSONTransformer(transforms);
        return transformer.transform(text);
    }



}


