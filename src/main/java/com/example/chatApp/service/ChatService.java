package com.example.chatApp.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chatApp.controller.ChatController;
import com.example.chatApp.domain.Chat;
import com.example.chatApp.form.Message;
import com.example.chatApp.repository.ChatRepository;

@Service
@Transactional
public class ChatService {
	Logger logger = LoggerFactory.getLogger(ChatController.class);
	private MessageSource messageSource;

	@Value("${imgFilePath}")
	private String imgFilePath;
	@Value("${imgDirectoryName}")
	private String imgDirectoryName;

	@Autowired
	private ChatRepository chatRepository;

	public void createChat(Chat chat) {
		chatRepository.createChat(chat);
	}

	public List<Chat> searchChatByRoomId(Chat chat) {
		return chatRepository.searchChatByRoomId(chat);
	}

	/**
	 * 画像をフォルダへ保存し、ファイル名を返却する
	 * 
	 * @param message
	 * @return
	 */
	public String saveImg(Message message) {
		// base64 で文字列化された画像を取得
		String imgBase64 = message.getImage();
		// 被らない名前のために現在の時間をミリ秒まで取得し、文字列化
		String imgName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		// 拡張子の部分のみを取得
		Integer period = message.getImageName().indexOf(".");
		String ImgExtension = message.getImageName().substring(period);

		// 画像を置くファイルパス
		String fileName = imgDirectoryName + imgName + ImgExtension;

		byte[] img = Base64.getDecoder().decode(imgBase64.split(",")[1]);

		try {
			FileOutputStream file = new FileOutputStream(imgFilePath + fileName);
			file.write(img);
			file.close();
		} catch (IOException e) {
			logger.error(messageSource.getMessage("error.message", null, Locale.JAPAN));
		}

		return fileName;
	}
}
