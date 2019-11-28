package com.xpower.xhomeconnect.agent.model;

import com.google.gson.internal.$Gson$Types;
import com.xpower.xhomeconnect.agent.agentdto.Output;

public class Outlet {
    private long mId;
    private short mState, mCurrent, mLoad;
    private String mName, mType;

    public Outlet(Output pojo, String type) {
        mId = pojo.getID();
        mState = pojo.getState().shortValue();
        mCurrent = pojo.getCurrent().shortValue();
        mLoad = pojo.getLoad().shortValue();
        mName = pojo.getName();     // TODO: Name comes from agent not POJO
        mType = type.equals("") ? "NON" : type;               // TODO: Type comes from agent not POJO
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public short getState() {
        return mState;
    }

    public void setState(short mState) {
        this.mState = mState;
    }

    public short getCurrent() {
        return mCurrent;
    }

    public void setCurrent(short mCurrent) {
        this.mCurrent = mCurrent;
    }

    public short getLoad() {
        return mLoad;
    }

    public void setLoad(short mLoad) {
        this.mLoad = mLoad;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }
}
