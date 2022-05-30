package com.labs.day10;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Set<String> s1 = new HashSet<String>();
      s1.add("india");
      s1.add("USA");
      s1.add("UK");
      s1.add(null);
      for(String countries: s1) {
    	  System.out.println(countries);
      }
      Set<String> s2 = new TreeSet<String>();
      s2.add("india");
      s2.add("USA");
      s2.add("UK");
     // s2.add(null);throwing null pointer exception
      
     // Comparator<Employee>
	}

}
