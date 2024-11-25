package com.practise.test.database;

import com.practise.test.entity.GroupRole;
import com.practise.test.entity.Skill;
import com.practise.test.entity.User;
import com.practise.test.repository.GroupRoleRepository;
import com.practise.test.repository.SkillRepository;
import com.practise.test.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final GroupRoleRepository roleRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public DataLoader(GroupRoleRepository roleRepository, UserRepository userRepository, SkillRepository skillRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public void run(String... args) {
        // Tạo dữ liệu mẫu
        GroupRole adminRole = new GroupRole(
                "1",
                "admin",
                "Quản trị viên",
                "Quản trị viên hệ thống",
                false,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        GroupRole examinerRole = new GroupRole(
                "2",
                "examiner",
                "Người chấm thi",
                "Người chấm thi",
                false,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        GroupRole contestantRole = new GroupRole(
                "3",
                "contestant",
                "Thí sinh",
                "Thí sinh",
                false,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        List<GroupRole> checkGroupRoleIsEmpty =  roleRepository.findAll();
        if (checkGroupRoleIsEmpty.isEmpty()) {
            roleRepository.saveAll(List.of(adminRole, examinerRole, contestantRole));
        }
        // Example Data for User
        User adminUser = new User(
                "1",
                "admin@gmail.com",
                "admin",
                "123123",
                "Admin",
                "1",
                "0987654321",
                "10/10/2003",
                "M",
                null,
                null,
                false,
                false,
                false
        );
        adminUser.setGroupRole(adminRole);
        List<User> checkUserIsEmpty = userRepository.findAll();
        if (checkUserIsEmpty.isEmpty()) {
            userRepository.save(adminUser);
        }

        // Example Data for Skill
        Skill listeningSkill = new Skill(
                "listening",
                "Listening",
                "Listening",
                "You will listen to a number of different recordings and you will have to answer questions based on what you hear. There will be time for you to read the questions and check your work.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354158/f8glkjhqfh3zqxhpjtej.png",
                1,
                47
        );
        Skill readingSkill = new Skill(
                "reading",
                "Reading",
                "Reading",
                "In this section, you will read several passages. Each one is followed by several questions about it.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354170/porws3ag9kdwg4pyyw5f.png",
                2,
                60
        );
        Skill writingSkill = new Skill(
                "writing",
                "Writing",
                "Writing",
                "There are three part in this level with three question. For each question in this level will have 1 minute for you to prepare.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354184/p0rvqerqzdnqlc7gclgt.png",
                3,
                60
        );
        Skill speakingSkill = new Skill(
                "speaking",
                "Speaking",
                "Speaking",
                "You have to 60 minutes to completed two task of writing skill.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354194/ruenzkglapqvxtkmdz7y.png",
                4,
                15
        );
        List<Skill> checkSkillIsEmpty = skillRepository.findAll();
        if (checkSkillIsEmpty.isEmpty()) {
            skillRepository.saveAll(List.of(listeningSkill, readingSkill, writingSkill, speakingSkill));
        }
        System.out.println("Dữ liệu khởi tạo thành công!");
    }
}
