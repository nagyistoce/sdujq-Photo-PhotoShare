package org.sdu.db.pojo;

import java.sql.Timestamp;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

@Table(name = "t_Dynamic")
public class Dynamic implements java.io.Serializable {

	private static final long serialVersionUID = 4384873673109447229L;
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "typeId")
	private Integer typeId;

	@Column(name = "userId")
	private Integer userId;
	
	@Column(name = "photoId")
	private Integer photoId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "tag")
	private String tag;
	
	@Column(name = "time")
	private long time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}