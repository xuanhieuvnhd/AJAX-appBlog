package com.service;

import com.model.Blog;
import com.model.Category;

public interface BlogService extends GeneralService<Blog> {
    Iterable<Blog> findAllByCategory(Category category);

    Iterable<Blog> findByNameBlogContaining(String nameBlog);
}
