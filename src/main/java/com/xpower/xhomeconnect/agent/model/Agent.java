/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/26/19
 */
package com.xpower.xhomeconnect.agent.model;

import com.xpower.xhomeconnect.agent.agentdto.AgentPOJO;
import com.xpower.xhomeconnect.agent.agentdto.Output;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    private List<Outlet> mOutlets;
    private String mIp;
    int mId;

    public Agent(AgentPOJO pojo, String ip){
        mId = pojo.getAgent().getOemID().intValue();
        mOutlets = new ArrayList<>();
        for (Output output : pojo.getOutputs()) {
            mOutlets.add(new Outlet(output, ""));
        }
        mIp = ip;
    }

    public List<Outlet> getOutlets() {
        return mOutlets;
    }

    public void setOutlets(List<Outlet> mOutlets) {
        this.mOutlets = mOutlets;
    }

    public String getIp() {
        return mIp;
    }

    public void setIp(String mIp) {
        this.mIp = mIp;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }
}
