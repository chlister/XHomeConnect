package com.xpower.xhomeconnect.agent;

import com.google.gson.Gson;
import com.xpower.xhomeconnect.agent.agentdto.AgentPOJO;
import com.xpower.xhomeconnect.agent.model.Agent;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AgentRunnerTest {


    @Test
    public void run() {
        OkHttpClient client = new OkHttpClient();
        List<Agent> agents = new ArrayList<>();
        String ip = "http://192.168.1.90";
        Request request = new Request.Builder()
                .url(ip + "/netio.json")
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.message());
            System.out.println(response.headers());
            System.out.println(response.body().contentType());
            String js =response.body().string();
            AgentPOJO pojo =  new Gson().fromJson(js, AgentPOJO.class);
            Assert.assertEquals(response.code(), 200);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}