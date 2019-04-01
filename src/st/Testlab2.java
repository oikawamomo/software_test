package test;

import java.io.IOException;
import java.nio.charset.Charset;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import com.csvreader.CsvReader;

public class Testlab2 {

	public static void main(String[] args) throws IOException {
		CsvReader r = new CsvReader("F://three2//软件测试//lab2.csv", ',', Charset.forName("UTF-8"));
		// 生成CsvReader对象，以，为分隔符
		//String infoPath = System.getProperty("user.dir") + "/src/sr/records.csv";
		//CsvReader r = new CsvReader(infoPath, ',', Charset.forName("UTF-8"));
		// 读取表头
		r.readHeaders();
		r.readHeaders();
		String[] head = r.getHeaders();
		
		// 逐条读取记录，直至读完
		String finall1="";
		while (r.readRecord()) {
			// 读取一条记录
			// 按列名读取这条记录的值
			String number_csv = r.get(head[1]);//学号
			String name_csv = r.get(head[2]);//姓名
			String address_csv = r.get(head[3]);//git地址
//			System.out.println(number_csv + " " + name_csv + " " + address_csv);
			String pwd_csv = number_csv.substring(number_csv.length() - 6, number_csv.length());

			// 打开火狐浏览器
			String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";System.setProperty("webdriver.gecko.driver", driverPath);
			System.setProperty("webdriver.gecko.driver", driverPath);
			WebDriver driver = new FirefoxDriver();
//			// 访问给定网址
			driver.get("http://121.193.130.195:8800//");
			driver.manage().window().maximize();
			// 输入用户名
			WebElement input_name = driver.findElement(By.name("id"));
			input_name.clear();
			input_name.sendKeys(number_csv);
			// 输入密码
			WebElement input_pwd = driver.findElement(By.name("password"));
			input_pwd.clear();
			input_pwd.sendKeys(pwd_csv);
			// 点击登录按钮
			WebElement btn = driver.findElement(By.id("btn_login"));
			btn.click();
			// 登录成功之后，获得当前页面的用户信息
			String name_web = driver.findElement(By.id("student-name")).getText();
			String number_web = driver.findElement(By.id("student-id")).getText();
			String address_web = driver.findElement(By.id("student-git")).getText();
			// 比较查询信息
			if (number_csv.equals(number_web) && address_csv.equals(address_web)) {
				finall1+=number_csv+"用户信息一致,";
			} else {
				finall1=number_csv + "的信息不一致，";
			}
			driver.close();
		}
		System.out.println(finall1);
		r.close();

	}

}