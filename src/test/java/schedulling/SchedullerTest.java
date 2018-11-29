package schedulling;

import org.apache.xml.security.exceptions.AlgorithmAlreadyRegisteredException;
import org.apache.xml.security.transforms.InvalidTransformException;
import org.junit.Test;

;
import schedulling.abstractions.DependencyContainer;
import schedulling.abstractions.InputDataBlock;
import util.SignatureProcessorException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class SchedullerTest {
    DependencyContainer deps = new DependencyContainer();
    Scheduller sch = new Scheduller(deps);

    public SchedullerTest() throws SQLException, ClassNotFoundException, SignatureProcessorException, InvalidTransformException, AlgorithmAlreadyRegisteredException, IOException {
    }

    @Test
    public void setTasker() throws SQLException {
        assertNotEquals(null, sch);
        assertNotEquals(null, sch.tasker);
    }





}