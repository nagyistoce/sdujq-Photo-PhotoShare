package org.sdu.db.dao;

import org.sdu.db.DBHelper;
import org.sdu.db.pojo.Dynamic;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class DynamicDao extends BaseDaoImpl<Dynamic> {

	public DynamicDao(Context c) {
		super(new DBHelper(c));
	}

}
