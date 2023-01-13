package io.github.sridharbandi;

import com.microsoft.playwright.Page;
import io.github.sridharbandi.a11y.HTMLCS;
import io.github.sridharbandi.util.A11y;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HtmlCsRunnerTest {

    @Mock
    Page page;
    @InjectMocks
    HtmlCsRunner htmlCsRunner = new HtmlCsRunner(page);
    @Mock
    A11y a11y;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        htmlCsRunner.setStandard(HTMLCS.WCAG2AA);
        String[] codes = {"Code1", "Code2"};
        htmlCsRunner.setIgnoreCodes(codes);
        Assertions.assertThrows(NullPointerException.class, () -> htmlCsRunner.setPageTile("").execute());
    }

}
