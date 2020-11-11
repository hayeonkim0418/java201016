package java201111;

import java.util.ArrayList;
import java.util.Scanner;

public class Page {
	public static void main(String[] args) {

		ArrayList<Article> articles = new ArrayList<>();

		for (int i = 0; i <= 50; i++) {
			Article a1 = new Article();
			a1.setId(i);
			a1.setTitle("제목: " + i);
			a1.setBody("내용: " + i);

			articles.add(a1);
		}

		Scanner sc = new Scanner(System.in);

		int currentPageNo = Integer.parseInt(sc.nextLine()); // 현재 페이지 기준으로 페이지를 만들어야 함.

		int total = 20;// 전체 게시물 갯수
		int startPageNo = 1;// 시작 페이지 번호
		int itemCntPerPage = 3;// 페이지당 출력 게시물 갯수
		int PageCntPerBlock = 5;// 한 페이지 블럭당 페이지 개수
		int endPageNo = (int) Math.ceil((double) total / itemCntPerPage); // 게시물의 갯수로 정해짐.마지막 페이지 번호

		if (currentPageNo < startPageNo) {
			currentPageNo = startPageNo; // 시작 페이지가 현재 페이지보다 커야함.
		}
		if (currentPageNo < endPageNo) {
			currentPageNo = endPageNo; // 마지막 페이지가 현재 페이지보다 커야함.
		}
		int currentPageBlock = (int) Math.ceil((double) currentPageNo / PageCntPerBlock); // 현재 페이지 블록
		// ㄴ Math.ceil = 값을 올림 ㄴ형변환 시켜줌./1블럭 넘어가면 바로 2블럭 갈 수 있도록 올림해줌.
		int startPageNoInBlock = (currentPageBlock - 1) * PageCntPerBlock + 1;// 현재 페이지 블록의 시작페이지 번호
		//ㄴ 1부터 시작해야 되기에 -1 해주고 (그럼 0 나옴)-> +1해주면 (1 나옴)
		int endPageNoBlock = startPageNoInBlock + PageCntPerBlock - 1;// 현재 페이지 블록의 마지막 페이지 번호
		//ㄴ6부터 시작이여서 - 1해줌
		if (endPageNoBlock > endPageNo) {
			endPageNoBlock = endPageNo; // 마지막 페이지 번호가 블록의 마지막 페이지를 넘으면 안됨.
		}
		int startIndex = (currentPageNo - 1) * itemCntPerPage;
		int endIndex = startIndex + itemCntPerPage;

		if (endIndex > total) {
			endIndex = total; // 페이지 마지막 인덱스가 전체 게시물 갯수보다 크면 안됨
		}

		// 페이지별 게시물 출력
		for (int i = startIndex; i <= endIndex; i++) {
			System.out.println("번호: " + articles.get(i).getId());
			System.out.println("제목: " + articles.get(i).getTitle());
			System.out.println("내용: " + articles.get(i).getBody());
			System.out.println("=====================================");
		}
		
		for(int i = startPageNoInBlock; i <= endPageNoBlock; i++) {
			if(i == currentPageNo) {
				System.out.print("[" + i + "] ");
			}else {
				System.out.print(i + " ");
			}
		}
	}
}
