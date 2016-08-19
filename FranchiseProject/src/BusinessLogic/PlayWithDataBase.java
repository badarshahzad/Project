package BusinessLogic;

import java.util.ArrayList;

import DataBase.sendToDatabase;

public class PlayWithDataBase {

	private sendToDatabase dataBase;
	private String adminTable = "admin";
	private String employeeTable = "employee";
	private String mobileTable = "mobiles";
	private String pricesTable = "prices";
	private String stockTable = "stock";
	private String telenorTable = "telenortable";	

	public PlayWithDataBase(){
		dataBase = new sendToDatabase();
	}

	public void insertEntry(String entryNumber, String date, String name,
			String hlr, String hlrPrice, String hlrProfit, String sim, String simPrice, String simProfit, String card, String cardPrice, String cardProfit, String easyLoad, String easyLoadProfit, String easyPaisa,
			String easyPaisaProfit, String easyPaisaReturn, String cash, String mobileName,
			String numOfMobile, String priceOfMobile, String mobilesProfit, String expenses, String totalAmount, String totalProfit){

		String query = "INSERT INTO "+ telenorTable +" VALUES('" + (entryNumber)
				+ "','" + date + "','" + name + "','" + hlr+"','"+ hlrPrice +"','" + hlrProfit + "','"+ sim
				+ "','" + simPrice+"','"+ simProfit + "','" + card + "','" + cardPrice+ "','" + cardProfit 
				+ "','" + easyLoad+ "','" + easyLoadProfit + "','" + easyPaisa + "','" + easyPaisaProfit+  "','"
				+ easyPaisaReturn + "','" + cash + "','" + mobileName
				+ "','" + numOfMobile + "','" + priceOfMobile + "','" + mobilesProfit + "','" + expenses+"','"
				+ totalAmount + "','"  + totalProfit + "')";
		dataBase.executeQuery(query);
	}

	public String getASpecificRow(String entryNo){
		String query = "SELECT * FROM "+ telenorTable +" WHERE EntryNo="+'"'+entryNo+'"'+";";
		dataBase.getResult(query);
		return dataBase.getARow();
	}

	public String getRowCount(){
		return dataBase.getRow("SELECT * FROM "+ telenorTable +";");
	}

	public String[][] getPrices(){

		return dataBase.getPrices("select * from "+pricesTable+";");
	}

	public void addEmployeeInDataBase(String name, String CNIC, String phoneNumber, String status){

		String query = "INSERT INTO" + employeeTable +" VALUES('"+name+"'"+","+"'"+CNIC+"'"+","+"'"+phoneNumber+"'"+","+"'"+status+"');";
		dataBase.executeQuery(query);
	}

	@SuppressWarnings("rawtypes")
	public ArrayList getEmployeesNames(){
		String query  = "select * from " + employeeTable + ";";
		dataBase.getResult(query);
		return dataBase.getEmployeeName();
	}

	@SuppressWarnings("rawtypes")
	public ArrayList getEntriesRecord(){

		String query = "select * from "+ telenorTable +";";
		dataBase.getResult(query);
		return dataBase.getRecord();
	}

	public void changePassword(String userName, String newPassword){

		String query = "UPDATE "+  adminTable +" set Password="+'"'+newPassword+'"'+" where UserName="+'"'+userName+'"'+";";
		dataBase.executeQuery(query);
	}

	@SuppressWarnings("rawtypes")
	public ArrayList checkUserNameAndPassword(){
		String query = "select * from "+ adminTable +";";
		dataBase.getResult(query);
		return dataBase.getAdminData();
	}

	public void changeEntry(String query){
		dataBase.executeQuery(query);
	}

	@SuppressWarnings("rawtypes")
	public ArrayList getMobileData(){

		String query = "select * from "+ mobileTable +" ;";
		dataBase.getResult(query);
		return dataBase.getMobilesData();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList getMobileNames(){
		
		ArrayList<String> data = getMobileData();
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i=0; i<data.size(); i++){
			
			String[] row = data.get(i).split(",");
			names.add(row[0]);
		}
		
		return names;
	}
	

	public void changePrice(String field, String value, String RSOPrice, String franchisePrice, String realPrice){

		if(field.equals("Mobile")){

			String query = "update "+ mobileTable +" set RSOPrice="+'"'+RSOPrice+'"'+", FranchisePrice="+'"'+franchisePrice+'"'+
					", RealPrice="+'"'+realPrice+'"'+" where Name="+'"'+value+'"'+";";
			dataBase.executeQuery(query);
		}

		else{

			String query = "update "+ pricesTable +" set RSO_Price="+'"'+RSOPrice+'"'+", FranchisePrice="+'"'+franchisePrice+'"'+
					", Real_Price="+'"'+realPrice+'"'+" where Names="+'"'+value+'"'+";";
			dataBase.executeQuery(query);			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteEntry(String id){
		
		String query = "DELETE FROM "+ telenorTable +" where EntryNo="+'"'+id+'"'+";";
		dataBase.executeQuery(query);
		
		int entryNumber = Integer.parseInt(id);
		ArrayList<String> entries = getEntriesRecord();
		
		for(int i=0; i<entries.size(); i++){
			
			String[] entryRow = entries.get(i).split(",");
			int entryId = Integer.parseInt(entryRow[0]);
			
			if(entryId > entryNumber){
				String updateQuery = "UPDATE "+ telenorTable +" set EntryNo="+'"'+(entryId-1)+'"'+" where EntryNo="+'"'+entryId+'"'+";";
				dataBase.executeQuery(updateQuery);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList getEmployeeRecord(){
		
		String query = "select * from " + employeeTable +";";
		return dataBase.getEmployeesReocord(query);
	}
	
	public void deleteEmployee(String CNIC){
		
		String query = "DELETE FROM "+ employeeTable +" where CNIC="+'"'+CNIC+'"'+";";
		dataBase.executeQuery(query);
	}
	
	public void addMobiles(String name, String RSOPrice, String realPrice, String franchisePrice){
		
		String query = "INSERT INTO "+ mobileTable +" values("+'"'+name+'"'+','+'"'+realPrice+'"'+','+'"'+RSOPrice+'"'+','
					+'"'+franchisePrice+'"'+");";
		dataBase.executeQuery(query);
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList getMobilesRecord(){
		
		String query = "select * from "+mobileTable+" ;";
		return dataBase.getMobilesRecord(query);
	}
	
	public void deleteMobile(String mobileName){
		
		String query = "DELETE FROM "+ mobileTable+" where Name="+'"'+mobileName+'"'+";";
		dataBase.executeQuery(query);
	}
	
	public void addStock(double newHLR, double newSim, double newCard, double newMobilesNumbers, double newEasyLoad, double newEasyPaisa, double newCash, double HLR){
		
		String query = "update "+ stockTable +" set HLR="+'"'+newHLR+'"'+", Sim="+'"'+newSim+'"'+
		", Card="+'"'+newCard+'"'+", EasyLoad="+'"'+newEasyLoad+'"'+", NumberOfMobiles="+'"'+newMobilesNumbers+'"'+", EasyPaisa="+'"'+newEasyPaisa+'"'+", Cash="+'"'+newCash+'"'+" where HLR="+'"'+HLR+'"'+";";
		dataBase.executeQuery(query);
	}
	
	public String[] getStock(){
		
		String query = "Select * from "+ stockTable+ ";";
		return dataBase.getStock(query);
	}
}
