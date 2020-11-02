package java201102;

public class Reply {
	private int id;
	private int parentsId;
    private String body;
    private String regDate;
    private String nickname;

    public int getParentsId() {
		return parentsId;
	}
	public void setParentsId(int parentsId) {
		this.parentsId = parentsId;
	}
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
