package Junit;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import DAO.part_time_DAO;
import DTO.part_time_DTO;

public class part_time_DAO_Test {
	part_time_DAO dao = null;

	@Before
	public void setDAO() {
		dao = part_time_DAO.getInstance();
	}

	@Test // 전체목록보기
	public void list() {
		ArrayList<part_time_DTO> list = dao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("-------------------------------------");
			System.out.println("no:" + list.get(i).getNo());
			System.out.println("회사명: " + list.get(i).getName());
			System.out.println("업무: " + list.get(i).getInfo());
			System.out.println("시급: " + list.get(i).getPay());
			System.out.println("근무시간: " + list.get(i).getWork_time());
			System.out.println("필요인원수: " + list.get(i).getCnt());
		}
	}

	@Test // 아르바이트채용정보 입력하기
	public void insertTest() {
		part_time_DTO test = new part_time_DTO();
		test.setName("테스트회사");
		test.setInfo("테스트중입니다.");
		test.setPay(8000);
		test.setWork_time(5);
		test.setCnt(0);
		dao.insert(test);
	}

	@Test // cnt 수량변경되는지 확인
	public void update() {
		dao.updatecnt(8); // 임시로 no 입력 인원수 변경되는지 확인
	}

	@Test // 출력 확인
	public void worktime() {
		ArrayList<part_time_DTO> list = dao.worktime();
		System.out.println("일일 아르바이트 근무시간이 4시간 미만인 근무처 목록 확인");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("-------------------------------------");
			System.out.println("no:" + list.get(i).getNo());
			System.out.println("회사명: " + list.get(i).getName());
			System.out.println("업무: " + list.get(i).getInfo());
			System.out.println("시급: " + list.get(i).getPay());
			System.out.println("근무시간: " + list.get(i).getWork_time());
			System.out.println("필요인원수: " + list.get(i).getCnt());
		}
	}

	@Test // 채용인원 다 뽑힌 회사 정보 삭제
	public void delete() {
		dao.delete();
	}
}
