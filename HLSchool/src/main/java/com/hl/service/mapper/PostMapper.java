package com.hl.service.mapper;

import com.hl.domain.*;
import com.hl.service.dto.PostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Post and its DTO PostDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PostMapper extends EntityMapper<PostDTO, Post> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    PostDTO toDto(Post post);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "comments", ignore = true)
    Post toEntity(PostDTO postDTO);

    default Post fromId(Long id) {
        if (id == null) {
            return null;
        }
        Post post = new Post();
        post.setId(id);
        return post;
    }
}
