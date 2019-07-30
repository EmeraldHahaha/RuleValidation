package entity;

import java.util.Map;

public class Partition extends Component {
	Map<String, String> attrs;
	private Map<String, Task> subTasksList;

	public Map<String, Task> getSubTasksList() {
		return subTasksList;
	}

	public void setSubTasksList(Map<String, Task> subTasksList) {
		this.subTasksList = subTasksList;
	}

	public Partition() {
		super();
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}

}
