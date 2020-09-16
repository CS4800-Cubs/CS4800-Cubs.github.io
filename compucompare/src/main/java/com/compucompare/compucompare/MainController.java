package com.compucompare.compucompare;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
