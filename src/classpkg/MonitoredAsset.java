package classpkg;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dbpkg.DbServices;

import org.w3c.dom.Element;

public class MonitoredAsset {
	private Asset asset = new Asset();
	private List<Monitor> monitor = new ArrayList<Monitor>();

	public List<Monitor> getMonitor() {
		return monitor;
	}

//	public void setMonitor(Monitor monitor) {
//		this.monitor.add(monitor);
//	}

	public Asset getAsset() {
		return asset;
	}

//	public void setAsset(Asset asset) {
//		this.asset = asset;
//	}

	public void loadMonitoredAsset(NodeList monitorAssetInfo) throws Exception {
		DbServices dbs = new DbServices();

		for (int j = 0; j < monitorAssetInfo.getLength(); j++) {
			Node node1 = monitorAssetInfo.item(j);
			if (node1.getNodeName().contains("Asset")) {
				try {
					Asset ast = new Asset();
					ast.setTypeId(Integer.parseInt(node1.getChildNodes().item(3).getTextContent()));
					ast.setName(dbs.getAssetNameFromId(ast.getTypeId()));
					ast.setValue(getAssetValueByTypeId(node1));
					this.asset = ast;
				} catch (NullPointerException | NumberFormatException | DOMException e) {
					throw e;
				}
				catch (Exception e1) {
					throw e1;
				}
			}
			if (node1.getNodeName().contains("Monitor")) {
				if (!(node1.getChildNodes().item(4).getTextContent().contains("CANCEL")
						|| node1.getChildNodes().item(4).getTextContent().contains("cancel"))) {
					Monitor mtr = new Monitor();
					mtr.setMonitorAbbr(node1.getChildNodes().item(2).getTextContent());
					mtr.setMonitorId(Integer.parseInt(node1.getChildNodes().item(3).getTextContent()));
					mtr.setMonitorStatus(node1.getChildNodes().item(4).getTextContent());
					monitor.add(mtr);
				}
			}
		}

	}

	public String getAssetValueByTypeId(Node asset) {
		String assetValue = "NO";
		NodeList assetNodes = asset.getChildNodes();
		int assetTypeId = Integer.parseInt(asset.getChildNodes().item(3).getTextContent());
		Element eAssetNode = (Element) assetNodes;
		switch (assetTypeId) {
		case 0:
			// Credit card asset
			assetValue = assetNodes.item(6).getChildNodes().item(0).getTextContent();
			break;
		case 2:
			// Drivers license asset
			assetValue = assetNodes.item(6).getChildNodes().item(0).getTextContent();
			break;
		case 69:
			// Kids SSN asset
			assetValue = assetNodes.item(6).getChildNodes().item(0).getTextContent();
			break;
		case 35:
			// Phone asset
			assetValue = assetNodes.item(6).getTextContent();
			break;
		case 93:
			// Mailing membership address asset - NCOA monitor
			assetValue = getAddressAssetValue(eAssetNode.getElementsByTagName("ns3:AdministrativeArea"));
			break;
		case 1:
			// Passport asset
			assetValue = assetNodes.item(6).getChildNodes().item(0).getTextContent();
			break;
		case 36:
			// Address monitor asset
			assetValue = getAddressAssetValue(eAssetNode.getElementsByTagName("ns3:AdministrativeArea"));
			break;
		case 30:
			// Name asset
			String firstName = assetNodes.item(6).getChildNodes().item(0).getTextContent();
			String lastName = assetNodes.item(6).getChildNodes().item(1).getTextContent();
			assetValue = firstName + " " + lastName;
			break;
		case 33:
			// DOB Asset
			assetValue = assetNodes.item(6).getChildNodes().item(0).getTextContent();
			break;
		case 31:
			// Adult SSN Asset
			assetValue = assetNodes.item(6).getChildNodes().item(0).getTextContent();
			break;
		case 34:
			// Email asset
			assetValue = assetNodes.item(6).getTextContent();
			break;
		case 32:
			// Bank Account asset
			String bankAcctNumber = assetNodes.item(6).getChildNodes().item(0).getTextContent();
			String bankRoutingNumber = assetNodes.item(6).getChildNodes().item(1).getTextContent();
			assetValue = bankAcctNumber + "/" + bankRoutingNumber;
			break;
		default:
			assetValue = "Could not find value!";
			break;
		}
		return assetValue;
	}

	public String getAddressAssetValue(NodeList nlAdminArea) {
		String assetValue = null;
		int i = 0;
		String addressLine = null, city = null, state = null, zipCode = null;
		for (i = 0; i < nlAdminArea.item(0).getChildNodes().getLength(); i++) {
			Node n = nlAdminArea.item(0).getChildNodes().item(i);
			Element nE = (Element) n;
			if (!(nE.getTagName().contains("Local"))) {
				if (nE.getAttributes().getNamedItem("Type").getNodeValue().equalsIgnoreCase("Street")
						&& addressLine == null)
					addressLine = nE.getTextContent();
				else if (nE.getAttributes().getNamedItem("Type").getNodeValue().equalsIgnoreCase("Street")
						&& addressLine != null)
					addressLine = addressLine + "," + nE.getTextContent();
				else if (nE.getAttributes().getNamedItem("Type").getNodeValue().equalsIgnoreCase("State"))
					state = nE.getTextContent();

			} else {
				city = nE.getChildNodes().item(0).getTextContent();
				zipCode = nE.getChildNodes().item(1).getTextContent();
			}
		}
		assetValue = addressLine + "," + city + "," + state + "," + zipCode;
		return assetValue;
	}

}
