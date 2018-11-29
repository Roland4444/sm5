package util;

import java.io.*;

public class Injector implements Serializable {
    public String injectAttribute(String input, String AttributeName, String valueToInject){
        String result="";
        int pStart=input.indexOf(AttributeName);
        if (pStart==-1) return input;
        for (int i= 0; i<pStart; i++)
            result+=input.charAt(i);
        int offset=0;
        int offset2=0;
        while (input.charAt(pStart+offset)!='"'){
            result+=input.charAt(pStart+offset);
            offset++;
        }
        result+=input.charAt(pStart+offset);
        result+=valueToInject;
        offset++;
        while (input.charAt(pStart+offset+offset2)!='"') offset2++;
        for (int i= pStart+offset+offset2; i<=input.length()-1;i++)
            result+=input.charAt(i);
        return result;
    }

    public String injectTag(String input, String TagwithclosedEmbrace, String valueToInject){
        String result="";
        int pStart=input.indexOf(TagwithclosedEmbrace);
        if (pStart==-1) return input;
        for (int i= 0; i<pStart+TagwithclosedEmbrace.length(); i++)
            result+=input.charAt(i);
        int offset=0;
        while (input.charAt(pStart+offset)!='<') offset++;
        result+=valueToInject;
        for (int i= pStart+offset; i<=input.length()-1;i++)
            result+=input.charAt(i);
        return result;
    }

    public String injectTagDirect(String input, String TagRawName, String valueToInject){
        StringBuffer result=new StringBuffer();
        int pStart=input.indexOf(TagRawName);
        if (pStart==-1) return input;
        int shift=0;
        while (input.charAt(pStart+(shift++))!='>') {}
        result.append(input.substring(0, pStart+(shift)));
        result.append(valueToInject);
        shift--;
        while (input.charAt(pStart+(shift++))!='<') {}
        result.append(input.substring(pStart+(shift)-1, input.length()));
        return result.toString();
    }

    public void injectTagInFile(String input, String output, String TagwithclosedEmbrace, String valueToInject) throws IOException {
        FileWriter wr = new FileWriter(output);
        BufferedReader b = new BufferedReader(new FileReader(input));
        String readLine = "";
        String res="";
        while ((readLine = b.readLine()) != null)
            wr.write(injectTag(readLine, TagwithclosedEmbrace, valueToInject).replace("\n",""));
        wr.close();
    }
    @Deprecated
    public String inject(String input){
        String result="";
        int pStart=input.indexOf("MessageID>");
        if (pStart==-1) return input;
        for (int i= 0; i<pStart+9; i++)
            result+=input.charAt(i);
        int offset=0;
        while (input.charAt(pStart+offset)!='<') offset++;
        result+=(new timeBasedUUID().generate());
        for (int i= pStart+offset; i<=input.length()-1;i++)
            result+=input.charAt(i);
        return result;
    }
    @Deprecated
    public void inject(String input, String output) throws IOException {
        FileWriter wr = new FileWriter(output);
        BufferedReader b = new BufferedReader(new FileReader(input));
        String readLine = "";
        String res="";
        while ((readLine = b.readLine()) != null)
            wr.write(inject(readLine).replace("\n",""));
        wr.close();
    };
    @Deprecated
    public String getTagValue(String xml, String tagName){
        return xml.split("<"+tagName+">")[1].split("</"+tagName+">")[0];
    }

    public String cert(String certFile) throws IOException {
        String res="";
        File f = new File(certFile);
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readLine = "";
        String input="";
        StringBuffer strBuffer = new StringBuffer();
        while ((readLine = b.readLine()) != null)
            if (readLine.indexOf("CERTIFICATE")<0) strBuffer.append(readLine);
        return strBuffer.toString();
    }

    public String burnTagWithData(String input, String TagnameToBurnWithoutEmbraces) throws IOException {
        boolean founded=false;
       // String input0=input.replace('\n',' ');
        int startpos=input.indexOf(TagnameToBurnWithoutEmbraces);
        if (startpos <0) return input;
        int stoppos=input.indexOf(TagnameToBurnWithoutEmbraces,startpos+1);
        while (input.charAt(startpos)!='<') startpos--;
        while (input.charAt(stoppos)!='>') stoppos++;
        stoppos++;
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(input.substring(0, startpos));
        strBuffer.append(input.substring(stoppos, input.length()));
        return strBuffer.toString();
    }


    public String flushTagData(String input, String TagnameToBurnWithoutEmbraces) throws IOException {
        boolean founded=false;
        // String input0=input.replace('\n',' ');
        int startpos=input.indexOf(TagnameToBurnWithoutEmbraces);
        if (startpos <0) return input;
        int stoppos=input.indexOf(TagnameToBurnWithoutEmbraces,startpos+1);
        while (input.charAt(startpos)!='>') startpos++;
        startpos++;
        while (input.charAt(stoppos)!='<') stoppos--;
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(input.substring(0, startpos));
        strBuffer.append(input.substring(stoppos, input.length()));
        return strBuffer.toString();
    }


}
