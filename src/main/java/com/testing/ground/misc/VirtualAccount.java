package com.testing.ground.misc;

import java.util.*;

public class VirtualAccount {
    public static void main(String[] args) {
        // Example usage of VirtualAccount
        System.out.println("VirtualAccount class is ready to use.");

        Map<String, String> allVas = new HashMap<String, String>();
        //{452534=VERIZON-SE-LAB, 445673=YMIYAGUC}
        allVas.put("452534", "VERIZON-SE-LAB");
        allVas.put("445673", "YMIYAGUC");

        Set<String> sourceVAs = new HashSet<String>();
        sourceVAs.add("YMIYAGUC");

        List<String> toFetchVaIdsList = new ArrayList<>();

        for (String virtualAccountId : allVas.keySet()) {
            String v = allVas.get(virtualAccountId);
//					if (v.trim().equalsIgnoreCase(sourceDevice.getSourceVirtualAccount().trim())) {
            if (sourceVAs.contains(v.trim().toUpperCase())) {
//						toFetchVaIds.append(toFetchVaIds.length() == 0 ? toFetchVaIds.append(virtualAccountId) : toFetchVaIds.append(",").append(virtualAccountId));
                toFetchVaIdsList.add(virtualAccountId);
            }
        }

        System.out.println("Virtual Account IDs to fetch: " + toFetchVaIdsList);
    }
}


