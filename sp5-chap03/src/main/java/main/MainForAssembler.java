package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;

public class MainForAssembler {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  // Scanner과 같은 입력받는 방식
		
		while(true) {
			System.out.println("명령어를 입력하세요 :");
			String command = reader.readLine(); // scanner로 치면 sc.next(); 같은거
			if(command.equalsIgnoreCase("exit")) {   // equalsIgnoreCase:대소문자 구분 없이, equals:대소문자 구분함.
				System.out.println("종료합니다.");
				break;
			}
			if(command.startsWith("new")) {  // command가 new로 시작하는거
				processNewCommand(command.split(" "));
				continue;
			}else if(command.startsWith("change")) {  // command가 change로 시작하는거
				processChangeCommand(command.split(" "));
				continue;
			}
			
			printHelp(); // 명령어를 잘못 입력한 경우 도움말을 출력해주는 메서드 실행 ---> 메서드 말
		}
				

		
	}
	





	private static Assembler assembler = new Assembler();

	private static void processNewCommand(String[] split) {
		// TODO Auto-generated method stub
		
	}
	
	private static void processChangeCommand(String[] split) {
		// TODO Auto-generated method stub
		
	}
	
	private static void printHelp() {
		// TODO Auto-generated method stub
		
	}


	
}
