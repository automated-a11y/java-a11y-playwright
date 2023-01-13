package io.github.sridharbandi;

import com.microsoft.playwright.Page;
import io.github.sridharbandi.a11y.Engine;
import io.github.sridharbandi.a11y.HTMLCS;
import io.github.sridharbandi.modal.htmlcs.Issues;
import io.github.sridharbandi.modal.htmlcs.Params;
import io.github.sridharbandi.util.A11y;

import java.io.IOException;
import java.util.Objects;

public class HtmlCsRunner implements IRunner {

    private final A11y a11y;
    private final Params params;
    private HTMLCS standard;
    private String[] codes = {};

    public HtmlCsRunner(Page page) {
        a11y = new A11y(page);
        params = new Params();
    }

    public HtmlCsRunner setStandard(HTMLCS standard) {
        this.standard = standard;
        return this;
    }

    public HtmlCsRunner setIgnoreCodes(String[] codes) {
        this.codes = codes;
        return this;
    }

    public HtmlCsRunner setPageTile(String pageTitle) {
        params.setPageTitle(pageTitle);
        return this;
    }

    @Override
    public Issues execute() throws IOException {
        String stdrd = Objects.isNull(standard) ? HTMLCS.WCAG2AA.name() : standard.name();
        params.setStandard(stdrd);
        params.setIgnoreCodes(codes);
        return (Issues) a11y.execute(Engine.HTMLCS, params);
    }

    public void generateHtmlReport() throws IOException {
        IRunner.super.generateHtmlReport(a11y, Engine.HTMLCS, Issues.class);
    }
}
