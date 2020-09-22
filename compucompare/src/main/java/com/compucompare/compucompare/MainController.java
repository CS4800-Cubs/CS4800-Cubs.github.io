package com.compucompare.compucompare;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random; 

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

    /**
     * Author: Alec
     * Generate a random password with customization options
     * @param length String containing length of password.
     * @param includeSpecialCharacters boolean to add special characters to password.
     * @param includeNumbers boolean to add numbers to password.
     * @param includeUpperCase boolean to add upper case letters to password.
     * @param includeLowerCase boolean to add lower case letters to password.
     * @return JSon Object with password and option fields.
     */
    @RequestMapping(value = "/generatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    String generatePassword(@RequestParam(value = "length", defaultValue = "8") String length, 
                            @RequestParam(value = "includeSpecialCharacters", required = false, defaultValue = "false") boolean includeSpecialCharacters,
                            @RequestParam(value = "includeNumbers", required = false, defaultValue = "false") boolean includeNumbers,
                            @RequestParam(value = "includeUpperCase", required = false, defaultValue = "false") boolean includeUpperCase,
                            @RequestParam(value = "includeLowerCase", required = false, defaultValue = "true") boolean includeLowerCase) 
    throws JSONException{

        String options = "";

        if(includeLowerCase){
            options += "abcdefghijklmnopqrstuvwxyz";
        }
        if(includeUpperCase){
            options += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if(includeNumbers){
            options += "0123456789";
        }
        if(includeSpecialCharacters){
            options += "!@#$%^&*_=+-/";
        }

        JSONObject password = new JSONObject();

        password.put("length", length);
        password.put("includeLowerCase", includeLowerCase);
        password.put("includeUpperCase", includeUpperCase);
        password.put("includeNumbers", includeNumbers);
        password.put("includeSpecialCharacters", includeSpecialCharacters);

        
        try {
            String result = "";
            Random rand = new Random(); 

            for (int i = 0; i < Integer.parseInt(length); i++) {
                int index = rand.nextInt(options.length());
                result += options.charAt(index);
            }
            password.put("password", result);

        }

        catch (JSONException e) {
            return "Error Forming JSON";
        }

        return password.toString();
    }
    
    /**
     * Author Monica
     */
    @RequestMapping(value = "/NeedABreak", method = RequestMethod.GET)
    public String getText(){
    	return "<HTML>"
    			+"<body style =\"background-color: black;\">"
    			+"<p style = \"color: Red; text-align: center\"> Happy ~almost~ Spooktober!<p>"
    			+"<img src=\"https://www.gamerevolution.com/assets/uploads/2019/12/dead-by-daylight-pennywise-1280x720.jpg\" style = \"width:500px; display: block;\r\n" + 
    			"  margin-left: auto;\r\n" + 
    			"  margin-right: auto;\r\n" + 
    			"  width: 50%; \">"
    			+           "<body><h1 style = \"color: Red; text-align: center\">Play DEAD BY DAYLIGHT!</h1></body>"
    			+       "</HTML>";
    }
    
}
