package java201110;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReplyDao {
	private ArrayList<Reply> replies;
	private int no = 1;

	public ReplyDao() {
		replies = new ArrayList<>();

//		Reply a1 = new Reply(1, "�ȳ�1", "����1", "�͸�", getCurrentDate());
//		Reply a2 = new Reply(2, "�ݰ���2", "����2", "�͸�", getCurrentDate());
//		Reply a3 = new Reply(3, "�ȳ��ϼ���3", "����3", "�͸�", getCurrentDate());
//
//		replies.add(a1);
//		replies.add(a2);
//		replies.add(a3);
	}

//	public static Reply getReplyById(int targetId) {
//		for (int i = 0; i < replies.size(); i++) {
//			int id = replies.get(i).getId();
//			if (id == targetId) {
//				return replies.get(i);
//			}
//		}
//
//		return null;
//	}

	public ArrayList<Reply> getSearchParentReply(int parentsId) {

		ArrayList<Reply> searchReply = new ArrayList<>();

		for (int i = 0; i < replies.size(); i++) {
			Reply reply = replies.get(i);

			if (reply.getParentsId() == parentsId) {
				searchReply.add(reply);
			}
		}
		return searchReply;
	}

//	public ArrayList<Reply> getSearchByFlag(int flag, String keyword) {
//
//		ArrayList<Reply> searchReply = new ArrayList<>();
//
//		for (int i = 0; i < replies.size(); i++) {
//			Reply reply = replies.get(i);
//			String str = Reply.getPropertiesByFlag(flag);
//
//			if (str.contains(keyword)) {
//				searchReply.add(reply);
//			}
//		}
//		return searchReply;
//	}

//    public ArrayList<Article> getReadByFlag(int flag){
//    	ArrayList<Article> ReadArticle = new ArrayList<>();
//
//		return ReadArticle;
//    }

	public void removeReply(Reply r) {
		replies.remove(r);
	}

	public void insertReply(Reply r) {
		r.setId(no);
		no++;
		r.setRegDate(Util.getCurrentDate());
		replies.add(r);
	}

	public ArrayList<Reply> getReplies() {
		return replies;
	}
}
