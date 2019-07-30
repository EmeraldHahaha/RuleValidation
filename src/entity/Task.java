package entity;

import java.util.Map;

public class Task extends Component {
	Map<String, String> attrs;
	private Map<String, Task> subTasksList;
	private Map<String, Port> subPortsList;
	private Map<String, FaultTask> faultTasksList;
	private Map<String, Connection> subConnectionsList;
	private Map<String, Propagation> subPropagationsList;

	public Task() {
		super();
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}

	public Map<String, Task> getSubTasksList() {
		return subTasksList;
	}

	public void setSubTasksList(Map<String, Task> subTasksList) {
		this.subTasksList = subTasksList;
	}

	public Map<String, Port> getSubPortsList() {
		return subPortsList;
	}

	public void setSubPortsList(Map<String, Port> subPortsList) {
		this.subPortsList = subPortsList;
	}

	public Map<String, FaultTask> getFaultTasksList() {
		return faultTasksList;
	}

	public void setFaultTasksList(Map<String, FaultTask> faultTasksList) {
		this.faultTasksList = faultTasksList;
	}

	public Map<String, Connection> getSubConnectionsList() {
		return subConnectionsList;
	}

	public void setSubConnectionsList(Map<String, Connection> subConnectionsList) {
		this.subConnectionsList = subConnectionsList;
	}

	public Map<String, Propagation> getSubPropagationsList() {
		return subPropagationsList;
	}

	public void setSubPropagationsList(Map<String, Propagation> subPropagationsList) {
		this.subPropagationsList = subPropagationsList;
	}

}
