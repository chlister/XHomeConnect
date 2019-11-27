/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/20/19
 */

package com.xpower.message.model;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Socket DTO to be sent over HTTP connection
 */
public class SocketDTO {
    private int id, agentId;
    private boolean state;
    private String name, applianceType;

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    public SocketDTO(int id, int agentId, String name, String applianceType, boolean state) {
        this.id = id;
        this.agentId = agentId;
        this.name = name;
        this.state = state;
        this.applianceType = applianceType;
    }

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    public SocketDTO() {
    }

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    public static SocketDTO deserialize(LinkedTreeMap tree) {
        SocketDTO dto = new SocketDTO();
        dto.id = (int) (double) tree.get("id");
        dto.agentId = (int) (double) tree.get("agentId");
        dto.name = String.valueOf(tree.get("name"));
        dto.applianceType = (String) tree.get("applianceType");

        return dto;
    }

    /**
     * @author Marc R. K.
     * @version 0.2
     * @status Under Development
     * @since 11/20/19
     */
    public static List<SocketDTO> deserialize(ArrayList<LinkedTreeMap> list) {
        List<SocketDTO> dto = new ArrayList<>();
        for (LinkedTreeMap treeMap : list) {
            dto.add(deserialize(treeMap));
        }
        return dto;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplianceType() {
        return applianceType;
    }

    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
    }


}
