package net.hb.crud;

import org.springframework.web.multipart.MultipartFile; //새로추가

public class BoardDTO {
  private int sabun;
  private String name;
  private String title;
  private String content;
  private String gender;
  private int pay;
  private int rn;
  private java.util.Date  wdate;
  private int hit;
  private String email;
  
	private  int hobby_idx; //추가
	private  String hobby; //추가
	private  String img_file_name ; //추가
	private  MultipartFile upload_f ;  //추가 <input type=file name=uplaod_f
	
	 public int getHobby_idx() { return hobby_idx;	}
	 public void setHobby_idx(int hobby_idx) {this.hobby_idx = hobby_idx;}
	 public String getHobby() {return hobby;}
	 public void setHobby(String hobby) {this.hobby = hobby;}
	 public String getImg_file_name() {return img_file_name;}
	 public void setImg_file_name(String img_file_name) {this.img_file_name = img_file_name;	}
	 public MultipartFile getUpload_f() {return upload_f;}
	 public void setUpload_f(MultipartFile upload_f) {this.upload_f = upload_f;	}
	
  
	public int getSabun() {return sabun;}
	public void setSabun(int sabun) {this.sabun = sabun;}
	public java.util.Date getWdate() {return wdate;	}
	public void setWdate(java.util.Date wdate) {this.wdate = wdate;	}
	public int getHit() {return hit;}
	public void setHit(int hit) {this.hit = hit;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public int getPay() {return pay;}
   public void setPay(int pay) {this.pay = pay;}
   public int getRn() {return rn;}
   public void setRn(int rn) {this.rn = rn;}
	
  public String getName() {return name;}
  public void setName(String name) {this.name = name;	}
  public String getTitle() {return title;}
  public void setTitle(String title) {this.title = title;}
  public String getContent() {return content;	}
  public void setContent(String content) {this.content = content;}
  public String getGender() {	return gender;	}
  public void setGender(String gender) {	this.gender = gender;}

}//class END
