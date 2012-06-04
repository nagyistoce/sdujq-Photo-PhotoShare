package org.sdu.task;

import java.util.List;

import org.sdu.db.pojo.Argument;
import org.sdu.db.pojo.Collection;
import org.sdu.db.pojo.Dynamic;
import org.sdu.db.pojo.Friend;
import org.sdu.db.pojo.Photo;
import org.sdu.db.pojo.User;

import android.graphics.Bitmap;

/**
 * �û���Ϣ�ȹز���
 * @author shadow
 *
 */
public interface IUserAction {
	/**
	 * �õ���ǰ��½�û�
	 * @return ��ǰ��½User����
	 */
	User getCurrentUser();
	/**
	 * �õ��û�u�ĺ��ѹ�ϵ�б�
	 * @param u ���ٰ���id 
	 * @return ���ѹ�ϵ�б� Friend.user_2Ϊ����id
	 */
	List<Friend> getFriendShipIdList(User u);
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
	/**
	 * �õ�ĳ�û����������б�
	 * @param u �û�
	 * @return ���100�������µ�id
	 */
	List<Integer> getDynamicIdList(User u);
	/**
	 * �õ�top num������ͼƬ���б�
	 * @param num ����
	 * @return ͼƬid�б�
	 */
	List<Integer> getHotPhotoIdList(int num);
	/**
	 * ���Ӷ�̬
	 * @param dynamic ��̬����
	 */
	void addDynamic(Dynamic dynamic);
	/**
	 * ����ͼƬ
	 * @param bmp Ҫ�����Bitmap����
	 */
	void savePhoto(Bitmap bmp,String title,String detail);
	/**
	 * �ӹ�ע
	 * @param uid Ŀ���ע��
	 */
	void makeFriendWith(int uid);
	/**
	 * ����ĳ��ͼƬ
	 * @param photoId ͼƬid
	 */
	void collect(int photoId);
	/**
	 * ��ĳͼƬ��������
	 * @param pid ͼƬid
	 * @param words ��������
	 */
	void makeArgument(int pid,String words);
	
	Photo getPhotoById(int id);
	
	Bitmap getBitmap(Photo p);
	
	List<Argument> getArgumentList(int id);
	
	List<Collection> getCollectionList();
}
