package com.demo.client;

import com.demo.shared.Contact;
import com.demo.shared.ContactService;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.datatable.ColumnConfig;
import org.dominokit.domino.ui.datatable.DataTable;
import org.dominokit.domino.ui.datatable.TableConfig;
import org.dominokit.domino.ui.datatable.store.LocalListDataStore;
import org.dominokit.domino.ui.elements.AnchorElement;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.elements.HeadingElement;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.utils.Domino;

import java.util.List;

import static org.dominokit.domino.ui.utils.Domino.*;

public class App implements EntryPoint {
    private final LocalListDataStore<Contact> contactDataStore = new LocalListDataStore<>();

    @Override
    public void onModuleLoad() {
        HeadingElement heading = h(1).textContent("Hello, world");
        Domino.body().appendChild(heading);

        AnchorElement anchor = a().textContent("Hello, Google");
        anchor.setHref("https://www.google.com");
        Domino.body().appendChild(anchor);

        DivElement div = div();
        Domino.body().appendChild(div);

        TextBox textBox = new TextBox();
        textBox.setValue("Hello, Developer");
        div.appendChild(textBox);

        Button button = new Button().textContent("Click me");
        button.addClickListener(event -> Window.alert("Text box: " + textBox.getValue()));
        div.appendChild(button);

        Domino.body().appendChild(br());

        Domino.body().appendChild(createContactTable());

        updateContactTable(0, 10);
    }

    private DataTable<Contact> createContactTable() {
        TableConfig<Contact> tableConfig = new TableConfig<>();

        ColumnConfig<Contact> columnConfig = ColumnConfig.create("firstName", "First Name");
        columnConfig.setCellRenderer(cellInfo -> text(cellInfo.getRecord().getFirstName()));
        tableConfig.addColumn(columnConfig);

        columnConfig = ColumnConfig.create("lastName", "Last Name");
        columnConfig.setCellRenderer(cellInfo -> text(cellInfo.getRecord().getLastName()));
        tableConfig.addColumn(columnConfig);

        columnConfig = ColumnConfig.create("gender", "Gender");
        columnConfig.setCellRenderer(cellInfo -> text(cellInfo.getRecord().getGender().name()));
        tableConfig.addColumn(columnConfig);

        columnConfig = ColumnConfig.create("email", "Email");
        columnConfig.setCellRenderer(cellInfo -> text(cellInfo.getRecord().getEmail()));
        tableConfig.addColumn(columnConfig);

        return new DataTable<>(tableConfig, contactDataStore);
    }

    private void updateContactTable(int offset, int size) {
        ContactService.App.getInstance().getContacts(offset, size, new AsyncCallback<>() {
            @Override
            public void onFailure(Throwable caught) {
                // Any client-server communication failure, like losing your internet connection.
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(List<Contact> result) {
                // The result was successfully retrieved from the backend server.
                contactDataStore.setData(result);
            }
        });
    }
}
