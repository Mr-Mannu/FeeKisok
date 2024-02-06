package FEEKIOSK;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import schooldata.DataBaseConnection;
import schooldata.SchoolInfo;
import session_work.RegexPattern;

@ManagedBean(name = "feepinBean")
@ViewScoped
public class FeekioskPinBean implements Serializable {

	private static final long serialVersionUID = 1L;
	String regex = RegexPattern.REGEX;
	private String id;
	String pin;
	private String pswd;
	String billMsg, schoolId;
	String header, msg, username;
	ArrayList<SchoolInfo> list = new ArrayList<>();
	DatabaseMethodkiosk DBK = new DatabaseMethodkiosk();

	public FeekioskPinBean() throws ParseException {
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);
		username = (String) httpSession.getAttribute("username");
	}

	public String check() {
		Connection conn = DataBaseConnection.javaConnection();
		boolean checkPin = DBK.verifyPinOfUser(username, pin, conn);
		if (checkPin) {
			
			HttpSession ss = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			ss.setAttribute("kioskpin", pin);
			
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			try {
				ec.redirect("homePage.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "homePage.xhtml";
			
		} else {
			header = "Validation error".toUpperCase();
			msg = "Not Authorized Pin ! Please Check pin Again!";
			PrimeFaces.current().executeScript("PF('commonDialog').show()");
			PrimeFaces.current().ajax().update("commonDia");
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "";
	}

	public void generate() {

	}

	public void skip() {
		PrimeFaces.current().executeScript("PF('commonDialog').hide()");
		PrimeFaces.current().ajax().update("commonDia");
	}

//	public ThemeInfo themeSetting() {
//		String schid = new DatabaseMethods1().schoolId();
//		if (schid.equals("")) {
//			schid = "302";
//		}
//		Connection conn = DataBaseConnection.javaConnection();
//		Statement st = null;
//		ResultSet rr = null;
//		ResultSet rr1 = null;
//		ThemeInfo info = new ThemeInfo();
//		try {
//			st = conn.createStatement();
//			String qq = "select * from theme_setting where schid='" + schid + "'";
//			System.out.println(qq);
//			rr = st.executeQuery(qq);
//			if (rr.next()) {
//				System.out.println("in geetting");
//				info.setLayoutPrimaryColor(rr.getString("layoutPrimaryColor"));
//				info.setTopbarTheme(rr.getString("topbarTheme"));
//				info.setComponentTheme(rr.getString("componentTheme"));
//				info.setMenuMode(rr.getString("menuMode"));
//				info.setMenuColor(rr.getString("menuColor"));
//				info.setMenuTheme(rr.getString("menuTheme"));
//				info.setInputStyle(rr.getString("inputStyle"));
//			} else {
//				qq = "select * from theme_setting where schid='302'";
//				//// system.out.println(qq);
//				rr1 = st.executeQuery(qq);
//				if (rr1.next()) {
//					info.setLayoutPrimaryColor(rr1.getString("layoutPrimaryColor"));
//					info.setTopbarTheme(rr1.getString("topbarTheme"));
//					info.setComponentTheme(rr1.getString("componentTheme"));
//					info.setMenuMode(rr1.getString("menuMode"));
//					info.setMenuColor(rr1.getString("menuColor"));
//					info.setMenuTheme(rr1.getString("menuTheme"));
//					info.setInputStyle(rr1.getString("inputStyle"));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (st != null) {
//					st.close();
//				}
//				if (rr != null) {
//					rr.close();
//				}
//				if (rr1 != null) {
//					rr1.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return info;
//	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillMsg() {
		return billMsg;
	}

	public void setBillMsg(String billMsg) {
		this.billMsg = billMsg;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public ArrayList<SchoolInfo> getList() {
		return list;
	}

	public void setList(ArrayList<SchoolInfo> list) {
		this.list = list;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

}
