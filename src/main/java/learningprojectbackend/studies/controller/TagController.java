package learningprojectbackend.studies.controller;

import learningprojectbackend.studies.controller.tag.CreateTagRequest;
import learningprojectbackend.studies.controller.tag.TagDto;
import learningprojectbackend.studies.controller.tag.UpdateTagRequest;
import learningprojectbackend.studies.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagDto> findAllTag() {
        return tagService.findAllTagOfUser();
    }

    @GetMapping("/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto findTagById(@PathVariable Long tagId) {
        return tagService.findTagById(tagId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto createNewTag(@RequestBody CreateTagRequest createTagRequest) {
        return tagService.createNewTag(createTagRequest);
    }

    @PutMapping("/tag/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTag(@RequestBody UpdateTagRequest updateTagRequest, @PathVariable Long tagId) {
        tagService.updateTagById(updateTagRequest, tagId);
    }

    @DeleteMapping("/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTagById(@PathVariable Long tagId) {
        tagService.deleteTagById(tagId);
    }
}
