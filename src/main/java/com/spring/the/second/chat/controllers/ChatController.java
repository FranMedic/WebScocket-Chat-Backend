package com.spring.the.second.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.spring.the.second.chat.models.documents.Mensaje;
import com.spring.the.second.chat.models.service.ChatService;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	private String[] colores= {"red","green", "blue", "magenta","purple","orange"};
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("Nuevo usuario");
			
		}else {
			chatService.guardar(mensaje);
		}
		mensaje.setTexto( mensaje.getTexto());
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) {
		return username.concat(" esta escribiendo ....");
	}
	
	@MessageMapping("/historial")
	
	public void historial(String clienteId){
		webSocket.convertAndSend("/chat/historial/"+ clienteId,chatService.obtenerUltimos10Mensajes());
		
	}

}
