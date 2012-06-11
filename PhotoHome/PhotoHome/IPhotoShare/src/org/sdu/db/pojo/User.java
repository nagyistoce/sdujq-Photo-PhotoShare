package org.sdu.db.pojo;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

@Table(name="t_user")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = -4904063637699684120L;
	@Id
	@Column(name="id")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="pwd")
	private String pwd;
	@Column(name="email")
	private String email;
	@Column(name="head")
	private byte[] head;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getHead() {
		return head;
	}
	public void setHead(byte[] head) {
		this.head = head;
	}


}