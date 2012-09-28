/*
 * Created by Andrey Markelov 28-09-2012.
 * Copyright Mail.Ru Group 2012. All rights reserved.
 */
package ru.mail.jira.pluigns;

import java.util.HashMap;
import java.util.Map;
import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginFunctionFactory;
import com.opensymphony.workflow.loader.AbstractDescriptor;
import com.opensymphony.workflow.loader.FunctionDescriptor;

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
    protected void getVelocityParamsForInput(
        Map<String, Object> velocityParams)
    {
        velocityParams.put(Consts.CUSTOM_FIELD_ID, "");
        velocityParams.put(Consts.LINK_NAME, "");
    }

    @Override
    protected void getVelocityParamsForView(
        Map<String, Object> velocityParams,
        AbstractDescriptor descriptor)
    {
        velocityParams.put(Consts.CUSTOM_FIELD_ID, getParam(descriptor, Consts.CUSTOM_FIELD_ID));
        velocityParams.put(Consts.LINK_NAME, getParam(descriptor, Consts.LINK_NAME));
    }

    @Override
    public Map<String, ?> getDescriptorParams(
        Map<String, Object> conditionParams)
    {
        Map<String, Object> map = new HashMap<String, Object>();

        if (conditionParams != null &&
            conditionParams.containsKey(Consts.CUSTOM_FIELD_ID) &&
            conditionParams.containsKey(Consts.LINK_NAME))
        {
            map.put(Consts.CUSTOM_FIELD_ID, extractSingleParam(conditionParams, Consts.CUSTOM_FIELD_ID));
            map.put(Consts.LINK_NAME, extractSingleParam(conditionParams, Consts.LINK_NAME));
            return map;
        }

        map.put(Consts.CUSTOM_FIELD_ID, "");
        map.put(Consts.LINK_NAME, "");
        return map;
    }

    @Override
    protected void getVelocityParamsForEdit(
        Map<String, Object> velocityParams,
        AbstractDescriptor descriptor)
    {
        velocityParams.put(Consts.CUSTOM_FIELD_ID, getParam(descriptor, Consts.CUSTOM_FIELD_ID));
        velocityParams.put(Consts.LINK_NAME, getParam(descriptor, Consts.LINK_NAME));
    }

    private String getParam(AbstractDescriptor descriptor, String param)
    {
        if (!(descriptor instanceof FunctionDescriptor))
        {
            throw new IllegalArgumentException("Descriptor must be a FunctionDescriptor.");
        }

        FunctionDescriptor functionDescriptor = (FunctionDescriptor) descriptor;
        String value = (String) functionDescriptor.getArgs().get(param);

        if (value!=null && value.trim().length() > 0)
        {
            return value;
        }
        else 
        {
            return "";
        }
    }
}
