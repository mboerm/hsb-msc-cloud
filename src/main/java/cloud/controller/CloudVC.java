package cloud.controller;

import cloud.model.DataBean;
import cloud.view.CloudView;

public class CloudVC {

    // Model
    private DataBean dataBean;

    // View
    private CloudView view;

    public CloudVC(DataBean dataBean) {
        this.dataBean = dataBean;
        this.view = new CloudView();
    }

    public void show() {
        view.show(dataBean.getPrimaryStage());
    }
}
