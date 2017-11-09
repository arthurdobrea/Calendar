/**
 * 
 */
package com.calendar.project.model;

public class MessageBroadcast {

    private String messageContent;

    /**
     * Default no argument constructor
     */
    public MessageBroadcast() {

    }

    /**
     * Constructor
     * 
     * @param messageContent
     */
    public MessageBroadcast(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * @return the messageContent
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * @param messageContent
     *            the messageContent to set
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

}
