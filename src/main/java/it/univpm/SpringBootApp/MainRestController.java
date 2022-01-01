package it.univpm.SpringBootApp;

import it.univpm.ArrayLists.ArrayListTweetPost;
import it.univpm.Service.ServiceFilters;
import it.univpm.Service.ServiceRetrieve;
import it.univpm.Service.ServiceStatistics;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.lang.String;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.GET, produces = "application/json")
public class MainRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainRestController.class);

    public MainRestController(){
        LOGGER.info("*** APPLICATION - START ***");
    }

    @GetMapping("/get-tweets")
    public ResponseEntity tweets(@RequestParam(name="location", required=false, defaultValue = "Milano") String location,
                                        @RequestParam(name="result_type", required=false, defaultValue = "recent") String type,
                                        @RequestParam(name="count", required=false, defaultValue = "20") String count,
                                        @RequestParam(name="lang", required=false, defaultValue = "it") String lang) throws IOException, ParseException {
        LOGGER.info("*** CALLED PATH [\"get-tweets\"] ***");
        LOGGER.warn("Location Value: " + location);
        LOGGER.warn("Result Type Value: " + type);
        LOGGER.warn("Count Value: " + count);
        LOGGER.warn("Language Value: " + lang);

        return new ResponseEntity<String>(new ServiceRetrieve().getJSONResponseFromAPI(location, type, count, lang), HttpStatus.OK);
    }

    @GetMapping("/get-attributes")
    public ResponseEntity attribute()
    {
        LOGGER.info("*** CALLED PATH [\"get-attributes\"] ***");
        return new ResponseEntity<String>(new ServiceRetrieve().getAttributesList(), HttpStatus.OK);
    }

    @GetMapping("/filters")
    public ResponseEntity filters(@RequestParam(name="filter", required = false, defaultValue = "lower") String filter,
                                  @RequestParam(name="field", required = false, defaultValue = "post_num") String field,
                                  @RequestParam(name="value", required = false, defaultValue = "20") String value) throws IOException, ParseException {
        LOGGER.info("*** CALLED PATH [\"filters\"] ***");
        LOGGER.warn("Filter value: " + filter);
        LOGGER.warn("Field Name value: " + field);
        LOGGER.warn("Field value: " + value);

        ArrayListTweetPost response = new ServiceFilters().filters(filter, field, value);
        if (response == null) {
            LOGGER.error("*** Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter VALUE] ***");
            return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter Value]\"}", HttpStatus.BAD_REQUEST);
        }else if (response != null && response.getAllTweets().size() != 0) {
            return new ResponseEntity<ArrayListTweetPost>(response, HttpStatus.OK);
        } else {
            LOGGER.error("*** Error 400 - BAD REQUEST / No record found [INSERT hashtags OR mentions OR username OR post_num IN field VALUE] ***");
            return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / No record found [INSERT hashtags OR mentions OR username OR post_num IN field VALUE]\"}", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/statistics")
    public ResponseEntity statistics(@RequestParam(name="stats_field", required = false, defaultValue = "created_at") String stats_field,
                                     @RequestParam(name="filter", required = false) String filter,
                                     @RequestParam(name="field", required = false) String field,
                                     @RequestParam(name="value", required = false) String value) throws IOException, ParseException {
        LOGGER.info("*** CALLED PATH [\"statistics\"] ***");
        LOGGER.warn("Stats field: " + stats_field);
        ArrayListTweetPost tp = null;
        String str = "";
        if(filter != null && field != null && value != null) {
            if(filters(filter, field, value).getStatusCode() == HttpStatus.OK) {
                tp = (ArrayListTweetPost) filters(filter, field, value).getBody();
                str = new ServiceStatistics(tp).statistics(stats_field);
                if(str.isEmpty()){
                    LOGGER.error("*** Error 400 - BAD REQUEST / Invalid Statistics Field [INSERT created_at OR listed_count IN stats_field VALUE] ***");
                    return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid Statistics Field [INSERT created_at OR listed_count IN stats_field VALUE]\"}", HttpStatus.BAD_REQUEST);
                } else if(tp == null){
                    LOGGER.error("*** Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter VALUE] ***");
                    return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter Value]\"}", HttpStatus.BAD_REQUEST);
                } else if (tp.getAllTweets().size() == 0) {
                    LOGGER.error("*** Error 400 - BAD REQUEST / No record found [INSERT hashtags OR mentions OR username OR post_num IN field VALUE] ***");
                    return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / No record found [INSERT hashtags OR mentions OR username OR post_num IN field VALUE]\"}", HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<String>(str, HttpStatus.OK);
                }
            } else {
                LOGGER.error("*** Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter VALUE] ***");
                return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter VALUE]\"}", HttpStatus.BAD_REQUEST);
            }
        } else {
            str = new ServiceStatistics().statistics(stats_field);
            if (!str.isEmpty()) {
                return new ResponseEntity<String>(str, HttpStatus.OK);
            } else {
                LOGGER.error("*** Error 400 - BAD REQUEST / Invalid Statistics Field [INSERT created_at OR listed_count IN stats_field VALUE] ***");
                return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid Statistics Field [INSERT created_at OR listed_count IN stats_field VALUE]\"}", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
