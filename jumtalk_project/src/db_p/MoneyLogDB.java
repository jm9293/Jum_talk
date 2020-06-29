package db_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MoneyLogDB {
	
static final String host ="localhost";
	
	
	static String [][] getMONEYLOG(String userID ,int kind) {
		
		
		
		Connection con = null;
		Statement stmt=null;
		ResultSet rs =null;
		String [][] retarr=null;
		
		ArrayList<String []> resarr = new ArrayList<String[]>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":1521:xe", "HR", "HR");
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("select * from MONEYLOG where ID= '"+userID+"' and kind= '"+kind+"'");
			
			while (rs.next()) {
				rs.getString("ID");
				String money = rs.getInt("money")+"원";
				String coinnum = rs.getInt("coinnum")+"개";
				String time = rs.getString("time");
				
				String [] arr = {money,coinnum,time};
				
				resarr.add(arr);
			}
			
			retarr = new String[resarr.size()][];
			for (int i = 0; i < retarr.length; i++) {
				retarr[i] = resarr.get(i);
			}
			System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return retarr;
	}
static String [][] getMONEYLOG(int kind) {
		
		
		
		Connection con = null;
		Statement stmt=null;
		ResultSet rs =null;
		String [][] retarr=null;
		
		ArrayList<String []> resarr = new ArrayList<String[]>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":1521:xe", "HR", "HR");
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("select * from MONEYLOG where kind= '"+kind+"'");
			
			while (rs.next()) {
				String id = rs.getString("ID");
				String money = rs.getInt("money")+"원";
				String coinnum = rs.getInt("coinnum")+"개";
				String time = rs.getString("time");
				
				String [] arr = {id,money,coinnum,time};
				
				resarr.add(arr);
			}
			
			retarr = new String[resarr.size()][];
			for (int i = 0; i < retarr.length; i++) {
				retarr[i] = resarr.get(i);
			}
			System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return retarr;
	}
	
	
	static boolean saveMONEYLOG(String userID, int money, int coinnum, int kind) {
		boolean res = false;
		
		Connection con = null;
		Statement stmt=null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":1521:xe", "HR", "HR");
			
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO MONEYLOG (ID, MONEY, COINNUM, TIME, KIND) VALUES ('"+userID+"', '"+money+"', '"+coinnum+"', sysdate, '"+kind+"')");
			System.out.println("성공");
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return res;
		
		
	}

	public static void main(String[] args) {
		getMONEYLOG("admin",1);
		saveMONEYLOG("admin", 10000, 100,0);
	}

}
