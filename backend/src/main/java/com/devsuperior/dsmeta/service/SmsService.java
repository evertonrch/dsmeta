package com.devsuperior.dsmeta.service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repository.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class SmsService {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    @Autowired
    private SaleRepository saleRepository;

    public void sendSms(Long id) {

        Sale sale = saleRepository.findById(id).get();

        String msg  = new StringBuilder().append("O vendendo ")
                        .append(sale.getSellerName())
                        .append(" vendeu: ")
                        .append(NumberFormat.getCurrencyInstance(Locale.US).format(sale.getAmount()))
                        .append(" na data: ")
                        .append(sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .toString();

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, msg).create();

        System.out.println(message.getSid());
    }
}