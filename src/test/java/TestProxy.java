import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import task2_proxy.BasicScrapper;
import task2_proxy.CashedScrapper;
import task2_proxy.Scrapper;

import java.net.CacheRequest;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.instanceOf;

public class TestProxy {
    @Test
    public void TetsBasic(){
        Scrapper scrapper1 = new Scrapper("rightURLit.com");
        Assertions.assertEquals(scrapper1.getURL(), "rightURLit.com");
        Assertions.assertNull(scrapper1.getScrapper());

        try {
            scrapper1.scrape();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertNotNull(scrapper1.getScrapper());
    }

    @Test
    public void TestScrap() throws SQLException {

        Scrapper scrapper1 = new Scrapper("rightURLit.com");
        Assertions.assertEquals("Not valid", scrapper1.scrape());

        Scrapper scrapper2 = new Scrapper("https://www.newhomesource.ua");
        Assertions.assertEquals("Not valid", scrapper2.scrape());

        Scrapper scrapper3 = new Scrapper("https://www.newhomesource.com/communities/nv/las-vegas-area");
        Assertions.assertTrue(scrapper3.scrape().contains("lang=\"en\""));

    }

    @Test
    public void TestScrapDb() throws SQLException {
        Scrapper scrapper1 = new Scrapper("https://docs.github.com/en/actions/learn-github-actions/variables");
        Assertions.assertEquals("Not valid", scrapper1.scrape());

        Scrapper scrapper2 = new Scrapper("https://www.newhomesource.com/communities/tx/houston-area/texas-city");
        Assertions.assertTrue(scrapper2.scrape().contains("facebook"));

        Scrapper scrapper3 = new Scrapper("https://www.newhomesource.com/communities/tx/houston-area/texas-city");
        Assertions.assertTrue(scrapper3.scrape().contains("facebook"));
    }
}
