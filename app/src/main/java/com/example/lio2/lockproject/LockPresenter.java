package com.example.lio2.lockproject;

/**
 * Presentation layer to handle all the business logic
 */
class LockPresenter implements ILockPresenter{

    private ILockLogicView lockView;
    private ILockLogicModel model;

    LockPresenter(ILockLogicView view, ILockLogicModel lockLogicModel){
        lockView = view;
        model = lockLogicModel;
    }

    @Override
    public void startService() {
        lockView.startService();
    }

    @Override
    public void stopService() {
        lockView.stopService();
    }

    @Override
    public void displayDialog() {
        if(isLockedApp()) {
            lockView.displayWarning();
        }
    }

    private boolean isLockedApp(){
        return model.getLockAppNames().contains(lockView.getAppInfo());
    }

    @Override
    public void hideDialog() {
        lockView.hideWarning();
        lockView.showHomeView();
    }

}
