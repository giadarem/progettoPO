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

/*
    Tipologie di chiamate:
    [ /get-tweets ]
    - http://localhost:8080/api/get-tweets
    - http://localhost:8080/api/get-tweets?location=<value>&result_type=<value>&count=<value>&lang=<value>
    NB: I parametri sono opzionali, possono essere omessi o parziali - hanno un valore di default
    [ /get-attributes ]
    - http://localhost:8080/api/get-attributes
   [ /filters ]
   - http://localhost:8080/api/filters
   - http://localhost:8080/api/filters?filter=<value>&field=<value>&value=<value>
   NB: I parametri sono opzionali, possono essere omessi o parziali - hanno un valore di default
   [ /statistics ]
   - http://localhost:8080/api/statistics
   - http://localhost:8080/api/statistics?stats_field=<value>
   - http://localhost:8080/api/statistics?stats_field=<value>&filter=<value>&field=<value>&value=<value>
   NB: I parametri sono opzionali, possono essere omessi o parziali - hanno un valore di default
*/

@RestController
@RequestMapping(value = "/api", method = RequestMethod.GET, produces = "application/json")
public class MainRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainRestController.class);

    public MainRestController(){ LOGGER.info("*** APPLICATION - START ***"); }

    /* RICHIESTA PARAMETRI - /get-tweets
        location -> Milano, Napoli, Ancona - Default: Milano
        result_type -> recent, mixed, popular - Default: recent
        count -> (Int) > 0 & < 100 - Default: 20
        lang -> it, fr, en, es - Default: it
    */
    
    /** richiede dei parametri non obbligatori in quanto se non forniti immette quelli di default
     * 
     * @param location, rappresenta la posizione geografica da cui viene fatto il tweet
     * @param type, rappresenta la tipologia dei post (recente, popolare, entrambi)
     * @param count, rappresenta il numero di tweet che avremo come risposta in json
     * @param lang, rappresenta la lingua dei tweet che avremo come risposta in json
     * @return Tweets che soddisfano le richieste passate tramite URL
     * @throws IOException
     * @throws ParseException
     */
    @GetMapping("/get-tweets")
    public ResponseEntity tweets(@RequestParam(name="location", required=false, defaultValue = "Milano") String location,
                                        @RequestParam(name="result_type", required=false, defaultValue = "recent") String type,
                                        @RequestParam(name="count", required=false, defaultValue = "20") String count,
                                        @RequestParam(name="lang", required=false, defaultValue = "it") String lang) throws IOException, ParseException {
        //LOG PARAMETRI - CHECK PER VISUALIZZAZIONE PARAMETRI PASSATI
        LOGGER.info("*** CALLED PATH [\"get-tweets\"] ***");
        LOGGER.warn("Location Value: " + location);
        LOGGER.warn("Result Type Value: " + type);
        LOGGER.warn("Count Value: " + count);
        LOGGER.warn("Language Value: " + lang);

        //RISPOSTA - Tweets che soddisfano le richieste passate tramite URL
        String response = new ServiceRetrieve().getJSONResponseFromAPI(location, type, count, lang);
        if(!response.isEmpty()) {
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } else {
            LOGGER.error("*** Error 400 - BAD REQUEST / Invalid location [INSERT Milano OR Ancona OR Napoli IN location VALUE] ***");
            return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid location [INSERT Milano OR Ancona OR Napoli IN location VALUE]\"}", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * composizione del json contenente tutti i metadati presenti per ogni tweet
     * @return invocazione del metodo getAll() della classe ArrayListAttribute, 
            composizione del JSON per ogni attributo presente nell'ArrayListAttribute
     */
    @GetMapping("/get-attributes")
    public ResponseEntity attribute()
    {
        LOGGER.info("*** CALLED PATH [\"get-attributes\"] ***");
        //RISPOSTA - Elenco degli attributi utilizzati per ogni Tweet
        return new ResponseEntity<String>(new ServiceRetrieve().getAttributesList(), HttpStatus.OK);
    }

    /* RICHIESTA PARAMETRI - /filters
        filter -> search, lower, upper, included - Default: lower
        field -> (search) hashtags, mentions, username | (lower,upper,included) post_num - Default: post_num
        value -> (String) in caso di ricerca | (Int) > 0 - Default: 20 [Included option - 20-40 / Occorre passare Min e Max range]
    */
    
    /**
     * richiede dei parametri non obbligatori per poterci effettuare dei filtri
     * recuperando un'array di Tweet dopo l'applicazione dei filtri 
     * e restituendo la risposta in formato Json
     * 
     * @param stats_field, created_at, listed_count
     * @param filter, rappresenta il filtro che si pu?? applicare
     * @param field, rappresenta il campo in cui applicare il filtro
     * @param value, rappresenta il numero di Tweet da ritornare
     * @return ArrayListTweetPost contenente tutti i tweet che rispettino le condizioni del filtro
     * @throws IOException
     * @throws ParseException
     */
    @GetMapping("/filters")
    public ResponseEntity filters(@RequestParam(name="filter", required = false, defaultValue = "lower") String filter,
                                  @RequestParam(name="field", required = false, defaultValue = "post_num") String field,
                                  @RequestParam(name="value", required = false, defaultValue = "20") String value) throws IOException, ParseException {
        LOGGER.info("*** CALLED PATH [\"filters\"] ***");
        LOGGER.warn("Filter value: " + filter);
        LOGGER.warn("Field Name value: " + field);
        LOGGER.warn("Field value: " + value);

        //AraayList di risposta
        ArrayListTweetPost response = new ServiceFilters().filters(filter, field, value);
        //Caso in cui il filtro passato ???? errato
        if (response == null) {
            LOGGER.error("*** Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter VALUE] ***");
            return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter Value]\"}", HttpStatus.BAD_REQUEST);
        //Caso in cui tutti i parametri sono corretti e si ha un ritorno valorizzato
        }else if (response.getAllTweets().size() != 0) {
            return new ResponseEntity<ArrayListTweetPost>(response, HttpStatus.OK);
        //Caso in cui non si ha un ritorno valorizzato - parametri corretti
        } else {
            LOGGER.error("*** Error 400 - BAD REQUEST / No record found [INSERT hashtags OR mentions OR username OR post_num IN field VALUE] ***");
            return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / No record found [INSERT hashtags OR mentions OR username OR post_num IN field VALUE]\"}", HttpStatus.BAD_REQUEST);
        }
    }

    /* RICHIESTA PARAMETRI - /statistics
        stats_field -> created_at, listed_count
        filter -> search, lower, upper, included - Default: lower
        field -> (search) hashtags, mentions, username | (lower,upper,included) post_num - Default: post_num
        value -> (String) in caso di ricerca | (Int) > 0 - Default: 20 [Included option - 20-40 / Occorre passare Min e Max range]
    */
    
    /**
     * richiede dei parametri non obbligatori per effettuarci delle statistiche
     * 
     * @param stats_field, created_at, listed_count
     * @param filter, rappresenta il filtro che si pu?? applicare
     * @param field, rappresenta il campo in cui applicare il filtro
     * @param value, rappresenta il numero di Tweet da ritornare
     * @return  Invocazione del metodo getValues(<>) della classe FreqStatistic 
     * +fornendo l'arraylist dei tweet della classe ServiceStatistics
     * @throws IOException
     * @throws ParseException
     */
    @GetMapping("/statistics")
    public ResponseEntity statistics(@RequestParam(name="stats_field", required = false, defaultValue = "created_at") String stats_field,
                                     @RequestParam(name="filter", required = false) String filter,
                                     @RequestParam(name="field", required = false) String field,
                                     @RequestParam(name="value", required = false) String value) throws IOException, ParseException {
        LOGGER.info("*** CALLED PATH [\"statistics\"] ***");
        LOGGER.warn("Stats field: " + stats_field);
        ArrayListTweetPost tp = null;
        String str = "";
        //Caso in cui vengono implementati i filtri prima della statistica
        if(filter != null && field != null && value != null) {
            //Se i parametri passati sono corretti
            if(filters(filter, field, value).getStatusCode() == HttpStatus.OK) {
                //Recupero la risposta dei filtri
                tp = (ArrayListTweetPost) filters(filter, field, value).getBody();
                //Recupero le statistiche sull'ArrayList filtrato
                str = new ServiceStatistics(tp).statistics(stats_field);
                //Se il ritorno ???? vuoto - Campo delle statistiche sbagliato
                if(str.isEmpty()){
                    LOGGER.error("*** Error 400 - BAD REQUEST / Invalid Statistics Field [INSERT created_at OR listed_count IN stats_field VALUE] ***");
                    return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid Statistics Field [INSERT created_at OR listed_count IN stats_field VALUE]\"}", HttpStatus.BAD_REQUEST);
                //Se l'ArrayList ???? null - Filtri non corretti
                } else if(tp == null){
                    LOGGER.error("*** Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter VALUE] ***");
                    return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter Value]\"}", HttpStatus.BAD_REQUEST);
                //Se l'ArrayList ???? vuoto - Non sono disponibili risultati per i filtri selezionati
                } else if (tp.getAllTweets().size() == 0) {
                    LOGGER.error("*** Error 400 - BAD REQUEST / No record found [INSERT hashtags OR mentions OR username OR post_num IN field VALUE] ***");
                    return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / No record found [INSERT hashtags OR mentions OR username OR post_num IN field VALUE]\"}", HttpStatus.BAD_REQUEST);
                //Se tutto ???? corretto - visualizzo le statistiche
                } else {
                    return new ResponseEntity<String>(str, HttpStatus.OK);
                }
            } else {
                LOGGER.error("*** Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter VALUE] ***");
                return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid filter [INSERT search OR lower OR upper OR included IN filter VALUE]\"}", HttpStatus.BAD_REQUEST);
            }
        //Se non vengono utilizzati i filtri
        } else {
            //Recupero le statistiche sull'ArrayList base
            str = new ServiceStatistics().statistics(stats_field);
            //Se il ritorno ???? valorizzato visualizzo i risutati
            if (!str.isEmpty()) {
                return new ResponseEntity<String>(str, HttpStatus.OK);
            //Se il ritorno ???? vuoto - Campo statistiche errato
            } else {
                LOGGER.error("*** Error 400 - BAD REQUEST / Invalid Statistics Field [INSERT created_at OR listed_count IN stats_field VALUE] ***");
                return new ResponseEntity<String>("{\"Error\":\"Error 400 - BAD REQUEST / Invalid Statistics Field [INSERT created_at OR listed_count IN stats_field VALUE]\"}", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
