package com.board.project.configuration.mapper;

public interface GenericMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
