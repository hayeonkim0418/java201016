package java201021;

import java.util.ArrayList;
import java.util.Scanner;

public class Exam2 {

	public static void main(String[] args) {

		ArrayList<Article> articles = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		int size = 0;
		int no = 1;

		while (true) {

			System.out.print("명령어 입력: ");
			String s = sc.next();

			if (s.equals("exit")) {
				System.out.println("종료");

				break;
			}
			if (s.equals("add")) {
				Article a = new Article();

				a.setId(no);
				no++;

				System.out.println("===게시물 추가===");

				System.out.println("제목을 입력해주세요: ");
				String title = sc.next();
				a.setTitle(title);

				System.out.println("내용을 입력해주세요: ");
				String body = sc.next();
				a.setBody(body);

				articles.add(a);
				System.out.println("게시물이 등록 되었습니다");

//                size++;

			}
			if (s.equals("list")) {
				System.out.println("===게시물 조회===");

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

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					int id = article.getId();

					if (id == targetId) {

						Article newArticle = new Article();

						System.out.println("제목을 입력해주세요");
						String newTitle = sc.next();

						System.out.println("내용을 입력해주세요");
						String newBody = sc.next();

						newArticle.setId(id);
						newArticle.setBody(newTitle);
						newArticle.setTitle(newBody);

						articles.set(i, newArticle);

						System.out.println("게시물이 수정 되었습니다.");
					}

				}
//                if (id > size){
//                    System.out.println("존재하지 않는 게시물 입니다.");
//                }

			}
			if (s.equals("delete")) {
				System.out.println("몇번째 게시물을 삭제 하시겠습니까 ? ");
				int targetId = sc.nextInt();
				int ex = 2;

				for (int i = 0; i < articles.size(); i++) {
					int id = articles.get(i).getId();
					if (id == targetId) {
						ex = 1;

						articles.remove(i);

						System.out.println("게시물이 삭제 되었습니다.");
					}
				}
				if (ex == 2) {
					System.out.println("존재하지 않는 게시물 입니다.");
				} else {
					System.out.println(targetId + " 번 게시물이 삭제 되었습니다.");
				}

			}
			if (s.equals("read")) {
				System.out.println("===상세보기===");
				int targetId = sc.nextInt();

				for (int i = 0; i < articles.size(); i++) {

					int id = articles.get(i).getId();

					if (id == targetId) {

						System.out.println("번호: " + articles.get(i).getId());
						System.out.println("제목: " + articles.get(i).getTitle());
						System.out.println("내용: " + articles.get(i).getBody());

						System.out.println("게시물이 삭제 되었습니다.");
					}

				}
			}

		}

	}
}
