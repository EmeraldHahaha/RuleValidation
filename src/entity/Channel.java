package entity;

import java.util.HashMap;
import java.util.Map;

public class Channel {
	Map<String, String> attrs;

	public Channel() {
		attrs = new HashMap<>();
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}

	
}
