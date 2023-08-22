package kr.klti.projectklti.service;

import kr.klti.projectklti.domain.Lecture;
import kr.klti.projectklti.dto.LectureDto;
import kr.klti.projectklti.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<LectureDto> allList(){
        List<Lecture> lectures = lectureRepository.findAll();
        for(Lecture l : lectures){
            System.out.println(l.getLectName());
        }
        return lectures.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private LectureDto mapToDto(Lecture lecture) {
        return LectureDto.builder()
                .lectNo(lecture.getLectNo())
                .lectName(lecture.getLectName())
                .lectDesc(lecture.getLectDesc())
                .lectStartDate(lecture.getLectStartDate())
                .lectEndDate(lecture.getLectEndDate())
                .lectStatus(lecture.getLectStatus())
                .build();
    }
}
