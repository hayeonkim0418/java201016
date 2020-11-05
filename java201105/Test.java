package java201105;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Num> nlist = new ArrayList<Num>();

		list.add(10);
		list.add(15);
		list.add(1);
		list.add(7);
		list.add(9);

		printList(list);
		Collections.sort(list);// 오름차순
		printList(list);
		Collections.sort(list, Collections.reverseOrder());// 내림차순
		printList(list);

		nlist.add(new Num(4, 5));
		nlist.add(new Num(1, 1));
		nlist.add(new Num(2, 4));
		nlist.add(new Num(5, 3));
		nlist.add(new Num(3, 2));

		Collections.sort(nlist);
		Collections.sort(nlist, Num);

		MyComparator
		
		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n2);
		}

		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n2);
		}
	}

	public static void printList(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}

class MyComparator implements Comparator<Num> {
	// 앞에 것을 뒤로 보내고 싶을땐 양수로 리턴
	@Override
	public int compare(Num o1, Num o2) {
		if (o1.n2 > o2.n2) {  //왼쪽이 더 크면 자리를 바꿔라 -> 큰게 뒤로 갔으니 오름차순
								//오른쪽 값이 더 크면 -> 내림차순
			return 1; //0,1 바꿔라
		}
		return -1;
	}
}

class Num {

	int n1;
	int n2;

	Num(int a, int b) {
		n1 = a;
		n2 = b;

	}

}
