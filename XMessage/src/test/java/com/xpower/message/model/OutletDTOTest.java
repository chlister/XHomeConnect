package com.xpower.message.model;

import com.google.gson.internal.LinkedTreeMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class OutletDTOTest {
    public static final String OBJECT1_NAME = "Kitchen";
    public static final double OBJECT1_ID = 22;
    public static final boolean OBJECT1_STATE = false;
    public static final double OBJECT1_AGENTID = 11;
    public static final String OBJECT1_TYPE = "light";

    public static final String OBJECT2_NAME = "Citron kage";
    public static final double OBJECT2_ID = 21;
    public static final boolean OBJECT2_STATE = false;
    public static final double OBJECT2_AGENTID = 10;
    public static final String OBJECT2_TYPE = "kage" ;

    // Test deserialize of LinkedTreeMap to DTO
    @Test
    public void deserialize() {
        LinkedTreeMap tree = new LinkedTreeMap();
        tree.put("id", OBJECT1_ID);
        tree.put("agentId", OBJECT1_AGENTID);
        tree.put("state", OBJECT1_STATE);
        tree.put("name", OBJECT1_NAME);
        tree.put("applianceType", OBJECT1_TYPE);


        Assert.assertEquals(OBJECT1_STATE , OutletDTO.deserialize(tree).getState()) ;
        // Add more assert

    }

    // Test deserialize of ArrayList<LinkedTreeMap> to List<DTO>
    @Test
    public void deserializeList() {
        ArrayList<LinkedTreeMap> list = new ArrayList<>();

        LinkedTreeMap tree = new LinkedTreeMap();
        tree.put("id", OBJECT1_ID);
        tree.put("agentId", OBJECT1_AGENTID);
        tree.put("state", OBJECT1_STATE);
        tree.put("name", OBJECT1_NAME);
        tree.put("applianceType", OBJECT1_TYPE);

        LinkedTreeMap tree2 = new LinkedTreeMap();
        tree2.put("id", OBJECT2_ID);
        tree2.put("agentId", OBJECT2_AGENTID);
        tree2.put("state", OBJECT2_STATE);
        tree2.put("name", OBJECT2_NAME);
        tree2.put("applianceType", OBJECT2_TYPE);

        list.add(tree);
        list.add(tree2);

        List<OutletDTO> dtos = OutletDTO.deserialize(list);
        Assert.assertEquals(2, dtos.size());

        Assert.assertEquals(OBJECT1_STATE , dtos.get(0).getState()) ;
        // Add more assert
        Assert.assertEquals(OBJECT2_STATE , dtos.get(1).getState()) ;
        // Add more assert

    }
}

