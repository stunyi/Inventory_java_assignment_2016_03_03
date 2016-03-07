package org.assignment.dao.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.assignment.bo.Product;
import org.assignment.dao.ProductDao;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class ProductDaoCSVImpl implements ProductDao {

	private CellProcessor[] cellProcessor;
	private List<String> beanPropertyNames;
	private String[] headerLabels;
	
	public LinkedList<Product> retrieveProducts(String filePath) {
		
		LinkedList<Product> products = new LinkedList<Product>();

		File file = new File(filePath);
		
		FileReader fileReader = null;
		ICsvBeanReader beanReader = null;
		try{
			fileReader = new FileReader(file);
            beanReader = new CsvBeanReader(fileReader, CsvPreference.STANDARD_PREFERENCE);
            
            //the header elements are used to map the values to the bean (names must match)
            final String[] header = beanReader.getHeader(true);
            headerLabels = header;
            
            Product productItem;
            while( (productItem = beanReader.read(Product.class, getBeanPropertyNames().toArray(new String[getBeanPropertyNames().size()]), getCellProcessor())) != null ) {
            	products.add(productItem);
            }
            
            beanReader.close();
            fileReader.close();
            
		}catch(IOException exception){
			exception.printStackTrace();
		 }catch(org.supercsv.exception.SuperCsvCellProcessorException ex){
			ex.printStackTrace(); 
		 }
            
		return products;

	}

	/**
	 * @return the cellProcessor
	 */
	public CellProcessor[] getCellProcessor() {
		if(this.cellProcessor == null){
			CellProcessor[] cellProcessor = new CellProcessor[]{
					new ParseInt(),
					new NotNull(),
					new ParseDouble()
			};
			this.cellProcessor = cellProcessor;
		}
		
		return this.cellProcessor;	
	}

	/**
	 * @param cellProcessor the cellProcessor to set
	 */
	public void setCellProcessor(CellProcessor[] cellProcessor) {
		this.cellProcessor = cellProcessor;
	}

	/**
	 * @return the beanPropertyNames
	 */
	public List<String> getBeanPropertyNames() {
		
		if(this.beanPropertyNames == null){
			this.beanPropertyNames = new ArrayList<String>();
			this.beanPropertyNames.add("productID");
			this.beanPropertyNames.add("description");
			this.beanPropertyNames.add("unitPrice");
		}
		return this.beanPropertyNames;
	}

	/**
	 * @param beanPropertyNames the beanPropertyNames to set
	 */
	public void setBeanPropertyNames(List<String> beanPropertyNames) {
		this.beanPropertyNames = beanPropertyNames;
	}

	/**
	 * @return the headerLabels
	 */
	public String[] getHeaderLabels() {
		if(this.headerLabels == null){
			this.headerLabels = new String[]{"Product ID","Description","Unit Price"};
		}
		return this.headerLabels;
	}

	/**
	 * @param headerLabels the headerLabels to set
	 */
	public void setHeaderLabels(String[] headerLabels) {
		this.headerLabels = headerLabels;
	}

}
