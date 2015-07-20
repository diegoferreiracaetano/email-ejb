package br.com.autodoc.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.Session;

import br.com.autodoc.entity.Email;

/**
 * Message-Driven Bean implementation class for: FilaEmail
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/Email") })
public class FilaEmail implements MessageListener {

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session session;
	
	
	public FilaEmail() {
		
	}


	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			
			if (message instanceof ObjectMessage ) {
				
				ObjectMessage msg = (ObjectMessage) message;
				
				System.out.println("recebendo object" + msg);
				Email email = (Email) msg.getObject();
				email.setSession(session);
				email.enviar();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
