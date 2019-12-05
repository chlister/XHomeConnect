/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package com.xpower.xhomeconnect.agent;

import com.google.gson.Gson;
import com.xpower.message.RespondCodes;
import com.xpower.message.model.OutletDTO;
import com.xpower.xhomeconnect.agent.agentdto.AgentPOJO;
import com.xpower.xhomeconnect.agent.model.Agent;
import com.xpower.xhomeconnect.agent.model.Outlet;
import com.xpower.xhomeconnect.agent.model.RegisterOutlet;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MockAgentManager implements IAgentManager, AgentRunner.IAgentRunnerCallback {

    IAgentCallback callback;
    List<Agent> mAgents;
    List<RegisterOutlet> registerOutlets;
    private AgentRunner mAgentRunner;

    /**
     * During init of AgentManager, the class will start a new thread which utilises the AgentRunner class
     *
     * @param callback IAgentCallback
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     */
    public MockAgentManager(IAgentCallback callback) {
        mAgents = new ArrayList<>();
        scanNetwork();
//        mAgentRunner = new AgentRunner(this.scanNetwork(), this);
//        Thread runner = new Thread(mAgentRunner);
//        runner.start();
        this.callback = callback;
        registerOutlets = new ArrayList<>();
    }

    /**
     * Looks through the internal list of Agents
     * converts the Outlets to OutletDTO
     * After converting to an DTO, it sets the appliance type and name
     *
     * @return List<OutletDTO>
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     */
    @Override
    public List<OutletDTO> getOutlets() {
        List<OutletDTO> outlets = new ArrayList<>();
        for (Agent agent : mAgents) {
            for (Outlet outlet : agent.getOutlets()) {
                OutletDTO dto = new OutletDTO(
                        outlet.getId(),
                        agent.getId(),
                        outlet.getName(),
                        "",
                        outlet.getState() > 0);
                for (RegisterOutlet ro : registerOutlets) {
                    if (ro.getmAgentId() == dto.getAgentId() && ro.getmId() == dto.getId()) {
                        dto.setApplianceType(ro.getmType());
                        dto.setName(ro.getmName());
                    }
                }
                outlets.add(dto);
            }
        }
        return outlets;
    }

    /**
     * Takes an OutletDTO and adds this objects id, name, type and agentId to the registered outlets list
     * If the outlet already exists it will be updated.
     * An outletChangedEvent is raised at the end of either scenario
     *
     * @param outletDTO OutletDTO
     * @author Marc R. K.
     * @status Done
     * @since 11/20/19
     */
    @Override
    public void updateOutlet(OutletDTO outletDTO) {
        RespondCodes response = RespondCodes.OK;
        for (RegisterOutlet outlet : registerOutlets) {
            if (outletDTO.getAgentId() == outlet.getmAgentId() && outletDTO.getId() == outlet.getmId()) {
                outlet.setmType(outletDTO.getApplianceType());
                outlet.setmName(outletDTO.getName());
                callback.outletChangedEvent(getOutlets(), response);
                return;
            }
        }
        // if no register of outlet found
        registerOutlets.add(new RegisterOutlet(outletDTO.getId(),
                outletDTO.getName(),
                outletDTO.getApplianceType(),
                outletDTO.getAgentId()));

        callback.outletChangedEvent(getOutlets(), response);
    }

    /**
     * Changes the state of an outlet
     * First it checks if the outlet exists
     * Then builds the HTTP request
     * If the server response is 200 then outletChangedEvent is raised
     *
     * @param outletDTO OutletDTO
     * @author Marc R. K.
     * @status Done
     * @since 11/27/19
     */
    @Override
    public void changeState(OutletDTO outletDTO) {
        for (Agent agent : mAgents) {
            if (agent.getId() == outletDTO.getAgentId()) {
                for (Outlet outlet : agent.getOutlets()) {
                    if (outlet.getId() == outletDTO.getId()) {
                        outlet.setState((short) (outletDTO.getState() ? 1 : 0));
                        callback.outletChangedEvent(getOutlets(), RespondCodes.OK);
                        return;
                    }
                }
            }
        }
    }

    /**
     * This method return a list of IPs
     * At this moment the list contains a single static IP
     *
     * @return List<String> ips
     * @author Marc R. K.
     * @status Done
     * @since 11/26/19
     */
    @Override
    public List<String> scanNetwork() {
        AgentPOJO pojo = new Gson().fromJson(getMockAgentPojoJson(), AgentPOJO.class);

        pojo.getOutputs().get(1).setName("");
        pojo.getOutputs().get(3).setName("");
        mAgents.add(new Agent(pojo, ""));
        pojo.getAgent().setOemID(1L);
        pojo.getOutputs().get(1).setName("");
        pojo.getOutputs().get(3).setName("");
        mAgents.add(new Agent(pojo, ""));
        pojo.getAgent().setOemID(2L);
        pojo.getOutputs().get(1).setName("");
        pojo.getOutputs().get(3).setName("");
        mAgents.add(new Agent(pojo, ""));
        pojo.getAgent().setOemID(3L);
        pojo.getOutputs().get(1).setName("");
        pojo.getOutputs().get(3).setName("");
        mAgents.add(new Agent(pojo, ""));
        return null;
    }


    /**
     * Updates the internal list of agents
     * Raises the outletChangedEvent
     *
     * @param agents List<Agent>
     * @author Marc R. K.
     * @status Done
     * @since 11/27/19
     */
    @Override
    public void updateAgents(List<Agent> agents) {
        mAgents = agents;
        callback.outletChangedEvent(getOutlets(), RespondCodes.OK);
    }

    private static String getMockAgentPojoJson() {
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
