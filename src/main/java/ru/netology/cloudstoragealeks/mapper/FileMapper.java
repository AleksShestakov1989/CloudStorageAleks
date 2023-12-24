package ru.netology.cloudstoragealeks.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.netology.cloudstoragealeks.entity.CloudFile;
import ru.netology.cloudstoragealeks.dto.response.FileWebResponse;

@Component
@Mapper(componentModel = "spring")
public interface FileMapper {
    FileWebResponse cloudFileToFileWebResponse(CloudFile cloudFile);
}
