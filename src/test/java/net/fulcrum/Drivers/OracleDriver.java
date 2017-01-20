/**
 * OracleDriver.java
 *
 * Java Class Wrapper Object for Oracle JDBC Driver functions for CATS Test Harness
 *
 */

package net.fulcrum.Drivers;

import net.fulcrum.Routines.Android.Verizon.ExpressStock;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Random;

public class OracleDriver {

    public Connection connection;
    public CallableStatement cstmt;

    public String sql;

    public String URL;
    public String DBUserName = "vzwpsft";
    public String DBPassword = "vzwpsft";

    private String toLDC;
    private String fromLDC;
    private String partCode;
    private String assetCode;
    private String containerCode;
    private String status;
    private boolean result;

    protected Random rand = new Random();

    protected String projectCode = "PROJECT-CTA-EXP";
    protected String poCode = "PO-CTA-EXP";

    public OracleDriver(String databaseIP, String username, String password) {
        URL = "jdbc:oracle:thin:@" + databaseIP + ":1521:ORCL";
        DBUserName = username;
        DBPassword = password;
        registerDriver(URL, DBUserName, DBPassword);
    }

    public OracleDriver(String databaseIP) {
        URL = "jdbc:oracle:thin:@" + databaseIP + ":1521:ORCL";
        DBUserName = "vzwpsft";
        DBPassword = "vzwpsft";
        registerDriver(URL, DBUserName, DBPassword);
    }

