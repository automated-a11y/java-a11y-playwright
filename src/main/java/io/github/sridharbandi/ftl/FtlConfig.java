package io.github.sridharbandi.ftl;

import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.util.Optional;


public class FtlConfig {

    public static final String TEMPLATE_DIR = "ftl";
    public static final String ENCODING = "UTF-8";

    private static FtlConfig instance;

    private final Configuration cfg;

    public FtlConfig() {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), TEMPLATE_DIR);
        cfg.setDefaultEncoding(ENCODING);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setSharedVariable("random", new BeansWrapperBuilder(Configuration.VERSION_2_3_31).build().getStaticModels());
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
    }

    public static FtlConfig getInstance() {
        return Optional.ofNullable(instance).orElseGet(FtlConfig::new);
    }

    public Template getTemplate(String name) throws IOException {
        return getInstance().cfg.getTemplate(name, ENCODING);
    }
}
