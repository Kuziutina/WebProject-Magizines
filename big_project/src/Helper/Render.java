package Helper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class Render {

    private static Configuration cfg;

    private static Configuration getCfg() throws IOException {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_20);
            cfg.setDirectoryForTemplateLoading(new File("C:/Users/Sofia/IdeaProjects/big_project/web/templates"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setLocale(Locale.US);
            cfg.setEncoding(Locale.US, "UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        }
        return cfg;
    }

    public static void render(HttpServletResponse response, Map<String, Object> context, String templateName) throws IOException, TemplateException {
        Configuration cfg = getCfg();
        Template template = cfg.getTemplate(templateName);
        response.setCharacterEncoding("UTF-8");
        template.process(context, response.getWriter());
    }
}
