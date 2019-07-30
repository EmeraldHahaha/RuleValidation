package ruleEntity.base;

import static util.DataBaseConnection.conn;

import java.util.Set;

import entity.Systemm;
import util.ParseXML;

public class ComponentType {
	/**
	 * 规则6：sysml的组件类型和simulink的component组件类型一致
	 * 
	 * @throws Exception
	 */
	public static void excute(Systemm sysml, Systemm simulink) throws Exception {

		Set<String> keySetSysML = sysml.getComMap().keySet();
		Set<String> keySetSimulink = simulink.getComMap().keySet();
		for (String sysml_id : keySetSysML) {
			String sqlString = "select simulink_id from mapping where sysml_id=" + sysml_id;
			String simulink_id = conn(sqlString, "simulink_id");
			String sysml_name = sysml.getComMap().get(sysml_id).getAttrMap().get("name");
			if (simulink_id != null && keySetSimulink.contains(simulink_id)) {
				String simulink_name = simulink.getComMap().get(simulink_id).getAttrMap().get("name");
				String typeOfSysml = sysml.getComMap().get(sysml_id).getAttrMap().get("type");
				String typeOfSimulink = simulink.getComMap().get(simulink_id).getAttrMap().get("type");
				if (typeOfSimulink != null && typeOfSysml != null && typeOfSysml == typeOfSimulink) {
					System.out.println(sysml_name + "与" + simulink_name + "类型一致");
				} else {
					System.out.println(sysml_name + "与" + simulink_name + "类型不一致");
				}

			} else {
				System.out.println("没有找到与" + sysml_name + "相对应的 sinmulink组件");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Systemm sysmlSystemm = ParseXML.parseXML("src/sysml.xml");
		Systemm simulinkSystemm = ParseXML.parseXML("src/simulink.xml");
		excute(sysmlSystemm, simulinkSystemm);

	}
}
