package learningprojectbackend.studies.service;

import learningprojectbackend.auth.exception.NoAuthorizationToAccessResourcesException;
import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.studies.controller.tag.CreateTagDto;
import learningprojectbackend.studies.controller.tag.TagDto;
import learningprojectbackend.studies.controller.tag.UpdateTag;
import learningprojectbackend.studies.exception.TagNotFoundException;
import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.repository.TagRepository;
import learningprojectbackend.studies.service.entity.tag.Tag;
import learningprojectbackend.studies.service.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final JwtTokenDetailsService jwtTokenDetailsService;
    private final UserService userService;
    private final TagRepository tagRepository;
    private final ModelMapper mapper;

    public List<TagDto> findAllTagOfUser() {
        return mapper.toTagDto(
                tagRepository.findAllByUserId(jwtTokenDetailsService.getUserIdFromJWTToken())
        );
    }

    public TagDto findTagById(Long tagId) {
        return mapper.toTagDto(isTheUserTheOwner(tagId));
    }

    private Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new TagNotFoundException(tagId));
    }

    private Tag isTheUserTheOwner(Long tagId) {
        Long userId = jwtTokenDetailsService.getUserIdFromJWTToken();
        Tag tag = getTagById(tagId);
        if (userId.equals(tag.getUser().getId())) {
            return tag;
        } else {
            throw new NoAuthorizationToAccessResourcesException("No Authorization to retrieve tag with id: " + tagId);
        }
    }

    public void deleteTagById(Long tagId) {
        tagRepository.delete(isTheUserTheOwner(tagId));
    }

    @Transactional
    public void updateTagById(UpdateTag updateTag, Long tagId) {
        Tag tag = isTheUserTheOwner(tagId);
        tag.setName(updateTag.getName());
        tag.setBackgroundColor(updateTag.getBackgroundColor());
        tag.setTextColor(updateTag.getTextColor());
    }

    private User getTheUser() {
        return userService.getUserByIdWithTags(jwtTokenDetailsService.getUserIdFromJWTToken());
    }

    @Transactional
    public TagDto createNewTag(CreateTagDto createTagDto) {
        User user = getTheUser();
        Tag tagToSave = mapper.toTag(createTagDto);
        user.addTag(tagToSave);
        return mapper.toTagDto(tagRepository.save(tagToSave));
    }
}
