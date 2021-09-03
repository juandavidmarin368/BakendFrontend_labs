/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jaivana.awssqsmaven_javaapplication;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;



public class MainJavaClass {
    
    
    public static void main(String[] args) {

        String accessKey = "AKIAR4GJOBAHA3AUHH";
        String secretKey = "4HFrqCVwKGcJtaq3p/OkVSKehchtt6NL6jOnSZ";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,
                secretKey);

        final AmazonSQS sqs = new AmazonSQSClient(credentials);

        System.out.println("===============================================");
        System.out.println("Getting Started with Amazon SQS Standard Queues");
        System.out.println("===============================================\n");

        try {

            final Map<String, String> attributes = new HashMap<>();

            attributes.put("FifoQueue", "true");

            attributes.put("ContentBasedDeduplication", "true");

            // The FIFO queue name must end with the .fifo suffix.
            final CreateQueueRequest createQueueRequest
                    = new CreateQueueRequest("EXAMPLE.fifo")
                            .withAttributes(attributes);
            final String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();

            System.out.println("Receiving messages EXAMPLE.fifo.\n");
            final ReceiveMessageRequest receiveMessageRequest
                    = new ReceiveMessageRequest(myQueueUrl);

            final List<Message> messages = sqs.receiveMessage(receiveMessageRequest)
                    .getMessages();
            for (final Message message : messages) {
                System.out.println("Message");
                System.out.println("  MessageId:     "
                        + message.getMessageId());
                System.out.println("  ReceiptHandle: "
                        + message.getReceiptHandle());
                System.out.println("  MD5OfBody:     "
                        + message.getMD5OfBody());
                System.out.println("  Body:          "
                        + message.getBody());

                    getItemstoJSON(message.getBody());
               
            }
            System.out.println();

            if (messages.size() > 0) {
                System.out.println("Deleting the message.\n");
                final String messageReceiptHandle = messages.get(0).getReceiptHandle();
                sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl,
                        messageReceiptHandle));
            }

        } catch (final AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means "
                    + "your request made it to Amazon SQS, but was "
                    + "rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (final AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means "
                    + "the client encountered a serious internal problem while "
                    + "trying to communicate with Amazon SQS, such as not "
                    + "being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

    }

    public static void getItemstoJSON(String jsonString)  {

        JSONArray temp1 = new JSONArray(jsonString);
        for (int i = 0; i < temp1.length(); i++) {
            JSONObject obj = temp1.getJSONObject(i);

            System.out.println("data1 " + obj.getString("data1"));
            System.out.println("data2 " + obj.getString("data2"));
            System.out.println("date " + obj.getString("date"));

            JSONArray jArray2 = obj.getJSONArray("hasmapList");
            for (int j = 0; j < jArray2.length(); j++) {
                JSONObject jOBJNEW = jArray2.getJSONObject(j);
                System.out.println("Base64 String: " +jOBJNEW.getString("subdata"));

            }

        }

    }
    
    
}
