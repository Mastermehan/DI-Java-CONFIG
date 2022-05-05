package service;

import exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import model.Post;
import repository.PostRepository;

import java.util.List;

@Data
@AllArgsConstructor
public class PostService {
    private final PostRepository repository;

    public List<Post> all() {

        return repository.all();
    }

    public Post getById(long id) {

        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {

        return repository.save(post);
    }

    public void removeById(long id) {

        repository.removeById(id);
    }
}
