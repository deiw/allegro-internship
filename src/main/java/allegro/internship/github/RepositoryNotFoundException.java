package allegro.internship.github;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Repository not found")
class RepositoryNotFoundException extends RuntimeException {
}
