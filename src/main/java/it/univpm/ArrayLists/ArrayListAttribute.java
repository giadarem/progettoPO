package it.univpm.ArrayLists;

import it.univpm.Model.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;

public class ArrayListAttribute {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrayListAttribute.class);
    private ArrayList<Attribute> attributes;

    public ArrayListAttribute() {
        this.attributes = new ArrayList<>();
    }

    //Aggiunta di un elemento all'ArrayList
    public void addElement(Attribute at){this.attributes.add(at);}
    //Composizione e ritorno del JSON Composto da tutti gli attributi presenti
    public String getAll(){
        String str = "{\"attributes\":[";

        for (int i = 0; i < this.attributes.size(); i++)
        {
            str += "{\"attribute_name\":\"" + this.attributes.get(i).getAttribute_name() + "\",";
            str += "\"attribute_description\":\"" + this.attributes.get(i).getAttribute_description() + "\"}";
            if((i+1) != this.attributes.size())
                str += ",";
        }
        str += "]}";
        LOGGER.info("*** REQUEST STATUS [\"get-attributes\"] - " + HttpStatus.OK + " ***");

        return str;
    }
}
