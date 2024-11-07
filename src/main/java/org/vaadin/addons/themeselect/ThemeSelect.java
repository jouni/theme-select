package org.vaadin.addons.themeselect;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;

@JsModule("./themeselect/theme-select.ts")
@Tag("theme-select")
public class ThemeSelect extends Component {
  private String label;

  public ThemeSelect() {
  }

  public ThemeSelect(String label) {
    setLabel(label);
  }

  public void setLabel(String label) {
    this.label = label;
    getElement().setProperty("label", this.label);
  }

  public String getLabel() {
    return label;
  }
}
