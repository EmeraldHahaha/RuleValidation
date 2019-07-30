package entity;

import java.util.HashMap;
import java.util.Map;
/*
 * ÊôĞÔÃÇ
 */
public class Linkpoint {
    private Map<String, String> attrs;

    public Linkpoint() {
    	attrs = new HashMap<>();
    }

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}
   
   

}
