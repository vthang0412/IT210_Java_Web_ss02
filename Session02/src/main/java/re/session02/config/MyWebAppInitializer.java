package re.session02.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Không cần root context config
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // Spring MVC config
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    // DispatcherServlet bắt tất cả URL
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
