package org.sdu.db.dao;

import org.sdu.db.DBHelper;
import org.sdu.db.pojo.DynamicType;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class DynamicTypeDao extends BaseDaoImpl<DynamicType> {

	public DynamicTypeDao(Context c) {
		super(new DBHelper(c));
	}

}
