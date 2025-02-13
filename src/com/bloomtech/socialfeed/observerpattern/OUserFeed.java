package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.App;
import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;

import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class OUserFeed implements Observer{
    private User user;
    private List<Post> feed;
    private SourceFeed source;

    public OUserFeed(User user) {
        this.user = user;
        feed = new ArrayList<>();
        source = App.sourceFeed;
        source.attach(this);
        //TODO: update OUserFeed in constructor after implementing observer pattern
    }

    public User getUser() {
        return user;
    }

    public List<Post> getFeed() {
        return feed;
    }

    @Override
    public void update() {
        if (source != null) {
            Post post = source.getPosts().get(source.getPosts().size()-1);
            if (post.getUsername().equals(user.getUsername())) {
                feed.add(post);
            }
        }
    }
}
