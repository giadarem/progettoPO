package it.univpm.Model;

public class Attribute {
    private String attribute_name;
    private String attribute_description;

    public Attribute(String attribute_name, String attribute_description)
    {
        this.setAttribute_name(attribute_name);
        this.setAttribute_description(attribute_description);
    }

    //GETTER & SETTER
    public String getAttribute_name() {return attribute_name;}
    public void setAttribute_name(String attribute_name) {this.attribute_name = attribute_name;}

    public String getAttribute_description() {return attribute_description;}
    public void setAttribute_description(String attribute_description) {this.attribute_description = attribute_description;}
}
