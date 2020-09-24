package com.compucompare.compucompare;

import org.joda.time.LocalTime;
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
     * Assignment 3 - Creates HTTP API request
     * @return an HTML design
     */
    @RequestMapping(value = "/nataliePage")
    public String getText(){
        return "<HTML>"
                +           "<body><h1 style = \"color: lightblue\">Natalie's Page</h1></body>" +
                            "<img src = \"data:image/jpeg;base64,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\">"
                +"</HTML>";
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
     * Author Monica, A3
     */
    @RequestMapping(value = "/NeedABreak", method = RequestMethod.GET)
    public String getHTMLString(){
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
    
    /**
     * Author Monica, A4
     */
    @RequestMapping("/SayHello")
    public String sayHello(){
    	LocalTime currentTime = new LocalTime();
    	String time = ("It is currently: " + currentTime);
    	Greeter greeter = new Greeter();
    	return greeter.sayHello()+"\n"+time;    

    }
    public class Greeter {
    	  public String sayHello() {
    	    return "You look bootiful!";
    	  }
    	}
}
