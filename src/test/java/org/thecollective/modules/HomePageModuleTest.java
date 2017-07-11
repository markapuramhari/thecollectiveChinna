package org.thecollective.modules;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.thecollective.dataprovider.DataDrivenTestingFromExcel;
import org.thecollective.maincontroller.PageFactoryInitializer;
import org.thecollective.utils.ApplicationSetUpPropertyFile;
import org.thecollective.utils.SearchDataPropertyFile;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;

public class HomePageModuleTest extends PageFactoryInitializer{

	SearchDataPropertyFile data = new SearchDataPropertyFile();
	ApplicationSetUpPropertyFile setUp = new ApplicationSetUpPropertyFile();
	
	@TestCaseId("TC_HomePage_001")
	@Features("Homepage Module")
	@Description("this test case verifies all the header links")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyHomePageHeadersBeforeLogin() throws Exception
	  {
		homePage()
		.verifyHomePage();
	  } 
	@TestCaseId("TC_HomePage_002")
	@Features("Homepage Module")
	@Description("this test case verifies all thestore branches")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyTheCollectiveStores() throws Exception
	  {
		homePage()
		.verifyStoresLink()
		.clickOnStoreLink()
		.storesPage()
		.verifyPageTitle()
		.verifyAvailableStores(data.getTheCollectiveBranches());
		
		
	  } 
	@TestCaseId("TC_HomePage_003")
	@Features("Homepage Module")
	@Description("this test case verifies all the footer links")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyHomePageFooters() throws Exception
	  {
		homePage()
		.clickOnMoreInfoLink()
		.verifyFooterHeaders(data.getExpFooterHeaders())
		.verifyFooterLinks(data.getFooterLinks());
	  } 
	@TestCaseId("TC_HomePage_004")
	@Features("Homepage Module")
	@Description("this test case verifies all the footer links")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyFooterLinksFunctionality() throws Exception
	  {
		homePage()
		.clickOnMoreInfoLink()
		.verifyFooterHeaders(data.getExpFooterHeaders())
		.verifyFooterLinks(data.getFooterLinks())
		.clickOnEachLink();
	  } 
	@TestCaseId("TC_HomePage_005")
	@Features("Homepage Module")
	@Description("this test case verifies all the header links after login")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyHomePageAfterLogin() throws Exception
	  {
		homePage()
		.clickOnLoginLink()
		.loginPage()
		.enterUserName(data.getUserName())
		.enterPassword(data.getPassword())
		.clickOnLoginButton()
		.homePage()
		.verifyUserProfile(data.getMyAccountPageTitle(),data.getProductName());
		
	  } 
	@TestCaseId("TC_HomePage_006")
	@Features("Homepage Module")
	@Description("this test case verifies all the header links after login")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyHomePageFooterLinks() throws Exception
	  {
		homePage()
		.megaNestedLinks();
		homePage()
		.navigateToEachCategory()
		.listPage()
		.verifyListedProducts();
	  } 
	@TestCaseId("TC_HomePage_007")
	@Features("Homepage Module")
	@Description("this test case verifies all the men accessories category ")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyMensAccessoriesLinks() throws Exception
	  {
		
		homePage()
		.navigateToMenAccessoriesCategory();
		
	  }
	@TestCaseId("TC_HomePage_008")
	@Features("Homepage Module")
	@Test(groups={"HomePageModule","regression"},dataProvider="mutipleSheetsSingleWorkbook",dataProviderClass=DataDrivenTestingFromExcel.class)
	  public void verifyContentsOfHeaderLinks(String testCaseId,String specificHeaderLink,String specifiedNestedLink,String breadCrumb,String contentLocator,String expectedContent) throws Exception{
		homePage()
		.clickOnSpecificSubDivisionLinkUnderDivisionsSectionInHeader(specificHeaderLink,specifiedNestedLink)
		
		.homePage()
		.verifyContent(specificHeaderLink,contentLocator,expectedContent);
	}
	@TestCaseId("TC_HomePage_009")
	@Features("Homepage Module")
	@Description("this test case verifies the search functinaloty")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifySearchFunctionality() throws Exception
	  {
		homePage()
		.clickOnSearchIcon()
		.enterSearchData()
		.listPage()
		.verifySearchResultsPage()
		.verifyPageTitle();
		
	  	}
	@TestCaseId("TC_HomePage_010")
	@Features("Homepage Module")
	@Description("this test case verifies the invalid search functinaloty")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyInvalidSearchFunctionality() throws Exception
		  {
			homePage()
			.clickOnSearchIcon()
			.enterSearchData()
			.listPage()
			.verifyInvalidSearchResultsPage()
			.verifyPageTitle();
			  }
	@TestCaseId("TC_HomePage_011")
	@Features("Homepage Module")
	@Description("this test case verifies the wish list icon")
	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyWishListIcon() throws Exception
		  {
			homePage()
			.verifyWishListIcon();
			
			
		  }
	@TestCaseId("TC_HomePage_012")
	@Features("Homepage Module")
	@Description("this test case verifies the wish list functionalities")

	@Test(groups={"HomePageModule","smoke","regression"})
	  public void verifyWishListFunctionality() throws Exception
		  {
			homePage()
			.verifyWishListIcon()
			.clickOnLoginLink()
			.loginPage()
			.enterUserName(data.getUserName())
			.enterPassword(data.getPassword())
			.clickOnLoginButton()
			.homePage()
			.verifyWishListIcon()
			.clickOnWishListIcon()
			.myAccountPage()
			.verifySavedItemsPage();
			
		  }
	
	/*@BeforeTest
	public void beforetest1(){
		System.out.println("beforetest1 execution");
			}
	@BeforeTest
	public void beforetest2(){
		System.out.println("beforetest2 execution");
			}
	@AfterTest
	public void aftertest(){
		System.out.println("aftertest execution");
			}
	@BeforeMethod
	public void BeforeMethod(){
		System.out.println("BeforeMethod execution");
			}
	@AfterMethod
	public void AfterMethod(){
		System.out.println("AfterMethod execution");
			}
	@BeforeClass
	public void BeforeClass1(){
		System.out.println("BeforeClass1 execution");
			}
	@BeforeClass
	public void BeforeClass2(){
		System.out.println("BeforeClass2 execution");
			}
	@AfterClass
	public void AfterClass(){
		System.out.println("AfterClass execution");
			}
	
	@Test
	public void test1(){
		System.out.println("test1 execution");
			}
	@Test
	public void test2(){
		System.out.println("test2 execution");

			}*/
	

	
	
}