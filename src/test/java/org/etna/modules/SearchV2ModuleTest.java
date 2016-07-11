package org.etna.modules;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import org.etna.dataprovider.SearchData;
import org.etna.maincontroller.PageFactoryInitializer;

public class SearchV2ModuleTest extends PageFactoryInitializer {
	
	LoginModuleTest loginModule = new LoginModuleTest();
	@Features("Search V2")
	@Description("This is a test case which verifies exact matching of the search keyword for part number.")
	@Test(groups={"regression"},dataProvider="SearchV2KeywordExactMatching",dataProviderClass=SearchData.class)
	@TestCaseId("{0}")
	public void keywords_exactMatching_PartNumber(String testCaseId,@Parameter("Search Keyword Part Number") String searchKeyword) throws Exception
	{
		loginModule.loginAsASuperUser();
		homePage()
		.searchText(searchKeyword)
		.clickOnSearch()
		.productDetailsPage()
		.verifyPartNumberInProductDetailsPage(searchKeyword);
	}
	
	@Features("Search V2")
	@Description("This is a test case which verifies exact matching of the search keyword for manufacturer part number.")
	@Test(groups={"regression"},dataProvider="SearchV2KeywordExactMatching",dataProviderClass=SearchData.class)
	@TestCaseId("{0}")
	public void keywords_exactMatching_MPN(String testCaseId,@Parameter("Search Keyword MPN") String searchKeyword) throws Exception
	{
		loginModule.loginAsASuperUser();
		homePage()
		.searchText(searchKeyword)
		.clickOnSearch()
		.productDetailsPage()
		.verifyManufacturerPartNumberInProductDetailsPage(searchKeyword);
	}

	@Features("Search V2")
	@Description("This is a test case which verifies exact matching of the search keyword for UPC.")
	@Test(groups={"regression"},dataProvider="SearchV2KeywordExactMatching",dataProviderClass=SearchData.class)
	@TestCaseId("{0}")
	public void keywords_exactMatching_UPC(String testCaseId,@Parameter("Search Keyword UPC") String searchKeyword) throws Exception
	{
		loginModule.loginAsASuperUser();
		homePage()
		.searchText(searchKeyword)
		.clickOnSearch()
		.productDetailsPage()
		.verifyUPCInProductDetailsPage(searchKeyword);
	}
	
	@Features("Search V2")
	@Description("This is a test case which verifies exact matching of the search keyword for brand name or MPN when given together.")
	@Test(groups={"regression"},dataProvider="SearchV2KeywordExactMatchingAbcOrXyz",dataProviderClass=SearchData.class)
	@TestCaseId("{0}")
	public synchronized void searchV2_Keyword_ExactMatching_BrandName_Or_MPN(String testCaseId,@Parameter("Brand Name Or MPN") String searchKeyword) throws Exception
	{
		loginModule.loginAsASuperUser();
		homePage()
		.searchText(searchKeyword)
		.clickOnSearch()
		.productListPage()
		.verifyBrandNameOrMPNProductListPage(searchKeyword);
	}
	
	@Features("Search V2")
	@Description("This is a test case which verifies exact matching of the search keyword for Part Number or Manufacturer Part Number.")
	@Test(groups={"regression"},dataProvider="SearchV2KeywordExactMatchingAbcOrXyz",dataProviderClass=SearchData.class)
	@TestCaseId("{0}")
	public void searchV2_Keyword_ExactMatching_PartNumber_Or_MPN(String testCaseId,@Parameter("Part Number Or MPN") String searchKeyword) throws Exception
	{
		loginModule.loginAsASuperUser();
		homePage()
		.searchText(searchKeyword)
		.clickOnSearch()
		.productListPage()
		.verifyPartNumberOrMPNProductListPage(searchKeyword);
	}
	
	
	@Features("Search V2")
	@Description("This is a test case which verifies exact matching of the search keyword for Brand Name or Manufacturer Part Number.")
	@Test(groups={"regression"},dataProvider="SearchV2KeywordExactMatchingAbcOrXyz",dataProviderClass=SearchData.class)
	@TestCaseId("{0}")
	public synchronized void searchV2_Keyword_ExactMatching_BrandName_Or_PartNumber(String testCaseId,@Parameter("Brand Name Or Part Number") String searchKeyword) throws Exception
	{
		loginModule.loginAsASuperUser();
		homePage()
		.searchText(searchKeyword)
		.clickOnSearch()
		.productListPage()
		.verifyBrandNameOrPartNumberProductListPage(searchKeyword);
	}
	
	@Features("Search V2")
	@Description("This is a test case which verifies exact matching of the search keyword for Brand Name or UPC.")
	@Test(groups={"regression"},dataProvider="SearchV2KeywordExactMatchingAbcOrXyz",dataProviderClass=SearchData.class)
	@TestCaseId("{0}")
	public void searchV2_Keyword_ExactMatching_BrandName_Or_UPC(String testCaseId,@Parameter("Brand Name Or UPC") String searchKeyword) throws Exception
	{
		loginModule.loginAsASuperUser();
		homePage()
		.searchText(searchKeyword)
		.clickOnSearch()
		.productListPage()
		.verifyBrandNameOrUPCProductListPage(searchKeyword);
	}
	
	@Features("Search V2")
	@Description("This is a test case which verifies exact matching of the search keyword for Brand Name or UPC.")
	@Test(groups={"regression"},dataProvider="SearchV2KeywordExactMatchingAbcOrXyz",dataProviderClass=SearchData.class)
	@TestCaseId("{0}")
	public void searchV2_Keyword_ExactMatching_PartNumber_Or_UPC(String testCaseId,@Parameter("Part Number Or UPC") String searchKeyword) throws Exception
	{
		loginModule.loginAsASuperUser();
		homePage()
		.searchText(searchKeyword)
		.clickOnSearch()
		.productListPage()
		.verifyPartNumberOrUPCProductListPage(searchKeyword);
	}
}

