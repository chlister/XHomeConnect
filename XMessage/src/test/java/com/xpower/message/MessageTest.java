package com.xpower.message;

import com.xpower.message.model.OutletDTO;
import org.junit.Assert;

public class MessageTest {
    String testJson = "{\"mRespondCodes\":\"OK\",\"mMethodCode\":\"GET_SOCKETS\",\"mObj\":{\"id\":1,\"agentId\":1,\"state\":false,\"name\":\"Test\",\"applianceType\":\"NON\"}}";

    Message testObject = new Message(
            RespondCodes.OK,
            MethodCode.GET_OUTLETS,
            new OutletDTO(1,1,"Test", "NON", false)
    );


    @org.junit.Test
    public void Message() {
        Assert.assertEquals(testObject.getMethodCode(), new Message(testJson).getMethodCode());
        Assert.assertEquals(testObject.getRespondCodes(), new Message(testJson).getRespondCodes());
    }

    @org.junit.Test
    public void encode() {
        Assert.assertEquals(testJson, testObject.encode());

    }


}