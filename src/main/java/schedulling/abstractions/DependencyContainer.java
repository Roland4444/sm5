package schedulling.abstractions;

import DB.Executor;
import com.sun.org.apache.xml.internal.security.exceptions.AlgorithmAlreadyRegisteredException;
import com.sun.org.apache.xml.internal.security.transforms.InvalidTransformException;
import logging.MyLogger;
import readfile.Readfile;
import schedulling.ResultsProcessors.egrResult;
import schedulling.ResultsProcessors.gisResult;
import schedulling.ResultsProcessors.passResult;
import schedulling.abstractions.OutDataPerfomImpl.PerfomReceivedData;
import schedulling.abstractions.OutDataPerform.tableResultProcessors;
import schedulling.gettingDataImplem.DataSource;
import schedulling.gettingDataImplem.getData;
import standart.*;
import transport.SAAJ;
import transport.Transport;
import util.*;

import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;

public class DependencyContainer implements Serializable {
    public Readfile r;
    public String pathtoLog="";
    public boolean useExternalSigner;
    public HashMap<String, String> ignored ;
    public TempDataContainer temp;
    public String IdBuffer="";
    public MapProcessor tableProcessor;
    public Sign sign;
    public SignerXML xmlsign;
    public PersonalSign personalSign ;
    public OutputStream os;
    public StreamResult sr;
    public Extractor ext;
    public Executor executor;
    public Injector inj;
    public Transport transport;
    public timeBasedUUID uuidgen;

    public gis gis;
    public egr egr;
    public pass pass;
    public standart.inn inn;
    public standart.esia esia;
    public ebs ebs;

    public InfoAllRequests dbReqs;
    public InputDataContainer inputDataFlow;
    public MyLogger logger;
    public final String sucesfullqueed = "queued";
    public final String errorqueed = "error";
    public final String stopperGetResponce = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:GetResponseResponse xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1\" xmlns:ns3=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/faults/1.1\"/></soap:Body></soap:Envelope>";

    public final String gis_  ="gis";
    public final String egr_  ="egr";
    public final String pass_ ="pass";
    public final String inn_ ="psinn";
    public final String inn2_ ="inn";
    public final String bank_ = "bankost";
    public final String gisreg_ = "gisreg";
    public final String esia_ = "esia";
    public final String ebs_ = "ebs";

    public boolean SupressConsole;
    public Identifier Idgen;
    public DataSource datasource;
    public getData dataImporter;
    public tableResultProcessors tableResultProcessors;
    public schedulling.ResultsProcessors.gisResult gisResult;
    public egrResult egrResult;
    public passResult passResult;
    public PerfomReceivedData performReceiveddata;
    public Freezer freezer;
    private void initTableProcesor(){
        this.tableProcessor=new MapProcessor();
        tableProcessor.OperatorMap.put(gis_, this.gis);
        tableProcessor.OperatorMap.put(egr_, this.egr);
        tableProcessor.OperatorMap.put(pass_, this.pass);
        tableProcessor.OperatorMap.put(inn_, this.inn);
        tableProcessor.OperatorMap.put(inn2_, this.inn);
        tableProcessor.OperatorMap.put(bank_, this.inn);
        tableProcessor.OperatorMap.put(gisreg_, this.egr);
        tableProcessor.OperatorMap.put(esia_, esia);
        tableProcessor.OperatorMap.put(ebs_, ebs);

    };

    private void initDataSource(){
        this.datasource = new DataSource();
        this.datasource.Source.put(gis_, "gis_files");
        this.datasource.Source.put(egr_, "fns_files");
        this.datasource.Source.put(pass_, "fms_zap");


    }

    private void generateOutputResultProcessors(){
        this.tableResultProcessors = new tableResultProcessors();
        this.tableResultProcessors.TableResultProcessors.put(gis_, this.gisResult);
        this.tableResultProcessors.TableResultProcessors.put(egr_, this.egrResult);
        this.tableResultProcessors.TableResultProcessors.put(pass_, this.passResult);
    }

