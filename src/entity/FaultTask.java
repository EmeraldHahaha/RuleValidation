package entity;

import java.util.Map;

public class FaultTask {
	Map<String, String> attrs;

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}

	public FaultTask() {
		super();
	}

	public FaultTask(Map<String, String> attrs) {
		super();
		this.attrs = attrs;
	}

}
