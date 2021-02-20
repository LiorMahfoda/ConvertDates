package com.example;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Date {
  @NotEmpty
  @Positive
	@Min(value = 1, message = "Day should not be smaller than 0")
  @Max(value = 31, message = "Day should not be greater than 31")
  @Pattern(regexp = "[0-9]+", message = "Only digits")
  private String day;

  @NotEmpty
  @Positive
	@Min(value = 1, message = "Month should not be smaller than 0")
  @Max(value = 12, message = "Month should not be greater than 12")
  @Pattern(regexp = "[0-9]+", message = "Only digits")
  private String month;

  @NotEmpty
  @Positive
	@Size(min=1, max=4, message = "Year should be between 0 and 4 characters")
  @Min(value = 0 , message = "Year should not be smaller than 0")
  @Max(value = 0, message = "Year should not be greater than 9999")
  @Pattern(regexp = "[0-9]+", message = "Only digits")
  private String year;

  //Constructors
  public Date(){this.day = "1";this.month = "1";this.year = "1970";}

  public Date(String d, String m , String y){this.day = m;this.month = m;this.year = y;}

  //getters and setters
  public String getDay(){
    return day;
  }

  public String getMonth(){
    return month;
  }
  
  public String getYear(){
    return year;
  }

  public void setDay(String day){
    this.day = day;
  }

  public void setMonth(String month){
    this.month = month;
  }

  public void setYear(String year){
    this.year = year;
  }
 
  @Override
  public String toString() {
    return "Date{" + day + "/" + month + "/" + year +"}";
  }
  
}

