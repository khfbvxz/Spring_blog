package com.myspring.springBBS.Reply;

public class ReplyDTO {	
	private int r_no;
	private String r_id;
	private String r_content;
	private String r_date;
	private int r_bno;

	public int getR_no() {return r_no;	}
	public void setR_no(int r_no) {	this.r_no = r_no;	}
	public String getR_id() {	return r_id;	}
	public void setR_id(String r_id) {	this.r_id = r_id;	}
	public String getR_content() {	return r_content;	}
	public void setR_content(String r_content) {this.r_content = r_content;	}
	public String getR_date() {	return r_date;	}
	public void setR_date(String r_date) {	this.r_date = r_date;	}
	public int getR_bno() {	return r_bno;	}
	
	public void setR_bno(int r_bno) {this.r_bno = r_bno;}

}//class END
