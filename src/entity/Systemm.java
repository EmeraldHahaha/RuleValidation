package entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Emerald 最外层的标签
 */
public class Systemm {
	private Map<String, Component> comMap = new HashMap<String, Component>();
	private Map<String, CommunicationChannel> channelMap = new HashMap<String, CommunicationChannel>();
	private Map<String, String> attrMap = new HashMap<String, String>();

	public Systemm() {
		super();
	}

	public Systemm(Map<String, Component> comMap, Map<String, CommunicationChannel> channelMap,
			Map<String, String> attrMap) {
		super();
		this.comMap = comMap;
		this.channelMap = channelMap;
		this.attrMap = attrMap;
	}

	public Map<String, Component> getComMap() {
		return comMap;
	}

	public void setComMap(Map<String, Component> comMap) {
		this.comMap = comMap;
	}

	public Map<String, CommunicationChannel> getChannelMap() {
		return channelMap;
	}

	public void setChannelMap(Map<String, CommunicationChannel> channelMap) {
		this.channelMap = channelMap;
	}

	public Map<String, String> getAttrMap() {
		return attrMap;
	}

	public void setAttrMap(Map<String, String> attrMap) {
		this.attrMap = attrMap;
	}

}
