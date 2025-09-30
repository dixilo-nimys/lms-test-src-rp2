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
 * ケース09
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース09 受講生 レポート登録 入力チェック")
public class Case09 {

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
	@DisplayName("テスト03 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		final WebElement qa = webDriver.findElement(By.linkText("ようこそ受講生ＡＡ１さん"));
		qa.click();
		assertEquals("http://localhost:8080/lms/user/detail", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 該当レポートの「修正する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		scrollBy("600");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/table[3]/tbody/tr[2]/td[5]/form[2]/input[1]"));
		qa.click();
		pageLoadTimeout(10);
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しエラー表示：学習項目が未入力")
	void test05() {
		// TODO ここに追加
		pageLoadTimeout(10);
		final WebElement inFiled = webDriver.findElement(By.id("intFieldName_0"));
		inFiled.clear();
		final Select inFiledValue = new Select(webDriver.findElement(By.id("intFieldValue_0")));
		inFiledValue.selectByIndex(0); 
		inFiledValue.selectByValue("2"); 
		final WebElement content = webDriver.findElement(By.id("content_0"));
		content.clear();
		content.sendKeys("5");
		final WebElement other = webDriver.findElement(By.id("content_1"));
		other.clear();
		other.sendKeys("所感");
		final WebElement weeks = webDriver.findElement(By.id("content_2"));
		weeks.clear();
		weeks.sendKeys("一週間の振り返り");
		scrollBy("300");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/form/div[3]/fieldset/div/div/button"));
		qa.click();
		pageLoadTimeout(10);
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：理解度が未入力")
	void test06() {
		// TODO ここに追加
		pageLoadTimeout(10);
		final WebElement inFiled = webDriver.findElement(By.id("intFieldName_0"));
		inFiled.clear();
		inFiled.sendKeys("学習項目");
		final Select inFiledValue = new Select(webDriver.findElement(By.id("intFieldValue_0")));
		inFiledValue.selectByValue("");; 
		final WebElement content = webDriver.findElement(By.id("content_0"));
		content.clear();
		content.sendKeys("5");
		final WebElement other = webDriver.findElement(By.id("content_1"));
		other.clear();
		other.sendKeys("所感");
		final WebElement weeks = webDriver.findElement(By.id("content_2"));
		weeks.clear();
		weeks.sendKeys("一週間の振り返り");
		scrollBy("300");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/form/div[3]/fieldset/div/div/button"));
		qa.click();
		pageLoadTimeout(10);
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が数値以外")
	void test07() {
		// TODO ここに追加
		pageLoadTimeout(10);
		final WebElement inFiled = webDriver.findElement(By.id("intFieldName_0"));
		inFiled.clear();
		inFiled.sendKeys("学習項目");
		final Select inFiledValue = new Select(webDriver.findElement(By.id("intFieldValue_0")));
		inFiledValue.selectByIndex(0); 
		inFiledValue.selectByValue("2"); 
		final WebElement content = webDriver.findElement(By.id("content_0"));
		content.clear();
		content.sendKeys("数値以外だとエラーになります");
		final WebElement other = webDriver.findElement(By.id("content_1"));
		other.clear();
		other.sendKeys("所感");
		final WebElement weeks = webDriver.findElement(By.id("content_2"));
		weeks.clear();
		weeks.sendKeys("一週間の振り返り");
		scrollBy("300");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/form/div[3]/fieldset/div/div/button"));
		qa.click();
		pageLoadTimeout(10);
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が範囲外")
	void test08() {
		// TODO ここに追加
		pageLoadTimeout(10);
		final WebElement inFiled = webDriver.findElement(By.id("intFieldName_0"));
		inFiled.clear();
		inFiled.sendKeys("学習項目");
		final Select inFiledValue = new Select(webDriver.findElement(By.id("intFieldValue_0")));
		inFiledValue.selectByIndex(0); 
		inFiledValue.selectByValue("2"); 
		final WebElement content = webDriver.findElement(By.id("content_0"));
		content.clear();
		content.sendKeys("11");
		final WebElement other = webDriver.findElement(By.id("content_1"));
		other.clear();
		other.sendKeys("所感");
		final WebElement weeks = webDriver.findElement(By.id("content_2"));
		weeks.clear();
		weeks.sendKeys("一週間の振り返り");
		scrollBy("300");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/form/div[3]/fieldset/div/div/button"));
		qa.click();
		pageLoadTimeout(10);
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度・所感が未入力")
	void test09() {
		// TODO ここに追加
		pageLoadTimeout(10);
		final WebElement inFiled = webDriver.findElement(By.id("intFieldName_0"));
		inFiled.clear();
		inFiled.sendKeys("学習項目");
		final Select inFiledValue = new Select(webDriver.findElement(By.id("intFieldValue_0")));
		inFiledValue.selectByIndex(0); 
		inFiledValue.selectByValue("2"); 
		final WebElement content = webDriver.findElement(By.id("content_0"));
		content.clear();
		final WebElement other = webDriver.findElement(By.id("content_1"));
		other.clear();
		final WebElement weeks = webDriver.findElement(By.id("content_2"));
		weeks.clear();
		weeks.sendKeys("一週間の振り返り");
		scrollBy("300");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/form/div[3]/fieldset/div/div/button"));
		qa.click();
		pageLoadTimeout(10);
		scrollBy("300");
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(10)
	@DisplayName("テスト10 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：所感・一週間の振り返りが2000文字超")
	void test10() {
		// TODO ここに追加
		pageLoadTimeout(10);
		final WebElement inFiled = webDriver.findElement(By.id("intFieldName_0"));
		inFiled.clear();
		inFiled.sendKeys("学習項目");
		final Select inFiledValue = new Select(webDriver.findElement(By.id("intFieldValue_0")));
		inFiledValue.selectByIndex(0); 
		inFiledValue.selectByValue("2"); 
		final WebElement content = webDriver.findElement(By.id("content_0"));
		content.clear();
		content.sendKeys("5");
		final WebElement other = webDriver.findElement(By.id("content_1"));
		other.clear();
		String str = "a".repeat(2001);
		other.sendKeys(str);
		final WebElement weeks = webDriver.findElement(By.id("content_2"));
		weeks.clear();
		String str2 = "a".repeat(2001);
		weeks.sendKeys(str2);
		scrollBy("300");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/form/div[3]/fieldset/div/div/button"));
		qa.click();
		pageLoadTimeout(10);
		scrollBy("300");
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
