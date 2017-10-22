package com.br.animal.game.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * Classe respons�vel pela leitura do conte�do dos arquivos de properties.
 * (message.properties).
 * @author Danilo
 *
 */
public final class ResourceUtil {
	
	private static ResourceBundle resourceBundle = null;
	private static final String RESOURCE_PATH = "messages";
	
	/**
	 * Retorna a mensagem configurada com a chave informada
	 * @param chave da mensagem a ser retornada
	 * @return mensagem encontrada ou vazio caso n�o encontre
	 */
	public static String getMessage(final String key){
		initializeResourceBundle();		
		String returnMsg = "";
		try {
			returnMsg = resourceBundle.getString(key);
		}catch(NullPointerException | MissingResourceException e) {
			returnMsg = "";
		}		
		return returnMsg;		
	}
	/**
	 * Retorna a mensagem configurada com a chave informada, incluindo 
	 * os valores dos par�metros na mensagem
	 * @param chave da mensagem a ser retornada
	 * @param par�metros a serem inclu�dos na mensagem
	 * @return mensagem encontrada ou vazio caso n�o encontre
	 */
	public static String getMessage(final String key, final Object... params){
		String msg = getMessage(key);		
		return MessageFormat.format(msg, params);
	}
	
	private static void initializeResourceBundle(){
		if(resourceBundle == null){			
			resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH ,Locale.getDefault());
		}
	}
}
