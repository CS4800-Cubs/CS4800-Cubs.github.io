package com.compucompare.compucompare.network;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.compucompare.compucompare.database.ComputerRepository;

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
    ComputerRepository laptopRepository;

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
        listing.pageUrl = laptopPageUrl;
        Set<StorageListing> storageListings = new HashSet<>();
        Set<NetworkListing> networkListings = new HashSet<>();
        Document laptopPage = Jsoup.connect(laptopPageUrl).get();
        Element thumbnailImage = laptopPage.selectFirst("img.primary-image");
        listing.thumbnailUrl = thumbnailImage.attr("src");
        Elements specificationTables = laptopPage.select("div.specs-table");
        String cpuModelNumber = "", cpuModelLine = "";
        StorageListing solidStateStorage = null;
        NetworkListing ethernetInterface = null;
        NetworkListing wirelessInterface = null;
        for (Element specificationTable : specificationTables)
        {
            Elements specifications = specificationTable.getElementsByTag("li");
            for (Element specification : specifications)
            {
                String key = specification.selectFirst(".row-title").text();
                String val = specification.selectFirst(".row-value").text();
                if (key.equals("Brand"))
                {
                    listing.brand = val;
                }
                else if (key.equals("Model Number"))
                {
                    listing.model = val;
                }
                else if (key.equals("Product Name"))
                {
                    listing.displayName = val;
                }
                else if (key.equals("Processor Brand"))
                {
                    listing.cpuBrand = val;
                }
                else if (key.equals("Processor Model"))
                {
                    cpuModelLine = val;
                }
                else if (key.equals("Processor Model Number"))
                {
                    String[] split = val.split(" ");
                    if (split.length != 0)
                    {
                        cpuModelNumber = split[split.length - 1];
                    }
                }
                else if (key.equals("Graphics"))
                {
                    String[] name = val.replaceAll("(\\((TM|R)\\)|Graphics|with|Design|,)+", "")
                                       .replaceAll("\\s+", " ")
                                       .trim()
                                       .split("\\s+", 2);
                    if (name.length == 2)
                    {
                        listing.gpuBrand = name[0];
                        listing.gpuModel = name[1];
                    }
                }
                else if (key.equals("System Memory (RAM)"))
                {
                    try
                    {
                        listing.ramAmount = Double.parseDouble(val.split(" ", 2)[0]);
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e)
                    {
                        System.out.println("Error Parsing RAM Amount");
                    }
                }
                else if (key.equals("System Memory RAM Speed"))
                {
                    try
                    {
                        listing.ramSpeed = Integer.parseInt(val.split(" ", 2)[0]);
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e)
                    {
                        System.out.println("Error Parsing RAM Speed");
                    }
                }
                else if (key.equals("Screen Size"))
                {
                    try
                    {
                        listing.displaySize = Double.parseDouble(val.split(" ", 2)[0]);
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e)
                    {
                        System.out.println("Error Parsing Screen Size");
                    }
                }
                else if (key.equals("Screen Resolution"))
                {
                    String[] resStr = val.split(" ", 4);
                    try
                    {
                        listing.resX = Integer.parseInt(resStr[0]);
                        listing.resY = Integer.parseInt(resStr[2]);
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e)
                    {
                        System.out.println("Error Parsing Resolution");
                    }
                }
                else if (key.equals("Screen Resolution"))
                {
                    String[] resStr = val.split(" ", 4);
                    try
                    {
                        listing.resX = Integer.parseInt(resStr[0]);
                        listing.resY = Integer.parseInt(resStr[2]);
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e)
                    {
                        System.out.println("Error Parsing Resolution");
                    }
                }
                else if (key.equals("Storage State Drive Capacity"))
                {
                    if (solidStateStorage == null)
                    {
                        solidStateStorage = new StorageListing();
                    }
                    solidStateStorage.isSolidState = true;
                    try
                    {
                        listing.displaySize = Integer.parseInt(val.split(" ", 2)[0]);
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e)
                    {
                        System.out.println("Error Parsing Solid State Storage");
                    }
                }
                else if (key.equals("Solid State Drive Interface"))
                {
                    if (solidStateStorage == null)
                    {
                        solidStateStorage = new StorageListing();
                    }
                    if (key.equalsIgnoreCase("NVMe"))
                    {
                        solidStateStorage.isNvme = true;
                    }
                }
                else if (key.equals("Battery Life"))
                {
                    try
                    {
                        listing.expectedBatteryLife = Double.parseDouble(val.split(" ", 2)[0]);
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e)
                    {
                        System.out.println("Error Parsing Battery Life");
                    }
                }
                else if (key.equals("Wireless Networking"))
                {
                    if (wirelessInterface == null)
                    {
                        wirelessInterface = new NetworkListing();
                        wirelessInterface.isWireless = true;
                    }
                    wirelessInterface.standards.add(key);
                }
                else if (key.equals("Bluetooth Enabled"))
                {
                    if (wirelessInterface == null)
                    {
                        wirelessInterface = new NetworkListing();
                        wirelessInterface.isWireless = true;
                    }
                    if (key.equalsIgnoreCase("Yes"))
                    {
                        wirelessInterface.standards.add("Bluetooth");
                    }
                }
                else if (key.equals("Ethernet Card"))
                {
                    try
                    {
                        String[] split = val.split("/");
                        int speed = Integer.parseInt(split[split.length - 1]);
                        if (ethernetInterface == null)
                        {
                            ethernetInterface = new NetworkListing();
                            ethernetInterface.isWireless = false;
                            ethernetInterface.maxSpeed = speed;
                        }
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e)
                    {
                        System.out.println("Error Parsing Ethernet Speed");
                    }
                }
                System.out.printf("%-50s:%s\n", key, val);
            }
            // Identify Processor Line
            if (cpuModelLine.contains("Celeron"))
            {
                cpuModelLine = "Celeron";
            }
            else if (cpuModelLine.contains("Core"))
            {
                cpuModelLine = "Core";
            }
            else if (cpuModelLine.contains("Pentium"))
            {
                cpuModelLine = "Pentium";
            }
            else if (cpuModelLine.contains("Ryzen 3"))
            {
                cpuModelLine = "Ryzen 3";
            }
            else if (cpuModelLine.contains("Ryzen 5"))
            {
                cpuModelLine = "Ryzen 5";
            }
            else if (cpuModelLine.contains("Ryzen 7"))
            {
                cpuModelLine = "Ryzen 7";
            }
            else if (cpuModelLine.contains("Xeon"))
            {
                cpuModelLine = "Xeon";
            }
            listing.cpuModel = cpuModelLine + " " + cpuModelNumber;
            System.out.println();
        }
        if (solidStateStorage != null)
        {
            storageListings.add(solidStateStorage);
        }
        if (wirelessInterface != null)
        {
            networkListings.add(wirelessInterface);
        }
        if (ethernetInterface != null)
        {
            networkListings.add(ethernetInterface);
        }
        listing.storageListings = storageListings;
        listing.networkListings = networkListings;
        return listing;
    }
}