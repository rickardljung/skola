import java.io.Serializable;
import java.util.Date;


public class Record implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String nurse;
	public String doctor;
	private String division;
	private String data;
	private String created;
	private String recordNbr;
	
	public Record(String nurse, String doctor,String division,String data, String nbr){
		this.nurse = nurse;
		this.data = data;
		this.division = division;
		this.doctor = doctor;
		created = new Date().toString();
		this.recordNbr = nbr;
	}
	public String getData(){
		return " created " + created + "; " +data;
	}
	
	public String getNurse(){
		return nurse;
	}
	
	public String getDoctor(){
		return doctor;
	}
	
	public String getDivision(){
		return division;
	}
	
	public String getCreated(){
		return created;
	}
	
	public String getRecordNbr(){
		return recordNbr;
	}
	public void addData(String newData){
		data = data + new Date().toString() +" " + newData;
		
	}
	
}
