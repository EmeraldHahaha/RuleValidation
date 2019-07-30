package entity;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Emerald
 *
 * ÊôĞÔÃÇ
 */
public class Propagation {
	private Map<String, String> attrs;
	
	public Propagation() {
		attrs = new HashMap<>();
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}
	
	
	

}
