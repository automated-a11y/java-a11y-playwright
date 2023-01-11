package com.automated.a11y;

import com.automated.a11y.a11y.Engine;
import com.automated.a11y.a11y.HTMLCS;
import com.automated.a11y.modal.htmlcs.Issues;
import com.automated.a11y.modal.htmlcs.Params;
import com.automated.a11y.util.A11y;
import com.microsoft.playwright.Page;

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

    public void setStandard(HTMLCS standard) {
        this.standard = standard;
    }

    public void setIgnoreCodes(String[] codes) {
        this.codes = codes;
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
