package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;

public class Product {
	private String productId;
	private String productName;
	private String dateSupplied;
	private int quantityInStock;
	private double unitPrice;

	// Constructors
	public Product() {
		// Default constructor
	}

	public Product(String productId, String productName, String dateSupplied, int quantityInStock, double unitPrice) {
		this.productId = productId;
		this.productName = productName;
		this.dateSupplied = dateSupplied;
		this.quantityInStock = quantityInStock;
		this.unitPrice = unitPrice;
	}

	// Getter and setter methods
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDateSupplied() {
		return dateSupplied;
	}

	public void setDateSupplied(String dateSupplied) {
		this.dateSupplied = dateSupplied;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
