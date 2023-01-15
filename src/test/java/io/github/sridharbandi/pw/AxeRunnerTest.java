package io.github.sridharbandi.pw;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AxeRunnerTest {

    @Mock
    Page page;
    @InjectMocks
    AxeRunner axeRunner = new AxeRunner(page);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        Assertions.assertThrows(NullPointerException.class, () -> axeRunner.setPageTile("").execute());
    }
}
