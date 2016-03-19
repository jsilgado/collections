package com.jsilgado.collections.controller;

import java.util.List;

public interface ControllerTemplate<T> {

	public T initialize();

	public List<T> getAll();

	public T getById(String id);

	public void insert(T t);

	public void update(T t);

	public void delete(String id);

}
