package FEEKIOSK;

import java.io.Serializable;
import java.util.ArrayList;

import schooldata.StudentInfo;

public class FeeKioskStudentInfo implements Serializable{

	public String schoolName,dltEntityName;
	String schid,aliasName,parentAppType;
	String schoolSession,defaultSession,defaultSessionPopup;
	String qIconShow;
	String feeStart;
	public String add1;
	public String add2;
	String app_link;
	String add3;
	String add4;
	String phoneNo;
	String mobileNo;
	String emailId;
	String website,gpsProvider;
	String regNo;
	String imagePath;
	String rname1;
	String rname2;
	String rname3;
	String permitAttendance;
	String permitLeave;
	String gps;
	String onlineFee;
	String username, key, url, installSession, client_type, boardType, marksheetHeader,certificateHeader, timetable;
	String downloadpath, uploadpath,smsSchoolName,hindiName,appointStartTime,appointEndTime;
	String projecttype;
	String ctype;
	String apikey;
	String adminApikey;
	String type, loginOtp;
	int reciept, sno;
	String permissionType;
	String schoolAppName, schRegNo, gpsApi, gpsUser, gpsPwd;
	String admin_app_Permission;
	String admin_image_permission,acdemic_coordinator_permission, student_app_permission, teacher_permission, lectureLabel, studentApp;
	String discountotp, discountOtpNo,cancalfee,autoCollection,cancelOtpNo,autoCollectionNo;
	String branch_id,adminApp,concessionRequest;
	String srnoType,srnoPrefix,srnoStart; //with getter-setter
	int studentAllowDays, teacherAllowDays, stdAllowQuantity,teacherAllowQuantity,agreementFor;
	String penaltySetting,country,district,state,zone,enqType,tcType,schType;
	String paytm_mid,paytm_marchent_key,galleryRequest;
	String appVersion,teacher_image_permission,academic_image_permission,student_image_permission,attendantComm,rfidComm,timeTableSchedule;
	String homeworkMessage;
	String id;
	String udise,tcHeader;
	String schoolCategory;
	String studentWebLogin,meetingApproval,jwt;
	String pg_type, rzp_mid, rzp_key, rzp_key_secret,exportPermission;
	
	
	ArrayList<StudentInfo> studentList = new ArrayList<StudentInfo>();
	
	
	
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getDltEntityName() {
		return dltEntityName;
	}
	public void setDltEntityName(String dltEntityName) {
		this.dltEntityName = dltEntityName;
	}
	public String getSchid() {
		return schid;
	}
	public void setSchid(String schid) {
		this.schid = schid;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getParentAppType() {
		return parentAppType;
	}
	public void setParentAppType(String parentAppType) {
		this.parentAppType = parentAppType;
	}
	public String getSchoolSession() {
		return schoolSession;
	}
	public void setSchoolSession(String schoolSession) {
		this.schoolSession = schoolSession;
	}
	public String getDefaultSession() {
		return defaultSession;
	}
	public void setDefaultSession(String defaultSession) {
		this.defaultSession = defaultSession;
	}
	public String getDefaultSessionPopup() {
		return defaultSessionPopup;
	}
	public void setDefaultSessionPopup(String defaultSessionPopup) {
		this.defaultSessionPopup = defaultSessionPopup;
	}
	public String getqIconShow() {
		return qIconShow;
	}
	public void setqIconShow(String qIconShow) {
		this.qIconShow = qIconShow;
	}
	public String getFeeStart() {
		return feeStart;
	}
	public void setFeeStart(String feeStart) {
		this.feeStart = feeStart;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getApp_link() {
		return app_link;
	}
	public void setApp_link(String app_link) {
		this.app_link = app_link;
	}
	public String getAdd3() {
		return add3;
	}
	public void setAdd3(String add3) {
		this.add3 = add3;
	}
	public String getAdd4() {
		return add4;
	}
	public void setAdd4(String add4) {
		this.add4 = add4;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getGpsProvider() {
		return gpsProvider;
	}
	public void setGpsProvider(String gpsProvider) {
		this.gpsProvider = gpsProvider;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getRname1() {
		return rname1;
	}
	public void setRname1(String rname1) {
		this.rname1 = rname1;
	}
	public String getRname2() {
		return rname2;
	}
	public void setRname2(String rname2) {
		this.rname2 = rname2;
	}
	public String getRname3() {
		return rname3;
	}
	public void setRname3(String rname3) {
		this.rname3 = rname3;
	}
	public String getPermitAttendance() {
		return permitAttendance;
	}
	public void setPermitAttendance(String permitAttendance) {
		this.permitAttendance = permitAttendance;
	}
	public String getPermitLeave() {
		return permitLeave;
	}
	public void setPermitLeave(String permitLeave) {
		this.permitLeave = permitLeave;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getOnlineFee() {
		return onlineFee;
	}
	public void setOnlineFee(String onlineFee) {
		this.onlineFee = onlineFee;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInstallSession() {
		return installSession;
	}
	public void setInstallSession(String installSession) {
		this.installSession = installSession;
	}
	public String getClient_type() {
		return client_type;
	}
	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getMarksheetHeader() {
		return marksheetHeader;
	}
	public void setMarksheetHeader(String marksheetHeader) {
		this.marksheetHeader = marksheetHeader;
	}
	public String getCertificateHeader() {
		return certificateHeader;
	}
	public void setCertificateHeader(String certificateHeader) {
		this.certificateHeader = certificateHeader;
	}
	public String getTimetable() {
		return timetable;
	}
	public void setTimetable(String timetable) {
		this.timetable = timetable;
	}
	public String getDownloadpath() {
		return downloadpath;
	}
	public void setDownloadpath(String downloadpath) {
		this.downloadpath = downloadpath;
	}
	public String getUploadpath() {
		return uploadpath;
	}
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}
	public String getSmsSchoolName() {
		return smsSchoolName;
	}
	public void setSmsSchoolName(String smsSchoolName) {
		this.smsSchoolName = smsSchoolName;
	}
	public String getHindiName() {
		return hindiName;
	}
	public void setHindiName(String hindiName) {
		this.hindiName = hindiName;
	}
	public String getAppointStartTime() {
		return appointStartTime;
	}
	public void setAppointStartTime(String appointStartTime) {
		this.appointStartTime = appointStartTime;
	}
	public String getAppointEndTime() {
		return appointEndTime;
	}
	public void setAppointEndTime(String appointEndTime) {
		this.appointEndTime = appointEndTime;
	}
	public String getProjecttype() {
		return projecttype;
	}
	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public String getAdminApikey() {
		return adminApikey;
	}
	public void setAdminApikey(String adminApikey) {
		this.adminApikey = adminApikey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLoginOtp() {
		return loginOtp;
	}
	public void setLoginOtp(String loginOtp) {
		this.loginOtp = loginOtp;
	}
	public int getReciept() {
		return reciept;
	}
	public void setReciept(int reciept) {
		this.reciept = reciept;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}
	public String getSchoolAppName() {
		return schoolAppName;
	}
	public void setSchoolAppName(String schoolAppName) {
		this.schoolAppName = schoolAppName;
	}
	public String getSchRegNo() {
		return schRegNo;
	}
	public void setSchRegNo(String schRegNo) {
		this.schRegNo = schRegNo;
	}
	public String getGpsApi() {
		return gpsApi;
	}
	public void setGpsApi(String gpsApi) {
		this.gpsApi = gpsApi;
	}
	public String getGpsUser() {
		return gpsUser;
	}
	public void setGpsUser(String gpsUser) {
		this.gpsUser = gpsUser;
	}
	public String getGpsPwd() {
		return gpsPwd;
	}
	public void setGpsPwd(String gpsPwd) {
		this.gpsPwd = gpsPwd;
	}
	public String getAdmin_app_Permission() {
		return admin_app_Permission;
	}
	public void setAdmin_app_Permission(String admin_app_Permission) {
		this.admin_app_Permission = admin_app_Permission;
	}
	public String getAdmin_image_permission() {
		return admin_image_permission;
	}
	public void setAdmin_image_permission(String admin_image_permission) {
		this.admin_image_permission = admin_image_permission;
	}
	public String getAcdemic_coordinator_permission() {
		return acdemic_coordinator_permission;
	}
	public void setAcdemic_coordinator_permission(String acdemic_coordinator_permission) {
		this.acdemic_coordinator_permission = acdemic_coordinator_permission;
	}
	public String getStudent_app_permission() {
		return student_app_permission;
	}
	public void setStudent_app_permission(String student_app_permission) {
		this.student_app_permission = student_app_permission;
	}
	public String getTeacher_permission() {
		return teacher_permission;
	}
	public void setTeacher_permission(String teacher_permission) {
		this.teacher_permission = teacher_permission;
	}
	public String getLectureLabel() {
		return lectureLabel;
	}
	public void setLectureLabel(String lectureLabel) {
		this.lectureLabel = lectureLabel;
	}
	public String getStudentApp() {
		return studentApp;
	}
	public void setStudentApp(String studentApp) {
		this.studentApp = studentApp;
	}
	public String getDiscountotp() {
		return discountotp;
	}
	public void setDiscountotp(String discountotp) {
		this.discountotp = discountotp;
	}
	public String getDiscountOtpNo() {
		return discountOtpNo;
	}
	public void setDiscountOtpNo(String discountOtpNo) {
		this.discountOtpNo = discountOtpNo;
	}
	public String getCancalfee() {
		return cancalfee;
	}
	public void setCancalfee(String cancalfee) {
		this.cancalfee = cancalfee;
	}
	public String getAutoCollection() {
		return autoCollection;
	}
	public void setAutoCollection(String autoCollection) {
		this.autoCollection = autoCollection;
	}
	public String getCancelOtpNo() {
		return cancelOtpNo;
	}
	public void setCancelOtpNo(String cancelOtpNo) {
		this.cancelOtpNo = cancelOtpNo;
	}
	public String getAutoCollectionNo() {
		return autoCollectionNo;
	}
	public void setAutoCollectionNo(String autoCollectionNo) {
		this.autoCollectionNo = autoCollectionNo;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getAdminApp() {
		return adminApp;
	}
	public void setAdminApp(String adminApp) {
		this.adminApp = adminApp;
	}
	public String getConcessionRequest() {
		return concessionRequest;
	}
	public void setConcessionRequest(String concessionRequest) {
		this.concessionRequest = concessionRequest;
	}
	public String getSrnoType() {
		return srnoType;
	}
	public void setSrnoType(String srnoType) {
		this.srnoType = srnoType;
	}
	public String getSrnoPrefix() {
		return srnoPrefix;
	}
	public void setSrnoPrefix(String srnoPrefix) {
		this.srnoPrefix = srnoPrefix;
	}
	public String getSrnoStart() {
		return srnoStart;
	}
	public void setSrnoStart(String srnoStart) {
		this.srnoStart = srnoStart;
	}
	public int getStudentAllowDays() {
		return studentAllowDays;
	}
	public void setStudentAllowDays(int studentAllowDays) {
		this.studentAllowDays = studentAllowDays;
	}
	public int getTeacherAllowDays() {
		return teacherAllowDays;
	}
	public void setTeacherAllowDays(int teacherAllowDays) {
		this.teacherAllowDays = teacherAllowDays;
	}
	public int getStdAllowQuantity() {
		return stdAllowQuantity;
	}
	public void setStdAllowQuantity(int stdAllowQuantity) {
		this.stdAllowQuantity = stdAllowQuantity;
	}
	public int getTeacherAllowQuantity() {
		return teacherAllowQuantity;
	}
	public void setTeacherAllowQuantity(int teacherAllowQuantity) {
		this.teacherAllowQuantity = teacherAllowQuantity;
	}
	public int getAgreementFor() {
		return agreementFor;
	}
	public void setAgreementFor(int agreementFor) {
		this.agreementFor = agreementFor;
	}
	public String getPenaltySetting() {
		return penaltySetting;
	}
	public void setPenaltySetting(String penaltySetting) {
		this.penaltySetting = penaltySetting;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getEnqType() {
		return enqType;
	}
	public void setEnqType(String enqType) {
		this.enqType = enqType;
	}
	public String getTcType() {
		return tcType;
	}
	public void setTcType(String tcType) {
		this.tcType = tcType;
	}
	public String getSchType() {
		return schType;
	}
	public void setSchType(String schType) {
		this.schType = schType;
	}
	public String getPaytm_mid() {
		return paytm_mid;
	}
	public void setPaytm_mid(String paytm_mid) {
		this.paytm_mid = paytm_mid;
	}
	public String getPaytm_marchent_key() {
		return paytm_marchent_key;
	}
	public void setPaytm_marchent_key(String paytm_marchent_key) {
		this.paytm_marchent_key = paytm_marchent_key;
	}
	public String getGalleryRequest() {
		return galleryRequest;
	}
	public void setGalleryRequest(String galleryRequest) {
		this.galleryRequest = galleryRequest;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getTeacher_image_permission() {
		return teacher_image_permission;
	}
	public void setTeacher_image_permission(String teacher_image_permission) {
		this.teacher_image_permission = teacher_image_permission;
	}
	public String getAcademic_image_permission() {
		return academic_image_permission;
	}
	public void setAcademic_image_permission(String academic_image_permission) {
		this.academic_image_permission = academic_image_permission;
	}
	public String getStudent_image_permission() {
		return student_image_permission;
	}
	public void setStudent_image_permission(String student_image_permission) {
		this.student_image_permission = student_image_permission;
	}
	public String getAttendantComm() {
		return attendantComm;
	}
	public void setAttendantComm(String attendantComm) {
		this.attendantComm = attendantComm;
	}
	public String getRfidComm() {
		return rfidComm;
	}
	public void setRfidComm(String rfidComm) {
		this.rfidComm = rfidComm;
	}
	public String getTimeTableSchedule() {
		return timeTableSchedule;
	}
	public void setTimeTableSchedule(String timeTableSchedule) {
		this.timeTableSchedule = timeTableSchedule;
	}
	public String getHomeworkMessage() {
		return homeworkMessage;
	}
	public void setHomeworkMessage(String homeworkMessage) {
		this.homeworkMessage = homeworkMessage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUdise() {
		return udise;
	}
	public void setUdise(String udise) {
		this.udise = udise;
	}
	public String getTcHeader() {
		return tcHeader;
	}
	public void setTcHeader(String tcHeader) {
		this.tcHeader = tcHeader;
	}
	public String getSchoolCategory() {
		return schoolCategory;
	}
	public void setSchoolCategory(String schoolCategory) {
		this.schoolCategory = schoolCategory;
	}
	public String getStudentWebLogin() {
		return studentWebLogin;
	}
	public void setStudentWebLogin(String studentWebLogin) {
		this.studentWebLogin = studentWebLogin;
	}
	public String getMeetingApproval() {
		return meetingApproval;
	}
	public void setMeetingApproval(String meetingApproval) {
		this.meetingApproval = meetingApproval;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getPg_type() {
		return pg_type;
	}
	public void setPg_type(String pg_type) {
		this.pg_type = pg_type;
	}
	public String getRzp_mid() {
		return rzp_mid;
	}
	public void setRzp_mid(String rzp_mid) {
		this.rzp_mid = rzp_mid;
	}
	public String getRzp_key() {
		return rzp_key;
	}
	public void setRzp_key(String rzp_key) {
		this.rzp_key = rzp_key;
	}
	public String getRzp_key_secret() {
		return rzp_key_secret;
	}
	public void setRzp_key_secret(String rzp_key_secret) {
		this.rzp_key_secret = rzp_key_secret;
	}
	public String getExportPermission() {
		return exportPermission;
	}
	public void setExportPermission(String exportPermission) {
		this.exportPermission = exportPermission;
	}
	public ArrayList<StudentInfo> getStudentList() {
		return studentList;
	}
	public void setStudentList(ArrayList<StudentInfo> studentList) {
		this.studentList = studentList;
	}
	
	
	
}
