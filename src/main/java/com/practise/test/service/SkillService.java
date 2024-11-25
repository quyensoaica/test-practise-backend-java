package com.practise.test.service;

import com.practise.test.dto.AppData.AppErrorBase;
import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.skill.UpdateSkillRequestDTO;
import com.practise.test.entity.Skill;
import com.practise.test.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public AppResponseBase getAllSkills() {
        try {
            List<Skill> skills = skillRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh sách kỹ năng thành công",
                    skills,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server" + e.toString(),
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }

    public AppResponseBase getSkillById(String id) {
        try {
            if (id.isEmpty() || id == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id không được để trống",
                        null,
                        new AppErrorBase("Id không được để trống", "Id không được để trống")
                );
            }
            Skill skill = skillRepository.findById(id).orElse(null);
            if (skill == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy kỹ năng",
                        null,
                        new AppErrorBase("Không tìm thấy kỹ năng", "Không tìm thấy kỹ năng")
                );
            }
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy kỹ năng thành công",
                    skill,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server" + e.toString(),
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }

    public AppResponseBase updateSkill(String skillId, UpdateSkillRequestDTO updateSkillData) {
        try {
            if (skillId.isEmpty() || skillId == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id không được để trống",
                        null,
                        new AppErrorBase("Id không được để trống", "Id không được để trống")
                );
            }
            if (updateSkillData == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Dữ liệu không được để trống",
                        null,
                        new AppErrorBase("Dữ liệu không được để trống", "Dữ liệu không được để trống")
                );
            }
            Skill skill = skillRepository.findById(skillId).orElse(null);
            if (skill == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy kỹ năng",
                        null,
                        new AppErrorBase("Không tìm thấy kỹ năng", "Không tìm thấy kỹ năng")
                );
            }
            skill.setImage(updateSkillData.getImage());
            skill.setDescription(updateSkillData.getDescription());
            skillRepository.save(skill);
            return new AppResponseBase(
                    200,
                    true,
                    "Cập nhật kỹ năng thành công",
                    skill,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server" + e.toString(),
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
}
