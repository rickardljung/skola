import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Journal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int nbr = 1;
	
	public String patient;
	private List <Record> listrecords;
	
	public Journal(String patient, String nurse, String doctor,String division,String data){
		this.patient = patient;
		addRecord(nurse, doctor, division, data);
		
		
	}
	
	public Journal(String patient){
		this.patient = patient;
	}
	
	public void addRecord(String nurse, String doctor,String division,String data){
		 if(listrecords == null){
			 listrecords = new ArrayList<Record>();
			 listrecords.add(new Record(nurse, doctor, division, data, String.valueOf(nbr)));
			 nbr++;
		 } else{
			 listrecords.add(new Record(nurse, doctor, division, data, String.valueOf(nbr)));
			 nbr++;
		 }
	 }
	 
//	public Record getRecord(String ){
//		 int i = account.getID();
//	}
	
	public String getPatient(){
		return patient;
	}
	public List<Record> getList() {
		// TODO Auto-generated method stub
		return listrecords;
	}
	
	public Record removeRecord(String recordNumber){
		
		for (int i = 0; i < listrecords.size(); i++) {
			
			if(listrecords.get(i).getRecordNbr().compareTo(recordNumber)==0){
				Record tempRecord = listrecords.remove(i);
				return tempRecord;
			}
			
		}
		return null;
		
	}

	public void addRecord(Record tempRecord) {
		listrecords.add(tempRecord);
		
	}
	

}
