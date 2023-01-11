package task2_proxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.sql.SQLException;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        String URL = "https://docs.github.com/en/actions/learn-github-actions/variables";

        String validURL = "https://www.newhomesource.com/communities/ny/new-york-area";

        Scrapper scrapper = new Scrapper(validURL);
        System.out.println(scrapper.getURL());
        scrapper.scrape();
    }
}
