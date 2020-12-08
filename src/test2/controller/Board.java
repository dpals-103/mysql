package test2.controller;

import java.util.Map;

public class Board {
	
	public int id;
	public String boardName;
	
	public Board(Map<String, Object> map) {
		this.id = (int)map.get("id");
		this.boardName = (String)map.get("boardName");
	}
}

