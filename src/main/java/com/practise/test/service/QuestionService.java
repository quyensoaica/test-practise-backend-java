package com.practise.test.service;

import com.practise.test.dto.AppData.AppErrorBase;
import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.question.QuestionDetailResponseDTO;
import com.practise.test.dto.question.QuestionResponseDTO;
import com.practise.test.entity.*;
import com.practise.test.model.category.CategoryShortInfo;
import com.practise.test.model.level.LevelShortInfo;
import com.practise.test.model.question.QuestionDetail;
import com.practise.test.model.question.SubQuestionAnswer;
import com.practise.test.model.question.SubQuestionData;
import com.practise.test.model.skill.SkillShortInfo;
import com.practise.test.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubQuestionRepository subQuestionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    private  AppResponseBase checkQuestionData(QuestionDetail questionDetail) {
        if (questionDetail.getQuestionContent() == null || questionDetail.getQuestionContent().isEmpty() || questionDetail.getQuestionContent().trim().isEmpty()) {
            return new AppResponseBase(
                    400,
                    false,
                    "Question content is required",
                    null,
                    new AppErrorBase("Question content is required", "Question content is required")
            );
        }
        if (questionDetail.getLevelId().isEmpty() || questionDetail.getLevelId() == null) {
            return new AppResponseBase(
                    400,
                    false,
                    "Level id is required",
                    null,
                    new AppErrorBase("Level id is required", "Level id is required")
            );
        }
        if (questionDetail.getSkillId().isEmpty() || questionDetail.getSkillId() == null) {
            return new AppResponseBase(
                    400,
                    false,
                    "Skill id is required",
                    null,
                    new AppErrorBase("Skill id is required", "Skill id is required")
            );
        }
        if (questionDetail.getCategoryId().isEmpty() || questionDetail.getCategoryId() == null) {
            return new AppResponseBase(
                    400,
                    false,
                    "Category id is required",
                    null,
                    new AppErrorBase("Category id is required", "Category id is required")
            );
        }

        Level level = levelRepository.findById(questionDetail.getLevelId()).orElse(null);

        if (questionDetail.getSubQuestions().length != level.getSubQuestionNumber()) {
            return new AppResponseBase(
                    400,
                    false,
                    "SubQuestion number must be equal to" + level.getSubQuestionNumber(),
                    null,
                    new AppErrorBase("SubQuestion number must be equal to" + level.getSubQuestionNumber(), "SubQuestion number must be equal to" + level.getSubQuestionNumber())
            );
        }

        boolean checkSubQuestionContent = true;
        for (int i = 0; i < questionDetail.getSubQuestions().length; i++) {
            if (
                    questionDetail.getSubQuestions()[i].getContent() == null ||
                    questionDetail.getSubQuestions()[i].getContent().isEmpty() ||
                    questionDetail.getSubQuestions()[i].getContent().trim().isEmpty()
            ) {
                checkSubQuestionContent = false;
                break;
            }
        }
        if (!checkSubQuestionContent) {
            return new AppResponseBase(
                    400,
                    false,
                    "Please fill all sub question content",
                    null,
                    new AppErrorBase("Please fill all sub question content", "Please fill all sub question content")
            );
        }

        boolean checkSubQuestionAnswerContent = true;
        boolean checkSubQuestionAnswerCorrect = true;
        for (int i = 0; i < questionDetail.getSubQuestions().length; i++) {
            SubQuestionAnswer[] subQuestionAnswer = questionDetail.getSubQuestions()[i].getAnswers();
            for(int j = 0; j < subQuestionAnswer.length; j++) {
                if (
                        subQuestionAnswer[j].getAnswerContent() == null ||
                        subQuestionAnswer[j].getAnswerContent().isEmpty() ||
                        subQuestionAnswer[j].getAnswerContent().trim().isEmpty()
                ) {
                    checkSubQuestionAnswerContent = false;
                    break;
                }
            }
            if (
                    questionDetail.getSubQuestions()[i].getCorrectAnswer() == null ||
                    questionDetail.getSubQuestions()[i].getCorrectAnswer().isEmpty() ||
                    questionDetail.getSubQuestions()[i].getCorrectAnswer().trim().isEmpty()
            ) {
                checkSubQuestionAnswerCorrect = false;
                break;
            }
        }
        if(!checkSubQuestionAnswerContent) {
            return new AppResponseBase(
                    400,
                    false,
                    "Please fill all sub question answer content",
                    null,
                    new AppErrorBase("Please fill all sub question answer content", "Please fill all sub question answer content")
            );
        }
        if(!checkSubQuestionAnswerCorrect) {
            return new AppResponseBase(
                    400,
                    false,
                    "Please fill all sub question correct answer",
                    null,
                    new AppErrorBase("Please fill all sub question correct answer", "Please fill all sub question correct answer")
            );
        }

        if (
                questionDetail.getSkillId().equals("listening") &&
                (questionDetail.getAttachedFile().isEmpty() || questionDetail.getAttachedFile() == null)
        ) {
            return new AppResponseBase(
                    400,
                    false,
                    "Please upload file audio for listening question",
                    null,
                    new AppErrorBase("Please upload file audio for listening question", "Please upload file audio for listening question")
            );
        }

        return new AppResponseBase(
                200,
                true,
                "Data is valid",
                questionDetail,
                null
        );
    }

    public AppResponseBase getQuestionByListIds(List<String> listQuestionIds) {
        try {
            List<Question> listQuestions = questionRepository.findByIdIn(listQuestionIds);

            List<QuestionResponseDTO> listQuestionResponseDTO = listQuestions.stream().map(
                    question -> new QuestionResponseDTO(
                            question.getId(),
                            question.getCategoryId(),
                            question.getLevelId(),
                            question.getSkillId(),
                            question.getQuestionContent(),
                            question.getDescription(),
                            question.getQuestionNote(),
                            question.getAttachedFile(),
                            question.isDeleted(),
                            question.isActive(),
                            new CategoryShortInfo(
                                    question.getCategory().getId(),
                                    question.getCategory().getName()
                            ),
                            new LevelShortInfo(
                                    question.getLevel().getId(),
                                    question.getLevel().getDisplayName(),
                                    question.getLevel().getName(),
                                    question.getLevel().getDescription(),
                                    question.getLevel().getSubQuestionNumber()
                            ),
                            new SkillShortInfo(
                                    question.getSkill().getId(),
                                    question.getSkill().getDisplayName()
                            )
                    )
            ).collect(Collectors.toList());

            return new AppResponseBase(
                    200,
                    true,
                    "Get question by list ids successfully",
                    listQuestionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }

    @Transactional
    public AppResponseBase createQuestion(List<QuestionDetail> listRequestQuestions, String userId) {
        try {
            List<String> listQuestionIds = new ArrayList<>();

            for (int i =0; i<listRequestQuestions.size(); i++) {
                AppResponseBase checkQuestionBeforeCreate = checkQuestionData(listRequestQuestions.get(i));
                if (!checkQuestionBeforeCreate.isSuccess()) {
                    return checkQuestionBeforeCreate;
                }
                QuestionDetail questionDetail = listRequestQuestions.get(i);

                Level level = levelRepository.findById(questionDetail.getLevelId()).orElse(null);
                if (level == null) {
                    return new AppResponseBase(
                            404,
                            false,
                            "Cannot find level id",
                            null,
                            new AppErrorBase("Cannot find level id", "Cannot find level id")
                    );
                }
                Skill skill = skillRepository.findById(questionDetail.getSkillId()).orElse(null);
                if (skill == null) {
                    return new AppResponseBase(
                            404,
                            false,
                            "Cannot find skill id",
                            null,
                            new AppErrorBase("Cannot find skill id", "Cannot find skill id")
                    );
                }

                Category category = categoryRepository.findById(questionDetail.getCategoryId()).orElse(null);
                if (category == null) {
                    return new AppResponseBase(
                            404,
                            false,
                            "Cannot find category id",
                            null,
                            new AppErrorBase("Cannot find category id", "Cannot find category id")
                    );
                }
                Question newQuestion = new Question();
                newQuestion.setId(questionDetail.getId());
                newQuestion.setSkillId(questionDetail.getSkillId());
                newQuestion.setLevelId(questionDetail.getLevelId());
                newQuestion.setCategoryId(questionDetail.getCategoryId());
                newQuestion.setQuestionContent(questionDetail.getQuestionContent());
                newQuestion.setDescription(questionDetail.getDescription());
                newQuestion.setAttachedFile(questionDetail.getAttachedFile());
                newQuestion.setDeleted(false);
                newQuestion.setActive(true);
                newQuestion.setCreatedBy(userId);
                newQuestion.setUpdatedBy(userId);
                newQuestion.setCategory(category);
                newQuestion.setSkill(skill);
                newQuestion.setLevel(level);

                Question savedQuestion = questionRepository.save(newQuestion);
                listQuestionIds.add(savedQuestion.getId());

                List<SubQuestionData> listSubQuestions = List.of(questionDetail.getSubQuestions());
                for (int j = 0; j < listSubQuestions.size(); j++) {
                    SubQuestionData subQuestionData = listSubQuestions.get(j);

                    SubQuestion newSubQuestion = new SubQuestion();
                    newSubQuestion.setId(subQuestionData.getId());
                    newSubQuestion.setContent(subQuestionData.getContent());
                    newSubQuestion.setQuestionId(savedQuestion.getId());
                    newSubQuestion.setDeleted(false);
                    newSubQuestion.setActive(true);
                    newSubQuestion.setCreatedBy(userId);
                    newSubQuestion.setUpdatedBy(userId);
                    newSubQuestion.setQuestion(savedQuestion);
                    newSubQuestion.setOrder(j);
                    newSubQuestion.setCorrectAnswer(subQuestionData.getCorrectAnswer());
                    SubQuestion savedSubQuestion = subQuestionRepository.save(newSubQuestion);

                    List<SubQuestionAnswer> listSubQuestionAnswers = List.of(subQuestionData.getAnswers());
                    for (int k = 0; k < listSubQuestionAnswers.size(); k++) {
                        SubQuestionAnswer subQuestionAnswer = listSubQuestionAnswers.get(k);

                        Answer newAnswer = new Answer();
                        newAnswer.setId(subQuestionAnswer.getId());
                        newAnswer.setAnswerContent(subQuestionAnswer.getAnswerContent());
                        newAnswer.setSubQuestionId(savedSubQuestion.getId());
                        newAnswer.setCreatedBy(userId);
                        newAnswer.setUpdatedBy(userId);
                        newAnswer.setSubQuestion(savedSubQuestion);
                        newAnswer.setOrder(k);
                        answerRepository.save(newAnswer);
                    }
                }
            }

            AppResponseBase response = getQuestionByListIds(listQuestionIds);
            if (!response.isSuccess()) {
                return response;
            }

            return new AppResponseBase(
                    200,
                    true,
                    "Create question successfully",
                    response.getData(),
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }

    public AppResponseBase getQuestionDetail(String id) {
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
            Question question = questionRepository.findById(id).orElse(null);
            if (question == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy câu hỏi",
                        null,
                        new AppErrorBase("Không tìm thấy câu hỏi", "Không tìm thấy câu hỏi")
                );
            }
            List<SubQuestion> listSubQuestions = question.getSubQuestions();
            List<SubQuestionData> listSubQuestionData = listSubQuestions.stream().map(
                    subQuestion -> new SubQuestionData(
                            subQuestion.getId(),
                            subQuestion.getQuestionId(),
                            subQuestion.getContent(),
                            subQuestion.getOrder(),
                            subQuestion.getCorrectAnswer(),
                            "",
                            subQuestion.getAnswers().stream().map(
                                    answer -> new SubQuestionAnswer(
                                            answer.getId(),
                                            answer.getSubQuestionId(),
                                            answer.getAnswerContent(),
                                            answer.getOrder(),
                                            answer.isCorrect()
                                    )
                            ).toArray(SubQuestionAnswer[]::new)
                    )
            ).collect(Collectors.toList());

            QuestionDetailResponseDTO questionDetailResponseDTO = new QuestionDetailResponseDTO(
                    question.getId(),
                    question.getCategoryId(),
                    question.getLevelId(),
                    question.getSkillId(),
                    question.getQuestionContent(),
                    question.getDescription(),
                    question.getQuestionNote(),
                    question.getAttachedFile(),
                    question.isDeleted(),
                    question.isActive(),
                    new CategoryShortInfo(
                            question.getCategory().getId(),
                            question.getCategory().getName()
                    ),
                    new LevelShortInfo(
                            question.getLevel().getId(),
                            question.getLevel().getDisplayName(),
                            question.getLevel().getName(),
                            question.getLevel().getDescription(),
                            question.getLevel().getSubQuestionNumber()
                    ),
                    new SkillShortInfo(
                            question.getSkill().getId(),
                            question.getSkill().getDisplayName()
                    ),
                    listSubQuestionData.toArray(SubQuestionData[]::new)
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Get question detail successfully",
                    questionDetailResponseDTO,
                    null
            );

        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }

    public AppResponseBase getQuestionById(String id) {
        try {
            if(id.isEmpty() || id == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id không được để trống",
                        null,
                        new AppErrorBase("Id không được để trống", "Id không được để trống")
                );
            }
            Question question = questionRepository.findById(id).orElse(null);
            if(question == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy câu hỏi",
                        null,
                        new AppErrorBase("Không tìm thấy câu hỏi", "Không tìm thấy câu hỏi")
                );
            }
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(
                    question.getId(),
                    question.getCategoryId(),
                    question.getLevelId(),
                    question.getSkillId(),
                    question.getQuestionContent(),
                    question.getDescription(),
                    question.getQuestionNote(),
                    question.getAttachedFile(),
                    question.isDeleted(),
                    question.isActive(),
                    new CategoryShortInfo(
                            question.getCategory().getId(),
                            question.getCategory().getName()
                    ),
                    new LevelShortInfo(
                        question.getLevel().getId(),
                        question.getLevel().getDisplayName(),
                        question.getLevel().getName(),
                        question.getLevel().getDescription(),
                        question.getLevel().getSubQuestionNumber()
                    ),
                    new SkillShortInfo(
                            question.getSkill().getId(),
                            question.getSkill().getDisplayName()
                    )
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Get question by id successfully",
                    questionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
    public AppResponseBase getAllQuestionByCategoryId(String categoryId, String status) {
        try {
            if (categoryId.isEmpty() || categoryId == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Category id không được để trống",
                        null,
                        new AppErrorBase("Category id không được để trống", "Category id không được để trống")
                );
            }
            List<Question> listQuestions = new ArrayList<>();

            if (status.equals("all")) {
                listQuestions = questionRepository.findByCategoryId(categoryId);
            } else if (status.equals("active")) {
                listQuestions = questionRepository.findByCategoryIdAndIsActive(categoryId, true);
            } else if (status.equals("inactive")) {
                listQuestions = questionRepository.findByCategoryIdAndIsActive(categoryId, false);
            } else if (status.equals("deleted")) {
                listQuestions = questionRepository.findByCategoryIdAndIsDeleted(categoryId, true);
            } else {
                return new AppResponseBase(
                        400,
                        false,
                        "Status không hợp lệ",
                        null,
                        new AppErrorBase("Status không hợp lệ", "Status không hợp lệ")
                );
            }

            List<QuestionResponseDTO> listQuestionResponseDTO = listQuestions.stream().map(
                    question -> new QuestionResponseDTO(
                            question.getId(),
                            question.getCategoryId(),
                            question.getLevelId(),
                            question.getSkillId(),
                            question.getQuestionContent(),
                            question.getDescription(),
                            question.getQuestionNote(),
                            question.getAttachedFile(),
                            question.isDeleted(),
                            question.isActive(),
                            new CategoryShortInfo(
                                    question.getCategory().getId(),
                                    question.getCategory().getName()
                            ),
                            new LevelShortInfo(
                                question.getLevel().getId(),
                                question.getLevel().getDisplayName(),
                                question.getLevel().getName(),
                                question.getLevel().getDescription(),
                                question.getLevel().getSubQuestionNumber()
                            ),
                            new SkillShortInfo(
                                    question.getSkill().getId(),
                                    question.getSkill().getDisplayName()
                            )
                    )
            ).collect(Collectors.toList());
            return new AppResponseBase(
                    200,
                    true,
                    "Get all question by category id successfully",
                    listQuestionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
    public AppResponseBase getAllQuestionBySkillId(String skillId, boolean isActive) {
        try {
            if (skillId.isEmpty() || skillId == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Skill id không được để trống",
                        null,
                        new AppErrorBase("Skill id không được để trống", "Skill id không được để trống")
                );
            }
            List<Question> listQuestions = questionRepository.findBySkillIdAndIsActive(skillId, isActive);
            List<QuestionResponseDTO> listQuestionResponseDTO = listQuestions.stream().map(
                    question -> new QuestionResponseDTO(
                            question.getId(),
                            question.getCategoryId(),
                            question.getLevelId(),
                            question.getSkillId(),
                            question.getQuestionContent(),
                            question.getDescription(),
                            question.getQuestionNote(),
                            question.getAttachedFile(),
                            question.isDeleted(),
                            question.isActive(),
                            new CategoryShortInfo(
                                    question.getCategory().getId(),
                                    question.getCategory().getName()
                            ),
                            new LevelShortInfo(
                                question.getLevel().getId(),
                                question.getLevel().getDisplayName(),
                                question.getLevel().getName(),
                                question.getLevel().getDescription(),
                                question.getLevel().getSubQuestionNumber()
                            ),
                            new SkillShortInfo(
                                    question.getSkill().getId(),
                                    question.getSkill().getDisplayName()
                            )
                    )
            ).collect(Collectors.toList());
            return new AppResponseBase(
                    200,
                    true,
                    "Get all question by skill id successfully",
                    listQuestionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
    public AppResponseBase getAllQuestionByLevelId(String levelId, boolean isActive) {
        try {
            if (levelId.isEmpty() || levelId == null) {
                return new AppResponseBase(
                        400,
                        false,
                        "Level id không được để trống",
                        null,
                        new AppErrorBase("Level id không được để trống", "Level id không được để trống")
                );
            }
            List<Question> listQuestions = questionRepository.findByLevelIdAndIsActive(levelId, isActive);
            List<QuestionResponseDTO> listQuestionResponseDTO = listQuestions.stream().map(
                    question -> new QuestionResponseDTO(
                            question.getId(),
                            question.getCategoryId(),
                            question.getLevelId(),
                            question.getSkillId(),
                            question.getQuestionContent(),
                            question.getDescription(),
                            question.getQuestionNote(),
                            question.getAttachedFile(),
                            question.isDeleted(),
                            question.isActive(),
                            new CategoryShortInfo(
                                    question.getCategory().getId(),
                                    question.getCategory().getName()
                            ),
                            new LevelShortInfo(
                                question.getLevel().getId(),
                                question.getLevel().getDisplayName(),
                                question.getLevel().getName(),
                                question.getLevel().getDescription(),
                                question.getLevel().getSubQuestionNumber()
                            ),
                            new SkillShortInfo(
                                    question.getSkill().getId(),
                                    question.getSkill().getDisplayName()
                            )
                    )
            ).collect(Collectors.toList());
            return new AppResponseBase(
                    200,
                    true,
                    "Get all question by level id successfully",
                    listQuestionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
    public AppResponseBase deleteQuestion(String id) {
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
            Question question = questionRepository.findById(id).orElse(null);
            if (question == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy câu hỏi",
                        null,
                        new AppErrorBase("Không tìm thấy câu hỏi", "Không tìm thấy câu hỏi")
                );
            }
            question.setDeleted(true);
            questionRepository.save(question);

            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(
                    question.getId(),
                    question.getCategoryId(),
                    question.getLevelId(),
                    question.getSkillId(),
                    question.getQuestionContent(),
                    question.getDescription(),
                    question.getQuestionNote(),
                    question.getAttachedFile(),
                    question.isDeleted(),
                    question.isActive(),
                    new CategoryShortInfo(
                            question.getCategory().getId(),
                            question.getCategory().getName()
                    ),
                    new LevelShortInfo(
                        question.getLevel().getId(),
                        question.getLevel().getDisplayName(),
                        question.getLevel().getName(),
                        question.getLevel().getDescription(),
                        question.getLevel().getSubQuestionNumber()
                    ),
                    new SkillShortInfo(
                            question.getSkill().getId(),
                            question.getSkill().getDisplayName()
                    )
            );

            return new AppResponseBase(
                    200,
                    true,
                    "Delete question successfully",
                    questionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
    public AppResponseBase restoreQuestion(String id) {
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
            Question question = questionRepository.findById(id).orElse(null);
            if (question == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy câu hỏi",
                        null,
                        new AppErrorBase("Không tìm thấy câu hỏi", "Không tìm thấy câu hỏi")
                );
            }
            question.setDeleted(false);
            questionRepository.save(question);

            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(
                    question.getId(),
                    question.getCategoryId(),
                    question.getLevelId(),
                    question.getSkillId(),
                    question.getQuestionContent(),
                    question.getDescription(),
                    question.getQuestionNote(),
                    question.getAttachedFile(),
                    question.isDeleted(),
                    question.isActive(),
                    new CategoryShortInfo(
                            question.getCategory().getId(),
                            question.getCategory().getName()
                    ),
                    new LevelShortInfo(
                        question.getLevel().getId(),
                        question.getLevel().getDisplayName(),
                        question.getLevel().getName(),
                        question.getLevel().getDescription(),
                        question.getLevel().getSubQuestionNumber()
                    ),
                    new SkillShortInfo(
                            question.getSkill().getId(),
                            question.getSkill().getDisplayName()
                    )
            );

            return new AppResponseBase(
                    200,
                    true,
                    "Restore question successfully",
                    questionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
    public AppResponseBase activeQuestion(String id) {
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
            Question question = questionRepository.findById(id).orElse(null);
            if (question == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy câu hỏi",
                        null,
                        new AppErrorBase("Không tìm thấy câu hỏi", "Không tìm thấy câu hỏi")
                );
            }
            question.setActive(true);
            questionRepository.save(question);

            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(
                    question.getId(),
                    question.getCategoryId(),
                    question.getLevelId(),
                    question.getSkillId(),
                    question.getQuestionContent(),
                    question.getDescription(),
                    question.getQuestionNote(),
                    question.getAttachedFile(),
                    question.isDeleted(),
                    question.isActive(),
                    new CategoryShortInfo(
                            question.getCategory().getId(),
                            question.getCategory().getName()
                    ),
                    new LevelShortInfo(
                        question.getLevel().getId(),
                        question.getLevel().getDisplayName(),
                        question.getLevel().getName(),
                        question.getLevel().getDescription(),
                        question.getLevel().getSubQuestionNumber()
                    ),
                    new SkillShortInfo(
                            question.getSkill().getId(),
                            question.getSkill().getDisplayName()
                    )
            );

            return new AppResponseBase(
                    200,
                    true,
                    "Active question successfully",
                    questionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
    public AppResponseBase inactiveQuestion(String id) {
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
            Question question = questionRepository.findById(id).orElse(null);
            if (question == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy câu hỏi",
                        null,
                        new AppErrorBase("Không tìm thấy câu hỏi", "Không tìm thấy câu hỏi")
                );
            }
            question.setActive(false);
            questionRepository.save(question);

            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(
                    question.getId(),
                    question.getCategoryId(),
                    question.getLevelId(),
                    question.getSkillId(),
                    question.getQuestionContent(),
                    question.getDescription(),
                    question.getQuestionNote(),
                    question.getAttachedFile(),
                    question.isDeleted(),
                    question.isActive(),
                    new CategoryShortInfo(
                            question.getCategory().getId(),
                            question.getCategory().getName()
                    ),
                    new LevelShortInfo(
                        question.getLevel().getId(),
                        question.getLevel().getDisplayName(),
                        question.getLevel().getName(),
                        question.getLevel().getDescription(),
                        question.getLevel().getSubQuestionNumber()
                    ),
                    new SkillShortInfo(
                            question.getSkill().getId(),
                            question.getSkill().getDisplayName()
                    )
            );

            return new AppResponseBase(
                    200,
                    true,
                    "Inactive question successfully",
                    questionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
    public AppResponseBase deleteQuestionPermanently(String id) {
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
            Question question = questionRepository.findById(id).orElse(null);
            if (question == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy câu hỏi",
                        null,
                        new AppErrorBase("Không tìm thấy câu hỏi", "Không tìm thấy câu hỏi")
                );
            }
            questionRepository.delete(question);

            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(
                    question.getId(),
                    question.getCategoryId(),
                    question.getLevelId(),
                    question.getSkillId(),
                    question.getQuestionContent(),
                    question.getDescription(),
                    question.getQuestionNote(),
                    question.getAttachedFile(),
                    question.isDeleted(),
                    question.isActive(),
                    new CategoryShortInfo(
                            question.getCategory().getId(),
                            question.getCategory().getName()
                    ),
                    new LevelShortInfo(
                        question.getLevel().getId(),
                        question.getLevel().getDisplayName(),
                        question.getLevel().getName(),
                        question.getLevel().getDescription(),
                        question.getLevel().getSubQuestionNumber()
                    ),
                    new SkillShortInfo(
                            question.getSkill().getId(),
                            question.getSkill().getDisplayName()
                    )
            );

            return new AppResponseBase(
                    200,
                    true,
                    "Delete question permanently successfully",
                    questionResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
}
