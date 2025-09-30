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
 * ケース12
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース12 受講生 勤怠直接編集 入力チェック")
public class Case12 {

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
	@DisplayName("テスト05 不適切な内容で修正してエラー表示：出退勤の（時）と（分）のいずれかが空白")
	void test05() {
		// TODO ここに追加
		final Select startHour = new Select(webDriver.findElement(By.id("startHour0")));
		startHour.selectByIndex(10);
		final Select startMinute = new Select(webDriver.findElement(By.id("startMinute0")));
		startMinute.selectByIndex(0);
		final Select endHour = new Select(webDriver.findElement(By.id("endHour0")));
		endHour.selectByIndex(19);
		final Select endMinute = new Select(webDriver.findElement(By.id("endMinute0")));
		endMinute.selectByIndex(1);
		scrollBy("600");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/form/div/input"));
		qa.click();
		Alert alert = webDriver.switchTo().alert();
		assertEquals("更新します。よろしいですか？", alert.getText());
		alert.accept();
		assertEquals("http://localhost:8080/lms/attendance/update", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正してエラー表示：出勤が空白で退勤に入力あり")
	void test06() {
		// TODO ここに追加
		final Select startHour = new Select(webDriver.findElement(By.id("startHour0")));
		startHour.selectByIndex(0);
		final Select startMinute = new Select(webDriver.findElement(By.id("startMinute0")));
		startMinute.selectByIndex(0);
		final Select endHour = new Select(webDriver.findElement(By.id("endHour0")));
		endHour.selectByIndex(19);
		final Select endMinute = new Select(webDriver.findElement(By.id("endMinute0")));
		endMinute.selectByIndex(1);
		scrollBy("600");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/form/div/input"));
		qa.click();
		Alert alert = webDriver.switchTo().alert();
		assertEquals("更新します。よろしいですか？", alert.getText());
		alert.accept();
		assertEquals("http://localhost:8080/lms/attendance/update", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正してエラー表示：出勤が退勤よりも遅い時間")
	void test07() {
		// TODO ここに追加
		final Select startHour = new Select(webDriver.findElement(By.id("startHour0")));
		startHour.selectByIndex(20);
		final Select startMinute = new Select(webDriver.findElement(By.id("startMinute0")));
		startMinute.selectByIndex(1);
		final Select endHour = new Select(webDriver.findElement(By.id("endHour0")));
		endHour.selectByIndex(19);
		final Select endMinute = new Select(webDriver.findElement(By.id("endMinute0")));
		endMinute.selectByIndex(1);
		scrollBy("600");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/form/div/input"));
		qa.click();
		Alert alert = webDriver.switchTo().alert();
		assertEquals("更新します。よろしいですか？", alert.getText());
		alert.accept();
		assertEquals("http://localhost:8080/lms/attendance/update", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正してエラー表示：出退勤時間を超える中抜け時間")
	void test08() {
		// TODO ここに追加
		final Select startHour = new Select(webDriver.findElement(By.id("startHour0")));
		startHour.selectByIndex(10);
		final Select startMinute = new Select(webDriver.findElement(By.id("startMinute0")));
		startMinute.selectByIndex(0);
		final Select endHour = new Select(webDriver.findElement(By.id("endHour0")));
		endHour.selectByIndex(11);
		final Select endMinute = new Select(webDriver.findElement(By.id("endMinute0")));
		endMinute.selectByIndex(1);
		final Select breakTime = new Select(webDriver.findElement(By.name("attendanceList[0].blankTime")));
		breakTime.selectByValue("90");
		scrollBy("600");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/form/div/input"));
		qa.click();
		Alert alert = webDriver.switchTo().alert();
		assertEquals("更新します。よろしいですか？", alert.getText());
		alert.accept();
		assertEquals("http://localhost:8080/lms/attendance/update", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正してエラー表示：備考が100文字超")
	void test09() {
		// TODO ここに追加
		final Select startHour = new Select(webDriver.findElement(By.id("startHour0")));
		startHour.selectByIndex(10);
		final Select startMinute = new Select(webDriver.findElement(By.id("startMinute0")));
		startMinute.selectByIndex(1);
		final Select endHour = new Select(webDriver.findElement(By.id("endHour0")));
		endHour.selectByIndex(19);
		final Select endMinute = new Select(webDriver.findElement(By.id("endMinute0")));
		endMinute.selectByIndex(1);
		final WebElement other = webDriver.findElement(By.name("attendanceList[0].note"));
		String str = "a".repeat(101);
		other.sendKeys(str);
		scrollBy("600");
		final WebElement qa = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/form/div/input"));
		qa.click();
		Alert alert = webDriver.switchTo().alert();
		assertEquals("更新します。よろしいですか？", alert.getText());
		alert.accept();
		assertEquals("http://localhost:8080/lms/attendance/update", webDriver.getCurrentUrl());
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
