/*******************************************************************************
 * Copyright (c) 2011, 2018 Eurotech and/or its affiliates
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech
 *******************************************************************************/
package org.eclipse.kura.message;

import org.eclipse.kura.cloud.CloudService;
import org.osgi.annotation.versioning.ProviderType;

/**
 * Models a topic for messages posted to the Kura platform.
 * Topics are expected to be in the form of "account/asset/&lt;application_specific&gt;";
 * The system control topic prefix is defined in the {@link CloudService} and defaults
 * to $EDC.
 *
 * @noextend This class is not intended to be subclassed by clients.
 * @deprecated Please use the new Cloud Connection APIs and refer to {@link KuraApplicationTopic}
 */
@Deprecated
@ProviderType
public class KuraTopic {

    private String fullTopic;
    private String[] topicParts;
    private String prefix;
    private String accountName;
    private String deviceId;
    private String applicationId;
    private String applicationTopic;

    public KuraTopic(String fullTopic) {
        this(fullTopic, "$");
    }

    public KuraTopic(String fullTopic, String controlPrefix) {
        this.fullTopic = fullTopic;
        if (fullTopic.compareTo("#") == 0) {
            return;
        }

        this.topicParts = fullTopic.split("/");
        if (this.topicParts.length == 0) {
            return;
        }

        // prefix
        int index = 0;
        int offset = 0; // skip a slash
        if (this.topicParts[0].startsWith(controlPrefix)) {
            this.prefix = this.topicParts[index];
            offset += this.prefix.length() + 1;
            index++;
        }

        // account name
        if (index < this.topicParts.length) {
            this.accountName = this.topicParts[index];
            offset += this.accountName.length() + 1;
            index++;
        }

        // deviceId
        if (index < this.topicParts.length) {
            this.deviceId = this.topicParts[index];
            offset += this.deviceId.length() + 1;
            index++;
        }

        // applicationId
        if (index < this.topicParts.length) {
            this.applicationId = this.topicParts[index];
            offset += this.applicationId.length() + 1;
            index++;
        }

        // applicationTopic
        if (offset < this.fullTopic.length()) {
            this.applicationTopic = this.fullTopic.substring(offset);
        }
    }

    public String getFullTopic() {
        return this.fullTopic;
    }

    public String[] getTopicParts() {
        return this.topicParts;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getApplicationTopic() {
        return this.applicationTopic;
    }
}