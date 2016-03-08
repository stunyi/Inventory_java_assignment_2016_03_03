package org.assignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.assignment.bo.Customer;
import org.assignment.bo.InventoryItem;
import org.assignment.bo.Product;
import org.assignment.bo.SaleItem;
import org.assignment.factory.impl.DefaultFactory;
import org.assignment.service.InventoryService;
import org.assignment.service.impl.InventoryServiceImpl;

public class App {

	public static void main(String[] args) {

		// Configure the Service Engine
		ConfigService.setObjectFactory(new DefaultFactory());
		
		// Print all the Products
		printImportedProducts();
		// Print Beginning Inventory
		printImportedInventory();
		// Print Imported Sales
		printImportedSales();
		// Print All Sales Per Product
		printTotalSalesPerProduct();
		//Print all Sales per Customer
		printTotalSalesPerCustomer();
		// Print Customers Based of Purchases
		printListOfCustomerBasedOnRevenue();
		// Print Products based on Sales
		printListOfProductsBasedPurchases();
		// Print Sample Inventory Level Change
		printSampleInventoryChange();

	}

	public static void printImportedProducts(){
	
		String productDisplay = "Id: %1$s, Name: %2$s, Unit Price: %3$s";
		LinkedList<Product> products = ConfigService.getObjectFactory().createProductService().getAllProducts();
		
		System.out.println("======================================================");
		System.out.println("All Products Imported");
		System.out.println("------------------------------------------------------");
		
		for(Product product: products){
			System.out.println(String.format(productDisplay, product.getProductID(),product.getDescription(),product.getUnitPrice()));
		}
		
		System.out.println("********************************************************");
	}

	public static void printImportedInventory(){
		
		String inventoryDisplay = "Inventory Id: %1$s, Product Name: %2$s, Quantity in Stock: %3$s";
		
		List<InventoryItem> invetoryItems = ConfigService.getObjectFactory().createInventoryService().getAllCurrentInventoryItems();
		
		System.out.println("======================================================");
		System.out.println("All Inventory Items Imported (Beginning Inventory)");
		System.out.println("------------------------------------------------------");
		
		for(InventoryItem inventory : invetoryItems){
			System.out.println(String.format(inventoryDisplay,inventory.getInventoryId(),inventory.getProduct().getDescription(),inventory.getQuantity()));
		}
		System.out.println("********************************************************");
	}

	public static void printImportedSales(){
		
		String salesDisplay = "Customer Name: %1$s, Product Name: %2$s, Unit Price: %3$s, Quantity Sold: %4$s, Total: %5$s";
		
		System.out.println("======================================================");
		System.out.println("All Sales Items Imported");
		System.out.println("------------------------------------------------------");
		
		LinkedList<SaleItem> sales = ConfigService.getObjectFactory().createSalesService().getAllItemsSold();
		
		for(SaleItem sale : sales){
			System.out.println(String.format(salesDisplay,
												sale.getCustomer().getCustomerName(), 
												sale.getProduct().getDescription(),
												sale.getProduct().getUnitPrice(),
												sale.getUnitsSold(),sale.getSaleTotal()));
		}
		
		System.out.println("------------------------------------------------------");
		System.out.println("Total Number of Units Sold: " + ConfigService.getObjectFactory().createSalesService().getTotalNumberOfUnitsSold());
		System.out.println("Total Revenue: " + ConfigService.getObjectFactory().createSalesService().getTotalValueOfUnitsSold());
		
		System.out.println("********************************************************");
	}
	
	public static void printTotalSalesPerProduct(){
		
		String salesDisplay = "Customer Name: %1$s, Product Name: %2$s, Unit Price: %3$s, Quantity Sold: %4$s, Total: %5$s";
		
		LinkedList<Product> products = ConfigService.getObjectFactory().createProductService().getAllProducts();
		
		System.out.println("======================================================");
		System.out.println("  All Sales per Product");
		System.out.println("------------------------------------------------------");
		
		for(Product product : products){
			
			LinkedList<SaleItem> salesItems = ConfigService.getObjectFactory().createSalesService().getAllProductsPurchasedByProductId(product.getProductID());
			
			if(salesItems != null){
				for(SaleItem sale : salesItems){
					System.out.println(String.format(salesDisplay,
							sale.getCustomer().getCustomerName(), 
							sale.getProduct().getDescription(),
							sale.getProduct().getUnitPrice(),
							sale.getUnitsSold(),sale.getSaleTotal()));
				}
				
				System.out.println("------------------------------------------------------");
				System.out.println("                      Product:" + product.getDescription() + 
						", Total Number of Items: " + ConfigService.getObjectFactory().createSalesService().getTotalNumberOfItemsSoldByProductId(product.getProductID()) + 
						" , Total Revenue: " + ConfigService.getObjectFactory().createSalesService().getTotalValueOfItemsSoldByProductId(product.getProductID()));
				
				System.out.println("------------------------------------------------------");		
			}
			
			
		}
		
		System.out.println("********************************************************");
	}
	
