package com.br.animal.game.model;

/**
 * Classe que representa o nó da árvore binária. 
 * (Mensagem atual e mensagens em caso de resposta positiva e negativa).
 * @author Danilo
 */
public final class MessageNode implements Cloneable{

	/** Mensagem a ser apresentada ao usuário */
	private String messageOutput;
	
	/**Animal informado pelo usuário*/
	private String animal;
	
	/** Nó que armazena mensagem para resposta positiva */
	private MessageNode positiveMessageNode;

	/** Nó que armazena mensagem para resposta negativa */
	private MessageNode negativeMessageNode;
	
	
	
	protected MessageNode() {

	}

	protected MessageNode(String messageOutput, String animal) {
		this.messageOutput = messageOutput;
		this.animal = animal;					
	}
	

	/** @return mensagem a ser apresentada ao usuário*/
	protected String getMessageOutput() {
		return this.messageOutput;
	}

	/**
	 * @param mensagem a ser apresentada ao usuário
	 */
	protected void setMessageOutput(String messageOutput) {
		this.messageOutput = messageOutput;
	}

	/** @return Nó que armazena mensagem para resposta positiva */
	protected MessageNode getPositiveMessageNode() {
		return this.positiveMessageNode;
	}
	
	/**@return animal informado pelo usuário*/	
	protected String getAnimal() {
		return animal;
	}
	
	/**@param animal informado pelo usuário*/
	protected void setAnimal(String animal) {
		this.animal = animal;
	}
		
	/**
	 * @param Nó que armazena mensagem para resposta positiva
	 */
	protected void setPositiveMessageNode(MessageNode positiveMessageNode) {
		this.positiveMessageNode = positiveMessageNode;
	}

	/** @return Nó que armazena mensagem para resposta negativa */
	protected MessageNode getNegativeMessageNode() {
		return this.negativeMessageNode;
	}

	/**
	 * @param Nó que armazena mensagem para resposta negativa
	 */
	protected void setNegativeMessageNode(MessageNode negativeMessageNode) {
		this.negativeMessageNode = negativeMessageNode;
	}
	
	protected MessageNode clone() {
		return new MessageNode(this.messageOutput, this.animal);
	}
	
	@Override
	public String toString() {
		return "MessageNode [messageOutput=" + messageOutput + ", animal=" + animal + ", positiveMessageNode="
				+ positiveMessageNode == null ? null
						: positiveMessageNode.getMessageOutput() + ", negativeMessageNode="
								+ negativeMessageNode == null ? null : negativeMessageNode.getMessageOutput() + "]";
	}
	

}
