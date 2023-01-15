package io.github.sridharbandi.pw;

import com.microsoft.playwright.Page;
import io.github.sridharbandi.pw.a11y.Engine;
import io.github.sridharbandi.pw.modal.htmlcs.Issues;
import io.github.sridharbandi.pw.util.A11y;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.Files.createDirectories;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class IRunnerTest {
    @Mock
    Page page;

    @InjectMocks
    HtmlCsRunner htmlCsRunner = new HtmlCsRunner(page);

    @Test
    public void testGenerateHtmlReport() throws IOException {
        Path source = Paths.get("./src/test/resources/test.json");
        Path target = Paths.get("./target/java-a11y/htmlcs/json/test.json");
        createDirectories(target.getParent());
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        A11y a11y = new A11y();
        htmlCsRunner.generateHtmlReport(a11y, Engine.HTMLCS, Issues.class);
        assertTrue(FileUtils.deleteQuietly(new File("./target/java-a11y/htmlcs/json/test.json")));
        assertTrue(FileUtils.deleteQuietly(new File("./target/java-a11y/htmlcs/html/id_javaa11y.html")));
        assertTrue(FileUtils.deleteQuietly(new File("./target/java-a11y/htmlcs/html/index.html")));
    }

}