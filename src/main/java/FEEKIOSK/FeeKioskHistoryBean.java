package FEEKIOSK;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import schooldata.DailyFeeCollectionBean;
import schooldata.DataBaseConnection;
import schooldata.DatabaseMethods1;
import schooldata.FeeInfo;
import schooldata.Feecollectionc;

@ManagedBean(name = "feeHistory")
@ViewScoped
public class FeeKioskHistoryBean implements Serializable{
	
	StudentInfo studentInfo = new StudentInfo();
	DailyFeeCollectionBean selectedstudent;
	ArrayList<DailyFeeCollectionBean> showFees = new ArrayList<DailyFeeCollectionBean>();
	
	public FeeKioskHistoryBean() {
		HttpSession ss = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);
		ss.setAttribute("portal", "history");	
		studentInfo=(StudentInfo) ss.getAttribute("studentInfo");
		
		showFees = studentInfo.getDailyFees();
		
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

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public ArrayList<DailyFeeCollectionBean> getShowFees() {
		return showFees;
	}

	public void setShowFees(ArrayList<DailyFeeCollectionBean> showFees) {
		this.showFees = showFees;
	}

	public DailyFeeCollectionBean getSelectedstudent() {
		return selectedstudent;
	}

	public void setSelectedstudent(DailyFeeCollectionBean selectedstudent) {
		this.selectedstudent = selectedstudent;
	}
	
	

}
