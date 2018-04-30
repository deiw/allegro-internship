package allegro.internship;

import allegro.internship.github.Repository;
import allegro.internship.github.RepositoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Comparator;

public class RepositoryTests {

    private RestTemplate restTemplate = new RestTemplate();
    private RepositoryService repositoryService = new  RepositoryService(restTemplate);
    private static final String API_URL = "https://api.github.com/users/allegro/repos";

    @Test
    public void check_connection_status(){
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void check_if_all_repositories_have_been_download() {
        Assert.assertEquals(60, repositoryService.getAllegroRepositories().length);
    }

    @Test
    public void check_sort_by_date() {
        Repository[] repositories = new Repository[]{
                new Repository("FirstRepo",OffsetDateTime.now().plusHours(3)),
                new Repository("SecondRepo", OffsetDateTime.now()),
                new Repository("ThirdRepo", OffsetDateTime.now().plusMinutes(10))};
        Assert.assertEquals("FirstRepo", getTheNewestRepositoryName(repositories));
    }

    private String getTheNewestRepositoryName(Repository[] repositories){
        return Arrays.stream(repositories)
                .max(Comparator.comparing(Repository::getModificationDate))
                .get()
                .getName();
    }
}
