package io.github.sridharbandi;

import com.microsoft.playwright.Page;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.modal.axe.Issues;
import io.github.sridharbandi.modal.htmlcs.Params;
import io.github.sridharbandi.util.A11y;

import java.io.IOException;

public class AxeRunner implements IRunner {

    private final A11y a11y;
    private final Params params;

    public AxeRunner(Page page) {
        a11y = new A11y(page);
        params = new Params();
    }

    public AxeRunner setPageTile(String pageTitle) {
        params.setPageTitle(pageTitle);
        return this;
    }

    @Override
    public Issues execute() throws IOException {
        return (Issues) a11y.execute(Engine.AXE, params);
    }


    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.AXE, Issues.class);
    }
}
