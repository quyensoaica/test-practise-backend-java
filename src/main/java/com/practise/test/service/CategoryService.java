package com.practise.test.service;

import com.practise.test.dto.AppData.AppErrorBase;
import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.category.CategoryDataRequest;
import com.practise.test.entity.Category;
import com.practise.test.entity.Level;
import com.practise.test.entity.Skill;
import com.practise.test.model.category.CategoryInfo;
import com.practise.test.repository.CategoryRepository;
import com.practise.test.repository.LevelRepository;
import com.practise.test.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LevelRepository levelRepository;

    public AppResponseBase getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            Stream<CategoryInfo> categoryList = categories.stream().map(
                    category -> new CategoryInfo(
                            category.getId(),
                            category.getName(),
                            category.getDescription(),
                            category.getImage(),
                            category.isDeleted(),
                            category.getIsActive(),
                            category.getSkillId(),
                            category.getLevelId()
                    )
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh sách danh mục thành công",
                    categoryList,
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

    public AppResponseBase getCategoryById(String id) {
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
            Category category = categoryRepository.findById(id).orElse(null);

            if (category == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy danh mục",
                        null,
                        new AppErrorBase("Không tìm thấy danh mục", "Không tìm thấy danh mục")
                );
            }
            CategoryInfo categoryInfo = new CategoryInfo(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getImage(),
                    category.isDeleted(),
                    category.getIsActive(),
                    category.getSkillId(),
                    category.getLevelId()
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh mục thành công",
                    categoryInfo,
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

    public AppResponseBase getCategoryOfLevel (String levelId) {
        try {
            List<Category> categories = categoryRepository.findByLevelId(levelId, Sort.by(Sort.Direction.DESC, "createdAt"));

            List<CategoryInfo> categoryList = categories.stream().map(
                    category -> new CategoryInfo(
                            category.getId(),
                            category.getName(),
                            category.getDescription(),
                            category.getImage(),
                            category.isDeleted(),
                            category.getIsActive(),
                            category.getSkillId(),
                            category.getLevelId()
                    )
            ).toList();

            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh sách danh mục theo cấp độ thành công",
                    categoryList,
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

    public AppResponseBase getCategoryOfSkill (String skillId) {
        try {
            List<Category> categories = categoryRepository.findBySkillId(skillId, Sort.by(Sort.Direction.DESC, "createdAt"));

            List<CategoryInfo> categoryList = categories.stream().map(
                    category -> new CategoryInfo(
                            category.getId(),
                            category.getName(),
                            category.getDescription(),
                            category.getImage(),
                            category.isDeleted(),
                            category.getIsActive(),
                            category.getSkillId(),
                            category.getLevelId()
                    )
            ).toList();

            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh sách danh mục theo kỹ năng thành công",
                    categoryList,
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
    public AppResponseBase createNewCategory(CategoryDataRequest categoryData, String userId) {
        try {
            if (categoryData.getName().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Tên danh mục không được để trống",
                        null,
                        new AppErrorBase("Tên danh mục không được để trống", "Tên danh mục không được để trống")
                );
            }
            if (categoryData.getSkillId().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id kỹ năng không được để trống",
                        null,
                        new AppErrorBase("Id kỹ năng không được để trống", "Id kỹ năng không được để trống")
                );
            }
            if (categoryData.getLevelId().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id cấp độ không được để trống",
                        null,
                        new AppErrorBase("Id cấp độ không được để trống", "Id cấp độ không được để trống")
                );
            }
            Optional<Skill> skillResponse = skillRepository.findById(categoryData.getSkillId());
            if (skillResponse.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy kỹ năng",
                        null,
                        new AppErrorBase("Không tìm thấy kỹ năng", "Không tìm thấy kỹ năng")
                );
            }
            Optional<Level> levelResponse = levelRepository.findById(categoryData.getLevelId());
            if (levelResponse.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy cấp độ",
                        null,
                        new AppErrorBase("Không tìm thấy cấp độ", "Không tìm thấy cấp độ")
                );
            }

            Category newCategory = new Category();
            newCategory.setId(UUID.randomUUID().toString());
            newCategory.setName(categoryData.getName());
            newCategory.setDescription(categoryData.getDescription());
            newCategory.setImage(categoryData.getImage());
            newCategory.setDeleted(false);
            newCategory.setIsActive(true);
            newCategory.setCreatedBy(userId);
            newCategory.setSkill(skillResponse.get());
            newCategory.setLevel(levelResponse.get());
            newCategory.setSkillId(categoryData.getSkillId());
            newCategory.setLevelId(categoryData.getLevelId());

            categoryRepository.save(newCategory);

            CategoryInfo categoryInfo = new CategoryInfo(
                    newCategory.getId(),
                    newCategory.getName(),
                    newCategory.getDescription(),
                    newCategory.getImage(),
                    newCategory.isDeleted(),
                    newCategory.getIsActive(),
                    newCategory.getSkillId(),
                    newCategory.getLevelId()
            );

            return new AppResponseBase(
                    200,
                    true,
                    "Tạo danh mục mới thành công",
                    categoryInfo,
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
    public AppResponseBase updateCategory(String id, CategoryDataRequest categoryData, String userId) {
        try {
            if (id.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id danh mục không được để trống",
                        null,
                        new AppErrorBase("Id danh mục không được để trống", "Id danh mục không được để trống")
                );
            }
            if (categoryData.getName().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Tên danh mục không được để trống",
                        null,
                        new AppErrorBase("Tên danh mục không được để trống", "Tên danh mục không được để trống")
                );
            }
            if (categoryData.getSkillId().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id kỹ năng không được để trống",
                        null,
                        new AppErrorBase("Id kỹ năng không được để trống", "Id kỹ năng không được để trống")
                );
            }
            if (categoryData.getLevelId().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id cấp độ không được để trống",
                        null,
                        new AppErrorBase("Id cấp độ không được để trống", "Id cấp độ không được để trống")
                );
            }
            Optional<Skill> skillResponse = skillRepository.findById(categoryData.getSkillId());
            if (skillResponse.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy kỹ năng",
                        null,
                        new AppErrorBase("Không tìm thấy kỹ năng", "Không tìm thấy kỹ năng")
                );
            }
            Optional<Level> levelResponse = levelRepository.findById(categoryData.getLevelId());
            if (levelResponse.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy cấp độ",
                        null,
                        new AppErrorBase("Không tìm thấy cấp độ", "Không tìm thấy cấp độ")
                );
            }

            Category category = categoryRepository.findById(categoryData.getId()).orElse(null);
            if (category == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy danh mục",
                        null,
                        new AppErrorBase("Không tìm thấy danh mục", "Không tìm thấy danh mục")
                );
            }

            category.setName(categoryData.getName());
            category.setDescription(categoryData.getDescription());
            category.setImage(categoryData.getImage());
            category.setUpdatedBy(userId);
            category.setSkill(skillResponse.get());
            category.setLevel(levelResponse.get());
            category.setSkillId(categoryData.getSkillId());
            category.setLevelId(categoryData.getLevelId());

            categoryRepository.save(category);

            CategoryInfo categoryInfo = new CategoryInfo(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getImage(),
                    category.isDeleted(),
                    category.getIsActive(),
                    category.getSkillId(),
                    category.getLevelId()
            );

            return new AppResponseBase(
                    200,
                    true,
                    "Cập nhật danh mục thành công",
                    categoryInfo,
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

    public AppResponseBase deleteCategory(String id) {
        try {
            if (id.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id danh mục không được để trống",
                        null,
                        new AppErrorBase("Id danh mục không được để trống", "Id danh mục không được để trống")
                );
            }
            Category category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy danh mục",
                        null,
                        new AppErrorBase("Không tìm thấy danh mục", "Không tìm thấy danh mục")
                );
            }
            category.setDeleted(true);
            categoryRepository.save(category);

            CategoryInfo categoryInfo = new CategoryInfo(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getImage(),
                    category.isDeleted(),
                    category.getIsActive(),
                    category.getSkillId(),
                    category.getLevelId()
            );

            return new AppResponseBase(
                    200,
                    true,
                    "Xóa danh mục thành công",
                    categoryInfo,
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

    public AppResponseBase deleteCategoryPermanently(String id) {
        try {
            if (id.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id danh mục không được để trống",
                        null,
                        new AppErrorBase("Id danh mục không được để trống", "Id danh mục không được để trống")
                );
            }
            Category category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy danh mục",
                        null,
                        new AppErrorBase("Không tìm thấy danh mục", "Không tìm thấy danh mục")
                );
            }
            categoryRepository.delete(category);
            CategoryInfo categoryInfo = new CategoryInfo(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getImage(),
                    category.isDeleted(),
                    category.getIsActive(),
                    category.getSkillId(),
                    category.getLevelId()
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Xóa danh mục vĩnh viễn thành công",
                    categoryInfo,
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
    public AppResponseBase restoreCategory(String id) {
        try {
            if (id.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id danh mục không được để trống",
                        null,
                        new AppErrorBase("Id danh mục không được để trống", "Id danh mục không được để trống")
                );
            }
            Category category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy danh mục",
                        null,
                        new AppErrorBase("Không tìm thấy danh mục", "Không tìm thấy danh mục")
                );
            }
            category.setDeleted(false);
            categoryRepository.save(category);
            CategoryInfo categoryInfo = new CategoryInfo(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getImage(),
                    category.isDeleted(),
                    category.getIsActive(),
                    category.getSkillId(),
                    category.getLevelId()
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Khôi phục danh mục thành công",
                    categoryInfo,
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

    public AppResponseBase activeCategory(String id) {
        try {
            if (id.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id danh mục không được để trống",
                        null,
                        new AppErrorBase("Id danh mục không được để trống", "Id danh mục không được để trống")
                );
            }
            Category category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy danh mục",
                        null,
                        new AppErrorBase("Không tìm thấy danh mục", "Không tìm thấy danh mục")
                );
            }
            category.setIsActive(true);
            categoryRepository.save(category);
            CategoryInfo categoryInfo = new CategoryInfo(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getImage(),
                    category.isDeleted(),
                    category.getIsActive(),
                    category.getSkillId(),
                    category.getLevelId()
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Kích hoạt danh mục thành công",
                    categoryInfo,
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
    public AppResponseBase inactiveCategory(String id) {
        try {
            if (id.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id danh mục không được để trống",
                        null,
                        new AppErrorBase("Id danh mục không được để trống", "Id danh mục không được để trống")
                );
            }
            Category category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy danh mục",
                        null,
                        new AppErrorBase("Không tìm thấy danh mục", "Không tìm thấy danh mục")
                );
            }
            category.setIsActive(false);
            categoryRepository.save(category);
            CategoryInfo categoryInfo = new CategoryInfo(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getImage(),
                    category.isDeleted(),
                    category.getIsActive(),
                    category.getSkillId(),
                    category.getLevelId()
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Vô hiệu hóa danh mục thành công",
                    categoryInfo,
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
