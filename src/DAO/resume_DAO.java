package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import DTO.resume_DTO;

public class resume_DAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "system";
	private String pwd = "oracle";
	private ResultSet rs = null;

	public static resume_DAO mydao = null;

	private resume_DAO() {
	}

	public static resume_DAO getInstance() {// dao 싱글톤
		if (mydao == null) {
			mydao = new resume_DAO();
		}
		return mydao;
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

	// 채용지원하기
	public void insert(resume_DTO input) {
		if (conn() != null) {
			PreparedStatement ppst = null;
			try {
				String sql = "insert into resume values (?,?,?,?,default)";
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, input.getUserid());
				ppst.setInt(2, input.getCompany_no());
				ppst.setString(3, input.getUsername());
				ppst.setInt(4, input.getAge());
				ppst.executeUpdate();
				System.out.println("아르바이트를 지원하였습니다.");
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

	// 내정보보기
	public ArrayList<resume_DTO> selectone(String userid) {
		String sql = "select*from resume where userid = ?";
		PreparedStatement ppst = null;
		ArrayList<resume_DTO> List = new ArrayList<>();
		resume_DTO DTO = new resume_DTO();
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, userid);
				rs = ppst.executeQuery();
				if (rs.next()) {
					DTO.setUserid(rs.getString("userid"));
					DTO.setCompany_no(rs.getInt("company_no"));
					DTO.setUsername(rs.getString("username"));
					DTO.setAge(rs.getInt("age"));
					DTO.setPass(rs.getString("pass"));
					List.add(DTO);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (conn != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return List;
	}

	// 지원자 합격여부 변경 메서드
	public void updatepass(String yesno, String userid) {
		String sql = "update resume set pass = ? where userid = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, yesno);
				ppst.setString(2, userid);
				ppst.executeUpdate();
				System.out.println("해당 지원자의 합격여부가 변경되었습니다.");
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
}
