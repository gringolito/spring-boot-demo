package com.gringolito.springbootdemo.actuators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class OwnerInfoContributor implements InfoContributor {
    @Value("${app.owner}")
    private String projectOwner;

    @Value("${app.repository}")
    private String githubRepository;

    @Value("${app.contact.email}")
    private String contactEmail;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("project_owner", projectOwner)
                .withDetail("contact", contactEmail)
                .withDetail("github_repository", githubRepository);
    }
}
