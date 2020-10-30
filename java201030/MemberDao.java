package java201030;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {
	private ArrayList<Member> members;
	private int no = 1;
	
	public MemberDao() {
		members = new ArrayList<>();

//		Article a1 = new Article(1, "안녕1", "내용1", "익명", getCurrentDate());
//		Article a2 = new Article(2, "반가워2", "내용2", "익명", getCurrentDate());
//		Article a3 = new Article(3, "안녕하세요3", "내용3", "익명", getCurrentDate());
//
//		articles.add(a1);
//		articles.add(a2);
//		articles.add(a3);
	}

	public Member getArticleById(int targetId) {
		for (int i = 0; i < members.size(); i++) {
			int id = members.get(i).getId();
			if (id == targetId) {
				return members.get(i);
			}
		}

		return null;
	}

	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}
	

	public void insertMember(Member m) {
		m.setId(no);
		no++;
		m.setRegDate(getCurrentDate());
		members.add(m);
	}
	
	public ArrayList<Member> getMembers() {
		return members;
	}
	
	public ArrayList<Member> getSignInById(String keyword) {

		ArrayList<Member> searchMember = new ArrayList<>();

		for (int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
			String str = "";

			if (str.contains(keyword)) {
				searchMember.add(member);
			}
		}
		return searchMember;
	}

}
