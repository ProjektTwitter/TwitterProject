package tokrystkowiak.twitterApp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Hello world! OK
 *
 */
public class App {
	private static Twitter twitt;
	private static List<String> qList;

	public static void main(String[] args) throws TwitterException, IOException {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("0NRMt0oG83AyOKFPh37ii8xk6")
				.setOAuthConsumerSecret("jrv9os8dkCeSLvheGeE7mdRZ5V5cLviCBHWlFy3zyri7ejcVIE")
				.setOAuthAccessToken("4364663123-X99BG94lfbNdWrZpYIESU1l9TKJOYgv01EFkrwG")
				.setOAuthAccessTokenSecret("Dh5v32dFPSCgk8AMxnEUQw4blrknEzdVROlQwt8ICii7f");
		TwitterFactory tfactory = new TwitterFactory(cb.build());
		twitt = tfactory.getInstance();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		qList = new ArrayList<>();
		boolean timeToQuit = false;
		do {
			timeToQuit = executeMenu(in);
		} while (!timeToQuit);

	}

	public static boolean executeMenu(BufferedReader in) throws IOException, TwitterException {
		String action;
		
		System.out.println("\n\n[A]dd | [S]earch | [D]elete | [L]ist | [Q]uit: ");
		
		action = in.readLine();
		if ((action.length() == 0 || action.toUpperCase().charAt(0) == 'Q')) {
			return true;
		}
		switch ((action.toUpperCase().charAt(0))) {
		case 'A':
			System.out.println("Add a #tag to the search list.");
			qList.add(in.readLine());
			System.out.println("#tag succesfully added.");
			break;
		case 'S':
			Query query;
			QueryResult result;
			List<Status> status;
			for(String q: qList){
				query = new Query(q);
				result = twitt.search(query);
				status = result.getTweets();
				
				for (Status s : status) {
					System.out.println(s.getUser().getName());
					System.out.println(s.getText());
					System.out.println(s.getCreatedAt());
					System.out.println("");
				}
			}
			break;
		}
		return false;
	}

}
