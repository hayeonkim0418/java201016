package java201028;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReplyDao {
	private ArrayList<Reply> replies;
	private int no = 1;

	public ReplyDao() {
		replies = new ArrayList<>();

//		Reply a1 = new Reply(1, "안녕1", "내용1", "익명", getCurrentDate());
//		Reply a2 = new Reply(2, "반가워2", "내용2", "익명", getCurrentDate());
//		Reply a3 = new Reply(3, "안녕하세요3", "내용3", "익명", getCurrentDate());
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

	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}

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
		r.setRegDate(getCurrentDate());
		replies.add(r);
	}

	public ArrayList<Reply> getReplies() {
		return replies;
	}
}
