package org.sdu.db.dao;

import org.sdu.db.DBHelper;
import org.sdu.db.pojo.Argument;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class ArgumentDao extends BaseDaoImpl<Argument> {

	public ArgumentDao(Context c) {
		super(new DBHelper(c));
	}

}
