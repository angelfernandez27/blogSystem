package com.blogsystem.dto;

import java.util.List;

public class PublicationResponse {
	
	private List<PublicationDTO> content;
	private int pageNumb;
	private int pageSize;
	private Long totalElements;
	private int totalPages;
	private boolean lastPage;
	public List<PublicationDTO> getContent() {
		return content;
	}
	public void setContent(List<PublicationDTO> content) {
		this.content = content;
	}
	public int getPageNumb() {
		return pageNumb;
	}
	public void setPageNumb(int pageNumb) {
		this.pageNumb = pageNumb;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public PublicationResponse() {
		super();
	}
	
	
}
