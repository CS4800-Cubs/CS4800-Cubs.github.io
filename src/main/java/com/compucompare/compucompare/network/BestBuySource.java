package com.compucompare.compucompare.network;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.compucompare.compucompare.database.LaptopRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BestBuySource extends LaptopSource
{
    public static final String LAPTOP_LIST_URL = "https://www.bestbuy.com/site/laptop-computers/all-laptops/pcmcat138500050001.c?id=pcmcat138500050001";

    @Autowired
    LaptopRepository laptopRepository;

    @Override
    public Set<LaptopListing> fetchLaptopListings()
    {
        Set<LaptopListing> listings = new HashSet<>();
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
                listings.add(fetchLaptopListing(laptopPageUrl));
            }
        }
        catch (IOException e)
        {
            System.out.println("Error Fetching Best Buy Laptops!");
        }
        return listings;
    }

    @Override
    public boolean validUrl(String laptopPageUrl)
    {
        return false;
    }

    @Override
    public LaptopListing fetchLaptopListing(String laptopPageUrl) throws IOException
    {
        LaptopListing listing = new LaptopListing();
        Set<StorageListing> storageListings = new HashSet<>();
        Set<NetworkListing> networkListings = new HashSet<>();
        Document laptopPage = Jsoup.connect(laptopPageUrl).get();
        Elements specificationTables = laptopPage.select("div.specs-table");
        for (Element specificationTable : specificationTables)
        {
            Elements specifications = specificationTable.getElementsByTag("li");
            for (Element specification : specifications)
            {
                String key = specification.selectFirst(".row-title").text();
                String val = specification.selectFirst(".row-value").text();
                switch (key)
                {
                    case "Brand":
                        listing.brand = val;
                        break;
                    case "Model Number":
                        listing.model = val;
                        break;
                    case "Screen Size":
                        String[] split = val.split(" ");
                        try
                        {
                            if (split.length > 0)
                            {
                                listing.displaySize = Double.parseDouble(split[0]);
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            
                        }
                        break;
                    case "Processor Model":
                        break;
                }
                System.out.printf("%-50s:%s\n", key, val);
            }
            System.out.println();
        }
        listing.storageListings = storageListings;
        listing.networkListings = networkListings;
        return listing;
    }
}