package verification;

import entity.Systemm;
import ruleEntity.realTime.guide1;
import util.ParseXML2;

public class ver {

	public static void main(String[] args) throws Exception {

		/*
		 * 解析文件
		 */
		Systemm sysmlSystemm = ParseXML2.parseXML("src/sysml.xml");
		Systemm simulinkSystemm = ParseXML2.parseXML("src/simulink.xml");
		Systemm aadlSystemm = ParseXML2.parseXML("src/aadl.xml");
//		Systemm testSystemm = ParseXML2.parseXML("src/testXML.xml");
		/*
		 * 验证
		 */
		// ComponentInOut.excute(sysmlSystemm, simulinkSystemm);
		// ComponentType.excute(sysmlSystemm, simulinkSystemm);
		// CompositeComponent.excute(sysmlSystemm, simulinkSystemm);
		// LinkPointDirectionConsistency.excute(sysmlSystemm);
		// LinkPointTypeAndPeriod.excute(sysmlSystemm, simulinkSystemm, aadlSystemm);
		// 我还是有问题的没实现好CompositeComponent.excute(sysmlSystemm);
		guide1.excute(aadlSystemm);

	}

}