    public void registerDriver (String url, String username, String password) {
        // Register JDBC Driver to load Oracle's driver class into memory
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = DriverManager.getConnection(URL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Call F_GETPOLDC function to get TO LDC of PO Line
    public String getPOLDC(String poCode, String lineNumber) {
        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_GETPOLDC(?, ?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, poCode);
            cstmt.setString(3, lineNumber);
            cstmt.executeUpdate();
            toLDC = cstmt.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
            toLDC = null;
        }
        return toLDC;
    }


    // Get RA Code
    public String getRACode() {
        String raCode;
        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_GETRACODE()}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.executeUpdate();
            raCode = cstmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
            raCode = null;
        }
        return raCode;
    }

    // Call F_GETPOPART function to get Part Code of PO Line
    public String getPOPart(String poCode, String lineNumber) {
        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_GETPOPART(?, ?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, poCode);
            cstmt.setString(3, lineNumber);
            cstmt.executeUpdate();
            partCode = cstmt.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
            partCode = null;
        }
        return partCode;
    }

    public String generateContainerCode() {
        return "VZC-CTA-" + rand.nextInt(100000);
    }

    public String generateAssetCode() {
        return "ASSET-CTA-" + rand.nextInt(100000);
    }

    // Call F_ISASSET function to determine if PO Line is receiving an Asset
    public boolean isAsset(String partCode) {
        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_ISSERIALIZED(?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, partCode);
            cstmt.executeUpdate();
            status = cstmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
            status = "ERROR";
        }
        if (status.toUpperCase().equals("Y")) {
            return true;
        }
        return false;
    }

    //Call F_RESOLVE_barcode function to determine if a barcode is Asset, Partcode or Container
    public String resolveBarcode(String item)
    {
    	try{
    		cstmt=null;
    		sql="{?=call VZWTST_P_UTIL.F_RESOLVE_barcode(?)}";
    		cstmt=connection.prepareCall(sql);
    		cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, item);
            cstmt.executeUpdate();
            status = cstmt.getString(1);
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		status = null;
    	}
    	return status;
    }

    // Call GETTOTALLINES function to determine if same part exist on multiple PO Lines
    public int getTotalLines(String POCode, String PartCode) {
        int totalLines;

        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_GETPOLINES(?, ?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, POCode);
            cstmt.setString(3, PartCode);
            cstmt.executeUpdate();
            totalLines = cstmt.getInt(1);
            System.out.println("Total Part Lines: " + totalLines);
        } catch (Exception e) {
            e.printStackTrace();
            totalLines = -1;
        }
        return totalLines;
    }

    // Call GETMRLINECOUNT function to determine if same part exist on multiple MR Lines
    public int getTotalMRLines(String mrCode, String PartCode, String containerCode) {
        int totalLines;

        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_GETMRLINECOUNT(?, ?, ?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, mrCode);
            cstmt.setString(3, PartCode);
            cstmt.setString(4, containerCode);
            cstmt.executeUpdate();
            totalLines = cstmt.getInt(1);
            System.out.println("Total MR Lines: " + totalLines);
        } catch (Exception e) {
            e.printStackTrace();
            totalLines = -1;
        }
        return totalLines;
    }


    //Stage a Retirement Batch
    public String stageRetirementBatch() {
        String retirementBatch;

        try {
            cstmt = null;
            sql = "{? = call VZWTST_F_STAGERETIREMENTBATCH(?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setInt(2, 1);
            cstmt.executeUpdate();
            retirementBatch = cstmt.getString(1);
            System.out.println("Retirement Batch created: " + retirementBatch);
        } catch (Exception e) {
            e.printStackTrace();
            retirementBatch = null;
        }
        return retirementBatch;
    }


    //Stage a Materials Request
    public String stageMaterialsRequest(String location) {
        String mr;

        try {
            cstmt = null;
            sql = "{? = call VZWTST_F_STAGEMR(?, ?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setInt(2, 1);
            cstmt.setString(3, location);
            cstmt.executeUpdate();
            mr = cstmt.getString(1);
            System.out.println("Materials Request created: " + mr);
        } catch (Exception e) {
            e.printStackTrace();
            mr = null;
        }
        return mr;
    }



    //Call DBFunction to perfrom PoReceive
    public String poReceive(String poCode, int testID, String poLine, int qty)
    {
    	try
    	{
    		cstmt = null;
    		sql = "{?= call VZWTST_P_TRANSACTIONS.PORECEIVE(?,?,?,?)}";
    		cstmt = connection.prepareCall(sql);
    		cstmt.registerOutParameter(1, Types.VARCHAR);
    		cstmt.setString(2, poCode);
    		cstmt.setInt(3, testID);
    		cstmt.setString(4, poLine);
    		cstmt.setInt(5, qty);
    		cstmt.executeUpdate();
            status = cstmt.getString(1);
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return status;
    }

    //Call DB function to perform CancelReceive
    public String cancelReceive (String poCode, int testID, String poLine, int qty, String assetCode)
    {
    	try
    	{
    		cstmt = null;
    		sql = "{?= call VZWTST_P_TRANSACTIONS.CANCELRECEIVE(?,?,?,?,?)}";
    		cstmt = connection.prepareCall(sql);
    		cstmt.registerOutParameter(1, Types.VARCHAR);
    		cstmt.setString(2, poCode);
    		cstmt.setInt(3, testID);
    		cstmt.setString(4, poLine);
    		cstmt.setInt(5, qty);
    		cstmt.setString(6, assetCode);
    		cstmt.executeUpdate();
            status = cstmt.getString(1);
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return status;
    }

    // Call function to perform Express Stock
    public String expressStock(int testID, String fromLDC,String partCode , String assetCode, int qty,
        String ProjectCode, String POCode, int POLine) {

        System.out.println(fromLDC + partCode + assetCode + qty + ProjectCode + POCode);
    	try
    	{
    		cstmt = null;
    		sql = "{?= call VZWTST_P_TRANSACTIONS.EXPRESSSTOCK(?,?, ?,?,?,?, ?,?)}";
    		cstmt = connection.prepareCall(sql);
    		cstmt.registerOutParameter(1, Types.VARCHAR);
    		cstmt.setInt(2, testID);
    		cstmt.setString(3, fromLDC);
    		cstmt.setString(4, partCode);
    		cstmt.setString(5, assetCode);
    		cstmt.setInt(6, qty);
    		cstmt.setString(7,ProjectCode);
    		cstmt.setString(8, POCode);
    		cstmt.setInt(9, POLine);
    		cstmt.executeUpdate();
    	    status = cstmt.getString(1);
    		//System.out.println("AssetCode is" +AssetCode);
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return status;

    }
    // Call function to perform ADDContainer
    public String addContainer(int testID, String toLDC, String fromLDC, String containerCode, String partCode,
        String assetCode, int qty)
    {
    	try
    	{
            cstmt = null;
            sql= "{?=call VZWTST_P_TRANSACTIONS.ADD_CONTAINER(?,?,?,?,?,?,?,?,?,?)}";
            cstmt =connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setInt(2, testID);
            cstmt.setString(3, toLDC);
            cstmt.setString(4, fromLDC);
            cstmt.setString(5, containerCode);
            cstmt.setString(6, partCode);
            cstmt.setString(7, assetCode);
            cstmt.setInt(8, 1);
            cstmt.setString(9, "");
            cstmt.setString(10, "");
            cstmt.setString(11, "");
            cstmt.executeUpdate();
            status  = cstmt.getString(1);

    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return status;
    }
    // Call Function to Perform Transfer
    public String transfer(int testID, String toLDC, String fromLDC, String partCode, String assetCode, int qty)
    {
    	String result = "";
    	try
    	{
    		cstmt=null;
    		sql= "{?=call VZWTST_P_TRANSACTIONS.TRANSFER(?,?,?,?,?,?)}";
        	cstmt =connection.prepareCall(sql);
        	cstmt.registerOutParameter(1, Types.VARCHAR);
        	cstmt.setInt(2, testID);
    		cstmt.setString(3, toLDC);
    		cstmt.setString(4, fromLDC);
    		cstmt.setString(5, partCode);
    		cstmt.setString(6, assetCode);
    		cstmt.setInt(7, qty);
    		cstmt.executeUpdate();
    		result = cstmt.getString(1);
    	}catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return result;
    }
    //Call Function to perform UpdateFAID
    public String updateFAID(int testID, String assetCode)
    {
    	String result = "";
    	try
    	{
    		cstmt=null;
    		sql= "{?=call VZWTST_P_TRANSACTIONS.UPDATE_FAID(?,?)}";
        	cstmt =connection.prepareCall(sql);
        	cstmt.registerOutParameter(1, Types.VARCHAR);
        	cstmt.setInt(2, testID);
    		cstmt.setString(3, assetCode);
    		cstmt.executeUpdate();
    		result = cstmt.getString(1);
    	}catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return result;
    }
    //Call Function to perform CaseBreak
    public String caseBreak(int testID, String containerCode, String containerItems, int qty)
    {
    	String result = "";
    	try
    	{
    	cstmt=null;
		sql= "{?=call VZWTST_P_TRANSACTIONS.CASEBREAK(?,?,?,?)}";
    	cstmt =connection.prepareCall(sql);
    	cstmt.registerOutParameter(1, Types.VARCHAR);
    	cstmt.setInt(2, testID);
		cstmt.setString(3, containerCode);
		cstmt.setString(4, containerItems);
		cstmt.setInt(5, qty);
		cstmt.executeUpdate();
		result = cstmt.getString(1);
	    }catch (Exception e)
	    {
		e.printStackTrace();
	    }
	    return result;
    }
    //Call Function to perform InventoryShip
    public String inventoryShip(int testID, String mrCode, String lineNumber, String fromLDC, String barcode, int qty)
    {
    	String result = "";
    	try
    	{
    		cstmt=null;
    		sql= "{?=call VZWTST_P_TRANSACTIONS.INVENTORY_SHIP(?,?,?,?,?,?)}";
        	cstmt =connection.prepareCall(sql);
        	cstmt.registerOutParameter(1,Types.VARCHAR);
        	cstmt.setInt(2, testID);
    		cstmt.setString(3, mrCode);
    		cstmt.setString(4, lineNumber);
    		cstmt.setString(5, fromLDC);
    		cstmt.setString(6, barcode);
    		cstmt.setInt(7, qty);
    		cstmt.executeUpdate();
    		result = cstmt.getString(1);
    	}catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return result;
    }
  //Call Function to perform InventoryPick
    public String inventoryPick(int testID, String mrCode, String lineNumber, String fromLDC, String barcode, int qty)
    {
    	String result = "";
    	try
    	{
    		cstmt=null;
    		sql= "{?=call VZWTST_P_TRANSACTIONS.INVENTORY_PICK(?,?,?,?,?,?)}";
        	cstmt =connection.prepareCall(sql);
        	cstmt.registerOutParameter(1,Types.VARCHAR);
        	cstmt.setInt(2, testID);
    		cstmt.setString(3, mrCode);
    		cstmt.setString(4, lineNumber);
    		cstmt.setString(5, fromLDC);
    		cstmt.setString(6, barcode);
    		cstmt.setInt(7, qty);
    		cstmt.executeUpdate();
    		result = cstmt.getString(1);
    	}catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return result;
    }

    // Perform a Purchase Order through CATS Test Harness
    public String stagePOData() {
        String POCode;
        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = DriverManager.getConnection(URL, DBUserName, DBPassword);
            cstmt = null;


            // Call AUTO_F_STAGEPO function to Stage a PO. Returns random generated PO Code.
            String sql = "{? = call VZWTST_F_STAGEPO(?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setInt(2, 1);
            cstmt.executeUpdate();
            POCode = cstmt.getString(1);
            System.out.println("Purchase Order Staged: " + POCode);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return POCode;
    }

    public String createAsset(String toLDC, String partCode) {
        assetCode = generateAssetCode();
        status = expressStock(001, toLDC, partCode, assetCode, 1, projectCode, poCode, 1);
        return assetCode;
    }

    public String createAsset(String toLDC) {
        partCode = "109091595";
        assetCode = generateAssetCode();
        status = expressStock(1, toLDC, partCode, assetCode, 1, projectCode, poCode, 1);
        return assetCode;
    }

    public void createPart (String toLDC, String partCode, int qty) {
        status = expressStock(001, toLDC, partCode, assetCode, qty, projectCode, poCode, 1);
    }

    public void createPart (String toLDC, int qty) {
        partCode= "107893304";
        status = expressStock(001, toLDC, partCode, assetCode, qty, projectCode, poCode, 1);
    }

    public void createPart (String toLDC, int qty, String project, String poCode, int lineNumber) {
        partCode= "107893304";
        status = expressStock(001, toLDC, partCode, assetCode, qty, project, poCode, lineNumber);
    }

    //Creates a container with an Asset
    public String createContainerAsset(String toLDC, String fromLDC) {
        containerCode = generateContainerCode();
        partCode = "109091595";
        assetCode = createAsset(toLDC, partCode);
        status = addContainer(001, toLDC, fromLDC, containerCode, partCode, assetCode, 1);
        return containerCode;
    }

    //Creates a container with an Asset
    public String createContainerAsset(String toLDC, String fromLDC, String assetCode) {
        containerCode = generateContainerCode();
        status = addContainer(1, toLDC, fromLDC, containerCode, partCode, assetCode, 1);
        return containerCode;
    }


    //Creates a container with an Asset
    public String createContainerParts(String toLDC, String fromLDC, String partCode, int qty) {
        containerCode = generateContainerCode();
        status = addContainer(1, toLDC, fromLDC, containerCode, partCode, " ", qty);
        return containerCode;
    }

    public void addToContainer(String toLDC, String fromLDC, String containerCode, String partCode, String assetCode, int qty) {
        status = addContainer(1, toLDC, fromLDC, containerCode, partCode, assetCode, qty);
    }

    public int verifyExpressStock(Boolean batched, String partCode, String locationDetailCode,
                                   String assetCode, int qty) {
        int customTransactionID;

        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_VERIFYEXPRESSSTOCK(?, ?, ?, ?, ?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.INTEGER);
            if (batched) {
                cstmt.setString(2, "Y");
            } else {
                cstmt.setString(2, "N");
            }
            cstmt.setString(3, partCode);
            cstmt.setString(4, locationDetailCode);
            cstmt.setString(5, assetCode);
            cstmt.setInt(6, qty);
            cstmt.executeUpdate();
            customTransactionID = cstmt.getInt(1);
            System.out.println("Express Stock Custom Trx ID: " + customTransactionID);
        } catch (Exception e) {
            e.printStackTrace();
            customTransactionID = 0;
        }
        return customTransactionID;
    }

    public int verifyCustomTransaction(Boolean batched, String trxType) {
        int customTransactionID;

        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_VERIFYCUSTOMTRANSACTION(?, ?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.INTEGER);
            if (batched) {
                cstmt.setString(2, "Y");
            } else {
                cstmt.setString(2, "N");
            }
            cstmt.setString(3, trxType);
            cstmt.executeUpdate();
            customTransactionID = cstmt.getInt(1);
            System.out.println("Custom Trx ID: " + customTransactionID);
        } catch (Exception e) {
            e.printStackTrace();
            customTransactionID = 0;
        }
        return customTransactionID;
    }

    public String getAssetLocation(String assetCode) {
        String ldc = null;

        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_GETASSETLOCATION(?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, assetCode);
            cstmt.executeUpdate();
            ldc = cstmt.getString(1);
            System.out.println("Location for Asset Code: " + ldc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ldc;
    }

    public String getLocationBU(String locationDetailCode) {
        String businessUnit = null;

        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_GETBU(?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, locationDetailCode);
            cstmt.executeUpdate();
            businessUnit = cstmt.getString(1);
            System.out.println("Location BU for " + locationDetailCode +  " : " + businessUnit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return businessUnit;
    }

    public String getLDCDescription(String locationDetailCode) {
        String desc = null;

        try {
            cstmt = null;
            sql = "{? = call VZWTST_P_UTIL.F_GETLDCDESCRIPTION(?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, locationDetailCode);
            cstmt.executeUpdate();
            desc = cstmt.getString(1);
            System.out.println("Location Desc for " + locationDetailCode +  " : " + desc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desc;
    }

    public int createAssetXO(String partCode, String assetCode) {
        int success = 0;
        String location = "SEA-WAREHOUSE";
        String status = "ON HAND";
        String bin = "ENG";

        try {
            cstmt = null;
            sql = "{? = call XOTST_P_UTIL.F_CREATEASSET(?, ?, ?, ?, ?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, partCode);
            cstmt.setString(3, assetCode);
            cstmt.setString(4, location);
            cstmt.setString(5, status);
            cstmt.setString(6, bin);
            cstmt.executeUpdate();
            success = cstmt.getInt(1);
            if (success > 0) {
                System.out.println("Created Asset: " + assetCode + " Asset ID: " + success);
            } else {
                System.out.println("Unable to create Asset: " + assetCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return success;


        }
        return success;
    }

    public void cleanAuditLocationXO(String locationID) {
        try {
            System.out.println("Cleaning up Audit Location ID: " + locationID);
            cstmt = null;
            sql = "{call XOTST_P_UTIL.SP_CLEANAUDITLOCATION(?)}";
            cstmt = connection.prepareCall(sql);
            cstmt.setString(1, locationID);
            cstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //Transfer Request - AIRTEL
    
    public String getTRLocation(String locationType, String transferRequest){
    	String location = null;    	
    	try {
            System.out.println("Getting "+locationType+"LOCATION for Transfer Request "+transferRequest);
            cstmt = null;
            sql = "SELECT NAME FROM CATS_LOCATION WHERE LOCATIONID IN (SELECT "+locationType+"LOCATIONID FROM CATSCUST_REQUISITION WHERE REQNUMBER='?')";
            cstmt = connection.prepareCall(sql);
            cstmt.setString(1, transferRequest);
            cstmt.execute();
            location = cstmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return location;
    }
    
    public String getTRPartcode(int lineNumber, String transferRequest){
    	String partCode = null;    	
    	try {
            System.out.println("Getting Non-Serialized Partcode for Transfer Request "+transferRequest);
            cstmt = null;
            sql = "SELECT PARTCODE FROM CATS_PART WHERE PARTID IN (SELECT PARTID FROM CATSCUST_REQUISITIONLINE WHERE LINENUMBER=? AND REQUISITIONID IN (SELECT REQUISITIONID FROM CATSCUST_REQUISITION WHERE REQNUMBER='?'))";
            cstmt = connection.prepareCall(sql);
            cstmt.setString(1, String.valueOf(lineNumber));
            cstmt.setString(2, transferRequest);
            cstmt.execute();
            partCode = cstmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return partCode;
    }
    
    
    public String getTRQty(int lineNumber, String transferRequest){
    	String qty = null;    	
    	try {
            System.out.println("Getting Non-Serialized Partcode for Transfer Request "+transferRequest);
            cstmt = null;
            sql = "SELECT QTYORDERED FROM CATSCUST_REQUISITIONLINE WHERE LINENUMBER=? AND REQUISITIONID IN (SELECT REQUISITIONID FROM CATSCUST_REQUISITION WHERE REQNUMBER='?')";
            cstmt = connection.prepareCall(sql);
            cstmt.setString(1, String.valueOf(lineNumber));
            cstmt.setString(2, transferRequest);
            cstmt.execute();
            qty = cstmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return qty;
    }
    
    
    public String getTransferOrder(String transferRequest){
    	String transferOrder = null;    	
    	try {
            System.out.println("Getting Non-Serialized Partcode for Transfer Request "+transferRequest);
            cstmt = null;
            sql = "SELECT TRANSFERNUMBER FROM CATS_TRANSFER WHERE REFERENCENUMBER IN (SELECT REQNUMBER FROM CATSCUST_REQUISITION WHERE REQNUMBER='?')";
            cstmt = connection.prepareCall(sql);
            cstmt.setString(1, transferRequest);
            cstmt.execute();
            transferOrder = cstmt.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return transferOrder;
    }
    

    //Close OJDBC connection prior to Java Garbage Collection
    @Override
    public void finalize () throws Throwable {
        try {
            cstmt.close();
            connection.close();
        } catch (Throwable t) {
            System.out.println("Exception closing OJDBC connection: " + t.toString());
        } finally {
            super.finalize();
        }
    }
}
