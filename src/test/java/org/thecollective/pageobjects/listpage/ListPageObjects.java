package org.thecollective.pageobjects.listpage;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.thecollective.maincontroller.PageFactoryInitializer;
import org.thecollective.pageobjects.homepage.StoresPageObjects;
import org.thecollective.utils.SearchDataPropertyFile;
import org.thecollective.utils.Waiting;

import ru.yandex.qatools.allure.annotations.Step;

public class ListPageObjects extends PageFactoryInitializer{

	SearchDataPropertyFile data=new SearchDataPropertyFile();
	
	@FindAll(value={@FindBy(xpath="//div[contains(@class,'category__breadcrumbs')]/a")})
	private  List<WebElement> breadCrumbs;
	
	@FindBy(xpath="//h1[contains(@class,'brand-title')]")
	private WebElement brandTitle;
	
	@FindAll(value={@FindBy(xpath="//div[contains(@class,'products_list_item')]")})
	private List<WebElement> listedItems;
	
	@FindAll(value={@FindBy(xpath="//div[contains(@class,'product-price')]/span")})
	private List<WebElement> listedItemsPrice;
	
	@FindBy(xpath="//div[@class='custom_pagination_inner']//a[@class='nextpageupdate']")
	private WebElement nextPaginationBottom;
	
	@FindAll(value={@FindBy(xpath="//span[@itemprop='desc']")})
	private List<WebElement> listedItemsDescriptionName;
	
	@FindBy(xpath="//h1[contains(text(),'SORT BY')]")
	private WebElement sortByText;
	
	@FindAll(value={@FindBy(xpath="//ul[@class='dropdown-menu']//a")})
	private List<WebElement> sortByOptions;
	
	@FindBy(xpath="//h1[contains(@class,'dropdown-toggle')]")
	private WebElement sortByDropdown;
	
	@FindBy(xpath="//div[contains(@class,'products_list')]//h1")
	private WebElement noResultsFoundText;
	
	//===================================================
	@Step("verify product list page")
	public ListPageObjects verifyListedProducts() throws Exception {
			
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Waiting.explicitWaitVisibilityOfElements(listedItems, 30);
			Assert.assertTrue(listedItems.get(0).isDisplayed(),"products are not available");
			return this;
		
	}
	@Step("verify product list page")
	public boolean verifyListedProduct() throws Exception {
	Waiting.explicitWaitVisibilityOfElements(listedItems, 30);
	Assert.assertTrue(listedItems.get(0).isDisplayed(),"products are not available");
			return true;
		
	}

	public ListPageObjects verifyBreadcrumbs(String brandName)
	{
		Assert.assertTrue(verifyBreadcrumb(brandName),"breadcrumbs were not same");
		Assert.assertEquals(driver.getTitle(),""+brandName+" | "+data.getProductName(),"page title is not correct");

		return this;
	}

