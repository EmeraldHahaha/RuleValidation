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
import entity.ExceptionXML;
import entity.Linkpoint;
import entity.Partition;
import entity.Port;
import entity.Propagation;
import entity.State;
import entity.Systemm;
import entity.Task;
import entity.Transition;

public class ParseXML {

	public String urlString;

	private static List<Element> elements;
	// public static Map<String, T> mapAll=new HashMap<String, T>;

	public ParseXML() throws Exception {
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

		String name;
		String value;

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

		for (Node node : selectNodess) {// communicationchannel
			// System.out.println("========我是channel======");
			Element element = (Element) node;
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

//--------------------使用XPath查询component--------------------

		List<Node> selectNodes = doc.selectNodes("system/component");
		for (Node node : selectNodes) {
			// System.out.println("========我是component======");
			Element componentElement = (Element) node;
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
			// System.out.println(component.getAttrMap());

//--------------------查询component子属性-------------------------------------------------------------------------------
			// System.out.println("========我是component子标签======");
			elements = componentElement.elements();
			for (Element elemen1 : elements) {
				if (elemen1.getName() == "linkpoint") {
					List<Attribute> attributesLinkPoint = elemen1.attributes();
					Map<String, String> attrMapLinkpoint = new HashMap<String, String>();
					Linkpoint linkpoint = new Linkpoint();
					// 容器、当前元素的属性列表、属性map、linkpoint的map、linkpoint对象、
					BrowseLinkPoint(component, attributesLinkPoint, attrMapLinkpoint, linkpointList, linkpoint);

				} else if (elemen1.getName() == "exception") {
					List<Attribute> attributesException = elemen1.attributes();
					Map<String, String> attrMapException = new HashMap<String, String>();
					ExceptionXML exception = new ExceptionXML();
					BrowseException(component, attrMapException, exceptionList, exception, attributesException);

				} else if (elemen1.getName() == "task") {
					// task可能有嵌套
					List<Attribute> attributesTask = elemen1.attributes();
					Map<String, String> attrMapTask = new HashMap<String, String>();
					Task task = new Task();
					BrowseTask(component, attrMapTask, attributesTask, task, taskList, elemen1, subTaskList,
							subPortList);

				} else if (elemen1.getName() == "propagation") {
					List<Attribute> attributesPro = elemen1.attributes();
					Map<String, String> attrMapPro = new HashMap<String, String>();
					Propagation pro = new Propagation();
					BrowsePropagation(component, proList, pro, attributesPro, attrMapPro);

				} else if (elemen1.getName() == "transition") {
					List<Attribute> attributesTran = elemen1.attributes();
					Map<String, String> attrMapTran = new HashMap<String, String>();
					Transition tran = new Transition();
					BrowseTransition(component, tranList, tran, attributesTran, attrMapTran);

				} else if (elemen1.getName() == "state") {
					List<Attribute> attributesState = elemen1.attributes();
					Map<String, String> attrMapState = new HashMap<String, String>();
					State state = new State();
					BrowseState(component, stateList, state, attributesState, attrMapState, elemen1);
				} else if (elemen1.getName() == "connection") {
					List<Attribute> attributesConnection = elemen1.attributes();
					Map<String, String> attrMapConnection = new HashMap<String, String>();
					Connection connection = new Connection();
					BrowseConnection(component, connectionList, connection, attributesConnection, attrMapConnection);
				} else if (elemen1.getName() == "partition") {
					List<Attribute> attributesPar = elemen1.attributes();
					Map<String, String> attrMapPar = new HashMap<String, String>();
					Partition partition = new Partition();
					BrowsePartition(component, partitionList, partition, attributesPar, attrMapPar, elemen1);

				} else {
					// System.out.println("component解析结束");
				}
			} // component
		}

		systemName.setChannelMap(comChannelList);
		systemName.setComMap(comList);

		return systemName;
	}

	private static void BrowseTask(Component component, Map<String, String> attrMapTask, List<Attribute> attributesTask,
			Task task, Map<String, Task> taskList, Element element, Map<String, Task> subTaskList,
			Map<String, Port> subPortList) {
		String name;
		String value;
		for (Attribute attribute : attributesTask) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapTask.put(name, value);
		}
		task.setAttrs(attrMapTask);
		taskList.put(task.getAttrs().get("id"), task);
		component.setTaskList(taskList);
		List<Element> elements2 = element.elements();
		if (elements2 != null) {
			for (Element element2 : elements2) {
				if (element2.getName() == "task") {
					Task subTask = new Task();
					List<Attribute> attributesSubTask = element2.attributes();
					Map<String, String> attrMapSubTask = new HashMap<String, String>();
					String nameSub;
					String valueSub;
					for (Attribute attribute : attributesSubTask) {
						nameSub = attribute.getName();
						valueSub = attribute.getText();
						attrMapSubTask.put(nameSub, valueSub);
					}
					subTask.setAttrs(attrMapSubTask);
					subTaskList.put(subTask.getAttrs().get("id"), subTask);
					task.setSubTasksList(subTaskList);

				} else if (element2.getName() == "port") {
					BrowsePort(task, subPortList, element2);

				} else {
					// System.out.println("我不是port也不是task我是" + element2.getName());
				}
			}
		}

	}

