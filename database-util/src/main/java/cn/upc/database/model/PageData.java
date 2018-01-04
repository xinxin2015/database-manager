package cn.upc.database.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageData<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1220133925815297906L;

	private int pageNum;
	private int pageSize;
	private int startRow;
	private int endRow;
	private long totalCount;
	private int totalPage;
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	private List<E> result = new ArrayList<>();
	
	public PageData(int pageNum,int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
		this.endRow = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<E> getResult() {
		return result;
	}

	public void setResult(List<E> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "PageData [pageNum=" + pageNum + ", pageSize=" + pageSize + ", startRow=" + startRow + ", endRow="
				+ endRow + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", result=" + result + "]";
	}

	
	
	
}
