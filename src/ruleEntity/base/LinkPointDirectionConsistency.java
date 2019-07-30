package ruleEntity.base;

import java.util.Map;
import java.util.Set;

import entity.Linkpoint;
import entity.Systemm;

public class LinkPointDirectionConsistency {

	/**
	 * 规则3：有消息交换的组件间，对应的交换接口的方向是一致的，输出端口对应下一个组件的输入端口。
	 * 
	 * 
	 * @throws Exception
	 */
	public static void excute(Systemm sysml) {
		/*
		 * 根据每一条channel来查看其目的或源，分别对应方向为in、out。或者inout
		 */

		Set<String> keySetChannel = sysml.getChannelMap().keySet();
		for (String keyChannel : keySetChannel) {

			Map<String, String> attrMap = sysml.getChannelMap().get(keyChannel).getAttrMap();
			String source = attrMap.get("source");
			String dest = attrMap.get("dest");

			// 根据id获取linkpoint和其方向
			String dirSource = checkDir(sysml, source);
			String dirDest = checkDir(sysml, dest);
			System.out.println("对于channel：" + keyChannel + "   source是：" + source + "    dest是：" + dest + "    方向是："
					+ dirSource + dirDest);
			if ((dirSource == "out" || dirSource == null) && (dirDest == "in" || dirDest == null)) {
				System.out.println("端口方向具有一致性");

			} else {
				System.out.println("方向不匹配：source的方向是：" + dirSource + " 	destination的方向是：" + dirDest);
			}
			System.out.println();
		}
	}

	private static String checkDir(Systemm sysml, String idString) {
		String dir = null;
		String name = null;
		Set<String> keySetCom = sysml.getComMap().keySet();
		for (String keyCom : keySetCom) {
			Map<String, Linkpoint> linkpointList = sysml.getComMap().get(keyCom).getLinkpointList();
			if (linkpointList != null && linkpointList.containsKey(idString)) {
				// System.out.println(linkpointList);
				name = linkpointList.get(idString).getAttrs().get("name");
				dir = linkpointList.get(idString).getAttrs().get("direction");
			}
		}
		if (name != null) {
			System.out.println(name + "找不到对应的端口");
		}

		return dir;
	}

}
