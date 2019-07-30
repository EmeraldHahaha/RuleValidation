package entity;

import java.util.Map;

/**
 * 
 * @author Emerald 属性 可能会有子状态
 *
 */
public class State {

	private Map<String, String> attrs;
	private Map<String, State> subStateList;

	public State() {
		super();
	}

	public State(Map<String, String> attrs, Map<String, State> subStateList) {
		super();
		this.attrs = attrs;
		this.subStateList = subStateList;
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}

	public Map<String, State> getSubStateList() {
		return subStateList;
	}

	public void setSubStateList(Map<String, State> subStateList) {
		this.subStateList = subStateList;
	}

}
