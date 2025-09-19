package com.demo.server;

import com.demo.shared.Contact;
import com.demo.shared.ContactService;
import com.demo.shared.Gender;
import com.google.gwt.user.server.rpc.jakarta.RemoteServiceServlet;
import jakarta.servlet.annotation.WebServlet;

import java.util.Arrays;
import java.util.List;

@WebServlet({"/app/service/contact"})
public class ContactServiceServlet extends RemoteServiceServlet implements ContactService {
    @Override
    public List<Contact> getContacts(int offset, int size) {
        /*
        This implementation is for a demonstration only. A real-world application should query a database to retrieve
        data, utilizing the offset and size arguments to control pagination.
         */
        return Arrays.asList(new Contact("Edgar", "Klerks", Gender.m, "edgar.klerks@demm.com"),
                new Contact("Hua", "Zhang", Gender.m, "hua.zhang@demm.com"));
    }
}
