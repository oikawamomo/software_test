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
		CsvReader r = new CsvReader("F://three2//�������//lab2.csv", ',', Charset.forName("UTF-8"));
		// ����CsvReader�����ԣ�Ϊ�ָ���
		//String infoPath = System.getProperty("user.dir") + "/src/sr/records.csv";
		//CsvReader r = new CsvReader(infoPath, ',', Charset.forName("UTF-8"));
		// ��ȡ��ͷ
		r.readHeaders();
		r.readHeaders();
		String[] head = r.getHeaders();
		
		// ������ȡ��¼��ֱ������
		String finall1="";
		while (r.readRecord()) {
			// ��ȡһ����¼
			// ��������ȡ������¼��ֵ
			String number_csv = r.get(head[1]);//ѧ��
			String name_csv = r.get(head[2]);//����
			String address_csv = r.get(head[3]);//git��ַ
//			System.out.println(number_csv + " " + name_csv + " " + address_csv);
			String pwd_csv = number_csv.substring(number_csv.length() - 6, number_csv.length());

			// �򿪻�������
			String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";System.setProperty("webdriver.gecko.driver", driverPath);
			System.setProperty("webdriver.gecko.driver", driverPath);
			WebDriver driver = new FirefoxDriver();
//			// ���ʸ�����ַ
			driver.get("http://121.193.130.195:8800//");
			driver.manage().window().maximize();
			// �����û���
			WebElement input_name = driver.findElement(By.name("id"));
			input_name.clear();
			input_name.sendKeys(number_csv);
			// ��������
			WebElement input_pwd = driver.findElement(By.name("password"));
			input_pwd.clear();
			input_pwd.sendKeys(pwd_csv);
			// �����¼��ť
			WebElement btn = driver.findElement(By.id("btn_login"));
			btn.click();
			// ��¼�ɹ�֮�󣬻�õ�ǰҳ����û���Ϣ
			String name_web = driver.findElement(By.id("student-name")).getText();
			String number_web = driver.findElement(By.id("student-id")).getText();
			String address_web = driver.findElement(By.id("student-git")).getText();
			// �Ƚϲ�ѯ��Ϣ
			if (number_csv.equals(number_web) && address_csv.equals(address_web)) {
				finall1+=number_csv+"�û���Ϣһ��,";
			} else {
				finall1=number_csv + "����Ϣ��һ�£�";
			}
			driver.close();
		}
		System.out.println(finall1);
		r.close();

	}

}