	public static void printTotalSalesPerCustomer(){

		String salesDisplay = "Customer Name: %1$s, Product Name: %2$s, Unit Price: %3$s, Quantity Sold: %4$s, Total: %5$s";

		LinkedList<Customer> customers = ConfigService.getObjectFactory().createCustomerService().getAllCustomers();

		System.out.println("======================================================");
		System.out.println("  All Sales per Customer");
		System.out.println("------------------------------------------------------");

		for(Customer product : customers){

			LinkedList<SaleItem> salesItems = ConfigService.getObjectFactory().createSalesService().getAllItemsSoldToCustomer(product.getCustomerId());

			if(salesItems != null){
				for(SaleItem sale : salesItems){
					System.out.println(String.format(salesDisplay,
							sale.getCustomer().getCustomerName(), 
							sale.getProduct().getDescription(),
							sale.getProduct().getUnitPrice(),
							sale.getUnitsSold(),sale.getSaleTotal()));
				}

				System.out.println("------------------------------------------------------");
				System.out.println("                      Customer:" + product.getCustomerName() + 
						", Total Number of Items: " + ConfigService.getObjectFactory().createSalesService().getTotalNumberOfItemPurchasedByCustomer(product.getCustomerId()) + 
						" , Total Revenue: " + ConfigService.getObjectFactory().createSalesService().getTotalValueOfItemPurchasedByCustomer(product.getCustomerId()));

				System.out.println("------------------------------------------------------");		
			}


		}

		System.out.println("********************************************************");
	}

	public static void printListOfCustomerBasedOnRevenue(){
		
		System.out.println("======================================================");
		System.out.println("  List of Customers based on Purchases (i.e. Descending Order)");
		System.out.println("------------------------------------------------------");
		
		LinkedList<Customer> customers = ConfigService.getObjectFactory().createSalesService().getInDescendingOrderListOfCustomersBasedOnTotalPurchases();
		
		for(Customer customer : customers){
			System.out.println("Customer Name: " + customer.getCustomerName());
		}
		
		System.out.println("********************************************************");
	}
	
	public static void printListOfProductsBasedPurchases(){

		System.out.println("======================================================");
		System.out.println("  List of Products based on Purchases (i.e. Descending Order)");
		System.out.println("------------------------------------------------------");

		LinkedList<Product> products = ConfigService.getObjectFactory().createSalesService().getInDescendingOrderListOfProductsSold();

		for(Product product : products){
			System.out.println("Product Name: " + product.getDescription());
		}

		System.out.println("********************************************************");
	}

	public static void printSampleInventoryChange(){
		
		System.out.println("======================================================");
		System.out.println("  Inventory Change for product '10002-" + ConfigService.getObjectFactory().createProductService().getProductById(10002).getDescription() + "'");
		System.out.println("------------------------------------------------------");
		
		InventoryService inventoryService = ConfigService.getObjectFactory().createInventoryService();
		double initialNoOfItemsInStock = InventoryServiceImpl.getQuantityByProductId(10002).doubleValue();
		System.out.println("Initial Stock Quantity: " + initialNoOfItemsInStock);
		
		SaleItem sale = ConfigService.getObjectFactory().createSaleItem();
		sale.setCustomerId(1);
		sale.setUnitsSold(1);
		sale.setProductId(10002);
		
		List<SaleItem> sales = new ArrayList<SaleItem>();
		sales.add(sale);
		
		System.out.println("Quantity Sold: " + sale.getUnitsSold());
		
		inventoryService.updateInventoryLevelsBasedOnSales(sales);
		double currentStockQuantity = InventoryServiceImpl.getQuantityByProductId(10002).doubleValue();
		
		System.out.println("Remaining Stock: " + currentStockQuantity);
	}
}
