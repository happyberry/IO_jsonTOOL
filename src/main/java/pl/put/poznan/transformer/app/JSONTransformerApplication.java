package pl.put.poznan.transformer.app;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.JSONMinifier;
import pl.put.poznan.transformer.logic.JSONTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class JSONTransformerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JSONTransformerApplication.class, args);
        Logger logger = LoggerFactory.getLogger(JSONTransformerApplication.class);
        String[] stringArray = null;
        JSONTransformer transformer = new JSONTransformer("{\n" +
                "    \"glossary\": {\n" +
                "        \"title\": \"example glossary\",\n" +
                "\t\t\"GlossDiv\": {\n" +
                "            \"title\": \"S\",\n" +
                "\t\t\t\"GlossList\": {\n" +
                "                \"GlossEntry\": {\n" +
                "                    \"ID\": \"SGML\",\n" +
                "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
                "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
                "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
                "\t\t\t\t\t\"GlossDef\": {\n" +
                "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
                "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
                "                    },\n" +
                "\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", stringArray, logger);
        JSONMinifier minifier = new JSONMinifier();
        String minifiedJSON = minifier.minify(transformer.jsonArray);
        FileWriter writer = null;
        try {
            writer = new FileWriter("example.json");
            writer.write(minifiedJSON);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
