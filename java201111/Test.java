package java201111;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Test {

	public static void main(String[] args) {
		//데이터 표현 방식
		
		//JSON <제목> ㅊㅊ </제목>
		//XML 제목 : ㅇㅇㅇ

		//키 : 벨류
		//[] 배열 //{} 오브젝트 //중괄호로 감싼건 하나로 본다.
		//json 에서 숫자는 int 가 아닌 long
		String strJson = "[0,"
				+ "{\\\"1\\\":{\\\"2\\\":{\\\"3\\\":{\\\"4\\\":[5,{\\\"6\\\":7}]}}}}"
				+ "]";
		JSONArray array = (JSONArray)JSONValue.parse(strJson); //배열
		
		System.out.println(array.get(1));
		System.out.println();
		
		JSONObject obj2 = (JSONObject)array.get(1);//맵
		System.out.println("Field \"1\"");
		System.out.println(obj2.get("1"));    
		
		// 파일 쓰기
		try {
			// 파일 객체 생성
			File file = new File("C:/test/test.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			// BufferedWriter 내용을 한번에 읽어줌

			if (file.isFile() && file.canWrite()) {
				// 쓰기
				bufferedWriter.write("문자열 추가1");
				// 개행문자쓰기
				bufferedWriter.newLine();
				bufferedWriter.write("문자열 추가2");

				bufferedWriter.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		// 파일 읽기
		try {
			// 파일 객체 생성
			File file = new File("C:/test/test.txt");
			// 입력 스트림 생성
			FileReader filereader = new FileReader(file);
			// 입력 버퍼 생성
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) { // readLine 한줄읽다가 더 읽을게 없으면 null 리턴
				System.out.println(line);
			}
			// .readLine()은 끝에 개행문자를 읽지 않는다.
			bufReader.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}

}
