package com.deliverit.utility;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Getter
public class UtilityService {
    @Value("${app.profile}")
    private String appProfile;

    @Value("${jwt.secret}")
    private String secret;
}
