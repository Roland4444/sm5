package DB;

import crypto.Gost3411Hash;
import org.junit.Test;
import readfile.Readfile;
import schedulling.abstractions.Freezer;
import util.Event;
import util.buildSql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExecutorTest {

    @Test
    public void getLenth() {
    }

    @Test
    public void submit() throws SQLException, IOException {
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        buildSql sql = new buildSql("3567");
        assertNotEquals(null, f.submit(sql.result()) );
        FileWriter wr = new FileWriter("3567");
        ResultSet Select2 = f.submit("set concat_null_yields_null off; SELECT * FROM dbo.smev3files WHERE f_key=19;");
        Gost3411Hash gost = new Gost3411Hash();
        if (Select2.next()){
            String res = String.valueOf(gost.getBytesFromBase64(Select2.getString("f_body_xml")));
            System.out.print(res);
        }
        wr.close();

    }
    @Test
    public void submitver() throws SQLException, IOException {
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        buildSql sql = new buildSql("3567");
        assertNotEquals(null, f.submit(sql.result()) );
        FileWriter wr = new FileWriter("3567");
        ResultSet Select2 = f.submit("set concat_null_yields_null off; SELECT @@version;");
        Gost3411Hash gost = new Gost3411Hash();
        if (Select2.next()){
            String res = Select2.getString(1);
            System.out.print(res);
        }
        wr.close();

    }

    @Test
    public void submit2() throws SQLException, IOException {
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        buildSql sql = new buildSql("3567");
        assertNotEquals(null, f.submit(sql.result()) );
        FileWriter wr = new FileWriter("xml4test/1637006.xml");
        ResultSet Select2 = f.submit("set concat_null_yields_null off; SELECT f_body_xml FROM gis_files WHERE f_key='1647314';");
        Gost3411Hash gost = new Gost3411Hash();
        if (Select2.next()){
            String res =Select2.getString("f_body_xml");
            System.out.print(res);
            wr.write(res);
        }
        wr.close();
    }

    @Test
    public void gettasker() throws SQLException, IOException {
        System.out.println("in tasker==>");
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        ResultSet Select2 = null;
        try {
            Select2 = f.submit("set concat_null_yields_null off; SELECT f_key FROM fns_files WHERE f_stat='12';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Select2.next()){
                String res =Select2.getString("f_key");
                System.out.print(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void gettasker2() throws SQLException, IOException {
        System.out.println("in tasker==>");
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        ResultSet Select2 = null;
        try {
            Select2 = f.submit( "SELECT * FROM  fns_files  WHERE f_key='3609';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Select2.next()){
                String res =Select2.getString("f_rec_id");
                System.out.println(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void gettasker22() throws SQLException, IOException {
        System.out.println("in tasker==>");
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        ResultSet Select2 = null;
        try {
            Select2 = f.submit( "SELECT * FROM  smev3files WHERE f_key='4';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Select2.next()){
                String res =Select2.getString("f_body_xml");
                System.out.println(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gettasker21() throws SQLException, IOException {
        System.out.println("in tasker==>");
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        ResultSet Select2 = null;
        try {
            Select2 = f.submit( "set concat_null_yields_null off; EXEC smev_query1s 'x', 1;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int counter=0;
        try {
            while (Select2.next()){
                String res =Select2.getString("f_key");
                System.out.println(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //UPDATE fns_egr SET f_namp =? WHERE f_key = ?;"
    @Test
    public void gettasker221() throws SQLException, IOException {
        long startTime = System.currentTimeMillis();



        ArrayList<String> datablock = new ArrayList<>();
        System.out.println("in tasker==>");
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        ResultSet Select2 = null;
        try {
            Select2 = f.submit( "SELECT f_narrative FROM  gkh_payment;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int counter=0;
        try {
            while (Select2.next()){
                String res =Select2.getString("f_narrative");
                datablock.add(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("total="+elapsedTime/1000+"   Sekunden");
        System.out.print("freezing...");
        Freezer fr = new Freezer();
        FileOutputStream fos2 = new FileOutputStream("binData/array.bin");
        fos2.write(fr.SaveStringArrayList(datablock));
        fos2.close();

    }



    @Test
    public void gettasker212225() throws SQLException, IOException {
        System.out.println("in tasker==>");
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        String target ="xml4test/VIPUL-respansw-0001.XML";
        Path p = Paths.get(target);
        String data="0";
        byte[] arr =data.getBytes();
        String table = "fms_zap";
        PreparedStatement pst = f.getConn().prepareStatement("set concat_null_yields_null on; EXEC smev_answer2s ?;");
        pst.setBytes(1,data.getBytes());
        pst.executeUpdate();

    }

    @Test
    public void gettasker2125() throws SQLException, IOException {
        System.out.println("in tasker==>");
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        String target ="xml4test/VIPUL-respansw-0001.XML";
        Path p = Paths.get(target);
        String data="0";
        byte[] arr =data.getBytes();
        String table = "fms_zap";
        PreparedStatement pst = f.getConn().prepareStatement("UPDATE "+table+" SET f_stat =? WHERE f_key = ?;");
        pst.setString(1,data);
        pst.setString(2, "48827");
        pst.executeUpdate();

    }
//set concat_null_yields_null off; EXEC cb2017_vypiska_roma '"+p1+"', '"+p2+"', '"+p3+"', '2018-04-30'
    @Test
    public void gettasker21255() throws SQLException, IOException {
        System.out.println("in tasker==>");
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
      //  byte[] arr = Files.readAllBytes(p);
        PreparedStatement pst = f.getConn().prepareStatement("UPDATE fns_files SET f_stat ='0' WHERE  f_stat ='12';");
        //   pst.setBytes(1,arr);
        pst.executeUpdate();
         pst = f.getConn().prepareStatement("UPDATE gis_files SET f_stat ='0' WHERE  f_stat ='12';");
        //   pst.setBytes(1,arr);
        pst.executeUpdate();
         pst = f.getConn().prepareStatement("UPDATE fms_zap SET f_stat ='0' WHERE  f_stat ='12';");
        //   pst.setBytes(1,arr);
        pst.executeUpdate();

    }


    @Test
    public void callstored() throws SQLException, IOException {
        System.out.println("in tasker==>");
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        //  byte[] arr = Files.readAllBytes(p);
        ResultSet result =f.submit("set concat_null_yields_null off; EXEC smev_query 'x';");
        assertNotEquals(null, result);

    }

    @Test
    public void gettasker212() throws SQLException, IOException {
        System.out.println("in tasker==>");
        String etalon = "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\"><S:Body><ns2:SendRequestRequest><ns:SenderProvidedRequestData xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\" Id=\"SIGNED_BY_CONSUMER\"><ns:MessageID>000</ns:MessageID><ns2:MessagePrimaryContent><req:ImportPaymentsRequest xmlns:com=\"http://roskazna.ru/gisgmp/xsd/Common/2.0.1\" xmlns:req=\"urn://roskazna.ru/gisgmp/xsd/services/import-payments/2.0.1\" xmlns:pmnt=\"http://roskazna.ru/gisgmp/xsd/Payment/2.0.1\" xmlns:rfd=\"http://roskazna.ru/gisgmp/xsd/Refund/2.0.1\" xmlns:chg=\"http://roskazna.ru/gisgmp/xsd/Charge/2.0.1\" xmlns:org=\"http://roskazna.ru/gisgmp/xsd/Organization/2.0.1\" xmlns:bdi=\"http://roskazna.ru/gisgmp/xsd/BudgetIndex/2.0.1\" xmlns:pkg=\"http://roskazna.ru/gisgmp/xsd/Package/2.0.1\" Id=\"PERSONAL_SIGNATURE\" senderIdentifier=\"3637c9\" senderRole=\"9\" timestamp=\"2018-07-13T08:31:23.0998280+04:00\"><pkg:PaymentsPackage><pkg:ImportedPayment Id=\"I_AC0B90D8-757D-4BD2-9843-EA148907D237\" paymentId=\"10412037291014111207201812545317\" purpose=\"КБК 18011301081017000130 Оплата за охрану 2-х объектов согласно доп. соглашениям к договорам К1406 от 21.01.2013 г., К1409 от 08.11.2012 г. согл. распоряжения вкладчика от 12.11.2017 г.\" kbk=\"18011301081017000130\" oktmo=\"12701000\" supplierBillID=\"0\" amount=\"58000\" paymentDate=\"2018-07-12\" transKind=\"01\"><pmnt:PaymentOrg><org:Bank bik=\"041203729\" name=\"АО ВКАБАНК\" correspondentBankAccount=\"30101810700000000729\"/></pmnt:PaymentOrg><pmnt:Payer payerIdentifier=\"1210000000301700924632\"/><org:Payee name=\"УФК по Астраханской области (ОВО по городу Астрахани-филиал ФГКУ ОВО ВНГ России по Астраханской области,л/сч 04251D20860)\" inn=\"3015096808\" kpp=\"301543001\"><com:OrgAccount accountNumber=\"40101810400000010009\"><com:Bank bik=\"041203001\"/></com:OrgAccount></org:Payee><pmnt:BudgetIndex status=\"13\" paytReason=\"0\" taxPeriod=\"0\" taxDocNumber=\"0\" taxDocDate=\"0\"/><pmnt:AccDoc accDocDate=\"2018-07-12\"/><com:ChangeStatus meaning=\"1\"/><com:AdditionalData><com:Name>Плательщик</com:Name><com:Value>Сухоруков Владимир Павлович г.Астрахань просп.Губернатора А.Гужвина д.6 кв.36</com:Value></com:AdditionalData></pkg:ImportedPayment></pkg:PaymentsPackage></req:ImportPaymentsRequest></ns2:MessagePrimaryContent><ns:PersonalSignature><ds:Signature xmlns:ns4=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><ds:Reference URI=\"#PERSONAL_SIGNATURE\"><ds:Transforms><ds:Transform xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:Transform xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Algorithm=\"urn://smev-gov-ru/xmldsig/transform\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><ds:DigestValue>000</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>000</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>000</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ns:PersonalSignature><ns:TestMessage/></ns:SenderProvidedRequestData><ns4:CallerInformationSystemSignature xmlns:ns4=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"><ds:Signature><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><ds:Reference URI=\"#SIGNED_BY_CONSUMER\"><ds:Transforms><ds:Transform xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:Transform xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Algorithm=\"urn://smev-gov-ru/xmldsig/transform\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><ds:DigestValue>000</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>000</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>000</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ns4:CallerInformationSystemSignature></ns2:SendRequestRequest></S:Body></S:Envelope>";
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        String target ="xml4test/VIPUL-respansw-0001.XML";
        Path p = Paths.get(target);
        byte[] arr = Files.readAllBytes(p);
        PreparedStatement pst = f.getConn().prepareStatement("UPDATE fns_files SET f_stat =? WHERE f_key = '3590';");
        pst.setString(1,"0");
        pst.executeUpdate();

    }

    @Test
    public void callstoredxml() throws SQLException, IOException {
        System.out.println("in tasker==>");
        String res="<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:GetResponseResponse xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ns3=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/faults/1.1\"><ns2:ResponseMessage><ns2:Response Id=\"SIGNED_BY_SMEV\"><ns2:OriginalMessageId>b7d83929-8f4b-11e8-90b3-33846193b27c</ns2:OriginalMessageId><ns2:SenderProvidedResponseData Id=\"SIGNED_BY_CALLER\"><ns2:MessageID>6a4bc8d3-8f2a-11e8-9129-fa163e24a723</ns2:MessageID><ns2:To>eyJzaWQiOjg5MjgzLCJtaWQiOiJiN2Q4MzkyOS04ZjRiLTExZTgtOTBiMy0zMzg0NjE5M2IyN2MiLCJlb2wiOjAsInNsYyI6InJ1X212ZF9zb3ZtX3AwMDFfMS4wLjBfcmVxdWVzdCIsIm1ubSI6Ijk4MTYwMV8zVCJ9</ns2:To><MessagePrimaryContent><ns:response xmlns:ns=\"urn://ru/mvd/sovm/p001/1.0.0\"><ns:requestInfo xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\"><ns:passportRFSeries>2479</ns:passportRFSeries><ns:passportRFNumber>674804</ns:passportRFNumber>\n" +
                "   </ns:requestInfo><ns:responseInfo><ns:docStatus>301</ns:docStatus><ns:invalidityReason>601</ns:invalidityReason><ns:invaliditySince>2011-07-16</ns:invaliditySince>\n" +
                "   </ns:responseInfo>\n" +
                "</ns:response></MessagePrimaryContent></ns2:SenderProvidedResponseData><ns2:MessageMetadata><ns2:MessageId>6a4bc8d3-8f2a-11e8-9129-fa163e24a723</ns2:MessageId><ns2:MessageType>RESPONSE</ns2:MessageType><ns2:Sender><ns2:Mnemonic>emu</ns2:Mnemonic><ns2:HumanReadableName>emu</ns2:HumanReadableName></ns2:Sender><ns2:SendingTimestamp>2018-07-24T13:14:57.000+03:00</ns2:SendingTimestamp><ns2:DestinationName>unknown</ns2:DestinationName><ns2:Recipient><ns2:Mnemonic>981601_3T</ns2:Mnemonic><ns2:HumanReadableName>ВКАБАНК</ns2:HumanReadableName></ns2:Recipient><ns2:SupplementaryData><ns2:DetectedContentTypeName>not detected</ns2:DetectedContentTypeName><ns2:InteractionType>NotDetected</ns2:InteractionType></ns2:SupplementaryData><ns2:DeliveryTimestamp>2018-07-24T13:25:28.860+03:00</ns2:DeliveryTimestamp><ns2:Status>responseIsDelivered</ns2:Status></ns2:MessageMetadata><ns2:SenderInformationSystemSignature><ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><ds:Reference URI=\"#SIGNED_BY_CALLER\"><ds:Transforms><ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:Transform Algorithm=\"urn://smev-gov-ru/xmldsig/transform\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><ds:DigestValue>0sa+zL1cJrc1o/3je4bAUgWY/kLrzM7alULV4d5O5Gg=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>XJU6kDjv4EYIEfPUOnRG53WepSzVgvEyO+9K867XmxpGo8OdQIO95S79RSJoY6iVropqIF83due0Aut4BOYH5g==</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>MIIH3DCCB4ugAwIBAgIRAXILAVZQAHCj6BFWMr8dB1owCAYGKoUDAgIDMIIBRjEYMBYGBSqFA2QBEg0xMjM0NTY3ODkwMTIzMRowGAYIKoUDA4EDAQESDDAwMTIzNDU2Nzg5MDEpMCcGA1UECQwg0KHRg9GJ0LXQstGB0LrQuNC5INCy0LDQuyDQtC4gMjYxFzAVBgkqhkiG9w0BCQEWCGNhQHJ0LnJ1MQswCQYDVQQGEwJSVTEYMBYGA1UECAwPNzcg0JzQvtGB0LrQstCwMRUwEwYDVQQHDAzQnNC+0YHQutCy0LAxJDAiBgNVBAoMG9Ce0JDQniDQoNC+0YHRgtC10LvQtdC60L7QvDEwMC4GA1UECwwn0KPQtNC+0YHRgtC+0LLQtdGA0Y/RjtGJ0LjQuSDRhtC10L3RgtGAMTQwMgYDVQQDDCvQotC10YHRgtC+0LLRi9C5INCj0KYg0KDQotCaICjQoNCi0JvQsNCx0YEpMB4XDTE4MDMyODA2NTUxMVoXDTE5MDMyODA3MDUxMVowggEPMR8wHQYJKoZIhvcNAQkCDBDQotCh0JzQrdCSM1/QrdCcMRowGAYIKoUDA4EDAQESDDAwNzcwNzA0OTM4ODEYMBYGBSqFA2QBEg0xMDI3NzAwMTk4NzY3MSgwJgYDVQQKDB/Qn9CQ0J4gwqvQoNC+0YHRgtC10LvQtdC60L7QvMK7MSYwJAYDVQQHDB3QodCw0L3QutGCLdCf0LXRgtC10YDQsdGD0YDQszEtMCsGA1UECAwkNzgg0LMuINCh0LDQvdC60YIt0J/QtdGC0LXRgNCx0YPRgNCzMQswCQYDVQQGEwJSVTEoMCYGA1UEAwwf0J/QkNCeIMKr0KDQvtGB0YLQtdC70LXQutC+0LzCuzBjMBwGBiqFAwICEzASBgcqhQMCAiQABgcqhQMCAh4BA0MABEDt/NkbN2KN5D7zCI5yysq8B3b19K4YrnYo3CvHvStJcSFfH5m2oBI/sS+I4URk7caRdrMqgleRcAmpwI1oWlfeo4IEgzCCBH8wDgYDVR0PAQH/BAQDAgTwMB0GA1UdDgQWBBRZdtXJC9SYq9wCOjQMH8YaLHyT2DCCAYgGA1UdIwSCAX8wggF7gBQ+7xk/D7l5sPHmKSGj5LmVuaXukKGCAU6kggFKMIIBRjEYMBYGBSqFA2QBEg0xMjM0NTY3ODkwMTIzMRowGAYIKoUDA4EDAQESDDAwMTIzNDU2Nzg5MDEpMCcGA1UECQwg0KHRg9GJ0LXQstGB0LrQuNC5INCy0LDQuyDQtC4gMjYxFzAVBgkqhkiG9w0BCQEWCGNhQHJ0LnJ1MQswCQYDVQQGEwJSVTEYMBYGA1UECAwPNzcg0JzQvtGB0LrQstCwMRUwEwYDVQQHDAzQnNC+0YHQutCy0LAxJDAiBgNVBAoMG9Ce0JDQniDQoNC+0YHRgtC10LvQtdC60L7QvDEwMC4GA1UECwwn0KPQtNC+0YHRgtC+0LLQtdGA0Y/RjtGJ0LjQuSDRhtC10L3RgtGAMTQwMgYDVQQDDCvQotC10YHRgtC+0LLRi9C5INCj0KYg0KDQotCaICjQoNCi0JvQsNCx0YEpghEBcgsBVlAAubPnEc86vjR3oDAdBgNVHSUEFjAUBggrBgEFBQcDAgYIKwYBBQUHAwQwJwYJKwYBBAGCNxUKBBowGDAKBggrBgEFBQcDAjAKBggrBgEFBQcDBDAdBgNVHSAEFjAUMAgGBiqFA2RxATAIBgYqhQNkcQIwKwYDVR0QBCQwIoAPMjAxODAzMjgwNjU1MTBagQ8yMDE5MDMyODA2NTUxMFowggE0BgUqhQNkcASCASkwggElDCsi0JrRgNC40L/RgtC+0J/RgNC+IENTUCIgKNCy0LXRgNGB0LjRjyAzLjkpDCwi0JrRgNC40L/RgtC+0J/RgNC+INCj0KYiICjQstC10YDRgdC40LggMi4wKQxj0KHQtdGA0YLQuNGE0LjQutCw0YIg0YHQvtC+0YLQstC10YLRgdGC0LLQuNGPINCk0KHQkSDQoNC+0YHRgdC40Lgg4oSWINCh0KQvMTI0LTI1Mzkg0L7RgiAxNS4wMS4yMDE1DGPQodC10YDRgtC40YTQuNC60LDRgiDRgdC+0L7RgtCy0LXRgtGB0YLQstC40Y8g0KTQodCRINCg0L7RgdGB0LjQuCDihJYg0KHQpC8xMjgtMjg4MSDQvtGCIDEyLjA0LjIwMTYwNgYFKoUDZG8ELQwrItCa0YDQuNC/0YLQvtCf0YDQviBDU1AiICjQstC10YDRgdC40Y8gMy45KTBlBgNVHR8EXjBcMFqgWKBWhlRodHRwOi8vY2VydGVucm9sbC50ZXN0Lmdvc3VzbHVnaS5ydS9jZHAvM2VlZjE5M2YwZmI5NzliMGYxZTYyOTIxYTNlNGI5OTViOWE1ZWU5MC5jcmwwVwYIKwYBBQUHAQEESzBJMEcGCCsGAQUFBzAChjtodHRwOi8vY2VydGVucm9sbC50ZXN0Lmdvc3VzbHVnaS5ydS9jZHAvdGVzdF9jYV9ydGxhYnMyLmNlcjAIBgYqhQMCAgMDQQDC4cx4P093b5ai+RKhGdvvkxFMVxK0UPy3JEUUs3zmsC1parcrJK/QqCIVmPmdUehIIHSBDid/kcE1xUdE6X32</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ns2:SenderInformationSystemSignature></ns2:Response><ns2:SMEVSignature><ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><ds:Reference URI=\"#SIGNED_BY_SMEV\"><ds:Transforms><ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:Transform Algorithm=\"urn://smev-gov-ru/xmldsig/transform\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><ds:DigestValue>lNwn2Y7wK2nd0qIqr+M2g9RhIkrWnCEDpxU9P8jKIPI=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>L03p7KxAArzf6a1rYfZ3hzUqHw7j/WnWhDtaU1o+fTnCJX5y8lUnIEwx2hpFMTzCPg/QcZQZul1JN2Kugh+ZHw==</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>MIIH2DCCB4egAwIBAgIRAXILAVZQAHCj6BHaIfbqMnEwCAYGKoUDAgIDMIIBRjEYMBYGBSqFA2QBEg0xMjM0NTY3ODkwMTIzMRowGAYIKoUDA4EDAQESDDAwMTIzNDU2Nzg5MDEpMCcGA1UECQwg0KHRg9GJ0LXQstGB0LrQuNC5INCy0LDQuyDQtC4gMjYxFzAVBgkqhkiG9w0BCQEWCGNhQHJ0LnJ1MQswCQYDVQQGEwJSVTEYMBYGA1UECAwPNzcg0JzQvtGB0LrQstCwMRUwEwYDVQQHDAzQnNC+0YHQutCy0LAxJDAiBgNVBAoMG9Ce0JDQniDQoNC+0YHRgtC10LvQtdC60L7QvDEwMC4GA1UECwwn0KPQtNC+0YHRgtC+0LLQtdGA0Y/RjtGJ0LjQuSDRhtC10L3RgtGAMTQwMgYDVQQDDCvQotC10YHRgtC+0LLRi9C5INCj0KYg0KDQotCaICjQoNCi0JvQsNCx0YEpMB4XDTE4MDMwNzA3Mjc1M1oXDTE5MDMwNzA3Mzc1M1owggELMR8wHQYJKoZIhvcNAQkCDBDQotCh0JzQrdCSM1/QmtCaMRowGAYIKoUDA4EDAQESDDAwNzcwNzA0OTM4ODEYMBYGBSqFA2QBEg0xMDI3NzAwMTk4NzY3MSgwJgYDVQQKDB/Qn9CQ0J4gwqvQoNC+0YHRgtC10LvQtdC60L7QvMK7MSYwJAYDVQQHDB3QodCw0L3QutGCLdCf0LXRgtC10YDQsdGD0YDQszEpMCcGA1UECAwgNzgg0KHQsNC90LrRgi3Qn9C10YLQtdGA0LHRg9GA0LMxCzAJBgNVBAYTAlJVMSgwJgYDVQQDDB/Qn9CQ0J4gwqvQoNC+0YHRgtC10LvQtdC60L7QvMK7MGMwHAYGKoUDAgITMBIGByqFAwICJAAGByqFAwICHgEDQwAEQMC71XesoJAavwGJgMlhlPywcaFx3HYPwMUIYBhtoglDW3BhQUzgIXFQ6uvD7FUshBl38VH+AXZM/BkzGGzpzFSjggSDMIIEfzAOBgNVHQ8BAf8EBAMCBPAwHQYDVR0OBBYEFItxgSnIqbor48DVSfsV11JpIwXDMIIBiAYDVR0jBIIBfzCCAXuAFD7vGT8PuXmw8eYpIaPkuZW5pe6QoYIBTqSCAUowggFGMRgwFgYFKoUDZAESDTEyMzQ1Njc4OTAxMjMxGjAYBggqhQMDgQMBARIMMDAxMjM0NTY3ODkwMSkwJwYDVQQJDCDQodGD0YnQtdCy0YHQutC40Lkg0LLQsNC7INC0LiAyNjEXMBUGCSqGSIb3DQEJARYIY2FAcnQucnUxCzAJBgNVBAYTAlJVMRgwFgYDVQQIDA83NyDQnNC+0YHQutCy0LAxFTATBgNVBAcMDNCc0L7RgdC60LLQsDEkMCIGA1UECgwb0J7QkNCeINCg0L7RgdGC0LXQu9C10LrQvtC8MTAwLgYDVQQLDCfQo9C00L7RgdGC0L7QstC10YDRj9GO0YnQuNC5INGG0LXQvdGC0YAxNDAyBgNVBAMMK9Ci0LXRgdGC0L7QstGL0Lkg0KPQpiDQoNCi0JogKNCg0KLQm9Cw0LHRgSmCEQFyCwFWUAC5s+cRzzq+NHegMB0GA1UdJQQWMBQGCCsGAQUFBwMCBggrBgEFBQcDBDAnBgkrBgEEAYI3FQoEGjAYMAoGCCsGAQUFBwMCMAoGCCsGAQUFBwMEMB0GA1UdIAQWMBQwCAYGKoUDZHEBMAgGBiqFA2RxAjArBgNVHRAEJDAigA8yMDE4MDMwNzA3Mjc1M1qBDzIwMTkwMzA3MDcyNzUzWjCCATQGBSqFA2RwBIIBKTCCASUMKyLQmtGA0LjQv9GC0L7Qn9GA0L4gQ1NQIiAo0LLQtdGA0YHQuNGPIDMuOSkMLCLQmtGA0LjQv9GC0L7Qn9GA0L4g0KPQpiIgKNCy0LXRgNGB0LjQuCAyLjApDGPQodC10YDRgtC40YTQuNC60LDRgiDRgdC+0L7RgtCy0LXRgtGB0YLQstC40Y8g0KTQodCRINCg0L7RgdGB0LjQuCDihJYg0KHQpC8xMjQtMjUzOSDQvtGCIDE1LjAxLjIwMTUMY9Ch0LXRgNGC0LjRhNC40LrQsNGCINGB0L7QvtGC0LLQtdGC0YHRgtCy0LjRjyDQpNCh0JEg0KDQvtGB0YHQuNC4IOKEliDQodCkLzEyOC0yODgxINC+0YIgMTIuMDQuMjAxNjA2BgUqhQNkbwQtDCsi0JrRgNC40L/RgtC+0J/RgNC+IENTUCIgKNCy0LXRgNGB0LjRjyAzLjkpMGUGA1UdHwReMFwwWqBYoFaGVGh0dHA6Ly9jZXJ0ZW5yb2xsLnRlc3QuZ29zdXNsdWdpLnJ1L2NkcC8zZWVmMTkzZjBmYjk3OWIwZjFlNjI5MjFhM2U0Yjk5NWI5YTVlZTkwLmNybDBXBggrBgEFBQcBAQRLMEkwRwYIKwYBBQUHMAKGO2h0dHA6Ly9jZXJ0ZW5yb2xsLnRlc3QuZ29zdXNsdWdpLnJ1L2NkcC90ZXN0X2NhX3J0bGFiczIuY2VyMAgGBiqFAwICAwNBAAfuLimwLO1RNL+ekh4hgnPu+yyLSiF2xGN4yZqWeA4d5VKU2zxveBMmSb4nvJFZ/3Qod1aQDdlgTRObLhKnEO8=</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ns2:SMEVSignature></ns2:ResponseMessage></ns2:GetResponseResponse></soap:Body></soap:Envelope>";
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);
        String target ="xml4test/VIPUL-respansw-0001.XML";
        Path p = Paths.get(target);
        byte[] arr = Files.readAllBytes(p);
        long startTime = System.currentTimeMillis();
        PreparedStatement insertResponseXML = f.getConn().prepareStatement("set concat_null_yields_null off; EXEC smev_test_xml 'z', 999, ?;");
        insertResponseXML.setBytes(1, res.getBytes());
        for (int i =0; i<10000; i++){
            insertResponseXML.executeUpdate();

        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("total="+elapsedTime/1000+"   Sekunden\n");
        System.out.println("per request "+(elapsedTime)/10000.0+"   milleseconds");


    }


    @Test
    public void smev1() throws SQLException {
        Readfile r = new Readfile("sqlset");
        Executor f = new Executor(r.read(), true);

        ResultSet Select2=null;
        try {
            Select2 = f.submit( "EXEC smev_answer1s 'x'");
        } catch (SQLException e) {
        }
        if (Select2.next())
            System.out.println( Select2.getString("f_srv"));

    }

    @Test
    public void banksread() throws SQLException, IOException {
        Readfile r = new Readfile("sqlset2");
        Executor f = new Executor(r.read(), true);


        ResultSet Select2 = f.submit(
                        "SELECT *\n" +
                                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                                "WHERE TABLE_NAME = N'banks'");//"set concat_null_yields_null off; SELECT * from dbo.banks;");
        
        assertNotEquals(null, Select2);
        if (Select2.next()){
            for (int i=1; i<Select2.getFetchSize();i++){
                String res = Select2.getString(i);
                System.out.println(res);
            }

        }


    }




}