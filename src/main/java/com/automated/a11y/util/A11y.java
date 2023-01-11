package com.automated.a11y.util;

import com.automated.a11y.a11y.Engine;
import com.automated.a11y.modal.htmlcs.Issues;
import com.automated.a11y.modal.htmlcs.Params;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;

public class A11y {

    private static final Logger LOG = LoggerFactory.getLogger(A11y.class);

    private Page page = null;

    public A11y(Page page) {
        this.page = page;
    }

    public A11y() {
    }

    public Object execute(Engine engine, Params params) throws IOException {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("js/" + engine.toString().toLowerCase() + ".js");
        String js = IOUtils.toString(Objects.requireNonNull(in), StandardCharsets.UTF_8);
        page.evaluate("async (js)=> await window.eval(js)", js);
        ObjectMapper mapper = new ObjectMapper();
        String strJson = mapper.writeValueAsString(params);
        Object issues = engine.name().equalsIgnoreCase("axe") ?
                page.evaluate("async (param) => await axeData(param)", strJson) :
                page.evaluate("async (param) => await getData(param)", strJson);
        ObjectMapper objectMapper = new ObjectMapper();
        String strResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(issues);
        Path path = get("./target/java-a11y/" + engine.toString().toLowerCase() + "/json/" + UUID.randomUUID() + ".json");
        createDirectories(path.getParent());
        write(path, strResponse.getBytes(StandardCharsets.UTF_8));
        Class<?> clazz = engine.name().equalsIgnoreCase("axe") ? com.automated.a11y.modal.axe.Issues.class : Issues.class;
        return mapper.readValue(strResponse, clazz);
    }

    public List<?> jsonReports(Engine engine, Class<?> clazz) throws IOException {
        return walk(get("./target/java-a11y/" + engine.toString().toLowerCase() + "/json/"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .filter(file -> FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("json"))
                .map(file -> {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        return mapper.readValue(file, clazz);
                    } catch (IOException e) {
                        e.printStackTrace();
                        LOG.error("Failed to read json file {}", file.getAbsolutePath());
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    public void save(Template tmpl, Object map, String name, Engine engine) {
        Path path = null;
        File report;
        try {
            path = get("./target/java-a11y/" + engine.toString().toLowerCase() + "/html");
            createDirectories(path);
            report = new File(path + File.separator + name + ".html");
            Writer file = new FileWriter(report);
            if (tmpl == null) {
                throw new IOException();
            }
            tmpl.process(map, file);
            file.flush();
            file.close();
            String loggerMsg = name.equalsIgnoreCase("index") ? "Consoliated " : "Page ";
            LOG.info(loggerMsg + "report generated at " + report.getAbsolutePath());
        } catch (IOException e) {
            LOG.error("unable to write file: " + path + File.separator + name);
            e.printStackTrace();
        } catch (TemplateException e) {
            LOG.error("unable to find template: " + tmpl + " for " + name);
            e.printStackTrace();
        }
    }
}
