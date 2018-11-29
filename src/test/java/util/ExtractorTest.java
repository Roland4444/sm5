package util;

import crypto.Gost3411Hash;
import org.apache.xml.security.transforms.TransformationException;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class ExtractorTest {

    @Test
    public void parse() throws IOException {
        Extractor ext = new Extractor();
        assertEquals("<Sender><addtional>ghghghgh</addtional><additi>fgdffdghfdhfddfhdfhfdfdhfdhfdhdfhdffh</additi></Sender>", ext.parse("xml4test/3.xml", "Sender"));
    }

    @Test
    public void parse222() throws IOException {
        String etalon = "<ns1:FNSVipIPResponse xmlns:ns1=\"urn://x-artefacts-fns-vipip-tosmv-ru/311-15/4.0.5\" ИдДок=\"33F0F745-FF7B-40B6-87B6-C9B4080E201A\"><ns1:СвИП xmlns:fnst=\"urn://x-artefacts-fns/vipip-types/4.0.5\" ДатаВып=\"2015-10-26\" ДатаОГРНИП=\"2005-04-19\" КодВидИП=\"1\" НаимВидИП=\"ИНДИВИДУАЛЬНЫЙ ПРЕДПРИНИМАТЕЛЬ\" ОГРНИП=\"305500910900012\"><ns1:СвФЛ Пол=\"1\"><ns1:ФИОРус Имя=\"ИМЯ 55009109000004\" Отчество=\"ОТЧЕСТВО 55009109000004\" Фамилия=\"ФАМИЛИЯ 55009109000004\"/><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвФЛ><ns1:СвГражд ВидГражд=\"1\" НаимСтран=\"РОССИЯ\" ОКСМ=\"643\"><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвГражд><ns1:СвАдрМЖ><ns1:АдресРФ КодРегион=\"50\"><fnst:Регион НаимРегион=\"МОСКОВСКАЯ\" ТипРегион=\"ОБЛАСТЬ\"/></ns1:АдресРФ><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвАдрМЖ><ns1:СвРегИП ДатаОГРНИП=\"2005-04-19\" НаимРО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\" ОГРНИП=\"305500910900012\"/><ns1:СвРегОрг АдрРО=\",142000,МОСКОВСКАЯ ОБЛ,,ДОМОДЕДОВО Г,,КРАСНОАРМЕЙСКАЯ УЛ,42А,,\" КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\"><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвРегОрг><ns1:СвУчетНО ДатаПостУч=\"2005-04-19\" ИННФЛ=\"500907235960\"><ns1:СвНО КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г.ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ\"/><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвУчетНО><ns1:СвОКВЭД><ns1:СвОКВЭДОсн КодОКВЭД=\"52.62\" НаимОКВЭД=\"РОЗНИЧНАЯ ТОРГОВЛЯ В ПАЛАТКАХ И НА РЫНКАХ\"><fnst:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвОКВЭДОсн><ns1:СвОКВЭДДоп КодОКВЭД=\"52.61\" НаимОКВЭД=\"РОЗНИЧНАЯ ТОРГОВЛЯ ПО ЗАКАЗАМ\"><fnst:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвОКВЭДДоп><ns1:СвОКВЭДДоп КодОКВЭД=\"52.63\" НаимОКВЭД=\"ПРОЧАЯ РОЗНИЧНАЯ ТОРГОВЛЯ ВНЕ МАГАЗИНОВ\"><fnst:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвОКВЭДДоп></ns1:СвОКВЭД><ns1:СвЗапЕГРИП ГРНИП=\"305500910900012\" ДатаЗап=\"2005-04-19\" ИдЗап=\"305500910900012\"><ns1:ВидЗап КодСПВЗ=\"21211\" НаимВидЗап=\"(Р21001) РЕГИСТРАЦИЯ ФЛ В КАЧЕСТВЕ ИП\"/><ns1:СвРегОрг КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\"/><ns1:СведПредДок><ns1:НаимДок>ДОКУМЕНТ, УДОСТОВЕРЯЮЩИЙ ЛИЧНОСТЬ ГРАЖДАНИНА РОССИИ</ns1:НаимДок></ns1:СведПредДок><ns1:СведПредДок><ns1:НаимДок>ДОКУМЕНТ ОБ УПЛАТЕ ГОСУДАРСТВЕННОЙ ПОШЛИНЫ</ns1:НаимДок></ns1:СведПредДок><ns1:СведПредДок><ns1:НаимДок>Р21001 ЗАЯВЛЕНИЕ О РЕГИСТРАЦИИ ФЛ В КАЧЕСТВЕ ИП</ns1:НаимДок></ns1:СведПредДок><ns1:СвСвид ДатаВыдСвид=\"2005-04-19\" Номер=\"000285309\" Серия=\"50\"/></ns1:СвЗапЕГРИП><ns1:СвЗапЕГРИП ГРНИП=\"305500910900012\" ДатаЗап=\"2005-04-19\" ИдЗап=\"405500935601018\"><ns1:ВидЗап КодСПВЗ=\"23200\" НаимВидЗап=\"ВНЕСЕНИЕ СВЕДЕНИЙ ОБ УЧЕТЕ В НАЛОГОВОМ ОРГАНЕ\"/><ns1:СвРегОрг КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\"/></ns1:СвЗапЕГРИП></ns1:СвИП></ns1:FNSVipIPResponse>";
        Extractor ext = new Extractor();
        assertEquals(etalon, ext.parse("xml4test/res.xml", "ns1:FNSVipIPResponse"));
    }


    @Test
    public void parse2() throws IOException, TransformationException, NoSuchAlgorithmException {
        Extractor ext = new Extractor();
        String result = "<ns:SenderProvidedRequestData Id=\"SIGNED_BY_CONSUMER\" xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\">\t<ns:MessageID>db0486d0-3c08-11e5-95e2-d4c9eff07b77</ns:MessageID><ns2:MessagePrimaryContent><ns1:BreachRequest xmlns:ns1=\"urn://x-artefacts-gibdd-gov-ru/breach/root/1.0\"  xmlns:ns2=\"urn://x-artefacts-gibdd-gov-ru/breach/commons/1.0\"  xmlns:ns3=\"urn://x-artefacts-smev-gov-ru/supplementary/commons/1.0.1\" Id=\"PERSONAL_SIGNATURE\"> <ns1:RequestedInformation> <ns2:RegPointNum>Т785ЕС57</ns2:RegPointNum> </ns1:RequestedInformation> <ns1:Governance> <ns2:Name>ГИБДД РФ</ns2:Name> <ns2:Code>GIBDD</ns2:Code> <ns2:OfficialPerson> <ns3:FamilyName>Загурский</ns3:FamilyName> <ns3:FirstName>Андрей</ns3:FirstName> <ns3:Patronymic>Петрович</ns3:Patronymic> </ns2:OfficialPerson></ns1:Governance> </ns1:BreachRequest> </ns2:MessagePrimaryContent>\t<ns:TestMessage/></ns:SenderProvidedRequestData>";
        assertEquals(result, ext.parse("xml4test/1.xml", "SenderProvidedRequestData"));
        FileWriter wr = new FileWriter("xml4test/reqdataonly.xml");
        wr.write(ext.parse("xml4test/1.xml", "SenderProvidedRequestData"));
        wr.close();
        org.apache.xml.security.Init.init();
        SmevTransformSpi trans = new SmevTransformSpi();
        InputStream in  = new FileInputStream("xml4test/reqdataonly.xml");
        OutputStream out  = new FileOutputStream("xml4test/result.xml");
        trans.process(in, out);
        Path p = Paths.get("xml4test/result.xml");
        byte[] arr = Files.readAllBytes(p);
        Gost3411Hash hasher = new Gost3411Hash();
        assertEquals("/jXl70XwnttJB5sSokwh8SaVHwo2gjgILSu0qBaLUAo=", hasher.h_Base64rfc2045(arr));
    }

    @Test
    public void extractRaw() throws IOException {
        Extractor ext = new Extractor();
        String data ="<Sender>12</Sender>";
        FileWriter wr = new FileWriter("xml4test/raww.xml");
        wr.write(data);
        wr.close();
        assertEquals("12", ext.extractRaw("xml4test/raww.xml", "Sender"));
    }

    @Test
    public void extractRaw2() throws IOException {
        Extractor ext = new Extractor();
        String data ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:GetResponseResponse xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ns3=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/faults/1.1\"><ns2:ResponseMessage><ns2:Response Id=\"SIGNED_BY_SMEV\"><ns2:OriginalMessageId>257eec40-7edc-11e8-8664-b1d321bf4237</ns2:OriginalMessageId><ns2:SenderProvidedResponseData Id=\"SIGNED_BY_CALLER\"><ns2:MessageID>adcc1553-7eba-11e8-83c9-fa163e24a723</ns2:MessageID><ns2:To>eyJzaWQiOjg5MjgzLCJtaWQiOiIyNTdlZWM0MC03ZWRjLTExZTgtODY2NC1iMWQzMjFiZjQyMzciLCJlb2wiOjAsInNsYyI6InJvc2them5hLnJ1X2dpc2dtcF94c2Rfc2VydmljZXNfaW1wb3J0LXBheW1lbnRzXzIuMC4wX0ltcG9ydFBheW1lbnRzUmVxdWVzdCIsIm1ubSI6Ijk4MTYwMV8zVCJ9</ns2:To><ns2:RequestRejected><ns2:RejectionReasonCode>UNKNOWN_REQUEST_DESCRIPTION</ns2:RejectionReasonCode><ns2:RejectionReasonDescription>Ошибка идентификации сценария тестирования. Доступны следующие сценарии: [Успешный прием необходимой для уплаты информации (начисления) (2.0.1) (//req:ImportPaymentsRequest/pkg:PaymentsPackage/pkg:ImportedPayment[@paymentId = '10471020010005232407201700000001'])]</ns2:RejectionReasonDescription></ns2:RequestRejected></ns2:SenderProvidedResponseData><ns2:MessageMetadata><ns2:MessageId>adcc1553-7eba-11e8-83c9-fa163e24a723</ns2:MessageId><ns2:MessageType>RESPONSE</ns2:MessageType><ns2:Sender><ns2:Mnemonic>emu</ns2:Mnemonic><ns2:HumanReadableName>emu</ns2:HumanReadableName></ns2:Sender><ns2:SendingTimestamp>2018-07-03T15:14:48.000+03:00</ns2:SendingTimestamp><ns2:DestinationName>unknown</ns2:DestinationName><ns2:Recipient><ns2:Mnemonic>981601_3T</ns2:Mnemonic><ns2:HumanReadableName>ВКАБАНК</ns2:HumanReadableName></ns2:Recipient><ns2:SupplementaryData><ns2:DetectedContentTypeName>not detected</ns2:DetectedContentTypeName><ns2:InteractionType>NotDetected</ns2:InteractionType></ns2:SupplementaryData><ns2:DeliveryTimestamp>2018-07-04T12:36:06.042+03:00</ns2:DeliveryTimestamp><ns2:Status>responseIsDelivered</ns2:Status></ns2:MessageMetadata><ns2:SenderInformationSystemSignature><ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><ds:Reference URI=\"#SIGNED_BY_CALLER\"><ds:Transforms><ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:Transform Algorithm=\"urn://smev-gov-ru/xmldsig/transform\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><ds:DigestValue>VJK8qTLGH+k5YADSxVf2svT2hMCzW9LXOWmsBtxU/1Q=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>ffw7X4Ee1kCKbOBQv71iWwRCwwqc+lkQAMeOOkseEpMdnAH4isYd2GM85C1VrRzCMes9K2b26M21GFFhygn8+Q==</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>MIIH3DCCB4ugAwIBAgIRAXILAVZQAHCj6BFWMr8dB1owCAYGKoUDAgIDMIIBRjEYMBYGBSqFA2QBEg0xMjM0NTY3ODkwMTIzMRowGAYIKoUDA4EDAQESDDAwMTIzNDU2Nzg5MDEpMCcGA1UECQwg0KHRg9GJ0LXQstGB0LrQuNC5INCy0LDQuyDQtC4gMjYxFzAVBgkqhkiG9w0BCQEWCGNhQHJ0LnJ1MQswCQYDVQQGEwJSVTEYMBYGA1UECAwPNzcg0JzQvtGB0LrQstCwMRUwEwYDVQQHDAzQnNC+0YHQutCy0LAxJDAiBgNVBAoMG9Ce0JDQniDQoNC+0YHRgtC10LvQtdC60L7QvDEwMC4GA1UECwwn0KPQtNC+0YHRgtC+0LLQtdGA0Y/RjtGJ0LjQuSDRhtC10L3RgtGAMTQwMgYDVQQDDCvQotC10YHRgtC+0LLRi9C5INCj0KYg0KDQotCaICjQoNCi0JvQsNCx0YEpMB4XDTE4MDMyODA2NTUxMVoXDTE5MDMyODA3MDUxMVowggEPMR8wHQYJKoZIhvcNAQkCDBDQotCh0JzQrdCSM1/QrdCcMRowGAYIKoUDA4EDAQESDDAwNzcwNzA0OTM4ODEYMBYGBSqFA2QBEg0xMDI3NzAwMTk4NzY3MSgwJgYDVQQKDB/Qn9CQ0J4gwqvQoNC+0YHRgtC10LvQtdC60L7QvMK7MSYwJAYDVQQHDB3QodCw0L3QutGCLdCf0LXRgtC10YDQsdGD0YDQszEtMCsGA1UECAwkNzgg0LMuINCh0LDQvdC60YIt0J/QtdGC0LXRgNCx0YPRgNCzMQswCQYDVQQGEwJSVTEoMCYGA1UEAwwf0J/QkNCeIMKr0KDQvtGB0YLQtdC70LXQutC+0LzCuzBjMBwGBiqFAwICEzASBgcqhQMCAiQABgcqhQMCAh4BA0MABEDt/NkbN2KN5D7zCI5yysq8B3b19K4YrnYo3CvHvStJcSFfH5m2oBI/sS+I4URk7caRdrMqgleRcAmpwI1oWlfeo4IEgzCCBH8wDgYDVR0PAQH/BAQDAgTwMB0GA1UdDgQWBBRZdtXJC9SYq9wCOjQMH8YaLHyT2DCCAYgGA1UdIwSCAX8wggF7gBQ+7xk/D7l5sPHmKSGj5LmVuaXukKGCAU6kggFKMIIBRjEYMBYGBSqFA2QBEg0xMjM0NTY3ODkwMTIzMRowGAYIKoUDA4EDAQESDDAwMTIzNDU2Nzg5MDEpMCcGA1UECQwg0KHRg9GJ0LXQstGB0LrQuNC5INCy0LDQuyDQtC4gMjYxFzAVBgkqhkiG9w0BCQEWCGNhQHJ0LnJ1MQswCQYDVQQGEwJSVTEYMBYGA1UECAwPNzcg0JzQvtGB0LrQstCwMRUwEwYDVQQHDAzQnNC+0YHQutCy0LAxJDAiBgNVBAoMG9Ce0JDQniDQoNC+0YHRgtC10LvQtdC60L7QvDEwMC4GA1UECwwn0KPQtNC+0YHRgtC+0LLQtdGA0Y/RjtGJ0LjQuSDRhtC10L3RgtGAMTQwMgYDVQQDDCvQotC10YHRgtC+0LLRi9C5INCj0KYg0KDQotCaICjQoNCi0JvQsNCx0YEpghEBcgsBVlAAubPnEc86vjR3oDAdBgNVHSUEFjAUBggrBgEFBQcDAgYIKwYBBQUHAwQwJwYJKwYBBAGCNxUKBBowGDAKBggrBgEFBQcDAjAKBggrBgEFBQcDBDAdBgNVHSAEFjAUMAgGBiqFA2RxATAIBgYqhQNkcQIwKwYDVR0QBCQwIoAPMjAxODAzMjgwNjU1MTBagQ8yMDE5MDMyODA2NTUxMFowggE0BgUqhQNkcASCASkwggElDCsi0JrRgNC40L/RgtC+0J/RgNC+IENTUCIgKNCy0LXRgNGB0LjRjyAzLjkpDCwi0JrRgNC40L/RgtC+0J/RgNC+INCj0KYiICjQstC10YDRgdC40LggMi4wKQxj0KHQtdGA0YLQuNGE0LjQutCw0YIg0YHQvtC+0YLQstC10YLRgdGC0LLQuNGPINCk0KHQkSDQoNC+0YHRgdC40Lgg4oSWINCh0KQvMTI0LTI1Mzkg0L7RgiAxNS4wMS4yMDE1DGPQodC10YDRgtC40YTQuNC60LDRgiDRgdC+0L7RgtCy0LXRgtGB0YLQstC40Y8g0KTQodCRINCg0L7RgdGB0LjQuCDihJYg0KHQpC8xMjgtMjg4MSDQvtGCIDEyLjA0LjIwMTYwNgYFKoUDZG8ELQwrItCa0YDQuNC/0YLQvtCf0YDQviBDU1AiICjQstC10YDRgdC40Y8gMy45KTBlBgNVHR8EXjBcMFqgWKBWhlRodHRwOi8vY2VydGVucm9sbC50ZXN0Lmdvc3VzbHVnaS5ydS9jZHAvM2VlZjE5M2YwZmI5NzliMGYxZTYyOTIxYTNlNGI5OTViOWE1ZWU5MC5jcmwwVwYIKwYBBQUHAQEESzBJMEcGCCsGAQUFBzAChjtodHRwOi8vY2VydGVucm9sbC50ZXN0Lmdvc3VzbHVnaS5ydS9jZHAvdGVzdF9jYV9ydGxhYnMyLmNlcjAIBgYqhQMCAgMDQQDC4cx4P093b5ai+RKhGdvvkxFMVxK0UPy3JEUUs3zmsC1parcrJK/QqCIVmPmdUehIIHSBDid/kcE1xUdE6X32</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ns2:SenderInformationSystemSignature></ns2:Response><ns2:SMEVSignature><ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><ds:Reference URI=\"#SIGNED_BY_SMEV\"><ds:Transforms><ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:Transform Algorithm=\"urn://smev-gov-ru/xmldsig/transform\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><ds:DigestValue>3qF5w3F9+7us7G84nYmjRlt4IAytsxGYiE7/rJWEULs=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>ggLrTKQgITzc/gHZm4uyf6xWZgkTIul47ylSrzOJmPNlRGzNOwQiGjD1cya2fsVSitpu7KIoceCSs55FyoXagg==</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>MIIH2DCCB4egAwIBAgIRAXILAVZQAHCj6BHaIfbqMnEwCAYGKoUDAgIDMIIBRjEYMBYGBSqFA2QBEg0xMjM0NTY3ODkwMTIzMRowGAYIKoUDA4EDAQESDDAwMTIzNDU2Nzg5MDEpMCcGA1UECQwg0KHRg9GJ0LXQstGB0LrQuNC5INCy0LDQuyDQtC4gMjYxFzAVBgkqhkiG9w0BCQEWCGNhQHJ0LnJ1MQswCQYDVQQGEwJSVTEYMBYGA1UECAwPNzcg0JzQvtGB0LrQstCwMRUwEwYDVQQHDAzQnNC+0YHQutCy0LAxJDAiBgNVBAoMG9Ce0JDQniDQoNC+0YHRgtC10LvQtdC60L7QvDEwMC4GA1UECwwn0KPQtNC+0YHRgtC+0LLQtdGA0Y/RjtGJ0LjQuSDRhtC10L3RgtGAMTQwMgYDVQQDDCvQotC10YHRgtC+0LLRi9C5INCj0KYg0KDQotCaICjQoNCi0JvQsNCx0YEpMB4XDTE4MDMwNzA3Mjc1M1oXDTE5MDMwNzA3Mzc1M1owggELMR8wHQYJKoZIhvcNAQkCDBDQotCh0JzQrdCSM1/QmtCaMRowGAYIKoUDA4EDAQESDDAwNzcwNzA0OTM4ODEYMBYGBSqFA2QBEg0xMDI3NzAwMTk4NzY3MSgwJgYDVQQKDB/Qn9CQ0J4gwqvQoNC+0YHRgtC10LvQtdC60L7QvMK7MSYwJAYDVQQHDB3QodCw0L3QutGCLdCf0LXRgtC10YDQsdGD0YDQszEpMCcGA1UECAwgNzgg0KHQsNC90LrRgi3Qn9C10YLQtdGA0LHRg9GA0LMxCzAJBgNVBAYTAlJVMSgwJgYDVQQDDB/Qn9CQ0J4gwqvQoNC+0YHRgtC10LvQtdC60L7QvMK7MGMwHAYGKoUDAgITMBIGByqFAwICJAAGByqFAwICHgEDQwAEQMC71XesoJAavwGJgMlhlPywcaFx3HYPwMUIYBhtoglDW3BhQUzgIXFQ6uvD7FUshBl38VH+AXZM/BkzGGzpzFSjggSDMIIEfzAOBgNVHQ8BAf8EBAMCBPAwHQYDVR0OBBYEFItxgSnIqbor48DVSfsV11JpIwXDMIIBiAYDVR0jBIIBfzCCAXuAFD7vGT8PuXmw8eYpIaPkuZW5pe6QoYIBTqSCAUowggFGMRgwFgYFKoUDZAESDTEyMzQ1Njc4OTAxMjMxGjAYBggqhQMDgQMBARIMMDAxMjM0NTY3ODkwMSkwJwYDVQQJDCDQodGD0YnQtdCy0YHQutC40Lkg0LLQsNC7INC0LiAyNjEXMBUGCSqGSIb3DQEJARYIY2FAcnQucnUxCzAJBgNVBAYTAlJVMRgwFgYDVQQIDA83NyDQnNC+0YHQutCy0LAxFTATBgNVBAcMDNCc0L7RgdC60LLQsDEkMCIGA1UECgwb0J7QkNCeINCg0L7RgdGC0LXQu9C10LrQvtC8MTAwLgYDVQQLDCfQo9C00L7RgdGC0L7QstC10YDRj9GO0YnQuNC5INGG0LXQvdGC0YAxNDAyBgNVBAMMK9Ci0LXRgdGC0L7QstGL0Lkg0KPQpiDQoNCi0JogKNCg0KLQm9Cw0LHRgSmCEQFyCwFWUAC5s+cRzzq+NHegMB0GA1UdJQQWMBQGCCsGAQUFBwMCBggrBgEFBQcDBDAnBgkrBgEEAYI3FQoEGjAYMAoGCCsGAQUFBwMCMAoGCCsGAQUFBwMEMB0GA1UdIAQWMBQwCAYGKoUDZHEBMAgGBiqFA2RxAjArBgNVHRAEJDAigA8yMDE4MDMwNzA3Mjc1M1qBDzIwMTkwMzA3MDcyNzUzWjCCATQGBSqFA2RwBIIBKTCCASUMKyLQmtGA0LjQv9GC0L7Qn9GA0L4gQ1NQIiAo0LLQtdGA0YHQuNGPIDMuOSkMLCLQmtGA0LjQv9GC0L7Qn9GA0L4g0KPQpiIgKNCy0LXRgNGB0LjQuCAyLjApDGPQodC10YDRgtC40YTQuNC60LDRgiDRgdC+0L7RgtCy0LXRgtGB0YLQstC40Y8g0KTQodCRINCg0L7RgdGB0LjQuCDihJYg0KHQpC8xMjQtMjUzOSDQvtGCIDE1LjAxLjIwMTUMY9Ch0LXRgNGC0LjRhNC40LrQsNGCINGB0L7QvtGC0LLQtdGC0YHRgtCy0LjRjyDQpNCh0JEg0KDQvtGB0YHQuNC4IOKEliDQodCkLzEyOC0yODgxINC+0YIgMTIuMDQuMjAxNjA2BgUqhQNkbwQtDCsi0JrRgNC40L/RgtC+0J/RgNC+IENTUCIgKNCy0LXRgNGB0LjRjyAzLjkpMGUGA1UdHwReMFwwWqBYoFaGVGh0dHA6Ly9jZXJ0ZW5yb2xsLnRlc3QuZ29zdXNsdWdpLnJ1L2NkcC8zZWVmMTkzZjBmYjk3OWIwZjFlNjI5MjFhM2U0Yjk5NWI5YTVlZTkwLmNybDBXBggrBgEFBQcBAQRLMEkwRwYIKwYBBQUHMAKGO2h0dHA6Ly9jZXJ0ZW5yb2xsLnRlc3QuZ29zdXNsdWdpLnJ1L2NkcC90ZXN0X2NhX3J0bGFiczIuY2VyMAgGBiqFAwICAwNBAAfuLimwLO1RNL+ekh4hgnPu+yyLSiF2xGN4yZqWeA4d5VKU2zxveBMmSb4nvJFZ/3Qod1aQDdlgTRObLhKnEO8=</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ns2:SMEVSignature></ns2:ResponseMessage></ns2:GetResponseResponse></soap:Body></soap:Envelope>";
        FileWriter wr = new FileWriter("xml4test/raww5.xml");
        wr.write(data);
        wr.close();
        assertEquals("adcc1553-7eba-11e8-83c9-fa163e24a723", ext.extractRaw("xml4test/raww5.xml", "MessageID"));
    }

    @Test
    public void extractRawextended() throws IOException {
        Extractor ext = new Extractor();
        String data ="<  12Sender mr  >12</   12Sender mr   >";
        FileWriter wr = new FileWriter("xml4test/raww.xml");
        wr.write(data);
        wr.close();
        assertEquals("12", ext.extractRaw("xml4test/raww.xml", "Sender"));
    }

    @Test
    public void extractTagValue() {
        Extractor extractor = new Extractor();
        String data  = "aaaa<br>67586</br>";
        assertEquals("67586", extractor.extractTagValue(data, "br"));
    }

    @Test
    public void parseTagFromByte() throws IOException {
        String filename="xml4test/res.xml";
        Path p = Paths.get(filename);
        byte[] arr = Files.readAllBytes(p);
        String etalon = "<ns1:FNSVipIPResponse xmlns:ns1=\"urn://x-artefacts-fns-vipip-tosmv-ru/311-15/4.0.5\" ИдДок=\"33F0F745-FF7B-40B6-87B6-C9B4080E201A\"><ns1:СвИП xmlns:fnst=\"urn://x-artefacts-fns/vipip-types/4.0.5\" ДатаВып=\"2015-10-26\" ДатаОГРНИП=\"2005-04-19\" КодВидИП=\"1\" НаимВидИП=\"ИНДИВИДУАЛЬНЫЙ ПРЕДПРИНИМАТЕЛЬ\" ОГРНИП=\"305500910900012\"><ns1:СвФЛ Пол=\"1\"><ns1:ФИОРус Имя=\"ИМЯ 55009109000004\" Отчество=\"ОТЧЕСТВО 55009109000004\" Фамилия=\"ФАМИЛИЯ 55009109000004\"/><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвФЛ><ns1:СвГражд ВидГражд=\"1\" НаимСтран=\"РОССИЯ\" ОКСМ=\"643\"><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвГражд><ns1:СвАдрМЖ><ns1:АдресРФ КодРегион=\"50\"><fnst:Регион НаимРегион=\"МОСКОВСКАЯ\" ТипРегион=\"ОБЛАСТЬ\"/></ns1:АдресРФ><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвАдрМЖ><ns1:СвРегИП ДатаОГРНИП=\"2005-04-19\" НаимРО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\" ОГРНИП=\"305500910900012\"/><ns1:СвРегОрг АдрРО=\",142000,МОСКОВСКАЯ ОБЛ,,ДОМОДЕДОВО Г,,КРАСНОАРМЕЙСКАЯ УЛ,42А,,\" КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\"><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвРегОрг><ns1:СвУчетНО ДатаПостУч=\"2005-04-19\" ИННФЛ=\"500907235960\"><ns1:СвНО КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г.ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ\"/><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвУчетНО><ns1:СвОКВЭД><ns1:СвОКВЭДОсн КодОКВЭД=\"52.62\" НаимОКВЭД=\"РОЗНИЧНАЯ ТОРГОВЛЯ В ПАЛАТКАХ И НА РЫНКАХ\"><fnst:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвОКВЭДОсн><ns1:СвОКВЭДДоп КодОКВЭД=\"52.61\" НаимОКВЭД=\"РОЗНИЧНАЯ ТОРГОВЛЯ ПО ЗАКАЗАМ\"><fnst:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвОКВЭДДоп><ns1:СвОКВЭДДоп КодОКВЭД=\"52.63\" НаимОКВЭД=\"ПРОЧАЯ РОЗНИЧНАЯ ТОРГОВЛЯ ВНЕ МАГАЗИНОВ\"><fnst:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвОКВЭДДоп></ns1:СвОКВЭД><ns1:СвЗапЕГРИП ГРНИП=\"305500910900012\" ДатаЗап=\"2005-04-19\" ИдЗап=\"305500910900012\"><ns1:ВидЗап КодСПВЗ=\"21211\" НаимВидЗап=\"(Р21001) РЕГИСТРАЦИЯ ФЛ В КАЧЕСТВЕ ИП\"/><ns1:СвРегОрг КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\"/><ns1:СведПредДок><ns1:НаимДок>ДОКУМЕНТ, УДОСТОВЕРЯЮЩИЙ ЛИЧНОСТЬ ГРАЖДАНИНА РОССИИ</ns1:НаимДок></ns1:СведПредДок><ns1:СведПредДок><ns1:НаимДок>ДОКУМЕНТ ОБ УПЛАТЕ ГОСУДАРСТВЕННОЙ ПОШЛИНЫ</ns1:НаимДок></ns1:СведПредДок><ns1:СведПредДок><ns1:НаимДок>Р21001 ЗАЯВЛЕНИЕ О РЕГИСТРАЦИИ ФЛ В КАЧЕСТВЕ ИП</ns1:НаимДок></ns1:СведПредДок><ns1:СвСвид ДатаВыдСвид=\"2005-04-19\" Номер=\"000285309\" Серия=\"50\"/></ns1:СвЗапЕГРИП><ns1:СвЗапЕГРИП ГРНИП=\"305500910900012\" ДатаЗап=\"2005-04-19\" ИдЗап=\"405500935601018\"><ns1:ВидЗап КодСПВЗ=\"23200\" НаимВидЗап=\"ВНЕСЕНИЕ СВЕДЕНИЙ ОБ УЧЕТЕ В НАЛОГОВОМ ОРГАНЕ\"/><ns1:СвРегОрг КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\"/></ns1:СвЗапЕГРИП></ns1:СвИП></ns1:FNSVipIPResponse>";
        Extractor ext = new Extractor();
        assertEquals(etalon, ext.parseTagFromByte(arr, "ns1:FNSVipIPResponse"));
    }

    @Test
    public void FIO() throws IOException {
        String filename="xml4test/res.xml";
        Path p = Paths.get(filename);
        byte[] arr = Files.readAllBytes(p);
        String etalon = "<ns1:FNSVipIPResponse xmlns:ns1=\"urn://x-artefacts-fns-vipip-tosmv-ru/311-15/4.0.5\" ИдДок=\"33F0F745-FF7B-40B6-87B6-C9B4080E201A\"><ns1:СвИП xmlns:fnst=\"urn://x-artefacts-fns/vipip-types/4.0.5\" ДатаВып=\"2015-10-26\" ДатаОГРНИП=\"2005-04-19\" КодВидИП=\"1\" НаимВидИП=\"ИНДИВИДУАЛЬНЫЙ ПРЕДПРИНИМАТЕЛЬ\" ОГРНИП=\"305500910900012\"><ns1:СвФЛ Пол=\"1\"><ns1:ФИОРус Имя=\"ИМЯ 55009109000004\" Отчество=\"ОТЧЕСТВО 55009109000004\" Фамилия=\"ФАМИЛИЯ 55009109000004\"/><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвФЛ><ns1:СвГражд ВидГражд=\"1\" НаимСтран=\"РОССИЯ\" ОКСМ=\"643\"><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвГражд><ns1:СвАдрМЖ><ns1:АдресРФ КодРегион=\"50\"><fnst:Регион НаимРегион=\"МОСКОВСКАЯ\" ТипРегион=\"ОБЛАСТЬ\"/></ns1:АдресРФ><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвАдрМЖ><ns1:СвРегИП ДатаОГРНИП=\"2005-04-19\" НаимРО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\" ОГРНИП=\"305500910900012\"/><ns1:СвРегОрг АдрРО=\",142000,МОСКОВСКАЯ ОБЛ,,ДОМОДЕДОВО Г,,КРАСНОАРМЕЙСКАЯ УЛ,42А,,\" КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\"><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвРегОрг><ns1:СвУчетНО ДатаПостУч=\"2005-04-19\" ИННФЛ=\"500907235960\"><ns1:СвНО КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г.ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ\"/><ns1:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвУчетНО><ns1:СвОКВЭД><ns1:СвОКВЭДОсн КодОКВЭД=\"52.62\" НаимОКВЭД=\"РОЗНИЧНАЯ ТОРГОВЛЯ В ПАЛАТКАХ И НА РЫНКАХ\"><fnst:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвОКВЭДОсн><ns1:СвОКВЭДДоп КодОКВЭД=\"52.61\" НаимОКВЭД=\"РОЗНИЧНАЯ ТОРГОВЛЯ ПО ЗАКАЗАМ\"><fnst:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвОКВЭДДоп><ns1:СвОКВЭДДоп КодОКВЭД=\"52.63\" НаимОКВЭД=\"ПРОЧАЯ РОЗНИЧНАЯ ТОРГОВЛЯ ВНЕ МАГАЗИНОВ\"><fnst:ГРНИПДата ГРНИП=\"305500910900012\" ДатаЗаписи=\"2005-04-19\"/></ns1:СвОКВЭДДоп></ns1:СвОКВЭД><ns1:СвЗапЕГРИП ГРНИП=\"305500910900012\" ДатаЗап=\"2005-04-19\" ИдЗап=\"305500910900012\"><ns1:ВидЗап КодСПВЗ=\"21211\" НаимВидЗап=\"(Р21001) РЕГИСТРАЦИЯ ФЛ В КАЧЕСТВЕ ИП\"/><ns1:СвРегОрг КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\"/><ns1:СведПредДок><ns1:НаимДок>ДОКУМЕНТ, УДОСТОВЕРЯЮЩИЙ ЛИЧНОСТЬ ГРАЖДАНИНА РОССИИ</ns1:НаимДок></ns1:СведПредДок><ns1:СведПредДок><ns1:НаимДок>ДОКУМЕНТ ОБ УПЛАТЕ ГОСУДАРСТВЕННОЙ ПОШЛИНЫ</ns1:НаимДок></ns1:СведПредДок><ns1:СведПредДок><ns1:НаимДок>Р21001 ЗАЯВЛЕНИЕ О РЕГИСТРАЦИИ ФЛ В КАЧЕСТВЕ ИП</ns1:НаимДок></ns1:СведПредДок><ns1:СвСвид ДатаВыдСвид=\"2005-04-19\" Номер=\"000285309\" Серия=\"50\"/></ns1:СвЗапЕГРИП><ns1:СвЗапЕГРИП ГРНИП=\"305500910900012\" ДатаЗап=\"2005-04-19\" ИдЗап=\"405500935601018\"><ns1:ВидЗап КодСПВЗ=\"23200\" НаимВидЗап=\"ВНЕСЕНИЕ СВЕДЕНИЙ ОБ УЧЕТЕ В НАЛОГОВОМ ОРГАНЕ\"/><ns1:СвРегОрг КодНО=\"5009\" НаимНО=\"ИНСПЕКЦИЯ ФЕДЕРАЛЬНОЙ НАЛОГОВОЙ СЛУЖБЫ ПО Г. ДОМОДЕДОВО МОСКОВСКОЙ ОБЛАСТИ.\"/></ns1:СвЗапЕГРИП></ns1:СвИП></ns1:FNSVipIPResponse>";
        Extractor ext = new Extractor();
        assertEquals(etalon, ext.parseTagFromByte(arr, "ns1:FNSVipIPResponse"));
    }


    @Test
    public void parsens42() throws IOException, TransformationException, NoSuchAlgorithmException {
        String input="<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\"><S:Body><ns2:SendRequestRequest><ns:SenderProvidedRequestData xmlns:ns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\" Id=\"SIGNED_BY_CONSUMER\"><ns:MessageID>000</ns:MessageID><ns2:MessagePrimaryContent><ns1:FNSVipULRequest xmlns:ns1=\"urn://x-artefacts-fns-vipul-tosmv-ru/311-14/4.0.6\" ИдДок=\"EC6418C9-D64C-4C6A-971F-00E1308C57FB\" НомерДела=\"0EA5C356-DB3C-4E33-8E5B-C312F4874909\"><ns1:ЗапросЮЛ><ns1:ИННЮЛ>5257045651</ns1:ИННЮЛ></ns1:ЗапросЮЛ></ns1:FNSVipULRequest></ns2:MessagePrimaryContent><ns:TestMessage/></ns:SenderProvidedRequestData><ns4:CallerInformationSystemSignature xmlns:ns4=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"><ds:Signature><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><ds:Reference URI=\"#SIGNED_BY_CONSUMER\"><ds:Transforms><ds:Transform xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:Transform xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Algorithm=\"urn://smev-gov-ru/xmldsig/transform\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><ds:DigestValue>000</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>000</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>000</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ns4:CallerInformationSystemSignature></ns2:SendRequestRequest></S:Body></S:Envelope>";
        Extractor ext = new Extractor();
        String result = "<ds:Signature><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><ds:Reference URI=\"#SIGNED_BY_CONSUMER\"><ds:Transforms><ds:Transform xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:Transform xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Algorithm=\"urn://smev-gov-ru/xmldsig/transform\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><ds:DigestValue>000</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>000</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>000</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature>";
        assertEquals(result, ext.parseTagFromByte(input.getBytes(), "ns4:CallerInformationSystemSignature"));

    }
    @Test
    public void extractAttribute() throws IOException {
        String filename="xml4test/res.xml";
        Path p = Paths.get(filename);
        byte[] arr = Files.readAllBytes(p);
        String etalon = "ИМЯ 55009109000004";
        Extractor ext = new Extractor();
        assertEquals(etalon, ext.extractAttribute(arr, "Имя"));


    }

    @Test
    public void extractCN() {
        Extractor ext = new Extractor();
        String input = "CN=ПАО «Ростелеком», C=RU, ST=78 г. Санкт-Петербург, L=Санкт-Петербург, O=ПАО «Ростелеком», OID.1.2.643.100.1=#120D31303237373030313938373637, OID.1.2.643.3.131.1.1=#120C303037373037303439333838, OID.1.2.840.113549.1.9.2=ТСМЭВ3_ЭМ\n";
        assertEquals("ПАО «Ростелеком»", ext.extractCN(input));
    }

    @Test
    public void extractNumber() {
        Extractor ext = new Extractor();
        String input="logs/binary/23.bin";
        assertEquals(23, ext.extractNumber(input));
    }
}