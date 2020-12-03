package soappkg;

import classpkg.Asset;
import classpkg.Monitor;

public class SoapRequests {

	static private String env = "staging";
	
	
	static private String xmlURL = "https://xmlgw.int." + env + ".cuc.com";
	static private String idpOfflineUrl = "https://entbatchwls." + env
			+ ".cuc.com/IDPBS-ws-offline/com/affinion/idp/webservices/impl/IDPBizServicesOffline";

	public String[] getIdpFindRequest(int memberNumber, String service) {
		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = xmlURL + "/ServiceGateway/Product/IDP/BizServicesOnline";
		urlActionRequest[1] = "find";
		urlActionRequest[2] = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\r\n"
				+ "   <soap:Body>\r\n" + "      <find xmlns=\"monitoredasset.wi.xml.idp.product\">\r\n"
				+ "         <FindMonitoredAssetRequest Version=\"1.1\" xmlns=\"findmonitoredasset.wi.xml.idp.product\">\r\n"
				+ "            <RequestCommon>\r\n"
				+ "               <MembershipNumber xmlns=\"common.xml.idpbiz.product\">" + memberNumber
				+ "</MembershipNumber>\r\n" + "               <ProductLine xmlns=\"common.xml.idpbiz.product\">"
				+ service + "</ProductLine>\r\n"
				+ "               <Username xmlns=\"common.xml.idpbiz.product\">pgvikaskb</Username>\r\n"
				+ "               <ProductAbbr xmlns=\"common.xml.idpbiz.product\">PGP210</ProductAbbr>\r\n"
				+ "               <Source xmlns=\"common.xml.idpbiz.product\">IDP</Source>\r\n"
				+ "               <GUID xmlns=\"common.xml.idpbiz.product\">a0454684f50240abb173f4a13401efff</GUID>\r\n"
				+ "            </RequestCommon>\r\n" + "            <IncludeAssetInfo>true</IncludeAssetInfo>\r\n"
				+ "            <IncludeMonitorInfo>true</IncludeMonitorInfo>\r\n"
				+ "            <IncludePayLoad>true</IncludePayLoad>\r\n" + "         </FindMonitoredAssetRequest>\r\n"
				+ "      </find>\r\n" + "   </soap:Body>\r\n" + "</soap:Envelope>";

		return urlActionRequest;
	}

	public String[] getBankAcctFraudRequest(int memberNumber, String service, int monitorId, String assetValue) {

		String bankAcctNumber = assetValue.split("/")[0];
		String bankRoutingNumber = assetValue.split("/")[1];
		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>BANKACCT_FRAUD_CRE</ven:Operation>\r\n"
				+ "            <FraudBenefitVISResponse xmlns=\"response.fraud.vis.monitor.idp.product\" xmlns:mbsvis=\"vis.monitor.idp.product\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n"
				+ "               <FraudBenefitVISResponseInfo>\r\n" + "                  <ResponseCommon>\r\n"
				+ "                     <mbsvis:Status>Success</mbsvis:Status>\r\n"
				+ "                     <mbsvis:ResponseStatusType>INFO</mbsvis:ResponseStatusType>\r\n"
				+ "                     <mbsvis:Command>BANKACCT_FRAUD_CRE</mbsvis:Command>\r\n"
				+ "                     <mbsvis:GUID>GUID-152399</mbsvis:GUID>\r\n"
				+ "                     <mbsvis:Vendor>CSIDENTITY</mbsvis:Vendor>\r\n"
				+ "                     <mbsvis:MemberNumber>" + memberNumber + "</mbsvis:MemberNumber>\r\n"
				+ "                     <mbsvis:ProductLine>" + service + "</mbsvis:ProductLine>\r\n"
				+ "                  </ResponseCommon>\r\n" + "                  <Monitor>\r\n"
				+ "                     <MonitorID>" + monitorId + "</MonitorID>\r\n"
				+ "                  </Monitor>\r\n" + "                  <Asset>\r\n"
				+ "                     <mbsvis:BankAccountFraudAsset>\r\n"
				+ "                        <mbsvis:BankAccountNumber>" + bankAcctNumber
				+ "</mbsvis:BankAccountNumber>\r\n" + "                        <mbsvis:RoutingNumber>"
				+ bankRoutingNumber + "</mbsvis:RoutingNumber>\r\n"
				+ "                     </mbsvis:BankAccountFraudAsset>\r\n" + "                  </Asset>\r\n"
				+ "                  <FraudBenefitVendorResponeDetails>\r\n"
				+ "                     <compromisedReport>\r\n" + "                        <Record>\r\n"
				+ "                           <MemberID>" + memberNumber + "-" + service + "</MemberID>\r\n"
				+ "                           <ServiceCode>503</ServiceCode>\r\n"
				+ "                           <Date>2010-04-08</Date>\r\n" + "                           <Data>\r\n"
				+ "                              <report>\r\n" + "                                 <Record>\r\n"
				+ "                                    <MemberID>" + memberNumber + "-" + service + "</MemberID>\r\n"
				+ "                                    <ServiceCode>503</ServiceCode>\r\n"
				+ "                                    <Date>2010-04-08</Date>\r\n"
				+ "                                    <Data>\r\n"
				+ "                                       <report>\r\n"
				+ "                                          <record>\r\n"
				+ "                                             <id>19</id>\r\n"
				+ "                                             <namefirst/>\r\n"
				+ "                                             <namemid/>\r\n"
				+ "                                             <namelast/>\r\n"
				+ "                                             <addr1/>\r\n"
				+ "                                             <addr2/>\r\n"
				+ "                                             <addrcity/>\r\n"
				+ "                                             <addrstate/>\r\n"
				+ "                                             <addrzip/>\r\n"
				+ "                                             <addrcountry/>\r\n"
				+ "                                             <phone/>\r\n"
				+ "                                             <phone1/>\r\n"
				+ "                                             <phone2/>\r\n"
				+ "                                             <phone3/>\r\n"
				+ "                                             <phoneext/>\r\n"
				+ "                                             <ssn/>\r\n"
				+ "                                             <mmn/>\r\n"
				+ "                                             <dob/>\r\n"
				+ "                                             <dobyear/>\r\n"
				+ "                                             <dobmon/>\r\n"
				+ "                                             <dobday/>\r\n"
				+ "                                             <dlnumber/>\r\n"
				+ "                                             <dlstate/>\r\n"
				+ "                                             <email/>\r\n"
				+ "                                             <userid/>\r\n"
				+ "                                             <password/>\r\n"
				+ "                                             <cardtype/>\r\n"
				+ "                                             <cardname/>\r\n"
				+ "                                             <cardnum/>\r\n"
				+ "                                             <cardexpdate/>\r\n"
				+ "                                             <cardexpyear/>\r\n"
				+ "                                             <cardexpmon/>\r\n"
				+ "                                             <cardexpday/>\r\n"
				+ "                                             <cardaddr/>\r\n"
				+ "                                             <paypaluid>vikasfraud@yahoo.com</paypaluid>\r\n"
				+ "                                             <paypalpwd>05142002</paypalpwd>\r\n"
				+ "                                             <ebayuid/>\r\n"
				+ "                                             <ebaypwd/>\r\n"
				+ "                                             <bankname/>\r\n"
				+ "                                             <bankphone>1-800-841-4000</bankphone>\r\n"
				+ "                                             <bankroute>" + bankRoutingNumber + "</bankroute>\r\n"
				+ "                                             <bankacct>" + bankAcctNumber + "</bankacct>\r\n"
				+ "                                             <bankuid/>\r\n"
				+ "                                             <bankpwd/>\r\n"
				+ "                                             <sha1>6675185c2c91f9b608c19e0d513f3520b27d4b9a</sha1>\r\n"
				+ "                                             <Source>/work/ca/2006-07-11-16-43-30-temp/2006-04-07-CCPower.EliteIRC.tmp</Source>\r\n"
				+ "                                             <SourceDate>2006-04-07</SourceDate>\r\n"
				+ "                                             <UpdateDate>2006-07-11 18:47:05</UpdateDate>\r\n"
				+ "                                             <CreationDate>2006-07-11 18:47:05</CreationDate>\r\n"
				+ "                                             <note/>\r\n"
				+ "                                             <match>IDBANKROUTE,IDBANKACCT</match>\r\n"
				+ "                                          </record>\r\n"
				+ "                                       </report>\r\n"
				+ "                                    </Data>\r\n" + "                                 </Record>\r\n"
				+ "                              </report>\r\n" + "                           </Data>\r\n"
				+ "                        </Record>\r\n" + "                     </compromisedReport>\r\n"
				+ "                  </FraudBenefitVendorResponeDetails>\r\n"
				+ "               </FraudBenefitVISResponseInfo>\r\n" + "            </FraudBenefitVISResponse>\r\n"
				+ "         </ven:VendorEventRequest>\r\n" + "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";

		return urlActionRequest;

	}

