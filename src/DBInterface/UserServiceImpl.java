package DBInterface;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.part_time_DAO;
import DAO.resume_DAO;
import DTO.part_time_DTO;
import DTO.resume_DTO;

public class UserServiceImpl implements UserService {
	private Scanner in = new Scanner(System.in);
	part_time_DAO partdao = part_time_DAO.getInstance();
	resume_DAO rdao = resume_DAO.getInstance();

	@Override
	public void list() {
		ArrayList<part_time_DTO> list = partdao.selectAll();
		System.out.println("+++아르바이트 채용 게시판+++");
		for (int i = 0; i < list.size(); i++) {
			part_time_DTO tempDTO = new part_time_DTO();
			tempDTO = list.get(i);
			System.out.println("----------------------------------------------");
			System.out.println("글번호: " + tempDTO.getNo());
			System.out.println("회사이름: " + tempDTO.getName());
			System.out.println("근무내용: " + tempDTO.getInfo());
			System.out.println("시급: " + tempDTO.getPay());
			System.out.println("근무시간: " + tempDTO.getWork_time());
			System.out.println("필요인원: " + tempDTO.getCnt());
		}
	}

	@Override
	public void resume() {
		list();
		System.out.println("고객님의 ID를 입력해주세요.");
		String id = in.nextLine();
		System.out.println("지원하고 싶은 채용공고 게시글 번호를 입력해주세요.");
		int num = in.nextInt();
		in.nextLine();
		System.out.println("이름을 입력해주세요.");
		String name = in.nextLine();
		System.out.println("나이를 입력해주세요.");
		int age = in.nextInt();
		in.nextLine();
		System.out.println("지원이 완료되었습니다. 합격여부는 회사의 연락이나 나의정보보기 메뉴를 확인해주세요.");

		resume_DTO rDTO = new resume_DTO();
		rDTO.setUserid(id);
		rDTO.setCompany_no(num);
		rDTO.setUsername(name);
		rDTO.setAge(age);

		rdao.insert(rDTO);
		partdao.updatecnt(num);
	}

	@Override
	public void mylist() {
		System.out.println("고객님의 ID를 입력해주세요.");
		String userid = in.nextLine();
		ArrayList<resume_DTO> list = rdao.selectone(userid);
		System.out.println("--------------------나의정보--------------------");
		System.out.println("합격여부는 아르바이트 채용지원 후 채용회사에서 확인 전까지는");
		System.out.println("w로 표시되고, 합격이면 y 불합격이면 n으로 표시됩니다.");
		for (int i = 0; i < list.size(); i++) {
			resume_DTO tempDTO = new resume_DTO();
			tempDTO = list.get(i);
			System.out.println("----------------------------------------------");
			System.out.println(tempDTO.getUserid() + "님의 정보 입니다.");
			System.out.println("채용지원 게시글번호: " + tempDTO.getCompany_no());
			System.out.println("이름: " + tempDTO.getUsername());
			System.out.println("나이: " + tempDTO.getAge());
			System.out.println("합격여부: " + tempDTO.getPass());
		}
	}

	@Override
	public void oenday_work() {
		ArrayList<part_time_DTO> list = partdao.worktime();
		System.out.println("+++단기 근무채용 게시판+++");
		for (int i = 0; i < list.size(); i++) {
			part_time_DTO tempDTO = new part_time_DTO();
			tempDTO = list.get(i);
			System.out.println("----------------------------------------------");
			System.out.println("글번호: " + tempDTO.getNo());
			System.out.println("회사이름: " + tempDTO.getName());
			System.out.println("근무내용: " + tempDTO.getInfo());
			System.out.println("시급: " + tempDTO.getPay());
			System.out.println("근무시간: " + tempDTO.getWork_time());
			System.out.println("필요인원: " + tempDTO.getCnt());
		}
	}

}
