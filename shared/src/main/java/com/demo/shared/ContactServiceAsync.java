package com.demo.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface ContactServiceAsync {
    void getContacts(int offset, int size, AsyncCallback<List<Contact>> callback);
}
