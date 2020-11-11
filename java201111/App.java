package java201111;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
				System.out.print("명령어 입력: ");
			} else {
				System.out.println(
						"명령어 입력: " + LoginedMember.getLoginId() + "[" + LoginedMember.getNickname() + "]" + ":");
			}
			String s = sc.nextLine();

			if (s.equals("exit")) {
				System.out.println("종료");

				break;
			}
			if (s.equals("help")) {
				System.out.println("article [add: 게시물 추가 / list : 게시물 목록 조회 / read : 게시물 조회 / search : 검색]");
				System.out.println(
						"member [signup : 회원가입 / signin : 로그인 / findpass : 비밀번호 찾기 / findid : 아이디 찾기 / logout : 로그아웃 / myinfo : 나의 정보 확인 및 수정]");
			}
			if (s.equals("article add")) {

				if (!isLogin()) {
					continue;
				}

				Article a = new Article();

				System.out.println("===게시물 추가===");

				System.out.println("제목을 입력해주세요: ");
				String title = sc.nextLine();
				a.setTitle(title);

				System.out.println("내용을 입력해주세요: ");
				String body = sc.nextLine();
				a.setBody(body);

				a.setMid(LoginedMember.getId());
				articleDao.insertArticle(a);

				System.out.println("게시물이 등록 되었습니다");

//                size++;

			}
			if (s.equals("article list")) {

				System.out.println("===게시물 조회===");
				ArrayList<Article> listArticle = articleDao.getArticles();

				printArticle(listArticle);

				System.out.println("---------------------");
			}
			if (s.equals("article update")) {
				System.out.println("몇번째 게시물을 수정 하시겠습니까 ? ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					System.out.println("게시물 제목을 입력해주세요 :");
					String newTitle = sc.next();

					System.out.println("게시물 내용을 입력해주세요 :");
					String newBody = sc.next();

					target.setTitle(newTitle);
					target.setBody(newBody);

					System.out.println("게시물이 수정 되었습니다.");

				}
			}
			if (s.equals("article delete")) {
				ArrayList<Article> articles = articleDao.getArticles();

				System.out.println("몇번째 게시물을 삭제 하시겠습니까 ? ");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					articleDao.removeArticle(target);
					System.out.println("게시물이 삭제 되었습니다.");
				}
			}

			if (s.equals("article read")) {
				System.out.println("=====게시물 상세보기=====");
				int targetId = Integer.parseInt(sc.nextLine());
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					printArticles(target);
					target.setHit(target.getHit() + 1);
				}

				while (true) {

					System.out.println("상세보기 기능을 선택해보세요." + " 1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로");
					int cmd = Integer.parseInt(sc.nextLine());
					if (cmd == 1) {
						Reply r = new Reply();

						System.out.println("댓글 내용을 입력해주세요.");
						String body = sc.nextLine();
						r.setParentsId(target.getId());
						r.setBody(body);
						r.setNickname("익명");
						repliesDao.insertReply(r);

						printArticles(target);

						System.out.println("게시물이 등록 되었습니다");
					} else if (cmd == 2) {
						System.out.println("좋아요 기능");
					} else if (cmd == 3) {

						if (!isLogin() || !isMyArticle(target)) {
							continue;
						}
						System.out.println("수정 할 제목을 입력해주세요: ");
						String newTitle = sc.next();

						System.out.println("수정 할 내용을 입력해주세요: ");
						String newBody = sc.next();

						target.setTitle(newTitle);
						target.setBody(newBody);

						printArticles(target);

						System.out.println("게시물이 수정 되었습니다.");

					} else if (cmd == 4) {

						if (!isLogin() || !isMyArticle(target)) {
							continue;
						}
						articleDao.removeArticle(target);
						break;

					} else if (cmd == 5) {
						System.out.println("목록으로");
						break;
					}
				}
			}
			if (s.equals("article search")) {

				System.out.println("검색항목: 1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자");
				int flag = Integer.parseInt(sc.nextLine());
				System.out.println("검색 키워드를 입력해주세요: ");
				String keyword = sc.nextLine();

				ArrayList<Article> searchArticle;
				searchArticle = articleDao.getSearchByFlag(flag, keyword);

				printArticle(searchArticle);
			}
			if (s.equals("article sort")) {
				System.out.println("정렬 대상을 선택해주세요. (like : 좋아요, hit : 조회수) :");
				String sortType = sc.nextLine();
				
				System.out.println("정렬 방법을 선택해주세요. (asc : 오름차순, desc : 내림차순) : ");
				String sortOrder = sc.nextLine();
				
				MyComparator comp = new MyComparator();
				comp.sortOrder = sortOrder;
				
				ArrayList<Article> articles = articleDao.getArticles();
				Collections.sort(articles, comp);
				
				printArticle(articles);

			}
			if(s.equals("article page")) {
				System.out.println("페이징 이동 기능 선택: (prev : 이전, next : 다음, go : 선택, back : 뒤로가기)");
			}
			if (s.equals("member signup")) {
				Member m = new Member();
				System.out.println("=====회원가입을 진행합니다=====");
				System.out.println("아이디를 입력해주세요: ");
				String id = sc.nextLine();
				m.setLoginId(id);
				System.out.println("비밀번호를 입력해주세요: ");
				String pw = sc.nextLine();
				m.setLoginPw(pw);
				System.out.println("닉네임을 입력해주세요: ");
				String nick = sc.nextLine();
				m.setNickname(nick);
				System.out.println("=====회원가입이 완료되었습니다=====");
				memberDao.insertMember(m);
			}
			if (s.equals("member signin")) {
				System.out.println("========== 로그인 ==========");
				System.out.println("ID: ");
				String id = sc.nextLine();
				System.out.println("PW: ");
				String pw = sc.nextLine();

				Member searchMember = memberDao.getSearchByIdAndPw(id, pw);

				if (searchMember == null) {
					System.out.println("비밀번호가 틀렸거나 잘못된 회원정보 입니다.");
				} else {
					LoginedMember = searchMember;
					System.out.println(searchMember.getNickname() + "님 환영합니다 !");
				}
			}
			if (s.equals("member logout")) {
				if (!isLogin()) {
					continue;
				}
				LoginedMember = null;
				System.out.println("로그아웃 되었습니다.");

			}
		}

	}

	private void printArticle(ArrayList<Article> ArticleList) {

		for (int i = 0; i < ArticleList.size(); i++) {
			Article article = ArticleList.get(i);

			System.out.println("번호: " + article.getId());
			System.out.println("제목: " + article.getTitle());
			System.out.println("내용: " + article.getBody());
			Member regMember = memberDao.getMemberById(article.getMid());
			System.out.println("작성자: " + regMember.getNickname());
			System.out.println("조회수 : " + article.getHit());

			System.out.println("---------------------");
		}

	}

	private void printArticles(Article target) {
		System.out.println("==== " + target.getId() + " ====");
		System.out.println("번호: " + target.getId());
		System.out.println("제목: " + target.getTitle());
		System.out.println("내용: " + target.getBody());
		Member regMember = memberDao.getMemberById(target.getMid());
		System.out.println("작성자: " + regMember.getNickname());
		System.out.println("등록날짜: " + target.getRegDate());
		System.out.println("조회수 : " + target.getHit());
		System.out.println("==================");

		ArrayList<Reply> listReplies = repliesDao.getSearchParentReply(target.getId());

		printReply(listReplies);
	}

	private void printReply(ArrayList<Reply> ReplyList) {
		for (int i = 0; i < ReplyList.size(); i++) {
			Reply replies = ReplyList.get(i);

			System.out.println("========댓글========");
			System.out.println("내용: " + replies.getBody());
			System.out.println("작성자: " + replies.getNickname());
			System.out.println("등록날짜: " + replies.getRegDate());

			System.out.println("---------------------");
		}

	}

	private boolean isLogin() {
		if (LoginedMember == null) {
			System.out.println("로그인이 필요합니다.");
			return false;
		} else {
			return true;
		}
	}

	private boolean isMyArticle(Article article) {
		if (LoginedMember.getId() != article.getId()) {
			System.out.println("본인 게시물만 수정이 가능합니다.");
			return false;
		} else {
			return true;
		}
	}

}

class MyComparator implements Comparator<Article> {

	String sortOrder = "asc";
	String sortType = "hit";

	@Override
	public int compare(Article o1, Article o2) {
		if (sortOrder.equals("asc")) {
			if (o1.getHit() > o2.getHit()) {
				return 1;
			}
			return -1;
		} else {
			if (o1.getHit() < o2.getHit()) {
				return 1;
			}
			return -1;
		}
	}
}