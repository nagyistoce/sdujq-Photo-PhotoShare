package org.sdu.task;

public interface Reg {
	/**
	 * ע�����û�
	 * @param name �û���
	 * @param email ����
	 * @param pwd ����
	 * @return �Ƿ��½�ɹ�
	 */
	boolean reg(String name,String email,String pwd);
	/**
	 * �õ�ע����ʾ��Ϣ
	 * @return ע����ʾ��Ϣ
	 */
	String getRegInfo();
}
