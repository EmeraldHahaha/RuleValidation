package ruleEntity.realTime;

import static utils.XMLParseUtil.parseXML;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import entity.Channel;
import entity.Component;
import entity.Connection;

public class ReqAndDesignWcetConsistency {

	public static void excute() {
		Map<String, PathNode> pathNodeList = new HashMap<String, PathNode>();
		Map<String, Component> componentListSysml = new LinkedHashMap<>();
		Map<String, Channel> channelListSysml = new LinkedHashMap<>();
		Map<String, Component> componentListSimulink = new LinkedHashMap<>();
		Map<String, Channel> channelListSimulink = new LinkedHashMap<>();

		parseXML("simulink(4).xml", componentListSimulink, channelListSimulink);
		parseXML("sysml0629.xml", componentListSysml, channelListSysml);
	}

	private static void getPathNode(List<Connection> connectionList, Map<String, Component> subComponentList,
			Map<String, PathNode> pathNodeList) {
		for (Connection connection : connectionList) {
			String sourceId = connection.getAttr("source");
			String destId = connection.getAttr("dest");
			if (subComponentList.get(destId) == null || subComponentList.get(sourceId) == null)
				continue;
			if (pathNodeList.get(destId) != null)
				pathNodeList.get(destId).setIsFirst(false);
			else {
				PathNode newDestNode = new PathNode(destId, subComponentList.get(destId).getAttr("wcet"), false);
				pathNodeList.put(destId, newDestNode);
			}
			if (pathNodeList.get(sourceId) != null) {
				pathNodeList.get(sourceId).getNextComponents().add(pathNodeList.get(destId));
			} else {
				PathNode newSourceNode = new PathNode(sourceId, subComponentList.get(sourceId).getAttr("wcet"), true);
				newSourceNode.getNextComponents().add(pathNodeList.get(destId));
				pathNodeList.put(sourceId, newSourceNode);
			}

		}
	}

	private static float getMaxPathWcet(Map<String, PathNode> pathNodeList) {
		Stack<String> pathNodeIdStack = new Stack<String>();
		float maxPathWcet = 0;
		for (String pathNodeKeyString : pathNodeList.keySet()) {
			if (pathNodeList.get(pathNodeKeyString).getIsFirst()) {
				float maxPathWcetTemp = 0;
				float currentWcet = pathNodeList.get(pathNodeKeyString).getWcet();
				pathNodeIdStack.add(pathNodeList.get(pathNodeKeyString).getId());
				maxPathWcetTemp = calculateMaxWcet(maxPathWcetTemp, pathNodeList.get(pathNodeKeyString), currentWcet,
						pathNodeIdStack);
				// System.out.println(maxPathWcet + "max path wcet temp is " + maxPathWcetTemp);
				if (maxPathWcet < maxPathWcetTemp)
					maxPathWcet = maxPathWcetTemp;
				pathNodeIdStack.clear();
			}
		}

		return maxPathWcet;
	}

	private static float calculateMaxWcet(float maxPathWcet, PathNode pathNode, float currentWcet,
			Stack<String> pathNodeIdStack) {
		if (pathNode.getNextComponents().size() <= 0) {
			// System.err.println(maxPathWcet + " current wcet is " + currentWcet);
			if (currentWcet > maxPathWcet)
				maxPathWcet = currentWcet;
			// System.err.println("max wcet is " + maxPathWcet);
			return maxPathWcet;
		}

		if (pathNodeIdStack.size() > 1) {
			pathNodeIdStack.pop();
			for (String id : pathNodeIdStack) {
				if (pathNode.getId().equals(id)) {
					return maxPathWcet;
				}
			}
		}
		for (PathNode nextPathNode : pathNode.getNextComponents()) {
			currentWcet += nextPathNode.getWcet();
			pathNodeIdStack.add(nextPathNode.getId());
			// System.out.println(pathNode.getId());
			// System.out.println("current wcet is " + currentWcet);
			maxPathWcet = calculateMaxWcet(maxPathWcet, nextPathNode, currentWcet, pathNodeIdStack);
			currentWcet -= nextPathNode.getWcet();
			pathNodeIdStack.pop();
		}
		return maxPathWcet;
	}

	public static void main(String[] args) {
		excute();
	}
}
