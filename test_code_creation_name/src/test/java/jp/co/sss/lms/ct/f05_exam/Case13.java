package jp.co.sss.lms.ct.f05_exam;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト 試験実施機能
 * ケース13
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース13 受講生 試験の実施 結果0点")
public class Case13 {

	/** テスト07およびテスト08 試験実施日時 */
	static Date date;

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
	@DisplayName("テスト03 「試験有」の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		WebDriverUtils.visibilityTimeout(By.xpath("//*[@id=\"main\"]/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/form/input[3]"),60);
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/form/input[3]"));
		qa.click();
		assertEquals("http://localhost:8080/lms/section/detail",webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「本日の試験」エリアの「詳細」ボタンを押下し試験開始画面に遷移")
	void test04() {
		// TODO ここに追加
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"sectionDetail\"]/table[1]/tbody/tr[2]/td[2]/form/input[1]"));
		qa.click();
		assertEquals("試験【ITリテラシー①】 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 「試験を開始する」ボタンを押下し試験問題画面に遷移")
	void test05() {
		// TODO ここに追加
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/form/input[4]"));
		qa.click();
		assertEquals("ITリテラシー① | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 未回答の状態で「確認画面へ進む」ボタンを押下し試験回答確認画面に遷移")
	void test06() {
		// TODO ここに追加
		scrollBy("5000");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"examQuestionForm\"]/div[13]/fieldset/input"));
		qa.click();
		assertEquals("http://localhost:8080/lms/exam/answerCheck", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 「回答を送信する」ボタンを押下し試験結果画面に遷移")
	void test07() throws InterruptedException {
		// TODO ここに追加
		scrollBy("2000");
		final WebElement qa = webDriver.findElement(By.id("sendButton"));
		qa.click();
		Alert alert = webDriver.switchTo().alert();
		assertEquals("回答を送信します。よろしいですか？", alert.getText());
		alert.accept();
		assertEquals("http://localhost:8080/lms/exam/result", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 「戻る」ボタンを押下し試験開始画面に遷移後当該試験の結果が反映される")
	void test08() {
		// TODO ここに追加
		scrollBy("5000");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"examBeing\"]/div[13]/fieldset/form/input[1]"));
		qa.click();
		assertEquals("http://localhost:8080/lms/exam/start", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
