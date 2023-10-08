package learningprojectbackend.studies.controller.course;

import lombok.Getter;

@Getter
public class PaginationRequest {
    private FilterRequestDetails filters;
    private int pageNumber;
    private int pageSize;
    private SortRequestDetails sortRequestDetails;
}
