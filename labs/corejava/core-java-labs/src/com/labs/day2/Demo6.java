package com.labs.day2;

class Book {

	public String title;

	public String author;

	public int price;

	public Book(String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public void printBookInfo() {
		System.out.println("Book Title:" + title + "  Author :" + author + " Price:" + price);

	}
}

public class Demo6 {
	public static void main(String[] args) {

		Book obj1 = new Book("title1", "Author1", 100);

		obj1.printBookInfo();

		Book obj2 = new Book("title2", "Author2", 200);
		obj2.printBookInfo();
	}

}
