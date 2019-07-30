package entity;

import java.util.HashMap;
import java.util.Map;

public class Connection {
	private Map<String, String> attrsMap;

	public Connection() {
		attrsMap = new HashMap<String, String>();
	}

	public Map<String, String> getAttrs() {
		return attrsMap;
	}

	public void setAttrs(Map<String, String> attrsMap) {
		this.attrsMap = attrsMap;
	}

}
