package org.sdu.db.dao;

import org.sdu.db.DBHelper;
import org.sdu.db.pojo.Friend;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class FriendDao extends BaseDaoImpl<Friend> {

	public FriendDao(Context c) {
		super(new DBHelper(c));
	}

}
