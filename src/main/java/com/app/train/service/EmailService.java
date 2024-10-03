package com.app.train.service;

import com.app.train.model.entity.BaseTicket;
import com.app.train.model.entity.InternationalTicket;

public interface EmailService {
    void createPdfBaseTicket(BaseTicket baseTicket);
    void createPdfInternationalTicket(InternationalTicket internationalTicket);
    void sendAttachmentEmail();
}
