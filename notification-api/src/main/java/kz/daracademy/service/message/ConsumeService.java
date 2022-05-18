package kz.daracademy.service.message;


import kz.daracademy.model.EventNotificationInfo;

public interface ConsumeService {
    //void consumeEmailInfo(ClientEmailInfo emailInfo);

    void consumeEventInfo(EventNotificationInfo eventNotificationInfo);
}
