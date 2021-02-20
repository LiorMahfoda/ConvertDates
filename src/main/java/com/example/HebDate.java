package com.example;

public class HebDate {
    private String hebDay;
    private String hebMonth;
    private String hebYear;
    private String hebrewName;
    
    //Constructors
    public HebDate(){ hebDay = null; hebMonth = null; hebYear =null;hebrewName=null;}

    public HebDate(String d,String m,String y, String hebrewName){
      this.hebDay = d;
      this.hebMonth = m;
      this.hebYear = y;
      this.hebrewName = hebrewName;
    }
    //getters and setters
    public String getDay(){
      return hebDay;
    }
    public String getMonth(){
      return hebMonth;
    }
    public String getYear(){
      return hebYear;
    }
    public String getName(){
      return hebrewName;
    }

    public void setDay(String hebDay){
      this.hebDay = hebDay;
    }
  
    public void setMonth(String hebMonth){
      this.hebMonth = hebMonth;
    }
  
    public void setYear(String hebYear){
      this.hebYear = hebYear;
    }
   
    @Override
    public String toString() {
      return "Hebrew Date{" +  hebMonth + ", " + hebDay + ", "+ hebYear + ", "+hebrewName +"}";
    } 
}
