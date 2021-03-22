package com.smartsoft.converter.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * TODO: Добавить свое исключение
 *       Пробросить его дальше в catch
 *
 * */

@Component
public class HttpRequestImpl implements HttpRequest {

    private Logger LOGGER = LoggerFactory.getLogger(HttpRequestImpl.class);
    private static final String CHARSET_NAME = "Cp1251";
    private static String URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";
    private URL url;
    private HttpURLConnection connection;
    private String xmlResponse;

    @Override
    public String request() throws IOException {

        LocalDate todayDate = LocalDate.now();
        String formattedTodayDate = todayDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        URL += formattedTodayDate;

        url = new URL(URL);
        connection = (HttpURLConnection) url.openConnection();

        LOGGER.info("Parse from: " + URL);
        LOGGER.info("Response Code : " + connection.getResponseCode());

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), CHARSET_NAME));) {
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            xmlResponse = response.toString();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            // пробросить исключение дальше
        }
        return xmlResponse;
    }
}
