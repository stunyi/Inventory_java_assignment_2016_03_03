package org.assignment.dao.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.assignment.ConfigService;
import org.assignment.bo.InventoryItem;
import org.assignment.dao.InventoryDao;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class InventoryDaoCSVImpl implements InventoryDao {
	
	private CellProcessor[] cellProcessor;
	private List<String> beanPropertyNames;
	private String[] headerLabels;

	public List<InventoryItem> retrieveInventoryItems(String filePath) {
		
		List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();

		File file = new File(filePath);
		
		FileReader fileReader = null;
		ICsvBeanReader beanReader = null;
		try{
			fileReader = new FileReader(file.getAbsolutePath());
            beanReader = new CsvBeanReader(fileReader, CsvPreference.STANDARD_PREFERENCE);
            
            //the header elements are used to map the values to the bean (names must match)
            final String[] header = beanReader.getHeader(true);
            headerLabels = header;
            
            InventoryItem inventoryItem;
            while( (inventoryItem = beanReader.read(InventoryItem.class, getBeanPropertyNames().toArray(new String[getBeanPropertyNames().size()]), getCellProcessor())) != null ) {
            	inventoryItem.setProduct(ConfigService.getObjectFactory().createProductService().getProductById(inventoryItem.getProductId()));
            	inventoryItems.add(inventoryItem);
            }
            
            beanReader.close();
            fileReader.close();
            
		}catch(IOException exception){
			exception.printStackTrace();
		 }catch(org.supercsv.exception.SuperCsvCellProcessorException ex){
			ex.printStackTrace(); 
		 }
            
		return inventoryItems;
	}

	public void setCellProcessor(CellProcessor[] cellProcessor){
		this.cellProcessor = cellProcessor;
	}
	
	public void setBeanPropertyNames(List<String> beanPropertyNames) {
		this.beanPropertyNames = beanPropertyNames;
	}
	
	public void setHeaderLabels(String[] headerLabels) {
		this.headerLabels = headerLabels;
	}

	/**
	 * @return the cellProcessor
	 */
	public CellProcessor[] getCellProcessor() {
		if(this.cellProcessor == null){
			CellProcessor[] cellProcessor = new CellProcessor[]{
					new ParseInt(),
					new ParseInt(),
					new ParseDouble()
			};
			this.cellProcessor = cellProcessor;
		}
		
		return this.cellProcessor;	
	}

	/**
	 * @return the beanPropertyNames
	 */
	public List<String> getBeanPropertyNames() {
		if(this.beanPropertyNames == null){
			this.beanPropertyNames = new ArrayList<String>();
			this.beanPropertyNames.add("inventoryId");
			this.beanPropertyNames.add("productId");
			this.beanPropertyNames.add("quantity");
		}
		return this.beanPropertyNames;
	}

	/**
	 * @return the headerLabels
	 */
	public String[] getHeaderLabels() {
		if(this.headerLabels == null){
		String[] headers = new String[]{"Inventory ID","Product ID","Quantity"};
		this.headerLabels = headers;
		}
		return this.headerLabels;
	}
}
