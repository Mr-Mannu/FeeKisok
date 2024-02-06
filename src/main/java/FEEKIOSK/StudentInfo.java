package FEEKIOSK;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.file.UploadedFile;

import exam_module.ExamInfo;
import eyfs_module.EyfsInfo;
import reports_module.RfidDataInfo;
import schooldata.AdmitCardInfo;
import schooldata.DailyFeeCollectionBean;
import schooldata.ExtraFieldInfo;
import schooldata.ParentsInfo;
import schooldata.PickDropInfo;
import schooldata.SubjectGroupInfo;
import schooldata.SubjectInfo;
import schooldata.Subjects;
import schooldata.TermInfo;

public class StudentInfo implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	String fname, fathersName, motherName, className, sectionName, transportRoute, gender, religion, currentAddress,
			permanentAddress, sectionid, hostel;
	String schoolfee, admissionDate, admitClassName, admitclassid, residenceMobile, fathersOccupation, residencePhone,
			session, dobString;
	String tutionFeeDueAmount, transportFeeDueAmount, fullName, routeName, routeSource, routeDestination, routeStop,
			driverName, vehicleNo, selectedsection1, selectedclass;
	String documentsSubmitted, transferStatus, grade, reason = "", otherReason = "", month, period1, period2, classId,
			country, driverNo;
	String percentage, ass1, ass2, ass3, ass4, ass5, assType, assName, description, rank, marks = "", addNumber,
			startingDateStr;
	String termFeeDueAmount, tutionFee, activityFeeDueAmount, totalFees, artFeeDueAmount, nationality, category,
			attendance, schoolFeeDueAmount;
	String annualFeeDueAmount, startDate, endDate, admissionFeeDueAmount, examinationFeeDueAmount, sendMessageMobileNo,
			motherImage;
	String present, absent, holidays, studentStatus, status, day, year, studentName, fatherName, totalPresent,
			totalAbsent, totalHoliday;
	String totalPresentUpToLastMonth, totalAbsentUpToLastMonth, totalHolidayUpToLastMonth, previousClass, promotedClass,
			promotedDateStr, minority;
	String bpl, bplCardNo, concession, caste, singleChild, bloodGroup, aadharNo, SingleParent, livingWith, fatherEmail,
			motherEmail, fatherImage, enqUAEId, instructions;
	int sno, pincode, tutionDiscount, admissionDiscount, examDiscount, id, studentRollNo, rankInt, serialNumber;
	Map<String, String> studentPerformnaceMap, studentPerformnaceMap2, attendanceMap, feesMap;
	Date addDate, uploadDate, openingDate, dob, startingDate, date;
	String naStudents, admRemark, categId, extraClass, attachmentStr, ageGroupId, ageGroupName;
	ArrayList<SubjectGroupInfo> subGroupList;
	String fileNo, ncc, promotionStatus;
	String percentageRemark, imageDownloadPath, imageUploadPath;
	String enquiryNo, actionBy;
	String gpsImei, gpsPwd, schname, studentImage, dateToWord;
	String thought = "", strThoughtDate = "", transfee;
	ArrayList<SubjectInfo> overallList, englistList, hindiList, mathList, scienceList, evsList, computerList, artList,
			indianMusicList, westernMusicList, danceList, dramaList, phyEducationList, drawingList, sportsList,
			personalList, workingList;
	ArrayList<SubjectInfo> overallRemarkList, englistRemarkList, hindiRemarkList, scienceRemarkList, mathRemarkList,
			evsRemarkList, computerRemarkList, artRemarkList, indianMusicRemarkList, westernMusicRemarkList,
			danceRemarkList, dramaRemarkList, phyEducationRemarkList;
	String ledgerNo;
	ArrayList<SubjectInfo> subjectList, markslist, iqSubjectList, eqSubList, sqSubList, pmdSubList, lifeScienceSubList,
			csSubList, otherSubjectlist, csoeSubList;
	ArrayList<SubjectInfo> coscholasticMarklist;
	ArrayList<EyfsInfo> eyfsMarkList;
	String termName, examName, comment;
	String app_download, conductorName, conductorNo, attendantName, attendNo, showDriver, showConductor, showAttendant;
	ArrayList<Subjects> subjectsList;
	String empImageDriver, empImageConductor, empImageAttendant;
	CartesianChartModel combinedModel;
	String totalDiscount, rfidStatus;
	ArrayList<SelectItem> docList;
	String maxMarks;
	String geoFence;
	String marksType, regNo_IX, regNo_XI, admnFileNo;
	String studentPercentage, fileName, classStrName;
	boolean readOnly, selected;
	String studentDetails, fatherDetails, motherDetails, guardianDetails;
	String fatherAnnIncome, motherAnnIncome, houseName, modules, dueAmount;
	String subject1, subject2, subject3, subject4, subject5, hisHer, guardian_1_image, guardian_2_image,
			guardian_name_2, fatherOfficeContatNo, motherOfficeContatNo, tansportFeeDiscount, mainClassId;
	String sy[];
	String clasid, subjectName, subjectType, optional, marksGrade, halfYearly, meetingid, classStartTimeStr,
			classEndTimeStr;
	UploadedFile file;
	String documentName, docId;
	boolean fileRender;
	String lactureImage, link = "", title, assignmentSubmit;
	Map<String, String> docMap = new HashMap<String, String>();
	Map<String, String> docMapCal = new HashMap<String, String>();
	String views;
	String reissuDateStr;
	Date reissueDate;
	int totalPaid = 0;
	int totalDis = 0;
	private DonutChartModel donutModel;

	String schoolName = "";
	ArrayList<DailyFeeCollectionBean> dailyFees = new ArrayList<DailyFeeCollectionBean>();
	ArrayList<DailyFeeCollectionBean> showFee = new ArrayList<DailyFeeCollectionBean>();
	int serialNum ;
	boolean first,second;
	
	
	public boolean isSecond() {
		return second;
	}

	public void setSecond(boolean second) {
		this.second = second;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

	public int getTotalDis() {
		return totalDis;
	}

	public void setTotalDis(int totalDis) {
		this.totalDis = totalDis;
	}

	public int getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(int totalPaid) {
		this.totalPaid = totalPaid;
	}

	public ArrayList<DailyFeeCollectionBean> getShowFee() {
		return showFee;
	}

	public void setShowFee(ArrayList<DailyFeeCollectionBean> showFee) {
		this.showFee = showFee;
	}

	public ArrayList<DailyFeeCollectionBean> getDailyFees() {
		return dailyFees;
	}

	public void setDailyFees(ArrayList<DailyFeeCollectionBean> dailyFees) {
		this.dailyFees = dailyFees;
	}

	public Date getReissueDate() {
		return reissueDate;
	}

	public void setReissueDate(Date reissueDate) {
		this.reissueDate = reissueDate;
	}

	public String getReissuDateStr() {
		return reissuDateStr;
	}

	public void setReissuDateStr(String reissuDateStr) {
		this.reissuDateStr = reissuDateStr;
	}

	public String[] getSy() {
		return sy;
	}

	public void setSy(String[] sy) {
		this.sy = sy;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	boolean check, showActive, disable = false, naStudentsBooolean = false, showCoscholasticSubject = false,
			showFile = false;
	ArrayList<ExamInfo> examList;
	double discountFees, previousFees;
	ArrayList<SelectItem> currentRouteList = new ArrayList<>();
	ArrayList<SelectItem> newRouteList = new ArrayList<>();
	String newRoute, currentRoute;
	String addmisssionNumber, rollNo, division, result, studentPhone, overallAttendence, totalAttendence, expenseAmount,
			username;
	String gname, lname, relation, occupation, phone, address, fname1, lname1, relation1, occupation1, phone1, address1,
			fname2, relation2, occupation2, phone2, address2, lastSchoolName, passedClass, medium, sname1, sname2,
			sclassid1, sclassid2;
	String pResult, p_percent, pReason, height, weight, eyes, fatherQualification, motherQualification,
			motherOccupation, fatherDesignation;
	String motherDesignation, fatherOrganization, motherOrganization, fatherOfficeAdd, motherOfficeAdd, fatherIncome,
			motherIncome, FatherAadhaar, motherAadhaar, fatherSchoolEmp, motherSchoolEmp;
	Date tcDate, endingDate;
	String tcDateStr, board, classFromId, classFromName, sectionFrom, classToId, classToName, sectionTo;
	String discountfee, marksInWords, age, messagesend, studentType, student_image, eventtime, disability, handicap;
	String transport, transStatus, resultDeclarationDate, feeamount, paidby, ctno, routeId, testMarks, totalTestNo;
	String totalStudentShowInStopWiseReport, signImage, signText, remark, princiRemark, house, concessionName;
	boolean pricipalImage, admicImage, sendmessage = false, completedStatus;
	int grandTotalBoysAndGirls, boysTotal, girlTotal, transTotal, grandTotal, boyE, girlE, boyT, girlT;
	ArrayList<ExtraFieldInfo> extraFieldList, attendanceList, remarkList, otherValueList;

	ArrayList<SubjectInfo> markListCoscholastic, markListDiscipline, markList, footerList, markListOtherSubject,
			markListAddSub;
	boolean showExtraFieldTable;
	ArrayList<String> coscholasticColumnsList = new ArrayList<>(), otherColumnsList = new ArrayList<>(),
			disciplineColumnsList = new ArrayList<>(), scholasticColumnsList = new ArrayList<>(),
			footerColumnsList = new ArrayList<>(), addColumnsList = new ArrayList<>();
	ArrayList<TermInfo> extraFieldColumnsList = new ArrayList<>(), termList, termListForCoscholastic,
			termListForAdditional;
	double fee, tranportFee, totalFee, paidFee, leftFee;
	ArrayList<SelectItem> classSection, sectionList1;
	Map<String, String> marksMap;
	String examResult, note, stdImageWithoutPath;

	String contactNo, joinDateStr, dobStr, dobInWord, schoolExam;
	String failedOrNot, subjectStudied, qualifiedPromotion, monthOfFeePaid, workingDays, workingDayPresent,
			feeConcession, gamesPlayed;
	String extraActivity, otherRemark, lastClass, leavingDateStr, issueDateStr, tcNo, performance = "", sNo, addClass;
	String schoolCode, boardNumber, tcNumber, name, leavingClassName, SchoolNumber, schoolAddress,
			admitClass, oldSchoolName, oldSchoolLastClassName, leavingSchoolReason;
	String srno, mothersName, exam, behaviour, schoolContact, strtcDate, strExamResultDate, strAdmitDate, strDob,
			struckOffDateStr;
	Date examResultDate, admitDate, struckOffDate;
	int failureTimes, studentPresent, attId, regId;
	Date joinDate, leavingDate, issueDate, applicationDate, doa, dop;

	String doaStr, dopStr, schoolMeeting, studentMeeting, totalStudent, work;
	ArrayList<SelectItem> concessionList = new ArrayList<>();
	String currentConcessionAssign, newConcessionAssign;
	String concessionStatus, oldconcessionCategory;
	String winterRemark, winterDateStr, attendWinter, rfidNo;
	transient UploadedFile studentImageget, attachmentFile;

	PickDropInfo pickdropInfo = new PickDropInfo();
	RfidDataInfo rfidDataInfo = new RfidDataInfo();
	SelectItem rfidInfo = new SelectItem();
	ArrayList<String> addNoList, motherNameList, fatherNameList, nameList;
	int graceCounter;
	String geoFanceTime;
	String eveningGeoFancingTime;
	String latestChatTime;
	String file1 = "";
	String file2 = "", fileName1 = "", fileName2 = "", fileName3 = "", fileName4 = "", fileName5 = "", fileName6 = "",
			file4 = "", file3 = "", file5 = "", file6 = "";
	String srNo, msg;
	String totalQues, rightAnswers, wrongAnswers, noAnswer, postiveMarks, negativeMarks, marksObtained;
	String[] blockModList;
	ArrayList<SelectItem> modList;
	BarChartModel barmodel;
	String tenRoll, tenYearOfPass, tenBoard;
	String village;

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getTenRoll() {
		return tenRoll;
	}

	public void setTenRoll(String tenRoll) {
		this.tenRoll = tenRoll;
	}

	public String getTenYearOfPass() {
		return tenYearOfPass;
	}

	public void setTenYearOfPass(String tenYearOfPass) {
		this.tenYearOfPass = tenYearOfPass;
	}

	public String getTenBoard() {
		return tenBoard;
	}

	public void setTenBoard(String tenBoard) {
		this.tenBoard = tenBoard;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public BarChartModel getBarmodel() {
		return barmodel;
	}

	public void setBarmodel(BarChartModel barmodel) {
		this.barmodel = barmodel;
	}

	public int getTransTotal() {
		return transTotal;
	}

	public void setTransTotal(int transTotal) {
		this.transTotal = transTotal;
	}

	public String getFeeamount() {
		return feeamount;
	}

	public void setFeeamount(String feeamount) {
		this.feeamount = feeamount;
	}

	public String getPaidby() {
		return paidby;
	}

	public void setPaidby(String paidby) {
		this.paidby = paidby;
	}

	public String getCtno() {
		return ctno;
	}

	public void setCtno(String ctno) {
		this.ctno = ctno;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getTestMarks() {
		return testMarks;
	}

	public void setTestMarks(String testMarks) {
		this.testMarks = testMarks;
	}

	public String getMessagesend() {
		return messagesend;
	}

	public void setMessagesend(String messagesend) {
		this.messagesend = messagesend;
	}

	public String getDiscountfee() {
		return discountfee;
	}

	public void setDiscountfee(String discountfee) {
		this.discountfee = discountfee;
	}

	public boolean isSendmessage() {
		return sendmessage;
	}

	public void setSendmessage(boolean sendmessage) {
		this.sendmessage = sendmessage;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getAddmisssionNumber() {
		return addmisssionNumber;
	}

	public void setAddmisssionNumber(String addmisssionNumber) {
		this.addmisssionNumber = addmisssionNumber;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFname1() {
		return fname1;
	}

	public void setFname1(String fname1) {
		this.fname1 = fname1;
	}

	public String getLname1() {
		return lname1;
	}

	public void setLname1(String lname1) {
		this.lname1 = lname1;
	}

	public String getRelation1() {
		return relation1;
	}

	public void setRelation1(String relation1) {
		this.relation1 = relation1;
	}

	public String getOccupation1() {
		return occupation1;
	}

	public void setOccupation1(String occupation1) {
		this.occupation1 = occupation1;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getLastSchoolName() {
		return lastSchoolName;
	}

	public void setLastSchoolName(String lastSchoolName) {
		this.lastSchoolName = lastSchoolName;
	}

	public String getPassedClass() {
		return passedClass;
	}

	public void setPassedClass(String passedClass) {
		this.passedClass = passedClass;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getSname1() {
		return sname1;
	}

	public void setSname1(String sname1) {
		this.sname1 = sname1;
	}

	public String getSname2() {
		return sname2;
	}

	public void setSname2(String sname2) {
		this.sname2 = sname2;
	}

	public String getSclassid1() {
		return sclassid1;
	}

	public void setSclassid1(String sclassid1) {
		this.sclassid1 = sclassid1;
	}

	public String getSclassid2() {
		return sclassid2;
	}

	public void setSclassid2(String sclassid2) {
		this.sclassid2 = sclassid2;
	}

	public String getpResult() {
		return pResult;
	}

	public void setpResult(String pResult) {
		this.pResult = pResult;
	}

	public String getP_percent() {
		return p_percent;
	}

	public void setP_percent(String p_percent) {
		this.p_percent = p_percent;
	}

	public String getpReason() {
		return pReason;
	}

	public void setpReason(String pReason) {
		this.pReason = pReason;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getEyes() {
		return eyes;
	}

	public void setEyes(String eyes) {
		this.eyes = eyes;
	}

	public String getFatherQualification() {
		return fatherQualification;
	}

	public void setFatherQualification(String fatherQualification) {
		this.fatherQualification = fatherQualification;
	}

	public String getMotherQualification() {
		return motherQualification;
	}

	public void setMotherQualification(String motherQualification) {
		this.motherQualification = motherQualification;
	}

	public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	public String getFatherDesignation() {
		return fatherDesignation;
	}

	public void setFatherDesignation(String fatherDesignation) {
		this.fatherDesignation = fatherDesignation;
	}

	public String getMotherDesignation() {
		return motherDesignation;
	}

	public void setMotherDesignation(String motherDesignation) {
		this.motherDesignation = motherDesignation;
	}

	public String getFatherOrganization() {
		return fatherOrganization;
	}

	public void setFatherOrganization(String fatherOrganization) {
		this.fatherOrganization = fatherOrganization;
	}

	public String getMotherOrganization() {
		return motherOrganization;
	}

	public void setMotherOrganization(String motherOrganization) {
		this.motherOrganization = motherOrganization;
	}

	public String getFatherOfficeAdd() {
		return fatherOfficeAdd;
	}

	public void setFatherOfficeAdd(String fatherOfficeAdd) {
		this.fatherOfficeAdd = fatherOfficeAdd;
	}

	public String getMotherOfficeAdd() {
		return motherOfficeAdd;
	}

	public void setMotherOfficeAdd(String motherOfficeAdd) {
		this.motherOfficeAdd = motherOfficeAdd;
	}

	public String getFatherIncome() {
		return fatherIncome;
	}

	public void setFatherIncome(String fatherIncome) {
		this.fatherIncome = fatherIncome;
	}

	public String getMotherIncome() {
		return motherIncome;
	}

	public void setMotherIncome(String motherIncome) {
		this.motherIncome = motherIncome;
	}

	public String getFatherAadhaar() {
		return FatherAadhaar;
	}

	public void setFatherAadhaar(String fatherAadhaar) {
		FatherAadhaar = fatherAadhaar;
	}

	public String getMotherAadhaar() {
		return motherAadhaar;
	}

	public void setMotherAadhaar(String motherAadhaar) {
		this.motherAadhaar = motherAadhaar;
	}

	public String getFatherSchoolEmp() {
		return fatherSchoolEmp;
	}

	public void setFatherSchoolEmp(String fatherSchoolEmp) {
		this.fatherSchoolEmp = fatherSchoolEmp;
	}

	public String getMotherSchoolEmp() {
		return motherSchoolEmp;
	}

	public void setMotherSchoolEmp(String motherSchoolEmp) {
		this.motherSchoolEmp = motherSchoolEmp;
	}

	public Date getTcDate() {
		return tcDate;
	}

	public void setTcDate(Date tcDate) {
		this.tcDate = tcDate;
	}

	public String getTcDateStr() {
		return tcDateStr;
	}

	public void setTcDateStr(String tcDateStr) {
		this.tcDateStr = tcDateStr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSchid() {
		return schid;
	}

	public void setSchid(String schid) {
		this.schid = schid;
	}

	long fathersPhone, mothersPhone;
	ParentsInfo parentInfo;
	String schid;

	public String getEventtime() {
		return eventtime;
	}

	public void setEventtime(String eventtime) {
		this.eventtime = eventtime;
	}

	ArrayList<AdmitCardInfo> admitCardInfo;

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getStartingDateStr() {
		return startingDateStr;
	}

	public void setStartingDateStr(String startingDateStr) {
		this.startingDateStr = startingDateStr;
	}

	public String getAss1() {
		return ass1;
	}

	public void setAss1(String ass1) {
		this.ass1 = ass1;
	}

	public String getAss2() {
		return ass2;
	}

	public void setAss2(String ass2) {
		this.ass2 = ass2;
	}

	public String getAss3() {
		return ass3;
	}

	public void setAss3(String ass3) {
		this.ass3 = ass3;
	}

	public String getAss4() {
		return ass4;
	}

	public void setAss4(String ass4) {
		this.ass4 = ass4;
	}

	public String getAss5() {
		return ass5;
	}

	public void setAss5(String ass5) {
		this.ass5 = ass5;
	}

	public String getAssType() {
		return assType;
	}

	public void setAssType(String assType) {
		this.assType = assType;
	}

	public String getAssName() {
		return assName;
	}

	public void setAssName(String assName) {
		this.assName = assName;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getPreviousClass() {
		return previousClass;
	}

	public void setPreviousClass(String previousClass) {
		this.previousClass = previousClass;
	}

	public String getPromotedClass() {
		return promotedClass;
	}

	public void setPromotedClass(String promotedClass) {
		this.promotedClass = promotedClass;
	}

	public String getPromotedDateStr() {
		return promotedDateStr;
	}

	public void setPromotedDateStr(String promotedDateStr) {
		this.promotedDateStr = promotedDateStr;
	}

	public Map<String, String> getFeesMap() {
		return feesMap;
	}

	public void setFeesMap(Map<String, String> feesMap) {
		this.feesMap = feesMap;
	}

	public String getTermFeeDueAmount() {
		return termFeeDueAmount;
	}

	public void setTermFeeDueAmount(String termFeeDueAmount) {
		this.termFeeDueAmount = termFeeDueAmount;
	}

	public String getActivityFeeDueAmount() {
		return activityFeeDueAmount;
	}

	public void setActivityFeeDueAmount(String activityFeeDueAmount) {
		this.activityFeeDueAmount = activityFeeDueAmount;
	}

	public String getArtFeeDueAmount() {
		return artFeeDueAmount;
	}

	public void setArtFeeDueAmount(String artFeeDueAmount) {
		this.artFeeDueAmount = artFeeDueAmount;
	}

	public String getDocumentsSubmitted() {
		return documentsSubmitted;
	}

	public void setDocumentsSubmitted(String documentsSubmitted) {
		this.documentsSubmitted = documentsSubmitted;
	}

	public boolean isShowActive() {
		return showActive;
	}

	public void setShowActive(boolean showActive) {
		this.showActive = showActive;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public String getAbsent() {
		return absent;
	}

	public void setAbsent(String absent) {
		this.absent = absent;
	}

	public String getHolidays() {
		return holidays;
	}

	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getTotalPresent() {
		return totalPresent;
	}

	public void setTotalPresent(String totalPresent) {
		this.totalPresent = totalPresent;
	}

	public String getTotalAbsent() {
		return totalAbsent;
	}

	public void setTotalAbsent(String totalAbsent) {
		this.totalAbsent = totalAbsent;
	}

	public String getTotalHoliday() {
		return totalHoliday;
	}

	public void setTotalHoliday(String totalHoliday) {
		this.totalHoliday = totalHoliday;
	}

	public String getTotalPresentUpToLastMonth() {
		return totalPresentUpToLastMonth;
	}

	public void setTotalPresentUpToLastMonth(String totalPresentUpToLastMonth) {
		this.totalPresentUpToLastMonth = totalPresentUpToLastMonth;
	}

	public String getTotalAbsentUpToLastMonth() {
		return totalAbsentUpToLastMonth;
	}

	public void setTotalAbsentUpToLastMonth(String totalAbsentUpToLastMonth) {
		this.totalAbsentUpToLastMonth = totalAbsentUpToLastMonth;
	}

	public String getTotalHolidayUpToLastMonth() {
		return totalHolidayUpToLastMonth;
	}

	public void setTotalHolidayUpToLastMonth(String totalHolidayUpToLastMonth) {
		this.totalHolidayUpToLastMonth = totalHolidayUpToLastMonth;
	}

	public String getDobString() {
		return dobString;
	}

	public void setDobString(String dobString) {
		this.dobString = dobString;
	}

	public String getAdmitClassName() {
		return admitClassName;
	}

	public void setAdmitClassName(String admitClassName) {
		this.admitClassName = admitClassName;
	}

	public String getAdmitclassid() {
		return admitclassid;
	}

	public void setAdmitclassid(String admitclassid) {
		this.admitclassid = admitclassid;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getResidencePhone() {
		return residencePhone;
	}

	public void setResidencePhone(String residencePhone) {
		this.residencePhone = residencePhone;
	}

	public String getResidenceMobile() {
		return residenceMobile;
	}

	public void setResidenceMobile(String residenceMobile) {
		this.residenceMobile = residenceMobile;
	}

	public String getFathersOccupation() {
		return fathersOccupation;
	}

	public void setFathersOccupation(String fathersOccupation) {
		this.fathersOccupation = fathersOccupation;
	}

	public String getSchoolfee() {
		return schoolfee;
	}

	public void setSchoolfee(String schoolfee) {
		this.schoolfee = schoolfee;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public int getTutionDiscount() {
		return tutionDiscount;
	}

	public void setTutionDiscount(int tutionDiscount) {
		this.tutionDiscount = tutionDiscount;
	}

	public int getAdmissionDiscount() {
		return admissionDiscount;
	}

	public void setAdmissionDiscount(int admissionDiscount) {
		this.admissionDiscount = admissionDiscount;
	}

	public int getExamDiscount() {
		return examDiscount;
	}

	public void setExamDiscount(int examDiscount) {
		this.examDiscount = examDiscount;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getExaminationFeeDueAmount() {
		return examinationFeeDueAmount;
	}

	public void setExaminationFeeDueAmount(String examinationFeeDueAmount) {
		this.examinationFeeDueAmount = examinationFeeDueAmount;
	}

	public String getSchoolFeeDueAmount() {
		return schoolFeeDueAmount;
	}

	public void setSchoolFeeDueAmount(String schoolFeeDueAmount) {
		this.schoolFeeDueAmount = schoolFeeDueAmount;
	}

	public String getAnnualFeeDueAmount() {
		return annualFeeDueAmount;
	}

	public void setAnnualFeeDueAmount(String annualFeeDueAmount) {
		this.annualFeeDueAmount = annualFeeDueAmount;
	}

	public String getAdmissionFeeDueAmount() {
		return admissionFeeDueAmount;
	}

	public void setAdmissionFeeDueAmount(String admissionFeeDueAmount) {
		this.admissionFeeDueAmount = admissionFeeDueAmount;
	}

	public String getTutionFeeDueAmount() {
		return tutionFeeDueAmount;
	}

	public void setTutionFeeDueAmount(String tutionFeeDueAmount) {
		this.tutionFeeDueAmount = tutionFeeDueAmount;
	}

	public String getTransportFeeDueAmount() {
		return transportFeeDueAmount;
	}

	public void setTransportFeeDueAmount(String transportFeeDueAmount) {
		this.transportFeeDueAmount = transportFeeDueAmount;
	}

	public Map<String, String> getStudentPerformnaceMap2() {
		return studentPerformnaceMap2;
	}

	public void setStudentPerformnaceMap2(Map<String, String> studentPerformnaceMap2) {
		this.studentPerformnaceMap2 = studentPerformnaceMap2;
	}

	public Map<String, String> getStudentPerformnaceMap() {
		return studentPerformnaceMap;
	}

	public void setStudentPerformnaceMap(Map<String, String> studentPerformnaceMap) {
		this.studentPerformnaceMap = studentPerformnaceMap;
	}

	public Map<String, String> getAttendanceMap() {

		return attendanceMap;
	}

	public void setAttendanceMap(Map<String, String> attendanceMap) {
		this.attendanceMap = attendanceMap;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteSource() {
		return routeSource;
	}

	public void setRouteSource(String routeSource) {
		this.routeSource = routeSource;
	}

	public String getRouteDestination() {
		return routeDestination;
	}

	public void setRouteDestination(String routeDestination) {
		this.routeDestination = routeDestination;
	}

	public String getRouteStop() {
		return routeStop;
	}

	public void setRouteStop(String routeStop) {
		this.routeStop = routeStop;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverNo() {
		return driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getTransportRoute() {
		return transportRoute;
	}

	public void setTransportRoute(String transportRoute) {
		this.transportRoute = transportRoute;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSectionid() {
		return sectionid;
	}

	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}

	public long getFathersPhone() {
		return fathersPhone;
	}

	public void setFathersPhone(long fathersPhone) {
		this.fathersPhone = fathersPhone;
	}

	public long getMothersPhone() {
		return mothersPhone;
	}

	public void setMothersPhone(long mothersPhone) {
		this.mothersPhone = mothersPhone;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPeriod1() {
		return period1;
	}

	public void setPeriod1(String period1) {
		this.period1 = period1;
	}

	public String getPeriod2() {
		return period2;
	}

	public void setPeriod2(String period2) {
		this.period2 = period2;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int count) {
		this.sno = count;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBpl() {
		return bpl;
	}

	public void setBpl(String bpl) {
		this.bpl = bpl;
	}

	public String getBplCardNo() {
		return bplCardNo;
	}

	public void setBplCardNo(String bplCardNo) {
		this.bplCardNo = bplCardNo;
	}

	public String getConcession() {
		return concession;
	}

	public void setConcession(String concession) {
		this.concession = concession;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getSingleChild() {
		return singleChild;
	}

	public void setSingleChild(String singleChild) {
		this.singleChild = singleChild;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getSingleParent() {
		return SingleParent;
	}

	public void setSingleParent(String singleParent) {
		SingleParent = singleParent;
	}

	public String getLivingWith() {
		return livingWith;
	}

	public void setLivingWith(String livingWith) {
		this.livingWith = livingWith;
	}

	public String getFatherEmail() {
		return fatherEmail;
	}

	public void setFatherEmail(String fatherEmail) {
		this.fatherEmail = fatherEmail;
	}

	public String getMotherEmail() {
		return motherEmail;
	}

	public void setMotherEmail(String motherEmail) {
		this.motherEmail = motherEmail;
	}

	public String getFatherImage() {
		return fatherImage;
	}

	public void setFatherImage(String fatherImage) {
		this.fatherImage = fatherImage;
	}

	public String getMotherImage() {
		return motherImage;
	}

	public void setMotherImage(String motherImage) {
		this.motherImage = motherImage;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	public String getAddNumber() {
		return addNumber;
	}

	public void setAddNumber(String addNumber) {
		this.addNumber = addNumber;
	}

	public String getSendMessageMobileNo() {
		return sendMessageMobileNo;
	}

	public void setSendMessageMobileNo(String sendMessageMobileNo) {
		this.sendMessageMobileNo = sendMessageMobileNo;
	}

	public ParentsInfo getParentInfo() {
		return parentInfo;
	}

	public void setParentInfo(ParentsInfo parentInfo) {
		this.parentInfo = parentInfo;
	}

	public ArrayList<AdmitCardInfo> getAdmitCardInfo() {
		return admitCardInfo;
	}

	public void setAdmitCardInfo(ArrayList<AdmitCardInfo> admitCardInfo) {
		this.admitCardInfo = admitCardInfo;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public boolean isCompletedStatus() {
		return completedStatus;
	}

	public void setCompletedStatus(boolean completedStatus) {
		this.completedStatus = completedStatus;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getTotalStudentShowInStopWiseReport() {
		return totalStudentShowInStopWiseReport;
	}

	public void setTotalStudentShowInStopWiseReport(String totalStudentShowInStopWiseReport) {
		this.totalStudentShowInStopWiseReport = totalStudentShowInStopWiseReport;
	}

	/*
	 * @Override public int compareTo(Object o) {
	 * 
	 * String compareQuantity = ((StudentInfo) o).getFname();
	 * 
	 * //ascending order return compareQuantity.compareToIgnoreCase(this.fname);
	 * 
	 * 
	 * 
	 * }
	 */
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public boolean isPricipalImage() {
		return pricipalImage;
	}

	public void setPricipalImage(boolean pricipalImage) {
		this.pricipalImage = pricipalImage;
	}

	public boolean isAdmicImage() {
		return admicImage;
	}

	public void setAdmicImage(boolean admicImage) {
		this.admicImage = admicImage;
	}

	public ArrayList<TermInfo> getTermList() {
		return termList;
	}

	public void setTermList(ArrayList<TermInfo> termList) {
		this.termList = termList;
	}

	public ArrayList<ExtraFieldInfo> getExtraFieldList() {
		return extraFieldList;
	}

	public void setExtraFieldList(ArrayList<ExtraFieldInfo> extraFieldList) {
		this.extraFieldList = extraFieldList;
	}

	public ArrayList<SubjectInfo> getMarkList() {
		return markList;
	}

	public void setMarkList(ArrayList<SubjectInfo> markList) {
		this.markList = markList;
	}

	public ArrayList<String> getScholasticColumnsList() {
		return scholasticColumnsList;
	}

	public void setScholasticColumnsList(ArrayList<String> scholasticColumnsList) {
		this.scholasticColumnsList = scholasticColumnsList;
	}

	public ArrayList<SubjectInfo> getMarkListCoscholastic() {
		return markListCoscholastic;
	}

	public void setMarkListCoscholastic(ArrayList<SubjectInfo> markListCoscholastic) {
		this.markListCoscholastic = markListCoscholastic;
	}

	public ArrayList<SubjectInfo> getMarkListDiscipline() {
		return markListDiscipline;
	}

	public void setMarkListDiscipline(ArrayList<SubjectInfo> markListDiscipline) {
		this.markListDiscipline = markListDiscipline;
	}

	public boolean isShowExtraFieldTable() {
		return showExtraFieldTable;
	}

	public void setShowExtraFieldTable(boolean showExtraFieldTable) {
		this.showExtraFieldTable = showExtraFieldTable;
	}

	public ArrayList<String> getCoscholasticColumnsList() {
		return coscholasticColumnsList;
	}

	public void setCoscholasticColumnsList(ArrayList<String> coscholasticColumnsList) {
		this.coscholasticColumnsList = coscholasticColumnsList;
	}

	public ArrayList<String> getDisciplineColumnsList() {
		return disciplineColumnsList;
	}

	public void setDisciplineColumnsList(ArrayList<String> disciplineColumnsList) {
		this.disciplineColumnsList = disciplineColumnsList;
	}

	public ArrayList<TermInfo> getExtraFieldColumnsList() {
		return extraFieldColumnsList;
	}

	public void setExtraFieldColumnsList(ArrayList<TermInfo> extraFieldColumnsList) {
		this.extraFieldColumnsList = extraFieldColumnsList;
	}

	public String getSignImage() {
		return signImage;
	}

	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}

	public String getSignText() {
		return signText;
	}

	public void setSignText(String signText) {
		this.signText = signText;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStudent_image() {
		return student_image;
	}

	public void setStudent_image(String student_image) {
		this.student_image = student_image;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getTranportFee() {
		return tranportFee;
	}

	public void setTranportFee(double tranportFee) {
		this.tranportFee = tranportFee;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public double getPaidFee() {
		return paidFee;
	}

	public void setPaidFee(double paidFee) {
		this.paidFee = paidFee;
	}

	public double getLeftFee() {
		return leftFee;
	}

	public void setLeftFee(double leftFee) {
		this.leftFee = leftFee;
	}

	public int getGrandTotalBoysAndGirls() {
		return grandTotalBoysAndGirls;
	}

	public void setGrandTotalBoysAndGirls(int grandTotalBoysAndGirls) {
		this.grandTotalBoysAndGirls = grandTotalBoysAndGirls;
	}

	public int getBoysTotal() {
		return boysTotal;
	}

	public void setBoysTotal(int boysTotal) {
		this.boysTotal = boysTotal;
	}

	public int getGirlTotal() {
		return girlTotal;
	}

	public void setGirlTotal(int girlTotal) {
		this.girlTotal = girlTotal;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

	public int getBoyE() {
		return boyE;
	}

	public void setBoyE(int boyE) {
		this.boyE = boyE;
	}

	public int getGirlE() {
		return girlE;
	}

	public void setGirlE(int girlE) {
		this.girlE = girlE;
	}

	public int getBoyT() {
		return boyT;
	}

	public void setBoyT(int boyT) {
		this.boyT = boyT;
	}

	public int getGirlT() {
		return girlT;
	}

	public void setGirlT(int girlT) {
		this.girlT = girlT;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getHandicap() {
		return handicap;
	}

	public void setHandicap(String handicap) {
		this.handicap = handicap;
	}

	public String getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(String totalFees) {
		this.totalFees = totalFees;
	}

	public String getTutionFee() {
		return tutionFee;
	}

	public void setTutionFee(String tutionFee) {
		this.tutionFee = tutionFee;
	}

	public String getResultDeclarationDate() {
		return resultDeclarationDate;
	}

	public void setResultDeclarationDate(String resultDeclarationDate) {
		this.resultDeclarationDate = resultDeclarationDate;
	}

	public ArrayList<ExamInfo> getExamList() {
		return examList;
	}

	public void setExamList(ArrayList<ExamInfo> examList) {
		this.examList = examList;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getOverallAttendence() {
		return overallAttendence;
	}

	public void setOverallAttendence(String overallAttendence) {
		this.overallAttendence = overallAttendence;
	}

	public String getTotalAttendence() {
		return totalAttendence;
	}

	public void setTotalAttendence(String totalAttendence) {
		this.totalAttendence = totalAttendence;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFname2() {
		return fname2;
	}

	public void setFname2(String fname2) {
		this.fname2 = fname2;
	}

	public String getRelation2() {
		return relation2;
	}

	public void setRelation2(String relation2) {
		this.relation2 = relation2;
	}

	public String getOccupation2() {
		return occupation2;
	}

	public void setOccupation2(String occupation2) {
		this.occupation2 = occupation2;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getHostel() {
		return hostel;
	}

	public void setHostel(String hostel) {
		this.hostel = hostel;
	}

	public String getMarksInWords() {
		return marksInWords;
	}

	public void setMarksInWords(String marksInWords) {
		this.marksInWords = marksInWords;
	}

	public String getSelectedsection1() {
		return selectedsection1;
	}

	public void setSelectedsection1(String selectedsection1) {
		this.selectedsection1 = selectedsection1;
	}

	public String getSelectedclass() {
		return selectedclass;
	}

	public void setSelectedclass(String selectedclass) {
		this.selectedclass = selectedclass;
	}

	public ArrayList<SelectItem> getClassSection() {
		return classSection;
	}

	public void setClassSection(ArrayList<SelectItem> classSection) {
		this.classSection = classSection;
	}

	public ArrayList<SelectItem> getSectionList1() {
		return sectionList1;
	}

	public void setSectionList1(ArrayList<SelectItem> sectionList1) {
		this.sectionList1 = sectionList1;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String getClassFromId() {
		return classFromId;
	}

	public void setClassFromId(String classFromId) {
		this.classFromId = classFromId;
	}

	public String getClassFromName() {
		return classFromName;
	}

	public void setClassFromName(String classFromName) {
		this.classFromName = classFromName;
	}

	public String getSectionFrom() {
		return sectionFrom;
	}

	public void setSectionFrom(String sectionFrom) {
		this.sectionFrom = sectionFrom;
	}

	public String getClassToId() {
		return classToId;
	}

	public void setClassToId(String classToId) {
		this.classToId = classToId;
	}

	public String getClassToName() {
		return classToName;
	}

	public void setClassToName(String classToName) {
		this.classToName = classToName;
	}

	public String getSectionTo() {
		return sectionTo;
	}

	public void setSectionTo(String sectionTo) {
		this.sectionTo = sectionTo;
	}

	public String getTotalTestNo() {
		return totalTestNo;
	}

	public void setTotalTestNo(String totalTestNo) {
		this.totalTestNo = totalTestNo;
	}

	public ArrayList<TermInfo> getTermListForCoscholastic() {
		return termListForCoscholastic;
	}

	public void setTermListForCoscholastic(ArrayList<TermInfo> termListForCoscholastic) {
		this.termListForCoscholastic = termListForCoscholastic;
	}

	public Map<String, String> getMarksMap() {
		return marksMap;
	}

	public void setMarksMap(Map<String, String> marksMap) {
		this.marksMap = marksMap;
	}

	public String getExamResult() {
		return examResult;
	}

	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getConcessionName() {
		return concessionName;
	}

	public void setConcessionName(String concessionName) {
		this.concessionName = concessionName;
	}

	public String getNewRoute() {
		return newRoute;
	}

	public void setNewRoute(String newRoute) {
		this.newRoute = newRoute;
	}

	public String getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(String currentRoute) {
		this.currentRoute = currentRoute;
	}

	public double getDiscountFees() {
		return discountFees;
	}

	public void setDiscountFees(double discountFees) {
		this.discountFees = discountFees;
	}

	public double getPreviousFees() {
		return previousFees;
	}

	public void setPreviousFees(double previousFees) {
		this.previousFees = previousFees;
	}

	public ArrayList<SelectItem> getCurrentRouteList() {
		return currentRouteList;
	}

	public void setCurrentRouteList(ArrayList<SelectItem> currentRouteList) {
		this.currentRouteList = currentRouteList;
	}

	public ArrayList<SelectItem> getNewRouteList() {
		return newRouteList;
	}

	public void setNewRouteList(ArrayList<SelectItem> newRouteList) {
		this.newRouteList = newRouteList;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getJoinDateStr() {
		return joinDateStr;
	}

	public void setJoinDateStr(String joinDateStr) {
		this.joinDateStr = joinDateStr;
	}

	public String getDobStr() {
		return dobStr;
	}

	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
	}

	public String getDobInWord() {
		return dobInWord;
	}

	public void setDobInWord(String dobInWord) {
		this.dobInWord = dobInWord;
	}

	public String getSchoolExam() {
		return schoolExam;
	}

	public void setSchoolExam(String schoolExam) {
		this.schoolExam = schoolExam;
	}

	public String getFailedOrNot() {
		return failedOrNot;
	}

	public void setFailedOrNot(String failedOrNot) {
		this.failedOrNot = failedOrNot;
	}

	public String getSubjectStudied() {
		return subjectStudied;
	}

	public void setSubjectStudied(String subjectStudied) {
		this.subjectStudied = subjectStudied;
	}

	public String getQualifiedPromotion() {
		return qualifiedPromotion;
	}

	public void setQualifiedPromotion(String qualifiedPromotion) {
		this.qualifiedPromotion = qualifiedPromotion;
	}

	public String getMonthOfFeePaid() {
		return monthOfFeePaid;
	}

	public void setMonthOfFeePaid(String monthOfFeePaid) {
		this.monthOfFeePaid = monthOfFeePaid;
	}

	public String getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

	public String getWorkingDayPresent() {
		return workingDayPresent;
	}

	public void setWorkingDayPresent(String workingDayPresent) {
		this.workingDayPresent = workingDayPresent;
	}

	public String getFeeConcession() {
		return feeConcession;
	}

	public void setFeeConcession(String feeConcession) {
		this.feeConcession = feeConcession;
	}

	public String getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(String gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public String getExtraActivity() {
		return extraActivity;
	}

	public void setExtraActivity(String extraActivity) {
		this.extraActivity = extraActivity;
	}

	public String getOtherRemark() {
		return otherRemark;
	}

	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
	}

	public String getLastClass() {
		return lastClass;
	}

	public void setLastClass(String lastClass) {
		this.lastClass = lastClass;
	}

	public String getLeavingDateStr() {
		return leavingDateStr;
	}

	public void setLeavingDateStr(String leavingDateStr) {
		this.leavingDateStr = leavingDateStr;
	}

	public String getIssueDateStr() {
		return issueDateStr;
	}

	public void setIssueDateStr(String issueDateStr) {
		this.issueDateStr = issueDateStr;
	}

	public String getTcNo() {
		return tcNo;
	}

	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getAddClass() {
		return addClass;
	}

	public void setAddClass(String addClass) {
		this.addClass = addClass;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(String boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getTcNumber() {
		return tcNumber;
	}

	public void setTcNumber(String tcNumber) {
		this.tcNumber = tcNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeavingClassName() {
		return leavingClassName;
	}

	public void setLeavingClassName(String leavingClassName) {
		this.leavingClassName = leavingClassName;
	}

	

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolNumber() {
		return SchoolNumber;
	}

	public void setSchoolNumber(String schoolNumber) {
		SchoolNumber = schoolNumber;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getAdmitClass() {
		return admitClass;
	}

	public void setAdmitClass(String admitClass) {
		this.admitClass = admitClass;
	}

	public String getOldSchoolName() {
		return oldSchoolName;
	}

	public void setOldSchoolName(String oldSchoolName) {
		this.oldSchoolName = oldSchoolName;
	}

	public String getOldSchoolLastClassName() {
		return oldSchoolLastClassName;
	}

	public void setOldSchoolLastClassName(String oldSchoolLastClassName) {
		this.oldSchoolLastClassName = oldSchoolLastClassName;
	}

	public String getLeavingSchoolReason() {
		return leavingSchoolReason;
	}

	public void setLeavingSchoolReason(String leavingSchoolReason) {
		this.leavingSchoolReason = leavingSchoolReason;
	}

	public String getSrno() {
		return srno;
	}

	public void setSrno(String srno) {
		this.srno = srno;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public String getSchoolContact() {
		return schoolContact;
	}

	public void setSchoolContact(String schoolContact) {
		this.schoolContact = schoolContact;
	}

	public String getStrtcDate() {
		return strtcDate;
	}

	public void setStrtcDate(String strtcDate) {
		this.strtcDate = strtcDate;
	}

	public String getStrExamResultDate() {
		return strExamResultDate;
	}

	public void setStrExamResultDate(String strExamResultDate) {
		this.strExamResultDate = strExamResultDate;
	}

	public String getStrAdmitDate() {
		return strAdmitDate;
	}

	public void setStrAdmitDate(String strAdmitDate) {
		this.strAdmitDate = strAdmitDate;
	}

	public String getStrDob() {
		return strDob;
	}

	public void setStrDob(String strDob) {
		this.strDob = strDob;
	}

	public String getStruckOffDateStr() {
		return struckOffDateStr;
	}

	public void setStruckOffDateStr(String struckOffDateStr) {
		this.struckOffDateStr = struckOffDateStr;
	}

	public Date getExamResultDate() {
		return examResultDate;
	}

	public void setExamResultDate(Date examResultDate) {
		this.examResultDate = examResultDate;
	}

	public Date getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	public Date getStruckOffDate() {
		return struckOffDate;
	}

	public void setStruckOffDate(Date struckOffDate) {
		this.struckOffDate = struckOffDate;
	}

	public int getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(int failureTimes) {
		this.failureTimes = failureTimes;
	}

	public int getStudentPresent() {
		return studentPresent;
	}

	public void setStudentPresent(int studentPresent) {
		this.studentPresent = studentPresent;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getDoa() {
		return doa;
	}

	public void setDoa(Date doa) {
		this.doa = doa;
	}

	public Date getDop() {
		return dop;
	}

	public void setDop(Date dop) {
		this.dop = dop;
	}

	public String getDoaStr() {
		return doaStr;
	}

	public void setDoaStr(String doaStr) {
		this.doaStr = doaStr;
	}

	public String getDopStr() {
		return dopStr;
	}

	public void setDopStr(String dopStr) {
		this.dopStr = dopStr;
	}

	public String getSchoolMeeting() {
		return schoolMeeting;
	}

	public void setSchoolMeeting(String schoolMeeting) {
		this.schoolMeeting = schoolMeeting;
	}

	public String getStudentMeeting() {
		return studentMeeting;
	}

	public void setStudentMeeting(String studentMeeting) {
		this.studentMeeting = studentMeeting;
	}

	public String getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(String totalStudent) {
		this.totalStudent = totalStudent;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public ArrayList<SelectItem> getConcessionList() {
		return concessionList;
	}

	public void setConcessionList(ArrayList<SelectItem> concessionList) {
		this.concessionList = concessionList;
	}

	public String getCurrentConcessionAssign() {
		return currentConcessionAssign;
	}

	public void setCurrentConcessionAssign(String currentConcessionAssign) {
		this.currentConcessionAssign = currentConcessionAssign;
	}

	public String getNewConcessionAssign() {
		return newConcessionAssign;
	}

	public void setNewConcessionAssign(String newConcessionAssign) {
		this.newConcessionAssign = newConcessionAssign;
	}

	public int getAttId() {
		return attId;
	}

	public void setAttId(int attId) {
		this.attId = attId;
	}

	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	public String getNaStudents() {
		return naStudents;
	}

	public void setNaStudents(String naStudents) {
		this.naStudents = naStudents;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public boolean isNaStudentsBooolean() {
		return naStudentsBooolean;
	}

	public void setNaStudentsBooolean(boolean naStudentsBooolean) {
		this.naStudentsBooolean = naStudentsBooolean;
	}

	public String getConcessionStatus() {
		return concessionStatus;
	}

	public void setConcessionStatus(String concessionStatus) {
		this.concessionStatus = concessionStatus;
	}

	public String getOldconcessionCategory() {
		return oldconcessionCategory;
	}

	public void setOldconcessionCategory(String oldconcessionCategory) {
		this.oldconcessionCategory = oldconcessionCategory;
	}

	public String getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(String gpsImei) {
		this.gpsImei = gpsImei;
	}

	public String getGpsPwd() {
		return gpsPwd;
	}

	public void setGpsPwd(String gpsPwd) {
		this.gpsPwd = gpsPwd;
	}

	public String getSchname() {
		return schname;
	}

	public void setSchname(String schname) {
		this.schname = schname;
	}

	public String getAdmRemark() {
		return admRemark;
	}

	public void setAdmRemark(String admRemark) {
		this.admRemark = admRemark;
	}

	public ArrayList<String> getFooterColumnsList() {
		return footerColumnsList;
	}

	public void setFooterColumnsList(ArrayList<String> footerColumnsList) {
		this.footerColumnsList = footerColumnsList;
	}

	public ArrayList<SubjectInfo> getFooterList() {
		return footerList;
	}

	public void setFooterList(ArrayList<SubjectInfo> footerList) {
		this.footerList = footerList;
	}

	public ArrayList<SubjectInfo> getMarkslist() {
		return markslist;
	}

	public void setMarkslist(ArrayList<SubjectInfo> markslist) {
		this.markslist = markslist;
	}

	public ArrayList<SubjectInfo> getCoscholasticMarklist() {
		return coscholasticMarklist;
	}

	public void setCoscholasticMarklist(ArrayList<SubjectInfo> coscholasticMarklist) {
		this.coscholasticMarklist = coscholasticMarklist;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public boolean isShowCoscholasticSubject() {
		return showCoscholasticSubject;
	}

	public void setShowCoscholasticSubject(boolean showCoscholasticSubject) {
		this.showCoscholasticSubject = showCoscholasticSubject;
	}

	public ArrayList<SubjectInfo> getIqSubjectList() {
		return iqSubjectList;
	}

	public void setIqSubjectList(ArrayList<SubjectInfo> iqSubjectList) {
		this.iqSubjectList = iqSubjectList;
	}

	public ArrayList<SubjectInfo> getEqSubList() {
		return eqSubList;
	}

	public void setEqSubList(ArrayList<SubjectInfo> eqSubList) {
		this.eqSubList = eqSubList;
	}

	public ArrayList<SubjectInfo> getSqSubList() {
		return sqSubList;
	}

	public void setSqSubList(ArrayList<SubjectInfo> sqSubList) {
		this.sqSubList = sqSubList;
	}

	public ArrayList<SubjectInfo> getPmdSubList() {
		return pmdSubList;
	}

	public void setPmdSubList(ArrayList<SubjectInfo> pmdSubList) {
		this.pmdSubList = pmdSubList;
	}

	public ArrayList<SubjectInfo> getLifeScienceSubList() {
		return lifeScienceSubList;
	}

	public void setLifeScienceSubList(ArrayList<SubjectInfo> lifeScienceSubList) {
		this.lifeScienceSubList = lifeScienceSubList;
	}

	public ArrayList<SubjectInfo> getCsSubList() {
		return csSubList;
	}

	public void setCsSubList(ArrayList<SubjectInfo> csSubList) {
		this.csSubList = csSubList;
	}

	public ArrayList<SubjectInfo> getOtherSubjectlist() {
		return otherSubjectlist;
	}

	public void setOtherSubjectlist(ArrayList<SubjectInfo> otherSubjectlist) {
		this.otherSubjectlist = otherSubjectlist;
	}

	public ArrayList<SubjectInfo> getCsoeSubList() {
		return csoeSubList;
	}

	public void setCsoeSubList(ArrayList<SubjectInfo> csoeSubList) {
		this.csoeSubList = csoeSubList;
	}

	public String getThought() {
		return thought;
	}

	public void setThought(String thought) {
		this.thought = thought;
	}

	public String getStrThoughtDate() {
		return strThoughtDate;
	}

	public void setStrThoughtDate(String strThoughtDate) {
		this.strThoughtDate = strThoughtDate;
	}

	public boolean isShowFile() {
		return showFile;
	}

	public void setShowFile(boolean showFile) {
		this.showFile = showFile;
	}

	public String getStudentImage() {
		return studentImage;
	}

	public void setStudentImage(String studentImage) {
		this.studentImage = studentImage;
	}

	public String getApp_download() {
		return app_download;
	}

	public void setApp_download(String app_download) {
		this.app_download = app_download;
	}

	public ArrayList<SubjectInfo> getOverallList() {
		return overallList;
	}

	public void setOverallList(ArrayList<SubjectInfo> overallList) {
		this.overallList = overallList;
	}

	public ArrayList<SubjectInfo> getEnglistList() {
		return englistList;
	}

	public void setEnglistList(ArrayList<SubjectInfo> englistList) {
		this.englistList = englistList;
	}

	public ArrayList<SubjectInfo> getHindiList() {
		return hindiList;
	}

	public void setHindiList(ArrayList<SubjectInfo> hindiList) {
		this.hindiList = hindiList;
	}

	public ArrayList<SubjectInfo> getMathList() {
		return mathList;
	}

	public void setMathList(ArrayList<SubjectInfo> mathList) {
		this.mathList = mathList;
	}

	public ArrayList<SubjectInfo> getEvsList() {
		return evsList;
	}

	public void setEvsList(ArrayList<SubjectInfo> evsList) {
		this.evsList = evsList;
	}

	public ArrayList<SubjectInfo> getComputerList() {
		return computerList;
	}

	public void setComputerList(ArrayList<SubjectInfo> computerList) {
		this.computerList = computerList;
	}

	public ArrayList<SubjectInfo> getArtList() {
		return artList;
	}

	public void setArtList(ArrayList<SubjectInfo> artList) {
		this.artList = artList;
	}

	public ArrayList<SubjectInfo> getIndianMusicList() {
		return indianMusicList;
	}

	public void setIndianMusicList(ArrayList<SubjectInfo> indianMusicList) {
		this.indianMusicList = indianMusicList;
	}

	public ArrayList<SubjectInfo> getWesternMusicList() {
		return westernMusicList;
	}

	public void setWesternMusicList(ArrayList<SubjectInfo> westernMusicList) {
		this.westernMusicList = westernMusicList;
	}

	public ArrayList<SubjectInfo> getDanceList() {
		return danceList;
	}

	public void setDanceList(ArrayList<SubjectInfo> danceList) {
		this.danceList = danceList;
	}

	public ArrayList<SubjectInfo> getDramaList() {
		return dramaList;
	}

	public void setDramaList(ArrayList<SubjectInfo> dramaList) {
		this.dramaList = dramaList;
	}

	public ArrayList<SubjectInfo> getPhyEducationList() {
		return phyEducationList;
	}

	public void setPhyEducationList(ArrayList<SubjectInfo> phyEducationList) {
		this.phyEducationList = phyEducationList;
	}

	public ArrayList<SubjectInfo> getOverallRemarkList() {
		return overallRemarkList;
	}

	public void setOverallRemarkList(ArrayList<SubjectInfo> overallRemarkList) {
		this.overallRemarkList = overallRemarkList;
	}

	public ArrayList<SubjectInfo> getEnglistRemarkList() {
		return englistRemarkList;
	}

	public void setEnglistRemarkList(ArrayList<SubjectInfo> englistRemarkList) {
		this.englistRemarkList = englistRemarkList;
	}

	public ArrayList<SubjectInfo> getHindiRemarkList() {
		return hindiRemarkList;
	}

	public void setHindiRemarkList(ArrayList<SubjectInfo> hindiRemarkList) {
		this.hindiRemarkList = hindiRemarkList;
	}

	public ArrayList<SubjectInfo> getMathRemarkList() {
		return mathRemarkList;
	}

	public void setMathRemarkList(ArrayList<SubjectInfo> mathRemarkList) {
		this.mathRemarkList = mathRemarkList;
	}

	public ArrayList<SubjectInfo> getEvsRemarkList() {
		return evsRemarkList;
	}

	public void setEvsRemarkList(ArrayList<SubjectInfo> evsRemarkList) {
		this.evsRemarkList = evsRemarkList;
	}

	public ArrayList<SubjectInfo> getComputerRemarkList() {
		return computerRemarkList;
	}

	public void setComputerRemarkList(ArrayList<SubjectInfo> computerRemarkList) {
		this.computerRemarkList = computerRemarkList;
	}

	public ArrayList<SubjectInfo> getArtRemarkList() {
		return artRemarkList;
	}

	public void setArtRemarkList(ArrayList<SubjectInfo> artRemarkList) {
		this.artRemarkList = artRemarkList;
	}

	public ArrayList<SubjectInfo> getIndianMusicRemarkList() {
		return indianMusicRemarkList;
	}

	public void setIndianMusicRemarkList(ArrayList<SubjectInfo> indianMusicRemarkList) {
		this.indianMusicRemarkList = indianMusicRemarkList;
	}

	public ArrayList<SubjectInfo> getWesternMusicRemarkList() {
		return westernMusicRemarkList;
	}

	public void setWesternMusicRemarkList(ArrayList<SubjectInfo> westernMusicRemarkList) {
		this.westernMusicRemarkList = westernMusicRemarkList;
	}

	public ArrayList<SubjectInfo> getDanceRemarkList() {
		return danceRemarkList;
	}

	public void setDanceRemarkList(ArrayList<SubjectInfo> danceRemarkList) {
		this.danceRemarkList = danceRemarkList;
	}

	public ArrayList<SubjectInfo> getDramaRemarkList() {
		return dramaRemarkList;
	}

	public void setDramaRemarkList(ArrayList<SubjectInfo> dramaRemarkList) {
		this.dramaRemarkList = dramaRemarkList;
	}

	public ArrayList<SubjectInfo> getPhyEducationRemarkList() {
		return phyEducationRemarkList;
	}

	public void setPhyEducationRemarkList(ArrayList<SubjectInfo> phyEducationRemarkList) {
		this.phyEducationRemarkList = phyEducationRemarkList;
	}

	public String getWinterRemark() {
		return winterRemark;
	}

	public void setWinterRemark(String winterRemark) {
		this.winterRemark = winterRemark;
	}

	public String getWinterDateStr() {
		return winterDateStr;
	}

	public void setWinterDateStr(String winterDateStr) {
		this.winterDateStr = winterDateStr;
	}

	public String getAttendWinter() {
		return attendWinter;
	}

	public void setAttendWinter(String attendWinter) {
		this.attendWinter = attendWinter;
	}

	public int getStudentRollNo() {
		return studentRollNo;
	}

	public void setStudentRollNo(int studentRollNo) {
		this.studentRollNo = studentRollNo;
	}

	public ArrayList<SubjectInfo> getScienceList() {
		return scienceList;
	}

	public void setScienceList(ArrayList<SubjectInfo> scienceList) {
		this.scienceList = scienceList;
	}

	public ArrayList<SubjectInfo> getScienceRemarkList() {
		return scienceRemarkList;
	}

	public void setScienceRemarkList(ArrayList<SubjectInfo> scienceRemarkList) {
		this.scienceRemarkList = scienceRemarkList;
	}

	public UploadedFile getStudentImageget() {
		return studentImageget;
	}

	public void setStudentImageget(UploadedFile studentImageget) {
		this.studentImageget = studentImageget;
	}

	public int getRankInt() {
		return rankInt;
	}

	public void setRankInt(int rankInt) {
		this.rankInt = rankInt;
	}

	public String getConductorName() {
		return conductorName;
	}

	public void setConductorName(String conductorName) {
		this.conductorName = conductorName;
	}

	public String getConductorNo() {
		return conductorNo;
	}

	public void setConductorNo(String conductorNo) {
		this.conductorNo = conductorNo;
	}

	public String getAttendantName() {
		return attendantName;
	}

	public void setAttendantName(String attendantName) {
		this.attendantName = attendantName;
	}

	public String getAttendNo() {
		return attendNo;
	}

	public void setAttendNo(String attendNo) {
		this.attendNo = attendNo;
	}

	public String getShowDriver() {
		return showDriver;
	}

	public void setShowDriver(String showDriver) {
		this.showDriver = showDriver;
	}

	public String getShowConductor() {
		return showConductor;
	}

	public void setShowConductor(String showConductor) {
		this.showConductor = showConductor;
	}

	public String getShowAttendant() {
		return showAttendant;
	}

	public void setShowAttendant(String showAttendant) {
		this.showAttendant = showAttendant;
	}

	public String getEmpImageDriver() {
		return empImageDriver;
	}

	public void setEmpImageDriver(String empImageDriver) {
		this.empImageDriver = empImageDriver;
	}

	public String getEmpImageConductor() {
		return empImageConductor;
	}

	public void setEmpImageConductor(String empImageConductor) {
		this.empImageConductor = empImageConductor;
	}

	public String getEmpImageAttendant() {
		return empImageAttendant;
	}

	public void setEmpImageAttendant(String empImageAttendant) {
		this.empImageAttendant = empImageAttendant;
	}

	public String getCategId() {
		return categId;
	}

	public void setCategId(String categId) {
		this.categId = categId;
	}

	public String getEnquiryNo() {
		return enquiryNo;
	}

	public void setEnquiryNo(String enquiryNo) {
		this.enquiryNo = enquiryNo;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public String getEnqUAEId() {
		return enqUAEId;
	}

	public void setEnqUAEId(String enqUAEId) {
		this.enqUAEId = enqUAEId;
	}

	public String getPercentageRemark() {
		return percentageRemark;
	}

	public void setPercentageRemark(String percentageRemark) {
		this.percentageRemark = percentageRemark;
	}

	public PickDropInfo getPickdropInfo() {
		return pickdropInfo;
	}

	public void setPickdropInfo(PickDropInfo pickdropInfo) {
		this.pickdropInfo = pickdropInfo;
	}

	public String getExtraClass() {
		return extraClass;
	}

	public void setExtraClass(String extraClass) {
		this.extraClass = extraClass;
	}

	public ArrayList<EyfsInfo> getEyfsMarkList() {
		return eyfsMarkList;
	}

	public void setEyfsMarkList(ArrayList<EyfsInfo> eyfsMarkList) {
		this.eyfsMarkList = eyfsMarkList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CartesianChartModel getCombinedModel() {
		return combinedModel;
	}

	public void setCombinedModel(CartesianChartModel combinedModel) {
		this.combinedModel = combinedModel;
	}

	public String getTransfee() {
		return transfee;
	}

	public void setTransfee(String transfee) {
		this.transfee = transfee;
	}

	public String getNcc() {
		return ncc;
	}

	public void setNcc(String ncc) {
		this.ncc = ncc;
	}

	public String getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(String totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public String getAttachmentStr() {
		return attachmentStr;
	}

	public void setAttachmentStr(String attachmentStr) {
		this.attachmentStr = attachmentStr;
	}

	public UploadedFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(UploadedFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String getRfidNo() {
		return rfidNo;
	}

	public void setRfidNo(String rfidNo) {
		this.rfidNo = rfidNo;
	}

	public String getClasid() {
		return clasid;
	}

	public void setClasid(String clasid) {
		this.clasid = clasid;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public String getMarksGrade() {
		return marksGrade;
	}

	public void setMarksGrade(String marksGrade) {
		this.marksGrade = marksGrade;
	}

	public String getHalfYearly() {
		return halfYearly;
	}

	public void setHalfYearly(String halfYearly) {
		this.halfYearly = halfYearly;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public boolean isFileRender() {
		return fileRender;
	}

	public void setFileRender(boolean fileRender) {
		this.fileRender = fileRender;
	}

	public Map<String, String> getDocMap() {
		return docMap;
	}

	public void setDocMap(Map<String, String> docMap) {
		this.docMap = docMap;
	}

	public Map<String, String> getDocMapCal() {
		return docMapCal;
	}

	public void setDocMapCal(Map<String, String> docMapCal) {
		this.docMapCal = docMapCal;
	}

	public ArrayList<SelectItem> getDocList() {
		return docList;
	}

	public void setDocList(ArrayList<SelectItem> docList) {
		this.docList = docList;
	}

	public ArrayList<ExtraFieldInfo> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(ArrayList<ExtraFieldInfo> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public ArrayList<ExtraFieldInfo> getRemarkList() {
		return remarkList;
	}

	public void setRemarkList(ArrayList<ExtraFieldInfo> remarkList) {
		this.remarkList = remarkList;
	}

	public ArrayList<SubjectInfo> getMarkListOtherSubject() {
		return markListOtherSubject;
	}

	public void setMarkListOtherSubject(ArrayList<SubjectInfo> markListOtherSubject) {
		this.markListOtherSubject = markListOtherSubject;
	}

	public ArrayList<SubjectInfo> getMarkListAddSub() {
		return markListAddSub;
	}

	public void setMarkListAddSub(ArrayList<SubjectInfo> markListAddSub) {
		this.markListAddSub = markListAddSub;
	}

	public ArrayList<TermInfo> getTermListForAdditional() {
		return termListForAdditional;
	}

	public void setTermListForAdditional(ArrayList<TermInfo> termListForAdditional) {
		this.termListForAdditional = termListForAdditional;
	}

	public ArrayList<String> getAddColumnsList() {
		return addColumnsList;
	}

	public void setAddColumnsList(ArrayList<String> addColumnsList) {
		this.addColumnsList = addColumnsList;
	}

	public ArrayList<SubjectGroupInfo> getSubGroupList() {
		return subGroupList;
	}

	public void setSubGroupList(ArrayList<SubjectGroupInfo> subGroupList) {
		this.subGroupList = subGroupList;
	}

	public String getAgeGroupId() {
		return ageGroupId;
	}

	public void setAgeGroupId(String ageGroupId) {
		this.ageGroupId = ageGroupId;
	}

	public String getAgeGroupName() {
		return ageGroupName;
	}

	public void setAgeGroupName(String ageGroupName) {
		this.ageGroupName = ageGroupName;
	}

	public RfidDataInfo getRfidDataInfo() {
		return rfidDataInfo;
	}

	public void setRfidDataInfo(RfidDataInfo rfidDataInfo) {
		this.rfidDataInfo = rfidDataInfo;

	}

	public String getRfidStatus() {
		return rfidStatus;
	}

	public void setRfidStatus(String rfidStatus) {
		this.rfidStatus = rfidStatus;
	}

	public ArrayList<String> getAddNoList() {
		return addNoList;
	}

	public void setAddNoList(ArrayList<String> addNoList) {
		this.addNoList = addNoList;
	}

	public ArrayList<String> getMotherNameList() {
		return motherNameList;
	}

	public void setMotherNameList(ArrayList<String> motherNameList) {
		this.motherNameList = motherNameList;
	}

	public ArrayList<String> getFatherNameList() {
		return fatherNameList;
	}

	public void setFatherNameList(ArrayList<String> fatherNameList) {
		this.fatherNameList = fatherNameList;
	}

	public ArrayList<String> getNameList() {
		return nameList;
	}

	public void setNameList(ArrayList<String> nameList) {
		this.nameList = nameList;
	}

	public ArrayList<SubjectInfo> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(ArrayList<SubjectInfo> subjectList) {
		this.subjectList = subjectList;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public SelectItem getRfidInfo() {
		return rfidInfo;
	}

	public void setRfidInfo(SelectItem rfidInfo) {
		this.rfidInfo = rfidInfo;
	}

	public String getLedgerNo() {
		return ledgerNo;
	}

	public void setLedgerNo(String ledgerNo) {
		this.ledgerNo = ledgerNo;
	}

	public ArrayList<SubjectInfo> getDrawingList() {
		return drawingList;
	}

	public void setDrawingList(ArrayList<SubjectInfo> drawingList) {
		this.drawingList = drawingList;
	}

	public ArrayList<SubjectInfo> getSportsList() {
		return sportsList;
	}

	public void setSportsList(ArrayList<SubjectInfo> sportsList) {
		this.sportsList = sportsList;
	}

	public ArrayList<SubjectInfo> getPersonalList() {
		return personalList;
	}

	public void setPersonalList(ArrayList<SubjectInfo> personalList) {
		this.personalList = personalList;
	}

	public ArrayList<SubjectInfo> getWorkingList() {
		return workingList;
	}

	public void setWorkingList(ArrayList<SubjectInfo> workingList) {
		this.workingList = workingList;
	}

	public String getGeoFence() {
		return geoFence;
	}

	public void setGeoFence(String geoFence) {
		this.geoFence = geoFence;
	}

	public String getMarksType() {
		return marksType;
	}

	public void setMarksType(String marksType) {
		this.marksType = marksType;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public int getGraceCounter() {
		return graceCounter;
	}

	public void setGraceCounter(int graceCounter) {
		this.graceCounter = graceCounter;
	}

	public String getGeoFanceTime() {
		return geoFanceTime;
	}

	public void setGeoFanceTime(String geoFanceTime) {
		this.geoFanceTime = geoFanceTime;
	}

	public String getEveningGeoFancingTime() {
		return eveningGeoFancingTime;
	}

	public void setEveningGeoFancingTime(String eveningGeoFancingTime) {
		this.eveningGeoFancingTime = eveningGeoFancingTime;
	}

	public String getPrinciRemark() {
		return princiRemark;
	}

	public void setPrinciRemark(String princiRemark) {
		this.princiRemark = princiRemark;
	}

	public String getRegNo_IX() {
		return regNo_IX;
	}

	public void setRegNo_IX(String regNo_IX) {
		this.regNo_IX = regNo_IX;
	}

	public String getRegNo_XI() {
		return regNo_XI;
	}

	public void setRegNo_XI(String regNo_XI) {
		this.regNo_XI = regNo_XI;
	}

	public String getAdmnFileNo() {
		return admnFileNo;
	}

	public void setAdmnFileNo(String admnFileNo) {
		this.admnFileNo = admnFileNo;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getLactureImage() {
		return lactureImage;
	}

	public void setLactureImage(String lactureImage) {
		this.lactureImage = lactureImage;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLatestChatTime() {
		return latestChatTime;
	}

	public void setLatestChatTime(String latestChatTime) {
		this.latestChatTime = latestChatTime;
	}

	public String getStudentPercentage() {
		return studentPercentage;
	}

	public void setStudentPercentage(String studentPercentage) {
		this.studentPercentage = studentPercentage;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getClassStrName() {
		return classStrName;
	}

	public void setClassStrName(String classStrName) {
		this.classStrName = classStrName;
	}

	public ArrayList<String> getOtherColumnsList() {
		return otherColumnsList;
	}

	public void setOtherColumnsList(ArrayList<String> otherColumnsList) {
		this.otherColumnsList = otherColumnsList;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getFileName3() {
		return fileName3;
	}

	public void setFileName3(String fileName3) {
		this.fileName3 = fileName3;
	}

	public String getFileName4() {
		return fileName4;
	}

	public void setFileName4(String fileName4) {
		this.fileName4 = fileName4;
	}

	public String getFileName5() {
		return fileName5;
	}

	public void setFileName5(String fileName5) {
		this.fileName5 = fileName5;
	}

	public String getFileName6() {
		return fileName6;
	}

	public void setFileName6(String fileName6) {
		this.fileName6 = fileName6;
	}

	public String getFile4() {
		return file4;
	}

	public void setFile4(String file4) {
		this.file4 = file4;
	}

	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}

	public String getFile5() {
		return file5;
	}

	public void setFile5(String file5) {
		this.file5 = file5;
	}

	public String getFile6() {
		return file6;
	}

	public void setFile6(String file6) {
		this.file6 = file6;
	}

	public String getAssignmentSubmit() {
		return assignmentSubmit;
	}

	public void setAssignmentSubmit(String assignmentSubmit) {
		this.assignmentSubmit = assignmentSubmit;
	}

	public String getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(String studentDetails) {
		this.studentDetails = studentDetails;
	}

	public String getFatherDetails() {
		return fatherDetails;
	}

	public void setFatherDetails(String fatherDetails) {
		this.fatherDetails = fatherDetails;
	}

	public String getMotherDetails() {
		return motherDetails;
	}

	public void setMotherDetails(String motherDetails) {
		this.motherDetails = motherDetails;
	}

	public String getGuardianDetails() {
		return guardianDetails;
	}

	public void setGuardianDetails(String guardianDetails) {
		this.guardianDetails = guardianDetails;
	}

	public String getFatherAnnIncome() {
		return fatherAnnIncome;
	}

	public void setFatherAnnIncome(String fatherAnnIncome) {
		this.fatherAnnIncome = fatherAnnIncome;
	}

	public String getMotherAnnIncome() {
		return motherAnnIncome;
	}

	public void setMotherAnnIncome(String motherAnnIncome) {
		this.motherAnnIncome = motherAnnIncome;
	}

	public String getSubject1() {
		return subject1;
	}

	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}

	public String getSubject2() {
		return subject2;
	}

	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	public String getSubject3() {
		return subject3;
	}

	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}

	public String getSubject4() {
		return subject4;
	}

	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}

	public String getSubject5() {
		return subject5;
	}

	public void setSubject5(String subject5) {
		this.subject5 = subject5;
	}

	public String getHisHer() {
		return hisHer;
	}

	public void setHisHer(String hisHer) {
		this.hisHer = hisHer;
	}

	public ArrayList<ExtraFieldInfo> getOtherValueList() {
		return otherValueList;
	}

	public void setOtherValueList(ArrayList<ExtraFieldInfo> otherValueList) {
		this.otherValueList = otherValueList;
	}

	public String getPromotionStatus() {
		return promotionStatus;
	}

	public void setPromotionStatus(String promotionStatus) {
		this.promotionStatus = promotionStatus;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getGuardian_1_image() {
		return guardian_1_image;
	}

	public void setGuardian_1_image(String guardian_1_image) {
		this.guardian_1_image = guardian_1_image;
	}

	public String getGuardian_2_image() {
		return guardian_2_image;
	}

	public void setGuardian_2_image(String guardian_2_image) {
		this.guardian_2_image = guardian_2_image;
	}

	public String getGuardian_name_2() {
		return guardian_name_2;
	}

	public void setGuardian_name_2(String guardian_name_2) {
		this.guardian_name_2 = guardian_name_2;
	}

	public String getFatherOfficeContatNo() {
		return fatherOfficeContatNo;
	}

	public void setFatherOfficeContatNo(String fatherOfficeContatNo) {
		this.fatherOfficeContatNo = fatherOfficeContatNo;
	}

	public String getMotherOfficeContatNo() {
		return motherOfficeContatNo;
	}

	public void setMotherOfficeContatNo(String motherOfficeContatNo) {
		this.motherOfficeContatNo = motherOfficeContatNo;
	}

	public String getTansportFeeDiscount() {
		return tansportFeeDiscount;
	}

	public void setTansportFeeDiscount(String tansportFeeDiscount) {
		this.tansportFeeDiscount = tansportFeeDiscount;
	}

	public String getMainClassId() {
		return mainClassId;
	}

	public void setMainClassId(String mainClassId) {
		this.mainClassId = mainClassId;
	}

	public String getDateToWord() {
		return dateToWord;
	}

	public void setDateToWord(String dateToWord) {
		this.dateToWord = dateToWord;
	}

	public ArrayList<Subjects> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(ArrayList<Subjects> subjectsList) {
		this.subjectsList = subjectsList;
	}

	public String getImageDownloadPath() {
		return imageDownloadPath;
	}

	public void setImageDownloadPath(String imageDownloadPath) {
		this.imageDownloadPath = imageDownloadPath;
	}

	public String getImageUploadPath() {
		return imageUploadPath;
	}

	public void setImageUploadPath(String imageUploadPath) {
		this.imageUploadPath = imageUploadPath;
	}

	public String getStdImageWithoutPath() {
		return stdImageWithoutPath;
	}

	public void setStdImageWithoutPath(String stdImageWithoutPath) {
		this.stdImageWithoutPath = stdImageWithoutPath;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;

	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String[] getBlockModList() {
		return blockModList;
	}

	public void setBlockModList(String[] blockModList) {
		this.blockModList = blockModList;
	}

	public ArrayList<SelectItem> getModList() {
		return modList;
	}

	public void setModList(ArrayList<SelectItem> modList) {
		this.modList = modList;
	}

	public String getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(String meetingid) {
		this.meetingid = meetingid;
	}

	public String getClassStartTimeStr() {
		return classStartTimeStr;
	}

	public void setClassStartTimeStr(String classStartTimeStr) {
		this.classStartTimeStr = classStartTimeStr;
	}

	public String getClassEndTimeStr() {
		return classEndTimeStr;
	}

	public void setClassEndTimeStr(String classEndTimeStr) {
		this.classEndTimeStr = classEndTimeStr;
	}

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public String getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getTotalQues() {
		return totalQues;
	}

	public void setTotalQues(String totalQues) {
		this.totalQues = totalQues;
	}

	public String getRightAnswers() {
		return rightAnswers;
	}

	public void setRightAnswers(String rightAnswers) {
		this.rightAnswers = rightAnswers;
	}

	public String getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(String wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	public String getNoAnswer() {
		return noAnswer;
	}

	public void setNoAnswer(String noAnswer) {
		this.noAnswer = noAnswer;
	}

	public String getPostiveMarks() {
		return postiveMarks;
	}

	public void setPostiveMarks(String postiveMarks) {
		this.postiveMarks = postiveMarks;
	}

	public String getNegativeMarks() {
		return negativeMarks;
	}

	public void setNegativeMarks(String negativeMarks) {
		this.negativeMarks = negativeMarks;
	}

	public String getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getMinority() {
		return minority;
	}

	public void setMinority(String minority) {
		this.minority = minority;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

}
