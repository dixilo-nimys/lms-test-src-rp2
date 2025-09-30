package jp.co.sss.lms.ct.f04_attendance;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

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
import org.openqa.selenium.support.ui.Select;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト 勤怠管理機能
 * ケース11
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース11 受講生 勤怠直接編集 正常系")
public class Case11 {

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
	@DisplayName("テスト03 上部メニューの「勤怠」リンクから勤怠管理画面に遷移")
	void test03() {
		// TODO ここに追加
		final WebElement qa = webDriver.findElement(By.linkText("勤怠"));
		qa.click();
		Alert alert = webDriver.switchTo().alert();
		assertEquals("過去日の勤怠に未入力があります。", alert.getText());
		alert.accept();
		assertEquals("http://localhost:8080/lms/attendance/detail", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「勤怠情報を直接編集する」リンクから勤怠情報直接変更画面に遷移")
	void test04() {
		// TODO ここに追加
		final WebElement qa = webDriver.findElement(By.linkText("勤怠情報を直接編集する"));
		qa.click();
		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 すべての研修日程の勤怠情報を正しく更新し勤怠管理画面に遷移")
	void test05() {
		// TODO ここに追加
		for(int i = 0;i <= 20;i++) {
			final Select startHour = new Select(webDriver.findElement(By.id("startHour" + i)));
			startHour.selectByIndex(10);
			final Select startMinute = new Select(webDriver.findElement(By.id("startMinute" + i)));
			startMinute.selectByIndex(1);
			final Select endHour = new Select(webDriver.findElement(By.id("endHour" + i)));
			endHour.selectByIndex(19);
			final Select endMinute = new Select(webDriver.findElement(By.id("endMinute" + i)));
			endMinute.selectByIndex(1);
		}
		scrollBy("200");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/form/div/input"));
		qa.click();
		Alert alert = webDriver.switchTo().alert();
		assertEquals("更新します。よろしいですか？", alert.getText());
		alert.accept();
		assertEquals("勤怠情報変更｜LMS", webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
