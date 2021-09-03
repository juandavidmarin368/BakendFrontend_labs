package com.awssqssender.AwsSenderSQS.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.json.JSONArray;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;

import com.amazonaws.services.sqs.model.*;
import com.awssqssender.AwsSenderSQS.Controller.Model.TestData;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api")
public class MainController {

    @GetMapping("getfromfifo")
    public ResponseEntity<String> getTotalhoy() {

        return new ResponseEntity<String>(getMessages(), HttpStatus.OK);
    }

    public String getMessages() {

        String returnSTRING = "";
        String accessKey = "AKIAR4GJOBATEAUHH";
        String secretKey = "4HFrqCVwKGcJtaq3p/OKehcht36t6NL6jOnSZ";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        final AmazonSQS sqs = new AmazonSQSClient(credentials);

        System.out.println("===============================================");
        System.out.println("Getting Started with Amazon SQS Standard Queues");
        System.out.println("===============================================\n");

        try {

            final Map<String, String> attributes = new HashMap<>();

            attributes.put("FifoQueue", "true");

            attributes.put("ContentBasedDeduplication", "true");

            // The FIFO queue name must end with the .fifo suffix.
            final CreateQueueRequest createQueueRequest = new CreateQueueRequest("EXAMPLE.fifo")
                    .withAttributes(attributes);
            final String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();

            System.out.println("Receiving messages from F1.fifo.\n");
            final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);

            final List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            for (final Message message : messages) {
                System.out.println("Message");
                System.out.println("  MessageId:     " + message.getMessageId());
                System.out.println("  ReceiptHandle: " + message.getReceiptHandle());
                System.out.println("  MD5OfBody:     " + message.getMD5OfBody());
                System.out.println("  Body:          " + message.getBody());
                returnSTRING = message.getBody();
            }
            System.out.println();

            if (messages.size() > 0) {
                System.out.println("Deleting the message.\n");
                final String messageReceiptHandle = messages.get(0).getReceiptHandle();
                sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageReceiptHandle));
            }

        } catch (final AmazonServiceException ase) {
            System.out.println(
                    "Caught an AmazonServiceException, which means " + "your request made it to Amazon SQS, but was "
                            + "rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (final AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means "
                    + "the client encountered a serious internal problem while "
                    + "trying to communicate with Amazon SQS, such as not " + "being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

        return returnSTRING;
    }

    @PostMapping("/sendtofifo")
    public ResponseEntity<String> sendSQS(@RequestBody List<TestData> test) {

        JSONArray jsArray = new JSONArray(test);
        sendingSQS(jsArray.toString());
        return new ResponseEntity<String>("Sending SQS", HttpStatus.OK);
    }

    public void sendingSQS(String message) {

        String accessKey = "AKIAR4GJOBAHAAUHH";
        String secretKey = "4HFrqCVwKGcJtaq3p/OkVhcht36t6NL6jOnSZ";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        final AmazonSQS sqs = new AmazonSQSClient(credentials);
        try {

            final Map<String, String> attributes = new HashMap<>();

            // A FIFO queue must have the FifoQueue attribute set to true.
            attributes.put("FifoQueue", "true");

            /*
             * If the user doesn't provide a MessageDeduplicationId, generate a
             * MessageDeduplicationId based on the content.
             */
            attributes.put("ContentBasedDeduplication", "true");

            // The FIFO queue name must end with the .fifo suffix.
            final CreateQueueRequest createQueueRequest = new CreateQueueRequest("EXAMPLE.fifo")
                    .withAttributes(attributes);
            final String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();

            // List all queues.
            /*
             * System.out.println("Listing all queues in your account.\n"); for (final
             * String queueUrl : sqs.listQueues().getQueueUrls()) {
             * System.out.println("  QueueUrl: " + queueUrl); } System.out.println();
             */

            // Send a message.
            System.out.println("Sending a message to F1.fifo.\n");
            final SendMessageRequest sendMessageRequest = new SendMessageRequest(myQueueUrl, message);

            /*
             * When you send messages to a FIFO queue, you must provide a non-empty
             * MessageGroupId.
             */
            sendMessageRequest.setMessageGroupId("messageGroup1");

            // Uncomment the following to provide the MessageDeduplicationId
            // sendMessageRequest.setMessageDeduplicationId("1");
            final SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
            final String sequenceNumber = sendMessageResult.getSequenceNumber();
            final String messageId = sendMessageResult.getMessageId();
            System.out.println(
                    "SendMessage succeed with messageId " + messageId + ", sequence number " + sequenceNumber + "\n");

        } catch (final AmazonServiceException ase) {
            System.out.println(
                    "Caught an AmazonServiceException, which means " + "your request made it to Amazon SQS, but was "
                            + "rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (final AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means "
                    + "the client encountered a serious internal problem while "
                    + "trying to communicate with Amazon SQS, such as not " + "being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

    }

}
