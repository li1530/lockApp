package com.example.lio2.lockproject;

import junit.framework.TestCase;

public class LockLogicModelTest extends TestCase {

    public void testGetLockAppNames(){
        LockLogicModel model = new LockLogicModel();
        model.getLockAppNames().contains("YouTube");
    }
}
