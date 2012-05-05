package org.sdu.db;

import org.sdu.db.pojo.Argument;
import org.sdu.db.pojo.Collection;
import org.sdu.db.pojo.Dynamic;
import org.sdu.db.pojo.DynamicType;
import org.sdu.db.pojo.Friend;
import org.sdu.db.pojo.Photo;
import org.sdu.db.pojo.User;

import android.content.Context;

import com.tgb.lk.ahibernate.util.MyDBHelper;

public class DBHelper extends MyDBHelper {
	private static final String DBNAME = "p.db";
	private static final int DBVERSION = 1;

	private static final Class<?>[] classes = { Argument.class,
			Collection.class, Dynamic.class, DynamicType.class, Friend.class,
			Photo.class, User.class };

	public DBHelper(Context context) {
		super(context, DBNAME, null, DBVERSION, classes);
	}

}