package com.xpower.xhomeconnect.agent.model;

import com.google.gson.Gson;
import com.xpower.xhomeconnect.agent.agentdto.AgentPOJO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AgentTest {

    @Test
    public void jsonToPojoTest() {
        AgentPOJO pojo = new Gson().fromJson(getAgentPojoJson(), AgentPOJO.class);

        Assert.assertEquals(0L, pojo.getAgent().getOemID().longValue());
    }

    @Test
    public void agentConstructorTest() {
        AgentPOJO pojo = new Gson().fromJson(getAgentPojoJson(), AgentPOJO.class);

        Agent agent = new Agent(pojo, "");

        Assert.assertEquals(0, agent.getId());
        Assert.assertEquals(0, agent.getOutlets().get(0).getState());
    }

    private static String getAgentPojoJson() {
        // Test json from Netio Agent.

        return "{ \n" +
                "   \"Agent\":{ \n" +
                "      \"Model\":\"NETIO 4All\",\n" +
                "      \"Version\":\"3.0.6\",\n" +
                "      \"JSONVer\":\"2.0\",\n" +
                "      \"DeviceName\":\"myNetio\",\n" +
                "      \"VendorID\":0,\n" +
                "      \"OemID\":0,\n" +
                "      \"SerialNumber\":\"\",\n" +
                "      \"Uptime\":628,\n" +
                "      \"Time\":\"2001-01-02T05:16:13+00:00\",\n" +
                "      \"NumOutputs\":4\n" +
                "   },\n" +
                "   \"GlobalMeasure\":{ \n" +
                "      \"Voltage\":232.6,\n" +
                "      \"Frequency\":50.1,\n" +
                "      \"TotalCurrent\":0,\n" +
                "      \"OverallPowerFactor\":0.00,\n" +
                "      \"TotalLoad\":0,\n" +
                "      \"TotalEnergy\":0,\n" +
                "      \"EnergyStart\":\"2001-01-02T05:01:42+00:00\"\n" +
                "   },\n" +
                "   \"Outputs\":[ \n" +
                "      { \n" +
                "         \"ID\":1,\n" +
                "         \"Name\":\"output_1\",\n" +
                "         \"State\":0,\n" +
                "         \"Action\":6,\n" +
                "         \"Delay\":5000,\n" +
                "         \"Current\":0,\n" +
                "         \"PowerFactor\":0.00,\n" +
                "         \"Load\":0,\n" +
                "         \"Energy\":0\n" +
                "      },\n" +
                "      { \n" +
                "         \"ID\":2,\n" +
                "         \"Name\":\"output_2\",\n" +
                "         \"State\":0,\n" +
                "         \"Action\":6,\n" +
                "         \"Delay\":5000,\n" +
                "         \"Current\":0,\n" +
                "         \"PowerFactor\":0.00,\n" +
                "         \"Load\":0,\n" +
                "         \"Energy\":0\n" +
                "      },\n" +
                "      { \n" +
                "         \"ID\":3,\n" +
                "         \"Name\":\"output_3\",\n" +
                "         \"State\":0,\n" +
                "         \"Action\":6,\n" +
                "         \"Delay\":5000,\n" +
                "         \"Current\":0,\n" +
                "         \"PowerFactor\":0.00,\n" +
                "         \"Load\":0,\n" +
                "         \"Energy\":0\n" +
                "      },\n" +
                "      { \n" +
                "         \"ID\":4,\n" +
                "         \"Name\":\"output_4\",\n" +
                "         \"State\":0,\n" +
                "         \"Action\":6,\n" +
                "         \"Delay\":5000,\n" +
                "         \"Current\":0,\n" +
                "         \"PowerFactor\":0.00,\n" +
                "         \"Load\":0,\n" +
                "         \"Energy\":0\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }
}