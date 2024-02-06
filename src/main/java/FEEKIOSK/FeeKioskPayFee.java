package FEEKIOSK;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.primefaces.PrimeFaces;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import Json.DataBaseMeathodJson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import schooldata.DailyFeeCollectionBean;
import schooldata.DataBaseConnection;
import schooldata.DatabaseMethods1;
import schooldata.FeeDynamicList;
import schooldata.FeeInfo;
import schooldata.Feecollectionc;
import schooldata.SchoolInfoList;
import schooldata.StudentInfo;

@ManagedBean(name="studentOnlineFee")
@ViewScoped

public class FeeKioskPayFee implements Serializable
{
	ArrayList<FeeInfo> list = new ArrayList<>();
	ArrayList<FeeInfo> selectedList = new ArrayList<>();
	double totalFee,totalLateFee,totalAmount;
	boolean waveOffLateFee=true, showPay = false, statusTimer = false, showLoader = false;
	String addmissionNumber="",schoolid,totalPayAmunt,totalDiscountAmount, rzpAmount, rzpOrderId, fathersPhoneStr;

	ArrayList<DailyFeeCollectionBean> dailyfee=new ArrayList<>();
	StudentInfo sList;
	DailyFeeCollectionBean selectedstudent;
	ArrayList<Feecollectionc> feesSelected=new ArrayList<>();
	Date changeDate;
	DataBaseMeathodJson DBJ=new DataBaseMeathodJson();
	DatabaseMethods1 DBM=new DatabaseMethods1();
	SchoolInfoList schinfo = new SchoolInfoList();
	
	int number = 0;
	String orderid="",statusImage = "paymentsuccess1.png", statusMsg = "", statusAudio = "paymentsuccess.mp3" ,subStatusMsg = "";
	String rzpOrder,rzpPayment,rzpSignature, qrCode;
	String cfrOrderToken, cfrPayment, cfrOrder, cfrStatus, cfrPaymentStatus, cfrBankRef;
	
	public FeeKioskPayFee()
	{

		HttpSession ss = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);
		ss.setAttribute("portal", "pay");	
		addmissionNumber=(String) ss.getAttribute("addNumber");
		schoolid = (String) ss.getAttribute("schoolid");
		 // // system.out.println("inside conn....."+ss.getAttribute("selectedSession"));
		
		Connection conn= DataBaseConnection.javaConnection();
		
		sList=DBJ.studentDetailslistByAddNo(addmissionNumber, schoolid, conn);
		schinfo = DBM.fullSchoolInfo(schoolid, conn);
		showPay = false;
		if(schinfo.getPg_type().equalsIgnoreCase("paytm") 
				|| schinfo.getPg_type().equalsIgnoreCase("razorpay") 
				|| schinfo.getPg_type().equalsIgnoreCase("cashfree"))
		{
			showPay = true;
		}
		
		fathersPhoneStr = String.valueOf(sList.getFathersPhone());
		
