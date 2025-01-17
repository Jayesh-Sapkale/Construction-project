package com.constuction.serviceImpl;

import com.constuction.dto.request.CreateBuilderRequestDto;
import com.constuction.dto.response.CreateBuilderResponseDto;
import com.constuction.entity.Builder;
import com.constuction.repository.AdminRepository;
import com.constuction.repository.BuilderRepository;
import com.constuction.service.BuilderService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuilderServiceImpl implements BuilderService {
    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final BuilderRepository builderRepository;

    @Override
    public CreateBuilderResponseDto createBuilder(CreateBuilderRequestDto builderRequestDto) {
        Builder savedBuilder = entityRequestBuilder.convertBuilderEntityToDto(builderRequestDto);
        return entityResponseBuilder.convertBuilderEntityToDto(savedBuilder.getId());
    }

    @Override
    public CreateBuilderResponseDto updateBuilder(CreateBuilderRequestDto BuilderRequestDto) {
        return null;
    }

    @Override
    public List<CreateBuilderResponseDto> getAllBuilders() {
        List<Builder> builders = builderRepository.findAll();
        return builders.stream()
                .map(
                        builder -> entityResponseBuilder.convertBuilderEntityToDto(builder.getId())
                ).toList();
    }
}
