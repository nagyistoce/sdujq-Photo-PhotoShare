package org.sdu.task;

public interface Login {

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
