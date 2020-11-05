package com.compucompare.compucompare.network;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.compucompare.compucompare.computerType.Laptop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BestBuySource implements LaptopSource
{
    public static final String LAPTOP_LIST_URL = "https://www.bestbuy.com/site/laptop-computers/all-laptops/pcmcat138500050001.c?id=pcmcat138500050001";

    @Override
    public Set<Laptop> getAllLaptops()
    {
        Set<Laptop> laptops = new HashSet<>();
        try
        {
            Document resultPage = Jsoup.connect(LAPTOP_LIST_URL).get();
            Elements laptopElements = resultPage.getElementsByClass("sku-item");
            for (Element element : laptopElements)
            {
                Element skuHeader = element.selectFirst(".sku-header");
                if (skuHeader == null)
                {
                    continue;
                }
                Element link = skuHeader.selectFirst("a");
                if (link == null)
                {
                    continue;
                }
                String laptopPageUrl = link.attr("abs:href");
                Document laptopPage = Jsoup.connect(laptopPageUrl).get();
                Element specificationTable = laptopPage.selectFirst("div.specs-table");
                Elements specifications = specificationTable.getElementsByTag("li");
                for (Element specification : specifications)
                {
                    String key = specification.selectFirst(".row-title").text();
                    String val = specification.selectFirst(".row-value").text();
                    System.out.printf("%-50s:%s\n", key, val);
                }
                System.out.println();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error Fetching Best Buy Laptops!");
        }
        return laptops;
    }
}
