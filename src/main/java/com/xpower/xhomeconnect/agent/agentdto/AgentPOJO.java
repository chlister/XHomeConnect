
package com.xpower.xhomeconnect.agent.agentdto;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AgentPOJO {

    @SerializedName("Agent")
    private Agent mAgent;
    @SerializedName("GlobalMeasure")
    private GlobalMeasure mGlobalMeasure;
    @SerializedName("Outputs")
    private List<Output> mOutputs;

    public Agent getAgent() {
        return mAgent;
    }

    public void setAgent(Agent agent) {
        mAgent = agent;
    }

    public GlobalMeasure getGlobalMeasure() {
        return mGlobalMeasure;
    }

    public void setGlobalMeasure(GlobalMeasure globalMeasure) {
        mGlobalMeasure = globalMeasure;
    }

    public List<Output> getOutputs() {
        return mOutputs;
    }

    public void setOutputs(List<Output> outputs) {
        mOutputs = outputs;
    }

}