		if(schoolid.equals("251")||schoolid.equals("252"))
		{

			list = new ArrayList<>();
			int month = 0;
			String[] feemonths= {"4","6","8","9","11","12","1","3","-1"};
			String[] allfeemonths= {"April","May-June","Jul-Aug","September","Oct-Nov","December","January","Feb-Mar","Previous_Fee"};
			String date=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			String preSession=DBM.selectedSessionDetails(schoolid,conn);
			String studentStatus = sList.getStudentStatus();
			String connsessioncategory = sList.getConcession();
			ArrayList<FeeInfo> classFeeList;
			new ArrayList<>();


			//JSONArray arr=new JSONArray();
			String[] session=preSession.split("-");
			double previousDueAmount=0;
			for(int i=0;i<feemonths.length;i++)
			{
				double dueAmount = 0,actuladueAmount=0;
				String findDate1="";
				if(Integer.parseInt(feemonths[i])>3)
				{
					findDate1="01/"+feemonths[i]+"/"+session[0];
				}
				else
				{
					findDate1="01/"+feemonths[i]+"/"+session[1];
				}


				if(i > 4)
				{
					month = Integer.parseInt(feemonths[i])+12;
				}
				else
				{
					month = Integer.parseInt(feemonths[i]);
				}


				Date cDate=null;
				try {
					new SimpleDateFormat("dd/MM/yyyy").parse(findDate1);
					cDate=new SimpleDateFormat("dd/MM/yyyy").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if(i==8)
				{  
				classFeeList=new ArrayList<>();
				classFeeList = DBM.addPreviousFee(schoolid,classFeeList, addmissionNumber, conn);

				for(FeeInfo lss:classFeeList)
				{
					if(lss.getFeeId().equals("-1"))
					{
						int mainamount=lss.getFeeAmount();
						int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, lss.getFeeId(),feemonths[i],conn);
						int dAmount=mainamount-totalpaidamount;
						if(dAmount>0)
						{
							dueAmount += dAmount;
						}
					}


				}

				}
				else
				{

					int count=1;
					if(feemonths[i].equals("6")||feemonths[i].equals("8")||feemonths[i].equals("11")||feemonths[i].equals("3"))
					{
						count=2;
					}
	                  ArrayList<StudentInfo> studentList = DBM.searchStudentListForDueFeeReportForMasterForFees(schoolid,addmissionNumber, Integer.parseInt(feemonths[i]),
							preSession, conn, "feeCollection",count);

					HashMap<String, String> map = (HashMap<String, String>) studentList.get(0).getFeesMap();

					classFeeList = DBM.classFeesForStudent(schoolid,sList.getClassId(), preSession, studentStatus, connsessioncategory,
							conn);

					for (FeeInfo ls : classFeeList) {
						if (!ls.getFeeId().equals("-2") && !ls.getFeeId().equals("-3") && !ls.getFeeId().equals("-4")) {
							int mainamount=Integer.parseInt(map.get(ls.getFeeName()));
							int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, ls.getFeeId(),feemonths[i],conn);
							int dAmount=mainamount-totalpaidamount;
							if(dAmount>0)
							{
								dueAmount += dAmount;
							}


						}


					}

				}
				actuladueAmount=dueAmount-previousDueAmount;

				//JSONObject obj = new JSONObject();
				FeeInfo ff = new FeeInfo();
				ff.setAmount(actuladueAmount);
				ff.setFeeMonth(allfeemonths[i]);
				ff.setFeeMonthInt(Integer.parseInt(feemonths[i]));
				//		 obj.put("amount",String.valueOf(actuladueAmount));
				//		 obj.put("Fee_month",allfeemonths[i]);
				//(actuladueAmount+"................"+waveOffLateFee);

				int latefee=0;
				if(actuladueAmount>0)
				{
					if(waveOffLateFee==false)
					{
						if(i==0)
						{
							try {


								Date duedate=new SimpleDateFormat("dd/MM/yyyy").parse("30/04/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								//ff.setStdate(new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								if(duedate.before(cDate) && month>=4)
								{
									latefee=200;
								}
							} catch (ParseException e) {
								
								e.printStackTrace();
							}
							int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, "-2",feemonths[i],conn);
							int l=latefee-totalpaidamount;
							if(l>0)
							{
								latefee=l;
							}
							else
							{
								latefee=0;
							}

						}
						else if(i==1)
						{
							try {
								Date duedate=new SimpleDateFormat("dd/MM/yyyy").parse("10/05/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								//ff.setStdate(new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								Date duedate1=new SimpleDateFormat("dd/MM/yyyy").parse("15/05/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));

								if(duedate.before(cDate)&&!cDate.after(duedate1) && month>=5)
								{
									latefee=100;
								}
								else if(cDate.after(duedate1)  && month>=5)
								{
									latefee=200;
								}
							} catch (ParseException e) {
								
								e.printStackTrace();
							}
							int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, "-2",feemonths[i],conn);
							int l=latefee-totalpaidamount;
							if(l>0)
							{
								latefee=l;
							}
							else
							{
								latefee=0;
							}
						}
						else if(i==2)
						{
							try {
								Date duedate=new SimpleDateFormat("dd/MM/yyyy").parse("10/07/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								//ff.setStdate(new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								Date duedate1=new SimpleDateFormat("dd/MM/yyyy").parse("15/07/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));

								if(duedate.before(cDate)&&!cDate.after(duedate1) && month>=7)
								{
									latefee=100;
								}
								else if(cDate.after(duedate1) && month>=7)
								{
									latefee=200;
								}
							} catch (ParseException e) {
								
								e.printStackTrace();
							}

							int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, "-2",feemonths[i],conn);
							int l=latefee-totalpaidamount;
							if(l>0)
							{
								latefee=l;
							}
							else
							{
								latefee=0;
							}
						}
						else if(i==3)
						{
							try {
								Date duedate=new SimpleDateFormat("dd/MM/yyyy").parse("10/09/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								//ff.setStdate(new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								Date duedate1=new SimpleDateFormat("dd/MM/yyyy").parse("15/09/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));

								if(duedate.before(cDate)&&!cDate.after(duedate1) && month>=9)
								{
									latefee=100;
								}
								else if(cDate.after(duedate1) && month>=9)
								{
									latefee=200;
								}
							} catch (ParseException e) {
								
								e.printStackTrace();
							}

							int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, "-2",feemonths[i],conn);
							int l=latefee-totalpaidamount;
							if(l>0)
							{
								latefee=l;
							}
							else
							{
								latefee=0;
							}
						}
						else if(i==4)
						{
							try {
								Date duedate=new SimpleDateFormat("dd/MM/yyyy").parse("10/10/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								//ff.setStdate(new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								Date duedate1=new SimpleDateFormat("dd/MM/yyyy").parse("15/10/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));

								if(duedate.before(cDate)&&!cDate.after(duedate1) && month>=10)
								{
									latefee=100;
								}
								else if(cDate.after(duedate1) && month>=10)
								{
									latefee=200;
								}
							} catch (ParseException e) {
								
								e.printStackTrace();
							}

							int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, "-2",feemonths[i],conn);
							int l=latefee-totalpaidamount;
							if(l>0)
							{
								latefee=l;
							}
							else
							{
								latefee=0;
							}
						}
						else if(i==5)
						{
							try {
								Date duedate=new SimpleDateFormat("dd/MM/yyyy").parse("10/12/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								//ff.setStdate(new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								Date duedate1=new SimpleDateFormat("dd/MM/yyyy").parse("15/12/"+session[0]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));

								//(duedate+"-----------------"+duedate1+"------------"+month+"------------"+cDate);
								if(duedate.before(cDate)&&!cDate.after(duedate1) && month>=12)
								{
									latefee=100;
								}
								else if(cDate.after(duedate1) && month>=12)
								{
									latefee=200;
								}
							} catch (ParseException e) {
								
								e.printStackTrace();
							}
							//("late"+latefee);
							int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, "-2",feemonths[i],conn);
							int l=latefee-totalpaidamount;
							if(l>0)
							{
								latefee=l;
							}
							else
							{
								latefee=0;
							}

							//("DEC"+latefee);
						}
						else if(i==6)
						{
							try {
								Date duedate=new SimpleDateFormat("dd/MM/yyyy").parse("10/01/"+session[1]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								//ff.setStdate(new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								Date duedate1=new SimpleDateFormat("dd/MM/yyyy").parse("15/01/"+session[1]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));

								if(duedate.before(cDate)&&!cDate.after(duedate1) && month>=13)
								{
									latefee=100;
								}
								else if(cDate.after(duedate1) && month>=13)
								{
									latefee=200;
								}
							} catch (ParseException e) {
								
								e.printStackTrace();
							}

							int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, "-2",feemonths[i],conn);
							int l=latefee-totalpaidamount;
							if(l>0)
							{
								latefee=l;
							}
							else
							{
								latefee=0;
							}
						}
						else if(i==7)
						{
							try {
								Date duedate=new SimpleDateFormat("dd/MM/yyyy").parse("10/02/"+session[1]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								//ff.setStdate(new SimpleDateFormat("dd/MM/yyyy").format(duedate));
								Date duedate1=new SimpleDateFormat("dd/MM/yyyy").parse("15/02/"+session[1]);
								//obj.put("dueDate",new SimpleDateFormat("dd/MM/yyyy").format(duedate));

								if(duedate.before(cDate)&&!cDate.after(duedate1) && month>=14)
								{
									latefee=100;
								}
								else if(cDate.after(duedate1) && month>=14)
								{
									latefee=200;
								}
							} catch (ParseException e) {
								
								e.printStackTrace();
							}

							int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, "-2",feemonths[i],conn);
							int l=latefee-totalpaidamount;

							if(l>0)
							{
								latefee=l;
							}
							else
							{
								latefee=0;
							}
						}

					}
					//(latefee);

					totalLateFee += latefee;

					//ff.setLateFee(latefee);
					ff.setTotalFee(ff.getAmount()+latefee);
					list.add(ff);
				}


			}
		}
		else
		{
			try {
				lateFeeCalculation();
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		
		searchStudentByName();
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void duplicateFee()
	{
		HttpSession rr=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		rr.setAttribute("selectedStudent", selectedstudent);
		rr.setAttribute("type1", "duplicate");
		rr.setAttribute("chaqueDate", selectedstudent.getChallanDate());
		rr.setAttribute("paymentmode", selectedstudent.getPaymentmode());
		rr.setAttribute("bankname", selectedstudent.getBankname());
		rr.setAttribute("chequeno",selectedstudent.getChequenumber());
		rr.setAttribute("remark", selectedstudent.getFeeRemark());
		rr.setAttribute("type1","duplicate");
		rr.setAttribute("receiptNumber", String.valueOf(selectedstudent.getReciptno()));
		ArrayList<FeeInfo> selectedFees=new ArrayList<>();
		int i=1;
		Connection conn=DataBaseConnection.javaConnection();
		
		DatabaseMethods1 obj=new DatabaseMethods1();
		ArrayList<Feecollectionc>feesSelected=obj.studetFeeCollectionByRecipietNo(selectedstudent.getStudentid(),selectedstudent.getReciptno(),selectedstudent.getSchid(),conn);
		
		
		
		

		for(Feecollectionc ff:feesSelected)
		{
			/*String fee=selectedstudent.getAllFess().get(ff.getFeeId());
			String disc=selectedstudent.getAllDiscount().get(ff.getFeeId());
			String totalAmt=selectedstudent.getAllTotalAmount().get(ff.getFeeId());
			if(fee==null)
			{

			}
			else
			{*/

				FeeInfo info=new FeeInfo();
				info.setSno(i);
				info.setFeeName(ff.getFeeName());
				info.setPayAmount(ff.getFeeamunt());
				info.setPayDiscount(ff.getDiscount());
				info.setDueamount(String.valueOf(ff.getTotalAmount()));

				selectedFees.add(info);
				i=i+1;
			/*}*/
		}

		
		if(feesSelected.get(0).getIntallment().equals(""))
		{
			rr.setAttribute("feeupto", selectedstudent.getDueDateStr());
			
		}
		else
		{
			rr.setAttribute("feeupto",feesSelected.get(0).getIntallment() );
			
		}

		rr.setAttribute("selectedFee", selectedFees);
		rr.setAttribute("receiptDate", selectedstudent.getFeedate());
		PrimeFaces.current().executeScript("window.open('FeeReceiptParentCopyOnly.xhtml')");
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public int lateFeeCalculation() throws ParseException
	{

		Connection conn= DataBaseConnection.javaConnection();
		list = new ArrayList<>();
		String addmissionNumber=sList.getAddNumber();
		schoolid = sList.getSchid();
		FeeDynamicList lateFees=DBM.getlatefeecalculation(schoolid,conn);
		String date=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		String preSession=DBM.selectedSessionDetails(schoolid,conn);
		StudentInfo sList=DBJ.studentDetailslistByAddNo(addmissionNumber, schoolid, conn);
		ArrayList<FeeDynamicList>feelist;
		if((schoolid.equals("287") && Integer.parseInt(sList.getClassId())<13 ) ||(!schoolid.equals("287")))
		{
			feelist=DBM.getAllInstallment(schoolid,conn);
					
		}
		else
		{
			feelist=DBM.getAllInstallmentforAjmani(schoolid,conn);
			
		}
		
		String studentStatus = sList.getStudentStatus();
		String connsessioncategory = sList.getConcession();
		ArrayList<FeeInfo> classFeeList;
		new ArrayList<>();
		//JSONArray arr=new JSONArray();
		String[] session=preSession.split("-");
		double previousDueAmount=0; int totalLateFee=0;
		for(int i=0;i<feelist.size();i++)
		{
			

			Date findDate = null;
			Date cDate = null;
			Date dueDate = null;
			try {
				findDate = feelist.get(i).getFee_due_date();
				cDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			/*int count=1;
			if(feemonths[i].equals("6")||feemonths[i].equals("8")||feemonths[i].equals("11")||feemonths[i].equals("3"))
			{
				count=2;
			}*/

			double dueAmount = 0,actuladueAmount=0;
			
			if(feelist.get(i).getInsatllmentName().equalsIgnoreCase("Previous_Fee"))
			{
				  
				classFeeList=new ArrayList<>();
			classFeeList = DBM.addPreviousFee(schoolid,classFeeList, addmissionNumber, conn);

			for(FeeInfo lss:classFeeList)
			{
				if(lss.getFeeId().equals("-1"))
				{
					int mainamount=lss.getFeeAmount();
					int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, lss.getFeeId(),feelist.get(i).getInsatllmentValue(),conn);
					int dAmount=mainamount-totalpaidamount;
					if(dAmount>0)
					{
						dueAmount += dAmount;
					}
				}


			}

				
			}
			else
			{
				ArrayList<StudentInfo> studentList = DBM.searchStudentListForDueFeeReportForMasterForStkabeer(schoolid,addmissionNumber, Integer.parseInt(feelist.get(i).getInsatllmentValue()),
						preSession, conn, "feeCollection",Integer.parseInt(feelist.get(i).getMonth()),feelist.get(i).getInstallment_Month_Include());

				HashMap<String, String> map = (HashMap<String, String>) studentList.get(0).getFeesMap();

				classFeeList = DBM.classFeesForStudent11(schoolid,sList.getClassId(), preSession, studentStatus, connsessioncategory,
						conn);
				//classFeeList = DBM.addPreviousFee(schoolid,classFeeList, addmissionNumber, conn);
				
				for (FeeInfo ls : classFeeList) {
					if (!ls.getFeeId().equals("-2") && !ls.getFeeId().equals("-3") && !ls.getFeeId().equals("-4")) {
						int mainamount=Integer.parseInt(map.get(ls.getFeeName()));
						if(Integer.parseInt(ls.getTax_percn())>0)
						{
							mainamount=mainamount+((mainamount*Integer.parseInt(ls.getTax_percn()))/100);
						}

						int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, ls.getFeeId(),feelist.get(i).getInsatllmentValue(),conn);
						int dAmount=mainamount-totalpaidamount;
						if(dAmount>0)
						{
							dueAmount += dAmount;
						}
					}
				}
			}



			
			actuladueAmount=dueAmount-previousDueAmount;

			//JSONObject obj = new JSONObject();
			FeeInfo ff = new FeeInfo();
			ff.setAmount(actuladueAmount);
			ff.setFeeMonth(feelist.get(i).getInsatllmentName());
			ff.setFeeMonthInt(Integer.parseInt(feelist.get(i).getInsatllmentValue()));
			int latefee=0;

			if(actuladueAmount>0 )
			{
				
				
				long diff = cDate.getTime() - findDate.getTime();
				int fff = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				if(lateFees.getType().equals("static")) {
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
				}else {
					String dDate = lateFees.getFee_due_days()+"/"+(findDate.getMonth()+1)+"/"+(findDate.getYear()+1900);
					dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dDate);
					long difference = cDate.getTime() - dueDate.getTime();
					int diffDays = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
					if(diffDays>0) {
						int lateF = diffDays * Integer.valueOf(lateFees.getLate_fee());
						latefee = lateF;
					}
				}
				
				
//                long diff =  cDate.getTime()-findDate.getTime() ;
//				int fff =(int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
//				if(lateFees.getFee_due_days().equals("")) {
//					latefee=0;
//				}
//				else
//				{
//					String[] feeduedays=lateFees.getFee_due_days().split(",");
//					String[] latefeeamount=lateFees.getLate_fee().split(",");
//					if(feeduedays.length==1)
//					{
//						if(Integer.parseInt(feeduedays[0])>=fff)
//						{
//							latefee=Integer.parseInt(latefeeamount[0]);
//						}
//
//					}
//					else if(feeduedays.length==2)
//					{
//						int ii=Integer.parseInt(feeduedays[0]);
//						int j=Integer.parseInt(feeduedays[1]);
//						if(ii <= fff && fff < j)
//						{
//							latefee=Integer.parseInt(latefeeamount[0]);
//						}
//						else if(fff >= j)
//						{
//							latefee=Integer.parseInt(latefeeamount[1]);
//						}
//						else {
//							latefee=0;
//						}
//					}
//
//
//				}

				int totalpaidamount = DBM.FeePaidRecordForCheck(sList.getSchid(),sList, preSession, "-2",feelist.get(i).getInsatllmentValue(),conn);
				int l=latefee-totalpaidamount;
				if(l>0)
				{
					latefee=l;
				}
				else
				{
					latefee=0;
				}

			}


			
			

			if(actuladueAmount>0)
			{
				totalLateFee += latefee;

				ff.setLateFee(latefee);
				ff.setTotalFee(ff.getAmount()+latefee);
				list.add(ff);
			}

			


		}
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalLateFee;
	}
	

	/*public String sendRequest(String url) {
        String result = "";
        try {
            HttpClient client = new DefaultHttpClient();
            HttpParams httpParameters = client.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000000);
            HttpConnectionParams.setSoTimeout(httpParameters, 5000000);
            HttpConnectionParams.setTcpNoDelay(httpParameters, true);
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            InputStream ips = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String s;
            while (true) {
                s = buf.readLine();
                if (s == null || s.length() == 0)
                    break;
                sb.append(s);
            }
            buf.close();
            ips.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

	public void parseFromJSONResponse(String respo)
    {
        JSONArray myjson;
        JSONObject obj;
        list = new ArrayList<>();
        try
        {
        	////(respo);
            myjson = new JSONArray(respo);
            if(myjson.length()>0)
            {
            		//(myjson);
            		for(int i=0;i<myjson.length();i++)
            		{
            			obj = myjson.getJSONObject(i);
            			FeeInfo ff = new FeeInfo();
            			ff.setSno(i+1);
            			ff.setFeeMonth(obj.get("Fee_month").toString());
            			ff.setAmount(Double.valueOf(obj.get("amount").toString()));
            			ff.setLateFee(Double.valueOf(obj.get("latefee").toString()));
            			ff.setStdate(obj.get("dueDate").toString());
            			ff.setTotalFee(ff.getAmount()+ff.getLateFee());

            			list.add(ff);
            		}


            }
            else
            {
            		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No Record Found"));
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

	public void selectFee()
	{
		selectedList = new ArrayList<>();
		totalAmount=totalLateFee=totalFee=0;
		for(FeeInfo ff : list)
		{
			if(ff.isSelect())
			{
				selectedList.add(ff);
			}
		}

		if(selectedList.size()>0)
		{
			for(FeeInfo fi : selectedList)
			{
				totalAmount += fi.getAmount();
				totalLateFee += fi.getLateFee();
				totalFee += fi.getTotalFee();
			}
		}
		else
		{
			totalAmount=totalLateFee=totalFee=0;
		}

	}

	public void searchStudentByName()
	{
		Connection conn = DataBaseConnection.javaConnection();

		dailyfee=new ArrayList<>();
		schoolid=sList.getSchid();
		String id=sList.getAddNumber();
		dailyfee=DBM.getstudentWisFeesCollection(schoolid, id, DBM.selectedSessionDetails(schoolid,conn), conn);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editFee()
	{
		feesSelected=new ArrayList<>();
		Connection conn = DataBaseConnection.javaConnection();
		feesSelected=DBM.studetFeeCollectionByRecipietNo(sList.getAddNumber(),selectedstudent.getReciptno(),sList.getSchid(),conn);
		changeDate=feesSelected.get(0).getFeedate();
		double totalPAmount=0,totalPdiscount=0;
		for(Feecollectionc ls:feesSelected)
		{
			totalPAmount +=ls.getFeeamunt();
			totalPdiscount +=ls.getDiscount();
		}
		totalPayAmunt=String.valueOf(totalPAmount);
		totalDiscountAmount=String.valueOf(totalPdiscount);
		//(feesSelected.size());
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void pay()
	{
		if(selectedList.size()>0)
		{
			HttpSession ss= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			ss.setAttribute("onlist", selectedList);
			 // // system.out.println("inside pay....."+ss.getAttribute("selectedSession"));
			orderid="ORDS"+new SimpleDateFormat("ydMhms").format(new Date());
			try {
					if(schinfo.getPg_type().equalsIgnoreCase("paytm"))
					{
						FacesContext.getCurrentInstance().getExternalContext().redirect("https://www.chalkboxpro.in/PRO/pgRedirect.jsp?ORDER_ID="+orderid+"&CUST_ID="+addmissionNumber+"&INDUSTRY_TYPE_ID=PrivateEducation&CHANNEL_ID=WEB&TXN_AMOUNT="+totalFee);
//						FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/Chalkbox/pgRedirect.jsp?ORDER_ID="+ord+"&CUST_ID="+addmissionNumber+"&INDUSTRY_TYPE_ID=PrivateEducation&CHANNEL_ID=WEB&TXN_AMOUNT="+totalFee);
					}
					else if(schinfo.getPg_type().equalsIgnoreCase("razorpay"))
					{
						String amt = new DecimalFormat("##").format(totalFee*100);
						ss.setAttribute("rzpAmount", amt);
						rzpPay(amt,orderid);
						//FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8081/CBX-Code/rzpRedirect.jsp?receipt="+ord+"&amount="+amt+"&studentId="+addmissionNumber);
						//  FacesContext.getCurrentInstance().getExternalContext().redirect("https://www.chalkboxpro.in/PRO/rzpRedirect.jsp?receipt="+ord+"&amount="+amt+"&studentId="+addmissionNumber);
					}
					else if(schinfo.getPg_type().equalsIgnoreCase("cashfree"))
					{
						String amt = new DecimalFormat("##").format(totalFee);
						ss.setAttribute("cfrAmount", totalFee);
						cfrPay(amt,orderid);
					}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
		else
		{

		}

	}
	
	public void payQR()
	{
		statusTimer = false;
		cfrOrderToken = "";
		if(selectedList.size()>0)
		{
			HttpSession ss= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			ss.setAttribute("onlist", selectedList);
			 // // system.out.println("inside pay....."+ss.getAttribute("selectedSession"));
			orderid="ORDS"+new SimpleDateFormat("ydMhms").format(new Date());
			String amt = new DecimalFormat("##").format(totalFee);
			ss.setAttribute("cfrAmount", totalFee);
			cfrPay(amt,orderid);
			if(cfrOrderToken==null || cfrOrderToken.equals(""))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Order Token!", "Order Token is Invalid, Please Try Again!");
		        PrimeFaces.current().dialog().showMessageDynamic(message);
			}
			else
			{
				String status = onlineFeeJson();
				if(status.equals("1"))
				{
					generateQR();
					statusTimer = true;
				}
				else
				{
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Oops!", "Something Went Wrong, Please Try Again!");
			        PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}
		}
		else
		{

		}

	}
	
	public void rzpPay(String amt, String ord)
	{
		Order order = null;
		RazorpayClient razorpayClient = null;
		try {
				razorpayClient = new RazorpayClient(schinfo.getRzp_key(), schinfo.getRzp_key_secret());
			
				JSONObject orderRequest = new JSONObject();
				orderRequest.put("amount", Integer.valueOf(amt)); // amount in the smallest currency unit
			  	orderRequest.put("currency", "INR");
			  	orderRequest.put("receipt", ord);

			  	order = razorpayClient.Orders.create(orderRequest);
			  	rzpOrderId = order.get("id");
			  	rzpAmount = amt;
		} catch (RazorpayException e) {
		  // system.out.println(e.getMessage());
		}
	}

	public void paymentListener() throws IOException
	{
		HttpSession ss= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		ss.setAttribute("rzpPaymentId", rzpPayment);
		ss.setAttribute("rzpOrderId", rzpOrder);
		ss.setAttribute("rzpSignature", rzpSignature);
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("https://www.chalkboxpro.in/PRO/rzpResponse.jsp");
		
	}
	
	public void cfrPay(String amt, String ord)
	{
		try
		{
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/json");

			RequestBody body = RequestBody.create(mediaType, 
					"{\"customer_details\":{\"customer_id\":\""+addmissionNumber+"\","
							+ "\"customer_name\":\""+sList.getFname()+" - "+addmissionNumber+"\","
							+ "\"customer_phone\":\""+fathersPhoneStr+"\","
									+ "\"customer_email\":\""+sList.getFatherEmail()+"\"},"
											+ "\"order_meta\":{\"notify_url\":\"https://www.chalkboxpro.in/PRO/secapiure/webhook.xhtml\"},"
											+ "\"order_id\":\""+ord+"\","
													+ "\"order_amount\":"+totalFee+","
															+ "\"order_currency\":\"INR\"}");

			Request request = new Request.Builder()
			  .url("https://api.cashfree.com/pg/orders")
			  .post(body)
			  .addHeader("Accept", "application/json")
			  .addHeader("x-client-id", schinfo.getPaytm_mid())
			  .addHeader("x-client-secret", schinfo.getPaytm_marchent_key())
			  .addHeader("x-api-version", "2022-01-01")
			  .addHeader("Content-Type", "application/json")
			  .build();
	
			Response response = client.newCall(request).execute();
			
			ResponseBody respBody = response.body();
			String strResponse = respBody.string();
			System.out.println(strResponse);
			JSONObject robj = new JSONObject(strResponse);
			try
			{
				String code = robj.getString("code");
				if(code==null)
				{
					cfrOrderToken = robj.getString("order_token");
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, robj.getString("message"), robj.getString("type")));
				}
			}
			catch (Exception e) {
        		if(e.getMessage().equalsIgnoreCase("JSONObject[\"code\"] not found."))
        		{
        			cfrOrderToken = robj.getString("order_token");
        		}
        		else
        		{
        			e.printStackTrace();
        		}
        		
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void generateQR()
	{
		try
		{
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/json");

			RequestBody body = RequestBody.create(mediaType, 
					"{"
					+ "\"payment_method\":{"
					+ "\"upi\":{"
					+ "\"channel\":\"qrcode\""
					+ "}"
					+ "},"
					+ "\"order_token\":\""+cfrOrderToken+"\""
							+ "}");

			Request request = new Request.Builder()
			  .url("https://api.cashfree.com/pg/orders/pay")
			  .post(body)
			  .addHeader("Accept", "application/json")
			  .addHeader("x-client-id", schinfo.getPaytm_mid())
			  .addHeader("x-client-secret", schinfo.getPaytm_marchent_key())
			  .addHeader("x-api-version", "2022-01-01")
			  .addHeader("Content-Type", "application/json")
			  .build();
	
			Response response = client.newCall(request).execute();
			
			ResponseBody respBody = response.body();
			String strResponse = respBody.string();
			System.out.println(strResponse);
			JSONObject robj = new JSONObject(strResponse);
			try
			{
				String code = robj.getString("code");
				if(code==null)
				{
					JSONObject data = robj.getJSONObject("data");
					JSONObject payload = data.getJSONObject("payload");
					qrCode = payload.getString("qrcode");
					
					PrimeFaces.current().executeInitScript("PF('qrDia').show();");
					PrimeFaces.current().ajax().update("qrForm");
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, robj.getString("message"), robj.getString("type")));
				}
			}
			catch (Exception e) {
        		if(e.getMessage().equalsIgnoreCase("JSONObject[\"code\"] not found."))
        		{
        			JSONObject data = robj.getJSONObject("data");
					JSONObject payload = data.getJSONObject("payload");
					qrCode = payload.getString("qrcode");
					
					PrimeFaces.current().executeInitScript("PF('qrDia').show();");
					PrimeFaces.current().ajax().update("qrForm");
        		}
        		else
        		{
        			e.printStackTrace();
        		}
        		
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cfrPaymentListener() throws IOException
	{
		cfrOrder = orderid;
		HttpSession ss= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		ss.setAttribute("cfrOrderId", cfrOrder);
		String session1 = (String) ss.getAttribute("selectedSession");
		//FacesContext.getCurrentInstance().getExternalContext().redirect("https://www.chalkboxpro.in/PRO/rzpResponse.jsp");
		PrimeFaces.current().executeScript("PF('cfrPayDlg').hide()");
		PrimeFaces.current().ajax().update("cfrPayForm");
		
		checkOrdStatus();
		if(cfrStatus.equalsIgnoreCase("PAID"))
		{
			checkCfrStatus();
			new test().values(cfrPayment, cfrOrder,selectedList,addmissionNumber,schoolid,session1,"active");
	        
			statusImage = "paymentsuccess1.png";
			statusMsg = "Voila! your payment is successfully recorded.";
			subStatusMsg = "You can access your fee receipt from your ChalkBox School app.";
			PrimeFaces.current().executeScript("PF('successQR').show()");
			PrimeFaces.current().ajax().update("successQR");
			
			//Send Notificatoin
			sendNotifications(cfrOrder);
		}
		else if(cfrStatus.equalsIgnoreCase("ACTIVE"))
		{
			cfrPayment="";
			new test().values(cfrPayment, cfrOrder,selectedList,addmissionNumber,schoolid,session1,"Payment_In_Process");
			statusImage = "paymentfailed.png";
			statusMsg = "If you've made the payment, please be patient, we are processing your payment. Don't worry, the amount will be refunded if deducted from your bank account, in case of payment failure. \nPLEASE DO NOT MAKE ANOTHER PAYMENT BEFORE 1 HOUR.";
			
			PrimeFaces.current().executeScript("PF('prDlg').show()");
			PrimeFaces.current().ajax().update("processForm");
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Payment Processing!", " Please Wait, we are processing your payment. Don't worry, the amount will be refunded if deducted from your bank account, in case of payment failure. \nPLEASE DO NOT MAKE ANOTHER PAYMENT BEFORE 1 HOUR.");
//	        PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		else
		{
			statusImage = "paymentfailed.png";
			statusMsg = " Sorry, your payment has been failed. Don't worry, the amount will be refunded if deducted from your bank account.";
			PrimeFaces.current().executeScript("PF('successQR').show()");
			PrimeFaces.current().ajax().update("successQR");
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Payment Failed!", " Sorry, your payment has been failed. Don't worry, the amount will be refunded if deducted from your bank account.");
//	        PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
	
	int k = 0;
	
	public void checkStatus() throws MalformedURLException
	{
		
		number+=3;
		
		System.out.println("checkStatus");
		cfrOrder = orderid;
		HttpSession ss= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		ss.setAttribute("cfrOrderId", cfrOrder);
		String session1 = (String) ss.getAttribute("selectedSession");
		//FacesContext.getCurrentInstance().getExternalContext().redirect("https://www.chalkboxpro.in/PRO/rzpResponse.jsp");
		Connection conn = DataBaseConnection.javaConnection();
		String status = DBJ.checkFeeReceiptStatus(cfrOrder, conn);
		
		if(status.equalsIgnoreCase("active"))
		{
			
			k = k + 1;
			
			if(k == 1) {
				//get order deyails
				String details = new DatabaseMethodkiosk().getPAymentDetailsWithOrderId(cfrOrder,schoolid,conn);
				
				System.out.println(details);
				if(!details.equals("")) {
					String detailsArr[] = details.split("-");
							String num = detailsArr[1].trim();
							String amount = detailsArr[0].trim();
							String feeRemark = detailsArr[2].trim();
							String typeMessage = "Dear Parent,\n" + sList.getFname() + " (" + sList.getClassName() + ")"
									+ " Fee Receipt No. " + num + " Rs." + amount + " Via Payment Gateway For "
									+ feeRemark + "\n" + schinfo.getSmsSchoolName();
							
							System.out.println(typeMessage);
							
							DBM.notification(schoolid, "Fees", typeMessage,
									String.valueOf(sList.getFathersPhone()) + "-" + addmissionNumber + "-" + schoolid, conn);
							DBM.notification(schoolid, "Fees", typeMessage,
									String.valueOf(sList.getMothersPhone()) + "-" + addmissionNumber + "-" + schoolid, conn);
				}
			}
			
			
			statusTimer = false;
			statusImage = "paymentsuccess1.png";
			statusAudio = "paymentsuccess.mp3";
			statusMsg = "Voila! your payment is successfully recorded.";
			subStatusMsg = "You can access your fee receipt from your ChalkBox School app.";
			PrimeFaces.current().executeScript("PF('qrDia').hide()");
			PrimeFaces.current().ajax().update("qrForm");
			PrimeFaces.current().executeScript("PF('successQR').show()");
			PrimeFaces.current().ajax().update("successQR");
			
			
			
			//new MP3Player(new URL("http://www.chalkboxpro.in/CBXMASTER/paymentsuccess.mp3")).play();
		}
		else if(status.equalsIgnoreCase("Payment_In_Process") || status.equalsIgnoreCase("pending"))
		{
			if(number>=600)
			{
				statusTimer = false;
				statusImage = "paymentfailed.png";
				statusMsg = "If you've made the payment, please be patient, we are processing your payment. Don't worry, the amount will be refunded if deducted from your bank account, in case of payment failure. \nPLEASE DO NOT MAKE ANOTHER PAYMENT BEFORE 1 HOUR.";
				
				PrimeFaces.current().executeScript("PF('qrDia').hide()");
				PrimeFaces.current().ajax().update("qrForm");
				PrimeFaces.current().executeScript("PF('prDlg').show()");
				PrimeFaces.current().ajax().update("processForm");
//				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Payment Processing!", "If you've made the payment, please be patient, we are processing your payment. Don't worry, the amount will be refunded if deducted from your bank account, in case of payment failure. \nPLEASE DO NOT MAKE ANOTHER PAYMENT BEFORE 1 HOUR.");
//		        PrimeFaces.current().dialog().showMessageDynamic(message);
			}
		}
		else if(status.equalsIgnoreCase("TXN_FAILED") || status.equalsIgnoreCase("TXN_FAILURE"))
		{
			statusTimer = false;
			statusImage = "paymentfailed.png";
			statusAudio = "paymentfail.mp3";
			statusMsg = " Sorry, your payment has been failed. Don't worry, the amount will be refunded if deducted from your bank account.";
			PrimeFaces.current().executeScript("PF('qrDia').hide()");
			PrimeFaces.current().ajax().update("qrForm");
			PrimeFaces.current().executeScript("PF('successQR').show()");
			PrimeFaces.current().ajax().update("successQR");
			//new MP3Player(new URL("http://www.chalkboxpro.in/CBXMASTER/paymentfail.mp3")).play();
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Payment Failed!", " Sorry, your payment has been failed. Don't worry, the amount will be refunded if deducted from your bank account.");
//	        PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		
		
		
		
		
		
	}
	
	
	public void sendNotifications(String orderId) {
		
		Connection conn = DataBaseConnection.javaConnection();
		
		System.out.println("in method");
		System.out.println(orderId);
		
		String details = new DatabaseMethodkiosk().getPAymentDetailsWithOrderId(orderId,schoolid,conn);
		
		System.out.println(details);
		if(!details.equals("")) {
			String detailsArr[] = details.split("-");
					String num = detailsArr[1].trim();
					String amount = detailsArr[0].trim();
					String feeRemark = detailsArr[2].trim();
					String typeMessage = "Dear Parent,\n" + sList.getFname() + " (" + sList.getClassName() + ")"
							+ " Fee Receipt No. " + num + " Rs." + amount + " Via Payment Gateway For "
							+ feeRemark + "\n" + schinfo.getSmsSchoolName();
					
					System.out.println(typeMessage);
					
					DBM.notification(schoolid, "Fees", typeMessage,
							String.valueOf(sList.getFathersPhone()) + "-" + addmissionNumber + "-" + schoolid, conn);
					DBM.notification(schoolid, "Fees", typeMessage,
							String.valueOf(sList.getMothersPhone()) + "-" + addmissionNumber + "-" + schoolid, conn);
		}
		
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkOrdStatus()
	{
		try {
			OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder()
			  .url("https://api.cashfree.com/pg/orders/"+cfrOrder)
			  .get()
			  .addHeader("Accept", "application/json")
			  .addHeader("x-client-id", schinfo.getPaytm_mid())
			  .addHeader("x-client-secret", schinfo.getPaytm_marchent_key())
			  .addHeader("x-api-version", "2022-01-01")
			  .build();

			Response response = client.newCall(request).execute();
			ResponseBody respBody = response.body();
			String strResponse = respBody.string();
			System.out.println(strResponse);
			JSONObject robj = new JSONObject(strResponse);
			cfrStatus = robj.getString("order_status");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkCfrStatus()
	{
		try {
			OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder()
			  .url("https://api.cashfree.com/pg/orders/"+cfrOrder+"/payments")
			  .get()
			  .addHeader("Accept", "application/json")
			  .addHeader("x-client-id", schinfo.getPaytm_mid())
			  .addHeader("x-client-secret", schinfo.getPaytm_marchent_key())
			  .addHeader("x-api-version", "2022-01-01")
			  .build();

			Response response = client.newCall(request).execute();
			ResponseBody respBody = response.body();
			String strResponse = respBody.string();
			System.out.println(strResponse);
			JSONArray jarr = new JSONArray(strResponse);
			if(jarr.length()>0)
			{
				int length = jarr.length();
				int index = 0;
				for(int i=0; i<length; i++)
				{
					index = i;
					String tmpSts = jarr.getJSONObject(i).getString("payment_status");
					if(tmpSts.equalsIgnoreCase("SUCCESS"))
					{
						break;
					}
				}
				
				JSONObject robj = jarr.getJSONObject(index);
				cfrPayment = String.valueOf(robj.getInt("cf_payment_id"));
				cfrBankRef = robj.getString("bank_reference");
				cfrPaymentStatus = robj.getString("payment_status");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refresh() throws IOException
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.redirect("studentOnlineFee.xhtml");
	}
	
	public void goToHome() throws IOException
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.redirect("homePage.xhtml");
	}
	
	public void logOut() throws IOException
	{
		PrimeFaces.current().executeScript("window.location.assign('https://feekiosk.chalkboxpro.in/kiosk/feeKiosk.xhtml')");
	}
	
	
	public String onlineFeeJson()
	{
		String status = "";
		String selectedMonths = "";
		for(FeeInfo ls:selectedList)
		{
			if(selectedMonths.equals(""))
			{
				selectedMonths=String.valueOf(ls.getFeeMonthInt());
			}
			else
			{
				selectedMonths = selectedMonths + "," + ls.getFeeMonthInt();
			}
		}
		
		String user = "Self (Fee Kiosk)";
		String params = "schid="+schoolid+"&studentId="+addmissionNumber+"&orderid="+orderid+
				"&month="+URLEncoder.encode(selectedMonths)+"&status=pending&type=new&user="+URLEncoder.encode(user);
		
		try {
			OkHttpClient client = new OkHttpClient().newBuilder().build();
			//MediaType mediaType = MediaType.parse("text/plain");
			//RequestBody body = RequestBody.create(mediaType, "");
			Request request = new Request.Builder()
			  .url("http://www.chalkboxpro.in/PRO/secapiure/onlineFeePaymentJson.xhtml?"+params)
			  .get()
			  .addHeader("User-Agent", "okhttp/")
			  .build();
			Response response = client.newCall(request).execute();
			ResponseBody respBody = response.body();
			String strResponse = respBody.string();
			System.out.println(strResponse);
			JSONArray jarr = new JSONArray(strResponse);
			JSONObject robj = jarr.getJSONObject(0);
			status = robj.getString("status");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

	public ArrayList<FeeInfo> getList() {
		return list;
	}

	public void setList(ArrayList<FeeInfo> list) {
		this.list = list;
	}

	public ArrayList<FeeInfo> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(ArrayList<FeeInfo> selectedList) {
		this.selectedList = selectedList;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public double getTotalLateFee() {
		return totalLateFee;
	}

	public void setTotalLateFee(double totalLateFee) {
		this.totalLateFee = totalLateFee;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ArrayList<DailyFeeCollectionBean> getDailyfee() {
		return dailyfee;
	}

	public void setDailyfee(ArrayList<DailyFeeCollectionBean> dailyfee) {
		this.dailyfee = dailyfee;
	}

	public StudentInfo getsList() {
		return sList;
	}

	public void setsList(StudentInfo sList) {
		this.sList = sList;
	}

	public DailyFeeCollectionBean getSelectedstudent() {
		return selectedstudent;
	}

	public void setSelectedstudent(DailyFeeCollectionBean selectedstudent) {
		this.selectedstudent = selectedstudent;
	}

	public String getTotalPayAmunt() {
		return totalPayAmunt;
	}

	public void setTotalPayAmunt(String totalPayAmunt) {
		this.totalPayAmunt = totalPayAmunt;
	}

	public String getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(String totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public ArrayList<Feecollectionc> getFeesSelected() {
		return feesSelected;
	}

	public void setFeesSelected(ArrayList<Feecollectionc> feesSelected) {
		this.feesSelected = feesSelected;
	}


	public boolean isShowPay() {
		return showPay;
	}


	public void setShowPay(boolean showPay) {
		this.showPay = showPay;
	}


	public SchoolInfoList getSchinfo() {
		return schinfo;
	}


	public void setSchinfo(SchoolInfoList schinfo) {
		this.schinfo = schinfo;
	}


	public String getRzpAmount() {
		return rzpAmount;
	}


	public void setRzpAmount(String rzpAmount) {
		this.rzpAmount = rzpAmount;
	}


	public String getRzpOrderId() {
		return rzpOrderId;
	}


	public void setRzpOrderId(String rzpOrderId) {
		this.rzpOrderId = rzpOrderId;
	}


	public String getFathersPhoneStr() {
		return fathersPhoneStr;
	}


	public void setFathersPhoneStr(String fathersPhoneStr) {
		this.fathersPhoneStr = fathersPhoneStr;
	}


	public String getRzpOrder() {
		return rzpOrder;
	}


	public void setRzpOrder(String rzpOrder) {
		this.rzpOrder = rzpOrder;
	}


	public String getRzpPayment() {
		return rzpPayment;
	}


	public void setRzpPayment(String rzpPayment) {
		this.rzpPayment = rzpPayment;
	}


	public String getRzpSignature() {
		return rzpSignature;
	}


	public void setRzpSignature(String rzpSignature) {
		this.rzpSignature = rzpSignature;
	}


	public String getCfrOrderToken() {
		return cfrOrderToken;
	}


	public void setCfrOrderToken(String cfrOrderToken) {
		this.cfrOrderToken = cfrOrderToken;
	}


	public String getCfrPayment() {
		return cfrPayment;
	}


	public void setCfrPayment(String cfrPayment) {
		this.cfrPayment = cfrPayment;
	}


	public String getCfrOrder() {
		return cfrOrder;
	}


	public void setCfrOrder(String cfrOrder) {
		this.cfrOrder = cfrOrder;
	}


	public String getCfrStatus() {
		return cfrStatus;
	}


	public void setCfrStatus(String cfrStatus) {
		this.cfrStatus = cfrStatus;
	}


	public String getCfrBankRef() {
		return cfrBankRef;
	}


	public void setCfrBankRef(String cfrBankRef) {
		this.cfrBankRef = cfrBankRef;
	}


	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public String getCfrPaymentStatus() {
		return cfrPaymentStatus;
	}


	public void setCfrPaymentStatus(String cfrPaymentStatus) {
		this.cfrPaymentStatus = cfrPaymentStatus;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getQrCode() {
		return qrCode;
	}


	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}


	public boolean isStatusTimer() {
		return statusTimer;
	}


	public void setStatusTimer(boolean statusTimer) {
		this.statusTimer = statusTimer;
	}


	public String getStatusImage() {
		return statusImage;
	}


	public void setStatusImage(String statusImage) {
		this.statusImage = statusImage;
	}


	public String getStatusMsg() {
		return statusMsg;
	}


	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}


	public boolean isShowLoader() {
		return showLoader;
	}


	public void setShowLoader(boolean showLoader) {
		this.showLoader = showLoader;
	}


	public String getStatusAudio() {
		return statusAudio;
	}


	public void setStatusAudio(String statusAudio) {
		this.statusAudio = statusAudio;
	}


	public String getSubStatusMsg() {
		return subStatusMsg;
	}


	public void setSubStatusMsg(String subStatusMsg) {
		this.subStatusMsg = subStatusMsg;
	}
	
	
}