	public String[] getCcFraudtRequest(int memberNumber, String service, int monitorId) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>CC_FRAUD_CRE</ven:Operation>\r\n"
				+ "            <FraudBenefitVISResponse xmlns=\"response.fraud.vis.monitor.idp.product\" xmlns:mbsvis=\"vis.monitor.idp.product\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n"
				+ "               <FraudBenefitVISResponseInfo>\r\n" + "                  <ResponseCommon>\r\n"
				+ "                     <mbsvis:Status>Success</mbsvis:Status>\r\n"
				+ "                     <mbsvis:ResponseStatusType>INFO</mbsvis:ResponseStatusType>\r\n"
				+ "                     <mbsvis:Command>CC_FRAUD_CRE</mbsvis:Command>\r\n"
				+ "                     <mbsvis:GUID>GUID-152400</mbsvis:GUID>\r\n"
				+ "                     <mbsvis:Vendor>CSIDENTITY</mbsvis:Vendor>\r\n"
				+ "                     <mbsvis:MemberNumber>" + memberNumber + "</mbsvis:MemberNumber>\r\n"
				+ "                     <mbsvis:ProductLine>" + service + "</mbsvis:ProductLine>\r\n"
				+ "                  </ResponseCommon>\r\n" + "                  <Monitor>\r\n"
				+ "                     <MonitorID>" + monitorId + "</MonitorID>\r\n"
				+ "                  </Monitor>\r\n" + "                  <FraudBenefitVendorResponeDetails>\r\n"
				+ "                     <compromisedReport>\r\n" + "                        <Record>\r\n"
				+ "                           <MemberID>" + memberNumber + "-" + service + "</MemberID>\r\n"
				+ "                           <ServiceCode>503</ServiceCode>\r\n"
				+ "                           <Date>2019-08-03</Date>\r\n" + "                           <Data>\r\n"
				+ "                              <report>\r\n" + "                                 <Record>\r\n"
				+ "                                    <MemberID>" + memberNumber + "-" + service + "</MemberID>\r\n"
				+ "                                    <ServiceCode>503</ServiceCode>\r\n"
				+ "                                    <Date>2019-08-03</Date>\r\n"
				+ "                                 </Record>\r\n" + "                              </report>\r\n"
				+ "                           </Data>\r\n" + "                        </Record>\r\n"
				+ "                     </compromisedReport>\r\n"
				+ "                  </FraudBenefitVendorResponeDetails>\r\n"
				+ "               </FraudBenefitVISResponseInfo>\r\n" + "            </FraudBenefitVISResponse>\r\n"
				+ "         </ven:VendorEventRequest>\r\n" + "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>\r\n" + "";

