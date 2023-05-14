import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Trello {
	
	public static void main(String args[]) throws InterruptedException {
		
		String emailID = "haroonanamta@gmail.com";
		String password = "Anamta@123";
		String BoardName = "TestBoardA";
		String ListA = "List A";
		String ListB = "List B";
		String cardname = "Card A";
		
		System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
		Actions Ac = new Actions(driver);
		driver.get("https://trello.com/home");
		driver.manage().window().maximize();
		
		//click on login in header
		driver.findElement(By.xpath("//a[@class='Buttonsstyles__Button-sc-1jwidxo-0 kTwZBr']")).click();
		Thread.sleep(2000);
		
		//enter email and click on continue button
		WebElement EmailInput = driver.findElement(By.id("user"));
		EmailInput.sendKeys(emailID);
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);
		
		//enter password and log into Trello
		WebElement PasswordInput = driver.findElement(By.id("password"));
		PasswordInput.sendKeys(password);
		driver.findElement(By.id("login-submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Create board or Workspace']")));
		//click on create button
		driver.findElement(By.xpath("//button[@aria-label='Create board or Workspace']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='yTThzZMDkelhke']")));

		
		//click on Create Board button
		driver.findElement(By.xpath("//button[@class='yTThzZMDkelhke']")).click();
		Thread.sleep(5000);
		
		//enter a board name
		WebElement boardName = driver.findElement(By.xpath("//input[@type='text']"));
		boardName.sendKeys(BoardName);
		driver.findElement(By.xpath("//button[@data-testid=\"create-board-submit-button\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='open-add-list js-open-add-list']")));
		
		//add list A
		Ac.moveByOffset(0, 100).click().build().perform();
		driver.findElement(By.xpath("//a[@class='open-add-list js-open-add-list']")).click();
		Thread.sleep(1000);
		WebElement ListNameA = driver.findElement(By.xpath("//input[@class='list-name-input']"));
		ListNameA.sendKeys(ListA);
		
		driver.findElement(By.xpath("//input[@value=\"Add list\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='open-add-list js-open-add-list']")));
		
		//add list B
		Ac.moveByOffset(0, 125).click().build().perform();
		driver.findElement(By.xpath("//a[@class='open-add-list js-open-add-list']")).click();
		Thread.sleep(1000);
		WebElement ListNameB = driver.findElement(By.xpath("//input[@class='list-name-input']"));
		ListNameB.sendKeys(ListB);
		
		driver.findElement(By.xpath("//input[@value=\"Add list\"]")).click();
		
		//add a card
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(text(),'Add a card')])")));
		Ac.moveByOffset(0, 0).click().build().perform();
		driver.findElement(By.xpath("(//span[contains(text(),'Add a card')])")).click();
		
		//add card name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Enter a title for this card…']")));
		WebElement CardName = driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']"));
		CardName.sendKeys(cardname);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Add card']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel']")).click();
		
		//drag and drop
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='list-card-details js-card-details']")));
		WebElement draggedFrom = driver.findElement(By.xpath("//div[@class='list-card-details js-card-details']"));
		WebElement draggedTo = driver.findElement(By.xpath("(//div[@class='js-list list-wrapper list-wrapper-with-margins'])[2]"));
		Ac.dragAndDrop(draggedFrom, draggedTo).build().perform();
		
		//get x and y location
		WebElement cardCreated = driver.findElement(By.xpath("//div[@class='list-card-details js-card-details']"));
		long xLoc = cardCreated.getLocation().getX();
		long yLoc = cardCreated.getLocation().getY();
		System.out.println("X-Location = "+xLoc+"\nY-Location = "+yLoc);
		Thread.sleep(2000);
		
		//logout
		driver.findElement(By.xpath("//button[@aria-label=\"Open member menu\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@data-testid=\"account-menu-logout\"]")).click();
		
		//close driver
		driver.close();
	}
	
}
