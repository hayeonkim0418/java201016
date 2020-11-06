package java201106;

public class Article {
	private int id;
	private String title;
	private String body;
	private int mid;
	private String regDate;
	private int hit;


	public Article() {

	}

	public Article(int id, String title, String body, int mid, String regDate, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.mid = mid;
		this.regDate = regDate;
		this.hit = hit;
	}

	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getPropertiesByFlag(int flag) {
		String str = "";
		if (flag == 1) {
			str = this.getTitle();
		} else if (flag == 2) {
			str = this.getBody();
		} else if (flag == 3) {
			str = this.getBody() + this.getBody();
		} else {
			//str = this.getNickname();
		}
		return str;
	}
}
