package com.keyforge.searchglossary.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="glossary")
public class GlossaryEntry {
	
	@Id
	private int id;
	
	private String keyword;
	private String description;
	
	public GlossaryEntry() {
		super();
	}
	
	public GlossaryEntry(int id, String keyword, String description) {
		super();
		this.id = id;
		this.keyword = keyword;
		this.description = description;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
