package allegro.internship.github;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "GitHub internal error")
public class GitHubInternalError extends AssertionError {
}
