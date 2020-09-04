package Manager;

import java.util.Scanner;

import DBInterface.UserServiceImpl;

public class Setting {
	private Scanner in = new Scanner(System.in);
	private UserServiceImpl service = new UserServiceImpl();

	public Setting() {
		while (true) {
			menu();
			int num = in.nextInt();
			in.nextLine();

			switch (num) {
			case 1:
				list();
				break;
			case 2:
				resume();
				break;
			case 3:
				mylist();
				break;
				
			case 4:	
				oenday_work();
				break;
				
			default:
			}
		}
	}

	public void menu() {
		System.out.println("--------------------------------------");
		System.out.println("1.전체목록보기");
		System.out.println("2.채용지원하기");
		System.out.println("3.나의정보보기");
		System.out.println("4.단기근무채용보기");
		System.out.println("--------------------------------------");
	}

	public void list() {
		service.list();
	}

	public void resume() {
		service.resume();
	}

	public void mylist() {
		service.mylist();
	}

	public void oenday_work() {
		service.oenday_work();
	}
}
