## Accessibility Automation for Web Apps with Java and [Playwright](https://playwright.dev/).

### This project uses [HTML CodeSniffer](https://squizlabs.github.io/HTML_CodeSniffer/) and [Deque Axe](https://www.deque.com/)

**HTML CodeSniffer** : checks HTML source code and detects any Accessibility violations. Comes with standards that cover
the three (A, AA & AAA) conformance levels of the W3C's Web Content Accessibility Guidelines (WCAG) 2.1 and the U.S.
Section 508 legislation.

**Deque Axe** : World’s leading digital accessibility toolkit. Powerful and accurate accessibility toolkit can get you
to 80% issue coverage, or more, during development.

[![jdk badge](https://img.shields.io/badge/jdk-8-green.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/14bf5ccecfb74e7b8a1e4dda85241e32)](https://www.codacy.com/gh/automated-a11y/java-a11y-playwright/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=automated-a11y/java-a11y-playwright&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/14bf5ccecfb74e7b8a1e4dda85241e32)](https://www.codacy.com/gh/automated-a11y/java-a11y-playwright/dashboard?utm_source=github.com&utm_medium=referral&utm_content=automated-a11y/java-a11y-playwright&utm_campaign=Badge_Coverage)
[![License badge](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Contributer badge](https://img.shields.io/github/contributors/automated-a11y/java-a11y-playwright.svg)](https://github.com/automated-a11y/java-a11y-playwright/graphs/contributors)

### Features

1. Simple & Easy to use
2. No need of prior knowledge on Accessibility
3. Works with Java [Playwright](https://playwright.dev/)
4. Rich Reporting
5. Open source

### Getting Started

#### Using HTML CodeSniffer

Create object of `HtmlCsRunner` as below. `page` is instance of your single tab or a popup window within a browser
context.

```java
HtmlCsRunner htmlCsRunner=new HtmlCsRunner(page);
```

Once after you navigated to any page/popup with Playwright execute Accessibility on that particular page/popup

```java
htmlCsRunner.execute();
```

The above `execute` will also generate `JSON Report` on accessibility issues at page/popup level

Once after all the tests executed, you can call the below method to generate consolidated `HTML Report` on accessibility
issues

```java
htmlCsRunner.generateHtmlReport();
```

Below is junit example with reporting.

```java
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.sridharbandi.pw.HtmlCsRunner;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class HtmlcsTest {
    private static HtmlCsRunner htmlCsRunner;
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() throws IOException {
        playwright.close();
        htmlCsRunner.generateHtmlReport();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        htmlCsRunner = new HtmlCsRunner(page);
    }

    @AfterEach
    void closeContext() throws IOException {
        htmlCsRunner.execute();
        context.close();
    }

    @Test
    public void googleTest() {
        page.navigate("https://www.google.com/");
    }

    @Test
    public void stockTest() {
        page.navigate("https://www.istockphoto.com/");
    }
}
```

By default, it will check against `WCAG2AA` standards. However, you can configure it to standard you want to test with

```java
htmlCsRunner.setStandard(HTMLCS.WCAG2A);
```

HTML Reports will be generated under `./target/java-a11y/htmlcs` folder.

Below are the report screenshots

Consolidated Report

![Index](/readme/htmlcs_index.png)

Page Report

![Page](/readme/htmlcs_page.png)

#### Using Deque Axe

Create object of `AxeRunner` as below. `page` is instance of your single tab or a popup window within a browser context.

```java
AxeRunner axeRunner=new AxeRunner(page);
```

Once after you navigated to any page/popup with Playwright execute Accessibility on that particular page/popup

```java
axeRunner.execute();
```

The above `execute` will also generate `JSON Report` on accessibility issues at page/popup level

Once after all the tests executed, you can call the below method to generate consolidated `HTML Report` on accessibility
issues

```java
axeRunner.generateHtmlReport();
```

Below is junit example with reporting.

```java
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.sridharbandi.pw.AxeRunner;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class AxeTest {
    private static AxeRunner axeRunner;
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() throws IOException {
        playwright.close();
        axeRunner.generateHtmlReport();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        axeRunner = new AxeRunner(page);
    }

    @AfterEach
    void closeContext() throws IOException {
        axeRunner.execute();
        context.close();
    }

    @Test
    public void googleTest() {
        page.navigate("https://www.google.com/");
    }

    @Test
    public void stockTest() {
        page.navigate("https://www.istockphoto.com/");
    }
}
```

HTML Reports will be generated under `./target/java-a11y/axe` folder.

Below are the report screenshots

Consolidated Report

![Index](/readme/axe_index.png)

Page Report

![Page](/readme/axe_page.png)
