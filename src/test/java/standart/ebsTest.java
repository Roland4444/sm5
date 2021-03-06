package standart;

import org.apache.xml.security.exceptions.AlgorithmAlreadyRegisteredException;
import org.apache.xml.security.transforms.InvalidTransformException;
import org.junit.Test;
import schedulling.Scheduller;
import schedulling.abstractions.DependencyContainer;
import util.Injector;
import util.Sign;
import util.SignatureProcessorException;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;


public class ebsTest {
    DependencyContainer deps = new DependencyContainer();
    Scheduller sch = new Scheduller(deps);
    Sign signer = new Sign();
    public boolean supress=false;
    Injector inj = new Injector();

    public ebsTest() throws AlgorithmAlreadyRegisteredException, InvalidTransformException, IOException, SQLException, SignatureProcessorException, ClassNotFoundException {
    }

    @Test
    public void sendInitialRequestRequestGis() throws Exception {
            String data = "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\">\n" +
                    "   <S:Body>\n" +
                    "      <ns2:SendRequestRequest xmlns:ns3=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/faults/1.1\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\">\n" +
                    "         <ns:SenderProvidedRequestData Id=\"SIGNED_BY_CONSUMER\" xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\">\t<ns:MessageID>6fefa07c-5e7f-11e4-a9ff-d4c9eff07b77</ns:MessageID><ns2:MessagePrimaryContent><ns1:BreachRequest Id=\"PERSONAL_SIGNATURE\" xmlns:ns1=\"urn://x-artefacts-gibdd-gov-ru/breach/root/1.0\" xmlns:ns2=\"urn://x-artefacts-gibdd-gov-ru/breach/commons/1.0\" xmlns:ns3=\"urn://x-artefacts-smev-gov-ru/supplementary/commons/1.0.1\"> <ns1:RequestedInformation> <ns2:RegPointNum>Т785ЕС57</ns2:RegPointNum> </ns1:RequestedInformation> <ns1:Governance> <ns2:Name>ГИБДД РФ</ns2:Name> <ns2:Code>GIBDD</ns2:Code> <ns2:OfficialPerson> <ns3:FamilyName>Загурский</ns3:FamilyName> <ns3:FirstName>Андрей</ns3:FirstName> <ns3:Patronymic>Петрович</ns3:Patronymic> </ns2:OfficialPerson></ns1:Governance> </ns1:BreachRequest> </ns2:MessagePrimaryContent>\t<ns2:AttachmentHeaderList>\t<ns2:AttachmentHeader>\t<ns2:contentId>attach5MB.jpg</ns2:contentId>\t<ns2:MimeType>image/jpeg</ns2:MimeType>\t<ns2:SignaturePKCS7>MIICyAYJKoZIhvcNAQcCoIICuTCCArUCAQExDDAKBgYqhQMCAgkFADALBgkqhkiG9w0BBwGgggGLMIIBhzCCATagAwIBAgIFAMFdkFQwCAYGKoUDAgIDMC0xEDAOBgNVBAsTB1NZU1RFTTExDDAKBgNVBAoTA09WMjELMAkGA1UEBhMCUlUwHhcNMTQwMjIxMTMzNDMyWhcNMTUwMjIxMTMzNDMyWjAtMRAwDgYDVQQLEwdTWVNURU0xMQwwCgYDVQQKEwNPVjIxCzAJBgNVBAYTAlJVMGMwHAYGKoUDAgITMBIGByqFAwICJAAGByqFAwICHgEDQwAEQLjcuMDezt3MrljIr+54Cy64Gvgy8uuGgTpjvlrDAkiGdTL/m9EDDJvMARnMjzSb1JTxovUWfTV8j2bns+KZXNyjOzA5MA4GA1UdDwEB/wQEAwID6DATBgNVHSUEDDAKBggrBgEFBQcDAjASBgNVHRMBAf8ECDAGAQH/AgEFMAgGBiqFAwICAwNBAMVRmhKGKFtRbBlGLl++KtOAvm96C5wnj+6L/wMYpw7Gd7WBM21Zqh9wu+3eZotglDsJMEYbKgiLRprSxKz+DHsxggEEMIIBAAIBATA2MC0xEDAOBgNVBAsTB1NZU1RFTTExDDAKBgNVBAoTA09WMjELMAkGA1UEBhMCUlUCBQDBXZBUMAoGBiqFAwICCQUAoGkwGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEHATAcBgkqhkiG9w0BCQUxDxcNMTQxMDI4MDg1MDE2WjAvBgkqhkiG9w0BCQQxIgQgmZUE4Hn2Dtr05pue921ZxU60Ia3toLVEQfIs24PTdT8wCgYGKoUDAgITBQAEQBwEN+RenIpvL6lZHzzsPj5H4xgqZjs330i1JjAhjcACqQcCyt3vorTEX9/gfs16s9Lt9XxNj9Y88NvvLIrfFaw=</ns2:SignaturePKCS7>\t</ns2:AttachmentHeader>\t</ns2:AttachmentHeaderList></ns:SenderProvidedRequestData>\t<AttachmentContentList>\t<AttachmentContent>\t<Id>attach5MB.jpg</Id>\t<Content><xop:Include href=\"cid:attach5MB.jpg\" xmlns:xop=\"http://www.w3.org/2004/08/xop/include\"/></Content>\t</AttachmentContent>\t</AttachmentContentList>\n" +
                    "         <ns2:CallerInformationSystemSignature><ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"><ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411\"/><ds:Reference URI=\"#SIGNED_BY_CONSUMER\"><ds:Transforms><ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:Transform Algorithm=\"urn://smev-gov-ru/xmldsig/transform\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#gostr3411\"/><ds:DigestValue>jTP0hndqPRXO9O+5euAtKsysn83a9+gVkvUpojGTL84=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>i3PzufK7XEmCtElirdXqtYh7/CdZCIIgowfj+TbBHPw/Mk+1YA+/LSdIM3MO2tcH8ZeFA5e04VSz0deGcnVAaA==</ds:SignatureValue><ds:KeyInfo><ds:X509Data><ds:X509Certificate>MIIBhzCCATagAwIBAgIFAMFdkFQwCAYGKoUDAgIDMC0xEDAOBgNVBAsTB1NZU1RFTTExDDAKBgNVBAoTA09WMjELMAkGA1UEBhMCUlUwHhcNMTQwMjIxMTMzNDMyWhcNMTUwMjIxMTMzNDMyWjAtMRAwDgYDVQQLEwdTWVNURU0xMQwwCgYDVQQKEwNPVjIxCzAJBgNVBAYTAlJVMGMwHAYGKoUDAgITMBIGByqFAwICJAAGByqFAwICHgEDQwAEQLjcuMDezt3MrljIr+54Cy64Gvgy8uuGgTpjvlrDAkiGdTL/m9EDDJvMARnMjzSb1JTxovUWfTV8j2bns+KZXNyjOzA5MA4GA1UdDwEB/wQEAwID6DATBgNVHSUEDDAKBggrBgEFBQcDAjASBgNVHRMBAf8ECDAGAQH/AgEFMAgGBiqFAwICAwNBAMVRmhKGKFtRbBlGLl++KtOAvm96C5wnj+6L/wMYpw7Gd7WBM21Zqh9wu+3eZotglDsJMEYbKgiLRprSxKz+DHs=</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></ns2:CallerInformationSystemSignature>\n" +
                    "      </ns2:SendRequestRequest>\n" +
                    "   </S:Body>\n" +
                    "</S:Envelope>";
            deps.ebs.setinput(data);
            assertNotEquals(null, deps.ebs.GetSoap());
            assertNotEquals(null, deps.ebs.SignedSoap());
            String response = new String(deps.ebs.SendSoapSigned());
            System.out.println(response);
            if (response.indexOf("fault")>0) {
                System.out.println("FAULT");
            }
    }
}
