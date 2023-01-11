package task2_proxy;

import lombok.Getter;

import java.sql.SQLException;

@Getter
public class Scrapper implements BasicScrapper{
    private final String URL;
    private BasicScrapper scrapper;

    public Scrapper(String url) {
        this.URL = url;
    }

    public String scrape() throws SQLException {
        if (scrapper == null){
            scrapper = new CashedScrapper(URL);
        }
        return scrapper.scrape();
    }
}
