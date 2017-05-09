package com.example.lio2.lockproject;

import junit.framework.TestCase;

import org.junit.Before;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LockPresenterTest extends TestCase{

    private ILockPresenter lockPresenter;
    private ILockLogicView view;
    private ILockLogicModel model;

    @Before
    public void setUp() throws Exception {
        view = Mockito.mock(ILockLogicView.class);
        model = Mockito.mock(ILockLogicModel.class);
        lockPresenter = new LockPresenter(view, model);
    }

    public void testConstructor(){
        assertNotNull(lockPresenter);
    }

    public void testStartService(){
        lockPresenter.startService();
        Mockito.verify(view, Mockito.times(1)).startService();
    }

    public void testStopService(){
        lockPresenter.stopService();
        Mockito.verify(view, Mockito.times(1)).stopService();
    }

    public void testDisplayDialog(){
        populateModelAndView("YouTube", "YouTube");
        lockPresenter.displayDialog();
        Mockito.verify(view, Mockito.times(1)).displayWarning();
    }

    private void populateModelAndView(String lockAppName, String detectApkName){
        List<String> appNames = new ArrayList<>();
        appNames.add(lockAppName);
        Mockito.when(model.getLockAppNames()).thenReturn(appNames);
        Mockito.when(view.getAppInfo()).thenReturn(detectApkName);
        lockPresenter = new LockPresenter(view, model);
    }

    public void testDisplayDialogAnotherTest(){
        populateModelAndView("NotYoutube", "YouTube");
        lockPresenter.displayDialog();
        Mockito.verify(view, Mockito.times(0)).displayWarning();
    }

    public void testHideDialog(){
        lockPresenter.hideDialog();
        Mockito.verify(view, Mockito.times(1)).hideWarning();
        Mockito.verify(view, Mockito.times(1)).showHomeView();
    }
}