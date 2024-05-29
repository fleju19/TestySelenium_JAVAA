import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.CartPage;
import org.example.CheckoutPage;
import org.example.InventoryPage;
import org.example.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testLogin() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isLoggedIn());
    }

    @Test
    public void testAddItemsToCartAndRemoveBackpackAndBoltTShirt() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        inventoryPage.addBackpackToCart();
        inventoryPage.addBoltTShirtToCart();
        inventoryPage.goToCart();

        Assert.assertTrue(cartPage.isBackpackInCart());
        Assert.assertTrue(cartPage.isBoltTShirtInCart());

        cartPage.removeBackpackFromCart();
        cartPage.removeBoltTShirtFromCart();

        Assert.assertFalse(cartPage.isBackpackInCart());
        Assert.assertFalse(cartPage.isBoltTShirtInCart());
    }

    @Test
    public void testAddItemsToCart() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        inventoryPage.addBackpackToCart();
        inventoryPage.addBoltTShirtToCart();
        inventoryPage.goToCart();

        Assert.assertTrue(cartPage.isBackpackInCart());
        Assert.assertTrue(cartPage.isBoltTShirtInCart());
    }

    @Test
    public void testCheckout() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        inventoryPage.addBackpackToCart();
        inventoryPage.addBoltTShirtToCart();
        inventoryPage.goToCart();
        cartPage.clickCheckout();

        checkoutPage.enterFirstName("Jan");
        checkoutPage.enterLastName("Kowalski");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(checkoutPage.isOrderComplete());
    }

    @Test
    public void testAddOnesieAndBikeLightToCart() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        inventoryPage.addOnesieToCart();
        inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isOnesieInCart());

        driver.navigate().back();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();

        Assert.assertTrue(cartPage.isBikeLightInCart());
        Assert.assertTrue(cartPage.isOnesieInCart());

        cartPage.clickCheckout();

        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(checkoutPage.isOrderComplete());
    }


}
