package br.com.autodoc.entity;

import java.io.Serializable;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import br.com.autodoc.interfaces.Mensagem;


public class Email implements Mensagem, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8183013269649535416L;
	private String from;
	private String to; 
	private String cc;
	private String cco;
	private String assunto; 
	private String corpo;
	private Session session;
	private String replay;
	
	
	public Email() {

	}

	@Override
	public void enviar() {
	
		try {
	         		         
			session.setDebug(true);
		
			MimeMessage mail = new MimeMessage(session);
			Address[] to = InternetAddress.parse(this.getTo());
			mail.setRecipients(javax.mail.Message.RecipientType.TO, to);
			mail.setSubject(this.getAssunto());
			mail.setSentDate(new java.util.Date());
			
			if(this.getFrom() != null){
				
				mail.setFrom( new InternetAddress(this.getTo(),this.getFrom()));
			}
			
	          // Destinatario(s) com Copia  
	        if (this.getCc() != null)  
	        {  
	            mail.setRecipients(javax.mail.Message.RecipientType.CC, this.getCc());  
	        }  

	        // Destinatario(s) com Copia Oculta  
	        if (this.getCco() != null)  
	        {  
	            mail.setRecipients(javax.mail.Message.RecipientType.BCC, this.getCco());  
	        } 
	        
	        // Destinatario(s) com Copia Oculta  
	        if (this.getCco() != null)  
	        {  
	            mail.setRecipients(javax.mail.Message.RecipientType.BCC, this.getCco());  
	        } 

	        if(this.getReplay() != null){
	        	
	        	mail.setReplyTo(new javax.mail.Address[]
	        	{
	        			new javax.mail.internet.InternetAddress(this.getReplay())
	        	});
	        }
	        
	        System.out.println("enviado mensagem object"+mail);
			mail.setText(this.getCorpo(), "ISO-8859-1" ,  "html" );
			Transport.send(mail);
			System.out.println("Enviado com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getCco() {
		return cco;
	}

	public void setCco(String cco) {
		this.cco = cco;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public String getReplay() {
		return replay;
	}

	public void setReplay(String replay) {
		this.replay = replay;
	}
		
	public void setSession(Session session ){
		this.session = session;		
	}
}
