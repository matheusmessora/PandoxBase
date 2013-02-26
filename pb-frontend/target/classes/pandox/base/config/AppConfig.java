package pandox.base.config;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import pandox.base.config.util.BasePropertyConfigurer;
import pandox.base.config.util.NameGenerator;

/**
 * Classe de configuração Spring MVC 3
 */
@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan(nameGenerator = NameGenerator.class, basePackages = "pandox.base.controller", excludeFilters = @Filter(Configuration.class))
public class AppConfig {

    private static Logger log = Logger.getLogger(AppConfig.class);

    private static HashMap<String, Object> velocityProperties = new HashMap<String, Object>();

    @Bean
    public static final BasePropertyConfigurer voucherPropertyConfigurer() {
        BasePropertyConfigurer config = new BasePropertyConfigurer();
        config.setIgnoreResourceNotFound(false);
        Resource location = new ClassPathResource("config.properties");
        config.setLocation(location);
        return config;
    }

    @Bean
    public static final VelocityConfigurer velocityConfig() {
        String path = "file:/export/htdocs/pandox.base.intranet";
        log.info("Configurando Velocity...");
        log.info("Diretorio dos templates:" + path);

        velocityProperties.put("input.encoding", "UTF-8");
        velocityProperties.put("output.encoding", "UTF-8");
        velocityProperties.put("directive.foreach.counter.name", "velocityCount");
        velocityProperties.put("directive.foreach.counter.initial.value ", "1");
        VelocityConfigurer config = new VelocityConfigurer();
        config.setVelocityPropertiesMap(velocityProperties);
        config.setResourceLoaderPath(path);
        return config;
    }

    @Bean(name = "resourceBundleMessageSource")
    public static final ReloadableResourceBundleMessageSource resourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("classpath:Messages");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }

    @Bean
    public static final ReloadableResourceBundleMessageSource config() {
        ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("classpath:config");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }

    @Bean
    public static final VelocityViewResolver viewResolver() {
        VelocityViewResolver viewResolver = new VelocityViewResolver();
        viewResolver.setToolboxConfigLocation("/WEB-INF/toolbox.xml");
        HashMap<String, Object> velocityProperties = new HashMap<String, Object>();
        viewResolver.setCache(true);
        viewResolver.setSuffix(".vm");
        viewResolver.setAttributesMap(velocityProperties);
        return viewResolver;
    }

}
