/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/26/19
 */

package com.xpower.xhomeconnect.agent;

import com.google.gson.Gson;
import com.xpower.message.RespondCodes;
import com.xpower.xhomeconnect.IAgentCallback;
import com.xpower.xhomeconnect.IAgentGetSocketsCallback;
import com.xpower.message.model.OutletDTO;
import com.xpower.xhomeconnect.agent.agentdto.AgentPOJO;
import com.xpower.xhomeconnect.agent.model.Agent;
import com.xpower.xhomeconnect.agent.model.Outlet;
import com.xpower.xhomeconnect.agent.model.RegisterOutlet;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgentManager implements IAgentManager, AgentRunner.IAgentRunnerCallback {

    IAgentGetSocketsCallback mAgentGetSocketsCallback;
    IAgentCallback callback;
    List<Agent> mAgents;
    List<RegisterOutlet> registerOutlets;
    private AgentRunner mAgentRunner;


    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    public AgentManager(IAgentGetSocketsCallback agentGetSocketsCallback) {
        this.mAgentGetSocketsCallback = agentGetSocketsCallback;
    }

    public AgentManager(IAgentCallback callback) {
        mAgents = new ArrayList<>();
        mAgentRunner = new AgentRunner(this.scanNetwork(), this);
        Thread runner = new Thread(mAgentRunner);
        runner.start();
        this.callback = callback;
        registerOutlets = new ArrayList<>();
    }

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    @Override
    public List<OutletDTO> getOutlets() {
        List<OutletDTO> outlets = new ArrayList<>();
        for (Agent agent : mAgents) {
            for (Outlet outlet : agent.getOutlets()) {
                OutletDTO o = new OutletDTO(
                        outlet.getId(),
                        agent.getId(),
                        outlet.getName(),
                        "",
                        outlet.getState() > 0);
                for (RegisterOutlet ro : registerOutlets) {
                    if (ro.getmAgentId() == o.getAgentId() && ro.getmId() == o.getId()) {
                        o.setApplianceType(ro.getmType());
                        o.setName(ro.getmName());
                    }
                }
                outlets.add(o);
            }
        }
        return outlets;
    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
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

    @Override
    public void changeState(OutletDTO outletDTO) {
        for (Agent agent : mAgents) {
            if (agent.getId() == outletDTO.getAgentId()) {
                for (Outlet outlet : agent.getOutlets()) {
                    if (outlet.getId() == outletDTO.getId()) {
                        OkHttpClient client = new OkHttpClient();
                        int state = outletDTO.getState() ? 1 : 0;
                        Request request = new Request.Builder()
                                .url(agent.getIp() + "/netio.json")
                                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"),
                                        "{\"Outputs\":[{\"ID\":"
                                                + outletDTO.getId() + ",\"State\":"
                                                + state + ",\"Action\":6}]}"))
                                .build();
                        Call call = client.newCall(request);
                        try {
                            Response response = call.execute();
                            if (response.code() == 200){
                                String responseJson = response.body().string();
                                AgentPOJO pojo = new Gson().fromJson(responseJson, AgentPOJO.class);
                                agent = new Agent(pojo, agent.getIp());
                                callback.outletChangedEvent(getOutlets(), RespondCodes.OK);
                            }
                            else
                                System.out.println("State change failed");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    @Override
    public List<String> scanNetwork() {
        // TODO returns IP address' of Netio agents
        String ip = "http://192.168.1.90";
        List<String> ips = new ArrayList<>();
        ips.add(ip);
        return ips;
    }


    @Override
    public void updateAgents(List<Agent> agents) {
        mAgents = agents;
        callback.outletChangedEvent(getOutlets(), RespondCodes.OK);
    }
}
