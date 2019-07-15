package io.vertx.pointer;

import java.util.concurrent.atomic.AtomicInteger;

public class Menu 
{
	private static final AtomicInteger COUNTER = new AtomicInteger();

	private final int _iId;

	private String _sDishType;

	private String _sDishName;

	public Menu(String type, String dish) 
	{
		_iId = COUNTER.getAndIncrement();
		_sDishType = type;
		_sDishName = dish;
	}

	public Menu() 
	{
		_iId = COUNTER.getAndIncrement();
	}

	public String getName() 
	{
		return _sDishType;
	}

	public String getDishName() 
	{
		return _sDishName;
	}

	public int getId() 
	{
		return _iId;
	}
		
	public void setName(String type) 
	{
		_sDishType = type;
	}

	public void setDishName(String dish) 
	{
		_sDishName = dish;
	}
}
