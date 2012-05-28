package org.sdu.db.dao;

import org.sdu.db.DBHelper;
import org.sdu.db.pojo.Photo;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class PhotoDao extends BaseDaoImpl<Photo> {

	public PhotoDao(Context c) {
		super(new DBHelper(c));
	}

}
