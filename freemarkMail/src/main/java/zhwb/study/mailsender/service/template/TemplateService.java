package zhwb.study.mailsender.service.template;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * The Class TemplateService.
 */
public class TemplateService implements ITemplateService
{

    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * Instantiates a new template service.
     */
    public TemplateService(final FreeMarkerConfigurer freeMarkerConfigurer)
    {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    @Override
    public String processTempalte(final String templateName, final String templateStr, final Map<String, Object> param)
    {
        String emailBody = null;
        try
        {
            if (templateName != null && !templateName.isEmpty() && templateStr != null)
            {
                emailBody = getContentByTemplateStr(param, templateStr, templateName);
            }
            else if (templateName != null && templateStr == null)
            {
                emailBody = getContentByTemplateName(param, templateName);
            }
            else
            {
                emailBody = getContentByDefaultTemplate("test");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
        return emailBody;
    }

    /**
     * Process template into string.
     *
     * @param template the template
     * @param model the model
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws TemplateException the template exception
     */
    private String processTemplateIntoString(final Template template, final Object model)
        throws IOException, TemplateException
    {
        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }

    private String getContentByDefaultTemplate(final String content)
        throws IOException, TemplateException
    {
        String htmlText = "";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("content", content);
        Template tpl = null;
        tpl = freeMarkerConfigurer.getConfiguration().getTemplate("reg.ftl");
        htmlText = processTemplateIntoString(tpl, map);
        return htmlText;
    }

    private String getContentByTemplateName(final Map<String, Object> map, final String templateName)
        throws IOException, TemplateException
    {
        Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
        String htmlText = processTemplateIntoString(tpl, map);
        return htmlText;
    }

    private String getContentByTemplateStr(final Map<String, Object> map, final String templateStr, final String templateName)
        throws IOException, TemplateException
    {
        Template template = new Template(templateName, new StringReader(templateStr), freeMarkerConfigurer.getConfiguration());
        String emailBody = processTemplateIntoString(template, map);

        return emailBody;
    }
}
