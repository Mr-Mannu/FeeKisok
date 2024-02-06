package FEEKIOSK;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import schooldata.DataBaseConnection;
import schooldata.SchoolInfo;
import schooldata.ThemeInfo;
import session_work.RegexPattern;

@ManagedBean(name = "feeLoginBean")
@ViewScoped
public class FeekioskBean implements Serializable {

	private static final long serialVersionUID = 1L;
	String regex = RegexPattern.REGEX;
	private String id;
	private String pswd;
	ArrayList<StudentInfo> stdList = new ArrayList<StudentInfo>();
	String billMsg, schoolId;
	String header, msg;
	String otp;
	String button = "GENERATE PIN";
	ArrayList<SchoolInfo> list = new ArrayList<>();
	String confirmpin, pin;
	DatabaseMethodkiosk DBK = new DatabaseMethodkiosk();

	public FeekioskBean() throws ParseException {
		HttpSession ss = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		try {
			ss.invalidate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	public String check() {
		Connection conn = DataBaseConnection.javaConnection();
		try {

			String regex = "^\\d{10}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(id);

			if (matcher.matches()) {

				if (id.length() == 10 && !id.equals("2222222222") && !id.equals("9999999999")
						&& !id.equals("1111111111") && !id.equals("1234567890") && !id.equals("0123456789")) {
					
					// number exist or not

					boolean checkNumberExistance = DBK.checkNumberExistsOrNot(id, conn);
					if (checkNumberExistance) {
						
						HttpSession ss = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
						ss.setAttribute("username", id);
//						stdList = DBK.getstudentsDetails(id,pin,conn);
//						ss.setAttribute("studentList", stdList);
						FacesContext fc = FacesContext.getCurrentInstance();
						ExternalContext ec = fc.getExternalContext();
						try {
							PrimeFaces.current().executeScript("PF('statusDialog').show()");
							PrimeFaces.current().ajax().update("ajaxDia");
							
							
							ec.redirect("homePage.xhtml");
						} catch (IOException e) {
							e.printStackTrace();
						}
//	LOGIN VIA PIN						
//						boolean checkPin = DBK.checkPin(id, conn);
//						if (checkPin) {
							
//							FacesContext fc = FacesContext.getCurrentInstance();
//							ExternalContext ec = fc.getExternalContext();

//							try {
//								ec.redirect("feeKioskPin.xhtml");
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//						} else {
//							
//							header = "Information".toUpperCase();
//							msg = "Pin is not generated yet for login please GENERATE PIN first.";
//							PrimeFaces.current().executeScript("PF('infoDialog').show()");
//							PrimeFaces.current().ajax().update("infoDia");
//						}

					} else {

						header = "Validation error".toUpperCase();
						msg = "Please Check Number Again! Number is Not Registred";
						PrimeFaces.current().executeScript("PF('commonDialog').show()");
						PrimeFaces.current().ajax().update("commonDia");

					}

				} else {
					header = "Validation error".toUpperCase();
					msg = "Please Check Number Again! Number Not Acceptable";
					PrimeFaces.current().executeScript("PF('commonDialog').show()");
					PrimeFaces.current().ajax().update("commonDia");
				}
			} else {
				header = "Validation error".toUpperCase();
				msg = "Please Check Number Again!";
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

	public void generate() {

		Connection conn = DataBaseConnection.javaConnection();
		
		PrimeFaces.current().executeScript("PF('infoDialog').hide()");
		PrimeFaces.current().ajax().update("infoDia");

//		PrimeFaces.current().executeScript("PF('changeDialog').show()");
//		PrimeFaces.current().ajax().update("chnagePinForm");

		// generate otp
		int otp = (int) (Math.random() * 9000) + 1000;
		String typemessage = "Hello User, " + String.valueOf(otp)
				+ " is  your pin generate OTP.Treat this as confidential.";

		// fix otp for testing.
		otp = 1234;

		// otp sending method

		String dltId = "";
		String sender = "";
		DBK.sendOtp(typemessage, id, dltId, sender);
		
		DBK.updateOtpInTable(id,otp,conn);
		

		
		header = "Confirmation".toUpperCase();
		msg = "Please confirm OTP sent on your Number "+id;
		PrimeFaces.current().executeScript("PF('changeDialog').show()");
		PrimeFaces.current().ajax().update("chnagePinForm");
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void verifyOtp() {
		
		
		PrimeFaces.current().executeScript("PF('changeDialog').hide()");
		PrimeFaces.current().ajax().update("chnagePinForm");
		
		Connection conn = DataBaseConnection.javaConnection();
		
		boolean verify = DBK.verifyOtp(id,otp,conn);
		if(verify) {
			
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			try {
				ec.redirect("changeFeekioskPin.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}else {
			
			header = "Confirmation".toUpperCase();
			msg = "OTP is not correct please check and confirm OTP sent on your Number "+id;
			PrimeFaces.current().executeScript("PF('changeDialog').show()");
			PrimeFaces.current().ajax().update("chnagePinForm");
			
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	public void skip() {
		PrimeFaces.current().executeScript("PF('commonDialog').hide()");
		PrimeFaces.current().ajax().update("commonDia");
	}

	
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

	public String getConfirmpin() {
		return confirmpin;
	}

	public void setConfirmpin(String confirmpin) {
		this.confirmpin = confirmpin;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	
}
