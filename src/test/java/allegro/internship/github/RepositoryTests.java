package allegro.internship.github;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import java.time.OffsetDateTime;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTests {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private RepositoryService repositoryService;

    @Test(expected = GitHubInternalError.class)
    public void forNullResponse_shouldThrowAssertionError() {
        //setup
        when(restTemplate.getForObject(anyString(), eq(Repository[].class))).thenReturn(null);

        // execute
        Repository lastModifiedRepository = repositoryService.getLastModifiedRepository();

        // verify
    }

    @Test(expected = RepositoryNotFoundException.class)
    public void forEmptyRepositories_shouldThrowRepositoryNotFoundException() {
        //setup
        when(restTemplate.getForObject(anyString(), eq(Repository[].class))).thenReturn(new Repository[]{});

        // execute
        Repository lastModifiedRepository = repositoryService.getLastModifiedRepository();

        // verify
    }

    @Test
    public void afterSuccessfullyResponse_shouldReturnRepository() {
        //setup
        Repository firstRepo = new Repository("FirstRepo", OffsetDateTime.now());
        when(restTemplate.getForObject(anyString(), eq(Repository[].class))).thenReturn(new Repository[]{firstRepo});

        //execute
        Repository lastModifiedRepository = repositoryService.getLastModifiedRepository();

        //verify
        Assert.assertEquals(firstRepo, lastModifiedRepository);
    }
}
