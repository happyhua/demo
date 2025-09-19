package com.demo.shared;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("service/contact")
public interface ContactService extends RemoteService {
    List<Contact> getContacts(int offset, int size);

    class App {
        private static final ContactServiceAsync instance = GWT.create(ContactService.class);

        public static ContactServiceAsync getInstance() {
            return instance;
        }
    }
}
