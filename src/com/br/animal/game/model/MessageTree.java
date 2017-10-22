package com.br.animal.game.model;


/**
 * Classe que representa a �rvore bin�ria de mensagens do jogo.  
 * Permite a cria��o de uma �nica inst�ncia, uma vez que o jogo fica todo armazenado em mem�ria
 * e armazena inorma��es constantemente enquanto o usu�rio joga.
 * @author Danilo
 * */
public final class MessageTree {
	
	private static final MessageTree INSTANCE = new MessageTree();
	
	/**Conte�do inicial de mensagens da �rvore*/
	private MessageNode rootMessage;
	
	/**Representa a mensagem atual de itera��o com o usu�rio*/
	private MessageNode currentMessageNode;
	
	/**Representa o n� pai da mensagem atual de itera��o com o usu�rio*/
	private MessageNode currentParentMessageNode;
	
	private enum TypeOfMessage{POSITIVE, NEGATIVE};
	
	private MessageTree() {		
	
	}	
	
	public static final MessageTree getInstance() {
		return INSTANCE;
	}
	/**
	 * Cria a estrutura inicial de mensagens.
	 * @param mensagem a ser exibida ao usu�rio inicialmente.
	 * @param mensagem a ser exibida ao usu�rio em caso de resposta positiva do usu�rio.
	 * @param mensagem a ser exibida ao usu�rio em caso de resposta negativa do usu�rio.
	 * @param animal referente a mensagem de resposta positiva do usu�rio. 
	 * @param animal referente a mensagem de resposta negativa do usu�rio. 
	 */
	public void createRootMessage(final String rootMessage, final String positiveMessage, final String negativeMessage,
			final String positiveAnimal, final String negativeAnimal) {
		
		MessageNode positiveMessageNode = new MessageNode(positiveMessage, positiveAnimal);
		MessageNode negativeMessageNode = new MessageNode(negativeMessage, negativeAnimal);		
		this.rootMessage = new MessageNode(rootMessage, null);
		this.rootMessage.setPositiveMessageNode(positiveMessageNode);
		this.rootMessage.setNegativeMessageNode(negativeMessageNode);
		restartCurrentMessage();
	}
	/**
	 * Reinicia a mensagem atual a ser apresentada ao usu�rio.
	 */
	public void restartCurrentMessage() {
		this.currentMessageNode = this.rootMessage;
		this.currentParentMessageNode = null;
	}
		
	/**
	 * @return mensagem a ser exibida ao usu�rio.
	 */
	public String getCurrentMessage() {
		return this.currentMessageNode == null ? null : this.currentMessageNode.getMessageOutput();
	}
	
			
	/**
	 * A mensagen atual de itera��o com o usu�rio passa a ser a mensagem
	 * para resposta positiva do usu�rio.
	 * Retorna a mensagem para resposta positiva encontrada. 
	 * 
	 * @return mensagem a ser exibida ao usu�rio.
	 */
	public String getPositiveMessage() {
		return getMessage(TypeOfMessage.POSITIVE);
	}
	
	/**
	 * A mensagen atual de itera��o com o usu�rio passa a ser a mensagem
	 * para resposta negativa do usu�rio.
	 * Retorna a mensagem para resposta negativa encontrada. 
	 * 
	 * @return mensagem a ser exibida ao usu�rio.
	 */
	public String getNegativeMessage() {
		return getMessage(TypeOfMessage.NEGATIVE);
	}
	
	private String getMessage(TypeOfMessage typeOfMessage) {
		if(this.currentMessageNode == null) {
			return null;
		}
		this.currentParentMessageNode = this.currentMessageNode;
		if(TypeOfMessage.POSITIVE.equals(typeOfMessage)) {
			this.currentMessageNode = this.currentMessageNode.getPositiveMessageNode();
		}else {
			this.currentMessageNode = this.currentMessageNode.getNegativeMessageNode();
		}
		return getCurrentMessage();
	}
	
	/**
	 * Retorna o animal configurado no n� de mensagem pai
	 * @return animal configurado no n� de mensagem pai
	 */
	public String getParentAnimal() {
		return this.currentParentMessageNode == null ? null : this.currentParentMessageNode.getAnimal();
	}
	
	/**
	 * Cria um novo animal na �rvore bin�ria.
	 * @param mensagem perguntando se o animal � o animal informado pelo usu�rio
	 * @param animal informado pelo usu�rio
	 * @param mensagem perguntando se o animal possui a caracter�stica informada pelo usu�rio
	 */
	public void createNewAnimal(final String animalMsg, final String inputAnimal, final String characteristicMsg) {
		
		MessageNode parent = this.currentParentMessageNode.clone();		
		this.currentParentMessageNode.setMessageOutput(characteristicMsg);
		this.currentParentMessageNode.setAnimal(null);		
		MessageNode positiveMessageNode = new MessageNode(animalMsg, inputAnimal);
		this.currentParentMessageNode.setPositiveMessageNode(positiveMessageNode);
		this.currentParentMessageNode.setNegativeMessageNode(parent);				
	}
}
