package com.lestath.xmpro.repository;

import java.util.List;



public interface Repository<T> {
	public List<T> findAll();
	public T findById(Long id);
	public void save(T obj);
	public void delete(T obj);
	public void update(T obj);
	
}
