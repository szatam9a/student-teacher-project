package learningprojectbackend.studies.controller.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterRequestDetails {
    private String filterOn;
    private String filterType;
    private String filter;
}
