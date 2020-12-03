package model;


import java.io.Serializable;

public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
    private String tagStr;
    private String value;


    public Tag(String t, String v) {
        tagStr = t;
        value = v;
    }

    public Tag(Tag t) {
        tagStr = t.tagStr;
        value = t.value;
    }
    
    public String getTag(){
    	return tagStr;
    }
    
    public String getValue(){
    	return value;
    }
    
    public void setTag(String s){
    	tagStr = s;
    }

    public void setValue(String s){
    	value = s;
    }
}
