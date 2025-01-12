package com.practise.test.service;

import com.practise.test.dto.AppData.AppErrorBase;
import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.exam.ContinueExamDTO;
import com.practise.test.dto.exam.CurrentExamDTO;
import com.practise.test.dto.exam.ExamScoreDTO;
import com.practise.test.dto.exam.ISpeakingQuestionSubmitDTO;
import com.practise.test.dto.exam.QuestionResultResponseDTO;
import com.practise.test.dto.exam.SubmitSkillRequestDTO;
import com.practise.test.dto.question.GroupedQuestionDTO;
import com.practise.test.dto.question.QuestionDetailResponseDTO;
import com.practise.test.entity.*;
import com.practise.test.model.category.CategoryShortInfo;
import com.practise.test.model.exam.CurrentExam;
import com.practise.test.model.exam.QuestionByLevel;
import com.practise.test.model.examQuestion.ExamQuestionData;
import com.practise.test.model.examSkillStatus.CurrentSkillStatus;
import com.practise.test.model.examSkillStatus.ScoreOfSkill;
import com.practise.test.model.level.LevelShortInfo;
import com.practise.test.model.level.Levelinfo;
import com.practise.test.model.question.AnswerNotChoose;
import com.practise.test.model.question.QuestionOfExam;
import com.practise.test.model.question.QuestionResult;
import com.practise.test.model.question.ResultOfQuestion;
import com.practise.test.model.question.SubQuestionAnswer;
import com.practise.test.model.question.SubQuestionData;
import com.practise.test.model.question.SubQuestionNotChoose;
import com.practise.test.model.skill.SkillExprired;
import com.practise.test.model.skill.SkillShortInfo;
import com.practise.test.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Autowired
    private ExamSkillStatusRepository examSkillStatusRepository;

    @Autowired
    private ExamResultReadingRepository examResultReadingRepository;

    @Autowired
    private ExamResultWritingRepository examResultWritingRepository;

    @Autowired
    private ExamResultListeningRepository examResultListeningRepository;

    @Autowired
    private ExamResultSpeakingRepository examResultSpeakingRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SubQuestionRepository subQuestionRepository;

    private boolean isEndTimeExpired(String endTimeString) {
        long endTimeMillis = 0;

        try {
            endTimeMillis = Long.parseLong(endTimeString); // Chuyển đổi chuỗi timestamp thành long
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Xử lý lỗi nếu không phải là số hợp lệ
            return false; // Nếu không thể chuyển đổi thành long, trả về false
        }

        Date endTime = new Date(endTimeMillis); // Tạo đối tượng Date từ timestamp

        // So sánh thời gian với thời gian hiện tại
        return endTime.before(new Date()); // Kiểm tra xem thời gian kết thúc có trước thời gian hiện tại không
    }

    @Transactional
    public AppResponseBase startNewExam(String userid) {
        try {
            List<Level> levels = levelRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

            if (levels == null || levels.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "No levels found",
                        null,
                        new AppErrorBase("No levels found", "Levels data is empty")
                );
            }

            List<Object[]> results = questionRepository.findLevelIdAndQuestionIds();

            // Group data by levelId
            Map<String, List<String>> groupedData = results.stream()
                    .collect(Collectors.groupingBy(
                            result -> (String) result[0], // levelId
                            Collectors.mapping(
                                    result -> (String) result[1], // questionId
                                    Collectors.toList()
                            )
                    ));

            List<GroupedQuestionDTO> groupedQuestions = groupedData.entrySet().stream()
                    .<GroupedQuestionDTO>map(entry -> new GroupedQuestionDTO(entry.getKey(), entry.getValue()))
                    .toList();

            if(groupedQuestions == null || groupedQuestions.isEmpty() || groupedQuestions.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "No questions found",
                        null,
                        new AppErrorBase("No questions found", "Questions data is empty")
                );
            }

            Random random = new Random();
            List<QuestionByLevel> questionByLevels = groupedQuestions.stream()
                    .map(level -> {
                        List<String> questionIds = level.getQuestionIds();
                        // Nếu có ít nhất một câu hỏi, lấy ngẫu nhiên câu hỏi
                        String randomQuestionId = questionIds.isEmpty() ? null : questionIds.get(random.nextInt(questionIds.size()));
                        return new QuestionByLevel(level.getLevelId(), randomQuestionId);
                    })
                    .collect(Collectors.toList());

            Exam newExam = new Exam();

            String examId = UUID.randomUUID().toString();
            newExam.setUserId(userid);
            newExam.setId(examId);
            newExam.setStartTime(Long.toString(System.currentTimeMillis()));
            newExam.setEndTime(Long.toString(System.currentTimeMillis() + 200 * 60 * 1000));
            newExam.setExamCode("EXAM-12345");
            newExam.setCreatedBy(userid);
            newExam.setUpdatedBy(userid);
            examRepository.save(newExam);

            // Thêm các kỹ năng (skills) vào bảng ExamSkillStatus
            List<ExamSkillStatus> skillStatuses = Arrays.asList(
                new ExamSkillStatus(
                    UUID.randomUUID().toString(),
                    examId,
                    "listening",
                    Long.toString(System.currentTimeMillis()),
                    Long.toString(System.currentTimeMillis() + 50 * 60 * 1000),
                    "NOT_STARTED",
                    1,
                    0,
                    35
                ),
                new ExamSkillStatus(
                    UUID.randomUUID().toString(),
                    examId,
                    "reading",
                    Long.toString(System.currentTimeMillis()),
                    Long.toString(System.currentTimeMillis() + 50 * 60 * 1000),
                    "NOT_STARTED",
                    2,
                    0,
                    40
                ),
                new ExamSkillStatus(
                    UUID.randomUUID().toString(),
                    examId,
                    "writing",
                    Long.toString(System.currentTimeMillis()),
                    Long.toString(System.currentTimeMillis() + 50 * 60 * 1000),
                    "NOT_STARTED",
                    3,
                    0,
                    2
                ),
                new ExamSkillStatus(
                    UUID.randomUUID().toString(),
                    examId,
                    "speaking",
                    Long.toString(System.currentTimeMillis()),
                    Long.toString(System.currentTimeMillis() + 50 * 60 * 1000),
                    "NOT_STARTED",
                    4,
                    0,
                    3
                ));
            examSkillStatusRepository.saveAll(skillStatuses);

            // Thêm câu hỏi vào bảng ExamQuestion
            List<ExamQuestion> examQuestions = questionByLevels.stream()
                    .map(qb -> new ExamQuestion(UUID.randomUUID().toString(), examId, qb.getQuestionId(), qb.getLevelId()))
                    .collect(Collectors.toList());
            examQuestionRepository.saveAll(examQuestions);

            return new AppResponseBase(
                    200,
                    true,
                    "Exam created successfully",
                    newExam,
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

    public AppResponseBase getCurrentExam(String userid) {
        try {
                List<Exam> listExam = examRepository.findExamsByUserId(userid);
                if (listExam == null || listExam.isEmpty()) {
                    return new AppResponseBase(
                            404,
                            false,
                            "No exam found, please start a new exam",
                            null,
                            new AppErrorBase("No exam found, please start a new exam", "No exam found")
                    );
                }

                Exam lastExam = listExam.get(0);
                List<ExamSkillStatus> examSkillStatuses = examSkillStatusRepository.findSkillStatusesByExamId(lastExam.getId());

                if (examSkillStatuses == null || examSkillStatuses.isEmpty()) {
                    return new AppResponseBase(
                            404,
                            false,
                            "No exam skill status found, please try again later",
                            null,
                            new AppErrorBase("No exam skill status found, please try again later", "No exam skill status found")
                    );
                }

                String currentSkill = examSkillStatuses.get(0).getSkillId();
                boolean isDone = true;

            for (ExamSkillStatus examSkillStatus : examSkillStatuses) {
                // Chuyển startTime từ chuỗi timestamp thành long
                long startTime = Long.parseLong(examSkillStatus.getStartTime());
                long expiredTimeOfThisExamSkill = startTime + (long) examSkillStatus.getSkill().getExpiredTime() * 60 * 1000;

                // Kiểm tra nếu trạng thái là IN_PROGRESS và thời gian hết hạn chưa đến
                if ("IN_PROGRESS".equals(examSkillStatus.getStatus()) && expiredTimeOfThisExamSkill > System.currentTimeMillis()) {
                    currentSkill = examSkillStatus.getSkillId();
                    isDone = false;
                    break;
                }

                // Kiểm tra nếu trạng thái là NOT_STARTED
                if ("NOT_STARTED".equals(examSkillStatus.getStatus())) {
                    currentSkill = examSkillStatus.getSkillId();
                    isDone = false;
                    break;
                }
            }

            if (isDone || lastExam.isDone() || isEndTimeExpired(lastExam.getEndTime())) {
                    return new AppResponseBase(
                            400,
                            false,
                            "Your exam is expired, please start a new exam!",
                            null,
                            new AppErrorBase("Your exam is expired, please start a new exam!", "Your exam is expired, please start a new exam!")
                    );
                }

                String finalCurrentSkill = currentSkill;
                ExamSkillStatus skill = examSkillStatuses.stream()
                        .filter(examSkillStatus -> examSkillStatus.getSkillId().equals(finalCurrentSkill))
                        .findFirst()
                        .orElse(null);

                CurrentSkillStatus currentSkillStatus = new CurrentSkillStatus(
                        skill.getId(),
                        skill.getExamId(),
                        skill.getSkillId(),
                        skill.getStartTime(),
                        skill.getEndTime(),
                        skill.getStatus(),
                        skill.getOrder(),
                        new SkillExprired(
                                skill.getSkill().getName(),
                                skill.getSkill().getExpiredTime()
                        )
                );

                return new AppResponseBase(
                        200,
                        true,
                        "Continue with the last exam",
                        new CurrentExamDTO(
                                new CurrentExam(
                                        lastExam.getId(),
                                        lastExam.getUserId(),
                                        lastExam.getExamCode(),
                                        lastExam.getStartTime(),
                                        lastExam.getEndTime(),
                                        lastExam.isDeleted(),
                                        lastExam.isActive(),
                                        lastExam.isDone(),
                                        lastExam.getCreatedAt().toString(),
                                        lastExam.getUpdatedAt().toString()
                                ),
                                currentSkillStatus
                        ),
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

    public AppResponseBase participateExam(String userId) {
        try {
            AppResponseBase currentExamResponse = getCurrentExam(userId);
            if (!currentExamResponse.isSuccess()) {
                return startNewExam(userId);
            }
            return currentExamResponse;
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

    public AppResponseBase continueExam(String userId) {
        try {
            AppResponseBase currentExamResponse = getCurrentExam(userId);
            if (!currentExamResponse.isSuccess()) {
                return new AppResponseBase(
                        404,
                        false,
                        "No exam found, please start a new exam",
                        null,
                        new AppErrorBase("No exam found, please start a new exam", "No exam found")
                );
            }

            CurrentExamDTO currentExam = (CurrentExamDTO) currentExamResponse.getData();
            CurrentSkillStatus currentSkillStatus = currentExam.getCurrentSkill();
            CurrentExam lastExam = currentExam.getExam();
            List<ExamSkillStatus> examSkillStatuses = examSkillStatusRepository.findSkillStatusesByExamId(lastExam.getId());

            if (examSkillStatuses == null || examSkillStatuses.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "No exam skill status found, please try again later",
                        null,
                        new AppErrorBase("No exam skill status found, please try again later", "No exam skill status found")
                );
            }

            String currentSkill = examSkillStatuses.get(0).getSkillId();
            boolean isDone = true;

            for (ExamSkillStatus examSkillStatus : examSkillStatuses) {
                long startTime = Long.parseLong(examSkillStatus.getStartTime());
                long expiredTimeOfThisExamSkill = startTime + (long) examSkillStatus.getSkill().getExpiredTime() * 60 * 1000;

                if ("IN_PROGRESS".equals(examSkillStatus.getStatus()) && expiredTimeOfThisExamSkill > System.currentTimeMillis()) {
                    currentSkill = examSkillStatus.getSkillId();
                    isDone = false;
                    break;
                }

                if ("NOT_STARTED".equals(examSkillStatus.getStatus())) {
                    currentSkill = examSkillStatus.getSkillId();
                    isDone = false;
                    break;
                }
            }

            if (isDone || lastExam.isDone() || isEndTimeExpired(lastExam.getEndTime())) {
                return new AppResponseBase(
                        400,
                        false,
                        "Your exam is expired, please start a new exam!",
                        null,
                        new AppErrorBase("Your exam is expired, please start a new exam!", "Your exam is expired, please start a new exam!")
                );
            }

            String finalCurrentSkill = currentSkill;
            ExamSkillStatus currentSkillData = examSkillStatuses.stream()
                    .filter(examSkillStatus -> examSkillStatus.getSkillId().equals(finalCurrentSkill))
                    .findFirst()
                    .orElse(null);

            if(currentSkillData != null && currentSkillData.getStatus().equals("NOT_STARTED")) {
                currentSkillData.setStatus("IN_PROGRESS");
                currentSkillData.setStartTime(Long.toString(System.currentTimeMillis()));
                currentSkillData.setEndTime(Long.toString(System.currentTimeMillis() + (long) currentSkillData.getSkill().getExpiredTime() * 60 * 1000));
                examSkillStatusRepository.save(currentSkillData);
            }

            List<ExamQuestion> listExamQuestions = examQuestionRepository.getListQuestion(lastExam.getId(), currentSkillData.getSkillId());

            ExamQuestionData[] questionOfExams = listExamQuestions.stream()
                    .map(examQuestion -> {
                        Question question = examQuestion.getQuestion();
                        Levelinfo level = new Levelinfo(
                                question.getLevel().getId(),
                                question.getLevel().getSkillId(),
                                question.getLevel().getName(),
                                question.getLevel().getDisplayName(),
                                question.getLevel().getDescription(),
                                question.getLevel().getImage(),
                                question.getLevel().getSubQuestionNumber()
                        );
                        SkillShortInfo skill = new SkillShortInfo(
                                question.getSkillId(),
                                question.getSkill().getDisplayName(),
                                question.getSkill().getName()
                        );
                        List<SubQuestion> listSubQuestions = question.getSubQuestions();
                        SubQuestionNotChoose[] subQuestions = listSubQuestions.stream()
                                .map(subQuestion -> {
                                    List<Answer> listAnswers = subQuestion.getAnswers();
                                    AnswerNotChoose[] answers = listAnswers.stream()
                                            .map(answer -> new AnswerNotChoose(
                                                    answer.getId(),
                                                    answer.getAnswerContent(),
                                                    answer.getOrder()
                                            ))
                                            .toArray(AnswerNotChoose[]::new);
                                    return new SubQuestionNotChoose(
                                            subQuestion.getId(),
                                            subQuestion.getContent(),
                                            subQuestion.getOrder(),
                                            null,
                                            answers
                                    );
                                })
                                .toArray(SubQuestionNotChoose[]::new);

                        QuestionOfExam questionOfExam = new QuestionOfExam();
                        questionOfExam.setId(question.getId());
                        questionOfExam.setQuestionContent(question.getQuestionContent());
                        questionOfExam.setDescription(question.getDescription());
                        questionOfExam.setAttachedFile(question.getAttachedFile());
                        questionOfExam.setLevelId(question.getLevelId());
                        questionOfExam.setQuestionNote(question.getQuestionNote());
                        questionOfExam.setSkill(skill);
                        questionOfExam.setLevel(level);
                        questionOfExam.setSubQuestions(subQuestions);

                        return new ExamQuestionData(
                                examQuestion.getId(),
                                examQuestion.getExamId(),
                                examQuestion.getQuestionId(),
                                questionOfExam
                        );
                    })
                    .toArray(ExamQuestionData[]::new); // ⭐ Chuyển thành mảng ExamQuestionData[]

            if(listExamQuestions.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "No questions found, please try again later",
                        null,
                        new AppErrorBase("No questions found, please try again later", "No questions found")
                );
            }

            return new AppResponseBase(
                    200,
                    true,
                    "Continue with the last exam",
                    new ContinueExamDTO(
                            lastExam,
                            currentSkillStatus,
                            questionOfExams
                    ),
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

    @Transactional
    public AppResponseBase submitExam(String userId, SubmitSkillRequestDTO dataSubmit) {
        try {
            AppResponseBase currentExamResponse = getCurrentExam(userId);
            if (!currentExamResponse.isSuccess()) {
                return new AppResponseBase(
                        404,
                        false,
                        "No exam found, please start a new exam",
                        null,
                        new AppErrorBase("No exam found, please start a new exam", "No exam found")
                );
            }
            CurrentExamDTO currentExam = (CurrentExamDTO) currentExamResponse.getData();

            String skillId = dataSubmit.getSkillId();
            if (skillId == null || skillId.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "No skill found, please try again later",
                        null,
                        new AppErrorBase("No skill found, please try again later", "No skill found")
                );
            }

            Skill skillData = skillRepository.findById(skillId).orElse(null);

            QuestionDetailResponseDTO[] questions = dataSubmit.getQuestions();

            if (skillData != null && skillData.getLevels().size() != questions.length) {
                return new AppResponseBase(
                        400,
                        false,
                        "Number of questions is not equal to number of levels",
                        null,
                        new AppErrorBase("Number of questions is not equal to number of levels", "Number of questions is not equal to number of levels")
                );
            }

            if ("listening".equals(skillId)) {
                int totalScore = 0;
                for (QuestionDetailResponseDTO question : questions) {
                    ExamQuestion examQuestion = examQuestionRepository.findByExamIdAndLevelId(currentExam.getExam().getId(), question.getLevelId());

                    SubQuestionData[] subQuestions = question.getSubQuestions();
                    for (SubQuestionData subQuestion : subQuestions) {
                        if (subQuestion.getSelectedAnswerId() != null) {
                            SubQuestion subQuestionScore = subQuestionRepository.findById(subQuestion.getId()).orElse(null);

                            if (subQuestionScore.getCorrectAnswer().equals(subQuestion.getSelectedAnswerId())) {
                                totalScore += 1;
                            }

                            ExamResultListening examResultListening = new ExamResultListening();
                            examResultListening.setId(UUID.randomUUID().toString());
                            examResultListening.setExamQuestionId(examQuestion.getId());
                            examResultListening.setSubQuestionId(subQuestion.getId());
                            examResultListening.setAnswerId(subQuestion.getSelectedAnswerId());
                            examResultListeningRepository.save(examResultListening);
                        }
                    }
                }

                ExamSkillStatus examSkillStatus = examSkillStatusRepository.findByExamIdAndSkillId(currentExam.getExam().getId(), skillId);
                examSkillStatus.setStatus("FINISHED");
                examSkillStatus.setScore(totalScore);
                examSkillStatusRepository.save(examSkillStatus);
            }

            if ("reading".equals(skillId)) {
                int totalScore = 0;
                for (QuestionDetailResponseDTO question : questions) {
                    ExamQuestion examQuestion = examQuestionRepository.findByExamIdAndLevelId(currentExam.getExam().getId(), question.getLevelId());

                    SubQuestionData[] subQuestions = question.getSubQuestions();
                    for (SubQuestionData subQuestion : subQuestions) {
                        if (subQuestion.getSelectedAnswerId() != null) {
                            SubQuestion subQuestionScore = subQuestionRepository.findById(subQuestion.getId()).orElse(null);

                            if ( subQuestionScore != null && subQuestionScore.getCorrectAnswer().equals(subQuestion.getSelectedAnswerId())) {
                                totalScore += 1;
                            }

                            ExamResultReading examResultReading = new ExamResultReading();
                            examResultReading.setId(UUID.randomUUID().toString());
                            examResultReading.setExamQuestionId(examQuestion.getId());
                            examResultReading.setSubQuestionId(subQuestion.getId());
                            examResultReading.setAnswerId(subQuestion.getSelectedAnswerId());
                            examResultReadingRepository.save(examResultReading);
                        }
                    }
                }

                ExamSkillStatus examSkillStatus = examSkillStatusRepository.findByExamIdAndSkillId(currentExam.getExam().getId(), skillId);
                examSkillStatus.setStatus("FINISHED");
                examSkillStatus.setScore(totalScore);
                examSkillStatusRepository.save(examSkillStatus);
            }

            if ("writing".equals(skillId)) {
                for (QuestionDetailResponseDTO question : questions) {
                    ExamQuestion examQuestion = examQuestionRepository.findByExamIdAndLevelId(currentExam.getExam().getId(), question.getLevelId());

                    ExamResultWriting examResultWriting = new ExamResultWriting();
                    examResultWriting.setId(UUID.randomUUID().toString());
                    examResultWriting.setExamQuestionId(examQuestion.getId());
                    examResultWriting.setData(question.getQuestionData());
                    examResultWriting.setFeedback("");
                    examResultWriting.setIsRated(false);
                    examResultWriting.setPoint(0.0f);
                    examResultWritingRepository.save(examResultWriting);
                }
                ExamSkillStatus examSkillStatus = examSkillStatusRepository.findByExamIdAndSkillId(currentExam.getExam().getId(), skillId);
                examSkillStatus.setStatus("FINISHED");
                examSkillStatusRepository.save(examSkillStatus);
            }
            if ("speaking".equals(skillId)) {
                for (QuestionDetailResponseDTO question : questions) {
                    ExamQuestion examQuestion = examQuestionRepository.findByExamIdAndLevelId(currentExam.getExam().getId(), question.getLevelId());

                    List<ExamResultSpeaking> examResultSpeakings = examResultSpeakingRepository.findByExamQuestionId(examQuestion.getId());

                    if (examResultSpeakings.isEmpty() || examResultSpeakings != null) {
                        ExamResultSpeaking examResultSpeaking = new ExamResultSpeaking();
                        examResultSpeaking.setId(UUID.randomUUID().toString());
                        examResultSpeaking.setExamQuestionId(examQuestion.getId());
                        examResultSpeaking.setData(question.getQuestionData());
                        examResultSpeaking.setFeedback("");
                        examResultSpeaking.setIsRated(false);
                        examResultSpeaking.setPoint(0.0f);
                        examResultSpeakingRepository.save(examResultSpeaking);
                    }

                }
                ExamSkillStatus examSkillStatus = examSkillStatusRepository.findByExamIdAndSkillId(currentExam.getExam().getId(), skillId);
                examSkillStatus.setStatus("FINISHED");
                examSkillStatusRepository.save(examSkillStatus);
            }

            return new AppResponseBase(
                    200,
                    true,
                    "Submit exam successfully",
                    null,
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

    public AppResponseBase submitSpeakingSkill(String userId, ISpeakingQuestionSubmitDTO submitData) {
        try {
            AppResponseBase currentExamResponse = getCurrentExam(userId);
            if (!currentExamResponse.isSuccess()) {
                return currentExamResponse;
            }
            CurrentExamDTO currentExam = (CurrentExamDTO) currentExamResponse.getData();

            String skillId = submitData.getSkillId();
            if (skillId == null || skillId.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "No skill found, please try again later",
                        null,
                        new AppErrorBase("No skill found, please try again later", "No skill found")
                );
            }

            ExamQuestion examQuestion = examQuestionRepository.findByExamIdAndLevelId(currentExam.getExam().getId(), submitData.getLevelId());
            if(examQuestion == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "No question found, please try again later",
                        null,
                        new AppErrorBase("No question found, please try again later", "No question found")
                );
            }
            List<ExamResultSpeaking> examResultSpeakings = examResultSpeakingRepository.findByExamQuestionId(examQuestion.getId());
            if(!examResultSpeakings.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "This question has been submitted",
                        null,
                        new AppErrorBase("This question has been submitted", "This question has been submitted")
                );
            }
            ExamResultSpeaking examResultSpeaking = new ExamResultSpeaking();
            examResultSpeaking.setId(UUID.randomUUID().toString());
            examResultSpeaking.setExamQuestionId(examQuestion.getId());
            examResultSpeaking.setData(submitData.getAnswer());
            examResultSpeaking.setFeedback("");
            examResultSpeaking.setIsRated(false);
            examResultSpeaking.setPoint(0.0f);
            examResultSpeakingRepository.save(examResultSpeaking);

            List<ExamResultSpeaking> examResultSpeakingsAfterSave = examResultSpeakingRepository.findByExamId(currentExam.getExam().getId());
            if(examResultSpeakingsAfterSave.size() == 3) {
                ExamSkillStatus examSkillStatus = examSkillStatusRepository.findByExamIdAndSkillId(currentExam.getExam().getId(), skillId);
                examSkillStatus.setStatus("FINISHED");
                examSkillStatusRepository.save(examSkillStatus);
            }
            return new AppResponseBase(
                    200,
                    true,
                    "Submit speaking skill successfully",
                examResultSpeaking,
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

    public AppResponseBase getCurrentSpeakingQuestion(String userId) {
        try {
            // Lấy thông tin bài thi hiện tại
            AppResponseBase currentExamResponse = getCurrentExam(userId);
            if (!currentExamResponse.isSuccess()) {
                return currentExamResponse;
            }

            CurrentExamDTO currentExam = (CurrentExamDTO) currentExamResponse.getData();

            // Kiểm tra trạng thái kỹ năng "speaking"
            ExamSkillStatus examSkillStatus = examSkillStatusRepository.findByExamIdAndSkillId(
                currentExam.getExam().getId(), "speaking");

            if (examSkillStatus != null && "FINISHED".equals(examSkillStatus.getStatus())) {
                return new AppResponseBase(
                    400,
                    false,
                    "Speaking skill has been finished",
                    null,
                    new AppErrorBase("Speaking skill has been finished", "Speaking skill has been finished")
                );
            }

            // Lấy danh sách câu hỏi của kỹ năng "speaking"
            List<ExamQuestion> examSpeakingQuestions = examQuestionRepository.getListQuestion(
                currentExam.getExam().getId(), "speaking");

            // Lấy danh sách câu hỏi đã hoàn thành
            List<ExamResultSpeaking> examResultSpeakings = examResultSpeakingRepository.findByExamId(
                currentExam.getExam().getId());

            // Tìm câu hỏi chưa được trả lời
            ExamQuestion currentExamSpeakingQuestion = null;
            for (ExamQuestion question : examSpeakingQuestions) {
                boolean isSubmitted = examResultSpeakings.stream()
                    .anyMatch(result -> result.getExamQuestion().getId().equals(question.getId()));

                if (!isSubmitted) {
                    currentExamSpeakingQuestion = question;
                    break;
                }
            }

            // Nếu tất cả câu hỏi đã được trả lời, cập nhật trạng thái là FINISHED
            if (currentExamSpeakingQuestion == null) {
                if (examSkillStatus == null) {
                    examSkillStatus = new ExamSkillStatus();
                    examSkillStatus.setExamId(currentExam.getExam().getId());
                    examSkillStatus.setSkillId("speaking");
                }

                examSkillStatus.setStatus("FINISHED");
                examSkillStatusRepository.save(examSkillStatus);

                return new AppResponseBase(
                    200,
                    true,
                    "OK",
                    null,
                    null
                );
            }

            // Trả về câu hỏi chưa trả lời
            return new AppResponseBase(
                200,
                true,
                "Get speaking question successfully",
                currentExamSpeakingQuestion.getQuestionId(),
                null
            );

        } catch (RuntimeException e) {
            return new AppResponseBase(
                500,
                false,
                "Server error: " + e.getMessage(),
                null,
                new AppErrorBase("Internal Server Error", e.getMessage())
            );
        }
    }


    public AppResponseBase getScoreOfExam(String examId) {
        try {
            if(examId == null || examId.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "ExamId is required",
                        null,
                        new AppErrorBase("ExamId is required", "ExamId is required")
                );
            }
            Exam exam = examRepository.findById(examId).orElse(null);

            if(exam == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "No exam found",
                        null,
                        new AppErrorBase("No exam found", "No exam found")
                );
            }
            List<ExamSkillStatus> examSkillStatus = exam.getExamSkillStatuses();
            if(examSkillStatus == null || examSkillStatus.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "No exam skill status found",
                        null,
                        new AppErrorBase("No exam skill status found", "No exam skill status found")
                );
            }


            return new AppResponseBase(
                    200,
                    true,
                    "Get score of exam successfully",
                    new ExamScoreDTO(
                            exam.getId(),
                            exam.getExamCode(),
                            exam.getStartTime(),
                            exam.getEndTime(),
                            examSkillStatus.stream()
                                    .map(status -> new ScoreOfSkill(
                                            status.getSkillId(),
                                            (int) status.getScore(),
                                            status.getTotalQuestion(),
                                            status.getStatus(),
                                            status.getOrder()
                                    ))
                                    .toArray(ScoreOfSkill[]::new)
                    ),
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

    public AppResponseBase getResultOfExam(String examId, String skillId) {
        try {
            if (examId.isEmpty() || skillId.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "ExamId and SkillId are required",
                        null,
                        new AppErrorBase("ExamId and SkillId are required", "ExamId and SkillId are required")
                );
            }

            Exam currentExam = examRepository.findById(examId).orElse(null);

            List<ExamSkillStatus> examSkillStatuses = examSkillStatusRepository.findSkillStatusesByExamId(currentExam.getId());

            if (examSkillStatuses == null || examSkillStatuses.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "No exam skill status found, please try again later",
                        null,
                        new AppErrorBase("No exam skill status found, please try again later", "No exam skill status found")
                );
            }

            List<CurrentSkillStatus> listCurrentSkillStatus = examSkillStatuses.stream()
                    .map(examSkillStatus -> new CurrentSkillStatus(
                            examSkillStatus.getId(),
                            examSkillStatus.getExamId(),
                            examSkillStatus.getSkillId(),
                            examSkillStatus.getStartTime(),
                            examSkillStatus.getEndTime(),
                            examSkillStatus.getStatus(),
                            examSkillStatus.getOrder(),
                            new SkillExprired(
                                    examSkillStatus.getSkill().getName(),
                                    examSkillStatus.getSkill().getExpiredTime()
                            )
                    ))
                    .toList();

            if (listCurrentSkillStatus.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "No exam skill status found, please try again later",
                        null,
                        new AppErrorBase("No exam skill status found, please try again later", "No exam skill status found")
                );
            }
            CurrentSkillStatus currentSkillData = listCurrentSkillStatus.stream()
                    .filter(examSkillStatus -> examSkillStatus.getSkillId().equals(skillId))
                    .findFirst()
                    .orElse(null);
            if(currentSkillData == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "No exam skill status found, please try again later",
                        null,
                        new AppErrorBase("No exam skill status found, please try again later", "No exam skill status found")
                );
            }
            QuestionResult[] questionResult = null;
            if(skillId.equals("listening")) {
                List<ExamQuestion> examQuestion = examQuestionRepository.getListQuestion(examId, skillId);
                questionResult = examQuestion.stream()
                        .map(eq -> {
                            SubQuestionData[] listSubQuestions = eq.getQuestion().getSubQuestions().stream()
                                    .map(subQuestion -> {
                                        SubQuestionAnswer[] listAnswers = subQuestion.getAnswers().stream()
                                                .map(answer -> new SubQuestionAnswer(
                                                        answer.getId(),
                                                        answer.getSubQuestionId(),
                                                        answer.getAnswerContent(),
                                                        answer.getOrder(),
                                                        answer.isCorrect()
                                                ))
                                                .toArray(SubQuestionAnswer[]::new);
                                        return new SubQuestionData(
                                                subQuestion.getId(),
                                                eq.getQuestionId(),
                                                subQuestion.getContent(),
                                                subQuestion.getOrder(),
                                                subQuestion.getCorrectAnswer(),
                                                null,
                                                listAnswers
                                        );
                                    })
                                    .toArray(SubQuestionData[]::new);

                            ResultOfQuestion[] results = eq.getExamResultListenings().stream()
                                .map(erl -> new ResultOfQuestion(
                                    erl.getId(),
                                    erl.getSubQuestionId(),
                                    erl.getAnswerId(),
                                    null,
                                    null,
                                    false
                                ))
                                .toArray(ResultOfQuestion[]::new);
                            return new QuestionResult(
                                    eq.getId(),
                                    eq.getQuestionId(),
                                    eq.getExamId(),
                                    new QuestionDetailResponseDTO(
                                            eq.getQuestion().getId(),
                                            eq.getQuestion().getCategoryId(),
                                            eq.getQuestion().getLevelId(),
                                            eq.getQuestion().getSkillId(),
                                            eq.getQuestion().getQuestionContent(),
                                            eq.getQuestion().getDescription(),
                                            eq.getQuestion().getQuestionNote(),
                                            eq.getQuestion().getAttachedFile(),
                                            eq.getQuestion().isDeleted(),
                                            eq.getQuestion().isActive(),
                                            new CategoryShortInfo(
                                                    eq.getQuestion().getCategory().getId(),
                                                    eq.getQuestion().getCategory().getName()
                                            ),
                                            new LevelShortInfo(
                                                    eq.getQuestion().getLevel().getId(),
                                                    eq.getQuestion().getLevel().getDisplayName(),
                                                    eq.getQuestion().getLevel().getName(),
                                                    eq.getQuestion().getLevel().getDescription(),
                                                    eq.getQuestion().getLevel().getSubQuestionNumber()
                                            ),
                                            new SkillShortInfo(
                                                    eq.getQuestion().getSkillId(),
                                                    eq.getQuestion().getSkill().getDisplayName(),
                                                    eq.getQuestion().getSkill().getName()
                                            ),
                                        listSubQuestions,
                                        null
                                    ),
                                results
                            );
                        }).toArray(QuestionResult[]::new);
            }
            if(skillId.equals("reading")) {
                List<ExamQuestion> examQuestion = examQuestionRepository.getListQuestion(examId, skillId);
                questionResult = examQuestion.stream()
                    .map(eq -> {
                        SubQuestionData[] listSubQuestions = eq.getQuestion().getSubQuestions().stream()
                            .map(subQuestion -> {
                                SubQuestionAnswer[] listAnswers = subQuestion.getAnswers().stream()
                                    .map(answer -> new SubQuestionAnswer(
                                        answer.getId(),
                                        answer.getSubQuestionId(),
                                        answer.getAnswerContent(),
                                        answer.getOrder(),
                                        answer.isCorrect()
                                    ))
                                    .toArray(SubQuestionAnswer[]::new);
                                return new SubQuestionData(
                                    subQuestion.getId(),
                                    eq.getQuestionId(),
                                    subQuestion.getContent(),
                                    subQuestion.getOrder(),
                                    subQuestion.getCorrectAnswer(),
                                    null,
                                    listAnswers
                                );
                            })
                            .toArray(SubQuestionData[]::new);

                        ResultOfQuestion[] results = eq.getExamResultReadings().stream()
                            .map(erl -> new ResultOfQuestion(
                                erl.getId(),
                                erl.getSubQuestionId(),
                                erl.getAnswerId(),
                                null,
                                null,
                                false
                            ))
                            .toArray(ResultOfQuestion[]::new);
                        return new QuestionResult(
                            eq.getId(),
                            eq.getQuestionId(),
                            eq.getExamId(),
                            new QuestionDetailResponseDTO(
                                eq.getQuestion().getId(),
                                eq.getQuestion().getCategoryId(),
                                eq.getQuestion().getLevelId(),
                                eq.getQuestion().getSkillId(),
                                eq.getQuestion().getQuestionContent(),
                                eq.getQuestion().getDescription(),
                                eq.getQuestion().getQuestionNote(),
                                eq.getQuestion().getAttachedFile(),
                                eq.getQuestion().isDeleted(),
                                eq.getQuestion().isActive(),
                                new CategoryShortInfo(
                                    eq.getQuestion().getCategory().getId(),
                                    eq.getQuestion().getCategory().getName()
                                ),
                                new LevelShortInfo(
                                    eq.getQuestion().getLevel().getId(),
                                    eq.getQuestion().getLevel().getDisplayName(),
                                    eq.getQuestion().getLevel().getName(),
                                    eq.getQuestion().getLevel().getDescription(),
                                    eq.getQuestion().getLevel().getSubQuestionNumber()
                                ),
                                new SkillShortInfo(
                                    eq.getQuestion().getSkillId(),
                                    eq.getQuestion().getSkill().getDisplayName(),
                                    eq.getQuestion().getSkill().getName()
                                ),
                                listSubQuestions,
                                null
                            ),
                            results
                        );
                    }).toArray(QuestionResult[]::new);
            }

            if(skillId.equals("writing")) {
                List<ExamQuestion> examQuestion = examQuestionRepository.getListQuestion(examId, skillId);
                questionResult = examQuestion.stream()
                    .map(eq -> {

                        ResultOfQuestion[] results = eq.getExamResultWritings().stream()
                            .map(erl -> new ResultOfQuestion(
                                erl.getId(),
                                erl.getExamQuestionId(),
                                erl.getData(),
                                null,
                                null,
                                false
                            ))
                            .toArray(ResultOfQuestion[]::new);
                        return new QuestionResult(
                            eq.getId(),
                            eq.getQuestionId(),
                            eq.getExamId(),
                            new QuestionDetailResponseDTO(
                                eq.getQuestion().getId(),
                                eq.getQuestion().getCategoryId(),
                                eq.getQuestion().getLevelId(),
                                eq.getQuestion().getSkillId(),
                                eq.getQuestion().getQuestionContent(),
                                eq.getQuestion().getDescription(),
                                eq.getQuestion().getQuestionNote(),
                                eq.getQuestion().getAttachedFile(),
                                eq.getQuestion().isDeleted(),
                                eq.getQuestion().isActive(),
                                new CategoryShortInfo(
                                    eq.getQuestion().getCategory().getId(),
                                    eq.getQuestion().getCategory().getName()
                                ),
                                new LevelShortInfo(
                                    eq.getQuestion().getLevel().getId(),
                                    eq.getQuestion().getLevel().getDisplayName(),
                                    eq.getQuestion().getLevel().getName(),
                                    eq.getQuestion().getLevel().getDescription(),
                                    eq.getQuestion().getLevel().getSubQuestionNumber()
                                ),
                                new SkillShortInfo(
                                    eq.getQuestion().getSkillId(),
                                    eq.getQuestion().getSkill().getDisplayName(),
                                    eq.getQuestion().getSkill().getName()
                                ),
                                null,
                                null
                            ),
                            results
                        );
                    }).toArray(QuestionResult[]::new);
            }
            if(skillId.equals("speaking")) {
                List<ExamQuestion> examQuestion = examQuestionRepository.getListQuestion(examId, skillId);
                questionResult = examQuestion.stream()
                    .map(eq -> {

                        ResultOfQuestion[] results = eq.getExamResultSpeakings().stream()
                            .map(erl -> new ResultOfQuestion(
                                erl.getId(),
                                erl.getExamQuestionId(),
                                erl.getData(),
                                null,
                                null,
                                false
                            ))
                            .toArray(ResultOfQuestion[]::new);
                        return new QuestionResult(
                            eq.getId(),
                            eq.getQuestionId(),
                            eq.getExamId(),
                            new QuestionDetailResponseDTO(
                                eq.getQuestion().getId(),
                                eq.getQuestion().getCategoryId(),
                                eq.getQuestion().getLevelId(),
                                eq.getQuestion().getSkillId(),
                                eq.getQuestion().getQuestionContent(),
                                eq.getQuestion().getDescription(),
                                eq.getQuestion().getQuestionNote(),
                                eq.getQuestion().getAttachedFile(),
                                eq.getQuestion().isDeleted(),
                                eq.getQuestion().isActive(),
                                new CategoryShortInfo(
                                    eq.getQuestion().getCategory().getId(),
                                    eq.getQuestion().getCategory().getName()
                                ),
                                new LevelShortInfo(
                                    eq.getQuestion().getLevel().getId(),
                                    eq.getQuestion().getLevel().getDisplayName(),
                                    eq.getQuestion().getLevel().getName(),
                                    eq.getQuestion().getLevel().getDescription(),
                                    eq.getQuestion().getLevel().getSubQuestionNumber()
                                ),
                                new SkillShortInfo(
                                    eq.getQuestion().getSkillId(),
                                    eq.getQuestion().getSkill().getDisplayName(),
                                    eq.getQuestion().getSkill().getName()
                                ),
                                null,
                                null
                            ),
                            results
                        );
                    }).toArray(QuestionResult[]::new);
            }

            return new AppResponseBase(
                    200,
                    true,
                    "Get result of exam successfully",
                    new QuestionResultResponseDTO(
                            new CurrentExam(
                                    currentExam.getId(),
                                    currentExam.getUserId(),
                                    currentExam.getExamCode(),
                                    currentExam.getStartTime(),
                                    currentExam.getEndTime(),
                                    currentExam.isDeleted(),
                                    currentExam.isActive(),
                                    currentExam.isDone(),
                                    currentExam.getCreatedAt().toString(),
                                    currentExam.getUpdatedAt().toString()
                            ),
                            questionResult,
                            currentSkillData
                    ),
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

    public AppResponseBase getMyExams(String userId) {
        try {
            if(userId == null || userId.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "UserId is required",
                        null,
                        new AppErrorBase("UserId is required", "UserId is required")
                );
            }
            List<Exam> exams = examRepository.findExamsByUserId(userId);
            List<ExamScoreDTO> examScoreDTOs = exams.stream()
                    .map(exam -> new ExamScoreDTO(
                            exam.getId(),
                            exam.getExamCode(),
                            exam.getStartTime(),
                            exam.getEndTime(),
                            exam.getExamSkillStatuses().stream()
                                    .map(status -> new ScoreOfSkill(
                                            status.getSkillId(),
                                            (int) status.getScore(),
                                            status.getTotalQuestion(),
                                            status.getStatus(),
                                            status.getOrder()
                                    ))
                                    .toArray(ScoreOfSkill[]::new)
                    ))
                    .toList();
            return new AppResponseBase(
                200,
                true,
                "Get score of exam successfully",
                examScoreDTOs,
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
