package FEEKIOSK;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;



import Json.DataBaseMeathodJson;
import Master.BannerInfo;
import schooldata.DailyFeeCollectionBean;
import schooldata.DataBaseConnection;
import schooldata.DatabaseMethods1;
import schooldata.FeeDynamicList;
import schooldata.FeeInfo;
import schooldata.SchoolInfoList;

@ManagedBean(name = "side")
@ViewScoped
public class FeeKioskSideMenu implements Serializable {

	DatabaseMethodkiosk DBK = new DatabaseMethodkiosk();
	ArrayList<StudentInfo> list = new ArrayList<StudentInfo>();
	ArrayList<FeeInfo> installmentfeeList = new ArrayList<FeeInfo>();
	ArrayList<BannerInfo> banners = new ArrayList<BannerInfo>();
	private DonutChartModel donutModel;
	boolean waveOffLateFee = false;
	String msg = "";
	
	ArrayList<DailyFeeCollectionBean> fees = new ArrayList<DailyFeeCollectionBean>();

	public FeeKioskSideMenu() throws ParseException {

		Connection conn = DataBaseConnection.javaConnection();

		HttpSession ss = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String username = (String) ss.getAttribute("username");
		String pin = (String) ss.getAttribute("kioskpin");
		//list = (ArrayList<StudentInfo>) ss.getAttribute("studentList");
		list = DBK.getstudentsDetails(username,pin,conn);
		msg = "";
		if(list.size()==0) {
			msg = "There is no students in current session of school. Please Contact with School.";
			PrimeFaces.current().executeScript("PF('commonDialog').show()");
			PrimeFaces.current().ajax().update("commonDia");
		}else {
			for(StudentInfo y : list) {
				
				y.setStudent_image(y.getStudent_image().replace("http://", "https://"));
				
				if(y.getDailyFees().isEmpty()==false) {
					int size =  y.getDailyFees().size();
					int red = 0;
					if(size >5 ) {
						red = size - 5;
					}
					int i = 1;
					for(DailyFeeCollectionBean x : y.getDailyFees()) {
						x.setSrno(i);
						i = i+1;
						if(i == 6) {
							i = 1;
						}
					}
					fees = new ArrayList<DailyFeeCollectionBean>();
					for(int z = size;z>red;z--) {
						fees.add(y.getDailyFees().get(z-1));
					}
					y.setShowFee(fees);
					
					
				}
				
				if(y.getShowFee().size()==0) {
					y.setFirst(false);
					y.setSecond(true);
				}else {
					y.setFirst(true);
					y.setSecond(false);
				}
				
				lateFeeCalculation(y);
				double tFee = 0;
				double totalDue = 0;
				for (FeeInfo ls : installmentfeeList) {
					totalDue = totalDue + ls.getTotalFee();

				}
				
				tFee = totalDue + y.getTotalPaid();
				int totalPaid = y.getTotalPaid();
				donutModel = new DonutChartModel();
		        

				ChartData data = new ChartData();

		        DonutChartDataSet dataSet = new DonutChartDataSet();
		        ArrayList<Number> values = new ArrayList<>();
		        values.add(0);
		        
		        double totalP = (totalPaid/tFee)*100;
		        double totalD = (totalDue/tFee)*100;
		        
		        values.add(totalP);
		        values.add(totalD);
		        dataSet.setData(values);

		        ArrayList<String> bgColors = new ArrayList<>();
		        bgColors.add("rgb(255, 99, 132)");
		        bgColors.add("rgb(255, 205, 86)");
		        bgColors.add("rgb(151, 216, 255)");
		        dataSet.setBackgroundColor(bgColors);

		        data.addChartDataSet(dataSet);
		        ArrayList<String> labels = new ArrayList<>();
		        labels.add("Total Fees : ₹"+tFee);
		        labels.add("Total Paid : ₹"+totalPaid);
		        labels.add("Total Due : ₹"+totalDue);
		        data.setLabels(labels);
		        
		        donutModel.setData(data);
		        y.setDonutModel(donutModel);
		        
			}
			
		}
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int lateFeeCalculation(StudentInfo std) throws ParseException {
		Date recipietDate = new Date();
		Connection conn = DataBaseConnection.javaConnection();
		DataBaseMeathodJson DBJ = new DataBaseMeathodJson();
		DatabaseMethods1 DBM = new DatabaseMethods1();
		installmentfeeList = new ArrayList<>();

		String addmissionNumber = "";
		try {
			addmissionNumber = std.getAddNumber();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String schoolid = std.getSchid();
		FeeDynamicList lateFees = new DatabaseMethods1().getlatefeecalculation(schoolid, conn);
		String date = new SimpleDateFormat("dd/MM/yyyy").format(recipietDate);
		String preSession = DatabaseMethods1.selectedSessionDetails(schoolid, conn);
		schooldata.StudentInfo sList = DBM.studentDetailslistByAddNo(schoolid,addmissionNumber,conn);
		ArrayList<FeeDynamicList> feelist;
		if ((schoolid.equals("287") && Integer.parseInt(sList.getClassId()) < 13) || (!schoolid.equals("287"))) {
			feelist = new DatabaseMethods1().getAllInstallment(schoolid, conn);

		} else {
			feelist = new DatabaseMethods1().getAllInstallmentforAjmani(schoolid, conn);

		}

		String studentStatus = sList.getStudentStatus();
		String connsessioncategory = sList.getConcession();
		ArrayList<FeeInfo> classFeeList;
		new ArrayList<>();
		String[] session = preSession.split("-");
		double previousDueAmount = 0;
		int totalLateFee = 0;
		for (int i = 0; i < feelist.size(); i++) {

			Date findDate = null;
			Date cDate = null;
			try {
				findDate = feelist.get(i).getFee_due_date();
				cDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			double dueAmount = 0, actuladueAmount = 0;

			if (feelist.get(i).getInsatllmentName().equalsIgnoreCase("Previous_Fee")) {

				classFeeList = new ArrayList<>();
				classFeeList = DBM.addPreviousFee(schoolid, classFeeList, addmissionNumber, conn);

				for (FeeInfo lss : classFeeList) {
					if (lss.getFeeId().equals("-1")) {
						int mainamount = lss.getFeeAmount();
						int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(), sList, preSession,
								lss.getFeeId(), feelist.get(i).getInsatllmentValue(), conn);
						int dAmount = mainamount - totalpaidamount;
						if (dAmount > 0) {
							dueAmount += dAmount;
						}
					}

				}

			} else {
				ArrayList<schooldata.StudentInfo> studentList = DBM.searchStudentListForDueFeeReportForMasterForStkabeer(schoolid,
						addmissionNumber, Integer.parseInt(feelist.get(i).getInsatllmentValue()), preSession, conn,
						"feeCollection", Integer.parseInt(feelist.get(i).getMonth()),
						feelist.get(i).getInstallment_Month_Include());
				HashMap<String, String> map = (HashMap<String, String>) studentList.get(0).getFeesMap();
				classFeeList = new ArrayList<>();

				classFeeList = DBM.classFeesForStudent11(schoolid, sList.getClassId(), preSession, studentStatus,
						connsessioncategory, conn);
				for (FeeInfo ls : classFeeList) {
					if (!ls.getFeeId().equals("-2") && !ls.getFeeId().equals("-3") && !ls.getFeeId().equals("-4")) {
						int mainamount = Integer.parseInt(map.get(ls.getFeeName()));
						if (Integer.parseInt(ls.getTax_percn()) > 0) {
							mainamount = mainamount + ((mainamount * Integer.parseInt(ls.getTax_percn())) / 100);
						}

						int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(), sList, preSession,
								ls.getFeeId(), feelist.get(i).getInsatllmentValue(), conn);
						int dAmount = mainamount - totalpaidamount;
						if (dAmount > 0) {
							dueAmount += dAmount;
						}
					}
				}
			}

			actuladueAmount = dueAmount - previousDueAmount;

			// JSONObject obj = new JSONObject();
			FeeInfo ff = new FeeInfo();
			ff.setAmount(actuladueAmount);
			ff.setFeeMonth(feelist.get(i).getInsatllmentName());
			ff.setFeeMonthInt(Integer.parseInt(feelist.get(i).getInsatllmentValue()));
			int latefee = 0;

			if (actuladueAmount > 0 && waveOffLateFee == false) {

				long diff = cDate.getTime() - findDate.getTime();
				int fff = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				if (lateFees.getFee_due_days().equals("")) {
					latefee = 0;
				} else {
					String[] feeduedays = lateFees.getFee_due_days().split(",");
					String[] latefeeamount = lateFees.getLate_fee().split(",");
					if (feeduedays.length == 1) {
						if (Integer.parseInt(feeduedays[0]) >= fff) {
							latefee = Integer.parseInt(latefeeamount[0]);
						}

					} else if (feeduedays.length == 2) {
						int ii = Integer.parseInt(feeduedays[0]);
						int j = Integer.parseInt(feeduedays[1]);
						if (ii <= fff && fff < j) {
							latefee = Integer.parseInt(latefeeamount[0]);
						} else if (fff >= j) {
							latefee = Integer.parseInt(latefeeamount[1]);
						} else {
							latefee = 0;
						}
					}

				}

				int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(), sList, preSession, "-2",
						feelist.get(i).getInsatllmentValue(), conn);
				int l = latefee - totalpaidamount;
				if (l > 0) {
					latefee = l;
				} else {
					latefee = 0;
				}

			}

			if (actuladueAmount > 0) {
				totalLateFee += latefee;
				// ff.setLateFee(latefee);
				ff.setTotalFee(ff.getAmount() + latefee);
				installmentfeeList.add(ff);
			}

		}
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalLateFee;
	}
	
	
	
	
	public void redirectPayment(StudentInfo std) {
		HttpSession ss = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Connection conn = DataBaseConnection.javaConnection();
//
		ss.setAttribute("addNumber",std.getAddNumber());
		ss.setAttribute("schoolid",std.getSchid());
		ss.setAttribute("selectedSession",std.getSession());
		
		SchoolInfoList schinfo = new DatabaseMethods1().fullSchoolInfo(std.getSchid(),conn);
		ss.setAttribute("topName", schinfo.getSchoolName());
		
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		try {
			ec.redirect("studentOnlineFee.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void checkHistory(StudentInfo std) {
		HttpSession ss = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Connection conn = DataBaseConnection.javaConnection();
//
		ss.setAttribute("addNumber",std.getAddNumber());
		ss.setAttribute("schoolid",std.getSchid());
		ss.setAttribute("selectedSession",std.getSession());
		ss.setAttribute("studentInfo", std);
		
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		try {
			ec.redirect("feeKioskHistoryReport.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public ArrayList<FeeInfo> getInstallmentfeeList() {
		return installmentfeeList;
	}


	public void setInstallmentfeeList(ArrayList<FeeInfo> installmentfeeList) {
		this.installmentfeeList = installmentfeeList;
	}


	public ArrayList<StudentInfo> getList() {
		return list;
	}


	public void setList(ArrayList<StudentInfo> list) {
		this.list = list;
	}


	public ArrayList<BannerInfo> getBanners() {
		return banners;
	}


	public void setBanners(ArrayList<BannerInfo> banners) {
		this.banners = banners;
	}


	public DonutChartModel getDonutModel() {
		return donutModel;
	}


	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}
	
	

}
