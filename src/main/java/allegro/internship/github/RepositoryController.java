package allegro.internship.github;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/repositories")
@AllArgsConstructor
class RepositoryController {

    private final RepositoryService repositoryService;

    @GetMapping(path = "/last-modified", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLastModifiedRepositoryName() {
        return repositoryService.getLastModifiedRepository().getName();
    }
}
