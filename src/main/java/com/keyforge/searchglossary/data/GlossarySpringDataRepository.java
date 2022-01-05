package com.keyforge.searchglossary.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface GlossarySpringDataRepository extends CrudRepository<GlossaryEntry,Integer>{
	public GlossaryEntry findById(int id);
	
	public List<GlossaryEntry> findByKeyword(String keyword);
	
	public List<GlossaryEntry> findAll();
}
