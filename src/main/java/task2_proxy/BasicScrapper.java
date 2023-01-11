package task2_proxy;

import java.sql.SQLException;

public interface BasicScrapper {
    String scrape() throws SQLException;
}
