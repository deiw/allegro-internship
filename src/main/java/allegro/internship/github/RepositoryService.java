package allegro.internship.github;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class RepositoryService {

    private final RestTemplate restTemplate;
    private static final String REPOS_URL = "https://api.github.com/users/allegro/repos?per_page=100";

    public Repository getLastModifiedRepository(){
        return Arrays.stream(getAllegroRepositories())
                .max(Comparator.comparing(Repository::getModificationDate))
                .orElseThrow(RepositoryNotFoundException::new);
    }

    public Repository[] getAllegroRepositories(){
        return restTemplate.getForObject(REPOS_URL, Repository[].class);
    }
}
