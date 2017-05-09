package com.example.lio2.lockproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Supply data model
 */
public class LockLogicModel implements ILockLogicModel {

    private List<String> blockedApplicationNames;

    LockLogicModel(){
        blockedApplicationNames = new ArrayList<>();
        blockedApplicationNames.add("YouTube");
    }


    @Override
    public List<String> getLockAppNames() {
        return blockedApplicationNames;
    }
}
