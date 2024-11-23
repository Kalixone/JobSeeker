package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.*;
import com.cvenjoyer.cv_enjoyer.exceptions.EntityNotFoundException;
import com.cvenjoyer.cv_enjoyer.exceptions.LocationNotFoundException;
import com.cvenjoyer.cv_enjoyer.mapper.JobMapper;
import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.model.NominatimResponse;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.JobRepository;
import com.cvenjoyer.cv_enjoyer.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final RestTemplate restTemplate;

    @Override
    public List<JobDto> getAllJobs(Authentication authentication) {
        User authenticatedUser = (User) authentication.getPrincipal();

        return jobRepository.findByUser(authenticatedUser)
                .stream()
                .map(jobMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobDto updateFeedBackDate(Long id, UpdateFeedBackDateRequestDto updateFeedBackDateRequestDto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found by ID: " + id));

        if (updateFeedBackDateRequestDto.feedBackDate() != null) {
            job.setFeedBackDate(updateFeedBackDateRequestDto.feedBackDate());
        }

        jobRepository.save(job);

        return jobMapper.toDto(job);
    }

    @Override
    public JobDto findById(Long id) {
        return jobRepository.findById(id).map(jobMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void checkAndExpireOldApplications() {
        LocalDate now = LocalDate.now();

        jobRepository.findAll().stream()
                .filter(j -> j.getApplicationDate() != null &&
                        j.getJobStatus() == Job.JobStatus.APPLIED &&
                        j.getApplicationDate().plusDays(14).isBefore(now))
                .forEach(j -> {
                    j.setJobStatus(Job.JobStatus.EXPIRED);
                    jobRepository.save(j);
                });
    }

    @Override
    public JobDto updateJobStatus(Long id, UpdateJobStatusRequestDto updateJobStatusRequestDto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found by ID: " + id));

        if (updateJobStatusRequestDto.jobStatus() != null) {
            job.setJobStatus(updateJobStatusRequestDto.jobStatus());
        }
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public List<JobDto> getJobsSortedBySalary() {
        Comparator<Job> jobComparator = new Comparator<>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o2.getSalary().compareTo(o1.getSalary());
            }
        };

        return jobRepository.findAll().stream()
                .sorted(jobComparator)
                .map(jobMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobDto> getOnlyAppliedStatus() {
        return jobRepository.findByJobStatus(Job.JobStatus.APPLIED)
                .stream()
                .map(jobMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<JobDto> getOnlyRejectedStatus() {
        return jobRepository.findByJobStatus(Job.JobStatus.REJECTED)
                .stream()
                .map(jobMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<JobDto> getOnlyExpiredStatus() {
        return jobRepository.findByJobStatus(Job.JobStatus.EXPIRED)
                .stream()
                .map(jobMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public JobDto createJob(Authentication authentication, CreateJobRequestDto createJobRequestDto) {
        Job newJob = jobMapper.toModel(createJobRequestDto);
        newJob.setJobStatus(Job.JobStatus.APPLIED);

        User authenticatedUser = (User) authentication.getPrincipal();
        newJob.setUser(authenticatedUser);

        try {
            NominatimResponse firstApiResponse = getLocation(createJobRequestDto.location());
            double lat1 = firstApiResponse.getLatitude();
            double lon1 = firstApiResponse.getLongitude();

            String city = authenticatedUser.getCity();
            NominatimResponse secondApiResponse = getLocation(city);
            double lat2 = secondApiResponse.getLatitude();
            double lon2 = secondApiResponse.getLongitude();

            Double distance = calculateDistance(lat1, lon1, lat2, lon2);
            newJob.setKilometers((double) Math.round(distance));
        } catch (LocationNotFoundException e) {
            System.err.println("Error occurred while fetching data: " + e.getMessage());
            throw e;
        }

        jobRepository.save(newJob);
        return jobMapper.toDto(newJob);
    }

    @Override
    public Double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public NominatimResponse getLocation(String location) {
        String url = "https://nominatim.openstreetmap.org/search?q=" + location + "&format=json";
        try {
            List<NominatimResponse> responseList = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<NominatimResponse>>() {}).getBody();

            if (responseList == null || responseList.isEmpty()) {
                throw new LocationNotFoundException("Location not found: " + location);
            }

            return responseList.get(0);
        } catch (Exception e) {
            throw new LocationNotFoundException("Error fetching location: " + location, e);
        }
    }

    @Override
    public List<JobDto> findByKilometersBetween(Double from, Double to) {
        List<JobDto> collect = jobRepository.findByKilometersBetween(from, to)
                .stream()
                .map(jobMapper::toDto).collect(Collectors.toList());
        return collect;
    }

    @Override
    public JobDto updateCompanyName(Long id, UpdateCompanyNameRequestDto updateCompanyNameRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setCompanyName(updateCompanyNameRequestDto.companyName());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updatePosition(Long id, UpdatePositionRequestDto updatePositionRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setPosition(updatePositionRequestDto.position());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updateLocation(Authentication authentication, Long id, UpdateLocationRequestDto updateLocationRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        String oldLocation = job.getLocation();

        job.setLocation(updateLocationRequestDto.location());

        try {
            NominatimResponse newLocationResponse = getLocation(updateLocationRequestDto.location());
            double newLat = newLocationResponse.getLatitude();
            double newLon = newLocationResponse.getLongitude();

            User authenticatedUser = (User) authentication.getPrincipal();
            String userCity = authenticatedUser.getCity();

            NominatimResponse userLocationResponse = getLocation(userCity);
            double userLat = userLocationResponse.getLatitude();
            double userLon = userLocationResponse.getLongitude();

            double distance = calculateDistance(newLat, newLon, userLat, userLon);
            job.setKilometers((double) Math.round(distance));
        } catch (LocationNotFoundException e) {
            System.err.println("Error occurred while fetching location: " + e.getMessage());
            throw e;
        }

        jobRepository.save(job);

        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updateSalary(Long id, UpdateSalaryRequestDto updateSalaryRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setSalary(updateSalaryRequestDto.salary());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updateApplicationDate(Long id, UpdateApplicationDateRequestDto updateApplicationDateRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setApplicationDate(updateApplicationDateRequestDto.applicationDate());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updateJobType(Long id, UpdateJobTypeRequestDto updateJobTypeRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setJobType(updateJobTypeRequestDto.jobType());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updateLink(Long id, UpdateLinkRequestDto updateLinkRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setLink(updateLinkRequestDto.link());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updateCompanyWebsite(Long id, UpdateCompanyWebsiteRequestDto updateCompanyWebsiteRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setCompanyWebsite(updateCompanyWebsiteRequestDto.companyWebsite());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updateContactEmail(Long id, UpdateContactEmailRequestDto updateContactEmailRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setContactEmail(updateContactEmailRequestDto.contactEmail());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updateKilometers(Long id, UpdateKilometersRequestDto updateKilometersRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setKilometers(updateKilometersRequestDto.kilometers());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto updateNotes(Long id, UpdateNotesRequestDto updateNotesRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));

        job.setNotes(updateNotesRequestDto.notes());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public JobDto updateTags(Long id, UpdateTagsRequestDto updateTagsRequestDto) {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));
        Set<String> tags = job.getTags();
        tags.addAll(updateTagsRequestDto.tags());
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }
}
