package com.tweetapp.api.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tweet")
public class Tweet {
	@Id
	private String id;
	private String tweetText;
	@CreatedDate
	private LocalDateTime createdDate;
	private long likes;
	private User user;
	private List<Tweet> replies;
	private String tweetTag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTweetText() {
		return tweetText;
	}
	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Tweet(String id, String tweetText, LocalDateTime createdDate, long likes, User user, List<Tweet> replies,
			String tweetTag) {
		super();
		this.id = id;
		this.tweetText = tweetText;
		this.createdDate = createdDate;
		this.likes = likes;
		this.user = user;
		this.replies = replies;
		this.tweetTag = tweetTag;
	}
	public Tweet() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", tweet=" + tweetText + ", postDate=" + createdDate + ", likes=" + likes + ", user=" + user
				+ ", replies=" + replies + "]";
	}
	public List<Tweet> getReplies() {
		return replies;
	}
	public void setReplies(List<Tweet> replies) {
		this.replies = replies;
	}
	public String getTweetTag() {
		return tweetTag;
	}
	public void setTweetTag(String tweetTag) {
		this.tweetTag = tweetTag;
	}

}
