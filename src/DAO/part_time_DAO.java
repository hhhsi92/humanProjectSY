package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.part_time_DTO;

public class part_time_DAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "system";
	private String pwd = "oracle";
	private ResultSet rs = null;

	public static part_time_DAO partdao = null;

	private part_time_DAO() {
	}

	public static part_time_DAO getInstance() {// 싱글톤
		if (partdao == null) {
			partdao = new part_time_DAO();
		}
		return partdao;
	}

	public Connection conn() { // DB연결
		try { //
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 채용정보 목록보기
	public ArrayList<part_time_DTO> selectAll() {
		String sql = "select*from part_time order by no";
		Statement st = null;
		ArrayList<part_time_DTO> tempList = new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);

				while (rs.next()) {
					part_time_DTO tempDTO = new part_time_DTO();
					tempDTO.setNo(rs.getInt("no"));
					tempDTO.setName(rs.getString("name"));
					tempDTO.setInfo(rs.getString("info"));
					tempDTO.setPay(rs.getInt("pay"));
					tempDTO.setWork_time(rs.getInt("work_time"));
					tempDTO.setCnt(rs.getInt("cnt"));

					tempList.add(tempDTO);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (st != null)
						st.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return tempList;
	}

	// 채용정보 입력하기
	public void insert(part_time_DTO input) {
		if (conn() != null) {
			PreparedStatement ppst = null;
			try {
				String sql = "insert into part_time values (seq_ptj.nextval,?,?,?,?,?)";
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, input.getName());
				ppst.setString(2, input.getInfo());
				ppst.setInt(3, input.getPay());
				ppst.setInt(4, input.getWork_time());
				ppst.setInt(5, input.getCnt());
				ppst.executeUpdate();
				System.out.println("아르바이트 채용정보가 입력되었습니다.");
			} catch (Exception e) {
			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 유저 채용 지원시 채용 인원 수량 cnt -1 update
	public void updatecnt(int no) {
		String sql = "update part_time set cnt = cnt-1 where no = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, no);
				ppst.executeUpdate();
				System.out.println("채용 공고 필요인원수가 -1 되었습니다.");
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 근무시간이 4시간 미만인 아르바이트 목록보기
	public ArrayList<part_time_DTO> worktime() {
		String sql = "select*from part_time where work_time <4";
		Statement st = null;
		ArrayList<part_time_DTO> tempList = new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);

				while (rs.next()) {
					part_time_DTO tempDTO = new part_time_DTO();
					tempDTO.setNo(rs.getInt("no"));
					tempDTO.setName(rs.getString("name"));
					tempDTO.setInfo(rs.getString("info"));
					tempDTO.setPay(rs.getInt("pay"));
					tempDTO.setWork_time(rs.getInt("work_time"));
					tempDTO.setCnt(rs.getInt("cnt"));
					tempList.add(tempDTO);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (st != null)
						st.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return tempList;
	}

	// 아르바이트 필요인원이 다 뽑힌 회사튜플 삭제
	public void delete() {
		String sql = "delete from part_time where cnt = 0";
		Statement st = null;
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				System.out.println("채용공고가 마감된 목록이 삭제되었습니다.");
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (st != null)
						st.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
