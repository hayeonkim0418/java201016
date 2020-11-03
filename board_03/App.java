package board_03;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	MemberDao memberDao = new MemberDao();
	ArticleDao articleDao = new ArticleDao();
	ReplyDao repliesDao = new ReplyDao();
	Member LoginedMember = null;

	public void start() {
		Scanner sc = new Scanner(System.in);

		int view = 1;

		while (true) {
			if (LoginedMember == null) {
				System.out.print("��ɾ� �Է�: ");
			} else {
				System.out.println(
						"��ɾ� �Է�: " + LoginedMember.getLoginId() + "[" + LoginedMember.getNickname() + "]" + ":");
			}
			String s = sc.nextLine();

			if (s.equals("exit")) {
				System.out.println("����");

				break;
			}
			if (s.equals("help")) {
				System.out.println("article [add: �Խù� �߰� / list : �Խù� ��� ��ȸ / read : �Խù� ��ȸ / search : �˻�]");
				System.out.println(
						"member [signup : ȸ������ / signin : �α��� / findpass : ��й�ȣ ã�� / findid : ���̵� ã�� / logout : �α׾ƿ� / myinfo : ���� ���� Ȯ�� �� ����]");
			}
			if (s.equals("article add")) {

				if (!isLogin()) {
					continue;
				}

				Article a = new Article();

				System.out.println("===�Խù� �߰�===");

				System.out.println("������ �Է����ּ���: ");
				String title = sc.nextLine();
				a.setTitle(title);

				System.out.println("������ �Է����ּ���: ");
				String body = sc.nextLine();
				a.setBody(body);

				a.setMid(LoginedMember.getId());
				articleDao.insertArticle(a);

				System.out.println("�Խù��� ��� �Ǿ����ϴ�");

//                size++;

			}
			if (s.equals("article list")) {

				System.out.println("===�Խù� ��ȸ===");
				ArrayList<Article> listArticle = articleDao.getArticles();

				printArticle(listArticle);

				System.out.println("---------------------");
			}
			if (s.equals("article update")) {
				System.out.println("���° �Խù��� ���� �Ͻðڽ��ϱ� ? ");
				int targetId = Integer.parseInt(sc.nextLine());
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

				}
			}
			if (s.equals("article delete")) {
				ArrayList<Article> articles = articleDao.getArticles();

				System.out.println("���° �Խù��� ���� �Ͻðڽ��ϱ� ? ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					articleDao.removeArticle(target);
					System.out.println("�Խù��� ���� �Ǿ����ϴ�.");
				}
			}

			if (s.equals("article read")) {
				System.out.println("=====�Խù� �󼼺���=====");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					printArticles(target);
				}
				view++;

				while (true) {

					System.out.println("�󼼺��� ����� �����غ�����." + " 1. ��� ���, 2. ���ƿ�, 3. ����, 4. ����, 5. �������");
					int cmd = Integer.parseInt(sc.nextLine());
					if (cmd == 1) {
						Reply r = new Reply();
						
						System.out.println("��� ������ �Է����ּ���.");
						String body = sc.nextLine();
						r.setParentsId(target.getId());
						r.setBody(body);
						r.setNickname("�͸�");
						repliesDao.insertReply(r);
						
						printArticles(target);

						System.out.println("�Խù��� ��� �Ǿ����ϴ�");
					} else if (cmd == 2) {
						System.out.println("���ƿ� ���");
					} else if (cmd == 3) {
						
						if(!isLogin() || !isMyArticle(target)) {
							continue;
						}
						System.out.println("���� �� ������ �Է����ּ���: ");
						String newTitle = sc.next();

						System.out.println("���� �� ������ �Է����ּ���: ");
						String newBody = sc.next();

						target.setTitle(newTitle);
						target.setBody(newBody);
						
						printArticles(target);

						System.out.println("�Խù��� ���� �Ǿ����ϴ�.");

					} else if (cmd == 4) {
						
						if(!isLogin() || !isMyArticle(target)) {
							continue;
						}
						articleDao.removeArticle(target);
						break;
						
					} else if (cmd == 5) {
						System.out.println("�������");
						break;
					}
				}
			}
			if (s.equals("article search")) {

				System.out.println("�˻��׸�: 1. ����, 2. ����, 3. ���� + ����, 4. �ۼ���");
				int flag = Integer.parseInt(sc.nextLine());
				System.out.println("�˻� Ű���带 �Է����ּ���: ");
				String keyword = sc.nextLine();

				ArrayList<Article> searchArticle;
				searchArticle = articleDao.getSearchByFlag(flag, keyword);

				printArticle(searchArticle);
			}
			if (s.equals("member signup")) {
				Member m = new Member();
				System.out.println("=====ȸ�������� �����մϴ�=====");
				System.out.println("���̵� �Է����ּ���: ");
				String id = sc.nextLine();
				m.setLoginId(id);
				System.out.println("��й�ȣ�� �Է����ּ���: ");
				String pw = sc.nextLine();
				m.setLoginPw(pw);
				System.out.println("�г����� �Է����ּ���: ");
				String nick = sc.nextLine();
				m.setNickname(nick);
				System.out.println("=====ȸ�������� �Ϸ�Ǿ����ϴ�=====");
				memberDao.insertMember(m);
			}
			if (s.equals("member signin")) {
				System.out.println("========== �α��� ==========");
				System.out.println("ID: ");
				String id = sc.nextLine();
				System.out.println("PW: ");
				String pw = sc.nextLine();

				Member searchMember = memberDao.getSearchByIdAndPw(id, pw);

				if (searchMember == null) {
					System.out.println("��й�ȣ�� Ʋ�Ȱų� �߸��� ȸ������ �Դϴ�.");
				} else {
					LoginedMember = searchMember;
					System.out.println(searchMember.getNickname() + "�� ȯ���մϴ� !");
				}
			}
			if (s.equals("member logout")) {
				if (!isLogin()) {
					continue;
				}
					LoginedMember = null;
					System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
				
			}
		}

	}

	private void printArticle(ArrayList<Article> ArticleList) {

		for (int i = 0; i < ArticleList.size(); i++) {
			Article article = ArticleList.get(i);

			System.out.println("��ȣ: " + article.getId());
			System.out.println("����: " + article.getTitle());
			System.out.println("����: " + article.getBody());
			Member regMember = memberDao.getMemberById(article.getMid());
			System.out.println("�ۼ���: " + regMember.getNickname());

			System.out.println("---------------------");
		}

	}
	
	private void printArticles(Article target) {
		System.out.println("==== " + target.getId() + " ====");
		System.out.println("��ȣ: " + target.getId());
		System.out.println("����: " + target.getTitle());
		System.out.println("����: " + target.getBody());
		Member regMember = memberDao.getMemberById(target.getMid());
		System.out.println("�ۼ���: " + regMember.getNickname());
		System.out.println("��ϳ�¥: " + target.getRegDate());
		System.out.println("==================");
		
		ArrayList<Reply> listReplies = repliesDao.getSearchParentReply(target.getId());

		printReply(listReplies);
	}

	private void printReply(ArrayList<Reply> ReplyList) {
		for (int i = 0; i < ReplyList.size(); i++) {
			Reply replies = ReplyList.get(i);

			System.out.println("========���========");
			System.out.println("����: " + replies.getBody());
			System.out.println("�ۼ���: " + replies.getNickname());
			System.out.println("��ϳ�¥: " + replies.getRegDate());

			System.out.println("---------------------");
		}

	}

	private boolean isLogin() {
		if (LoginedMember == null) {
			System.out.println("�α����� �ʿ��մϴ�.");
			return false;
		} else {
			return true;
		}
	}
	
	private boolean isMyArticle(Article article) {
		if (LoginedMember.getId() != article.getId()) {
			System.out.println("���� �Խù��� ������ �����մϴ�.");
			return false;
		} else {
			return true;
		}
	}

}
