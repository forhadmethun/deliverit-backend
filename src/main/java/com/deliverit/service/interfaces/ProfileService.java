package com.deliverit.service.interfaces;

import com.deliverit.utility.request.ProfileRequest;
import com.deliverit.utility.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest request);
    ProfileResponse getProfile(String userName);
}
