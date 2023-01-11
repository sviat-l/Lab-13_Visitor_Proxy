package task2_proxy;

import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.sql.SQLException;


public class CashedScrapper implements BasicScrapper {
    private Document document;
    private String result;
    private final String URL;
    private final DBConnection dbConnection;

    public CashedScrapper(String url) throws SQLException {
        this.URL = url;
        this.dbConnection = DBConnection.getInstance();
    }

    @Override
    public String scrape() throws SQLException {
        if (result!= null){
            return result;
        }

        String dbResult = dbConnection.dbGetQuery("SELECT data from rc_table WHERE url = '" + URL + "'");
        if (dbResult != null){
            result = dbResult;
        } else if (! URL.startsWith("https://www.newhomesource.com/")){
            dbConnection.dbPostQuery("INSERT INTO rc_table values ('"+ URL + "', 'Not valid')");
            result = "Not valid";
        } else{ try {
            document = Jsoup.connect(URL).userAgent("Mozilla").get();
            result = document.toString();
            dbConnection.dbPostQuery("INSERT INTO rc_table VALUES ('" + URL+"', '"+result.substring(0, 100)+"');");
            }  catch (IOException e) {
            throw new RuntimeException(e);
            }
        }
        return result;
    }
}