		return urlActionRequest;

	}

	public String[] getDossierMtrRequest(int memberNumber, String service, int monitorId) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.myid.alert.vis.monitor.idp.product\" xmlns:even=\"event.myid.xml.batch.idp.product\" xmlns:res4=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res5=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssnmonitoring.xml.idp.infrastructure\" xmlns:ins=\"instantid.xml.idp.infrastructure\" xmlns:prot=\"protectionreminders.xml.idp.infrastructure\" xmlns:res6=\"response.ssnauth.vis.monitor.idp.product\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>DOSSIER_MTR_REQ</ven:Operation>\r\n"
				+ "            <resp:PublicRecordsBenefitVISResponse xmlns:resp=\"response.prr.vis.monitor.idp.product\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:lx=\"http://webservices.seisint.com/WsAccurint\" xmlns:cmn=\"common.xml.idp.infrastructure\">\r\n"
				+ "               <resp:PublicRecordsBenefitVISResponseInfo>\r\n"
				+ "                  <resp:ResponseCommon>\r\n"
				+ "                     <vis:Status>SUCCESS</vis:Status>\r\n"
				+ "                     <vis:ResponseStatusType>INFO</vis:ResponseStatusType>\r\n"
				+ "                     <vis:RoundTripTimeMillis>547</vis:RoundTripTimeMillis>\r\n"
				+ "                     <vis:Command>DOSSIER_MTR_REQ</vis:Command>\r\n"
				+ "                     <vis:GUID>55690623</vis:GUID>\r\n" + "                     <vis:Vendor/>\r\n"
				+ "                     <vis:MemberNumber>" + memberNumber + "</vis:MemberNumber>\r\n"
				+ "                     <vis:ProductLine>" + service + "</vis:ProductLine>\r\n"
				+ "                  </resp:ResponseCommon>\r\n" + "                  <resp:Monitor>\r\n"
				+ "                     <resp:MonitorID>" + monitorId + "</resp:MonitorID>\r\n"
				+ "                  </resp:Monitor>\r\n" + "                  <resp:Details>\r\n"
				+ "                     <prr:PRRQueryResponse>\r\n"
				+ "                        <prr:CategoryResponses>\r\n"
				+ "                           <prr:PhonesResponse>\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:PhonesResponse>\r\n"
				+ "                           <prr:DeathResponse>\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:DeathResponse>\r\n"
				+ "                           <prr:UCCResponse>\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:UCCResponse>\r\n"
				+ "                           <prr:GlobalWatchListResponse>\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:GlobalWatchListResponse>\r\n"
				+ "                           <prr:PropertyResponse>\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:PropertyResponse>\r\n"
				+ "                           <prr:CorporateAffiliationsResponse PDEFCategory=\"CORPORATE\">\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:CorporateAffiliationsResponse>\r\n"
				+ "                           <prr:CriminalResponse>\r\n"
				+ "                              <cat:CriminalRecord>\r\n"
				+ "                                 <cat:CaseNumber>2202000CR 000282</cat:CaseNumber>\r\n"
				+ "                                 <cat:CountyOfOrigin>CLEVELAND</cat:CountyOfOrigin>\r\n"
				+ "                                 <cat:DOCNumber>NF101544738796678572082202000CR00028220000229</cat:DOCNumber>\r\n"
				+ "                                 <cat:Race>BLACK</cat:Race>\r\n"
				+ "                                 <cat:Sex>Female</cat:Sex>\r\n"
				+ "                                 <cat:SSN>xxxxxxxxx</cat:SSN>\r\n"
				+ "                                 <cat:StateOfOrigin>North Carolina</cat:StateOfOrigin>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>2815 PHILADELPHIA RD</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>LAWNDALE</vis:City>\r\n"
				+ "                                       <vis:State>NC</vis:State>\r\n"
				+ "                                       <vis:Zip>28090-9134</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>RACHEL</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>ARCHIE</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName>J</cmn:MiddleName>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix/>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:DOB>1968-10-03</cat:DOB>\r\n"
				+ "                                 <cat:Offenses>\r\n"
				+ "                                    <cat:Offense/>\r\n"
				+ "                                 </cat:Offenses>\r\n"
				+ "                              </cat:CriminalRecord>\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:CriminalResponse>\r\n"
				+ "                           <prr:LienJudgmentResponse PDEFCategory=\"LIENJUDGMENTS\">\r\n"
				+ "                              <cat:LienJudgmentRecord>\r\n"
				+ "                                 <cat:Amount>58170</cat:Amount>\r\n"
				+ "                                 <cat:OriginFilingDate>2019-04-01</cat:OriginFilingDate>\r\n"
				+ "                                 <cat:FilingState>NY</cat:FilingState>\r\n"
				+ "                                 <cat:Creditors>\r\n"
				+ "                                    <cat:Creditor>\r\n"
				+ "                                       <cat:Name>INTERNAL REVENUE SERVICE</cat:Name>\r\n"
				+ "                                    </cat:Creditor>\r\n"
				+ "                                 </cat:Creditors>\r\n"
				+ "                              </cat:LienJudgmentRecord>\r\n"
				+ "                              <cat:LienJudgmentRecord>\r\n"
				+ "                                 <cat:Amount>34204</cat:Amount>\r\n"
				+ "                                 <cat:OriginFilingDate>2017-08-21</cat:OriginFilingDate>\r\n"
				+ "                                 <cat:FilingState>NY</cat:FilingState>\r\n"
				+ "                                 <cat:Creditors>\r\n"
				+ "                                    <cat:Creditor>\r\n"
				+ "                                       <cat:Name>CFG MERCHANT SOLUTIONS LLC</cat:Name>\r\n"
				+ "                                    </cat:Creditor>\r\n"
				+ "                                 </cat:Creditors>\r\n"
				+ "                              </cat:LienJudgmentRecord>\r\n"
				+ "                              <cat:LienJudgmentRecord>\r\n"
				+ "                                 <cat:Amount>77404</cat:Amount>\r\n"
				+ "                                 <cat:OriginFilingDate>2017-06-05</cat:OriginFilingDate>\r\n"
				+ "                                 <cat:FilingState>NY</cat:FilingState>\r\n"
				+ "                                 <cat:Creditors>\r\n"
				+ "                                    <cat:Creditor>\r\n"
				+ "                                       <cat:Name>YELLOWSTONE CAPITAL LLC</cat:Name>\r\n"
				+ "                                    </cat:Creditor>\r\n"
				+ "                                 </cat:Creditors>\r\n"
				+ "                              </cat:LienJudgmentRecord>\r\n"
				+ "                              <cat:LienJudgmentRecord>\r\n"
				+ "                                 <cat:Amount>10643</cat:Amount>\r\n"
				+ "                                 <cat:OriginFilingDate>2007-04-06</cat:OriginFilingDate>\r\n"
				+ "                                 <cat:FilingState>NY</cat:FilingState>\r\n"
				+ "                                 <cat:Creditors>\r\n"
				+ "                                    <cat:Creditor>\r\n"
				+ "                                       <cat:Name>INTERNAL REVENUE SERVICE</cat:Name>\r\n"
				+ "                                    </cat:Creditor>\r\n"
				+ "                                 </cat:Creditors>\r\n"
				+ "                              </cat:LienJudgmentRecord>\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:LienJudgmentResponse>\r\n"
				+ "                           <prr:BankruptcyResponse PDEFCategory=\"BANKRUPTCY\">\r\n"
				+ "                              <cat:BankruptcyRecord>\r\n"
				+ "                                 <cat:CaseNumber>9901216</cat:CaseNumber>\r\n"
				+ "                                 <cat:Chapter>13</cat:Chapter>\r\n"
				+ "                                 <cat:FilingStatus>DISCHARGED</cat:FilingStatus>\r\n"
				+ "                                 <cat:FilingDate>1999-03-02</cat:FilingDate>\r\n"
				+ "                                 <cat:DischargeDate>2004-03-05</cat:DischargeDate>\r\n"
				+ "                              </cat:BankruptcyRecord>\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:BankruptcyResponse>\r\n"
				+ "                           <prr:AircraftResponse PDEFCategory=\"AIRCRAFT\">\r\n"
				+ "                              <cat:TransactionId>129364942R185130</cat:TransactionId>\r\n"
				+ "                           </prr:AircraftResponse>\r\n"
				+ "                        </prr:CategoryResponses>\r\n"
				+ "                     </prr:PRRQueryResponse>\r\n" + "                  </resp:Details>\r\n"
				+ "               </resp:PublicRecordsBenefitVISResponseInfo>\r\n"
				+ "            </resp:PublicRecordsBenefitVISResponse>\r\n" + "         </ven:VendorEventRequest>\r\n"
				+ "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		return urlActionRequest;

	}

	public String[] getDossierRptRequest(int memberNumber, String service, int monitorId) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.myid.alert.vis.monitor.idp.product\" xmlns:even=\"event.myid.xml.batch.idp.product\" xmlns:res4=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res5=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssnmonitoring.xml.idp.infrastructure\" xmlns:ins=\"instantid.xml.idp.infrastructure\" xmlns:prot=\"protectionreminders.xml.idp.infrastructure\" xmlns:res6=\"response.ssnauth.vis.monitor.idp.product\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>DOSSIER_RPT_REQ</ven:Operation>\r\n"
				+ "            <resp:PublicRecordsBenefitVISResponse xmlns:resp=\"response.prr.vis.monitor.idp.product\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:lx=\"http://webservices.seisint.com/WsAccurint\" xmlns:cmn=\"common.xml.idp.infrastructure\">\r\n"
				+ "               <resp:PublicRecordsBenefitVISResponseInfo>\r\n"
				+ "                  <resp:ResponseCommon>\r\n"
				+ "                     <vis:Status>SUCCESS</vis:Status>\r\n"
				+ "                     <vis:ResponseStatusType>INFO</vis:ResponseStatusType>\r\n"
				+ "                     <vis:RoundTripTimeMillis>759</vis:RoundTripTimeMillis>\r\n"
				+ "                     <vis:Command>DOSSIER_RPT_REQ</vis:Command>\r\n"
				+ "                     <vis:GUID>29927565</vis:GUID>\r\n" + "                     <vis:Vendor/>\r\n"
				+ "                     <vis:MemberNumber>" + memberNumber + "</vis:MemberNumber>\r\n"
				+ "                     <vis:ProductLine>" + service + "</vis:ProductLine>\r\n"
				+ "                  </resp:ResponseCommon>\r\n" + "                  <resp:Monitor>\r\n"
				+ "                     <resp:MonitorID>" + monitorId + "</resp:MonitorID>\r\n"
				+ "                  </resp:Monitor>\r\n" + "                  <resp:Details>\r\n"
				+ "                     <prr:PRRQueryResponse>\r\n"
				+ "                        <prr:CategoryResponses>\r\n"
				+ "                           <prr:PhonesResponse>\r\n"
				+ "                              <cat:PhoneRecord>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>MICHAEL</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>JONKINS</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName/>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix/>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>4318 TIDEWATER DR</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>HOUSTON</vis:City>\r\n"
				+ "                                       <vis:State>TX</vis:State>\r\n"
				+ "                                       <vis:Zip>77045-4342</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:PhoneNumber>8324044131</cat:PhoneNumber>\r\n"
				+ "                                 <cat:ListedName>MICHAEL K JONKINS</cat:ListedName>\r\n"
				+ "                              </cat:PhoneRecord>\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:PhonesResponse>\r\n"
				+ "                           <prr:DeathResponse>\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:DeathResponse>\r\n"
				+ "                           <prr:UCCResponse>\r\n"
				+ "                              <cat:UCCRecord>\r\n"
				+ "                                 <cat:OriginFilingNumber>20030727025</cat:OriginFilingNumber>\r\n"
				+ "                                 <cat:OriginFilingDate>2003-08-12</cat:OriginFilingDate>\r\n"
				+ "                                 <cat:Filings>\r\n"
				+ "                                    <cat:Filing>\r\n"
				+ "                                       <cat:Number>20040235007</cat:Number>\r\n"
				+ "                                       <cat:Type>TERMINATION</cat:Type>\r\n"
				+ "                                       <cat:Date>2004-03-05</cat:Date>\r\n"
				+ "                                    </cat:Filing>\r\n"
				+ "                                 </cat:Filings>\r\n"
				+ "                              </cat:UCCRecord>\r\n"
				+ "                              <cat:UCCRecord>\r\n"
				+ "                                 <cat:OriginFilingNumber>20030727025</cat:OriginFilingNumber>\r\n"
				+ "                                 <cat:OriginFilingDate>2003-08-11</cat:OriginFilingDate>\r\n"
				+ "                                 <cat:Filings>\r\n"
				+ "                                    <cat:Filing>\r\n"
				+ "                                       <cat:Number>20030727025</cat:Number>\r\n"
				+ "                                       <cat:Type>INITIAL FILING</cat:Type>\r\n"
				+ "                                       <cat:Date>2003-08-11</cat:Date>\r\n"
				+ "                                    </cat:Filing>\r\n"
				+ "                                 </cat:Filings>\r\n"
				+ "                                 <cat:Collaterals>\r\n"
				+ "                                    <cat:Collateral>\r\n"
				+ "                                       <cat:Description>08/11/2003 20030727025 - GENERAL INTANGIBLE(S) INCLUDING PROCEEDS AND PRODUCTS;ACCOUNT(S) INCLUDING PROCEEDS AND PRODUCTS;ACCOUNTS RECEIVABLE INCLUDING PROCEEDS AND PRODUCTS;CONTRACT RIGHTS INCLUDING PROCEEDS AND PRODUCTS;NEGOTIABLE INSTRUMENTS INCLUDING PROCEEDS AND PRODUCTS;CHATTEL PAPER INCLUDING PROCEEDS AND PRODUCTS;INVENTORY INCLUDING PROCEEDS AND PRODUCTS;EQUIPMENT INCLUDING PROCEEDS AND PRODUCTS;FIXTURES INCLUDING PROCEEDS AND PRODUCTS;MACHINERY INCLUDING PROCEEDS AND PRODUCTS;VEHICLES INCLUDING PRO</cat:Description>\r\n"
				+ "                                    </cat:Collateral>\r\n"
				+ "                                 </cat:Collaterals>\r\n"
				+ "                              </cat:UCCRecord>\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:UCCResponse>\r\n"
				+ "                           <prr:GlobalWatchListResponse>\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:GlobalWatchListResponse>\r\n"
				+ "                           <prr:PropertyResponse>\r\n"
				+ "                              <cat:PropertyRecord>\r\n"
				+ "                                 <cat:PropertyAddress>1890 HONEYCHUCK LN  KENT  OH  44240</cat:PropertyAddress>\r\n"
				+ "                                 <cat:OwnerOccupied>Y</cat:OwnerOccupied>\r\n"
				+ "                                 <cat:SalesPrice>279000</cat:SalesPrice>\r\n"
				+ "                                 <cat:AssessedTotalValue>93450</cat:AssessedTotalValue>\r\n"
				+ "                                 <cat:YearBuilt>2007</cat:YearBuilt>\r\n"
				+ "                                 <cat:NoOfStories>2+AB</cat:NoOfStories>\r\n"
				+ "                                 <cat:NoOfBedrooms>3</cat:NoOfBedrooms>\r\n"
				+ "                                 <cat:NoOfBaths>2.00</cat:NoOfBaths>\r\n"
				+ "                                 <cat:LandAcres>0.42</cat:LandAcres>\r\n"
				+ "                                 <cat:GarageType>GARAGE</cat:GarageType>\r\n"
				+ "                              </cat:PropertyRecord>\r\n"
				+ "                              <cat:PropertyRecord>\r\n"
				+ "                                 <cat:PropertyAddress>1890 HONEYCHUCK LN  KENT  OH  44240</cat:PropertyAddress>\r\n"
				+ "                              </cat:PropertyRecord>\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:PropertyResponse>\r\n"
				+ "                           <prr:CorporateAffiliationsResponse PDEFCategory=\"CORPORATE\">\r\n"
				+ "                              <cat:CorporateAffiliationRecord>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>GIGI</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>ABBO</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName>P</cmn:MiddleName>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix>MS</cmn:Prefix>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:CompanyName>DELAIRE LOT 26, LLC</cat:CompanyName>\r\n"
				+ "                                 <cat:Gender>Female</cat:Gender>\r\n"
				+ "                                 <cat:Title>MEMBER MANAGER</cat:Title>\r\n"
				+ "                                 <cat:State>FL</cat:State>\r\n"
				+ "                                 <cat:CorporationNumber>L11000085724</cat:CorporationNumber>\r\n"
				+ "                                 <cat:Status>INACTIVE</cat:Status>\r\n"
				+ "                                 <cat:StatusDescription>INACTIVE</cat:StatusDescription>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>2472 64TH ST</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>BOCA RATON</vis:City>\r\n"
				+ "                                       <vis:State>FL</vis:State>\r\n"
				+ "                                       <vis:Zip>33496-3620</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:FilingDate>2014-09-26</cat:FilingDate>\r\n"
				+ "                              </cat:CorporateAffiliationRecord>\r\n"
				+ "                              <cat:CorporateAffiliationRecord>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>GIGI</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>ABBO</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName>P</cmn:MiddleName>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix>MS</cmn:Prefix>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:CompanyName>GPA, LLC</cat:CompanyName>\r\n"
				+ "                                 <cat:Gender>Female</cat:Gender>\r\n"
				+ "                                 <cat:Title>MEMBER MANAGER</cat:Title>\r\n"
				+ "                                 <cat:State>FL</cat:State>\r\n"
				+ "                                 <cat:CorporationNumber>L13000080038</cat:CorporationNumber>\r\n"
				+ "                                 <cat:Status>ACTIVE</cat:Status>\r\n"
				+ "                                 <cat:StatusDescription>ACTIVE</cat:StatusDescription>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>2472 64TH ST</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>BOCA RATON</vis:City>\r\n"
				+ "                                       <vis:State>FL</vis:State>\r\n"
				+ "                                       <vis:Zip>33496-3620</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:FilingDate>2013-06-03</cat:FilingDate>\r\n"
				+ "                              </cat:CorporateAffiliationRecord>\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:CorporateAffiliationsResponse>\r\n"
				+ "                           <prr:CriminalResponse>\r\n"
				+ "                              <cat:CriminalRecord>\r\n"
				+ "                                 <cat:CaseNumber>2018TRD02386R</cat:CaseNumber>\r\n"
				+ "                                 <cat:CountyOfOrigin>PORTAGE RAVENNA</cat:CountyOfOrigin>\r\n"
				+ "                                 <cat:DOCNumber>I00243870631128581554472018TRD02386R20180227</cat:DOCNumber>\r\n"
				+ "                                 <cat:Race>WHITE</cat:Race>\r\n"
				+ "                                 <cat:Sex>Male</cat:Sex>\r\n"
				+ "                                 <cat:SSN>xxxxxxxxx</cat:SSN>\r\n"
				+ "                                 <cat:StateOfOrigin>Ohio</cat:StateOfOrigin>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>1890 HONEYCHUCK LN</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>KENT</vis:City>\r\n"
				+ "                                       <vis:State>OH</vis:State>\r\n"
				+ "                                       <vis:Zip>44240-5662</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>BENJAMEN</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>BRUGLER</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName>K</cmn:MiddleName>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix/>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:DOB>1976-10-04</cat:DOB>\r\n"
				+ "                                 <cat:Offenses>\r\n"
				+ "                                    <cat:Offense>\r\n"
				+ "                                       <cat:OffenseDate>2018-02-18</cat:OffenseDate>\r\n"
				+ "                                    </cat:Offense>\r\n"
				+ "                                 </cat:Offenses>\r\n"
				+ "                              </cat:CriminalRecord>\r\n"
				+ "                              <cat:CriminalRecord>\r\n"
				+ "                                 <cat:CaseNumber>2016TRD16529R</cat:CaseNumber>\r\n"
				+ "                                 <cat:CountyOfOrigin>PORTAGE RAVENNA</cat:CountyOfOrigin>\r\n"
				+ "                                 <cat:DOCNumber>I00243870631128581554472016TRD16529R20161129</cat:DOCNumber>\r\n"
				+ "                                 <cat:Race>WHITE</cat:Race>\r\n"
				+ "                                 <cat:Sex>Male</cat:Sex>\r\n"
				+ "                                 <cat:SSN>xxxxxxxxx</cat:SSN>\r\n"
				+ "                                 <cat:StateOfOrigin>Ohio</cat:StateOfOrigin>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>1890 HONEYCHUCK LN</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>KENT</vis:City>\r\n"
				+ "                                       <vis:State>OH</vis:State>\r\n"
				+ "                                       <vis:Zip>44240-5662</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>BENJAMEN</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>BRUGLER</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName>K</cmn:MiddleName>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix/>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:DOB>1976-10-04</cat:DOB>\r\n"
				+ "                                 <cat:Offenses>\r\n"
				+ "                                    <cat:Offense>\r\n"
				+ "                                       <cat:OffenseDate>2016-11-24</cat:OffenseDate>\r\n"
				+ "                                    </cat:Offense>\r\n"
				+ "                                 </cat:Offenses>\r\n"
				+ "                              </cat:CriminalRecord>\r\n"
				+ "                              <cat:CriminalRecord>\r\n"
				+ "                                 <cat:CaseNumber>2014TRD16685R</cat:CaseNumber>\r\n"
				+ "                                 <cat:CountyOfOrigin>PORTAGE RAVENNA</cat:CountyOfOrigin>\r\n"
				+ "                                 <cat:DOCNumber>I00243870631128581554472014TRD16685R20141209</cat:DOCNumber>\r\n"
				+ "                                 <cat:Race>WHITE</cat:Race>\r\n"
				+ "                                 <cat:Sex>Male</cat:Sex>\r\n"
				+ "                                 <cat:SSN>xxxxxxxxx</cat:SSN>\r\n"
				+ "                                 <cat:StateOfOrigin>Ohio</cat:StateOfOrigin>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>1890 HONEYCHUCK LN</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>KENT</vis:City>\r\n"
				+ "                                       <vis:State>OH</vis:State>\r\n"
				+ "                                       <vis:Zip>44240-5662</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>BENJAMEN</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>BRUGLER</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName>K</cmn:MiddleName>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix/>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:DOB>1976-10-04</cat:DOB>\r\n"
				+ "                                 <cat:Offenses>\r\n"
				+ "                                    <cat:Offense>\r\n"
				+ "                                       <cat:OffenseDate>2014-12-08</cat:OffenseDate>\r\n"
				+ "                                    </cat:Offense>\r\n"
				+ "                                 </cat:Offenses>\r\n"
				+ "                              </cat:CriminalRecord>\r\n"
				+ "                              <cat:CriminalRecord>\r\n"
				+ "                                 <cat:CaseNumber>2008TRD05176</cat:CaseNumber>\r\n"
				+ "                                 <cat:CountyOfOrigin>SUMMIT</cat:CountyOfOrigin>\r\n"
				+ "                                 <cat:DOCNumber>I0083113294065337652532672008TRD0517620080408</cat:DOCNumber>\r\n"
				+ "                                 <cat:SSN>xxxxxxxxx</cat:SSN>\r\n"
				+ "                                 <cat:StateOfOrigin>Ohio</cat:StateOfOrigin>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>1970 THORNHILL DR</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>AKRON</vis:City>\r\n"
				+ "                                       <vis:State>OH</vis:State>\r\n"
				+ "                                       <vis:Zip>44313-5462</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>BENJAMIN</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>BRUGLER</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName>K</cmn:MiddleName>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix/>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:DOB>1976-10-04</cat:DOB>\r\n"
				+ "                                 <cat:Offenses>\r\n"
				+ "                                    <cat:Offense>\r\n"
				+ "                                       <cat:OffenseDate>2008-04-03</cat:OffenseDate>\r\n"
				+ "                                    </cat:Offense>\r\n"
				+ "                                    <cat:Offense>\r\n"
				+ "                                       <cat:OffenseDate>2008-04-03</cat:OffenseDate>\r\n"
				+ "                                    </cat:Offense>\r\n"
				+ "                                 </cat:Offenses>\r\n"
				+ "                              </cat:CriminalRecord>\r\n"
				+ "                              <cat:CriminalRecord>\r\n"
				+ "                                 <cat:CaseNumber>1999 TRD 006601-01</cat:CaseNumber>\r\n"
				+ "                                 <cat:CountyOfOrigin>TRUMBULL</cat:CountyOfOrigin>\r\n"
				+ "                                 <cat:DOCNumber>9A113294065337652532671999TRD006601-0119990908</cat:DOCNumber>\r\n"
				+ "                                 <cat:SSN>xxxxxxxxx</cat:SSN>\r\n"
				+ "                                 <cat:StateOfOrigin>Ohio</cat:StateOfOrigin>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>6707 STATE ROUTE 45</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>BRISTOLVILLE</vis:City>\r\n"
				+ "                                       <vis:State>OH</vis:State>\r\n"
				+ "                                       <vis:Zip>44402-8700</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>BENJAMIN</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>BRUGLER</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName>K</cmn:MiddleName>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix/>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:DOB>1976-10-04</cat:DOB>\r\n"
				+ "                                 <cat:Offenses>\r\n"
				+ "                                    <cat:Offense/>\r\n"
				+ "                                 </cat:Offenses>\r\n"
				+ "                              </cat:CriminalRecord>\r\n"
				+ "                              <cat:CriminalRecord>\r\n"
				+ "                                 <cat:CaseNumber>1999 TRD 006601-02</cat:CaseNumber>\r\n"
				+ "                                 <cat:CountyOfOrigin>TRUMBULL</cat:CountyOfOrigin>\r\n"
				+ "                                 <cat:DOCNumber>9A113294065337652532671999TRD006601-0219990908</cat:DOCNumber>\r\n"
				+ "                                 <cat:SSN>xxxxxxxxx</cat:SSN>\r\n"
				+ "                                 <cat:StateOfOrigin>Ohio</cat:StateOfOrigin>\r\n"
				+ "                                 <cat:Address>\r\n"
				+ "                                    <cat:Address>\r\n"
				+ "                                       <vis:Address1>6707 STATE ROUTE 45</vis:Address1>\r\n"
				+ "                                       <vis:Address2/>\r\n"
				+ "                                       <vis:City>BRISTOLVILLE</vis:City>\r\n"
				+ "                                       <vis:State>OH</vis:State>\r\n"
				+ "                                       <vis:Zip>44402-8700</vis:Zip>\r\n"
				+ "                                    </cat:Address>\r\n"
				+ "                                 </cat:Address>\r\n"
				+ "                                 <cat:Name>\r\n"
				+ "                                    <cmn:FirstName>BENJAMIN</cmn:FirstName>\r\n"
				+ "                                    <cmn:LastName>BRUGLER</cmn:LastName>\r\n"
				+ "                                    <cmn:MiddleName>K</cmn:MiddleName>\r\n"
				+ "                                    <cmn:Suffix/>\r\n"
				+ "                                    <cmn:Prefix/>\r\n"
				+ "                                 </cat:Name>\r\n"
				+ "                                 <cat:DOB>1976-10-04</cat:DOB>\r\n"
				+ "                                 <cat:Offenses>\r\n"
				+ "                                    <cat:Offense/>\r\n"
				+ "                                 </cat:Offenses>\r\n"
				+ "                              </cat:CriminalRecord>\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:CriminalResponse>\r\n"
				+ "                           <prr:LienJudgmentResponse PDEFCategory=\"LIENJUDGMENTS\">\r\n"
				+ "                              <cat:LienJudgmentRecord>\r\n"
				+ "                                 <cat:Amount>1697</cat:Amount>\r\n"
				+ "                                 <cat:OriginFilingDate>2018-08-10</cat:OriginFilingDate>\r\n"
				+ "                                 <cat:FilingState>TN</cat:FilingState>\r\n"
				+ "                                 <cat:Creditors>\r\n"
				+ "                                    <cat:Creditor>\r\n"
				+ "                                       <cat:Name>REPUBLIC FINANCE</cat:Name>\r\n"
				+ "                                    </cat:Creditor>\r\n"
				+ "                                 </cat:Creditors>\r\n"
				+ "                              </cat:LienJudgmentRecord>\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:LienJudgmentResponse>\r\n"
				+ "                           <prr:BankruptcyResponse PDEFCategory=\"BANKRUPTCY\">\r\n"
				+ "                              <cat:BankruptcyRecord>\r\n"
				+ "                                 <cat:CaseNumber>9690364</cat:CaseNumber>\r\n"
				+ "                                 <cat:Chapter>7</cat:Chapter>\r\n"
				+ "                                 <cat:FilingDate>1996-02-29</cat:FilingDate>\r\n"
				+ "                                 <cat:DischargeDate>1996-06-13</cat:DischargeDate>\r\n"
				+ "                              </cat:BankruptcyRecord>\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:BankruptcyResponse>\r\n"
				+ "                           <prr:AircraftResponse PDEFCategory=\"AIRCRAFT\">\r\n"
				+ "                              <cat:TransactionId>159979383R477560</cat:TransactionId>\r\n"
				+ "                           </prr:AircraftResponse>\r\n"
				+ "                        </prr:CategoryResponses>\r\n"
				+ "                     </prr:PRRQueryResponse>\r\n" + "                  </resp:Details>\r\n"
				+ "               </resp:PublicRecordsBenefitVISResponseInfo>\r\n"
				+ "            </resp:PublicRecordsBenefitVISResponse>\r\n" + "         </ven:VendorEventRequest>\r\n"
				+ "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		return urlActionRequest;

	}

	public String[] getFraudAssistRequest(int memberNumber) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.myid.alert.vis.monitor.idp.product\" xmlns:even=\"event.myid.xml.batch.idp.product\" xmlns:res4=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res5=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssnmonitoring.xml.idp.infrastructure\" xmlns:ins=\"instantid.xml.idp.infrastructure\" xmlns:prot=\"protectionreminders.xml.idp.infrastructure\" xmlns:res6=\"response.ssnauth.vis.monitor.idp.product\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>FRAUD_ASSIST_ALERT</ven:Operation>\r\n"
				+ "            <!--Optional:-->\r\n"
				+ "            <ns0:RemindersBenefitVISResponse xmlns:ns0=\"protectionreminders.xml.idp.infrastructure\">\r\n"
				+ "               <ns0:RemindersBenefitVISResponseInfo>\r\n"
				+ "                  <ns0:ResponseCommon>\r\n"
				+ "                     <vis:Status>Success</vis:Status>\r\n"
				+ "                     <vis:ResponseStatusType>INFO</vis:ResponseStatusType>\r\n"
				+ "                     <vis:Command>FRAUD_ASSIST_ALERT</vis:Command>\r\n"
				+ "                     <vis:GUID>15898VIK40180</vis:GUID>\r\n"
				+ "                     <vis:MemberNumber>" + memberNumber + "</vis:MemberNumber>\r\n"
				+ "                     <vis:ProductLine>GRD</vis:ProductLine>\r\n"
				+ "                  </ns0:ResponseCommon>\r\n" + "                  <ns0:Details>\r\n"
				+ "                     <ns0:Notification>\r\n"
				+ "                        <ns0:Content>Prevention Services\r\n" + "\r\n"
				+ "TASKS MORE THAN ONE WEEK OLD\r\n" + "	Choose Contact Method for the Do Not Call Registry\r\n"
				+ "	Check Status of Opt-out Request\r\n" + "	Set credit bureau fraud alerts</ns0:Content>\r\n"
				+ "                        <ns0:Description>TasksDue</ns0:Description>\r\n"
				+ "                     </ns0:Notification>\r\n" + "                  </ns0:Details>\r\n"
				+ "               </ns0:RemindersBenefitVISResponseInfo>\r\n"
				+ "            </ns0:RemindersBenefitVISResponse>\r\n" + "         </ven:VendorEventRequest>\r\n"
				+ "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		return urlActionRequest;

	}

	public String[] getInstantIMtrdRequest(int memberNumber, String service, int monitorId) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>INSTANTID_MTR_ALERT</ven:Operation>\r\n"
				+ "            <ns2:InstantIdBenefitVISResponse xmlns:ns2=\"instantid.xml.idp.infrastructure\">\r\n"
				+ "               <ns2:InstantIdBenefitVISResponseInfo>\r\n"
				+ "                  <ns2:ResponseCommon>\r\n"
				+ "                     <mbsvis:Status xmlns:mbsvis=\"vis.monitor.idp.product\">Success</mbsvis:Status>\r\n"
				+ "                     <mbsvis:ResponseStatusType xmlns:mbsvis=\"vis.monitor.idp.product\">INFO</mbsvis:ResponseStatusType>\r\n"
				+ "                     <mbsvis:RoundTripTimeMillis xmlns:mbsvis=\"vis.monitor.idp.product\">244</mbsvis:RoundTripTimeMillis>\r\n"
				+ "                     <mbsvis:Command xmlns:mbsvis=\"vis.monitor.idp.product\">INSTANTID_MTR_ALERT</mbsvis:Command>\r\n"
				+ "                     <mbsvis:GUID xmlns:mbsvis=\"vis.monitor.idp.product\">1589370340455</mbsvis:GUID>\r\n"
				+ "                     <mbsvis:MemberNumber xmlns:mbsvis=\"vis.monitor.idp.product\">" + memberNumber
				+ "</mbsvis:MemberNumber>\r\n"
				+ "                     <mbsvis:ProductLine xmlns:mbsvis=\"vis.monitor.idp.product\">" + service
				+ "</mbsvis:ProductLine>\r\n" + "                  </ns2:ResponseCommon>\r\n"
				+ "                  <ns2:Monitor>\r\n" + "                     <ns2:MonitorID>" + monitorId
				+ "</ns2:MonitorID>\r\n" + "                  </ns2:Monitor>\r\n"
				+ "                  <ns2:VendorDetails>\r\n"
				+ "                     <mbsvis:RequestId xmlns:mbsvis=\"vis.monitor.idp.product\">1=158937018892768;2=26020000357046</mbsvis:RequestId>\r\n"
				+ "                  </ns2:VendorDetails>\r\n" + "                  <ns2:Details>\r\n"
				+ "                     <ns2:Notification>\r\n"
				+ "                        <ns2:TransactionId>31005354699558</ns2:TransactionId>\r\n"
				+ "                        <ns2:UserId>" + memberNumber + "-" + service + "-" + monitorId
				+ "</ns2:UserId>\r\n"
				+ "                        <ns2:TransactionTime>2020-05-13T11:32:45.451Z</ns2:TransactionTime>\r\n"
				+ "                        <ns2:TransactionType>iAuth</ns2:TransactionType>\r\n"
				+ "                        <ns2:Result>Passed</ns2:Result>\r\n"
				+ "                        <ns2:InitiatingInstitute>DocuSign</ns2:InitiatingInstitute>\r\n"
				+ "                        <ns2:QuestionId>10004</ns2:QuestionId>\r\n"
				+ "                        <ns2:QuestionId>10021</ns2:QuestionId>\r\n"
				+ "                        <ns2:QuestionId>10033</ns2:QuestionId>\r\n"
				+ "                        <ns2:Venue/>\r\n" + "                     </ns2:Notification>\r\n"
				+ "                  </ns2:Details>\r\n" + "               </ns2:InstantIdBenefitVISResponseInfo>\r\n"
				+ "            </ns2:InstantIdBenefitVISResponse>\r\n" + "         </ven:VendorEventRequest>\r\n"
				+ "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		return urlActionRequest;

	}

	public String[] getKidsSsnMtrRequest(int memberNumber, String service, int monitorId) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>KIDS_SSN_MTR_REQ</ven:Operation>\r\n"
				+ "            <SSNMonitorVISResponse xmlns=\"response.ssn.vis.monitor.idp.product\" xmlns:com=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:cmn=\"common.xml.idp.infrastructure\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:mbsvis=\"vis.monitor.idp.product\">\r\n"
				+ "               <SSNMonitorVISResponseInfo>\r\n" + "                  <ResponseCommon>\r\n"
				+ "                     <mbsvis:Status>Success</mbsvis:Status>\r\n"
				+ "                     <mbsvis:ResponseStatusType>INFO</mbsvis:ResponseStatusType>\r\n"
				+ "                     <mbsvis:RoundTripTimeMillis>2147483647</mbsvis:RoundTripTimeMillis>\r\n"
				+ "                     <mbsvis:Command>KIDS_SSN_MTR_REQ</mbsvis:Command>\r\n"
				+ "                     <mbsvis:GUID>1234-test</mbsvis:GUID>\r\n"
				+ "                     <mbsvis:MemberNumber>" + memberNumber + "</mbsvis:MemberNumber>\r\n"
				+ "                     <mbsvis:ProductLine>" + service + "</mbsvis:ProductLine>\r\n"
				+ "                  </ResponseCommon>\r\n" + "                  <Monitor>\r\n"
				+ "                     <MonitorID>" + monitorId + "</MonitorID>\r\n"
				+ "                  </Monitor>\r\n" + "                  <Details>\r\n"
				+ "                     <com:QuerySSNMonitorSubordinateResponse xmlns:com=\"common.ssnmonitoring.xml.idp.infrastructure\">\r\n"
				+ "                        <com:SSNSubordinateReportData>\r\n"
				+ "                           <com:SSNSubordinateReport>\r\n"
				+ "                              <com:CreditData>true</com:CreditData>\r\n"
				+ "                              <com:NonCreditData>true</com:NonCreditData>\r\n"
				+ "                           </com:SSNSubordinateReport>\r\n"
				+ "                        </com:SSNSubordinateReportData>\r\n"
				+ "                     </com:QuerySSNMonitorSubordinateResponse>\r\n"
				+ "                  </Details>\r\n" + "               </SSNMonitorVISResponseInfo>\r\n"
				+ "            </SSNMonitorVISResponse>\r\n" + "         </ven:VendorEventRequest>\r\n"
				+ "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		return urlActionRequest;

	}
	
	public String[] getAdultSsnMtrRequest(int mtrAccSysID, int monitorId) {
		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <mon:vendorEvent>\r\n" + 
				"         <ven:VendorEventRequest>\r\n" + 
				"            <ven:Operation>SSN_MTR_REQ</ven:Operation>\r\n" + 
				"            <ns2:SSNMonitorVISResponse xmlns:ns2=\"response.ssn.vis.monitor.idp.product\" xmlns=\"vis.monitor.idp.product\" xmlns:ns4=\"common.xml.idp.infrastructure\" xmlns:ns3=\"common.ssnmonitoring.xml.idp.infrastructure\" xmlns:ns5=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\">\r\n" + 
				"               <ns2:SSNMonitorVISResponseInfo>\r\n" + 
				"                  <ns2:ResponseCommon>\r\n" + 
				"                     <Status>Success</Status>\r\n" + 
				"                     <ResponseStatusType>INFO</ResponseStatusType>\r\n" + 
				"                     <Command>SSN_MTR_REQ</Command>\r\n" + 
				"                     <GUID>"+mtrAccSysID+"</GUID>\r\n" + 
				"                     <Vendor>ECS</Vendor>\r\n" + 
				"                  </ns2:ResponseCommon>\r\n" + 
				"                  <ns2:Monitor>\r\n" + 
				"                     <ns2:MonitorID>"+monitorId+"</ns2:MonitorID>\r\n" + 
				"                  </ns2:Monitor>\r\n" + 
				"                  <ns2:Details>\r\n" + 
				"                     <ns3:QuerySSNMonitorResponse>\r\n" + 
				"                        <ns3:SSNReportData>\r\n" + 
				"                           <ns3:SSNReport>\r\n" + 
				"                              <ns3:EventCount>2</ns3:EventCount>\r\n" + 
				"                              <ns3:Event>\r\n" + 
				"                                 <ns3:Id>N1003E</ns3:Id>\r\n" + 
				"                                 <ns3:Type>Name</ns3:Type>\r\n" + 
				"                                 <ns3:Source>\r\n" + 
				"                                    <ns3:Name>Credit Header</ns3:Name>\r\n" + 
				"                                    <ns3:Category>Credit Header Data</ns3:Category>\r\n" + 
				"                                 </ns3:Source>\r\n" + 
				"                                 <ns3:Detail>\r\n" + 
				"                                    <ns3:Name>\r\n" + 
				"                                       <ns4:FirstName>TestFirstName</ns4:FirstName>\r\n" + 
				"                                       <ns4:LastName>TestLastname</ns4:LastName>\r\n" + 
				"                                    </ns3:Name>\r\n" + 
				"                                 </ns3:Detail>\r\n" + 
				"                                 <ns3:FirstReportDate>2008-05-09</ns3:FirstReportDate>\r\n" + 
				"                              </ns3:Event>\r\n" + 
				"                              <ns3:Event>\r\n" + 
				"                                 <ns3:Id>N1007A</ns3:Id>\r\n" + 
				"                                 <ns3:Type>Address</ns3:Type>\r\n" + 
				"                                 <ns3:Source>\r\n" + 
				"                                    <ns3:Name>Credit Header</ns3:Name>\r\n" + 
				"                                    <ns3:Category>Credit Header Data</ns3:Category>\r\n" + 
				"                                 </ns3:Source>\r\n" + 
				"                                 <ns3:Detail>\r\n" + 
				"                                    <ns3:Address>\r\n" + 
				"                                       <Address1>300 W SCHROCK RD</Address1>\r\n" + 
				"                                       <City>WESTERVILLE</City>\r\n" + 
				"                                       <State>OH</State>\r\n" + 
				"                                       <Zip>43081</Zip>\r\n" + 
				"                                    </ns3:Address>\r\n" + 
				"                                 </ns3:Detail>\r\n" + 
				"                                 <ns3:FirstReportDate>2011-03-15</ns3:FirstReportDate>\r\n" + 
				"                              </ns3:Event>\r\n" + 
				"                           </ns3:SSNReport>\r\n" + 
				"                        </ns3:SSNReportData>\r\n" + 
				"                     </ns3:QuerySSNMonitorResponse>\r\n" + 
				"                  </ns2:Details>\r\n" + 
				"               </ns2:SSNMonitorVISResponseInfo>\r\n" + 
				"            </ns2:SSNMonitorVISResponse>\r\n" + 
				"         </ven:VendorEventRequest>\r\n" + 
				"      </mon:vendorEvent>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		return urlActionRequest;
		
	}

	public String[] getNcoaMtrRequest(int memberNumber, String service) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>NCOA_MTR_ALERT</ven:Operation>\r\n"
				+ "            <ns2:FraudBenefitVISResponse xmlns:ns2=\"response.fraud.vis.monitor.idp.product\" xmlns=\"vis.monitor.idp.product\">\r\n"
				+ "               <ns2:FraudBenefitVISResponseInfo>\r\n" + "                  <ns2:ResponseCommon>\r\n"
				+ "                     <Status>SUCCESS</Status>\r\n"
				+ "                     <ResponseStatusType>INFO</ResponseStatusType>\r\n"
				+ "                     <Command>NCOA_MTR_ALERT</Command>\r\n" + "                     <MemberNumber>"
				+ memberNumber + "</MemberNumber>\r\n" + "                     <ProductLine>" + service
				+ "</ProductLine>\r\n" + "                  </ns2:ResponseCommon>\r\n"
				+ "                  <ns2:FraudBenefitVendorResponeDetails>\r\n"
				+ "                     <ns2:NcoaAlert>\r\n"
				+ "                        <ns2:FirstName>DENISE</ns2:FirstName>\r\n"
				+ "                        <ns2:LastName>HENNESSY</ns2:LastName>\r\n"
				+ "                        <ns2:Address>300 W Schrock Rd</ns2:Address>\r\n"
				+ "                        <ns2:City>WESTERVILLE</ns2:City>\r\n"
				+ "                        <ns2:State>OH</ns2:State>\r\n"
				+ "                        <ns2:Zip>43081</ns2:Zip>\r\n"
				+ "                        <ns2:MovedDate>201909</ns2:MovedDate>\r\n"
				+ "                        <ns2:MoveType>I</ns2:MoveType>\r\n"
				+ "                        <ns2:MatchFlag>M</ns2:MatchFlag>\r\n"
				+ "                        <ns2:NXI>A</ns2:NXI>\r\n" + "                     </ns2:NcoaAlert>\r\n"
				+ "                  </ns2:FraudBenefitVendorResponeDetails>\r\n"
				+ "               </ns2:FraudBenefitVISResponseInfo>\r\n"
				+ "            </ns2:FraudBenefitVISResponse>\r\n" + "         </ven:VendorEventRequest>\r\n"
				+ "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>\r\n" + "";

		return urlActionRequest;

	}

	public String[] getQl2AddressMtrRequest(int memberNumber, String service, int monitorId) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>POSTAL_SEARCH_REQ</ven:Operation>\r\n"
				+ "            <DatasweepBenefitVISResponse xsi:schemaLocation=\"response.datasweep.vis.monitor.idp.product DatasweepBenefitResponseVIS.xsd\" xmlns=\"response.datasweep.vis.monitor.idp.product\" xmlns:ds=\"common.datasweep.xml.idp.infrastructure\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:mbsvis=\"vis.monitor.idp.product\">\r\n"
				+ "               <DatasweepBenefitVISResponseInfo>\r\n" + "                  <ResponseCommon>\r\n"
				+ "                     <mbsvis:Status>Success</mbsvis:Status>\r\n"
				+ "                     <mbsvis:ResponseStatusType>INFO</mbsvis:ResponseStatusType>\r\n"
				+ "                     <mbsvis:RoundTripTimeMillis>2147483647</mbsvis:RoundTripTimeMillis>\r\n"
				+ "                     <mbsvis:Command>POSTAL_SEARCH_REQ</mbsvis:Command>\r\n"
				+ "                     <mbsvis:GUID>String</mbsvis:GUID>\r\n"
				+ "                     <mbsvis:Vendor>CSIDENTITY</mbsvis:Vendor>\r\n"
				+ "                     <mbsvis:MemberNumber>" + memberNumber + "</mbsvis:MemberNumber>\r\n"
				+ "                     <mbsvis:ProductLine>" + service + "</mbsvis:ProductLine>\r\n"
				+ "                  </ResponseCommon>\r\n" + "                  <Monitor>\r\n"
				+ "                     <MonitorID>" + monitorId + "</MonitorID>\r\n"
				+ "                  </Monitor>\r\n" + "                  <SweepResults>\r\n"
				+ "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>411L_DR1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n" + "                        <ds:Url/>\r\n"
				+ "                        <ds:Record>address</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:ErrorMessage Code=\"371\">\r\n"
				+ "                              <ds:Description>Site doesn't support address search.</ds:Description>\r\n"
				+ "                           </ds:ErrorMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>411_DR1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>https://www.411.com</ds:Url>\r\n"
				+ "                        <ds:Record>Address</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 61 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>411_DR1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>https://www.411.com</ds:Url>\r\n"
				+ "                        <ds:Record>Address</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 61 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>GOOG_SE1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>http://www.google.com</ds:Url>\r\n"
				+ "                        <ds:Record>address</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 2780 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>					 \r\n"
				+ "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>WHPG_DR1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>https://www.whitepages.com</ds:Url>\r\n"
				+ "                        <ds:Record>Address</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 61 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>YHOO_DR1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>http://people.yahoo.com</ds:Url>\r\n"
				+ "                        <ds:Record>address</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 916000 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>YHOO_SE1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>http://www.yahoo.com</ds:Url>\r\n"
				+ "                        <ds:Record>address</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 10000 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "					 </SweepResults>\r\n"
				+ "               </DatasweepBenefitVISResponseInfo>\r\n"
				+ "            </DatasweepBenefitVISResponse>\r\n" + "         </ven:VendorEventRequest>\r\n"
				+ "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		return urlActionRequest;

	}

	public String[] getQl2EmailMtrRequest(int memberNumber, String service, int monitorId) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>EMAIL_SEARCH_REQ</ven:Operation>\r\n"
				+ "            <DatasweepBenefitVISResponse xsi:schemaLocation=\"response.datasweep.vis.monitor.idp.product DatasweepBenefitResponseVIS.xsd\" xmlns=\"response.datasweep.vis.monitor.idp.product\" xmlns:ds=\"common.datasweep.xml.idp.infrastructure\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:mbsvis=\"vis.monitor.idp.product\">\r\n"
				+ "               <DatasweepBenefitVISResponseInfo>\r\n" + "                  <ResponseCommon>\r\n"
				+ "                     <mbsvis:Status>Success</mbsvis:Status>\r\n"
				+ "                     <mbsvis:ResponseStatusType>INFO</mbsvis:ResponseStatusType>\r\n"
				+ "                     <mbsvis:RoundTripTimeMillis>55702494</mbsvis:RoundTripTimeMillis>\r\n"
				+ "                     <mbsvis:Command>EMAIL_SEARCH_REQ</mbsvis:Command>\r\n"
				+ "                     <mbsvis:GUID>String</mbsvis:GUID>\r\n"
				+ "                     <mbsvis:Vendor>CSIDENTITY</mbsvis:Vendor>\r\n"
				+ "                     <mbsvis:MemberNumber>" + memberNumber + "</mbsvis:MemberNumber>\r\n"
				+ "                     <mbsvis:ProductLine>" + service + "</mbsvis:ProductLine>\r\n"
				+ "                  </ResponseCommon>\r\n" + "                  <Monitor>\r\n"
				+ "                     <MonitorID>" + monitorId + "</MonitorID>\r\n"
				+ "                  </Monitor>\r\n" + "                  <SweepResults>\r\n"
				+ "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>GOOG_SE1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>http://www.google.com</ds:Url>\r\n"
				+ "                        <ds:Record>email</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:InfoMessage Code=\"501\">\r\n"
				+ "                              <ds:Description>No records found.</ds:Description>\r\n"
				+ "                           </ds:InfoMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>YHOO_SE1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>http://www.yahoo.com</ds:Url>\r\n"
				+ "                        <ds:Record>email</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:InfoMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>We did find results for: \"vikas@gmail.com\".</ds:Description>\r\n"
				+ "                           </ds:InfoMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                  </SweepResults>\r\n"
				+ "               </DatasweepBenefitVISResponseInfo>\r\n"
				+ "            </DatasweepBenefitVISResponse>\r\n" + "         </ven:VendorEventRequest>\r\n"
				+ "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		return urlActionRequest;

	}

	public String[] getQl2PhoneMtrRequest(int memberNumber, String service, int monitorId) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>PHONE_SEARCH_REQ</ven:Operation>\r\n"
				+ "            <DatasweepBenefitVISResponse xsi:schemaLocation=\"response.datasweep.vis.monitor.idp.product DatasweepBenefitResponseVIS.xsd\" xmlns=\"response.datasweep.vis.monitor.idp.product\" xmlns:ds=\"common.datasweep.xml.idp.infrastructure\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:mbsvis=\"vis.monitor.idp.product\">\r\n"
				+ "               <DatasweepBenefitVISResponseInfo>\r\n" + "                  <ResponseCommon>\r\n"
				+ "                     <mbsvis:Status>Success</mbsvis:Status>\r\n"
				+ "                     <mbsvis:ResponseStatusType>INFO</mbsvis:ResponseStatusType>\r\n"
				+ "                     <mbsvis:RoundTripTimeMillis>2147483647</mbsvis:RoundTripTimeMillis>\r\n"
				+ "                     <mbsvis:Command>PHONE_SEARCH_REQ</mbsvis:Command>\r\n"
				+ "                     <mbsvis:GUID>String</mbsvis:GUID>\r\n"
				+ "                     <mbsvis:Vendor>CSIDENTITY</mbsvis:Vendor>\r\n"
				+ "                     <mbsvis:MemberNumber>" + memberNumber + "</mbsvis:MemberNumber>\r\n"
				+ "                     <mbsvis:ProductLine>" + service + "</mbsvis:ProductLine>\r\n"
				+ "                  </ResponseCommon>\r\n" + "                  <Monitor>\r\n"
				+ "                     <MonitorID>" + monitorId + "</MonitorID>\r\n"
				+ "                  </Monitor>\r\n" + "                  <SweepResults>\r\n"
				+ "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>411L_DR1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>http://www.411locate.com</ds:Url>\r\n"
				+ "                        <ds:Record>phone</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 4 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>411_DR1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>https://www.411.com</ds:Url>\r\n"
				+ "                        <ds:Record>Phone</ds:Record>\r\n"
				+ "                        <ds:Messages/>\r\n" + "                     </ds:SweepResultData>\r\n"
				+ "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>GOOG_SE1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>http://www.google.com</ds:Url>\r\n"
				+ "                        <ds:Record>phone</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 12700 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>					 \r\n"
				+ "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>WHPG_DR1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>https://www.whitepages.com</ds:Url>\r\n"
				+ "                        <ds:Record>Phone</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 61 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>YHOO_DR1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>http://people.yahoo.com</ds:Url>\r\n"
				+ "                        <ds:Record>phone</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:WarningMessage Code=\"601\">\r\n"
				+ "                              <ds:Description>More than 3 records found.</ds:Description>\r\n"
				+ "                           </ds:WarningMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "                     <ds:SweepResultData>\r\n"
				+ "                        <ds:SearchSite>YHOO_SE1</ds:SearchSite>\r\n"
				+ "                        <ds:Found>true</ds:Found>\r\n"
				+ "                        <ds:Url>http://www.yahoo.com</ds:Url>\r\n"
				+ "                        <ds:Record>phone</ds:Record>\r\n"
				+ "                        <ds:Messages>\r\n"
				+ "                           <ds:InfoMessage Code=\"501\">\r\n"
				+ "                              <ds:Description>No match Exists.</ds:Description>\r\n"
				+ "                           </ds:InfoMessage>\r\n" + "                        </ds:Messages>\r\n"
				+ "                     </ds:SweepResultData>\r\n" + "					 </SweepResults>\r\n"
				+ "               </DatasweepBenefitVISResponseInfo>\r\n"
				+ "            </DatasweepBenefitVISResponse>\r\n" + "         </ven:VendorEventRequest>\r\n"
				+ "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

		return urlActionRequest;

	}

	public String[] getSsnFraudRequest(int memberNumber, String service, int monitorId) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = idpOfflineUrl;
		urlActionRequest[1] = "vendorEvent";
		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mon=\"monitoredasset.wi.xml.idp.product\" xmlns:ven=\"vendorevent.xml.offline.idp.product\" xmlns:res=\"response.datasweep.vis.monitor.idp.product\" xmlns:vis=\"vis.monitor.idp.product\" xmlns:com=\"common.datasweep.xml.idp.infrastructure\" xmlns:res1=\"response.fraud.vis.monitor.idp.product\" xmlns:res2=\"response.myid.vis.monitor.idp.product\" xmlns:mon1=\"monitor.myid.xml.idp.infrastructure\" xmlns:com1=\"common.myid.xml.idp.infrastructure\" xmlns:scor=\"score.myid.xml.idp.infrastructure\" xmlns:res3=\"response.prr.vis.monitor.idp.product\" xmlns:prr=\"prr.xml.idp.infrastructure\" xmlns:cat=\"category.prr.xml.idp.infrastructure\" xmlns:com2=\"common.xml.idp.infrastructure\" xmlns:urn=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:res4=\"response.ssn.vis.monitor.idp.product\" xmlns:com3=\"common.ssn.monitoring.xml.idp.infrastructure\" xmlns:com4=\"common.ssn.xml.idp.infrastructure\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <mon:vendorEvent>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <ven:VendorEventRequest>\r\n"
				+ "            <ven:Operation>SSN_FRAUD_CRE</ven:Operation>\r\n"
				+ "            <FraudBenefitVISResponse xmlns=\"response.fraud.vis.monitor.idp.product\" xmlns:mbsvis=\"vis.monitor.idp.product\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n"
				+ "               <FraudBenefitVISResponseInfo>\r\n" + "                  <ResponseCommon>\r\n"
				+ "                     <mbsvis:Status>Success</mbsvis:Status>\r\n"
				+ "                     <mbsvis:ResponseStatusType>INFO</mbsvis:ResponseStatusType>\r\n"
				+ "                     <mbsvis:Command>SSN_FRAUD_CRE</mbsvis:Command>\r\n"
				+ "                     <mbsvis:GUID>GUID-152399</mbsvis:GUID>\r\n"
				+ "                     <mbsvis:Vendor>CSIDENTITY</mbsvis:Vendor>\r\n"
				+ "                     <mbsvis:MemberNumber>" + memberNumber + "</mbsvis:MemberNumber>\r\n"
				+ "                     <mbsvis:ProductLine>" + service + "</mbsvis:ProductLine>\r\n"
				+ "                  </ResponseCommon>\r\n" + "                  <Monitor>\r\n"
				+ "                     <MonitorID>" + monitorId + "</MonitorID>\r\n"
				+ "                  </Monitor>\r\n" + "                  <FraudBenefitVendorResponeDetails>\r\n"
				+ "                     <compromisedReport>\r\n" + "                        <Record>\r\n"
				+ "                           <MemberID>" + memberNumber + "-" + service + "</MemberID>\r\n"
				+ "                           <ServiceCode>503</ServiceCode>\r\n"
				+ "                           <Date>2019-08-01</Date>\r\n" + "                           <Data>\r\n"
				+ "                              <report>\r\n" + "                                 <Record>\r\n"
				+ "                                    <MemberID>" + memberNumber + "-" + service + "</MemberID>\r\n"
				+ "                                    <ServiceCode>503</ServiceCode>\r\n"
				+ "                                    <Date>2019-08-01</Date>\r\n"
				+ "                                    <Data>\r\n"
				+ "                                       <report>\r\n"
				+ "                                          <record>\r\n"
				+ "                                           </record>\r\n"
				+ "                                       </report>\r\n"
				+ "                                    </Data>\r\n" + "                                 </Record>\r\n"
				+ "                              </report>\r\n" + "                           </Data>\r\n"
				+ "                        </Record>\r\n" + "                     </compromisedReport>\r\n"
				+ "                  </FraudBenefitVendorResponeDetails>\r\n"
				+ "               </FraudBenefitVISResponseInfo>\r\n" + "            </FraudBenefitVISResponse>\r\n"
				+ "         </ven:VendorEventRequest>\r\n" + "      </mon:vendorEvent>\r\n" + "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>\r\n" + "";

		return urlActionRequest;

	}

	public String[] getSentryBayMtrRequest(int memberNumber, String service, int monitorId, String monitorAbbr) {

		String[] urlActionRequest = new String[3];
		urlActionRequest[0] = xmlURL + "/ServiceGateway/VISIDP/Sentrybay/ScanResult/Callback";
		urlActionRequest[1] = "http://www.openuri.org/sentryBayScanResultCallbackService";
		String category = null;

		switch (monitorAbbr) {
		case "SSN_MTR_SB":
			category = "SSN_ADULT_SB";
			break;
		case "ADDRESS_MTR_SB":
			category = "Address_SB";
			break;
		case "BANKACCT_MTR_SB":
			category = "Bank_SB";
			break;
		case "EMAIL_MTR_SB":
			category = "EMAIL_SB";
			break;
		case "CC_MTR_SB":
			category = "CC_SB";
			break;
		case "DOB_MTR_SB":
			category = "DOB_SB";
			break;
		case "PHONE_MTR_SB":
			category = "Phone_SB";
			break;
		case "DL_MTR_SB":
			category = "DL_SB";
			break;
		case "PP_MTR_SB":
			category = "PP_SB";
			break;
		case "KIDS_SSN_MTR_SB":
			category = "SSN_KIDS_SB";
			break;
		default:
			category = null;
		}

		urlActionRequest[2] = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:add=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\" xmlns:open=\"http://www.openuri.org/\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n"
				+ "      <open:sentryBayScanResultCallbackServiceRequest>\r\n"
				+ "         <scanResultCallbackServiceRequest type=\"alert\">\r\n"
				+ "            <timestamp>2020-01-30T09:00:00</timestamp>\r\n" + "            <brands>\r\n"
				+ "               <!--Zero or more repetitions:-->\r\n" + "               <brand name=\"brand1\">\r\n"
				+ "                  <members>\r\n" + "                     <!--Zero or more repetitions:-->\r\n"
				+ "                     <member>\r\n" + "                        <MemberNo>" + memberNumber + "-"
				+ service + "</MemberNo>\r\n" + "                        <ScanDate>2020-05-30T09:00:00</ScanDate>\r\n"
				+ "                        <Result id=\"2\" category=\"" + category + "(" + monitorId
				+ ")\" risk=\"high\" source=\"publicweb\" percent=\"90\">www.yahoo.com</Result>\r\n"
				+ "                     </member>\r\n" + "                  </members>\r\n"
				+ "               </brand>\r\n" + "            </brands>\r\n"
				+ "         </scanResultCallbackServiceRequest>\r\n"
				+ "      </open:sentryBayScanResultCallbackServiceRequest>\r\n" + "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";

		return urlActionRequest;

	}

	public String[] getAlertRequest(int memberNumber, String service, Asset asset, Monitor monitor, int mtrAccSysId) throws Exception {

		String[] urlActionRequest = new String[3];

		switch (monitor.getMonitorAbbr()) {
		case "SSN_FRAUD":
			urlActionRequest = getSsnFraudRequest(memberNumber, service, monitor.getMonitorId());
			break;
		case "INSTANTID_MTR":
			urlActionRequest = getInstantIMtrdRequest(memberNumber, service, monitor.getMonitorId());
			break;
		case "DOSSIER_RPT":
			urlActionRequest = getDossierRptRequest(memberNumber, service, monitor.getMonitorId());
			break;
		case "DOSSIER_MTR":
			urlActionRequest = getDossierMtrRequest(memberNumber, service, monitor.getMonitorId());
			break;
		case "SSN_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		case "SSN_MTR":
			urlActionRequest = getAdultSsnMtrRequest(mtrAccSysId, monitor.getMonitorId());
			break;
		case "KIDS_SSN_MTR":
			urlActionRequest = getKidsSsnMtrRequest(memberNumber, service, monitor.getMonitorId());
			break;
		case "KIDS_SSN_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		case "CC_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		case "CC_FRAUD":
			urlActionRequest = getCcFraudtRequest(memberNumber, service, monitor.getMonitorId());
			break;
		case "BANKACCT_FRAUD":
			urlActionRequest = getBankAcctFraudRequest(memberNumber, service, monitor.getMonitorId(), asset.getValue());
			break;
		case "BANKACCT_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		case "NCOA_MTR":
			urlActionRequest = getNcoaMtrRequest(memberNumber, service);
			break;
		case "ADDRESS_MTR":
			urlActionRequest = getQl2AddressMtrRequest(memberNumber, service, monitor.getMonitorId());
			break;
		case "ADDRESS_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		case "EMAIL_MTR":
			urlActionRequest = getQl2EmailMtrRequest(memberNumber, service, monitor.getMonitorId());
			break;
		case "EMAIL_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		case "PHONE_MTR":
			urlActionRequest = getQl2EmailMtrRequest(memberNumber, service, monitor.getMonitorId());
			break;
		case "PHONE_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		case "DL_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		case "PP_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		case "DOB_MTR_SB":
			urlActionRequest = getSentryBayMtrRequest(memberNumber, service, monitor.getMonitorId(),
					monitor.getMonitorAbbr());
			break;
		default:
			throw new Exception("Not able to generate alert request for Monitor type: " + monitor.getMonitorAbbr()
					+ ". Development is still in progress!");
		}
		return urlActionRequest;

	}

}
