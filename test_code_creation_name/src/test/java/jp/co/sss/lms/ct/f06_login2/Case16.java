package jp.co.sss.lms.ct.f06_login2;

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

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト ログイン機能②
 * ケース16
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース16 受講生 初回ログイン 変更パスワード未入力")
public class Case16 {

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
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
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
		pageLoadTimeout(10);
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックを入れ「次へ」ボタン押下")
	void test03() {
		// TODO ここに追加
		scrollBy("200");
		final WebElement flg = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/form/fieldset/div[1]/div/label/input[1]"));
		flg.click();
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/form/fieldset/div[2]/button"));
		qa.click();
		pageLoadTimeout(10);
		assertEquals("http://localhost:8080/lms/password/changePassword", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 パスワードを未入力で「変更」ボタン押下")
	void test04() {
		// TODO ここに追加
		final WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		currentPassword.clear();
		final WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		passwordConfirm.clear();
		scrollBy("200");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"upd-form\"]/div[1]/fieldset/div[4]/div/button[2]"));
		qa.click();
		visibilityTimeout(By.id("upd-btn"),10);
		final WebElement upb = webDriver.findElement(By.id("upd-btn"));
		upb.click();
		pageLoadTimeout(10);
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 20文字以上の変更パスワードを入力し「変更」ボタン押下")
	void test05() {
		// TODO ここに追加
		final WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		currentPassword.clear();
		currentPassword.sendKeys("StudentAA0123");
		final WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("Aaaaaabbbbb1111122222");
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		passwordConfirm.clear();
		passwordConfirm.sendKeys("Aaaaaabbbbb1111122222");
		scrollBy("200");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"upd-form\"]/div[1]/fieldset/div[4]/div/button[2]"));
		qa.click();
		visibilityTimeout(By.id("upd-btn"),10);
		final WebElement upb = webDriver.findElement(By.id("upd-btn"));
		upb.click();
		pageLoadTimeout(10);
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 ポリシーに合わない変更パスワードを入力し「変更」ボタン押下")
	void test06() {
		// TODO ここに追加 
		final WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		currentPassword.clear();
		currentPassword.sendKeys("StudentAA0123");
		final WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("aaaaaaaa");
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		passwordConfirm.clear();
		passwordConfirm.sendKeys("aaaaaaaa");
		scrollBy("200");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"upd-form\"]/div[1]/fieldset/div[4]/div/button[2]"));
		qa.click();
		visibilityTimeout(By.id("upd-btn"),10);
		final WebElement upb = webDriver.findElement(By.id("upd-btn"));
		upb.click();
		pageLoadTimeout(10);
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 一致しない確認パスワードを入力し「変更」ボタン押下")
	void test07() {
		// TODO ここに追加
		final WebElement currentPassword = webDriver.findElement(By.id("currentPassword"));
		currentPassword.clear();
		currentPassword.sendKeys("StudentAA0123");
		final WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("StudentAA01");
		final WebElement passwordConfirm = webDriver.findElement(By.id("passwordConfirm"));
		passwordConfirm.clear();
		passwordConfirm.sendKeys("StudentAA");
		scrollBy("200");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"upd-form\"]/div[1]/fieldset/div[4]/div/button[2]"));
		qa.click();
		visibilityTimeout(By.id("upd-btn"),10);
		final WebElement upb = webDriver.findElement(By.id("upd-btn"));
		upb.click();
		pageLoadTimeout(10);
		assertEquals("パスワード変更 | LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
