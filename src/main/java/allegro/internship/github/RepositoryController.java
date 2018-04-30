package allegro.internship.github;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RepositoryController {

    private final RepositoryService repositoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getLastModifiedRepositoryName() {
        return ResponseEntity.ok(repositoryService.getLastModifiedRepository().getName());
    }
}
