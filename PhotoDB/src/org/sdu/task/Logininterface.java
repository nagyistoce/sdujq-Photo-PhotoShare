package org.sdu.task;
/**
 * ��½
 * @author shadow
 *
 */
public interface Logininterface {

	/**
	 * ��½
	 * @param id �˺�
	 * @param pwd ����
	 * @return �Ƿ��½�ɹ�
	 */
	boolean login(int id,String pwd);
	/**
	 * ��½
	 * @param name �û���
	 * @param pwd ����
	 * @return �Ƿ��½�ɹ�
	 */
	boolean login(String name,String pwd);
	/**
	 * �õ��˴ε�½����ʾ��Ϣ
	 * @return ��½��ʾ
	 */
	String getLogInfo();
}
