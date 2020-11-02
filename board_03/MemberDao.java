package board_03;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {
	private ArrayList<Member> members;
	private int no = 1;

	public MemberDao() {
		members = new ArrayList<>();

		Member m1 = new Member(1, "ID_1", "익명", getCurrentDate());
		Member m2 = new Member(2, "ID_2", "익명", getCurrentDate());
		Member m3 = new Member(3, "ID_3", "익명", getCurrentDate());

		members.add(m1);
		members.add(m2);
		members.add(m3);
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

	public Member getSearchByIdAndPw(String id, String pw) {

		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if(m.getLoginId().equals(id) && m.getLoginPw().equals(pw)) {
				return m;
			}
		}
		return null;
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

}
