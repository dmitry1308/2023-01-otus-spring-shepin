package ru.otus.spring.shepin.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dto.AuthorDto;
import ru.otus.spring.shepin.entity.Author;

@Service
@RequiredArgsConstructor
public class AuthorMapper {

    public  AuthorDto fromObjectToDto(Author author) {
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName());
    }
}
