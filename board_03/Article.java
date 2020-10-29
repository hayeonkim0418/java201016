package board_03;

public class Article {
	private int id;
	private String title;
	private String body;
	private String nickname;
	private String regDate;
	private String printBody;

	public Article() {

	}

	public Article(int id, String title, String body, String nickname, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.nickname = nickname;
		this.regDate = regDate;
		this.printBody = printBody;
	}

	public String getPrintBody() {
		return printBody;
	}

	public void setPrintBody(String printBody) {
		this.printBody = printBody;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
			str = this.getNickname();
		}
		return str;
	}
}
