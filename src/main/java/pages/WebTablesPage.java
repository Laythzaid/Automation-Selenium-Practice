package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.internal.Utils;

import utilities.ElementUtils;
import utilities.LocatorsUtil;

public class WebTablesPage {
	private WebDriver driver;
	private ElementUtils eleUtils;
	private By elementsNav;
	private By webTablesNav;
	
	public WebTablesPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtils = new ElementUtils(driver);
		
		elementsNav = By.xpath(LocatorsUtil.get("nav.elements"));
		webTablesNav = By.xpath(LocatorsUtil.get("nav.webTables"));
	}
	
	
	
	public void webTablesNav() {
		eleUtils.clickWhenReady(elementsNav, 5);
		eleUtils.clickWhenReady(webTablesNav, 5);
	}
	
	private By searchBar = By.cssSelector(LocatorsUtil.get("table.search.css"));
	
	public void search(String searchData) {
		driver.findElement(searchBar).clear();
		eleUtils.type(searchBar, searchData, 5);
	}
	
	private By deleteBtn = By.cssSelector(LocatorsUtil.get("table.deleteRecord.css")); 
	
	public void deleteRecord() {
		eleUtils.scrollTo(200);
		eleUtils.clickWhenReady(deleteBtn, 10);
	}

	
	private By editBtn= By.cssSelector(LocatorsUtil.get("table.editRecord.css")); 
	public void editData() {
		eleUtils.clickWhenReady(editBtn, 5);
	}
	
	public void resetSearch(){
		WebElement searchBarEl = driver.findElement(searchBar);
		searchBarEl.clear();
	}
	
	private By firstNameField = By.cssSelector(LocatorsUtil.get("table.firstName.css"));
	
	private By lastNameField = By.cssSelector(LocatorsUtil.get("table.lastName.css"));
	
	private By emailField = By.cssSelector(LocatorsUtil.get("table.email.css"));

	private By ageField = By.cssSelector(LocatorsUtil.get("table.age.css"));
	
	private By salaryField = By.cssSelector(LocatorsUtil.get("table.salary.css"));
	
	private By departmentField = By.cssSelector(LocatorsUtil.get("table.department.css"));


	
	public void writeFirstName(String empFirstName) {
		driver.findElement(firstNameField).clear();
		eleUtils.type(firstNameField, empFirstName, 5);
	}
	public void writeLastName(String empLastName) {
		driver.findElement(lastNameField).clear();
		eleUtils.type(lastNameField, empLastName, 5);
	}
	public void writeEmail(String empEmail) {
		driver.findElement(emailField).clear();
		eleUtils.type(emailField, empEmail, 5);
	}
	public void writeAge(String empAge) {
		driver.findElement(ageField).clear();
		eleUtils.type(ageField, empAge, 5);
	}
	public void writeSalary(String salary) {

		driver.findElement(salaryField).clear();
		eleUtils.type(salaryField, salary, 5);
	}
	public void writeDepartment(String empDepartment) {
		driver.findElement(departmentField).clear();
		eleUtils.type(departmentField, empDepartment, 5);
	}
	
	private By submitBtn = By.cssSelector(LocatorsUtil.get("table.submit.css"));
	
	public void submitData() {
		eleUtils.clickWhenReady(submitBtn, 5);
	}
	
	private By addData = By.cssSelector(LocatorsUtil.get("table.addData.css"));
	
	public void addData() {
		eleUtils.clickWhenReady(addData, 5);
	}
	
}
