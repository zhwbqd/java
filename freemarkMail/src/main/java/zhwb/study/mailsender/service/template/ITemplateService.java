/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.mailsender.service.template;

import java.util.Map;

/**
 * The Interface ITemplateService.
 */
public interface ITemplateService
{
    
    /**
     * Process tempalte.
     *
     * @param templateName the template name
     * @param templateStr the template str
     * @param param the param
     * @return the string
     */
    String processTempalte(String templateName, String templateStr, Map<String, Object> param);
}
