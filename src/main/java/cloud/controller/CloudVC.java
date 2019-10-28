package cloud.controller;

import cloud.model.DataBean;
import cloud.ui.CloudUI;

public class CloudVC {
    // Model
    private DataBean dataBean;

    // UI
    private CloudUI ui;

    public CloudVC(DataBean dataBean) {
        this.dataBean = dataBean;
        this.ui = new CloudUI();
    }

    public void show(){
        ui.show(dataBean.getPrimaryStage());
    }
}
