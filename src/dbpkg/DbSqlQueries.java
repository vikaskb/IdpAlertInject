package dbpkg;

public class DbSqlQueries {

	public static String getAssetNameSql(int ID) {
		String sqlQuery = "select * from GARS.ASSET_TYPE at where at.ASSET_TYPE_ID IN (" + ID + ")";
		return sqlQuery;
	}

	public static String getServiceFromAddTranSql(int memberNumber) {
		String sqlQuery = "select * from TRANSACTION.add_transaction where MEMBER_NUMBER = " + memberNumber;
		return sqlQuery;
	}

	public static String getServiceFromMemmstSql(int memberNumber) {
		String sqlQuery = "select * from CENTRAL.MEMMST m where m.MEMBERSHIP_NUMBER = " + memberNumber;
		return sqlQuery;
	}

	public static String getIdpAdultSmsIdSql(int memberNumber) {
		String sqlQuery = "select EXTERNAL_MAPPED_KEY from MEMBERSHIP.CUSTOMER_EXTERNAL_MAPPING where EXTERNAL_COMPANY_ID = 13 and CUSTOMER_ID = "
				+ memberNumber;
		return sqlQuery;
	}

	public static String updateSsnMtrStatusSql(int mtrReqSysId) {
		String sqlQuery = "update PRVGRD.MONITOR_REQUEST mr set mr.REQUEST_STATUS_ID = 103, mr.REQUEST_SUB_STATUS_ID = null where mr.MONITOR_REQUEST_SYSID = "+mtrReqSysId;
		return sqlQuery;
	}

	public static String insertMtrActSsnMtrSql(int mtrReqSysId, int vndActId) {
		String sqlQuery = "insert into PRVGRD.MONITOR_ACTIVITY \r\n"
				+ "	(MONITOR_ACTIVITY_SYSID,ACTIVITY_TYPE_SYSID,MONITOR_REQUEST_SYSID,VENDOR_SYSID,YEAR,MONTH,ADD_DATE,UPDATE_DATE,STATUS_CODE,VENDOR_ACTIVITY_ID,ADD_USERNAME,UPDATE_USERNAME,DELIVERY_CHANNEL) values \r\n"
				+ "	(MONITOR_ACTIVITY_SEQ.NEXTVAL,134," + mtrReqSysId + ",null,null,null,SYSDATE,SYSDATE,778,"
				+ vndActId + ",'IDP_ACK','IDP_ACK',null)";
		return sqlQuery;

	}

	public static String getMtrActIdWithVndActIdSql(int mtrReqSysId, int vndActId) {
		String sqlQuery = "select * from prvgrd.monitor_activity where STATUS_CODE = 778 and ACTIVITY_TYPE_SYSID = 134 and VENDOR_ACTIVITY_ID = '"
				+ vndActId + "' and MONITOR_REQUEST_SYSID = " + mtrReqSysId;
		return sqlQuery;
	}
	
	public static String insertMtrDataSsnMtrSql(int mtrActSysId, String dataBlob) {
		String sqlQuery = "Insert into PRVGRD.MONITOR_DATA \r\n" + 
				"  (MONITOR_DATA_SYSID,MONITOR_ACTIVITY_SYSID,DATA,CREATE_DATE) values \r\n" + 
				"  (MONITOR_DATA_SEQ.NEXTVAL,"+mtrActSysId+",utl_i18n.string_to_raw('"+dataBlob+"'),SYSDATE)";
		return sqlQuery;
	}

}
