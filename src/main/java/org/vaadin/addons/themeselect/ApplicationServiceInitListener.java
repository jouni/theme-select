package org.vaadin.addons.themeselect;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

@NpmPackage(value = "@lit-labs/preact-signals", version = "^1.0.2")
public class ApplicationServiceInitListener
        implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.addIndexHtmlRequestListener(response -> {
            Document document = response.getDocument();
            Element head = document.head();
            Element script = head.appendElement("script");
            script.text(
                    """
                            window._themeSelect = {};
                            window._themeSelect.prefersDark = matchMedia('(prefers-color-scheme: dark)');

                            window._themeSelect.getPreferredTheme = () => {
                                const theme = localStorage.getItem('theme');

                                if (theme) {
                                    return theme;
                                } else if (window._themeSelect.prefersDark.matches) {
                                    return 'dark';
                                } else {
                                    return null;
                                }
                            }

                            window._themeSelect.updateThemeAttribute = () => {
                                const theme = window._themeSelect.getPreferredTheme();

                                if (theme) {
                                    document.documentElement.setAttribute('theme', theme);
                                } else {
                                    document.documentElement.removeAttribute('theme');
                                }
                            }

                            window._themeSelect.prefersDark.onchange = window._themeSelect.updateThemeAttribute;

                            window._themeSelect.updateThemeAttribute();
                    """);
        });
    }

}
