package FEEKIOSK;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Json.DataBaseMeathodJson;
import schooldata.DailyFeeCollectionBean;
import schooldata.DatabaseMethods1;
import schooldata.Route;
import schooldata.SchoolInfoList;
import FEEKIOSK.StudentInfo;

public class DatabaseMethodkiosk implements Serializable {
	
	DatabaseMethods1 dd = new DatabaseMethods1();

	public boolean checkNumberExistsOrNot(String id, Connection conn) {
		boolean result = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "select *from registration1 where (fathersPhone = ? || mothersPhone = ?) and status = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, id);
			ps.setString(3, "ACTIVE");
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		return result;
	}

	public boolean checkPin(String id, Connection conn) {
		boolean result = false;
		String pin = "";
		int len = 0;
		int count = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		PreparedStatement ps1 = null;
		try {
			String query = "select *from registration1 where fathersPhone = ? and status = ? ";
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, "ACTIVE");
			rs = ps.executeQuery();
			len = rs.getFetchSize();
			System.out.println(len);
			while (rs.next()) {
				System.out.println(rs.getString("fname"));
				if (rs.getString("fee_kiosk_pin").equals("")) {
					result = false;
				} else {
					count = count + 1;
					pin = rs.getString("fee_kiosk_pin");
					result = true;
				}
			}

			if (count == 0) {
				result = false;
			} else if (len == count) {
				result = true;
			} else {
				try {
					String qq = "update registration1 set fee_kiosk_pin = ? where fathersPhone = ? and status = ?";
					ps1 = conn.prepareStatement(qq);
					ps1.setString(1, pin);
					ps1.setString(2, id);
					ps1.setString(3, "ACTIVE");
					ps1.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}

				result = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			if (ps1 != null) {
				try {
					ps1.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		return result;
	}

	public void sendOtp(String message1, String contactmo, String dltId, String sender1) {
		String api = "";
		String message2 = null;
		try {
			message2 = URLEncoder.encode(message1, "UTF-8");
		} catch (UnsupportedEncodingException e1) {

			e1.printStackTrace();
		}
		String apiKey = "authkey=" + api;
		String message = "&message=" + message2;
		String sender = "&sender=" + sender1;
		String numbers = "&mobiles=" + contactmo;
		String custom = "&route=" + "B";
		String DltId = "&Template_ID=" + dltId;

		// Send data
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(
					"http://sms.bulksmsserviceproviders.com/api/send_http.php?").openConnection();
			String data = apiKey + numbers + message + sender + custom + DltId + "&unicode=1";
			if (dltId.equals("")) {
				data = apiKey + numbers + message + sender + custom + "&unicode=1";
			}
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			String result = stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateOtpInTable(String id, int otp, Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "update registration1 set fee_kiosk_otp = ? where fathersPhone = ? and status = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, otp);
			ps.setString(2, id);
			ps.setString(3, "ACTIVE");
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	public boolean verifyOtp(String id, String otp, Connection conn) {
		boolean result = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "select fee_kiosk_otp from registration1 where fathersPhone = ? and status = ? group by fee_kiosk_otp";
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, "ACTIVE");
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("fee_kiosk_otp").equalsIgnoreCase(otp)) {
					result = true;
				} else {
					result = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return result;
	}

	public int changePin(String id, String pin, Connection conn) {
		int i = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "update registration1 set fee_kiosk_pin = ? where fathersPhone = ? and status = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, pin);
			ps.setString(2, id);
			ps.setString(3, "ACTIVE");
			System.out.println(ps);
			int k = ps.executeUpdate();
			if (k > 0) {
				i = i + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return i;
	}

	public boolean verifyPinOfUser(String id, String pin, Connection conn) {
		boolean result = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println("firsy - > " + pin);
		try {
			String query = "select fee_kiosk_pin from registration1 where fathersPhone = ? and status = ? group by fee_kiosk_pin";
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, "ACTIVE");
			rs = ps.executeQuery();
			System.out.println(ps);
			if (rs.next()) {
				System.out.println("pin");
				System.out.println(rs.getString("fee_kiosk_pin"));
				System.out.println(pin);
				if (rs.getString("fee_kiosk_pin").equalsIgnoreCase(pin)) {
					result = true;
				} else {
					result = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return result;
	}

	public FeeKioskStudentInfo fullSchoolInfo(String schid, Connection con) {
		FeeKioskStudentInfo info = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 1;
		try {
			st = con.createStatement();
			String qq = "Select * from schoolinfo where schid='" + schid + "'";
			rs = st.executeQuery(qq);

			if (rs.next()) {
				info = new FeeKioskStudentInfo();
				info.setSno(count++);

				info.setDltEntityName(rs.getString("dlt_entity_name"));
				info.setSmsSchoolName(rs.getString("sms_school_name") + "\n" + info.getDltEntityName());
				info.setHindiName(rs.getString("hindi_school_name") + "\n" + info.getDltEntityName());
				info.setSchoolName(rs.getString("schoolname"));
				info.setAdd1(rs.getString("address1"));
				info.setAdd2(rs.getString("address2"));
				info.setAdd3(rs.getString("address3"));
				info.setAdd4(rs.getString("address4"));
				info.setPhoneNo(rs.getString("phoneno"));
				info.setMobileNo(rs.getString("mobileno"));
				info.setEmailId(rs.getString("email_id"));
				info.setWebsite(rs.getString("website"));
				info.setSchid(rs.getString("schid"));
				info.setRegNo(rs.getString("reg_no"));
				info.setSchRegNo(rs.getString("sch_reg_no"));
				info.setUsername(rs.getString("authkey"));
				info.setKey(rs.getString("senderkey"));
				info.setDownloadpath(rs.getString("downloadpath"));
				info.setUploadpath(rs.getString("uploadpath"));
				info.setImagePath(rs.getString("image_path"));
				info.setMarksheetHeader(rs.getString("marksheet_header"));
				info.setCertificateHeader(rs.getString("certificate_header"));
				info.setInstallSession(rs.getString("install_session"));
				info.setDefaultSession(rs.getString("default_session"));
				info.setProjecttype(rs.getString("ptype"));
				info.setApikey(rs.getString("apikey"));
				info.setAdminApikey(rs.getString("adminserverkey"));
				info.setReciept(rs.getInt("reciept"));
				info.setRname1(rs.getString("rname1"));
				info.setRname2(rs.getString("rname2"));
				info.setRname3(rs.getString("rname3"));
				info.setCtype(rs.getString("ctype"));
				info.setType(rs.getString("type"));
				info.setHomeworkMessage(rs.getString("homework_message"));
				info.setSchoolAppName(rs.getString("app_name"));
				info.setStudentApp(rs.getString("student_app"));
				info.setAdminApp(rs.getString("admin_app"));
				info.setSchoolSession(rs.getString("session_month"));
				info.setFeeStart(rs.getString("fees_from"));
				info.setLectureLabel(rs.getString("lecture_label"));
				info.setPermitAttendance(rs.getString("attendance_message_teacher"));
				info.setPermitLeave(rs.getString("teacher_leave_action"));
				info.setPaytm_marchent_key(rs.getString("paytm_marchent_key"));
				info.setPaytm_mid(rs.getString("paytm_mid"));
				info.setGpsApi(rs.getString("gps_apikey"));
				info.setGpsUser(rs.getString("gps_username"));
				info.setGpsPwd(rs.getString("gps_pwd"));
				info.setGpsProvider(rs.getString("gps_provider"));
				info.setLoginOtp(rs.getString("login_otp"));
				info.setDiscountotp(rs.getString("discount_otp"));
				info.setDiscountOtpNo(rs.getString("discount_otp_no"));
				info.setCancelOtpNo(rs.getString("cancel_otp_no"));
				info.setAutoCollectionNo(rs.getString("collection_no"));
				info.setAutoCollection(rs.getString("autocollection"));
				info.setCancalfee(rs.getString("cancel_otp"));
				info.setSrnoType(rs.getString("srno_type"));
				info.setSrnoPrefix(rs.getString("srno_prefix"));
				info.setSrnoStart(rs.getString("srno_start"));
				info.setConcessionRequest(rs.getString("concession_request"));
				info.setStudentAllowDays(rs.getInt("std_allow_days"));
				info.setTeacherAllowDays(rs.getInt("tchr_allow_days"));
				info.setStdAllowQuantity(rs.getInt("std_allow_quant"));
				info.setTeacherAllowQuantity(rs.getInt("tchr_allow_quant"));
				info.setPenaltySetting(rs.getString("penalty_setting"));

				info.setDistrict(rs.getString("district"));
				info.setState(rs.getString("state"));
				info.setZone(rs.getString("zone"));
				info.setEnqType(rs.getString("enq_pros_reg"));
				info.setTcType(rs.getString("tc_type"));
				info.setSchType(rs.getString("sch_type"));

				info.setAppointStartTime(rs.getString("appointStartTime"));
				info.setAppointEndTime(rs.getString("appointEndTime"));
				info.setGalleryRequest(rs.getString("gallery_request"));
				info.setAttendantComm(rs.getString("attendant_comm"));
				info.setRfidComm(rs.getString("rfid_comm"));
				info.setTimeTableSchedule(rs.getString("timeTableSchedule"));
				info.setDefaultSession(rs.getString("default_session"));
				info.setTcHeader(rs.getString("tc_header"));
				info.setStudentWebLogin(rs.getString("student_web_login"));
				info.setJwt(rs.getString("jwt"));
				info.setMeetingApproval(rs.getString("meeting_approval"));
				info.setPg_type(rs.getString("pg_type"));
				info.setRzp_mid(rs.getString("rzp_mid"));
				info.setRzp_key(rs.getString("rzp_key"));
				info.setRzp_key_secret(rs.getString("rzp_key_secret"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return info;
	}

	public String classSectionNameFromidSchidSession(String schid, String sectionid, String session, Connection conn) {

		Statement st = null;
		ResultSet rr = null;
		try {
			st = conn.createStatement();
			String query = "select * from section where id='" + sectionid + "' and status='ACTIVE' and schid in ('"
					+ schid + "') and session like '%" + session + "%'";
			rr = st.executeQuery(query);
			if (rr.next()) {
				return rr.getString("classid");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			if (rr != null) {
				try {
					rr.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return "";
	}

	public String sectionNameByIdSchidSession(String schid, String sectionid, String session, Connection conn) {

		Statement st = null;
		ResultSet rr = null;
		try {
			st = conn.createStatement();
			String query = "select * from section where id='" + sectionid + "' and schid in ('" + schid
					+ "') and status='ACTIVE' and session like '%" + session + "%'";
			rr = st.executeQuery(query);
			if (rr.next()) {
				return rr.getString("name");
			} else
				return "";
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			if (rr != null) {
				try {
					rr.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public String classNameFromidSchid(String schid, String id, String session, Connection conn) {
		Statement st = null;
		ResultSet rr = null;
		try {
			st = conn.createStatement();
			String query = "select name from class where groupid='" + id + "' and status='ACTIVE' and schid in ('"
					+ schid + "') and session='" + session + "' order by id desc ";
			rr = st.executeQuery(query);
			if (rr.next()) {
				return rr.getString("name");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (rr != null) {
				try {
					rr.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public String studentCategoryById(String id, Connection conn) {

		Statement st = null;
		ResultSet rr = null;
		try {
			st = conn.createStatement();
			String query = "select * from studentcategorylist where id='" + id + "'";
			rr = st.executeQuery(query);
			if (rr.next()) {
				return rr.getString("category");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (rr != null) {
				try {
					rr.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public String concessionCategoryNameById(String schid, String id, Connection conn) {
		String name = "";
		Statement st = null;
		ResultSet rr = null;
		// String schId = new DatabaseMethods1().schoolId();
		try {
			st = conn.createStatement();
			String query = "select * from consession_category where id='" + id + "' and schid='" + schid + "'";
			rr = st.executeQuery(query);
			if (rr.next()) {
				name = rr.getString("category");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ee) {
					ee.printStackTrace();
				}
			}

			if (rr != null) {
				try {
					rr.close();
				} catch (SQLException ee) {
					ee.printStackTrace();
				}
			}
		}
		return name;
	}

	public String presentRouteStatusSession(String schid, String sid, String session, Connection conn) {
		Statement st = null;
		ResultSet rr = null;
		String preYear = session.split("-")[0];
		String postYear = session.split("-")[1];

		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
		if (month >= 1 && month <= 3) {
			year = Integer.parseInt(postYear);
		} else {
			year = Integer.parseInt(preYear);
		}

		try {
			st = conn.createStatement();
			String query = "Select route_id from transport_fee_table where  student_id='" + sid + "' and session='"
					+ session + "' and schid = '" + schid + "' and route_id!='0' and status='Yes' and " + "month='"
					+ month + "' and year='" + year + "'";
			rr = st.executeQuery(query);
			if (rr.next()) {
				return rr.getString("route_id");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (rr != null) {
				try {
					rr.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public String routeStopListAllAmountSession(String schid, String selectedRoute, String session, Connection conn) {
		Statement st = null;
		ResultSet rr = null;
		try {
			st = conn.createStatement();
			String query = "SELECT  amount FROM transportstop where groupid='" + selectedRoute + "' and session='"
					+ session + "' and schid='" + schid + "'";

			rr = st.executeQuery(query);
			while (rr.next()) {
				return rr.getString("amount");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (rr != null) {
				try {
					rr.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "0";
	}

	public Route transportRouteNameFromidStopDetail(String schid, String id, String session, Connection conn) {
		Route r = null;
		Statement st = null;
		ResultSet rr = null;
		try {
			st = conn.createStatement();
			String query = "select * from transportstop where groupid='" + id + "' and status='ACTIVE' and session='"
					+ session + "' and schid='" + schid + "' order by idtransportstop desc ";
			rr = st.executeQuery(query);
			if (rr.next()) {

				r = new DataBaseMeathodJson().allTransportRouteListbyId(rr.getString("routeid"), schid, conn);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (rr != null) {
				try {
					rr.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return r;
	}

	public String transportRouteNameFromId(String schid, String id, String session, Connection conn) {
		Statement st = null;
		ResultSet rr = null;
		try {
			st = conn.createStatement();
			String query = "select * from transportstop where groupid='" + id + "' and status='ACTIVE' and session='"
					+ session + "' and schid in ('" + schid + "') order by idtransportstop desc ";
			rr = st.executeQuery(query);

			if (rr.next()) {
				ArrayList<String> list = new DatabaseMethods1().transportRouteDetails(schid, rr.getString("routeid"),
						conn);
				if (list.size() > 0) {
					return list.get(0) + ":-" + list.get(1) + " to " + rr.getString("stopName");
				} else {
					return "";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (rr != null) {
				try {
					rr.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return "";
	}

	public ArrayList<StudentInfo> getstudentsDetails(String username, String pin, Connection conn) {
		ArrayList<StudentInfo> info = new ArrayList<StudentInfo>();
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String session = "";
		
		String savePath = "";
		int i = 1;
		
		ResultSet rr = null;
		try {
			String query = "select *from registration1 where (fathersPhone = ? || mothersPhone = ?) and status = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, username);
			ps.setString(3, "ACTIVE");
//			ps.setString(3, pin);
			rr = ps.executeQuery();
			while (rr.next()) {
				int cashAmount = 0;
				int chequeAmount = 0;
				int tamount = 0;
				int tdiscount = 0;
				boolean count = true;
				StudentInfo list = new StudentInfo();
				list.setSchid(rr.getString("schid"));
				list.setSerialNum(i);
				SchoolInfoList ls = new DatabaseMethods1().fullSchoolInfo(list.getSchid(), conn);
				if (ls.getProjecttype().equalsIgnoreCase("online")) {
					savePath = ls.getDownloadpath();
				}
				list.setSession(rr.getString("session"));
				session = list.getSession();
				list.setFullName(rr.getString("fname"));
				list.setAdmRemark(rr.getString("adm_remark"));
				list.setId(Integer.parseInt(rr.getString("id")));
				list.setAddNumber(rr.getString("addNumber"));
				list.setSrNo(rr.getString("sr_no"));
				list.setStartingDate(Timestamp.valueOf(rr.getString("postdate")));
				list.setStartingDateStr(sdf.format(list.getStartingDate()));
				list.setStatus(rr.getString("status"));
				list.setFname(rr.getString("fname"));
				list.setSchoolName(ls.getSchoolName());
				list.setFathersName(rr.getString("fathersname"));
				list.setMotherName(rr.getString("mname"));
				list.setAdmissionDate(sdf.format(rr.getDate("postdate")));
				String sectionid = rr.getString("classid");
				String classid = classSectionNameFromidSchidSession(list.getSchid(), sectionid, session, conn);

				list.setClassId(classid);
				list.setSectionid(sectionid);
				list.setSectionName(sectionNameByIdSchidSession(list.getSchid(), list.getSectionid(), session, conn));
				list.setClassToName(classNameFromidSchid(list.getSchid(), classid, session, conn));
				list.setClassName(
						classNameFromidSchid(list.getSchid(), classid, session, conn) + "-" + list.getSectionName());

				list.setSendMessageMobileNo(rr.getString("sendMessageMobileNo"));

				list.setTransportRoute(rr.getString("transportroute"));

				if (rr.getString("dob").equals("") || rr.getString("dob") == null) {
					list.setDob(new Date());
					list.setDobString(sdf.format(new Date()));
				} else {
					list.setDob(rr.getDate("dob"));
					list.setDobStr(sdf.format(rr.getDate("dob")));
				}

				list.setGender(rr.getString("gender"));
				list.setNationality(rr.getString("nationality"));
				list.setReligion(rr.getString("religion"));
				list.setCurrentAddress(rr.getString("currentAddress"));
				list.setPermanentAddress(rr.getString("permAddress"));
				list.setPincode(Integer.parseInt(rr.getString("pincode")));
				list.setCountry(rr.getString("country"));
				list.setFathersPhone(Long.parseLong(rr.getString("fathersPhone")));
				list.setMothersPhone(rr.getLong("mothersPhone"));
				list.setStudentPhone(rr.getString("student_phone"));
				list.setCategory(studentCategoryById(rr.getString("categoryid"), conn));
				list.setFathersOccupation(rr.getString("fatheroccupation"));
				list.setResidenceMobile(rr.getString("residencePhone"));
				list.setSession(rr.getString("session"));
				list.setDocumentsSubmitted(rr.getString("documents"));
				list.setDisability(rr.getString("disability"));
				list.setHandicap(rr.getString("handicapped"));
				list.setRollNo(rr.getString("rollno"));
				list.setBpl(rr.getString("bpl"));
				list.setBplCardNo(rr.getString("bplCardNo"));
				list.setConcession(rr.getString("concession"));
				list.setConcessionName(concessionCategoryNameById(list.getSchid(), list.getConcession(), conn));
				list.setConcessionStatus(rr.getString("concession_status"));
				list.setCaste(rr.getString("caste"));
				list.setSingleChild(rr.getString("singleChild"));
				list.setBloodGroup(rr.getString("bloodGroup"));
				list.setAadharNo(rr.getString("aadharNo"));
				list.setFatherAnnIncome(rr.getString("father_income"));
				list.setMotherAnnIncome(rr.getString("mother_income"));

				if (rr.getString("student_image_path") == null || rr.getString("student_image_path").equals("")) {
					list.setStudent_image("");
				} else {
					list.setStudent_image(savePath + rr.getString("student_image_path"));
				}

				if (rr.getString("student_image_path") == null || rr.getString("student_image_path").equals("")) {
					list.setStudentImage("");
				} else {
					list.setStudentImage(savePath + rr.getString("student_image_path"));
				}

				list.setSingleParent(rr.getString("SingleParent"));
				list.setLivingWith(rr.getString("livingWith"));
				list.setEnqUAEId(rr.getString("enq_uae_id"));
				list.setFatherEmail(rr.getString("fatherEmail"));
				list.setMotherEmail(rr.getString("motherEmail"));
				if (!list.getEnqUAEId().equalsIgnoreCase("temp")) {
					list.setActionBy(new DataBaseMeathodJson().recipientByEnqUAEId(list.getEnqUAEId(), conn));
				}
				list.setStudentStatus(rr.getString("student_status"));

				String transportRoute = presentRouteStatusSession(list.getSchid(), rr.getString("addNumber"), session,
						conn);
				if (transportRoute.equals("0") || transportRoute.equals("")) {
					list.setDriverNo("");
					list.setDriverName("");
					list.setVehicleNo("");
					list.setTransfee("0");
					list.setAttendantName("");
				} else {
					list.setTransfee(String.valueOf(Double
							.parseDouble(routeStopListAllAmountSession(list.getSchid(), transportRoute, session, conn))
							- Double.parseDouble(rr.getString("discounttransportFee"))));

					try {
						Route r = transportRouteNameFromidStopDetail(list.getSchid(), transportRoute, session, conn);

						list.setDriverNo(r.getDriverNo());
						list.setDriverName(r.getDriverName());
						list.setShowDriver(r.getShowDriver());

						list.setConductorName(r.getConductorName());
						list.setConductorNo(r.getConductorNo());
						list.setShowConductor(r.getShowConductor());

						list.setAttendantName(r.getAttendantName());
						list.setAttendNo(r.getAttendNo());
						list.setShowAttendant(r.getShowAttendant());
						list.setEmpImageAttendant(r.getEmpImageAttendant());
						list.setEmpImageConductor(r.getEmpImageConductor());
						list.setEmpImageDriver(r.getEmpImageDriver());
						list.setVehicleNo(r.getVehicleNumber());
					} catch (Exception e) {

						list.setDriverNo("");
						list.setDriverName("");
						list.setShowDriver("");

						list.setConductorName("");
						list.setConductorNo("");
						list.setShowConductor("");

						list.setAttendantName("");
						list.setAttendNo("");
						list.setShowAttendant("");
						list.setEmpImageAttendant("");
						list.setEmpImageConductor("");
						list.setEmpImageDriver("");
						list.setVehicleNo("");
					}

				}
				transportRoute = transportRouteNameFromId(list.getSchid(), transportRoute, session, conn);
				list.setTransportRoute(transportRoute);

				list.setRouteStop(rr.getString("transportroute"));
				list.setGname(rr.getString("g1name"));
				list.setRelation(rr.getString("relation"));
				list.setLastSchoolName(rr.getString("lastSchoolName"));
				list.setOccupation(rr.getString("occupation"));
				list.setPhone(rr.getString("mobile"));
				list.setPassedClass(rr.getString("passedClass"));
				list.setMedium(rr.getString("medium1"));
				list.setSname1(rr.getString("sname1"));
				list.setSname2(rr.getString("sname2"));
				list.setSclassid1(rr.getString("sclassid1"));
				list.setSclassid2(rr.getString("sclassid2"));

				list.setAddress(rr.getString("address"));
				list.setFname1(rr.getString("gname2"));
				list.setRelation1(rr.getString("relation2"));
				list.setOccupation1(rr.getString("occupation2"));
				list.setPhone1(rr.getString("phone2"));
				list.setAddress1(rr.getString("address2"));
				list.setpResult(rr.getString("pResult"));
				list.setP_percent(rr.getString("p_percent"));
				list.setpReason(rr.getString("pReason"));
				list.setHeight(rr.getString("height"));
				list.setWeight(rr.getString("weight"));
				list.setEyes(rr.getString("eyes"));
				list.setFatherQualification(rr.getString("fatherQualification"));
				list.setFatherDesignation(rr.getString("fatherdesignation"));
				list.setFatherOrganization(rr.getString("fatherofficecontactno"));
				list.setFatherOfficeAdd(rr.getString("fatherOfficeAdd"));
				list.setFatherAadhaar(rr.getString("FatherAadhaar"));
				list.setFatherSchoolEmp(rr.getString("fatherSchoolEmp"));
				list.setMotherQualification(rr.getString("motherQualification"));
				list.setMotherOccupation(rr.getString("motherOccupation"));
				list.setMotherDesignation(rr.getString("motherdesignation"));
				list.setMotherOrganization(rr.getString("motherofficecontacno"));
				list.setMotherOfficeAdd(rr.getString("motherOfficeAdd"));
				list.setMotherAadhaar(rr.getString("motherAadhaar"));
				list.setMotherSchoolEmp(rr.getString("motherSchoolEmp"));
				list.setTcDate(rr.getDate("tcdt"));
				list.setBoard(rr.getString("board"));
				list.setDiscountfee(rr.getString("discounttransportFee"));
				list.setRollNo(rr.getString("rollno"));
				list.setHouse(rr.getString("housename"));
				list.setHostel(rr.getString("hostel"));
				if (list.getId() > 29308) {
					list.setAdmitClassName(rr.getString("admitClass"));
				} else {

					list.setAdmitclassid(rr.getString("admitClass"));
					list.setAdmitClassName(
							classNameFromidSchid(list.getSchid(), list.getAdmitclassid(), session, conn));
					if (list.getAdmitClassName().equals("")) {
						String admitSectionid = rr.getString("admitClass");
						String admitClassid = classSectionNameFromidSchidSession(list.getSchid(), admitSectionid,
								session, conn);
						list.setAdmitclassid(admitClassid);
						list.setAdmitClassName(
								classNameFromidSchid(list.getSchid(), list.getAdmitclassid(), session, conn));
					}

					if (list.getAdmitClassName().equals("")) {
						list.setAdmitClassName(rr.getString("admitClass"));
					}
				}

				list.setFatherImage(rr.getString("fatherImage"));
				list.setMotherImage(rr.getString("motherImage"));
				list.setLedgerNo(rr.getString("ledger_no"));
				list.setAdmnFileNo(rr.getString("admnFileNo"));
				list.setTenRoll(rr.getString("tenRoll"));
				list.setTenYearOfPass(rr.getString("tenYearOfPass"));
				list.setTenBoard(rr.getString("tenBoard"));
				list.setMinority(rr.getString("minority"));
				list.setVillage(rr.getString("village"));
				
				
				// FEES HISTORY 
				
				String schoolid = list.getSchid();
				String id = list.getAddNumber();
				ArrayList<DailyFeeCollectionBean> dailyfee = new DatabaseMethods1().getstudentWisFeesCollection(schoolid, id,
						DatabaseMethods1.selectedSessionDetails(schoolid, conn), conn);

				if (dailyfee.size() > 0) {
					for (DailyFeeCollectionBean in : dailyfee) {
						if (in.getPaymentmode().equalsIgnoreCase("Cash")) {
							cashAmount += Integer.parseInt(in.getAmount());
						} else {
							chequeAmount += Integer.parseInt(in.getAmount());
							if (in.getPaymentmode().equalsIgnoreCase("Payment Gateway")) {
								in.setBankname("");
							}
						}
						tdiscount += Integer.parseInt(in.getDiscount());
					}
					tamount = cashAmount + chequeAmount;
				}
				
				list.setTotalDis(tdiscount);
				list.setTotalPaid(tamount);
				list.setDailyFees(dailyfee);
				
				if(ls.getDefaultSession().equalsIgnoreCase(list.getSession())) {
					info.add(list);
				}
				i = i + 1;
				
//				if (info.size() == 0) {
//					count = false;
//					FeeKioskStudentInfo l = new FeeKioskStudentInfo();
//					l = fullSchoolInfo(list.getSchid(), conn);
//					stdlist.add(list);
//					l.setStudentList(stdlist);
//					info.add(l);
//				} else {
//					for (FeeKioskStudentInfo x : info) {
//						if (x.getSchid().equalsIgnoreCase(rr.getString("schid"))) {
//							count = false;
//							x.getStudentList().add(list);
//						}
//					}
//
//				}
//
//				if (count == true) {
//					FeeKioskStudentInfo l = new FeeKioskStudentInfo();
//					l = fullSchoolInfo(list.getSchid(), conn);
//					stdlist = new ArrayList<StudentInfo>();
//					stdlist.add(list);
//					l.setStudentList(stdlist);
//					info.add(l);
//					
//				}
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rr != null) {
				try {
					rr.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return info;
	}

	public String getPAymentDetailsWithOrderId(String orderId, String schoolid, Connection conn) {
		String output = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "select sum(amount) as money,RecipietNo,fee_remark from student_fee_table where neftNo = ? and schid = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, orderId);
			ps.setString(2, schoolid);
			rs = ps.executeQuery();
			if(rs.next()) {
				output = rs.getString("money")+"-"+rs.getString("RecipietNo") + "-"+rs.getString("fee_remark") ;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return output;
	}
}
