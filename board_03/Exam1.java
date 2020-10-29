package board_03;

import java.util.ArrayList;
import java.util.Scanner;

public class Exam1 {

	static MemberDao memberDao = new MemberDao();
	static ArticleDao articleDao = new ArticleDao();
	static ReplyDao repliesDao = new ReplyDao();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int view = 1;
		// int size = 0;

		while (true) {

			System.out.print("��ɾ� �Է�: ");
			String s = sc.next();

			if (s.equals("exit")) {
				System.out.println("����");

				break;
			}
			if (s.equals("add")) {
				Article a = new Article();

				System.out.println("===�Խù� �߰�===");

				System.out.println("������ �Է����ּ���: ");
				String title = sc.next();
				a.setTitle(title);

				System.out.println("������ �Է����ּ���: ");
				String body = sc.next();
				a.setBody(body);

				a.setNickname("�͸�");
				articleDao.insertArticle(a);

				System.out.println("�Խù��� ��� �Ǿ����ϴ�");

//                size++;

			}
			if (s.equals("list")) {

				System.out.println("===�Խù� ��ȸ===");
				ArrayList<Article> listArticle = articleDao.getArticles();

				printArticle(listArticle);

				System.out.println("---------------------");
			}
			if (s.equals("update")) {
				System.out.println("���° �Խù��� ���� �Ͻðڽ��ϱ� ? ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("���� �Խù��Դϴ�.");
				} else {
					System.out.println("�Խù� ������ �Է����ּ��� :");
					String newTitle = sc.next();

					System.out.println("�Խù� ������ �Է����ּ��� :");
					String newBody = sc.next();

					target.setTitle(newTitle);
					target.setBody(newBody);

					System.out.println("�Խù��� ���� �Ǿ����ϴ�.");
//					break;

				}
			}
			if (s.equals("delete")) {
				ArrayList<Article> articles = articleDao.getArticles();

				System.out.println("���° �Խù��� ���� �Ͻðڽ��ϱ� ? ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					articleDao.removeArticle(target);
					System.out.println("�Խù��� ���� �Ǿ����ϴ�.");
				}
			}

			if (s.equals("read")) {
				System.out.println("=====�Խù� �󼼺���=====");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					System.out.println("==== " + target.getId() + " ====");
					System.out.println("��ȣ: " + target.getId());
					System.out.println("����: " + target.getTitle());
					System.out.println("�ۼ���: " + target.getNickname());
					System.out.println("��ϳ�¥: " + target.getRegDate());
					System.out.println("����: " + target.getBody());
					System.out.println("===============");
					System.out.println("=====���=====");
					ArrayList<Reply> listReplies = repliesDao.getSearchParentReply(target.getId());

					printReply(listReplies);
				}
				view++;

				while (true) {

					System.out.println("�󼼺��� ����� �����غ�����." + " 1. ��� ���, 2. ���ƿ�, 3. ����, 4. ����, 5. �������");
					int cmd = sc.nextInt();
					if (cmd == 1) {
						Reply r = new Reply();
						System.out.println("��� ���");
						System.out.println("��� ������ �Է����ּ���.");
						String body = sc.next();
						r.setParentsId(target.getId());
						r.setBody(body);
						r.setNickname("�͸�");
						repliesDao.insertReply(r);
						System.out.println("==== " + target.getId() + " ====");
						System.out.println("��ȣ: " + target.getId());
						System.out.println("����: " + target.getTitle());
						System.out.println("�ۼ���: " + target.getNickname());
						System.out.println("��ϳ�¥: " + target.getRegDate());
						System.out.println("����: " + target.getBody());
						System.out.println("=====���=====");
						ArrayList<Reply> listReplies = repliesDao.getSearchParentReply(target.getId());

						printReply(listReplies);

						System.out.println("�Խù��� ��� �Ǿ����ϴ�");
					} else if (cmd == 2) {
						System.out.println("���ƿ� ���");
					} else if (cmd == 3) {
						System.out.println("���� ���");
					} else if (cmd == 4) {
						System.out.println("���� ���");
					} else if (cmd == 5) {
						System.out.println("�������");
						break;
					}
				}
			}
			if (s.equals("search")) {

				System.out.println("�˻��׸�: 1. ����, 2. ����, 3. ���� + ����, 4. �ۼ���");
				int flag = sc.nextInt();
				System.out.println("�˻� Ű���带 �Է����ּ���: ");
				String keyword = sc.next();

				ArrayList<Article> searchArticle;
				searchArticle = articleDao.getSearchByFlag(flag, keyword);

				printArticle(searchArticle);
			}
			if (s.equals("signup")) {
				Member m = new Member();
				System.out.println("=====ȸ�������� �����մϴ�=====");
				System.out.println("ID�� �Է����ּ���: ");
				String id = sc.next();
				m.setLoginId(id);
				System.out.println("PW�� �Է����ּ���: ");
				String pw = sc.next();
				m.setLoginPw(pw);
				System.out.println("�г����� �Է����ּ���: ");
				String nick = sc.next();
				m.setNickname(nick);
				System.out.println("=====ȸ�������� �Ϸ�Ǿ����ϴ�=====");
			}
			if(s.equals("signin")) {
				System.out.println("========== �α��� ==========");
				for (int i = 0; i < members.size(); i++) {
					Member members = members.get(i);
					
					System.out.println("===== ID�� �Է����ּ��� =====");
					String id = sc.next();
					System.out.println("ID: " + id);
					System.out.println("===== PW�� �Է����ּ��� =====");
					String pw = sc.next();
					System.out.println("PW: " + pw);
					System.out.println("��й�ȣ�� Ʋ�Ȱų� �߸��� ȸ������ �Դϴ�.");
					System.out.println("�� ȯ���մϴ� !");
					
					System.out.println("����: " + members.getLoginId());
					System.out.println("��ϳ�¥: " + members.getLoginPw());
					System.out.println("��ȣ: " + members.getNickname());
				}

			}
		}

	}

	private static void printArticle(ArrayList<Article> ArticleList) {

		for (int i = 0; i < ArticleList.size(); i++) {
			Article article = ArticleList.get(i);

			System.out.println("��ȣ: " + article.getId());
			System.out.println("����: " + article.getTitle());
			System.out.println("����: " + article.getBody());

			System.out.println("---------------------");
		}

	}

	private static void printReply(ArrayList<Reply> ReplyList) {
		for (int i = 0; i < ReplyList.size(); i++) {
			Reply replies = ReplyList.get(i);

			System.out.println("����: " + replies.getBody());
			System.out.println("��ȣ: " + replies.getNickname());
			System.out.println("��ϳ�¥: " + replies.getRegDate());

			System.out.println("---------------------");
		}

	}

}