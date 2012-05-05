package org.sdu.db.pojo;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;


@Table(name="t_dynamicType")
public  class DynamicType implements java.io.Serializable {

	private static final long serialVersionUID = 2698473889503023373L;
	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="info")
	private String info;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}