/*
 * Created by Andrey Markelov 28-09-2012.
 * Copyright Mail.Ru Group 2012. All rights reserved.
 */
package ru.mail.jira.pluigns;

import java.util.Map;
import com.atlassian.jira.workflow.function.issue.AbstractJiraFunctionProvider;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.WorkflowException;

/**
 * Post-function.
 * 
 * @author Andrey Markelov
 */
public class LinkerPostFuction
    extends AbstractJiraFunctionProvider
{
    @Override
    public void execute(
        Map transientVars,
        Map args, PropertySet ps)
    throws WorkflowException
    {
        
    }
}
