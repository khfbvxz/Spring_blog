package com.myspring.springBBS.Pager;

public class Pager {
	private int curPage;
	private int startPage;
	private int endPage;
	private int start;
	private int end;
	private int total;
	private int pagecount;
	
	private String skey;
	private String sval;
	private String returnpage;
	
	public Pager(){}	
	public Pager(int total, int curPage, String skey, String sval) {
		this.curPage = curPage;
		this.total = total;	
		this.skey = skey;
		this.sval = sval;
		setRange();
		setReturn();
	}
	
	public void setRange() {
		if(total%5==0)
			pagecount=total/5;
		else
			pagecount=total/5 + 1;
		
		end=total-(curPage-1)*5 ; 
		start=end-4; 
		
		int temp=(curPage-1)%10 ; 
		startPage=curPage-temp;
		endPage=startPage+9; 
		
	    if(endPage>pagecount) { endPage=pagecount; }
	}
	
	public void setReturn() {
		 returnpage="&keyfield="+skey+"&keyword="+sval;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		System.out.println("Pager자바문서 skey = " + skey);
		this.skey = skey;
	}

	public String getSval() {
		return sval;
	}

	public void setSval(String sval) {
		this.sval = sval;
	}

	public String getReturnpage() {
		return returnpage;
	}

	public void setReturnpage(String returnpage) {
		this.returnpage = returnpage;
	}
	
} //Pager class END
