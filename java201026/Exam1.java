package java201026;

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
				ArrayList<Article> articles = dao.getArticles();

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					System.out.println("번호: " + article.getId());
					System.out.println("제목: " + article.getTitle());
					// System.out.println("내용: " + article.getBody());

					System.out.println("---------------------");
				}
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
					System.out.println("번호 : " + target.getId());
					System.out.println("제목 : " + target.getTitle());
					System.out.println("등록날짜: " + target.getRegDate());
					System.out.println("내용 : " + target.getBody());
					System.out.println("===============");
				}
				view++;
			}
			if(s.equals("search")) {
				System.out.println("검색 할 제목을 입력해주세요: ");
				String keyword = sc.next();
				ArrayList<Article> searchArticle = dao.getSearchByTitle(keyword);
				
				for (int i = 0; i < searchArticle.size(); i++) {
					Article article = searchArticle.get(i);

					System.out.println("번호: " + article.getId());
					System.out.println("제목: " + article.getTitle());
					System.out.println("내용: " + article.getBody());

					System.out.println("---------------------");
				}
				
				System.out.println("검색 할 내용을 입력해주세요: ");
				String keywordBody = sc.next();
				ArrayList<Article> searchArticleBody = dao.getSearchBody(keywordBody);
				
				for (int i = 0; i < searchArticleBody.size(); i++) {
					Article article = searchArticleBody.get(i);
					
					System.out.println("내용: " + article.getBody());
					System.out.println("---------------------");
				}
			}

		}


	}

}
