package org.lr.ibatis.bean;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private int id;
	private String name;
	private String info;
	private byte[] info_blob ;

    public int getId()
    {
		return id;
	}
    public void setId(int id)
    {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public byte[] getInfo_blob() {
		return info_blob;
	}
	public void setInfo_blob(byte[] info_blob) {
		this.info_blob = info_blob;
	}
}
