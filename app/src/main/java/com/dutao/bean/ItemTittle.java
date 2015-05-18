package com.dutao.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * @author dutao
 * @version 1.0 
 * @date 2015-5-14
 */
public class ItemTittle implements Serializable{
	private static final long serialVersionUID = 7776916829697266089L;
    private String tittle;
	private Date time;
	private String imageUrl;
	
	public ItemTittle(String tittle, Date time, String imageUrl) {
		super();
		this.tittle = tittle;
		this.time = time;
		this.imageUrl = imageUrl;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
