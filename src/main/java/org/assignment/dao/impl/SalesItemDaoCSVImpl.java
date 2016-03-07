package org.assignment.dao.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.assignment.ConfigService;
import org.assignment.bo.Customer;
import org.assignment.bo.SaleItem;
import org.assignment.dao.SaleItemDao;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class SalesItemDaoCSVImpl implements SaleItemDao {
	
	private CellProcessor[] cellProcessor;
	private List<String> beanPropertyNames;
	private String[] headerLabels;

	public LinkedList<SaleItem> retrieveSalesItems(String filePath) {
		
		LinkedList<SaleItem> sales = new LinkedList<SaleItem>();

		File file = new File(filePath);
		
		FileReader fileReader = null;
		ICsvBeanReader beanReader = null;
		try{
			fileReader = new FileReader(file.getAbsolutePath());
            beanReader = new CsvBeanReader(fileReader, CsvPreference.STANDARD_PREFERENCE);
            
            //the header elements are used to map the values to the bean (names must match)
            final String[] header = beanReader.getHeader(true);
            headerLabels = header;
            
            SaleItem saleItem;
            while( (saleItem = beanReader.read(SaleItem.class, getBeanPropertyNames().toArray(new String[getBeanPropertyNames().size()]), getCellProcessor())) != null ) {
            	saleItem.setProduct(ConfigService.getObjectFactory().createProductService().getProductById(saleItem.getProductId()));
            	ConfigService.getObjectFactory().createCustomerService().createCustomer(saleItem.getCustomerId(), String.valueOf(saleItem.getCustomerId()));
            	saleItem.setCustomer(ConfigService.getObjectFactory().createCustomerService().getCustomerById(saleItem.getCustomerId()));
            	sales.add(saleItem);
            }
            
            beanReader.close();
            fileReader.close();
            
		}catch(IOException exception){
			exception.printStackTrace();
		 }catch(org.supercsv.exception.SuperCsvCellProcessorException ex){
			ex.printStackTrace(); 
		 }
            
		return sales;

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
			this.beanPropertyNames.add("customerId");
			this.beanPropertyNames.add("productId");
			this.beanPropertyNames.add("unitsSold");
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
			this.headerLabels = new String[]{"Customer ID","Product ID","Units"};
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
