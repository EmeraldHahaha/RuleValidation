package entity;

import java.util.Map;

public class ErrorPath {
	private Map<String, String> attrs;

	public ErrorPath() {
		super();

	}

	public ErrorPath(Map<String, String> attrs) {
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
