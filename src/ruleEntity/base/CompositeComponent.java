package ruleEntity.base;

import java.util.Map;
import java.util.Set;

import entity.CommunicationChannel;
import entity.Component;
import entity.Connection;
import entity.Systemm;
import entity.Task;

public class CompositeComponent {
	/**
	 * 规则5：子组件定义的输入输出接口，应该包含复合组件定义的输入输出接口 aadl和sysml linkpoint和port之间有连线 不知道咋处理
	 * 先找与组件有关的channel，根据channel获取复合组间的接口
	 * 根据这些接口找component和port，遍历connection，若一段连着接口，另一端一定是port
	 */
	public static void excute(Systemm system) {

		Map<String, CommunicationChannel> channelMap = system.getChannelMap();
		Map<String, Component> comMap = system.getComMap();

		for (CommunicationChannel channel : channelMap.values()) {
			// System.out.println(channel);
			Map<String, String> attrMap = channel.getAttrMap();
			String deString = attrMap.get("dest");
			String sourceString = attrMap.get("source");
			// 根据linkpoint的id找compo
			Component comDes = findComponent(deString, system);
			Component comSource = findComponent(sourceString, system);
			System.out.println("------------------------------");
		}

	}

	private static Component findComponent(String idLinkpoint, Systemm system) {
		Set<String> keySetPortSet = null;
		String des = null;
		String source = null;
		for (Component values : system.getComMap().values()) {
			// 获取port
			if (values.getTaskList() != null) {
				for (Task task : values.getTaskList().values()) {
					if (task.getPortList() != null) {
						Set<String> keySet = task.getPortList().keySet();
						keySetPortSet.addAll(keySet);
					}
				}
				// 获取connection
				if (values.getConnectionList() != null) {
					for (Connection connection : values.getConnectionList().values()) {
						des = connection.getAttrsMap().get("dest");
						source = connection.getAttrsMap().get("source");
					}
				}

				if (des == idLinkpoint) {

					System.out.println(idLinkpoint + "包含子组件的输出接口吗：" + keySetPortSet.contains(source));
				} else if (source == idLinkpoint) {
					System.out.println(idLinkpoint + "包含子组件的输入接口吗：" + keySetPortSet.contains(des));
				} else {
					System.out.println("不包含");
				}

			}

		}

		return null;
	}
}