    private void init() throws ClassNotFoundException, SignatureProcessorException, InvalidTransformException, AlgorithmAlreadyRegisteredException, SQLException, IOException {
        initDataSource();
        this.r = new Readfile("sqlset");
        pathtoLog = r.binaryLogPath();
        this.ignored= new HashMap<>();
        this.temp= new TempDataContainer();
        this.freezer =  new Freezer();
        this.Idgen = new Identifier();
        this.SupressConsole=false;
        this.logger =  new MyLogger(pathtoLog);
        this.logger.setFreezer(this.freezer);
        this.dbReqs = new InfoAllRequests();
        this.inputDataFlow = new InputDataContainer();
        this.sign = new Sign();
        if (this.useExternalSigner==false)
            this.xmlsign = new SignerXML();
        this.personalSign  = new PersonalSign();
        this.os = new ByteArrayOutputStream();
        this.sr = new StreamResult(os);
        this.ext=new Extractor();
        this.executor=new Executor(r.read(), true);
        this.uuidgen=new timeBasedUUID();
        this.inj = new Injector();

        this.gis = new gis(this.sr, this.xmlsign, this.personalSign, this.sign);
        this.gis.setLink(this.temp);

        this.egr = new egr(this.sr, this.xmlsign, this.personalSign, this.sign);
        this.egr.setLink(this.temp);

        this.pass = new pass(this.sr, this.xmlsign, this.personalSign, this.sign);
        this.pass.setLink(this.temp);

        this.inn =  new inn(this.sr, this.xmlsign, this.personalSign, this.sign);
        this.inn.setLink(this.temp);

        this.esia = new esia(this.sr, this.xmlsign, this.personalSign, this.sign);
        this.esia.setLink(this.temp);

        this.ebs = new ebs(this.sr, this.xmlsign, this.personalSign, this.sign);
        this.ebs.setLink(this.temp);

        inn.setTransport(this.transport);
        gis.setTransport(this.transport);
        pass.setTransport(this.transport);
        egr.setTransport(this.transport);
        esia.setTransport(this.transport);
        ebs.setTransport(this.transport);

        initTableProcesor();

        this.dataImporter = new getData(this.executor, this.datasource, this.Idgen, this.inputDataFlow, this.tableProcessor);
        this.dataImporter.setInjector(this.inj);
        this.dataImporter.setIgnored(this.ignored);


        this.gisResult = new gisResult(this.executor, this.Idgen);
        this.gisResult.setExtractor(this.ext);
        this.egrResult = new egrResult(this.executor, this.Idgen);
        this.egrResult.setExtractor(this.ext);
        this.passResult = new passResult(this.executor, this.Idgen);
        this.passResult.setExtractor(this.ext);

        generateOutputResultProcessors();
        this.performReceiveddata = new PerfomReceivedData(this.tableResultProcessors, this.dbReqs, this.inputDataFlow);
        this.performReceiveddata = new PerfomReceivedData(this.tableResultProcessors, this.dbReqs, this.inputDataFlow);

    }


    public DependencyContainer() throws ClassNotFoundException, SignatureProcessorException, SQLException, IOException, InvalidTransformException, AlgorithmAlreadyRegisteredException {
        this.useExternalSigner = false;
        this.transport = new SAAJ("http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl");
        init();
    }

    public DependencyContainer(String addres) throws ClassNotFoundException, SignatureProcessorException, InvalidTransformException, AlgorithmAlreadyRegisteredException, SQLException, IOException {
        this.useExternalSigner = false;
        this.transport = new SAAJ(addres);
        init();
    }

    public DependencyContainer(SignerXML signer) throws ClassNotFoundException, SignatureProcessorException, InvalidTransformException, AlgorithmAlreadyRegisteredException, SQLException, IOException {
        this.transport = new SAAJ("http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl");
        this.useExternalSigner=true;
        this.xmlsign = signer;
        init();
    }


}