	private boolean verifyBreadcrumb(String brandName) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try{
			if(breadCrumbs.get(breadCrumbs.size()-1).getText().replace(">", "").trim().toLowerCase().contains(brandName))
		{
			return true;
		}
			else 
		{
			Assert.assertTrue(brandTitle.getText().toLowerCase().contains(brandName));
			return true;
		}
	}catch(Exception e)
		{
		return false;
	}
	}
	@Step("verify search results page")
	public ListPageObjects verifySearchResultsPage() {
		Waiting.explicitWaitVisibilityOfElements(listedItems, 15);
		Assert.assertTrue(listedItems.get(0).isDisplayed(), "search results not found");
		return this;
	}
	@Step("verify search results page title")
	public ListPageObjects verifyPageTitle() 
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals(driver.getTitle(), data.getSearchResultsPageTitle()+" | "+data.getProductName().trim());

		return this;
	}
	@Step("verify invalid search results")
	public ListPageObjects verifyInvalidSearchResultsPage(String noresultsText) throws Exception {
		Waiting.explicitWaitVisibilityOfElement(noResultsFoundText, 30);
		Assert.assertEquals(noResultsFoundText.getText().trim(),noresultsText);
		return this;
	}
	@Step("click on specific product {0} in list page")
	public ListPageObjects clickOnSpecificProduct(int number) throws InterruptedException {
		Waiting.explicitWaitVisibilityOfElements(listedItems, 30);
		listedItems.get(number).click();
		Thread.sleep(3000);

		return this;
	}
	@Step("click on a product for cod order")
	public ListPageObjects clickOnProductForCod(String orderType,String maxPriceForCod) throws Exception {
		Assert.assertTrue(assertVerifyItemsAvailability(), "items are not displayed in product list page");
		for(int i=0;i<listedItems.size();i++)
		{
			Double expPrice=Double.parseDouble(maxPriceForCod);
			Double actPrice=Double.parseDouble(listedItemsPrice.get(i).getText().replace(",","").trim());
		if(actPrice<expPrice)
		{
			listedItems.get(i).click();
			break;
		}else{
			if(nextPaginationBottom.isDisplayed())
			{
			nextPaginationBottom.click();
			Thread.sleep(4000);
			clickOnProductForCod(orderType, maxPriceForCod);
			}else
			{
				throw new Exception("Please select other category to place an order for COD option");
			}
		}
		}
			
			/*switch(orderType)
			{
			case "Cash on Delivery":
				for(int i=0;i<listedItems.size();i++)
				{
					Double expPrice=Double.parseDouble(maxPriceForCod);
					Double actPrice=Double.parseDouble(listedItemsPrice.get(i).getText().replace(",","").trim());
				if(actPrice<expPrice)
				{
					listedItems.get(i).click();
					break;
				}else{
					if(nextPaginationBottom.isDisplayed())
					{
					nextPaginationBottom.click();
					Thread.sleep(4000);
					clickOnProductForCod(orderType, maxPriceForCod);
					}else
					{
						throw new Exception("Please select other category to place an order for COD option");
					}
				}
				}
				break;
			case "Credit Card":
				for(int i=0;i<listedItems.size();i++)
				{
					Double expPrice=Double.parseDouble(maxPriceForCod);
					Double actPrice=Double.parseDouble(listedItemsPrice.get(i).getText().replace(",","").trim());
				if(actPrice>expPrice)
				{
					listedItems.get(i).click();
					break;
				}else{
					if(nextPaginationBottom.isDisplayed())
					{
					nextPaginationBottom.click();
					Thread.sleep(4000);
					clickOnProductForCod(orderType, maxPriceForCod);
					}else {
						throw new Exception("Please select other category to place an order for COD option");
					}
				}
				}
				break;
			case "Debit Card":
				for(int i=0;i<listedItems.size();i++)
				{
					Double expPrice=Double.parseDouble(maxPriceForCod);
					Double actPrice=Double.parseDouble(listedItemsPrice.get(i).getText().replace(",","").trim());
				if(actPrice>expPrice)
				{
					listedItems.get(i).click();
					break;
				}else{
					if(nextPaginationBottom.isDisplayed())
					{
					nextPaginationBottom.click();
					Thread.sleep(4000);
					clickOnProductForCod(orderType, maxPriceForCod);
					}else {
						throw new Exception("Please select other category to place an order for COD option");
					}
				}
				}
				break;
			case "Net Banking":
				for(int i=0;i<listedItems.size();i++)
				{
					Double expPrice=Double.parseDouble(maxPriceForCod);
					Double actPrice=Double.parseDouble(listedItemsPrice.get(i).getText().replace(",","").trim());
				
				if(actPrice>expPrice)
				{
					listedItems.get(i).click();
					break;
				}else{
					if(nextPaginationBottom.isDisplayed())
					{
					nextPaginationBottom.click();
					Thread.sleep(4000);
					clickOnProductForCod(orderType, maxPriceForCod);
					}else {
						throw new Exception("Please select other category to place an order for COD option");
					}
				}
				}
				break;
			case "Wallet":
				for(int i=0;i<listedItems.size();i++)
				{
					Double expPrice=Double.parseDouble(maxPriceForCod);
					Double actPrice=Double.parseDouble(listedItemsPrice.get(i).getText().replace(",","").trim());
				if(actPrice>expPrice)
				{
					listedItems.get(i).click();
					break;
				}else{
					if(nextPaginationBottom.isDisplayed())
					{
					nextPaginationBottom.click();
					Thread.sleep(4000);
					clickOnProductForCod(orderType, maxPriceForCod);
					}else {
						throw new Exception("Please select other category to place an order for COD option");
					}
				}
				
			}
			
				break;
			
		}
*/
		return this;
	}

	private boolean assertVerifyItemsAvailability() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try{
			if(listedItems.get(0).isDisplayed()){
				return true;
			}
			
		}catch(Exception e)
		{
			return false;
		}
		return false;
	}
	@Step("get the product name {0} from list page")
	public String getProductName(int num) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String productName=listedItemsDescriptionName.get(num).getText().trim();
		return productName;
	}
	@Step("verify product list page with mrp {0}")
	public ListPageObjects verifyProduct( String productMRP) {
		Waiting.explicitWaitVisibilityOfElement(listedItemsPrice.get(0), 30);
		String actualMRP=listedItemsPrice.get(0).getText();
		Assert.assertEquals(actualMRP, productMRP);
		
		return this;
	}
	@Step("verify sort by field")
	public ListPageObjects verifySortByText() {
		Waiting.explicitWaitVisibilityOfElement(sortByText, 30);
		Assert.assertTrue(sortByText.isDisplayed(),"Sort by text is not displayed");
		
		return this;
	}
	@Step("verify sort by options")
	public ListPageObjects verifySortByOptions(String sortByOptionsExp) throws InterruptedException {
		String[] expOptions=sortByOptionsExp.split(",");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(3000);
		sortByDropdown.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		for(int i=0;i<sortByOptions.size();i++)
		{
			
			if(sortByOptions.get(i).isDisplayed())
				{
					sortByOptions.get(i).click();
					Assert.assertEquals(sortByOptions.get(i).getAttribute("innerHTML").toString(), expOptions[i]);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					Thread.sleep(3500);
			}else
				{
					sortByDropdown.click();
					sortByOptions.get(i).click();
					Assert.assertEquals(sortByOptions.get(i).getAttribute("innerHTML").toString(), expOptions[i]);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					Thread.sleep(3500);
				}		
		}
		return this;
		}
	
	

}
