package org.sdu.db.pojo;

import java.sql.Timestamp;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

@Table(name="t_argument")
public  class Argument implements java.io.Serializable {

	private static final long serialVersionUID = 5403385752725137032L;
	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="userId")
	private Integer userId;
	@Column(name="time")
	private long time;
	@Column(name="photoId")
	private Integer photoId;
	@Column(name="info")
	private String info;
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
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public Integer getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}