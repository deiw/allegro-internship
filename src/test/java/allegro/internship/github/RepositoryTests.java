package allegro.internship.github;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTests {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private RepositoryService repositoryService;

    @Test(expected = GitHubUnavailableError.class)
    public void forNullResponse_shouldThrowAssertionError() {
        when(restTemplate.getForObject(anyString(), eq(Repository[].class))).thenReturn(null);

        // execute
        Repository lastModifiedRepository = repositoryService.getLastModifiedRepository();

        // verify
    }

    @Test(expected = RepositoryNotFoundException.class)
    public void forEmptyRepositories_shouldThrowRepositoryNotFoundException() {
        when(restTemplate.getForObject(anyString(), eq(Repository[].class))).thenReturn(new Repository[]{});

        // execute
        Repository lastModifiedRepository = repositoryService.getLastModifiedRepository();

        // verify
    }
}
