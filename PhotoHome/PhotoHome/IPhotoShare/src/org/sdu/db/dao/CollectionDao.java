package org.sdu.db.dao;

import org.sdu.db.DBHelper;
import org.sdu.db.pojo.Collection;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class CollectionDao extends BaseDaoImpl<Collection> {

	public CollectionDao(Context c) {
		super(new DBHelper(c));
	}

}
