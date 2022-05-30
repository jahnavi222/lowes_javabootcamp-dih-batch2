package com.labs.day13;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    System.out.println(getCity().isPresent()? getCity().get(): getCity().orElse("gg"));
    List<String> cities=getCities().orElse(Arrays.asList("gg"));
    List<String> cities1=getCities().isPresent()?getCities().get():getCities().orElseThrow(RuntimeException::new);
    System.out.println(cities.get(0));
    System.out.println(cities1.get(0));
    getCities().or(()->Optional.empty());
    getCities().ifPresentOrElse(System.out::println, null);
    
    getCities().ifPresentOrElse(System.out::println, ()-> new Runnable() {

		@Override
		public void run() {
			System.out.println("no cities");
			
		}
    	
    });
    Optional<List<String> > cities4 = getCities().filter(item ->item.contains("A2")&&!item.contains("A4"));
    System.out.println(cities4);
	}
	
	private static Optional<List<String> >getCities(){
		Optional<List<String> > cities=Optional.of (Arrays.asList("A1","A2","A3"));
		return cities;
	}
    private static Optional<String> getCity() {

Optional<String> city = Optional.of("Test");
Optional<String> city1 = Optional.ofNullable(null);
Optional<String> city2 = Optional.empty();
return city;


    }
}
