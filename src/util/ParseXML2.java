package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import entity.CommunicationChannel;
import entity.Component;
import entity.Connection;
import entity.ErrorPath;
import entity.ExceptionXML;
import entity.FaultTask;
import entity.Linkpoint;
import entity.Partition;
import entity.Port;
import entity.Propagation;
import entity.State;
import entity.Systemm;
import entity.Task;
import entity.Transition;

public class ParseXML2 {

	public String urlString;

	private static List<Element> elements;
	static String name;
	static String value;

	public ParseXML2() throws Exception {
		super();

		parseXML(urlString);
	}

	public static Systemm parseXML(String url) throws Exception {
		Systemm systemName = new Systemm();
		// System.out.println("========我是解析===========");
		Map<String, String> attrMap = new HashMap<String, String>();

		Map<String, CommunicationChannel> comChannelList = new HashMap<String, CommunicationChannel>();

		Map<String, Component> comList = new HashMap<String, Component>();

		Map<String, Linkpoint> linkpointList = new HashMap<String, Linkpoint>();

		Map<String, ExceptionXML> exceptionList = new HashMap<String, ExceptionXML>();

		Map<String, Port> subPortList = new HashMap<String, Port>();

		Map<String, Task> taskList = new HashMap<String, Task>();

		Map<String, Task> subTaskList = new HashMap<String, Task>();

		Map<String, Propagation> proList = new HashMap<String, Propagation>();

		Map<String, State> stateList = new HashMap<String, State>();

		Map<String, Connection> connectionList = new HashMap<String, Connection>();

		Map<String, Transition> tranList = new HashMap<String, Transition>();

		Map<String, Partition> partitionList = new HashMap<String, Partition>();

		Map<String, ErrorPath> errorpathList = new HashMap<String, ErrorPath>();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(url);
		Element rootElt = doc.getRootElement();

//------------------------------system-------------- ------ ----

		List<Attribute> attrsElements = rootElt.attributes();
		Map<String, String> attrMapSystem = new HashMap<String, String>();
		for (Attribute attribute : attrsElements) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapSystem.put(name, value);
		}
		systemName.setAttrMap(attrMapSystem);

//------------------使用XPath查询communicationChannel--------------------
		List<Node> selectNodess = doc.selectNodes("system/communicationchannel");
		for (Node node : selectNodess) {
			Element element = (Element) node;
			BrowseCommunication(comChannelList, element);
		}

//--------------------使用XPath查询component--------------------
		List<Node> selectNodes = doc.selectNodes("system/component");
		for (Node node : selectNodes) {
			Element componentElement = (Element) node;
			Component component = BrowseComponent(comList, componentElement);

//--------------------查询component子属性-------------------------------------------------------------------------------
			// System.out.println("========我是component子标签======");
			elements = componentElement.elements();
			for (Element elemen1 : elements) {
				if (elemen1.getName() == "linkpoint") {
					linkpointList = BrowseLinkPoint(linkpointList, elemen1);
					component.setLinkpointList(linkpointList);

				} else if (elemen1.getName() == "exception") {
					exceptionList = BrowseException(exceptionList, elemen1);
					component.setExceptionList(exceptionList);

				} else if (elemen1.getName() == "task") {
					taskList = BrowseTask(taskList, elemen1);
					component.setTaskList(taskList);

				} else if (elemen1.getName() == "propagation") {
					proList = BrowsePropagation(proList, elemen1);
					component.setPropagationList(proList);

				} else if (elemen1.getName() == "transition") {
					tranList = BrowseTransition(tranList, elemen1);
					component.setTransitionList(tranList);

				} else if (elemen1.getName() == "state") {
					stateList = BrowseState(stateList, elemen1);
					component.setStateList(stateList);

				} else if (elemen1.getName() == "connection") {
					connectionList = BrowseConnection(connectionList, elemen1);
					component.setConnectionList(connectionList);

				} else if (elemen1.getName() == "partition") {
					partitionList = BrowsePartition(partitionList, elemen1);
					component.setPartitionList(partitionList);

				} else if (elemen1.getName() == "errorpath") {
					errorpathList = BrowseErrorpath(errorpathList, elemen1);
					component.setErrorpathList(errorpathList);

				} else {
					// System.out.println("component解析结束");
				}
			} // component

		}

