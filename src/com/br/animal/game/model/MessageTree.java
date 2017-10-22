package com.br.animal.game.model;


/**
 * Classe que representa a árvore binária de mensagens do jogo.  
 * Permite a criação de uma única instância, uma vez que o jogo fica todo armazenado em memória
 * e armazena inormações constantemente enquanto o usuário joga.
 * @author Danilo
 * */
public final class MessageTree {
	
	private static final MessageTree INSTANCE = new MessageTree();
	
	/**Conteúdo inicial de mensagens da árvore*/
	private MessageNode rootMessage;
	
	/**Representa a mensagem atual de iteração com o usuário*/
	private MessageNode currentMessageNode;
	
	/**Representa o nó pai da mensagem atual de iteração com o usuário*/
	private MessageNode currentParentMessageNode;
	
	private enum TypeOfMessage{POSITIVE, NEGATIVE};
	
	private MessageTree() {		
	
	}	
	
	public static final MessageTree getInstance() {
		return INSTANCE;
	}
	/**
	 * Cria a estrutura inicial de mensagens.
	 * @param mensagem a ser exibida ao usuário inicialmente.
	 * @param mensagem a ser exibida ao usuário em caso de resposta positiva do usuário.
	 * @param mensagem a ser exibida ao usuário em caso de resposta negativa do usuário.
	 * @param animal referente a mensagem de resposta positiva do usuário. 
	 * @param animal referente a mensagem de resposta negativa do usuário. 
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
	 * Reinicia a mensagem atual a ser apresentada ao usuário.
	 */
	public void restartCurrentMessage() {
		this.currentMessageNode = this.rootMessage;
		this.currentParentMessageNode = null;
	}
		
	/**
	 * @return mensagem a ser exibida ao usuário.
	 */
	public String getCurrentMessage() {
		return this.currentMessageNode == null ? null : this.currentMessageNode.getMessageOutput();
	}
	
			
	/**
	 * A mensagen atual de iteração com o usuário passa a ser a mensagem
	 * para resposta positiva do usuário.
	 * Retorna a mensagem para resposta positiva encontrada. 
	 * 
	 * @return mensagem a ser exibida ao usuário.
	 */
	public String getPositiveMessage() {
		return getMessage(TypeOfMessage.POSITIVE);
	}
	
	/**
	 * A mensagen atual de iteração com o usuário passa a ser a mensagem
	 * para resposta negativa do usuário.
	 * Retorna a mensagem para resposta negativa encontrada. 
	 * 
	 * @return mensagem a ser exibida ao usuário.
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
	 * Retorna o animal configurado no nó de mensagem pai
	 * @return animal configurado no nó de mensagem pai
	 */
	public String getParentAnimal() {
		return this.currentParentMessageNode == null ? null : this.currentParentMessageNode.getAnimal();
	}
	
	/**
	 * Cria um novo animal na árvore binária.
	 * @param mensagem perguntando se o animal é o animal informado pelo usuário
	 * @param animal informado pelo usuário
	 * @param mensagem perguntando se o animal possui a característica informada pelo usuário
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
