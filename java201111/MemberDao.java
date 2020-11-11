package java201111;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {
	private ArrayList<Member> members;
	private int no = 1;

	public MemberDao() {
		members = new ArrayList<>();

		Member m1 = new Member(1, "a1","aa", "aaa", Util.getCurrentDate());
		Member m2 = new Member(2, "b2","bb", "bbb", Util.getCurrentDate());
		Member m3 = new Member(3, "c3","cc", "ccc", Util.getCurrentDate());

		members.add(m1);
		members.add(m2);
		members.add(m3);
	}

//	public Member getMemberById(int targetId) {
//		for (int i = 0; i < members.size(); i++) {
//			int id = members.get(i).getId();
//			if (id == targetId) {
//				return members.get(i);
//			}
//		}
//
//		return null;
//	}

	public Member getSearchByIdAndPw(String id, String pw) {

		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if(m.getLoginId().equals(id) && m.getLoginPw().equals(pw)) {
				return m;
			}
		}
		return null;
	}
	
	public Member getMemberById(int id) {
		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if(m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	public void insertMember(Member m) {
		m.setId(no);
		no++;
		m.setRegDate(Util.getCurrentDate());
		members.add(m);
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

}
