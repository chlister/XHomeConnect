
package com.xpower.xhomeconnect.agent.agentdto;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
/**
 * Auto generated class by JSON2pojo
 */
@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Agent {

    @SerializedName("DeviceName")
    private String mDeviceName;
    @SerializedName("JSONVer")
    private String mJSONVer;
    @SerializedName("Model")
    private String mModel;
    @SerializedName("NumOutputs")
    private Long mNumOutputs;
    @SerializedName("OemID")
    private Long mOemID;
    @SerializedName("SerialNumber")
    private String mSerialNumber;
    @SerializedName("Time")
    private String mTime;
    @SerializedName("Uptime")
    private Long mUptime;
    @SerializedName("VendorID")
    private Long mVendorID;
    @SerializedName("Version")
    private String mVersion;

    public String getDeviceName() {
        return mDeviceName;
    }

    public void setDeviceName(String deviceName) {
        mDeviceName = deviceName;
    }

    public String getJSONVer() {
        return mJSONVer;
    }

    public void setJSONVer(String jSONVer) {
        mJSONVer = jSONVer;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        mModel = model;
    }

    public Long getNumOutputs() {
        return mNumOutputs;
    }

    public void setNumOutputs(Long numOutputs) {
        mNumOutputs = numOutputs;
    }

    public Long getOemID() {
        return mOemID;
    }

    public void setOemID(Long oemID) {
        mOemID = oemID;
    }

    public String getSerialNumber() {
        return mSerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        mSerialNumber = serialNumber;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public Long getUptime() {
        return mUptime;
    }

    public void setUptime(Long uptime) {
        mUptime = uptime;
    }

    public Long getVendorID() {
        return mVendorID;
    }

    public void setVendorID(Long vendorID) {
        mVendorID = vendorID;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

}
