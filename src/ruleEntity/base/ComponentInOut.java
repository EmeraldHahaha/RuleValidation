package ruleEntity.base;

import static util.DataBaseConnection.conn;

import java.util.Set;

import entity.Systemm;
import util.ParseXML;;

public class ComponentInOut {
	/**
	 * 规则1：需求中某个组件的输入输出接口，一定与子系统设计模型中的某个输入输出接口对应
	 * 
	 * @throws Exception
	 */
	public static void excute(Systemm sysml, Systemm simulink) throws Exception {

		Set<String> keySetSysML = sysml.getComMap().keySet();
		Set<String> keySetSimulink = simulink.getComMap().keySet();
		for (String sysml_id : keySetSysML) {
			String sqlString = "select simulink_id from mapping where sysml_id=" + sysml_id;
			String simulink_id = conn(sqlString, "simulink_id");
			// System.out.println(simulink_id);

			if (simulink_id != null && keySetSimulink.contains(simulink_id)) {
				// 组件对应
				Set<String> keySetLinkpointOfSysml = sysml.getComMap().get(sysml_id).getLinkpointList().keySet();
				Set<String> keySetLinkpointOfSimulink = simulink.getComMap().get(simulink_id).getLinkpointList()
						.keySet();

				for (String keyLinkpointOfSysml : keySetLinkpointOfSysml) {
					// System.out.println(keyLinkpointOfSysml);
					String sqlLinkpoint = "select simulink_id from mapping where sysml_id=" + keyLinkpointOfSysml;
					String linkpointIdOfSimulink = conn(sqlLinkpoint, "simulink_id");
					if (linkpointIdOfSimulink != null && keySetLinkpointOfSimulink.contains(linkpointIdOfSimulink)) {
						System.out.println("验证成功");
					}
				}
				System.out.println("验证成功");

			} else {
				// System.out.println("不存在");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Systemm sysmlSystemm = ParseXML.parseXML("src/sysml.xml");
		Systemm simulinkSystemm = ParseXML.parseXML("src/simulink.xml");
		excute(sysmlSystemm, simulinkSystemm);

	}

}
