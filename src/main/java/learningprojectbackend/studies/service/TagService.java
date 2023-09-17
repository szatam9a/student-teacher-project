package learningprojectbackend.studies.service;

import learningprojectbackend.auth.exception.NoAuthorizationToAccessResourcesException;
import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.studies.controller.tag.CreateTagRequest;
import learningprojectbackend.studies.controller.tag.TagDto;
import learningprojectbackend.studies.controller.tag.UpdateTagRequest;
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

    public TagDto findTagById(Long tagId) {
        return mapper.toTagDto(authenticateUserTag(getTagById(tagId)));
    }

    public List<TagDto> findAllTagOfUser() {
        return mapper.toTagDto(
                tagRepository.findAllByUserId(jwtTokenDetailsService.getUserIdFromJWTToken())
        );
    }

    @Transactional
    public TagDto createNewTag(CreateTagRequest createTagRequest) {
        User user = getOwnerUser();
        Tag tagToSave = mapper.toTag(createTagRequest);
        user.addTag(tagToSave);
        return mapper.toTagDto(tagRepository.save(tagToSave));
    }

    @Transactional
    public void updateTagById(UpdateTagRequest updateTagRequest, Long tagId) {
        Tag tag = authenticateUserTag(getTagById(tagId));
        tag.setName(updateTagRequest.getName());
        tag.setBackgroundColor(updateTagRequest.getBackgroundColor());
        tag.setTextColor(updateTagRequest.getTextColor());
    }

    public void deleteTagById(Long tagId) {
        tagRepository.delete(authenticateUserTag(getTagById(tagId)));
    }

    private Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new TagNotFoundException(tagId));
    }

    private User getOwnerUser() {
        return userService.getUserByIdWithTags(jwtTokenDetailsService.getUserIdFromJWTToken());
    }

    private Tag authenticateUserTag(Tag tag) {
        if (isUserAuthorized(tag)) {
            return tag;
        } else {
            throw new NoAuthorizationToAccessResourcesException("No Authorization to retrieve tag with id: " + tag.getId());
        }
    }

    private boolean isUserAuthorized(Tag tag) {
        return (getOwnerUser().getId().equals(tag.getUser().getId()));
    }
}
