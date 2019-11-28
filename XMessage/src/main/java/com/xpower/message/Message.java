/**
 * TODO: Add class description
 *
 * @author Marc R. K.
 * @version 1.0
 * @since 11/26/19
 */

package com.xpower.message;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.xpower.message.model.SocketDTO;

public class Message {
    private RespondCodes mRespondCodes;
    private MethodCode mMethodCode;
    private Object mObj;

    public MethodCode getMethodCode() {
        return mMethodCode;
    }

    public void setMethodCode(MethodCode mMethodCode) {
        this.mMethodCode = mMethodCode;
    }

    public RespondCodes getRespondCodes() {
        return mRespondCodes;
    }

    public void setRespondCodes(RespondCodes mRespondCodes) {
        this.mRespondCodes = mRespondCodes;
    }

    public Object getObj() {
        return mObj;
    }

    public void setObj(Object mObj) {
        this.mObj = mObj;
    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    public Message(RespondCodes mRespondCodes, MethodCode methodCode, Object mObj) {
        this.mRespondCodes = mRespondCodes;
        this.mObj = mObj;
        this.mMethodCode = methodCode;
    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    public Message(String json) {
        try {
            Message m = new Gson().fromJson(json, Message.class);
            mRespondCodes = m.mRespondCodes;
            mMethodCode = m.mMethodCode;
            mObj = m.mObj;
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Defined
     * @since 11/20/19
     */
    public String encode() {
        return new Gson().toJson(this, this.getClass());
    }

    /**
     * @author Marc R. K.
     * @version 0.1
     * @status Under Development
     * @since 11/20/19
     */
    public boolean validateObjectType(MethodCode methodCode, Object obj) {
        switch (methodCode) {
            case GET_SOCKETS:
            case DETECT_AGENTS:
                if (obj == null)
                    return true;
                break;
            case REGISTER:
                if (obj.getClass() == SocketDTO.class)
                    return true;
        }
        return false;
    }

}
