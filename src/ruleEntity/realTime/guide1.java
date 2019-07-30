package ruleEntity.realTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Component;
import entity.Connection;
import entity.Partition;
import entity.Systemm;
import entity.Task;

/**
 * 规则1：系统架构模型中串行流程中子组件的最坏执行时间之和小于等于复合组件的最坏执行时间 没写完
 * 
 * @author Emerald
 *
 */
public class guide1 {

	public static void excute(Systemm system) {
		Map<String, String> subTaskMap = new HashMap<String, String>();
		Map<String, String> sourceMap = new HashMap<String, String>();
		Map<String, String> destMap = new HashMap<String, String>();
		for (Component com : system.getComMap().values()) {
			String wcetOfCom = com.getAttrMap().get("wcet");
			// 找子组件task，它存在于两个部分：partition的子或者他的同级task
			if (com.getTaskList() != null) {
				for (Task subTask : com.getTaskList().values()) {
					subTaskMap.put(subTask.getAttrs().get("id"), subTask.getAttrs().get("wcet"));
				}
			}
			if (com.getPartitionList() != null) {
				for (Partition partition : com.getPartitionList().values()) {
					if (partition.getSubTasksList() == null) {
						continue;
					}
					for (Task subTask : partition.getSubTasksList().values()) {
						subTaskMap.put(subTask.getAttrs().get("id"), subTask.getAttrs().get("wcet"));
					}
				}
			}
			if (com.getConnectionList() != null) {
				for (Connection connection : com.getConnectionList().values()) {
					String idString = connection.getAttrs().get("id");
					String sourceString = connection.getAttrs().get("source");
					String destString = connection.getAttrs().get("dest");
					sourceMap.put(idString, sourceString);
					destMap.put(idString, destString);
					// System.out.println(idString + " " + sourceString + " " + destString);
				}
			}
		}
		// path(subTaskMap,connectionMap);
		// 生成路径，找第一个点，他没有入边，即source里面没有他。这有错误！
		for (String taskId : subTaskMap.keySet()) {
			if (destMap.containsKey(taskId) == false || sourceMap.containsKey(taskId)) {
				// 是第一个点
				String nextTask = taskId;
				List<String> pathTasks = new ArrayList<String>();
				// 找下一个task
				while (nextTask != null) {
					pathTasks.add(nextTask);

					nextTask = destMap.get(findID(taskId, sourceMap));
				}
				System.out.println(pathTasks);
				System.out.println("----我是path分割线----");
			} else {
				System.out.println("我是单个边");
			}

		}
	}

	public static String findID(String id, Map<String, String> map) {
		// 根据value查找key
		for (String s : map.keySet()) {
			if (map.get(s) == id) {
				return s;
			}
		}
		return null;
	}
}
