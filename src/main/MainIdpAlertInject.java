package main;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.w3c.dom.NodeList;

import classpkg.Asset;
import classpkg.Monitor;
import classpkg.MonitoredAsset;
import dbpkg.DbServices;
import soappkg.SoapServices;

public class MainIdpAlertInject {
	static DbServices dbs = new DbServices();
	static Scanner sc = new Scanner(System.in);

//	@SuppressWarnings({ })
	public static void main(String[] args) {

		int memberNumber = 0;
		do {
			System.out.print("Enter member number: ");
			try {
				memberNumber = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e2) {
				System.out.println(
						"Invalid member number entered. Member number cannot be zero(0) or alphanumeric. Please try again");
				memberNumber = 0;
			}
		} while (memberNumber == 0);

		String service = null;
		try {
			service = MainIdpAlertInject.getServiceForMember(memberNumber);
		} catch (Exception e2) {
			System.out.println("Error while getting service code for member.");
			e2.printStackTrace();
			System.exit(0);
		}

		System.out.println("Member Number: " + memberNumber);
		System.out.println("Service: " + service);

		// Calling middle-tier idp find to get monitor asset details.
		String response = null;
		String[] urlActionRequest;
		SoapServices soap = new SoapServices();
		try {
			urlActionRequest = soap.getIdpFindRequest(memberNumber, service);
			response = soap.callSoap(urlActionRequest[0], urlActionRequest[1], urlActionRequest[2]);
			System.out.println("Response received from backend!");
			// Extracting required nodes from the response.
			List<MonitoredAsset> monitoredAsset = new ArrayList<MonitoredAsset>();

			NodeList nodeList = soap.extractNodes(response, "ns27:MonitorAssetInfo");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				NodeList monitorAssetInfo = nodeList.item(itr).getChildNodes();
				MonitoredAsset maInfo = new MonitoredAsset();
				maInfo.loadMonitoredAsset(monitorAssetInfo);
				monitoredAsset.add(maInfo);
			}

			// Selecting Asset
			int monitorAssetIndex = monitoredAsset.size() - 1;
			List<Object> obj = MainIdpAlertInject.selectAsset(monitoredAsset, monitorAssetIndex);
			Asset selectedAsset = (Asset) obj.get(0);
			monitorAssetIndex = (int) obj.get(1);

			// Listing monitors of selected asset

			Monitor selectedMonitor = MainIdpAlertInject.selectMonitor(monitoredAsset, monitorAssetIndex);

			// Generating alerts
			Boolean result = MainIdpAlertInject.generateAlert(memberNumber, service, selectedAsset, selectedMonitor);
			if (result) {
				System.out.println(
						"\n\n\t\t\t" + selectedMonitor.getMonitorAbbr() + " alert generated successfully. Thank you!");
			}
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}

	}

	public static String getServiceForMember(int memberNumber) throws Exception {
		String service = null;
		try {
			service = dbs.getServiceFromMemberNumber(memberNumber);
			if (!(service != null)) {
				throw new Exception("\nService returned is NULL for the member: "+memberNumber+"\n");
			}
		} catch (Exception e) {
			throw e;
		}
		return service;
	}

	public static List<Object> selectAsset(List<MonitoredAsset> monitoredAsset, int monitorAssetIndex)
			throws Exception {
		Asset selectedAsset = new Asset();

		// If only one asset then no need to ask user for selection.
		if (monitorAssetIndex < 0) {
			throw new Exception("No assets present for the member. Hence exiting.");
		} else if (monitorAssetIndex == 0) {
			System.out.println("\nOnly one asset(" + monitoredAsset.get(monitorAssetIndex).getAsset().getName() + "/"
					+ monitoredAsset.get(monitorAssetIndex).getAsset().getValue() + ") present for this member.");
			selectedAsset = monitoredAsset.get(monitorAssetIndex).getAsset();
		} else {
			System.out.println("\nNo. of Assets: " + monitoredAsset.size());
			do {
				// Listing all assets
				int l = 1;
				for (MonitoredAsset maInfo : monitoredAsset) {
					System.out.println("Asset Info: (Index) " + l);
					System.out.println("\tAsset Name: " + maInfo.getAsset().getName());
					System.out.println("\tAsset Value: " + maInfo.getAsset().getValue());
					System.out.println();
					l++;
				}
				System.out.print("Enter Asset Index: ");
				monitorAssetIndex = Integer.parseInt(sc.nextLine()) - 1;
				if (!(((monitorAssetIndex) >= 0) && (monitorAssetIndex < monitoredAsset.size()))) {
					System.out.println("Invalid selection. Going back to options.\n");
					continue;
				}
				selectedAsset = monitoredAsset.get(monitorAssetIndex).getAsset();
				System.out.println("\nAsset name selected: " + selectedAsset.getName());
				System.out.println("Asset Value selected: " + selectedAsset.getValue());

			} while (!(((monitorAssetIndex) >= 0) && (monitorAssetIndex < monitoredAsset.size())));

		}
		return Arrays.asList(selectedAsset, monitorAssetIndex);
	}

	public static Monitor selectMonitor(List<MonitoredAsset> monitoredAsset, int monitorAssetIndex) throws Exception {
		Monitor selectedMonitor = new Monitor();
		int monitorIndex = monitoredAsset.get(monitorAssetIndex).getMonitor().size() - 1;
		System.out.println();
		// If only one monitor then no need to ask user for selection.
		if (monitorIndex < 0) {
			throw new Exception("No Monitors present for the asset. Hence exiting.");
		} else if (monitorIndex == 0) {
			System.out.println("Only one monitor("
					+ monitoredAsset.get(monitorAssetIndex).getMonitor().get(monitorIndex).getMonitorAbbr()
					+ ") present for this asset.");
			selectedMonitor = monitoredAsset.get(monitorAssetIndex).getMonitor().get(monitorIndex);
		} else {
			do {
				int n = 1;
				for (Monitor m : monitoredAsset.get(monitorAssetIndex).getMonitor()) {
					System.out.println("Monitor Info: (Index) " + n);
					System.out.println("\tMonitor ID: " + m.getMonitorId());
					System.out.println("\tMonitor Name: " + m.getMonitorAbbr());
					System.out.println("\tMonitor Status: " + m.getMonitorStatus());
					n++;
				}
				// Selecting monitor
				System.out.print("\nEnter Monitor Index: ");
				monitorIndex = Integer.parseInt(sc.nextLine()) - 1;
				if (!(((monitorIndex) >= 0)
						&& (monitorIndex < monitoredAsset.get(monitorAssetIndex).getMonitor().size()))) {
					System.out.println("Invalid selection. Going back to options.\n");
					continue;
				}
				selectedMonitor = monitoredAsset.get(monitorAssetIndex).getMonitor().get(monitorIndex);
				System.out.println("\nMonitor name selected: " + selectedMonitor.getMonitorAbbr());

			} while (!((monitorIndex) >= 0
					&& monitorIndex < monitoredAsset.get(monitorAssetIndex).getMonitor().size()));
		}
		return selectedMonitor;
	}

	public static Boolean generateAlert(int memberNumber, String service, Asset selectedAsset, Monitor selectedMonitor)
			throws Exception {
		Boolean result = false;
		String response = null;
		String[] urlActionRequest;
		SoapServices soap = new SoapServices();
		int mtrActSysId = 0;
		System.out.println("\nAlert generation started for selected asset and monitor...");
		try {
			if (selectedMonitor.getMonitorAbbr().equalsIgnoreCase("SSN_MTR")) {
				String idpAdultSmsId = null;
				idpAdultSmsId = dbs.getIdpAdultSmsId(memberNumber);
				dbs.updateSsnMtrStatus(selectedMonitor.getMonitorId());
				int vndActId = (int) (Math.random() * (19999999 - 15000000 + 1) + 15000000);
				mtrActSysId = dbs.insertMtrActSsnMtr(selectedMonitor.getMonitorId(), vndActId);
				String dataBlob = "<NonCreditReport xmlns=\"reportingalert.ssnmonitoring.xml.idp.infrastructure\">\r\n"
						+ "    <EventDetail>\r\n"
						+ "        <EventSubject>CSID Social Security Trace Report</EventSubject>\r\n"
						+ "        <EventType></EventType>\r\n" + "        <EventDate></EventDate>\r\n"
						+ "        <AlertId>" + vndActId + "</AlertId>\r\n" + "        <RecordCount>2</RecordCount>\r\n"
						+ "    </EventDetail>\r\n" + "    <SubscriberDetail>\r\n"
						+ "        <SubscriptionType>2</SubscriptionType>\r\n" + "        <SubscriberNumber>"
						+ idpAdultSmsId + "</SubscriberNumber>\r\n"
						+ "        <ChildSubscriberNumber></ChildSubscriberNumber>\r\n"
						+ "        <Email>none@affiniongroup.com</Email>\r\n" + "    </SubscriberDetail>\r\n"
						+ "</NonCreditReport>";
				dbs.insertMtrDataSsnMtr(mtrActSysId, dataBlob);
			}
			urlActionRequest = soap.getAlertRequest(memberNumber, service, selectedAsset, selectedMonitor, mtrActSysId);
			response = soap.callSoap(urlActionRequest[0], urlActionRequest[1], urlActionRequest[2]);
			if (!response.contains("Success")) {
				System.out.println("Response:\n" + response);
				result = false;
				throw new Exception(
						selectedMonitor.getMonitorAbbr() + "alert generation failed. Please check with QA team.");
			}
			result = true;

		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
}
