package com.br.animal.game.controller;

import com.br.animal.game.model.MessageTree;
import static com.br.animal.game.util.ResourceKeys.*;
import static com.br.animal.game.util.ResourceUtil.*;

/**
 * Classe para troca de informa��es entre as telas itera��o do usu�rio
 * e a �rvore bin�ria com informa��es do jogo
 * @author Danilo
 *
 */
public class GameController {
	
	private final MessageTree tree = MessageTree.getInstance();
	
	/**
	 * Carrega as mensagens iniciais do jogo permitindo realizar as primeiras perguntas ao usu�rio.
	 * */
	public void loadInitialMessagesData() {
		String shark = getMessage(SHARK);
		String monkey=  getMessage(MONKEY);
		String wheredoesAnimalLive = getMessage(WHERE_DOES_THE_ANIMAL_LIVE);
		String isShark=getMessage(IS_THE_ANIMAL_A, shark);
		String isMonkey=getMessage(IS_THE_ANIMAL_A, monkey);
		
		this.tree.createRootMessage(wheredoesAnimalLive, isShark, isMonkey, shark, monkey);
	}
	
	/**
	 * Reinicia a mensagem atual a ser apresentada ao usu�rio.
	 */
	public void initializeTheGame() {
		this.tree.restartCurrentMessage();
	}
	
	/**	
	 * @return mensagem atual a ser exibida ao usu�rio.
	 */
	public String getCurrentMessage() {
		return this.tree.getCurrentMessage();
	}
	
	/**
	 * @return pr�xima mensagem para resposta positiva a ser exibida ao usu�rio.
	 */
	public String getNextPositiveMessage() {
		return this.tree.getPositiveMessage();
	}
	
	/**
	 * @return pr�xima mensagem para resposta negativa a ser exibida ao usu�rio.
	 */
	public String getNextNegativeMessage() {
		return this.tree.getNegativeMessage();
	}
	/**
	 * Retorna o animal configurado no n� pai.
	 * @return animal configurado no n� pai.
	 */
	public String getParentAnimal(){
		return this.tree.getParentAnimal();
	}
	
	/**
	 * Cria um novo animal na �rvore bin�ria
	 * @param animal informado pelo usu�rio
	 * @param character�stica do animal informada pelo usu�rio
	 */
	public void createNewAnimal(final String animal, final String characteristic) {
		String characteristicMsg = getMessage(DOES_THE_ANIMAL, characteristic);
		String animalMsg = getMessage(IS_THE_ANIMAL_A, animal);
		this.tree.createNewAnimal(animalMsg, animal, characteristicMsg);		
	}
}
