package com.utils;

import static io.restassured.RestAssured.*;

import io.restassured.http.Header;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class APIUtils {
    public static Properties prop;
    public static String baseurl;
    Response response;

    public Response getRequest(String url, Header header){
        response = given().header(header).contentType("application/json").
                when().get(url);
        return response;
    }
    public Response getRequest(String url, Header header, HashMap param){
        response = given().params(param).header(header).contentType("application/json").
                when().get(url);
        return response;
    }
    public Properties loadProperties() throws IOException {
        prop = new Properties();
        FileInputStream fileInputStream =
                new FileInputStream(System.getProperty("user.dir") + "/configurator.properties");
        prop.load(fileInputStream);
        baseurl = prop.getProperty("baseurl");
        return prop;
    }

}
