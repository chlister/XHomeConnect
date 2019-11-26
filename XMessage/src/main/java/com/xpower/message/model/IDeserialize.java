/**
 * @author Marc R. K.
 * @version 1.0
 * @since 11/26/19
 */

package com.xpower.message.model;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public interface IDeserialize<T extends IDeserialize> {
    T deserialize(LinkedTreeMap treeMap);
    List<T> deserialize(ArrayList<LinkedTreeMap> list);

}
