package entity;

import java.util.HashMap;
import java.util.Map;

public class ExceptionXML {
	
	private Map<String, String> attrs;
		
	public ExceptionXML() {
		attrs = new HashMap<>();
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}
		
	

}
