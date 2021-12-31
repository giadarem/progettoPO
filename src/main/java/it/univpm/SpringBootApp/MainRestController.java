package it.univpm.SpringBootApp;

import it.univpm.Service.ServiceFilter;
import it.univpm.Service.ServiceRetrieve;
import it.univpm.Service.StatisticService;
import it.univpm.Statistics.*;
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
                                        @RequestParam(name="count", required=false, defaultValue = "15") String count,
                                        @RequestParam(name="lang", required=false, defaultValue = "it") String lang) throws IOException, ParseException
    {
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
    public ResponseEntity filters(@RequestParam(name="filter", required = false, defaultValue = "search") String filter,
                                  @RequestParam(name="field", required = false, defaultValue = "text") String field,
                                  @RequestParam(name="value", required = false, defaultValue = "covid") String value) throws IOException, ParseException {
        LOGGER.info("*** CALLED PATH [\"filtering\"] ***");
        LOGGER.warn("Filter value: " + filter);
        LOGGER.warn("Field Name value: " + field);
        LOGGER.warn("Field value: " + value);

        return new ResponseEntity<String>(new ServiceFilter().filters(filter,field,value), HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity statistics(@RequestParam(name="statistic", required = false, defaultValue = "30") String statistic) throws IOException, ParseException {
    { LOGGER.info("*** CALLED PATH [\"STATISTIC1\"] ***");
        return new ResponseEntity<String>(new StatisticService().statistic(statistic), HttpStatus.OK);
    }}
  }