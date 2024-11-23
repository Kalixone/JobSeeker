package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.*;
import com.cvenjoyer.cv_enjoyer.model.NominatimResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface JobService {
    JobDto createJob(Authentication authentication, CreateJobRequestDto createJobRequestDto);
    List<JobDto> getAllJobs(Authentication authentication);
    JobDto findById(Long id);
    void checkAndExpireOldApplications();
    List<JobDto> getJobsSortedBySalary();
    List<JobDto> getOnlyAppliedStatus();
    List<JobDto> getOnlyRejectedStatus();
    List<JobDto> getOnlyExpiredStatus();
    Double calculateDistance(double lat1, double lon1, double lat2, double lon2);
    NominatimResponse getLocation(String location);
    List<JobDto> findByKilometersBetween(Double from, Double to);
    JobDto updateCompanyName(Long id, UpdateCompanyNameRequestDto updateCompanyNameRequestDto);
    JobDto updatePosition(Long id, UpdatePositionRequestDto updatePositionRequestDto);
    JobDto updateLocation(Authentication authentication, Long id, UpdateLocationRequestDto updateLocationRequestDto);
    JobDto updateSalary(Long id, UpdateSalaryRequestDto updateSalaryRequestDto);
    JobDto updateApplicationDate(Long id, UpdateApplicationDateRequestDto updateApplicationDateRequestDto);
    JobDto updateJobStatus(Long id, UpdateJobStatusRequestDto updateJobStatusRequestDto);
    JobDto updateJobType(Long id, UpdateJobTypeRequestDto updateJobTypeRequestDto);
    JobDto updateFeedBackDate(Long id, UpdateFeedBackDateRequestDto updateFeedBackDateRequestDto);
    JobDto updateLink(Long id, UpdateLinkRequestDto updateLinkRequestDto);
    JobDto updateCompanyWebsite(Long id, UpdateCompanyWebsiteRequestDto updateCompanyWebsiteRequestDto);
    JobDto updateContactEmail(Long id, UpdateContactEmailRequestDto updateContactEmailRequestDto);
    JobDto updateKilometers(Long id, UpdateKilometersRequestDto updateKilometersRequestDto);
    JobDto updateTags(Long id, UpdateTagsRequestDto updateTagsRequestDto);
    JobDto updateNotes(Long id, UpdateNotesRequestDto updateNotesRequestDto);
    void deleteById(Long id);
}
