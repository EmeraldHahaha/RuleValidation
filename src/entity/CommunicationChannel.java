package entity;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Emerald
 * Map<String, String> attrMap¥Ê∑≈ Ù–‘
 * 
 */
public class CommunicationChannel {
	private Map<String, String> attrMap = new HashMap<String, String>();
	
	public CommunicationChannel() {
		super();
		this.attrMap = attrMap;
	}

	public CommunicationChannel(Map<String, String> attrMap) {
		super();
		this.attrMap = attrMap;
	}

	public Map<String, String> getAttrMap() {
		return attrMap;
	}

	public void setAttrMap(Map<String, String> attrMap) {
		this.attrMap = attrMap;
	}

	@Override
	public String toString() {
		return "CommunicationChannel [attrMap=" + attrMap + "]";
	}
	
	

}
