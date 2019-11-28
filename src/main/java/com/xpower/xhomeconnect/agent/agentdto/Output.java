
package com.xpower.xhomeconnect.agent.agentdto;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
/**
 * Auto generated class by JSON2pojo
 */
@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Output {

    @SerializedName("Action")
    private Long mAction;
    @SerializedName("Current")
    private Long mCurrent;
    @SerializedName("Delay")
    private Long mDelay;
    @SerializedName("Energy")
    private Long mEnergy;
    @SerializedName("ID")
    private Long mID;
    @SerializedName("Load")
    private Long mLoad;
    @SerializedName("Name")
    private String mName;
    @SerializedName("PowerFactor")
    private Double mPowerFactor;
    @SerializedName("State")
    private Long mState;

    public Long getAction() {
        return mAction;
    }

    public void setAction(Long action) {
        mAction = action;
    }

    public Long getCurrent() {
        return mCurrent;
    }

    public void setCurrent(Long current) {
        mCurrent = current;
    }

    public Long getDelay() {
        return mDelay;
    }

    public void setDelay(Long delay) {
        mDelay = delay;
    }

    public Long getEnergy() {
        return mEnergy;
    }

    public void setEnergy(Long energy) {
        mEnergy = energy;
    }

    public Long getID() {
        return mID;
    }

    public void setID(Long iD) {
        mID = iD;
    }

    public Long getLoad() {
        return mLoad;
    }

    public void setLoad(Long load) {
        mLoad = load;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getPowerFactor() {
        return mPowerFactor;
    }

    public void setPowerFactor(Double powerFactor) {
        mPowerFactor = powerFactor;
    }

    public Long getState() {
        return mState;
    }

    public void setState(Long state) {
        mState = state;
    }

}
