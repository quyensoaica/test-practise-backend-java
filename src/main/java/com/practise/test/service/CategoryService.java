package com.practise.test.service;

import com.practise.test.dto.AppData.AppErrorBase;
import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.category.CategoryDataRequest;
import com.practise.test.entity.Category;
import com.practise.test.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public AppResponseBase getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh sách danh mục thành công",
                    categories,
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
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh mục thành công",
                    category,
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
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh sách danh mục theo cấp độ thành công",
                    categories,
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
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy danh sách danh mục theo kỹ năng thành công",
                    categories,
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
    public AppResponseBase createNewCategory(CategoryDataRequest categoryData) {
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
//            Category newCategory = new Category(
//                    UUID.randomUUID().toString(),
//                    categoryData.getName(),
//                    categoryData.getSkillId(),
//                    categoryData.getLevelId(),
//                    categoryData.getDescription(),
//                    categoryData.getImage(),
//                    categoryData.getSubCategoryNumber(),
//                    categoryData.getQuestionNumber(),
//                    categoryData.getCreatedAt(),
//                    categoryData.getUpdatedAt()
//            );

            return new AppResponseBase(
                    200,
                    true,
                    "Tạo danh mục mới thành công",
                    null,
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
