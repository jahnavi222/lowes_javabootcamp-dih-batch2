package com.labs.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class ArrayListDemo {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		list.add("test1");
		list.add("test2");
		list.add("test3");
		list.remove(0);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		list.add(0, "test1");
		list.set(1, "mute");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		for (String item : list) {
			System.out.println(item);
		}
		// ArrayList initialization
		ArrayList<String> gfg = new ArrayList<String>(Arrays.asList("test", "for", "list"));
		// print ArrayList
		System.out.println("ArrayList : " + gfg);
		ArrayList<String> gfg1 = new ArrayList<String>(List.of("test", "for", "list"));
		// print ArrayList
		System.out.println("ArrayList : " + gfg1);

		List<Integer> arr = new ArrayList<>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);
		List<Integer> arr1 = new Vector<Integer>(arr);
		System.out.println("ArrayList : " + arr1);
		//loop through the iterator
		Iterator<Integer> ite = arr.iterator();
		while(ite.hasNext()) {
			System.out.println(ite.next());
			
		}
		//loop through listIterator
		ListIterator<Integer>  listIte = arr1.listIterator();
		while(listIte.hasNext()) {
			System.out.println(listIte.next());
			
		}
		//loop through listIterator
		while(listIte.hasPrevious()) {
			System.out.println(listIte.previous());
			
		}
		

	}

}
