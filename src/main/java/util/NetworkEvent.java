package util;

import java.io.Serializable;
import java.util.Date;

public class NetworkEvent implements Serializable {
    public String Name;
//    public Enumeration<InetAddress> IP;
    public Date date;
    public boolean isUp;
    public int status;

    public NetworkEvent(String Name, Date date, boolean isUp){//Enumeration<InetAddress> IP,
        this.Name=Name;
      //  this.IP=IP;
        this.date=date;
        this.isUp=isUp;

    }

}
