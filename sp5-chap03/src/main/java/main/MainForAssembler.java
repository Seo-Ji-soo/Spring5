package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

public class MainForAssembler {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));  // Scanner과 같은 입력받는 방식. 
		
		while(true) {
			System.out.println("명령어를 입력하세요 :");
			String command = reader.readLine(); // scanner로 치면 sc.next(); 같은거. readLine() 메소드를 이용하여 입력 받음.
			if(command.equalsIgnoreCase("exit")) {   // equalsIgnoreCase:대소문자 구분 없이, equals:대소문자 구분함.
				System.out.println("종료합니다.");
				break;
			}
			if(command.startsWith("new")) {  // command가 new로 시작하는거.
				processNewCommand(command.split(" ")); // 공백을 구분자로 이용해서 콘솔에서 입력받은 문자열을 배열로 만든다.
				continue;
			}else if(command.startsWith("change")) {  // command가 change로 시작하는거
				processChangeCommand(command.split(" ")); 
				continue;
			} // processNewCommand()메서드와 processChangeCommand() 메서드에 전달 되는 값은 문자열 배열.
			
			printHelp(); // 명령어를 잘못 입력한 경우 도움말을 출력해주는 메서드 실행.
		}
				

		
	}
	





	private static Assembler assembler = new Assembler();

	private static void processNewCommand(String[] arg) {   // ---------무슨메소드?????????????
			if(arg.length != 5) {
				printHelp();   // 명령어를 잘못 입력한 경우 도움말 출력해주는 메서드.
				return;   // for문에서 break;한거 와 같은 의미. 원래 void메소드에서는 리턴 값이 없음.
			}//if문 end
			
			MemberRegisterService regSvc = assembler.getMemberRegisterService();
			RegisterRequest req = new RegisterRequest();
			req.setEmail(arg[1]);
			req.setName(arg[2]);
			req.setPassword(arg[3]);
			req.setConfirmPassword(arg[4]);
			
			if(!req.isPasswordEqualToConfirmPassword()) {  // 패스워드가 같지 않으면.
				System.out.println("암호와 확인이 일치하지 않습니다.");
				return;
			}//if문 end
			
			try { //예외처리.
				regSvc.regist(req); //--------?????????????????
				System.out.println("등록했습니다.");
				
			} catch (DuplicateMemberException e) {
				System.out.println("이미 존재하는 이메일입니다.");
			} 	
	}// processNewCommand() end

	
	private static void processChangeCommand(String[] arg) { //-------------->무슨메소드??????????????????
		if(arg.length != 4) {
			printHelp();
			return;
		}//if문 end
		
		ChangePasswordService changePwdSvc =
				assembler.getChangePasswordService();   // 패스워드 바꾸는 메소드.
		
		try { //예외처리.
			changePwdSvc.changePassword(arg[1],arg[2],arg[3]);
			System.out.println("암호를 변경했습니다.");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.");
			
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.");
		}
		
	}
	
	
	private static void printHelp() {
		
		
	}


	
}