	/*
	 * 参数Map、参数List、当前元素、当前元素的List、所属元素
	 */
	private static void BrowsePartition(Component component, Map<String, Partition> partitionList, Partition partition,
			List<Attribute> attributesPar, Map<String, String> attrMapPar, Element elemen1) {
		String name;
		String value;
		for (Attribute attribute : attributesPar) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapPar.put(name, value);
		}
		partition.setAttrs(attrMapPar);
		partitionList.put(partition.getAttrs().get("id"), partition);
		component.setPartitionList(partitionList);

		if (elemen1.elements() != null) {
			Map<String, Task> taskListOfPar = new HashMap<String, Task>();
			for (Element subElement : elemen1.elements()) {
				Map<String, String> attrMapSubPar = new HashMap<String, String>();
				if (subElement.getName() == "task") {
					// System.out.println("我是partition的子task");
					Task subOfParTask = new Task();
					List<Attribute> attrOfSubPar = subElement.attributes();
					for (Attribute attribute : attrOfSubPar) {
						name = attribute.getName();
						value = attribute.getText();
						attrMapSubPar.put(name, value);
					}
					subOfParTask.setAttrs(attrMapSubPar);
					taskListOfPar.put(subOfParTask.getAttrs().get("id"), subOfParTask);
					// System.out.println(subOfParTask.getAttrs());
					BrowserSubOFTaskPartition(subElement, subOfParTask);

				} else {
					System.out.println("暂无此属性");
				}
			}

			partition.setSubTasksList(taskListOfPar);

		} else {
			System.out.println("没有子属性");
		}
	}

	private static void BrowserSubOFTaskPartition(Element subElement, Task subOfParTask) {
		List<Element> elements2 = subElement.elements();
		if (elements2 != null) {
			Map<String, Task> subTaskListOfPar = new HashMap<String, Task>();
			Map<String, Port> subPortListOfPar = new HashMap<String, Port>();
			for (Element element2 : elements2) {
				if (element2.getName() == "task") {
					Task subTask = new Task();
					List<Attribute> attributesSubTask = element2.attributes();
					Map<String, String> attrMapSubTask = new HashMap<String, String>();
					String nameSub;
					String valueSub;
					for (Attribute attribute : attributesSubTask) {
						nameSub = attribute.getName();
						valueSub = attribute.getText();
						attrMapSubTask.put(nameSub, valueSub);
					}
					subTask.setAttrs(attrMapSubTask);
					subTaskListOfPar.put(subTask.getAttrs().get("id"), subTask);
					subOfParTask.setSubTasksList(subTaskListOfPar);

				} else if (element2.getName() == "port") {
					BrowsePort(subOfParTask, subPortListOfPar, element2);
				} else {
					// System.out.println("我不是port也不是task我是" + element2.getName());
				}
			}
		}
	}

	private static void BrowsePort(Task subOfParTask, Map<String, Port> subPortListOfPar, Element element2) {
		Port subPort = new Port();
		List<Attribute> attributesSubPort = element2.attributes();
		Map<String, String> attrMapSubPort = new HashMap<String, String>();
		String nameSub;
		String valueSub;
		for (Attribute attribute : attributesSubPort) {
			nameSub = attribute.getName();
			valueSub = attribute.getText();
			attrMapSubPort.put(nameSub, valueSub);
		}
		subPort.setAttrs(attrMapSubPort);
		subPortListOfPar.put(subPort.getAttrs().get("id"), subPort);
		subOfParTask.setSubPortsList(subPortListOfPar);
	}

	private static void BrowseConnection(Component component, Map<String, Connection> connectionlList,
			Connection connection, List<Attribute> attributesConnection, Map<String, String> attrMapConnection) {
		String name;
		String value;
		for (Attribute attribute : attributesConnection) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapConnection.put(name, value);
		}
		connection.setAttrs(attrMapConnection);
		connectionlList.put(connection.getAttrs().get("id"), connection);
		component.setConnectionList(connectionlList);

	}

	private static void BrowseState(Component component, Map<String, State> stateList, State state,
			List<Attribute> attributesState, Map<String, String> attrMapState, Element element1) {
		String name;
		String value;
		for (Attribute attribute : attributesState) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapState.put(name, value);
		}

		state.setAttrs(attrMapState);
		stateList.put(state.getAttrs().get("id"), state);
		component.setStateList(stateList);
		if (element1.elements() != null) {
			Map<String, State> stateListOfState = new HashMap<String, State>();
			for (Element subElement : element1.elements()) {
				Map<String, String> attrMapSubState = new HashMap<String, String>();
				if (subElement.getName() == "state") {
					// System.out.println("***********我是State的子task*****");
					State subOfParState = new State();
					List<Attribute> attrOfSubState = subElement.attributes();
					for (Attribute attribute : attrOfSubState) {
						name = attribute.getName();
						value = attribute.getText();
						attrMapSubState.put(name, value);
					}
					subOfParState.setAttrs(attrMapSubState);
					stateListOfState.put(subOfParState.getAttrs().get("id"), subOfParState);
					// System.out.println(subOfParState.getAttrs());
				}
			}
			state.setSubStateList(stateListOfState);
		}
	}

	private static void BrowseTransition(Component component, Map<String, Transition> tranList, Transition tran,
			List<Attribute> attributesTran, Map<String, String> attrMapTran) {
		String name;
		String value;
		for (Attribute attribute : attributesTran) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapTran.put(name, value);
		}
		tran.setAttrs(attrMapTran);
		tranList.put(tran.getAttrs().get("id"), tran);
		component.setTransitionList(tranList);

	}

	private static void BrowsePropagation(Component component, Map<String, Propagation> proList, Propagation pro,
			List<Attribute> attributesPro, Map<String, String> attrMapPro) {
		String name;
		String value;
		for (Attribute attribute : attributesPro) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapPro.put(name, value);
		}
		pro.setAttrs(attrMapPro);
		proList.put(pro.getAttrs().get("id"), pro);
		component.setPropagationList(proList);

	}

	private static void BrowseException(Component component, Map<String, String> attrMapException,
			Map<String, ExceptionXML> exceptionList, ExceptionXML exception, List<Attribute> attributesException) {
		String name;
		String value;
		for (Attribute attribute : attributesException) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapException.put(name, value);
		}
		exception.setAttrs(attrMapException);
		exceptionList.put(exception.getAttrs().get("id"), exception);
		component.setExceptionList(exceptionList);

	}

	private static void BrowseLinkPoint(Component component, List<Attribute> attributesLinkPoint,
			Map<String, String> attrMapLinkpoint, Map<String, Linkpoint> linkpointList, Linkpoint linkpoint) {
		// 容器、当前元素的属性列表、属性map、linkpoint的map、linkpoint对象、
		String name;
		String value;
		for (Attribute attribute : attributesLinkPoint) {
			name = attribute.getName();
			value = attribute.getText();
			attrMapLinkpoint.put(name, value);
		}
		linkpoint.setAttrs(attrMapLinkpoint);
		linkpointList.put(linkpoint.getAttrs().get("id"), linkpoint);
		component.setLinkpointList(linkpointList);

	}

	public static void print(Systemm system) {
		System.out.println("我是system及属性：" + system.getAttrMap());
		for (String keyCom : system.getComMap().keySet()) {
			System.out.println("-------------我是component: " + keyCom + "----------------");
			Map<String, String> attrMapCom = system.getComMap().get(keyCom).getAttrMap();
			System.out.println(attrMapCom);

			if (system.getComMap().get(keyCom).getConnectionList() != null) {
			}
			if (system.getComMap().get(keyCom).getConnectionList() != null) {
			}
			if (system.getComMap().get(keyCom).getExceptionList() != null) {
			}
			if (system.getComMap().get(keyCom).getLinkpointList() != null) {
			}
			if (system.getComMap().get(keyCom).getPartitionList() != null) {
				System.out.println("-----我是component的子标签partition--------");
				for (Partition par : system.getComMap().get(keyCom).getPartitionList().values()) {
					System.out.println(par.getAttrs());
					if (par.getSubTasksList() != null) {
						for (Task subTask : par.getSubTasksList().values()) {
							System.out.println(subTask.getAttrs());
						}
					}
				}
			}
			if (system.getComMap().get(keyCom).getPropagationList() != null) {
			}
			if (system.getComMap().get(keyCom).getStateList() != null) {
			}
			if (system.getComMap().get(keyCom).getTaskList() != null) {
			}
			if (system.getComMap().get(keyCom).getTransitionList() != null) {
			}

			System.out.println("-----------------------------");
		}

	}

}
