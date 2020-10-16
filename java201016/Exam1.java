package java201016;

import java.util.Scanner;

public class Exam1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			    Scanner scan = new Scanner(System.in);

			    int a = scan.nextInt();

			    for(int i = 1; i <= 9; i ++) {
			    	System.out.println(a * i);
			    }
			    
			    Scanner scan2 = new Scanner(System.in);

			    System.out.println("이름을 입력해주세요:");
			    
			    String name = scan2.next();
			    
			    System.out.println("나이를 입력해주세요:");
			    
			    int age = scan2.nextInt();


			    // 출력 : 안녕하세요 29세 홍길동입니다.
			    System.out.println("안녕하세요" + age + "세 " + name + "입니다.");
			    
			    Scanner scan3 = new Scanner(System.in);
			    
			    int c = scan3.nextInt();
			    
			    if(c % 2 == 0) {
			    	System.out.println("even");
			    }else {
			    	System.out.println("odd");
			    }
	}

}
