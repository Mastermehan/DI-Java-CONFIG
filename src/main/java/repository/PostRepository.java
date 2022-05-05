package repository;

import exception.NotFoundException;
import model.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {

    private final Map<Long, Post> posts = new ConcurrentHashMap<>(){};
    private final AtomicLong counter = new AtomicLong(0L);

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        if (post.getId() != 0 && !posts.containsKey(post.getId())) {
            throw new NotFoundException();
        }

        if (post.getId() == 0) {
            var newId = counter.incrementAndGet();
            post.setId(newId);
        }

        posts.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        if (!posts.containsKey(id))
            throw new NotFoundException();
        posts.remove(id);
    }
}
