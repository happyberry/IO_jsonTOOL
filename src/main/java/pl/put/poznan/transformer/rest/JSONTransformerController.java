package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

@RestController
public class JSONTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(JSONTransformerController.class);
    private final JSONTransformer arr = new JSONTransformer(logger);
    private final JSONMinifier min = new JSONMinifier();
    private final JSONUnminifier unm = new JSONUnminifier();
    private final JSONCompare cmp = new JSONCompare(logger);

    @GetMapping("/minify")
    public String minify(){
        String x = min.minify(arr.getText());
        return x;
    }
    @GetMapping("/unminify")
    public String unminify(){
        String x = unm.unminify(arr.getText());
        return x;
    }
    @PostMapping("/dodaj")
    public void add(@RequestBody String array){
        arr.add(array);
    }
    @GetMapping("/getAll")
    public String getAll() {
        return arr.wypisz();
    }
    @PutMapping("/compare")
    public String[] compare(@RequestBody String ob2){
        boolean res = cmp.Compare(arr.wypisz(), ob2);
        String wyn = res ? "True" : "False";
        String [] wynik = {wyn, cmp.getDifference1(), cmp.getDifference2()};
        return wynik;
    }


}


