package com.practise.test.service;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.level.UpdateLevelRequestDTO;
import com.practise.test.entity.Level;
import com.practise.test.model.level.Levelinfo;
import com.practise.test.repository.LevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelService {
    @Autowired
    private LevelRepository levelRepository;

    public AppResponseBase getAllLevels() {
        try {
            List<Level> levels = levelRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

            List<Levelinfo> levelInfoList = levels.stream()
                    .map(level -> new Levelinfo(
                            level.getId(),
                            level.getSkillId(),
                            level.getName(),
                            level.getDisplayName(),
                            level.getDescription(),
                            level.getImage(),
                            level.getSubQuestionNumber()
                    ))
                    .collect(Collectors.toList());

            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh sách cấp độ thành công",
                    levelInfoList,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server" + e.toString(),
                    null,
                    null
            );
        }
    }
    public AppResponseBase getLevelById(String id) {
        try {
            if (id.isEmpty() || id == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id không được để trống",
                        null,
                        null
                );
            }
            Level level = levelRepository.findById(id).orElse(null);
            if (level == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy cấp độ",
                        null,
                        null
                );
            }
            Levelinfo levelInfo = new Levelinfo(
                    level.getId(),
                    level.getSkillId(),
                    level.getName(),
                    level.getDisplayName(),
                    level.getDescription(),
                    level.getImage(),
                    level.getSubQuestionNumber()
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy cấp độ thành công",
                    levelInfo,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server" + e.toString(),
                    null,
                    null
            );
        }
    }
    public AppResponseBase getlistLevelOfSkillId(String skillId) {
        try {
            if (skillId.isEmpty() || skillId == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id không được để trống",
                        null,
                        null
                );
            }
            List<Level> levels = levelRepository.findBySkillId(skillId, Sort.by(Sort.Direction.ASC, "id"));
            List<Levelinfo> levelInfoList = levels.stream()
                    .map(level -> new Levelinfo(
                            level.getId(),
                            level.getSkillId(),
                            level.getName(),
                            level.getDisplayName(),
                            level.getDescription(),
                            level.getImage(),
                            level.getSubQuestionNumber()
                    ))
                    .collect(Collectors.toList());
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh sách cấp độ thành công",
                    levelInfoList,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server" + e.toString(),
                    null,
                    null
            );
        }
    }
    public AppResponseBase updateLevel (String levelId, UpdateLevelRequestDTO dataUpdateLevel) {
        try {
            if (levelId.isEmpty() || levelId == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id không được để trống",
                        null,
                        null
                );
            }
            if (dataUpdateLevel.getSubQuestionNumber() == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Số lượng câu hỏi không được để trống",
                        null,
                        null
                );
            }
            Level level = levelRepository.findById(levelId).orElse(null);
            if (level == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy cấp độ",
                        null,
                        null
                );
            }
            level.setImage(dataUpdateLevel.getImage());
            level.setDescription(dataUpdateLevel.getDescription());
            level.setSubQuestionNumber(dataUpdateLevel.getSubQuestionNumber());
            levelRepository.save(level);

            Levelinfo levelInfo = new Levelinfo(
                    level.getId(),
                    level.getSkillId(),
                    level.getName(),
                    level.getDisplayName(),
                    level.getDescription(),
                    level.getImage(),
                    level.getSubQuestionNumber()
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Cập nhật cấp độ thành công",
                    levelInfo,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server" + e.toString(),
                    null,
                    null
            );
        }
    }
}
