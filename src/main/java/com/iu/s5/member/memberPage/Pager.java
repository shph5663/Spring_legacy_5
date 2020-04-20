package com.iu.s5.member.memberPage;

public class Pager {
	
	private Long curPage;
	private Integer perPage;
	
	private long startRow;
	private long lastRow;
	
	private long totalPage;
	private long totalBlock;
	private long curBlock;
	private long startNum;
	private long lastNum;
	
	private String kind;
	private String search;
	
	public void makeRow() {
		this.startRow = (this.getCurPage()-1)*this.getPerPage()+1;
		this.lastRow = this.getCurPage()*this.getPerPage();
	}
	
	public void makePage(long totalCount) {
		//1. 전체 멤버의 수
		
		//2. 멤버의 수 토탈페이지로 계산
		this.totalPage = totalCount/this.getPerPage();
		if (totalCount % this.getPerPage() != 0) {
			this.totalPage++;
		}
		//3. 토탈페이지로 토탈 블럭계산
		long perBlock=5L;
		this.totalBlock = totalPage/perBlock;
		if (totalPage%perBlock != 0) {
			this.totalBlock++;
		}
		
		//4. cur페이지로 cur블럭 팢기
		this.curBlock = this.curPage/perBlock;
		if (this.curPage%perBlock != 0) {
			this.curBlock++;
		}
		
		//5. curBlock으로 startNum, LastNum 계산
		this.lastNum = this.curBlock*perBlock;
		this.startNum = (this.curBlock-1)*perBlock+1;
		
		if (this.curBlock==this.totalBlock) {
			this.lastNum=this.totalPage;
		}

	}
	
	

	
	public Long getCurPage() {
		if(this.curPage == null || this.curPage==0) {
			this.curPage=1L;	
		}
		return curPage;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public Integer getPerPage() {
		if(this.perPage == null || this.perPage==0) {
			this.perPage=10;
		}
		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}


	public long getStartRow() {
		return startRow;
	}


	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}


	public long getLastRow() {
		return lastRow;
	}


	public void setLastRow(long lastRow) {
		this.lastRow = lastRow;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(long totalBlock) {
		this.totalBlock = totalBlock;
	}

	public long getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(long curBlock) {
		this.curBlock = curBlock;
	}

	public long getStartNum() {
		return startNum;
	}

	public void setStartNum(long startNum) {
		this.startNum = startNum;
	}

	public long getLastNum() {
		return lastNum;
	}

	public void setLastNum(long lastNum) {
		this.lastNum = lastNum;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSearch() {
		if (this.search==null) {
			this.search="";
		}
		
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	

	
}
