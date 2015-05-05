package com.dhaval2404.androidmail.utils;

import android.content.Context;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SimpleMailUtils  {

    public static void sendSimpleMail(Context context, String fromId, String password, String toId, String subject, String message)
        throws EmailException {
        String hostName = SharedPrefrenceUtils.getString(context, HostUtils.HOST_NAME, HostUtils.GOOGLE_HOST_NAME);
        int smtpPort = SharedPrefrenceUtils.getInt(context, HostUtils.SMTP_PORT, HostUtils.GOOGLE_SMTP_PORT);
        boolean flag = SharedPrefrenceUtils.getBoolean(context, HostUtils.TLS_REQUIRE, false);
        SimpleEmail simpleemail = new SimpleEmail();
        simpleemail.setHostName(hostName);
        simpleemail.setSmtpPort(smtpPort);
        simpleemail.setAuthenticator(new DefaultAuthenticator(fromId, password));
        if (flag){
            simpleemail.setStartTLSEnabled(true);
        } else {
            simpleemail.setSSLOnConnect(true);
        }
        simpleemail.setFrom(fromId);
        simpleemail.setSubject(subject);
        simpleemail.setMsg(message);
        simpleemail.addTo(toId);
        simpleemail.send();
    }
}
