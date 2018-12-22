package login_main_etc;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import foodReservation.FoodPayment;
import foodReservation.FoodReservationFrame;
import login_main_etc.Join;
import manageMent.Management;
import movieReservation.Chair;
import movieReservation.CheckReservationFrame;
import movieReservation.SelectSchedule;

/*
 * Dispatcher클래스의 용도 설명
 * 각 폼에서 특정 이벤트(=버튼 클릭)이 있을시 Dispatcher클래스에 static메소드로 정의 되어있는 goTo메소드를 호출
 * 하면 Dispatcher객체에선 전달받은 객체 다음에 호출할 폼을 new연산자로 호출.(그 전 폼은 dispose사용해서 닫고)
 */


public class Dispatcher {
	//회원 정보 필요시 static으로 선언했으니 클래스명으로 어디서든 호출가능
	public static Member loginMember;

	
//	로그인폼,회원가입폼, 영화정보폼, 예약(영화,날짜,시간)폼, 예약(좌석)폼
	public void goTo(LoginForm form,Member member) { //이 메소드만 loginForm에서 로그인할때 loginMember에 호출하기 위해 static으로 선언안함
		Dispatcher.loginMember=member;
		form.dispose();
		new MainView();
	}
	
	public void goTo(LoginForm form, Member member, String command) {
		Dispatcher.loginMember=member;
		form.dispose();
		new Management();
	}
	
	public static void goTo(LoginForm form) {
		new Join();
		form.dispose();
	}
	
	
	public static void goTo(MainView form, String command) {
		if(command.equals("toCheckReservation")) { //예약 확인 폼
			form.dispose();
			new CheckReservationFrame();
		}else if(command.equals("toLogout")) { //로그아웃처리후 로그인폼으로
			form.dispose();
			Dispatcher.loginMember=null;
			new LoginForm();
		}else if(command.equals("toReservation")) {//예약하기 위해 예약1폼으로 이동
			new SelectSchedule();
		}else if(command.equals("toResFood")) {
			new FoodReservationFrame();
		}else if(command.equals("toMyInfor")) {
			new MyInformation();
		}
	}
	
	public static void goTo(FoodReservationFrame form) {
		new MainView();
	}
	
	public static void goTo(FoodReservationFrame form, HashMap infor) {
		new FoodPayment(form,infor);
	}
	
	public static void goTo(CheckReservationFrame form) {
		new MainView();
		form.dispose();
	}
	
	public static void goTo(MyInformation form) {
		new MainView();
	}
	
	public static void goTo(MyInformation form, String command) {
		new LoginForm();
	}
	
	public static void goTo(Join form) {
		new LoginForm();
	}
	
	public static void goTo(JTextArea textArea, JLabel[] text, SelectSchedule form) {
		form.dispose();
		String MovieName = textArea.getText();
		String screenNum = text[0].getText();
		String playDate = text[1].getText();
		String playTime = text[2].getText();
		new Chair(MovieName, screenNum, playDate, playTime);
		
	}
	
	//알림창
	public static void goTo(String str) {
		if(str.equals("MainView")) {
			new MainView();
		}
		if(str.equals("SelectSchedule")) {
			new SelectSchedule();
		}
	}
	
	public static void goTo(SelectSchedule form) {
		new MainView();
	}
	
	public static void goTo(JFrame form) {
		new LoginForm();
	    form.dispose();
	}
}