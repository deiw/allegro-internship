package allegro.internship.github;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Github service is unavailable")
public class GitHubUnavailable extends AssertionError {
}
