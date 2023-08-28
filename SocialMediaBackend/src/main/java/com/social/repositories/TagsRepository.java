package com.social.repositories;

import com.social.pojo.Tags;
import java.util.Optional;

public interface TagsRepository {

    Tags save(Tags tag);

    Optional<Tags> getByName(String name);

    Optional<Tags> getById(Integer id);
    
    boolean delete(Tags tags);
    
    boolean deleteById(Integer tagsId);
}
