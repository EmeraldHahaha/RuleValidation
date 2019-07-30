package entity;

import java.util.HashMap;
import java.util.Map;

public class Transition {
	private Map<String, String> attrs;
		
	public Transition() {
		attrs = new HashMap<String, String>();
	}

	public Transition(Map<String, String> attrs) {
		super();
		this.attrs = attrs;
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}
	
	

}
