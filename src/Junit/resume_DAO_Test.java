package Junit;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.resume_DAO;
import DTO.part_time_DTO;
import DTO.resume_DTO;

public class resume_DAO_Test {
	resume_DAO dao = null;

	@Before
	public void setDAO() {
		dao = resume_DAO.getInstance();
	}

	@Test
	// 채용지원 입력 저장 test
	public void insert() {
		resume_DTO test2 = new resume_DTO();
		test2.setUserid("human");
		test2.setUsername("알바몬");
		test2.setCompany_no(5);
		test2.setAge(20);
		dao.insert(test2);
	}

	@Test
	// 지원자 합격여부 변경 //처음 default값 w (w = 대기라는 의미로 지정)
	public void passupdate() {
		dao.updatepass("y", "human");
	}

	@Test
	// 내 정보보기
	public void mylist() {
		ArrayList<resume_DTO> list = dao.selectone("human");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("-------------------------------------");
			System.out.println("이름 :" + list.get(i).getUsername());
			System.out.println("게시번호 :" + list.get(i).getCompany_no());
			System.out.println("나이 :" + list.get(i).getAge());
			System.out.println("합격여부 :" + list.get(i).getPass());
		}
	}
}
