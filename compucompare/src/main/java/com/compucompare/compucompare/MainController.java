package com.compucompare.compucompare;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MainController
{
    @RequestMapping("/")
    String hello(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return String.format("Hello %s!", name);
    }

    /**
     * Added by Markus.
     * @param str A String parameter provided in the request.
     * @return Details about the String in JSON format.
     */
    @RequestMapping(value = "/stringinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    String stringInfo(@RequestParam(value = "str", defaultValue = "") String str)
    {
        JSONObject stringInfo = new JSONObject();
        try
        {
            stringInfo.put("data", str);
            stringInfo.put("length", str.length());
            stringInfo.put("firstChar", (str.isEmpty() ? "" : str.charAt(0)));
            stringInfo.put("lastChar", (str.isEmpty() ? "" : str.charAt(str.length() - 1)));
        }
        catch (JSONException e)
        {
            return "Error Forming JSON";
        }
        return stringInfo.toString();
    }

    /**
     * Added by Natalie.
     * @return Details about listed computers on the Best Buy Website.
     */
    @RequestMapping("/jsoupLibrary")
    String pullComputerInfo() throws IOException {
        Document doc = Jsoup.connect("https://www.bestbuy.com/site/laptop-computers/all-laptops/pcmcat138500050001.c?id=pcmcat138500050001").get();
        Elements products = doc.select("img.product-image");
        String[] strings = new String[products.size() + 1];
        int counter = 0;
        strings[counter++] = "Number of computers displayed on page: " + products.size();
        for (Element product: products)
            strings[counter++] = "<br/>Info: " + product.attr("alt");
        String fullString = "";
        for(String string: strings)
            fullString += string;
        return fullString;
    }
}
