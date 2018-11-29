package standart;

import org.xml.sax.SAXException;
import transport.Transport;
import util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public abstract class Standart implements Serializable {
    public Standart() {
        //this.transport=transport;//new Transport("http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl");
    }
    public boolean bypassID=false;
    protected Sign MainSign;

    public void setTransport(Transport set) {
        this.transport = set;
    }
    public boolean SupressConsole=false;
    /*http://smev3-n0.test.gosuslugi.ru:7500/ws?wsdl*/
    protected Transport transport;
    protected SignerXML signer;
    private StreamResult out;
    protected Injector inj = new Injector();
    protected Extractor ext = new Extractor();
    protected timeBasedUUID gen = new timeBasedUUID();
    public byte[] InfoToRequest;

    public abstract boolean check(byte[] input) throws IOException;

    public abstract void setinput(String input) throws IOException;

    public void setSupressConsole(boolean input){
        this.SupressConsole=input;
    }

    public abstract byte[] GetSoap();

    public abstract byte[] SignedSoap() throws ClassNotFoundException, SignatureProcessorException, IOException, CertificateException, NoSuchAlgorithmException, TransformerException, ParserConfigurationException, UnrecoverableEntryException, NoSuchProviderException, SAXException, KeyStoreException;
    //  public abstract byte[] SendRequestRequest();

    public byte[] SendSoapSigned() throws Exception {
        System.out.println("In Standart SendSoaqpSigned");
        InputStream in = new ByteArrayInputStream(SignedSoap());
        StreamSource input = new StreamSource(in);
        return this.transport.send(input, SupressConsole);
    }
    public abstract byte[]  GetResponceFilteredCompiled() throws Exception;
    public abstract byte[]  GetResponceRequestCompiled() throws Exception;
    public abstract byte[]  Ack(String Id) throws Exception;
    public byte[] GetResReq() throws Exception {
        String prepared = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ns1=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "<ns:GetResponseRequest>\n" +
                "<ns2:MessageTypeSelector xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\" xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" Id=\"SIGNED_BY_CALLER\"><ns2:Timestamp>2014-02-11T17:10:03.616+04:00</ns2:Timestamp></ns2:MessageTypeSelector>\n" +
                "<!--Optional:-->\n" +
                "<ns:CallerInformationSystemSignature></ns:CallerInformationSystemSignature>\n" +
                "</ns:GetResponseRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        //    String prepared=inj.injectAttribute(data, "Id", "SIGNED_BY_CONSUMER");
        this.setinput(prepared);
        InputStream in = new ByteArrayInputStream(signer.signcallernsbycaller(MainSign, GetSoap()));
        StreamSource input = new StreamSource(in);
        return this.transport.send(input, SupressConsole);
    }

    public abstract String getName();








}
