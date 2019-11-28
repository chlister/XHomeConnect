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
    private String mIp, mId;

    public Agent(AgentPOJO pojo, String ip){
        mId = pojo.getAgent().getSerialNumber();
        mOutlets = new ArrayList<>();
        for (Output output : pojo.getOutputs()) {
            mOutlets.add(new Outlet(output));
        }
        mIp = ip;
    }

    public List<Outlet> getmOutlets() {
        return mOutlets;
    }

    public void setmOutlets(List<Outlet> mOutlets) {
        this.mOutlets = mOutlets;
    }

    public String getmIp() {
        return mIp;
    }

    public void setmIp(String mIp) {
        this.mIp = mIp;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
}
