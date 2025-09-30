package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		goTo("http://localhost:8080/lms/");
		assertEquals("http://localhost:8080/lms/", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		final WebElement id = webDriver.findElement(By.name("loginId"));
		id.clear();
		id.sendKeys("StudentAA01");
		final WebElement pass = webDriver.findElement(By.name("password"));
		pass.clear();
		pass.sendKeys("StudentAA0123");
		final WebElement login = webDriver
				.findElement(By.xpath("//*[@id=\"main\"]/div[1]/form/fieldset/div[3]/div/input"));
		login.click();
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/form/input[3]"));
		qa.click();
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		scrollBy("200");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"sectionDetail\"]/table[2]/tbody/tr[3]/td/form/input[6]"));
		qa.click();
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// TODO ここに追加
		final WebElement inFiled = webDriver.findElement(By.id("intFieldName_0"));
		inFiled.clear();
		inFiled.sendKeys("項目");
		final Select inFiledValue = new Select(webDriver.findElement(By.id("intFieldValue_0")));
		inFiledValue.selectByIndex(0); 
		inFiledValue.selectByValue("2"); 
		final WebElement content = webDriver.findElement(By.id("content_0"));
		content.clear();
		content.sendKeys("5");
		final WebElement other = webDriver.findElement(By.id("content_1"));
		other.clear();
		other.sendKeys("所感");
		final WebElement report = webDriver.findElement(By.id("content_2"));
		report.clear();
		report.sendKeys("修正済み週報。①や｜、｛｝などの特殊記号も使用可能です");
		scrollBy("300");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/form/div[3]/fieldset/div/div/button"));
		qa.click();
		assertEquals("http://localhost:8080/lms/section/detail?sectionId=2", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// TODO ここに追加
		final WebElement qa = webDriver.findElement(By.linkText("ようこそ受講生ＡＡ１さん"));
		qa.click();
		assertEquals("http://localhost:8080/lms/user/detail", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		// TODO ここに追加
		scrollBy("600");
	final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/table[3]/tbody/tr[2]/td[5]/form[1]/input[1]"));
	qa.click();
	assertEquals("レポート詳細 | LMS", webDriver.getTitle());
	WebDriverUtils.getEvidence(new Object() {
	});
	}

}
