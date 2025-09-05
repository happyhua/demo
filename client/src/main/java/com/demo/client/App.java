package com.demo.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.ui.elements.AnchorElement;
import org.dominokit.domino.ui.utils.Domino;

import static org.dominokit.domino.ui.utils.Domino.a;

public class App implements EntryPoint {
    @Override
    public void onModuleLoad() {
        AnchorElement anchor = a();

        anchor.setTextContent("Hello, Google");
        anchor.setHref("https://www.google.com");

        Domino.body().appendChild(anchor);
    }
}
