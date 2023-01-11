package com.automated.a11y;

import com.automated.a11y.a11y.Engine;
import com.automated.a11y.modal.axe.Issues;
import com.automated.a11y.modal.htmlcs.Params;
import com.automated.a11y.util.A11y;
import com.microsoft.playwright.Page;

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
