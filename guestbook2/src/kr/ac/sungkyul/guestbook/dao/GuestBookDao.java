package kr.ac.sungkyul.guestbook.dao;

import java.sql.*;
import java.util.*;

import kr.ac.sungkyul.guestbook.vo.GuestbookVo;

public class GuestBookDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public boolean insert(GuestbookVo vo) {
		/* 방명록 작성 */
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection();

			String sql = "insert into GUESTBOOK values(seq_guestbook.nextval, ?, ?, ?, sysdate)";

			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (count == 1);
	}
	
	public boolean delete( GuestbookVo vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = getConnection();
			
			String sql = 
				"delete" +
				"  from guestbook" +
				" where no=?" +
				"   and password=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong( 1, vo.getNo() );
			pstmt.setString( 2, vo.getPassword() );
			
			count = pstmt.executeUpdate();
			
		} catch( SQLException e ) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException e ) {
				e.printStackTrace();
				return false;
			}
		}		
		
		return (count == 1);
	}

	
	public List<GuestbookVo> getList(){
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "select no, name, content, to_char(REG_DATE, 'yyyy-mm-dd pm hh12:mi:ss')from GUESTBOOK order by no desc";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setDate(regDate);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}