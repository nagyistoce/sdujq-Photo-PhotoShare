package org.sdu.db.pojo;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Table;

@Table(name="t_friend")
public  class Friend implements java.io.Serializable {

	private static final long serialVersionUID = 8010615403663409174L;
	@Column(name="user_1")
	private Integer user_1;
	@Column(name="user_2")
	private Integer user_2;
	public Integer getUser_1() {
		return user_1;
	}
	public void setUser_1(Integer user_1) {
		this.user_1 = user_1;
	}
	public Integer getUser_2() {
		return user_2;
	}
	public void setUser_2(Integer user_2) {
		this.user_2 = user_2;
	}

}