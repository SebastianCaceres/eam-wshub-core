package ch.cern.eam.wshub.core.services.administration.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "EAM_SCREEN_LAYOUT")
public class ScreenLayoutEntity {

    @Id
    @Column(name = "LAYOUT_ID")
    private String layoutId;

    @Lob
    @Column(name = "LAYOUT_JSON")
    private String layoutJson;

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public String getLayoutJson() {
        return layoutJson;
    }

    public void setLayoutJson(String layoutJson) {
        this.layoutJson = layoutJson;
    }
}
