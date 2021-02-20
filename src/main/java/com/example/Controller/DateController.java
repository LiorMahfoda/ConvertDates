package com.example.Controller;

import com.example.Date;
import com.example.HebDate;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class DateController {
  private static Logger logger = LoggerFactory.getLogger(DateController.class); // insted of System.out.println to console
  // a class to communicate with the http request in the api
  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/")
  public String dateForm(@ModelAttribute("date") Date date, Model model) {
    model.addAttribute("date", new Date());
    model.addAttribute("hebDate", new HebDate());
    return "index"; // index.html
  }

  @PostMapping("/index")
  public String dateSubmit(@Valid @ModelAttribute("date") Date date, Model model, BindingResult bindingResult) throws JSONException {
    if (bindingResult.hasErrors()) {
      logger.error("error:" + bindingResult);
      return "index";
    }  
    JSONObject d = getDate(date); // Data from json: hy-hebrew year, hm-hebrew month, hd - hebrew day, hebrew - name
    HebDate hebDate = new HebDate(d.getString("hd"),d.getString("hm"),d.getString("hy"), d.getString("hebrew"));
    model.addAttribute("date", date);
    model.addAttribute("hebDate", hebDate);
    /*logger.info("hday= "+hebDate.getDay()); logger.info("hmonth = "+
    hebDate.getMonth()); logger.info("hyear = "+hebDate.getYear()); logger.info("hname = "+hebDate.getName());
    logger.info("-----------------------"); // prints data to the console
    */
    return "index"; // index.html
  }

  // function to get hebrew date data
  // parmeters to uri are: day,month&year of gregorian date
  // returns json of hebrew date 
  public JSONObject getDate(Date date){
    String d = date.getDay();
    String m = date.getMonth();
    String y = date.getYear();
    final String uri = "https://www.hebcal.com/converter?cfg=json&gy=" + y + "&gm=" + m + "&gd=" + d + "&g2h=1";
    String result = restTemplate.getForObject(uri, String.class);
    JSONObject jsonObject = null;
    try {
      jsonObject = new JSONObject(result);
    } catch (JSONException e) {
      logger.error(e.getMessage(),jsonObject);
    }
    /*logger.info("jsonObject = " + jsonObject);
    logger.info("heb_year = " +jsonObject.getString("hy"));
    logger.info("heb_month = " +jsonObject.getString("hm"));
    logger.info("heb_day = "+jsonObject.getString("hd"));
    logger.info("heb_name = " +jsonObject.getString("hebrew")); // prints data of hebrew date
    */ 
    return jsonObject;
  }
}