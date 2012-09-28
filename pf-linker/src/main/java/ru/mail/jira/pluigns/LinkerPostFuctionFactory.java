/*
 * Created by Andrey Markelov 28-09-2012.
 * Copyright Mail.Ru Group 2012. All rights reserved.
 */
package ru.mail.jira.pluigns;

import java.util.Map;
import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginFunctionFactory;
import com.opensymphony.workflow.loader.AbstractDescriptor;

/**
 * Post-function factory.
 * 
 * @author Andrey Markelov
 */
public class LinkerPostFuctionFactory
    extends AbstractWorkflowPluginFactory
    implements WorkflowPluginFunctionFactory
{
    @Override
    public Map<String, ?> getDescriptorParams(
        Map<String, Object> conditionParams)
    {
        return null;
    }

    @Override
    protected void getVelocityParamsForEdit(
        Map<String, Object> velocityParams,
        AbstractDescriptor descriptor)
    {

    }

    @Override
    protected void getVelocityParamsForInput(
        Map<String, Object> velocityParams)
    {

    }

    @Override
    protected void getVelocityParamsForView(
        Map<String, Object> velocityParams,
        AbstractDescriptor descriptor)
    {

    }
}
