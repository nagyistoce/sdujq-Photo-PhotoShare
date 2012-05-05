package org.sdu.db.pojo;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;


@Table(name="t_Collection")
public  class Collection implements java.io.Serializable {

	private static final long serialVersionUID = 1754675090764202232L;
	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="userId")
	private Integer userId;
	@Column(name="photoId")
	private Integer photoId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

}