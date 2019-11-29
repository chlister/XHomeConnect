package com.xpower.xhomeconnect.agent;

import com.google.gson.Gson;
import com.xpower.xhomeconnect.agent.agentdto.AgentPOJO;
import com.xpower.xhomeconnect.agent.model.Agent;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgentRunner implements Runnable {

    private IAgentRunnerCallback mCallback;
    private List<String> mIps;
    private OkHttpClient client;

    public AgentRunner(List<String> ips, IAgentRunnerCallback callback) {
        mIps = ips;
        mCallback = callback;
        client = new OkHttpClient();
    }

    public interface IAgentRunnerCallback {
        void updateAgents(List<Agent> agents);
    }

    @Override
    public void run() {

        while (true) {
            List<Agent> agents = new ArrayList<>();
            for (String ip :
                    mIps) {
                Request request = new Request.Builder()
                        .url(ip + "/netio.json")
                        .build();

                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    String responseJson = response.body().string();
                    AgentPOJO pojo = new Gson().fromJson(responseJson, AgentPOJO.class);
                    agents.add(new Agent(pojo, ip));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                mCallback.updateAgents(agents);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
