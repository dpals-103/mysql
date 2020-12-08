package test2.dto;

import java.util.Map;

public class Article {

	public int boardId; 
	public String title;
	public String body;
	public int memberId;
	public int hit;
	public int recommand;
	public String regDate;
	public String updateDate;
	public int id;
	public String extra_writer; 
	
	public Article(Map<String, Object> map) {
		this.boardId = (int)map.get("boardId");
		this.id = (int)map.get("id");
		this.title = (String)map.get("title");
		this.body = (String)map.get("body");
		this.extra_writer = (String)map.get("extra_writer");
		this.memberId = (int)map.get("memberId");
		this.hit = (int)map.get("hit");
		this.recommand = (int)map.get("recommand");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
	}

}