		systemName.setChannelMap(comChannelList);
		systemName.setComMap(comList);
		System.out.println("-------------------解析完毕---------------------");
		return systemName;
	}

	private static Map<String, ErrorPath> BrowseErrorpath(Map<String, ErrorPath> errorpathList, Element element) {
		ErrorPath errorPath = new ErrorPath();
		List<Attribute> attributesErrorPath = element.attributes();
		Map<String, String> attrMapErrorPath = new HashMap<String, String>();
		for (Attribute attribute : attributesErrorPath) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapErrorPath.put(name, value);
		}
		errorPath.setAttrs(attrMapErrorPath);
		errorpathList.put(errorPath.getAttrs().get("id"), errorPath);
		return errorpathList;
	}

	private static Component BrowseComponent(Map<String, Component> comList, Element componentElement) {
		Component component = new Component();
		List<Attribute> attributesCom = componentElement.attributes();
		Map<String, String> attrMapCom = new HashMap<String, String>();
		for (Attribute attribute : attributesCom) {
			name = attribute.getName();// isolette?
			value = attribute.getText();
			attrMapCom.put(name, value);
		}
		component.setAttrMap(attrMapCom);
		comList.put(component.getAttrMap().get("id"), component);
		return component;
	}

	private static void BrowseCommunication(Map<String, CommunicationChannel> comChannelList, Element element) {
		CommunicationChannel comChannel = new CommunicationChannel();
		List<Attribute> attributeChannel = element.attributes();
		Map<String, String> attrMapChannel = new HashMap<String, String>();
		for (Attribute attribute : attributeChannel) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapChannel.put(name, value);
		}
		comChannel.setAttrMap(attrMapChannel);// comChannel
		comChannelList.put(comChannel.getAttrMap().get("id"), comChannel);
	}

	private static Map<String, Task> BrowseTask(Map<String, Task> taskList, Element element) {
		List<Attribute> attributesTask = element.attributes();
		Map<String, String> attrMapTask = new HashMap<String, String>();
		Task task = new Task();
		for (Attribute attribute : attributesTask) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapTask.put(name, value);
		}
		task.setAttrs(attrMapTask);
		taskList.put(task.getAttrs().get("id"), task);

		List<Element> elements2 = element.elements();

		if (elements2 != null) {
			// System.out.println("-----------------我是task的子标签--------------------");
			Map<String, Task> subTaskList = new HashMap<String, Task>();
			Map<String, Port> subPortList = new HashMap<String, Port>();
			Map<String, Connection> subConnectionList = new HashMap<String, Connection>();
			Map<String, Propagation> subPropagationList = new HashMap<String, Propagation>();
			Map<String, FaultTask> subFaultTaskList = new HashMap<String, FaultTask>();
			for (Element element2 : elements2) {

				if (element2.getName() == "task") {
					subTaskList = BrowseTask(subTaskList, element2);
					task.setSubTasksList(subTaskList);

				} else if (element2.getName() == "port") {
					subPortList = BrowsePort(subPortList, element2);
					task.setSubPortsList(subPortList);

				} else if (element2.getName() == "connection") {
					subConnectionList = BrowseConnection(subConnectionList, element2);
					task.setSubConnectionsList(subConnectionList);

				} else if (element2.getName() == "propagation") {
					subPropagationList = BrowsePropagation(subPropagationList, element2);
					task.setSubPropagationsList(subPropagationList);

				} else if (element2.getName() == "FaultTask") {
					subFaultTaskList = BrowseFaultTask(subFaultTaskList, element2);
					task.setFaultTasksList(subFaultTaskList);

				} else {
					// System.out.println("我不是port也不是task我是" + element2.getName());
				}
			}

		}
		return taskList;

	}

	private static Map<String, FaultTask> BrowseFaultTask(Map<String, FaultTask> faultTaskList, Element element2) {
		FaultTask faultTask = new FaultTask();
		List<Attribute> attributesSubFaultTask = element2.attributes();
		Map<String, String> attrMapSubFaultTask = new HashMap<String, String>();
		for (Attribute attribute : attributesSubFaultTask) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapSubFaultTask.put(name, value);
		}
		faultTask.setAttrs(attrMapSubFaultTask);
		faultTaskList.put(faultTask.getAttrs().get("id"), faultTask);
		return faultTaskList;
	}

	private static Map<String, Port> BrowsePort(Map<String, Port> portList, Element element2) {
		Port port = new Port();
		List<Attribute> attributesSubPort = element2.attributes();
		Map<String, String> attrMapSubPort = new HashMap<String, String>();
		for (Attribute attribute : attributesSubPort) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapSubPort.put(name, value);
		}
		port.setAttrs(attrMapSubPort);
		portList.put(port.getAttrs().get("id"), port);
		return portList;
	}

	private static Map<String, Partition> BrowsePartition(Map<String, Partition> partitionList, Element elemen1) {
		List<Attribute> attributesPar = elemen1.attributes();
		Map<String, String> attrMapPar = new HashMap<String, String>();
		Partition partition = new Partition();
		for (Attribute attribute : attributesPar) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapPar.put(name, value);
		}
		partition.setAttrs(attrMapPar);
		partitionList.put(partition.getAttrs().get("id"), partition);

		if (elemen1.elements() != null) {
			// System.out.println("----------------我是Partition的子标签---------------------");
			Map<String, Task> taskListOfPar = new HashMap<String, Task>();
			for (Element subElement : elemen1.elements()) {
				if (subElement.getName() == "task") {
					taskListOfPar = BrowseTask(taskListOfPar, subElement);
				} else {
					System.out.println("没有子属性");
				}
			}
			partition.setSubTasksList(taskListOfPar);
			// System.out.println(partition.getSubTasksList());

		}
		return partitionList;
	}

	private static Map<String, Connection> BrowseConnection(Map<String, Connection> connectionlList, Element element) {
		List<Attribute> attributesConnection = element.attributes();
		Map<String, String> attrMapConnection = new HashMap<String, String>();
		Connection connection = new Connection();
		for (Attribute attribute : attributesConnection) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapConnection.put(name, value);
		}
		connection.setAttrs(attrMapConnection);
		connectionlList.put(connection.getAttrs().get("id"), connection);
		return connectionlList;
	}

	private static Map<String, State> BrowseState(Map<String, State> stateList, Element element) {
		List<Attribute> attributesState = element.attributes();
		Map<String, String> attrMapState = new HashMap<String, String>();
		State state = new State();
		for (Attribute attribute : attributesState) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapState.put(name, value);
		}
		state.setAttrs(attrMapState);
		stateList.put(state.getAttrs().get("id"), state);
		if (element.elements() != null) {
			// System.out.println("-----------------我是State的子标签--------------------");
			Map<String, State> stateListOfState = new HashMap<String, State>();
			for (Element subElement : element.elements()) {
				if (subElement.getName() == "state") {
					stateListOfState = BrowseState(stateListOfState, subElement);
				}
			}
			state.setSubStateList(stateListOfState);

		}
		return stateList;
	}

	private static Map<String, Transition> BrowseTransition(Map<String, Transition> tranList, Element element) {
		List<Attribute> attributesTran = element.attributes();
		Map<String, String> attrMapTran = new HashMap<String, String>();
		Transition tran = new Transition();
		for (Attribute attribute : attributesTran) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapTran.put(name, value);
		}
		tran.setAttrs(attrMapTran);
		tranList.put(tran.getAttrs().get("id"), tran);
		return tranList;
	}

	private static Map<String, Propagation> BrowsePropagation(Map<String, Propagation> proList, Element element) {
		List<Attribute> attributesPro = element.attributes();
		Map<String, String> attrMapPro = new HashMap<String, String>();
		Propagation pro = new Propagation();
		for (Attribute attribute : attributesPro) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapPro.put(name, value);
		}
		pro.setAttrs(attrMapPro);
		proList.put(pro.getAttrs().get("id"), pro);
		return proList;

	}

	private static Map<String, ExceptionXML> BrowseException(Map<String, ExceptionXML> exceptionList, Element element) {
		List<Attribute> attributesException = element.attributes();
		Map<String, String> attrMapException = new HashMap<String, String>();
		ExceptionXML exception = new ExceptionXML();
		for (Attribute attribute : attributesException) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapException.put(name, value);
		}
		exception.setAttrs(attrMapException);
		exceptionList.put(exception.getAttrs().get("id"), exception);
		return exceptionList;
	}

	private static Map<String, Linkpoint> BrowseLinkPoint(Map<String, Linkpoint> linkpointList, Element element) {
		List<Attribute> attributesLinkPoint = element.attributes();
		Map<String, String> attrMapLinkpoint = new HashMap<String, String>();
		Linkpoint linkpoint = new Linkpoint();
		for (Attribute attribute : attributesLinkPoint) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapLinkpoint.put(name, value);
		}
		linkpoint.setAttrs(attrMapLinkpoint);
		linkpointList.put(linkpoint.getAttrs().get("id"), linkpoint);
		return linkpointList;
	}

}
