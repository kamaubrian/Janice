import com.brian.Model.Base;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class BaseTest {


    @Test
    public void testDatabaseConnection() throws SQLException{
        Base instance = new BaseImpl();
        boolean expectedResult = false;
        boolean result = instance.getConnection();
        assertEquals(expectedResult,result);
    }




    public class BaseImpl extends Base{

    }
}
