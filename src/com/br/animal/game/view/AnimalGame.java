package com.br.animal.game.view;

import javax.swing.JOptionPane;

import com.br.animal.game.controller.GameController;
import static com.br.animal.game.util.ResourceKeys.*;
import static com.br.animal.game.util.ResourceUtil.*;

public final class AnimalGame {
	
	private static final String[] YES_NO_OPTION = { getMessage(YES), getMessage(NO) };

	private static final String YES_NO_INITIAL_CHOICE = getMessage(YES);

	private final GameController gameController = new GameController();

	public AnimalGame() {
		this.gameController.loadInitialMessagesData();
	}
	
	/**
	 * Executa oo jogo.
	 */
	public void playGame() {
		this.gameController.initializeTheGame();
		introduceTheGame();
		proceedQuestion();
	}
	
	/**
	 * Apresenta o jogo ao usuário pedindo para que pense em um animal.
	 */
	private void introduceTheGame() {
		showInformationMessage(getMessage(THINK_OF_AN_ANIMAL));		
	}
	/**
	 * Exibe dialog de informação
	 * @param mensagem a ser exibida
	 */
	private void showInformationMessage(final String msg) {
		JOptionPane.showMessageDialog(null, msg, getMessage(ANIMALS), JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Prossegue fazendo perguntas ao usuário.
	 * Toma ações de acordo com a resposta do usuário.
	 * @see AnimalGame#proccessYesOrNoAnswer
	 */
	private void proceedQuestion() {
		proccessYesOrNoAnswer(askYesOrNoQuestion(this.gameController.getCurrentMessage()));
	}
	
	/**
	 * Toma ações de acordo com a resposta do usuário
	 * Para respostas positivas, pode-se indicar que o animal foi descoberto ou que o jogo
	 * está no caminho certo para descobrir o animal do usuário, permitindo fazer perguntas 
	 * mais específicas.
	 * Para respostas negativas, indica-se que o jogo não consegue descobrir o animal do usuário
	 * e deve solicitar que o usuário informe o animal, ou que o jogo precisa mudar o rumo das 
	 * perguntas para que possa fazer novas tentativas. 
	 * @param resposta do usuário
	 */
	private void proccessYesOrNoAnswer(final int answer){
		switch(answer) {
		case JOptionPane.YES_OPTION:
			processPositiveUserAnswer();
			break;
		case JOptionPane.NO_OPTION:
			processNegativeUserAnswer();
			break;
		case JOptionPane.CLOSED_OPTION:
			endTheGame();
		}
	}	
	

	/**
	 * Realiza uma pergunta ao usuário que exige sim ou não como resposta.
	 * @param mensagem a ser apresentada ao usuário
	 * @return escolha do usuário 
	 */
	private int askYesOrNoQuestion(final String message) {
		return JOptionPane.showOptionDialog(null, message, getMessage(CONFIRM), JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, YES_NO_OPTION, YES_NO_INITIAL_CHOICE);
	}
	
	/**
	 * Verifica se o animal foi descoberta e exibe mensagem ao usuário.
	 * Caso contrário continua fazendo perguntas
	 */
	private void processPositiveUserAnswer() {
		String msg = this.gameController.getNextPositiveMessage();		
		if(isTheAnimalDiscorvered(msg)) {
			processAnimalDiscoveredMessage();
		}else {
			proceedQuestion();
		}
	}
	/**
	 * Caso não seja possível como descobrir qual o animal pensado pelo usuário, pede ao usuário
	 * para que informe o mesmo. 
	 * Caso contrário continua fazendo perguntas
	 */
	private void processNegativeUserAnswer() {
		String msg = this.gameController.getNextNegativeMessage();		
		if(isGiveUp(msg)) {
			learnNewAnimal();
			playGame();
		}else {
			proceedQuestion();
		}
	}
	
	/**
	 * Pergunta ao usuário informações respeito do animal em que ele pensou e 
	 * salva na árvore essas novas características aprendidas.
	 */
	private void learnNewAnimal() {
		String animal = askDataForUser(getMessage(WHICH_ANIMAL_DID_YOU_THINK),  getMessage(GIVE_UP));
		String characteristicMsg = getMessage(AN_ANIMAL_DOES_BUT_NOT_OTHER, animal, this.gameController.getParentAnimal());
		String characteristic = askDataForUser(characteristicMsg,  getMessage(COMPLETE));		
		this.gameController.createNewAnimal(animal, characteristic);
	}
	
	/**
	 * Verifica se houve desistência de descobrir o animal.
	 * Se o usuário respondeu não a uma pergunta e não existem mais mensagens 
	 * a serem exibidas ao usuário, significa que houve desistência
	 * @param mensagem para resposta negativa do usuário
	 * @return true caso haja desistência ou false caso contrário.
	 */		
	private boolean isGiveUp(final String msg){
		return msg == null;
	}
	
	/**
	 * Verifica se o animal foi descoberto.
	 * Se o usuário respondeu sim a uma pergunta e não existem mais mensagens 
	 * a serem exibidas ao usuário, significa que o animal foi descoberto
	 * @param mensagem para resposta positiva do usuário
	 * @return true caso o animal tenha sido descoberto ou false caso contrário.
	 */			
	private boolean isTheAnimalDiscorvered(final String msg) {		
		return msg == null;
	}
	
	/**
	 * Exibe a mensagem informand que o animal foi descoberto e dá
	 * opção de jogar novamente ou sair.
	 */
	private void processAnimalDiscoveredMessage() {
		String[] options = {getMessage(PLAY_AGAIN), getMessage(QUIT)};
		int answer = JOptionPane.showOptionDialog(null, getMessage(I_GOT_RIGHT_AGAIN), getMessage(ANIMALS), JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, getMessage(PLAY_AGAIN));
		switch(answer) {
		case JOptionPane.YES_OPTION:
			playGame();
			break;
		case JOptionPane.NO_OPTION:
		case JOptionPane.CLOSED_OPTION:
			endTheGame();
		}		
	}
	
	
	/**
	 * Solicita que o usuário digite algum dado
	 * @param Mensagem a ser exibida ao usuário
	 * @param Título da caixa de diálogo
	 * @return dado fornecido pelo usuário
	 */
	private String askDataForUser(String msg, String title) {
		String data = null;		
		do {
			data = JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE);
		}while(data == null || data.trim().isEmpty());
		
		return data;		
	}
		
	private void endTheGame() {
		System.exit(0);
	}
}
