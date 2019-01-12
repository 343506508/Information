package com.project.tools;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @param <T> Page中记录的类型.
 * 
 */
public class Page<T> {

	protected  int pageNumber;
	//-- 返回结果 --//
	protected List<T> result = new ArrayList<T>();
	protected int totalCount = 0;
	
	//-- 构造函数 --//
	public Page() {}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}