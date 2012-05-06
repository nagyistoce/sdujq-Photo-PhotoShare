package org.sdu.task;

import java.util.List;

import org.sdu.db.pojo.User;

/**
 * �û���Ϣ�ȹز���
 * @author shadow
 *
 */
public interface UserAction {
	/**
	 * �õ���ǰ��½�û�
	 * @return ��ǰ��½User����
	 */
	User getCurrentUser();
	/**
	 * �õ��û�u�ĺ���id�б�
	 * @param u ���ٰ���id 
	 * @return ����id��list
	 */
	List<Integer> getFriendIdList(User u);
	/**
	 * �õ��û�u�ĺ����б�
	 * @param u ���ٰ���id
	 * @return list<���Ѷ���>
	 */
	List<User> getFriendList(User u);
	/**
	 * ��u����Ϊģ������û���Ϣ�����ڸ������롢ͷ��ȡ�
	 * @param u ע�⣺1��id�޷����Ĵ˴��Զ�����id��2��ֵΪnull�����Դ˴��Զ�����
	 * @return �Ƿ���³ɹ�
	 */
	boolean changeCurrentUserInfo(User u);
}
