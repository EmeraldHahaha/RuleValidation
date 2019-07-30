package entity;

import java.util.Map;

/*
 * @author Emerald
 * 
 * 
 */
public class Component {

	private Map<String, String> attrMap;

	private Map<String, State> stateList;
	private Map<String, Propagation> propagationList;
	private Map<String, ExceptionXML> exceptionList;
	private Map<String, Linkpoint> linkpointList;
	private Map<String, Transition> transitionList;
	private Map<String, Port> portList;
	private Map<String, Connection> connectionList;
	private Map<String, Channel> channelList;
	private Map<String, Task> taskList;
	private Map<String, Partition> partitionList;
	private Map<String, ErrorPath> errorpathList;

	public Map<String, ErrorPath> getErrorpathList() {
		return errorpathList;
	}

	public void setErrorpathList(Map<String, ErrorPath> errorpathList) {
		this.errorpathList = errorpathList;
	}

	public Component() {
		super();
	}

	public Map<String, String> getAttrMap() {
		return attrMap;
	}

	public void setAttrMap(Map<String, String> attrMap) {
		this.attrMap = attrMap;
	}

	public Map<String, State> getStateList() {
		return stateList;
	}

	public void setStateList(Map<String, State> stateList) {
		this.stateList = stateList;
	}

	public Map<String, Propagation> getPropagationList() {
		return propagationList;
	}

	public void setPropagationList(Map<String, Propagation> propagationList) {
		this.propagationList = propagationList;
	}

	public Map<String, ExceptionXML> getExceptionList() {
		return exceptionList;
	}

	public void setExceptionList(Map<String, ExceptionXML> exceptionList) {
		this.exceptionList = exceptionList;
	}

	public Map<String, Linkpoint> getLinkpointList() {
		return linkpointList;
	}

	public void setLinkpointList(Map<String, Linkpoint> linkpointList) {
		this.linkpointList = linkpointList;
	}

	public Map<String, Transition> getTransitionList() {
		return transitionList;
	}

	public void setTransitionList(Map<String, Transition> transitionList) {
		this.transitionList = transitionList;
	}

	public Map<String, Port> getPortList() {
		return portList;
	}

	public void setPortList(Map<String, Port> portList) {
		this.portList = portList;
	}

	public Map<String, Connection> getConnectionList() {
		return connectionList;
	}

	public void setConnectionList(Map<String, Connection> connectionList) {
		this.connectionList = connectionList;
	}

	public Map<String, Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(Map<String, Channel> channelList) {
		this.channelList = channelList;
	}

	public Map<String, Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(Map<String, Task> taskList) {
		this.taskList = taskList;
	}

	public Map<String, Partition> getPartitionList() {
		return partitionList;
	}

	public void setPartitionList(Map<String, Partition> partitionList) {
		this.partitionList = partitionList;
	}
}
