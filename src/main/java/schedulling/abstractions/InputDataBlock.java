package schedulling.abstractions;

import java.io.Serializable;

public class InputDataBlock implements Serializable {
    public String Id;
    public String operator;
    public byte[] DataToWork;
}
