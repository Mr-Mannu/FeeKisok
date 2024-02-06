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

@ManagedBean(name = "changepinBean")
@ViewScoped
public class ChangePinFeekiosk implements Serializable {

	private static final long serialVersionUID = 1L;
	String regex = RegexPattern.REGEX;
	private String pin;
	private String cpin;
	String billMsg, schoolId;
	String header,msg;
	int error = 0;
	String  username;
	ArrayList<SchoolInfo> list = new ArrayList<>();

	public ChangePinFeekiosk() throws ParseException {
		HttpSession httpSession=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		username = (String) httpSession.getAttribute("username");
		System.out.println(username);
	}

	public String check() {
		System.out.println(username);
		Connection conn = DataBaseConnection.javaConnection();
		DatabaseMethodkiosk DBK = new DatabaseMethodkiosk();
		try {
			if(pin.equalsIgnoreCase(cpin)) {
				System.out.println(username);
				System.out.println("fathers - "+username);
				int i = DBK.changePin(username,pin,conn);
				System.out.println(i);
				if(i>0) {
					System.out.println("success");
					header = "SUCCESSFULL".toUpperCase();
					msg = "PIN CHANGED SUCCESSFULLY ! ";
					error = 1;
					PrimeFaces.current().executeScript("PF('commonDialog').show()");
					PrimeFaces.current().ajax().update("commonDia");
					
					
				}else {
					header = "Validation error".toUpperCase();
					msg = "SOMETHING WENT WRONG!";
					error = 1;
					PrimeFaces.current().executeScript("PF('commonDialog').show()");
					PrimeFaces.current().ajax().update("commonDia");
				}
				
			}else {
				header = "Validation error".toUpperCase();
				msg = "Please Check pin Again!";
				error = 0;
				PrimeFaces.current().executeScript("PF('commonDialog').show()");
				PrimeFaces.current().ajax().update("commonDia");
			}
		} finally {

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return "";
	}
	
	public void skip() {
		
		if(error == 0) {
			PrimeFaces.current().executeScript("PF('commonDialog').hide()");
			PrimeFaces.current().ajax().update("commonDia");
		}else {
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			try {
				ec.redirect("feeKiosk.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}



	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCpin() {
		return cpin;
	}

	public void setCpin(String cpin) {
		this.cpin = cpin;
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
	

}
