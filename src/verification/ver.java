package verification;

import entity.Systemm;
import ruleEntity.realTime.guide1;
import util.ParseXML2;

public class ver {

	public static void main(String[] args) throws Exception {

		/*
		 * �����ļ�
		 */
		Systemm sysmlSystemm = ParseXML2.parseXML("src/sysml.xml");
		Systemm simulinkSystemm = ParseXML2.parseXML("src/simulink.xml");
		Systemm aadlSystemm = ParseXML2.parseXML("src/aadl.xml");
//		Systemm testSystemm = ParseXML2.parseXML("src/testXML.xml");
		/*
		 * ��֤
		 */
		// ComponentInOut.excute(sysmlSystemm, simulinkSystemm);
		// ComponentType.excute(sysmlSystemm, simulinkSystemm);
		// CompositeComponent.excute(sysmlSystemm, simulinkSystemm);
		// LinkPointDirectionConsistency.excute(sysmlSystemm);
		// LinkPointTypeAndPeriod.excute(sysmlSystemm, simulinkSystemm, aadlSystemm);
		// �һ����������ûʵ�ֺ�CompositeComponent.excute(sysmlSystemm);
		guide1.excute(aadlSystemm);

	}

}
