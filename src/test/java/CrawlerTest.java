import org.junit.Test;
import com.brian.Controller.MessageBot.Wikipedia.Connectus;
import static org.junit.Assert.assertEquals;
import java.io.IOException;

public class CrawlerTest {

    @Test
    public void testCrawlerConnection() throws IOException{
        Connectus connectus = new Connectus();
        System.out.println("Testing Connection To Wikipedia");
        boolean expectedResult = true;
        boolean result = connectus.getConnection();
        assertEquals(expectedResult,result);

    }

}
