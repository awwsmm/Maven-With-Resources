package com.companyname.packagename;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class MainClass {

  public static void main (String[] args) throws UnsupportedEncodingException, IOException {
    readResource();
  }

  public static void readResource() throws UnsupportedEncodingException, IOException {

    InputStream is = MainClass.class.getClassLoader().getResourceAsStream("example.txt");
    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
    BufferedReader file = new BufferedReader(isr);

    String line = null; // line read from file
    while ((line = file.readLine()) != null)
      System.out.println(line);

    file.close(); isr.close(); is.close();

  }

}
