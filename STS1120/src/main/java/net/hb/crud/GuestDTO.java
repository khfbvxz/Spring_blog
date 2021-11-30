package net.hb.crud;

public class GuestDTO {
  private int sabun;
  private String name;
  private String title;
  private java.util.Date  wdate;
  private int pay;
  private int hit;
  private String email;
  private int rn;
  // #{code},  #{title},  #{pay},  #{email}
  private int code ;
  
    public int getCode() {return code;}
	public void setCode(int code) {	this.code = code;}
  
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

}//class END
