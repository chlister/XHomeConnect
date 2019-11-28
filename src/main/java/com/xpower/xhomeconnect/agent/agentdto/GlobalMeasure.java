
package com.xpower.xhomeconnect.agent.agentdto;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
/**
 * Auto generated class by JSON2pojo
 */
@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GlobalMeasure {

    @SerializedName("EnergyStart")
    private String mEnergyStart;
    @SerializedName("Frequency")
    private Double mFrequency;
    @SerializedName("OverallPowerFactor")
    private Double mOverallPowerFactor;
    @SerializedName("TotalCurrent")
    private Long mTotalCurrent;
    @SerializedName("TotalEnergy")
    private Long mTotalEnergy;
    @SerializedName("TotalLoad")
    private Long mTotalLoad;
    @SerializedName("Voltage")
    private Double mVoltage;

    public String getEnergyStart() {
        return mEnergyStart;
    }

    public void setEnergyStart(String energyStart) {
        mEnergyStart = energyStart;
    }

    public Double getFrequency() {
        return mFrequency;
    }

    public void setFrequency(Double frequency) {
        mFrequency = frequency;
    }

    public Double getOverallPowerFactor() {
        return mOverallPowerFactor;
    }

    public void setOverallPowerFactor(Double overallPowerFactor) {
        mOverallPowerFactor = overallPowerFactor;
    }

    public Long getTotalCurrent() {
        return mTotalCurrent;
    }

    public void setTotalCurrent(Long totalCurrent) {
        mTotalCurrent = totalCurrent;
    }

    public Long getTotalEnergy() {
        return mTotalEnergy;
    }

    public void setTotalEnergy(Long totalEnergy) {
        mTotalEnergy = totalEnergy;
    }

    public Long getTotalLoad() {
        return mTotalLoad;
    }

    public void setTotalLoad(Long totalLoad) {
        mTotalLoad = totalLoad;
    }

    public Double getVoltage() {
        return mVoltage;
    }

    public void setVoltage(Double voltage) {
        mVoltage = voltage;
    }

}
