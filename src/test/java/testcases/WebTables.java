package testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.WebTablesPage;
import utilities.ElementUtils;

public class WebTables extends BaseTest{
	WebTablesPage table;
	ElementUtils eleUtils;

	
	@BeforeMethod(alwaysRun = true)
	public void init(){
	table = new WebTablesPage(driver);
	table.webTablesNav();
	}
	
	@Test
	public void tableData() {
		table.search("Cie");
		table.deleteRecord();
		table.resetSearch();
		table.search("lega");
		table.editData();
		table.writeSalary("5000");
		table.submitData();
		table.addData();
		table.writeFirstName("Theary");
		table.writeLastName("Hang");
		table.writeEmail("sasudaai@hotmail.com");
		table.writeAge("20");
		table.writeSalary("8000");
		table.writeDepartment("Content creation");
		table.submitData();
	}
	

}
