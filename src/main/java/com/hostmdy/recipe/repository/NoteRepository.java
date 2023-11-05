package com.hostmdy.recipe.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.Note;

public interface NoteRepository extends CrudRepository<Note, Long>{

}
