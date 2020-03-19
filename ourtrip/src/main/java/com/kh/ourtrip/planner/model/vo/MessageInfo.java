package com.kh.ourtrip.planner.model.vo;

public class MessageInfo {
	private String id;
	private String chatRoom;
	private String type;
	private String content;

	public MessageInfo() {
		
	}

	public MessageInfo(String id, String chatRoom, String type, String content) {
		super();
		this.id = id;
		this.chatRoom = chatRoom;
		this.type = type;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MessageInfo [id=" + id + ", chatRoom=" + chatRoom + ", type=" + type + ", content=" + content + "]";
	}
	
	
	
}
