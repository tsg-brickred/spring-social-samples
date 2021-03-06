/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.showcase.twitter;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.social.connect.ServiceProviderConnection;
import org.springframework.social.connect.ServiceProviderConnectionRepository;
import org.springframework.social.twitter.TwitterApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterShowcaseController {

	private final ServiceProviderConnectionRepository connectionRepository;

	@Inject
	public TwitterShowcaseController(ServiceProviderConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping(value="/twitter", method=RequestMethod.GET)
	public String home(Principal currentUser, Model model) {
		List<ServiceProviderConnection<TwitterApi>> twitterConnections = connectionRepository.findConnectionsByServiceApi(TwitterApi.class);
		if (twitterConnections.size() == 0) {
			return "redirect:/connect/twitter";
		}
		model.addAttribute("connections", twitterConnections);
		model.addAttribute(new TweetForm());
		return "twitter/twitter";
	}

	@RequestMapping(value="/twitter/tweet", method=RequestMethod.POST)
	public String postTweet(Principal currentUser, TweetForm tweetForm) {
		List<ServiceProviderConnection<TwitterApi>> twitterConnections = connectionRepository.findConnectionsByServiceApi(TwitterApi.class);		
		for (ServiceProviderConnection<TwitterApi> connection : twitterConnections) {
			connection.updateStatus(tweetForm.getMessage());
		}
		return "redirect:/twitter";
	}

}