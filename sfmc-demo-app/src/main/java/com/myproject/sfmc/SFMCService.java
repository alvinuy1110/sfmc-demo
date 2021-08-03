package com.myproject.sfmc;

import com.exacttarget.fuelsdk.*;
import com.exacttarget.fuelsdk.internal.*;
import com.myproject.sfmc.config.SFMCConfigProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class SFMCService {

    private final ETClient etClient;
    public SFMCService(ETClient etClient) {
        this.etClient=etClient;
    }

    public void getFolders() throws ETSdkException {


        ETResponse<ETFolder> response = etClient.retrieve(ETFolder.class);
        for (ETFolder folder : response.getObjects()) {
            log.info("Folder: {}",folder);
        }

    }

    public void getTracking() throws ETSdkException {

        List<String> filters = new ArrayList<>();
//        filters.add("subscriberKey = 'alvin_uy@ab.com'");
        ETResponse<ETOpenEvent> response =etClient.retrieve(ETOpenEvent.class, filters.toArray(new String[0]));
        for (ETOpenEvent etOpenEvent : response.getObjects()) {
            log.info("ETOpenEvent: {}",etOpenEvent);
        }

    }
    public void getTracking_2() {

        RetrieveRequest request = new RetrieveRequest();
        request.setObjectType("OpenEvent");
        String[] props = { "id", "customerKey","Clicks","opens","queued","inProcess","sent","bounces" };
        request.getProperties().addAll(Arrays.asList(props));
        request.setQueryAllAccounts(true); //scans all sub-Accounts of parent-Account
        //Query filter using Simplefilter.
        SimpleFilterPart filterPart = new SimpleFilterPart();
        filterPart.setProperty("id");
        String[] values = {"653abcba-a5df-4c80-915d-7fbaa7d9d8bd"};
        filterPart.getValue().addAll(Arrays.asList(values));
        filterPart.setSimpleOperator(SimpleOperators.EQUALS);
        request.setFilter(filterPart);


//        SimpleFilterPart filterPart = new SimpleFilterPart();
//        filterPart.setProperty("customerKey");
//        String[] values = {"alvin_uy@aa.com"};
//        filterPart.getValue().addAll(Arrays.asList(values));
//        filterPart.setSimpleOperator(SimpleOperators.EQUALS);
//        request.setFilter(filterPart);
        RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(request);

        RetrieveResponseMsg responseMsg = etClient.getSoapConnection().getSoap().retrieve(retrieveRequestMsg);

        Subscriber sub = (Subscriber) responseMsg.getResults().get(0);
        if(sub!=null && sub.getAttributes()!=null && !sub.getAttributes().isEmpty()){
            java.util.List<Attribute> a = sub.getAttributes();

            for (Attribute a1 : a) {
                log.info("Name :: " + a1.getName() + "  Value :: " + a1.getValue());
            }
        }
//        etClient.retrieveObject()
//        etClient.retrieve(RetrieveRequestMsg.class);
    }
    public void getTrackingNotWorking() {

        RetrieveRequest request = new RetrieveRequest();
        request.setObjectType("TriggeredSendSummary");
        String[] props = { "id", "customerKey","Clicks","opens","queued","inProcess","sent","bounces" };
                request.getProperties().addAll(Arrays.asList(props));
        request.setQueryAllAccounts(true); //scans all sub-Accounts of parent-Account
        //Query filter using Simplefilter.
        SimpleFilterPart filterPart = new SimpleFilterPart();
        filterPart.setProperty("id");
        String[] values = {"653abcba-a5df-4c80-915d-7fbaa7d9d8bd"};
        filterPart.getValue().addAll(Arrays.asList(values));
        filterPart.setSimpleOperator(SimpleOperators.EQUALS);
        request.setFilter(filterPart);


//        SimpleFilterPart filterPart = new SimpleFilterPart();
//        filterPart.setProperty("customerKey");
//        String[] values = {"alvin_uy@ab.com"};
//        filterPart.getValue().addAll(Arrays.asList(values));
//        filterPart.setSimpleOperator(SimpleOperators.EQUALS);
//        request.setFilter(filterPart);
        RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(request);

        RetrieveResponseMsg responseMsg = etClient.getSoapConnection().getSoap().retrieve(retrieveRequestMsg);

        Subscriber sub = (Subscriber) responseMsg.getResults().get(0);
        if(sub!=null && sub.getAttributes()!=null && !sub.getAttributes().isEmpty()){
            java.util.List<Attribute> a = sub.getAttributes();

            for (Attribute a1 : a) {
                log.info("Name :: " + a1.getName() + "  Value :: " + a1.getValue());
            }
        }
//        etClient.retrieveObject()
//        etClient.retrieve(RetrieveRequestMsg.class);
    }
//    public void t() {
//
//        Soap stub = init();
//        RetrieveRequest request = new RetrieveRequest();
//        request.setObjectType("Subscriber");
//        String[] props = { "ID", "SubscriberKey" };
//                request.getProperties().addAll(Arrays.asList(props));
//        request.setQueryAllAccounts(true); //scans all sub-Accounts of parent-Account
//        //Query filter using Simplefilter.
//        SimpleFilterPart filterPart = new SimpleFilterPart();
//        filterPart.setProperty("SubscriberKey");
//        String[] values = {"acruz@example.com"};
//        filterPart.getValue().addAll(Arrays.asList(values));
//        filterPart.setSimpleOperator(SimpleOperators.EQUALS);
//        request.setFilter(filterPart);
//        RetrieveRequestMsg requestMsg = new RetrieveRequestMsg();
//        requestMsg.setRetrieveRequest(request);
//        RetrieveResponseMsg responseMsg = stub.retrieve(requestMsg);  //Soap call to request Account object
////        assertNotNull(responseMsg);
////        assertNotNull(responseMsg.getResults(0));
//        Subscriber sub = (Subscriber) responseMsg.getResults().get(0);
//        if(sub!=null && sub.getAttributes()!=null && !sub.getAttributes().isEmpty()){
//            java.util.List<Attribute> a = sub.getAttributes();
//
//            for (Attribute a1 : a) {
//                log.info("Name :: " + a1.getName() + "  Value :: " + a1.getValue());
//            }
//        }
//    }
}
