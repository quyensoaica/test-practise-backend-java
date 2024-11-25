package com.practise.test.database;

import com.practise.test.entity.GroupRole;
import com.practise.test.entity.Level;
import com.practise.test.entity.Skill;
import com.practise.test.entity.User;
import com.practise.test.repository.GroupRoleRepository;
import com.practise.test.repository.LevelRepository;
import com.practise.test.repository.SkillRepository;
import com.practise.test.repository.UserRepository;
import com.practise.test.utils.PasswordHandle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final GroupRoleRepository roleRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final LevelRepository levelRepository;
    private PasswordHandle passwordHandle = new PasswordHandle();

    public DataLoader(
            GroupRoleRepository roleRepository,
            UserRepository userRepository,
            SkillRepository skillRepository,
            LevelRepository levelRepository
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.levelRepository = levelRepository;
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
        String hashPassword = passwordHandle.hashPassword("123123");
        User adminUser = new User(
                "1",
                "admin@gmail.com",
                "admin",
                hashPassword,
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

        Level listeningPart1 = new Level(
                "listening-part-1",
                "listening",
                "Part 1",
                "Part 1",
                "In this part, you will hear EIGHT short recordings. The recordings will be played ONCE only. There is one question following each recording. For each question, choose the right answer A, B, C or D.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354158/f8glkjhqfh3zqxhpjtej.png",
                8
        );
        Level listeningPart2 = new Level(
                "listening-part-2",
                "listening",
                "Part 2",
                "Part 2",
                "In this part, you will hear THREE conversations. The conversations will be played ONCE only. There are four questions for each conversation. For each conversation, choose the right answer A, B, C or D.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354158/f8glkjhqfh3zqxhpjtej.png",
                12
        );
        Level listeningPart3 = new Level(
                "listening-part-3",
                "listening",
                "Part 3",
                "Part 3",
                "In this part, you will hear THREE talks or lectures. The talks or lectures will be played ONCE only. There are five questions for each talk or lecture. For each question, choose the right answer A, B, C or D.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354158/f8glkjhqfh3zqxhpjtej.png",
                15
        );
        Level readingPart1 = new Level(
                "reading-part-1",
                "reading",
                "Part 1",
                "Part 1",
                "For questions 1 - 10, you are to choose the one best answer A, B, C or D to each question. Answer all questions following a passage on the basic of what is stated or implied in that message.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354170/porws3ag9kdwg4pyyw5f.png",
                10
        );
        Level readingPart2 = new Level(
                "reading-part-2",
                "reading",
                "Part 2",
                "Part 2",
                "For questions 1 - 10, you are to choose the one best answer A, B, C or D to each question. Answer all questions following a passage on the basic of what is stated or implied in that message.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354170/porws3ag9kdwg4pyyw5f.png",
                10
        );
        Level readingPart3 = new Level(
                "reading-part-3",
                "reading",
                "Part 3",
                "Part 3",
                "For questions 21 - 30, you are to choose the one best answer A, B, C or D to each question. Answer all questions following a passage on the basic of what is stated or implied in that message.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354170/porws3ag9kdwg4pyyw5f.png",
                10
        );
        Level readingPart4 = new Level(
                "reading-part-4",
                "reading",
                "Part 4",
                "Part 4",
                "For questions 31 - 40, you are to choose the one best answer A, B, C or D to each question. Answer all questions following a passage on the basic of what is stated or implied in that message.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354170/porws3ag9kdwg4pyyw5f.png",
                10
        );
        Level writingPart1 = new Level(
                "writing-part-1",
                "writing",
                "Part 1",
                "Part 1",
                "You should spend about 20 minutes on this task",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354184/p0rvqerqzdnqlc7gclgt.png",
                0
        );
        Level writingPart2 = new Level(
                "writing-part-2",
                "writing",
                "Part 2",
                "Part 2",
                "You should spend about 40 minutes on this task",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354184/p0rvqerqzdnqlc7gclgt.png",
                0
        );
        Level speakingPart1 = new Level(
                "speaking-part-1",
                "speaking",
                "Part 1",
                "Part 1",
                "You have 3 minute to complete this level. It wil start after 1 minute.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354194/ruenzkglapqvxtkmdz7y.png",
                0
        );
        Level speakingPart2 = new Level(
                "speaking-part-2",
                "speaking",
                "Part 2",
                "Part 2",
                "You have 4 minute to complete this level. It wil start after 1 minute.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354194/ruenzkglapqvxtkmdz7y.png",
                0
        );
        Level speakingPart3 = new Level(
                "speaking-part-3",
                "speaking",
                "Part 3",
                "Part 3",
                "You have 5 minute to complete this level. It wil start after 1 minute.",
                "https://res.cloudinary.com/domgx4abl/image/upload/v1728354194/ruenzkglapqvxtkmdz7y.png",
                0
        );
        List<Level> checkLevelIsEmpty = levelRepository.findAll();
        if (checkLevelIsEmpty.isEmpty()) {
            levelRepository.saveAll(List.of(
                    listeningPart1, listeningPart2, listeningPart3,
                    readingPart1, readingPart2, readingPart3, readingPart4,
                    writingPart1, writingPart2,
                    speakingPart1, speakingPart2, speakingPart3
            ));
        }

        System.out.println("Dữ liệu khởi tạo thành công!");
    }
}
