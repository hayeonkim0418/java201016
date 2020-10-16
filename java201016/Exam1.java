package java201016;
import java.util.Scanner;

public class Exam1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("명령어를 입력하세요");
		String a;
		
		do {			
			a = sc.next();
			System.out.println(a);
		} while (! a.equals("exit"));
		
		System.out.println();
		System.out.println("종료");
		
		
	}

}
