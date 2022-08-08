package com.tweetapp.api.servicetest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.web.servlet.MockMvc;
import com.tweetapp.api.model.Tweet;
import com.tweetapp.api.model.User;
import com.tweetapp.api.repository.TweetRepository;
import com.tweetapp.api.repository.UserRepository;
import com.tweetapp.api.service.TweetServiceImpl;
import com.tweetapp.api.service.UserServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TweetServiceTest {
	private MockMvc mockMvc;

	@Mock
	private TweetRepository tweetRepo;

	@Mock
	private UserRepository userRepo;

	@InjectMocks
	private TweetServiceImpl tweetServiceMock = new TweetServiceImpl();

	@BeforeEach
	public void setup() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void postTweetTest() {
		Tweet tweet = new Tweet();
		tweet.setId("1");
		tweet.setTweetText("abcdef");
		tweet.setUser(new User("1", "sanchit", "root", "abc@gmail.com", "sahaj", "Arora", "9999898998"));
		when(tweetServiceMock.postTweet(tweet)).thenReturn(tweet);
		Tweet actualresp = tweetServiceMock.postTweet(tweet);
		assertEquals(actualresp, tweet);
	}

	@Test
	public void editTweetTest() {
		Tweet tweet = new Tweet();
		tweet.setId("1");
		tweet.setTweetText("abcdef");
		tweet.setUser(new User("1", "sanchit", "root", "abc@gmail.com", "sandhaya", "Arora", "9999898998"));
		when(tweetServiceMock.editTweet(tweet)).thenReturn(tweet);
		Tweet actualresp = tweetServiceMock.editTweet(tweet);
		assertEquals(actualresp, tweet);
	}

	@Test
	public void likeTweetTest() {
		Tweet tweet = new Tweet();
		tweet.setId("1");
		tweet.setTweetText("abcdef");
		tweet.setLikes(1);
		tweet.setUser(new User("1", "sanchit", "root", "abc@gmail.com", "sandhaya", "Arora", "9999898998"));
		when(tweetServiceMock.likeTweet(tweet)).thenReturn(tweet);
		Tweet actualresp = tweetServiceMock.likeTweet(tweet);
		assertEquals(actualresp, tweet);
	}

	@Test
	public void likeTweetByIdTest() {
		Tweet tweet = new Tweet();
		tweet.setId("1");
		tweet.setTweetText("abcdef");
		tweet.setLikes(1);
		tweet.setUser(new User("1", "sanchit", "root", "abc@gmail.com", "sandhaya", "Arora", "9999898998"));
		when(tweetRepo.findById("1")).thenReturn(Optional.of(tweet));
		when(tweetRepo.save(tweet)).thenReturn(tweet);

		TweetServiceImpl myList = mock(TweetServiceImpl.class);
		doNothing().when(myList).likeTweetById("1");
		myList.likeTweetById("1");
		verify(myList, times(1)).likeTweetById("1");
	}

	@Test
	public void replyTweetTest() {
		Tweet tweet3 = new Tweet();
		tweet3.setId("1");
		tweet3.setTweetText("abcdef");
		tweet3.setUser(new User("1", "sanchit", "root", "abc@gmail.com", "sahaj", "Arora", "9999898998"));
		List<Tweet> list = new ArrayList<Tweet>();
		list.add(tweet3);

		Tweet tweet = new Tweet();
		tweet.setId("1");
		tweet.setTweetText("abcdef");
		tweet.setUser(new User("1", "sanchit", "root", "abc@gmail.com", "sahaj", "Arora", "9999898998"));
		tweet.setReplies(list);
		Tweet tweet1 = new Tweet();
		tweet1.setId("2");
		tweet1.setTweetText("wxyzabc");
		tweet1.setUser(new User("2", "aaple", "root", "xyz@gmail.com", "Treena", "Mira", "7766655432"));

		Tweet actualresp = tweetServiceMock.replyTweet(tweet, tweet1);
		when(tweetServiceMock.replyTweet(tweet, tweet1)).thenReturn(tweet1);
		assertEquals(actualresp, tweet1);
	}

	@Test
	public void deleteTweetTest() {
		Tweet tweet = new Tweet();
		tweet.setId("1");
		tweet.setTweetText("abcdef");
		tweet.setUser(new User("1", "sanchit", "root", "abc@gmail.com", "sandhaya", "Arora", "9999898998"));
		tweetServiceMock.deleteTweet(tweet);
		Mockito.verify(tweetRepo).delete(tweet);
	}

	@Test
	public void getAlltweetsTest() {
		Tweet tweet = new Tweet();
		tweet.setId("1");
		tweet.setTweetText("abcdef");
		tweet.setUser(new User("1", "sanchit", "root", "abc@gmail.com", "sahaj", "Arora", "9999898998"));
		Tweet tweety = new Tweet();
		tweety.setId("1");
		tweety.setTweetText("abcdef");
		tweety.setUser(new User("2", "sahil", "root", "abd@gmail.com", "sahil", "Arora", "9999898997"));
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.add(tweet);
		tweetList.add(tweety);
		when(tweetServiceMock.getAllTweets()).thenReturn(tweetList);
		List<Tweet> tweetListResp = tweetServiceMock.getAllTweets();
		assertEquals(tweetListResp, tweetList);

	}

	@Test
	public void getallTweetsByUserNameTest() {
		Tweet tweet = new Tweet();
		tweet.setId("1");
		tweet.setTweetText("abcdef");
		tweet.setUser(new User("1", "sanchi", "root", "abc@gmail.com", "sahaj", "Arora", "9999898998"));
		Tweet tweety = new Tweet();
		tweety.setId("1");
		tweety.setTweetText("abcdef");
		tweety.setUser(new User("2", "sahil", "root", "abd@gmail.com", "sahil", "Arora", "9999898997"));
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.add(tweet);
		tweetList.add(tweety);
		when(tweetServiceMock.getAllTweetsByUsername("sanchit")).thenReturn(tweetList);
		List<Tweet> tweetListResp = tweetServiceMock.getAllTweetsByUsername("sanchit");
		assertEquals(tweetListResp, tweetList);

	}

	@Test
	public void postTweetsByUserNameTest() {
		User user = new User();
		user.setId("1");
		user.setUsername("sanchi");
		user.setFirstName("sahaj");
		user.setLastName("Arora");

		Tweet tweet = new Tweet();
		tweet.setId("1");
		tweet.setTweetText("abcdef");
		tweet.setUser(new User("1", "sanchi", "root", "abc@gmail.com", "sahaj", "Arora", "9999898998"));
		when(userRepo.findByUsername("sanchi")).thenReturn(user);
		when(tweetRepo.save(tweet)).thenReturn(tweet);
		Tweet tweetListResp = tweetServiceMock.postTweetByUsername(tweet, "sanchi");
		assertEquals(tweetListResp, tweet);
	}

}