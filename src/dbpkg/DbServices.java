package dbpkg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbServices extends DbSqlQueries {

	DbConnection dbc;
	Connection conn;

	public String getServiceFromMemberNumber(int memberNumber) throws Exception {
		String service = null;
		try {
			dbc = new DbConnection();
			conn = dbc.dbConnect("CORBZ");
			String sql = getServiceFromAddTranSql(memberNumber);
			ResultSet rs = dbc.runSelectSql(conn, sql);
			if (rs.isAfterLast()) {
				//No record in TRANSACTION.ADD_TRANSACTION table. Querying in CENTRAL.MEMMST.
				rs.close();
				sql = getServiceFromMemmstSql(memberNumber);
				rs = dbc.runSelectSql(conn, sql);
				if (rs.isAfterLast()) {
					throw new SQLException("Requested member was not found. Member number: " + memberNumber);
				}
			}
			service = rs.getString("SERVICE");
			dbc.closeConn(conn);
		} catch (SQLException e) {
			throw e;
		} catch (Exception e1) {
			throw e1;
		}

		return service;

	}

	public String getAssetNameFromId(int ID) throws Exception {
		String assetName = null;
		try {
			dbc = new DbConnection();
			conn = dbc.dbConnect("PRDCT");
			String sql = getAssetNameSql(ID);
			ResultSet rs = dbc.runSelectSql(conn, sql);
			assetName = rs.getString("NAME");
			dbc.closeConn(conn);
		} catch (SQLException e) {
			throw e;
		} catch (Exception e1) {
			throw e1;
		}

		return assetName;
	}

	public String getIdpAdultSmsId(int memberNumber) throws Exception {
		String adultSmsId = "1GCENAQA";
		try {
			dbc = new DbConnection();
			conn = dbc.dbConnect("CORBZ");
			String sql = getIdpAdultSmsIdSql(memberNumber);
			ResultSet rs = dbc.runSelectSql(conn, sql);
			if (rs.isAfterLast()) {
				System.out.println(
						"External Adult SMS ID not present for the member. Defaulting to '" + adultSmsId + "'!");
			} else {
				adultSmsId = rs.getString("EXTERNAL_MAPPED_KEY");
			}
			rs.close();
			dbc.closeConn(conn);
		} catch (SQLException e) {
			throw e;
		} catch (Exception e1) {
			throw e1;
		}
		return adultSmsId;
	}

	public void updateSsnMtrStatus(int mtrReqSysId) throws Exception {
		boolean updateResult = false;
		try {
			dbc = new DbConnection();
			conn = dbc.dbConnect("PRDCT");
			String sql = updateSsnMtrStatusSql(mtrReqSysId);
			updateResult = dbc.runUpdateSql(conn, sql);
			if (!updateResult) {
				dbc.closeConn(conn);
				throw new SQLException("Requested SSN_MTR status update failed! SQL: \n" + sql);
			}
			dbc.closeConn(conn);
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	public int insertMtrActSsnMtr(int mtrReqSysId, int vndActId) throws Exception {
		boolean updateResult = false;
		int mtrActSysId = 0;
		try {
			dbc = new DbConnection();
			conn = dbc.dbConnect("PRDCT");

			String sql = insertMtrActSsnMtrSql(mtrReqSysId, vndActId);
			updateResult = dbc.runUpdateSql(conn, sql);
			if (!updateResult) {
				dbc.closeConn(conn);
				throw new SQLException("Insert into monitor activity table failed. SQL: \n" + sql);
			} else {
				mtrActSysId = getMtrActIdWithVndActId(mtrReqSysId, vndActId);
			}
			dbc.closeConn(conn);
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return mtrActSysId;
	}

	public int getMtrActIdWithVndActId(int mtrReqSysId, int vndActId) throws Exception {
		int mtrActId = 0;
		try {
			dbc = new DbConnection();
			conn = dbc.dbConnect("PRDCT");
			String sql = getMtrActIdWithVndActIdSql(mtrReqSysId, vndActId);
			ResultSet rs = dbc.runSelectSql(conn, sql);
			if (rs.isAfterLast()) {
				dbc.closeConn(conn);
				throw new SQLException("Monitor activity not found for monitor, SQL:\n" + sql);
			}
			mtrActId = Integer.parseInt(rs.getString("MONITOR_ACTIVITY_SYSID"));
			rs.close();
			dbc.closeConn(conn);
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return mtrActId;
	}

	public void insertMtrDataSsnMtr(int mtrActSysId, String dataBlob) throws Exception {
		boolean updateResult = false;

		try {
			dbc = new DbConnection();
			conn = dbc.dbConnect("PRDCT");

			String sql = insertMtrDataSsnMtrSql(mtrActSysId, dataBlob);
			updateResult = dbc.runUpdateSql(conn, sql);
			if (!updateResult) {
				dbc.closeConn(conn);
				throw new SQLException("Insert into monitor data table failed. SQL: \n" + sql);
			}
			dbc.closeConn(conn);
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

}
