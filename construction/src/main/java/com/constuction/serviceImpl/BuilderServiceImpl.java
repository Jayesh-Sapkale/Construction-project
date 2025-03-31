package com.constuction.serviceImpl;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateBuilderRequestDto;
import com.constuction.dto.response.CreateBuilderResponseDto;
import com.constuction.entity.Builder;
import com.constuction.exceptions.ConstructionException;
import com.constuction.repository.BuilderRepository;
import com.constuction.service.BuilderService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuilderServiceImpl implements BuilderService {
    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final BuilderRepository builderRepository;

    @Override
    public CreateBuilderResponseDto createBuilder(CreateBuilderRequestDto builderRequestDto) throws ConstructionException {
        log.info("START --> BuilderServiceImpl.createBuilder()");

        List<Builder> existingBuilders = builderRepository.findByFirstNameAndLastNameAndMobileNumber(builderRequestDto.getBasicDetails().getFirstName()
                , builderRequestDto.getBasicDetails().getLastName()
                , builderRequestDto.getBasicDetails().getMobileNumber());

        if (!existingBuilders.isEmpty())
            throw new ConstructionException("builder already exist");

        Builder savedBuilder = entityRequestBuilder.convertBuilderEntityToDto(builderRequestDto);
        log.info("START --> BuilderServiceImpl.createBuilder()");
        return entityResponseBuilder.convertBuilderEntityToDto(savedBuilder.getId());
    }

    @Override
    public CreateBuilderResponseDto updateBuilder(CreateBuilderRequestDto BuilderRequestDto) {
        return null;
    }

    @Override
    public List<CreateBuilderResponseDto> getAllBuilders() {
        log.info("START --> BuilderServiceImpl.getAllBuilders()");
        List<Builder> builders = builderRepository.findAll();
        log.info("START --> BuilderServiceImpl.getAllBuilders()");
        return builders.stream()
                .map(
                        builder -> entityResponseBuilder.convertBuilderEntityToDto(builder.getId())
                ).toList();
    }

    @Override
    public ApiResponseDto deleteBuilder(Long id) throws ConstructionException {
        log.info("START --> BuilderServiceImpl.deleteBuilder()");
        Builder savedBuilder = builderRepository.findById(id).orElseThrow(() -> new ConstructionException("Builder not found with id: " + id));
        savedBuilder.setIsDeleted(Boolean.TRUE);
        Builder deletedBuilder = builderRepository.save(savedBuilder);
        log.info("END --> BuilderServiceImpl.deleteBuilder()");
        return new ApiResponseDto("SUCCESS", deletedBuilder, "Builder deleted successfully");
    }
}
