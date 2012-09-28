/*
 * Created by Andrey Markelov 28-09-2012.
 * Copyright Mail.Ru Group 2012. All rights reserved.
 */
package ru.mail.jira.pluigns;

import java.util.Collection;
import java.util.Map;
import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.exception.CreateException;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.link.IssueLinkType;
import com.atlassian.jira.issue.link.IssueLinkTypeManager;
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
        Map args,
        PropertySet ps)
    throws WorkflowException
    {
        MutableIssue issue = getIssue(transientVars);

        String cfId = (String) args.get(Consts.CUSTOM_FIELD_ID);
        String linkName = (String) args.get(Consts.LINK_NAME);

        if (cfId == null || linkName == null)
        {
            return;
        }

        try
        {
            Long.parseLong(cfId);
        }
        catch (NumberFormatException nex)
        {
            return;
        }

        IssueLinkTypeManager issueLinkTypeManager = ComponentManager.getComponentInstanceOfType(IssueLinkTypeManager.class);
        Collection<IssueLinkType> types = issueLinkTypeManager.getIssueLinkTypesByName(linkName);
        if (types == null || types.isEmpty())
        {
            return;
        }
        IssueLinkType ilt = types.iterator().next();

        CustomField cf = ComponentManager.getInstance().getCustomFieldManager().getCustomFieldObject(Long.parseLong(cfId));
        if (cf == null)
        {
            return;
        }

        Object obj = issue.getCustomFieldValue(cf);
        if (obj == null)
        {
            return;
        }

        Issue ni = ComponentManager.getInstance().getIssueManager().getIssueObject(obj.toString());
        if (ni == null)
        {
            return;
        }

        try
        {
            ComponentManager.getInstance().getIssueLinkManager().createIssueLink(
                issue.getId(),
                ni.getId(),
                ilt.getId(),
                null,
                ComponentManager.getInstance().getJiraAuthenticationContext().getLoggedInUser());
        }
        catch (CreateException e)
        {
            return;
        }
    }
}
