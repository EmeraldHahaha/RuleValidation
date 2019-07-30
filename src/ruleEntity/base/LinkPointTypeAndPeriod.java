package ruleEntity.base;

import java.util.Map;
import java.util.Set;

import entity.Linkpoint;
import entity.Systemm;

public class LinkPointTypeAndPeriod {
	/**
	 * 规则2：有数据交换的组件间，对应的交换接口的数据类型和周期必须一致。 根据channel获取两个端口，获取其属性作对比
	 */
	static String dataType = null;
	static String period = null;
	static String name = null;

	public static void excute(Systemm... system) {
		for (Systemm sys : system) {
			check(sys);
			System.out.println("------------------------------------------");
		}
	}

	private static void check(Systemm system) {
		Set<String> keySetChannel = system.getChannelMap().keySet();
		for (String keyChannel : keySetChannel) {

			Map<String, String> attrMap = system.getChannelMap().get(keyChannel).getAttrMap();
			String source = attrMap.get("source");
			String dest = attrMap.get("dest");
			// 检查sysml、aadl、simulink
			checkTypeAndPeriod(system, source);
			String sourceName = name;
			String sourceDataType = dataType;
			String sourcePeriod = period;
			checkTypeAndPeriod(system, dest);
			String destName = name;
			String destDataType = dataType;
			String destPeriod = period;
			if ((sourceDataType == destDataType) && (sourcePeriod == destPeriod)) {
				System.out.println(sourceName + "与" + destName + "的数据类型和周期一致");
			} else {
				System.out.println(sourceName + "与" + destName + "的数据类型和周期不一致");
			}
			System.out.println();

		}

	}

	private static void checkTypeAndPeriod(Systemm system, String idString) {

		Set<String> keySetCom = system.getComMap().keySet();
		for (String keyCom : keySetCom) {
			Map<String, Linkpoint> linkpointList = system.getComMap().get(keyCom).getLinkpointList();
			if (linkpointList != null && linkpointList.containsKey(idString)) {
				// System.out.println(linkpointList);
				name = linkpointList.get(idString).getAttrs().get("name");
				dataType = linkpointList.get(idString).getAttrs().get("datatype");
				period = linkpointList.get(idString).getAttrs().get("period");
			}
		}
	}

}
