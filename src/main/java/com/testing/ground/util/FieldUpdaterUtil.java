package com.testing.ground.util;

import com.testing.ground.entity.user.UserDetail;
import com.testing.ground.request.user.UserDetailRequest;
import com.testing.ground.response.user.UserDetailResponse;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

public class FieldUpdaterUtil {
    public static void updateNonNullFields(Object source, Object target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Source and target must not be null");
        }
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            try {
                Object value = sourceField.get(source);
                if (value != null) {
                    for (Field targetField : targetFields) {
                        if (targetField.getName().equals(sourceField.getName())
                                && targetField.getType().isAssignableFrom(sourceField.getType())) {
                            targetField.setAccessible(true);
                            targetField.set(target, value);
                            break;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to update field: " + sourceField.getName(), e);
            }
        }
    }

    private UserDetail mapToEntity(UserDetailRequest userDetailRequest) {
        UserDetail entity = new UserDetail();
        BeanUtils.copyProperties(userDetailRequest, entity);
        return entity;
    }

    private UserDetailResponse mapToResponse(UserDetail entity) {
        UserDetailResponse userDetailResponse = new UserDetailResponse();
        BeanUtils.copyProperties(entity, userDetailResponse);
        return userDetailResponse;
    }

}
