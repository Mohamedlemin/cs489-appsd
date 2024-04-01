package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;

import java.util.Arrays;
import java.util.Comparator;

public class ProductMgmtApp {

	public static void main(String[] args) {
		Product[] products = {
				new Product("3128874119", "Banana", "2023-01-24", 124, 0.55),
				new Product("2927458265", "Apple", "2022-12-09", 18, 1.09),
				new Product("9189927460", "Carrot", "2023-03-31", 89, 2.99)
		};
		Arrays.sort(products, Comparator.comparing(Product::getProductName));


		printProductsInJSON(products);
		System.out.println("-------------------------");
		printProductsInXML(products);
		System.out.println("-------------------------");
		printProductsInCSV(products);
	}

	private static void printProductsInJSON(Product[] products) {
		System.out.println("JSON-formatted list of all Products:");
		System.out.println("[");
		for (Product product : products) {
			System.out.printf("  { \"productId\": \"%s\", \"productName\": \"%s\", \"dateSupplied\": \"%s\", \"quantityInStock\": %d, \"unitPrice\": %.2f },\n",
					product.getProductId(), product.getProductName(), product.getDateSupplied(), product.getQuantityInStock(), product.getUnitPrice());
		}
		System.out.println("]");
		System.out.println();
	}

	private static void printProductsInXML(Product[] products) {
		System.out.println("XML-formatted list of all Products:");
		System.out.println("<Products>");
		for (Product product : products) {
			System.out.printf("  <Product>\n    <productId>%s</productId>\n    <productName>%s</productName>\n    <dateSupplied>%s</dateSupplied>\n    <quantityInStock>%d</quantityInStock>\n    <unitPrice>%.2f</unitPrice>\n  </Product>\n",
					product.getProductId(), product.getProductName(), product.getDateSupplied(), product.getQuantityInStock(), product.getUnitPrice());
		}
		System.out.println("</Products>");
		System.out.println();
	}

	private static void printProductsInCSV(Product[] products) {
		System.out.println("Comma-Separated Values (CSV)-formatted list of all Products:");
		System.out.println("productId,productName,dateSupplied,quantityInStock,unitPrice");
		for (Product product : products) {
			System.out.printf("%s,%s,%s,%d,%.2f\n",
					product.getProductId(), product.getProductName(), product.getDateSupplied(), product.getQuantityInStock(), product.getUnitPrice());
		}
	}
}