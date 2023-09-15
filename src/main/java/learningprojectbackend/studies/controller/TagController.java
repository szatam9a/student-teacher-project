package learningprojectbackend.studies.controller;

import learningprojectbackend.studies.controller.dto.tag.CreateTagDto;
import learningprojectbackend.studies.controller.dto.tag.TagDto;
import learningprojectbackend.studies.controller.dto.tag.UpdateTag;
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
    public TagDto createNewTag(@RequestBody CreateTagDto createTagDto) {
        return tagService.createNewTag(createTagDto);
    }

    @PutMapping("/tag/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTag(@RequestBody UpdateTag updateTag, @PathVariable Long tagId) {
        tagService.updateTagById(updateTag, tagId);
    }

    @DeleteMapping("/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTagById(@PathVariable Long tagId) {
        tagService.deleteTagById(tagId);
    }
}
