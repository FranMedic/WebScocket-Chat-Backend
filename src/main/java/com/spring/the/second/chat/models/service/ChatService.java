package com.spring.the.second.chat.models.service;

import java.util.List;

import com.spring.the.second.chat.models.documents.Mensaje;

public interface ChatService {

	public List<Mensaje> obtenerUltimos10Mensajes();
	public Mensaje guardar(Mensaje mensaje);
}
