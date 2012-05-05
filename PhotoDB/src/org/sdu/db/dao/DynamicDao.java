package org.sdu.db.dao;

import org.sdu.db.DBHelper;
import org.sdu.db.pojo.Argument;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class DynamicDao extends BaseDaoImpl<Argument> {

	public DynamicDao(Context c) {
		super(new DBHelper(c));
	}

}
