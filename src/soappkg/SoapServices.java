package soappkg;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class SoapServices extends SoapRequests {
	
	public String callSoap(String wsURL, String SOAPAction, String xmlInput) throws MalformedURLException, IOException, KeyManagementException, NoSuchAlgorithmException {
		
		// Code to make a webservice HTTP request
		String responseString = "";
		String outputString = "";
		URL url = new URL(wsURL);
		URLConnection connection = url.openConnection();
		HttpsURLConnection httpsConn = (HttpsURLConnection) connection;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
		httpsConn.setSSLSocketFactory(sc.getSocketFactory());
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
		
		// Set the appropriate HTTP parameters.
		httpsConn.setRequestProperty("Content-Length", String.valueOf(b.length));
		httpsConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpsConn.setRequestProperty("SOAPAction", SOAPAction);
		httpsConn.setRequestMethod("POST");
		httpsConn.setDoOutput(true);
		httpsConn.setDoInput(true);
		OutputStream out = httpsConn.getOutputStream();
		// Write the content of the request to the output stream of the HTTP Connection.
		out.write(b);
		out.close();
		
		// Read the response.
		InputStreamReader isr = new InputStreamReader(httpsConn.getInputStream());
		BufferedReader in = new BufferedReader(isr);

		// Write the SOAP message response to a String.
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}
		return outputString;
	}
	
	public static Document parseXmlFile(String in) throws Exception {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException e) {
			throw e;
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (RuntimeException e) {
			throw e;
		}catch (Exception e) {
			throw e;
		}
	}
	
	public NodeList extractNodes(String response, String tagName) throws Exception {
		
		// Parse the String output to a org.w3c.dom.Document and be able to reach every
		NodeList nodeList = null;
		try {
			Document doc = parseXmlFile(response);
			doc.getDocumentElement().normalize();
			nodeList = doc.getElementsByTagName(tagName);
		} catch (Exception e) {
			throw new Exception("Node extraction failed!",e);
		}
		
		return nodeList;
	}
	

}
