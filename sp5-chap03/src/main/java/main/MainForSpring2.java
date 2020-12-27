package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConf1;
import config.AppConf2;
//import config.AppCtx;
import config.AppConfImport;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.VersionPrinter;
import spring.WrongIdPasswordException;

public class MainForSpring2 {
	
	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {
	 //	ctx = new AnnotationConfigApplicationContext(AppCtx.class);
	 // AppCtx클래스를 AppConf1, AppConf2 클래스로 나누어 설정했을때, 아래와 같이 코드 작성하면 됨.
	    ctx = new AnnotationConfigApplicationContext(AppConf1.class,AppConf2.class);
	
	// @Configuration 설정 클래스도 빈으로 등록된다는 것을 확인.
	    AppConf2 appConf2 = ctx.getBean(AppConf2.class);
	    System.out.println(appConf2 != null); // true 출력.
	    
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if(command.startsWith("new ")) {
				//command에서 공백을 기준으로 잘라서 문자열 배열을 생성해준 후, processNewCommand 메소드에 전달.
				processNewCommand(command.split(" ")); 
				continue;
			}else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}else if(command.equals("list")) {
				processListCommand();
				continue;
			}else if(command.startsWith("info")) {
				processInfoCommand(command.split(" "));
				continue;
			}else if(command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}
	}


	

	private static void processNewCommand(String[] arg) {   // 새로운 회원 정보 생성.
		if(arg.length != 5) {
			printHelp();   // 명령어를 잘못 입력한 경우 도움말 출력해주는 메서드.
			return;   // for문에서 break;한거 와 같은 의미. 원래 void메소드에서는 리턴 값이 없음.
		}//if문 end
		
		MemberRegisterService regSvc = 
				ctx.getBean("memberRegSvc", MemberRegisterService.class); // 스프링 컨테이너로부터 이름이 "memberRegSvc"인 빈 객체를 구함.
		RegisterRequest req = new RegisterRequest(); //회원가입.
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {  // 입력한 암호 값이 올바르지 않을 때,
			System.out.println("암호와 확인이 일치하지 않습니다.");
			return;
		}//if문 end
		
		try { //예외처리.
			regSvc.regist(req); //새로운 회원 데이터가 보관됨.
			System.out.println("등록했습니다.");
			
		} catch (DuplicateMemberException e) {   // 동일한 이메일을 가진 회원 데이터가 존재할 때,
			System.out.println("이미 존재하는 이메일입니다.");
		} 	
	}// processNewCommand() end


	private static void processChangeCommand(String[] arg) { // 암호 변경 기능 메소드.
		if(arg.length != 4) {
			printHelp();
			return;
		}//if문 end
		
		ChangePasswordService changePwdSvc =
				ctx.getBean("changePwdSvc", ChangePasswordService.class); // 스프링 컨테이너로부터 이름이 "changePwdSvc"인 빈 객체를 구함.
		
		try { //예외처리.
			changePwdSvc.changePassword(arg[1],arg[2],arg[3]); // 객체의 암호 변경 실행.
			System.out.println("암호를 변경했습니다."); 
		} catch (MemberNotFoundException e) {    //이메일에 해당하는 회원 데이터가 존재하지 않을 때,
			System.out.println("존재하지 않는 이메일입니다.");
			
		} catch (WrongIdPasswordException e) {  // 암호가 올바르지 않을 때,
		}
		
	}// processChangeCommand() end. 
	
	// processNewCommand()에서 MemberRegisterService 객체를 사용함. Assembler에서  MemberRegisterService는 객체를 생성할 때 MemberDao객체를 주입함.
	// -------> processNewCommand() 실행에 성공하면, MemberDao의 map에 생성된 회원데이터를 추가.
	// processChangeCommand()에서 ChangePasswordService 객체를 사용함. Assembler에서  ChangePasswordService는 객체를 생성할 때 MemberDao객체를 주입함.
	// -------> processChangeCommand() 실행에 성공하면, MemberDao의 map에 보관된 회원 데이터의 암호가 변경됨.
	
	
	private static void printHelp() {  // 도움말을 출력하는 메소드.
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
			
	}// printHelp() end
	
	// 리스트 출력 메소드
	private static void processListCommand() {
		MemberListPrinter listPrinter=
				ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
		
	}// processListCommand() end
	
	// 이메일로 이메일 같은 멤버 찾기
	private static void processInfoCommand(String[] arg) {
		if(arg.length !=2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter =
				ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
		
	}// processInfoCommand() end
	
	// version출력 메서드
	private static void processVersionCommand() {
		VersionPrinter versionPrinter =
				ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
		
	}// processVersionCommand() end



	
	



}
