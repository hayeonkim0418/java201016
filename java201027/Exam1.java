import java.util.ArrayList;
import java.util.Scanner;

public class Exam1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArticleDao dao = new ArticleDao();

		int view = 1;
		// int size = 0;

		while (true) {

			System.out.print("명령어 입력: ");
			String s = sc.next();

			if (s.equals("exit")) {
				System.out.println("종료");

				break;
			}
			if (s.equals("add")) {
				Article a = new Article();

				System.out.println("===게시물 추가===");

				System.out.println("제목을 입력해주세요: ");
				String title = sc.next();
				a.setTitle(title);

				System.out.println("내용을 입력해주세요: ");
				String body = sc.next();
				a.setBody(body);

				a.setNickname("익명");
				dao.insertArticle(a);

				System.out.println("게시물이 등록 되었습니다");

//                size++;

			}
			if (s.equals("list")) {

				System.out.println("===게시물 조회===");
				ArrayList<Article> listArticle = dao.getArticles();

				printArticle(listArticle);

				System.out.println("---------------------");
			}
			if (s.equals("update")) {
				System.out.println("몇번째 게시물을 수정 하시겠습니까 ? ");
				int targetId = sc.nextInt();
				Article target = dao.getArticleById(targetId);
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
//					break;

				}
			}
			if (s.equals("delete")) {
				ArrayList<Article> articles = dao.getArticles();

				System.out.println("몇번째 게시물을 삭제 하시겠습니까 ? ");
				int targetId = sc.nextInt();
				Article target = dao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					dao.removeArticle(target);
					System.out.println("게시물이 삭제 되었습니다.");
				}
			}

			if (s.equals("read")) {
				System.out.println("=====게시물 상세보기=====");
				int targetId = sc.nextInt();
				Article target = dao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					System.out.println("==== " + target.getId() + " ====");
					System.out.println("번호: " + target.getId());
					System.out.println("제목: " + target.getTitle());
					System.out.println("작성자: " + target.getNickname());
					System.out.println("등록날짜: " + target.getRegDate());
					System.out.println("내용: " + target.getBody());
					System.out.println("===============");
				}
				view++;

				while (true) {
					Article a = new Article();  
					
					System.out.println("상세보기 기능을 선택해보세요." + " 1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로");
					int cmd = sc.nextInt();
					if (cmd == 1) {
						System.out.println("댓글 기능");
						System.out.println("댓글 내용을 입력해주세요.");
						String printBody = sc.next();
						a.setPrintBody(printBody);
						
						System.out.println("댓글이 등록되었습니다.");
						System.out.println("==== " + target.getId() + " ====");
						System.out.println("번호 : " + target.getId());
						System.out.println("제목 : " + target.getTitle());
						System.out.println("작성자: " + target.getNickname());
						System.out.println("등록날짜: " + target.getRegDate());
						System.out.println("내용 : " + target.getBody());
						System.out.println("===============");
						System.out.println("------댓글------");
						System.out.println("내용: " + printBody);
						System.out.println("작성자: " + target.getNickname());
						System.out.println("등록날짜: " + target.getRegDate());
					} else if (cmd == 2) {
						System.out.println("좋아요 기능");
					} else if (cmd == 3) {
						System.out.println("수정 기능");
					} else if (cmd == 4) {
						System.out.println("삭제 기능");
					} else if (cmd == 5) {
						System.out.println("목록으로");
						break;
					}
				}
			}
			if (s.equals("search")) {

				System.out.println("검색항목: 1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자");
				int flag = sc.nextInt();
				System.out.println("검색 키워드를 입력해주세요: ");
				String keyword = sc.next();
				ArrayList<Article> searchArticle;
				searchArticle = dao.getSearchByFlag(flag, keyword);

				printArticle(searchArticle);
			}
		}

	}

	private static void printArticle(ArrayList<Article> ArticleList) {

		for (int i = 0; i < ArticleList.size(); i++) {
			Article article = ArticleList.get(i);

			System.out.println("번호: " + article.getId());
			System.out.println("제목: " + article.getTitle());
			System.out.println("내용: " + article.getBody());

			System.out.println("---------------------");
		}

	}
	private static void printReadArticle(ArrayList<Article> ArticleRead) {
		
	}
}
