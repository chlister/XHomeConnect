package com.xpower.xhomeconnect.agent.model;

public class RegisterOutlet {
    private int mId;
    private String mName, mType;
    private int mAgentId;

    public RegisterOutlet(int mId, String mName, String mType, int mAgentId) {
        this.mId = mId;
        this.mName = mName;
        this.mType = mType;
        this.mAgentId = mAgentId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public int getmAgentId() {
        return mAgentId;
    }

    public void setmAgentId(int mAgentId) {
        this.mAgentId = mAgentId;
    }
}
