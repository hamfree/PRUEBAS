package tutorial.jndi.ldap.examples;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import javax.naming.*;
import javax.naming.ldap.*;
import javax.naming.event.*;

import java.util.Hashtable;

/**
  * Demonstrates how to register a listener for Unsolicited Notifications.
  * In order for the listener to receive a notification, you must
  * prod the directory server to send an Unsolicited Notification.
  * The procedure for doing so is directory-dependent.
  *
  * usage: java RegUnsol
  */
class RegUnsol {
    public static void main(String[] args) {

	// Set up environment for creating initial context
        Hashtable<String, Object> env = new Hashtable<String, Object>(11);
	env.put(Context.INITIAL_CONTEXT_FACTORY, 
	    "com.sun.jndi.ldap.LdapCtxFactory");
	env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=JNDItutorial");

	try {
	    // Get event context for registering listener
	    EventContext ctx = (EventContext)
		(new InitialContext(env).lookup("ou=People"));

	    // Create listener
	    NamingListener listener = new UnsolListener();

	    // Register listener with context (all targets equivalent)
	    ctx.addNamingListener("", EventContext.ONELEVEL_SCOPE, listener);

	    // Wait 1 minutes for listener to receive events
	    try {
		Thread.sleep(60000);
	    } catch (InterruptedException e) {
		System.out.println("sleep interrupted");
	    }

	    // Not strictly necessary if we're going to close context anyhow
	    ctx.removeNamingListener(listener);

	    // Close context when we're done
	    ctx.close();

	} catch (NamingException e) {
	    e.printStackTrace();
	}
    }

    /**
     * A sample UnsolicitedNotificationListener.
     */
    static class UnsolListener implements UnsolicitedNotificationListener {
	public void notificationReceived(UnsolicitedNotificationEvent evt) {
	    System.out.println("received: " + evt);
	}

	public void namingExceptionThrown(NamingExceptionEvent evt) {
	    System.out.println(">>> UnsolListener got an exception");
	    evt.getException().printStackTrace();
	}
    }
}

