package com.lestath.xmpro.repository.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.lestath.xmpro.repository.Repository;
import com.lestath.xmpro.repository.csv.core.MyCsvManager;

public abstract class CsvRepository <T> implements Repository<T>{
	private ArrayList<T> entities;

	protected MyCsvManager manager;
	public CsvRepository(MyCsvManager manager, ArrayList<T> entities){
		setManager(manager);
		setEntities(entities);
	}

	@Override
	public List<T> findAll() {
		setEntities(new ArrayList<T>());
		try {
			List<String [] > rawlist = getManager().getAll();
			for(int i = 1; i<rawlist.size();i++){
				getEntities().add(csvToObj(rawlist.get(i)));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getEntities();
	}

	
	@Override
	public void save(T obj){
		try {
			getManager().add(objToCsv(obj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public abstract void delete(T obj);

	@Override
	public abstract T findById(Long id);

	@Override
	public abstract void update(T obj);
	
	protected abstract T csvToObj(String [] cols);
	
	protected abstract String [] objToCsv(T obj);
	
	
	public MyCsvManager getManager() {
		return manager;
	}
	public void setManager(MyCsvManager manager) {
		this.manager = manager;
	}

	public ArrayList<T> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<T> entities) {
		this.entities = entities;
	}
}